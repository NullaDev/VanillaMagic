package cn.nulladev.vanillamagic.block;

import cn.nulladev.vanillamagic.client.CollectorMenu;
import cn.nulladev.vanillamagic.core.VMRegistry;
import cn.nulladev.vanillamagic.item.conceptcore.ConceptCore;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.WorldlyContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BaseContainerBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.state.BlockState;

import java.util.stream.IntStream;

public class CollectorBE extends BaseContainerBlockEntity implements WorldlyContainer {

    protected NonNullList<ItemStack> items = NonNullList.withSize(CollectorMenu.SIZE, ItemStack.EMPTY);
    public static BlockEntityTicker<CollectorBE> ticker = (level, pos, state, blockEntity) -> blockEntity.serverTick(level, pos, state, blockEntity);

    private void serverTick(Level level, BlockPos pos, BlockState state, CollectorBE be) {
        ItemStack coreStack = be.getItem(0);
        if (coreStack.getItem() instanceof ConceptCore) {
            if (ConceptCore.getCD(coreStack) > 0) {
                ConceptCore.setCD(coreStack, ConceptCore.getCD(coreStack) - 1);
            } else {
                ConceptCore core = (ConceptCore) coreStack.getItem();
                boolean flag = false;
                for (ItemStack s : core.getMachineOutputs(coreStack)) {
                    if (!s.isEmpty() && be.addStack(s))
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

    public boolean addStack(ItemStack stack) {
        for (int i=0; i<items.size(); i++) {
            if (items.get(i) == ItemStack.EMPTY) {
                items.set(i, stack);
                return true;
            } else if(items.get(i).sameItem(stack)) {
                int num = items.get(i).getMaxStackSize() - items.get(i).getCount();
                if (num > 0) {
                    if (stack.getCount() <= num) {
                        items.get(i).grow(stack.getCount());
                        return true;
                    } else {
                        items.get(i).grow(num);
                        stack.shrink(num);
                        return addStack(stack);
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
        return new CollectorMenu(id, inv);
    }

    @Override
    public int getContainerSize() {
        return this.items.size();
    }

    @Override
    public boolean isEmpty() {
        for(ItemStack itemstack : this.items) {
            if (!itemstack.isEmpty()) {
                return false;
            }
        }
        return true;
    }

    @Override
    public ItemStack getItem(int index) {
        return this.items.get(index);
    }

    @Override
    public ItemStack removeItem(int index, int num) {
        return ContainerHelper.removeItem(this.items, index, num);
    }

    @Override
    public ItemStack removeItemNoUpdate(int index) {
        return ContainerHelper.takeItem(this.items, index);
    }

    @Override
    public void setItem(int index, ItemStack stack) {
        this.items.set(index, stack);
    }

    @Override
    public boolean stillValid(Player p_18946_) {
        return this.level.getBlockEntity(this.worldPosition) == this;
    }

    @Override
    public void clearContent() {
        this.items.clear();
    }

    @Override
    public void load(CompoundTag tag) {
        super.load(tag);
        this.items = NonNullList.withSize(this.getContainerSize(), ItemStack.EMPTY);
        ContainerHelper.loadAllItems(tag, this.items);
    }

    @Override
    protected void saveAdditional(CompoundTag tag) {
        super.saveAdditional(tag);
        ContainerHelper.saveAllItems(tag, this.items);
    }

    @Override
    public int[] getSlotsForFace(Direction direc) {
        if (direc == Direction.DOWN) {
            return new int[]{0};
        } else {
            return IntStream.rangeClosed(1,27).toArray();
        }
    }

    @Override
    public boolean canPlaceItemThroughFace(int index, ItemStack stack, Direction direc) {
        return this.canPlaceItem(index, stack);
    }

    @Override
    public boolean canTakeItemThroughFace(int index, ItemStack stack, Direction direc) {
        return true;
    }

    @Override
    public boolean canPlaceItem(int index, ItemStack stack) {
        if (index == 0)
            return stack.getItem() instanceof ConceptCore;
        else
            return false;
    }
}
