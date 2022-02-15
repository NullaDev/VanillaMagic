package cn.nulladev.vanillamagic.block;

import cn.nulladev.vanillamagic.client.CollectorMenu;
import cn.nulladev.vanillamagic.core.VMRegistry;
import cn.nulladev.vanillamagic.item.conceptcore.ConceptCore;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.WorldlyContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BaseContainerBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.wrapper.SidedInvWrapper;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.stream.IntStream;

public class CollectorBE extends BaseContainerBlockEntity implements WorldlyContainer {

    public static final String TAG_ITEMS = "Items";
    protected SimpleContainer container = new SimpleContainer(CollectorMenu.SIZE);

    public static BlockEntityTicker<CollectorBE> ticker = (level, pos, state, blockEntity) -> blockEntity.serverTick(level, pos, state, blockEntity);

    private void serverTick(Level level, BlockPos pos, BlockState state, CollectorBE be) {
        ItemStack coreStack = be.getItem(0);
        if (coreStack.getItem() instanceof ConceptCore) {
            if (ConceptCore.getCD(coreStack) > 0) {
                ConceptCore.setCD(coreStack, ConceptCore.getCD(coreStack) - 1);
            } else {
                ConceptCore core = (ConceptCore) coreStack.getItem();
                if (!core.canProvideItem(coreStack))
                    return;
                boolean flag = false;
                List<ItemStack> list = core.getMachineOutputs(coreStack);
                if (list.size() == 0)
                    flag = true;
                for (ItemStack s : list) {
                    if (be.addStack(s))
                        flag = true;
                }
                if (flag)
                    ConceptCore.setCD(coreStack, core.UsingCD);
            }
        }
    }

    public CollectorBE(BlockPos pos, BlockState blockState) {
        super(VMRegistry.BE_COLLECTOR.get(), pos, blockState);
    }

    public SimpleContainer getContainer() {
        return this.container;
    }

    public boolean addStack(ItemStack stack) {
        if (stack.isEmpty())
            return false;
        setChanged();
        for (int i = 0; i < container.getContainerSize(); i++) {
            if (container.getItem(i).isEmpty()) {
                container.setItem(i, stack);
                return true;
            } else if (container.getItem(i).sameItem(stack)) {
                int num = container.getItem(i).getMaxStackSize() - container.getItem(i).getCount();
                if (num > 0) {
                    if (stack.getCount() <= num) {
                        container.getItem(i).grow(stack.getCount());
                        return true;
                    } else {
                        container.getItem(i).grow(num);
                        stack.shrink(num);
                        addStack(stack);
                        return true;
                    }
                }
            }
        }
        return false;
    }

    @Override
    protected Component getDefaultName() {
        return new TranslatableComponent("container.collector");
    }

    @Override
    protected AbstractContainerMenu createMenu(int id, Inventory inv) {
        return new CollectorMenu(id, inv, this.worldPosition, this.level);
    }

    @Override
    public int getContainerSize() {
        return this.container.getContainerSize();
    }

    @Override
    public boolean isEmpty() {
        return this.container.isEmpty();
    }

    @Override
    public ItemStack getItem(int index) {
        return this.container.getItem(index);
    }

    @Override
    public ItemStack removeItem(int index, int num) {
        this.setChanged();
        return this.container.removeItem(index, num);
    }

    @Override
    public ItemStack removeItemNoUpdate(int index) {
        return this.container.removeItemNoUpdate(index);
    }

    @Override
    public void setItem(int index, ItemStack stack) {
        this.setChanged();
        this.container.setItem(index, stack);
    }

    @Override
    public boolean stillValid(Player p_18946_) {
        return this.level.getBlockEntity(this.worldPosition) == this;
    }

    @Override
    public void clearContent() {
        this.setChanged();
        this.container.clearContent();
    }

    protected void saveAdditional(CompoundTag tag) {
        super.saveAdditional(tag);
        tag.put("items", container.createTag());
    }

    public void load(CompoundTag tag) {
        super.load(tag);
        if (tag.contains("items")) {
            container.fromTag((ListTag) tag.get("items"));
        }

    }

    @Override
    public int[] getSlotsForFace(Direction direc) {
        System.out.println(direc);
        if (direc == Direction.UP) {
            return new int[]{0};
        } else {
            return IntStream.rangeClosed(1, 27).toArray();
        }
    }

    @Override
    public boolean canPlaceItemThroughFace(int index, ItemStack stack, Direction direc) {
        return this.canPlaceItem(index, stack);
    }

    @Override
    public boolean canTakeItemThroughFace(int index, ItemStack stack, Direction direc) {
        return !(stack.getItem() instanceof ConceptCore);
    }

    @Override
    public boolean canPlaceItem(int index, ItemStack stack) {
        if (index == 0)
            return stack.getItem() instanceof ConceptCore;
        else
            return false;
    }

    @Override
    public <T> LazyOptional<T> getCapability(Capability<T> cap, @Nullable Direction side) {
        if (cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
            return (LazyOptional<T>) LazyOptional.of(() -> new SidedInvWrapper(this, side));
        } else return super.getCapability(cap, side);
    }
}
