package com.lcy0x1.base;

import com.lcy0x1.core.util.Automator;
import com.lcy0x1.core.util.ExceptionHandler;
import com.lcy0x1.core.util.SerialClass;
import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.Connection;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.Objects;

@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
@SerialClass
public class BaseTileEntity extends BlockEntity {

    public BaseTileEntity(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
    }

    @Override
    public void load(CompoundTag tag) {
        if (tag.contains("auto-serial"))
            ExceptionHandler.run(() -> Automator.fromTag(tag.getCompound("auto-serial"), getClass(), this, f -> true));
        super.load(tag);
    }

    @Override
    public void saveAdditional(CompoundTag tag) {
        super.saveAdditional(tag);
        CompoundTag ser = ExceptionHandler.get(() -> Automator.toTag(new CompoundTag(), getClass(), this, f -> true));
        if (ser != null) tag.put("auto-serial", ser);
    }

    @Nullable
    @Override
    public Packet<ClientGamePacketListener> getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    @Override
    public void onDataPacket(Connection net, ClientboundBlockEntityDataPacket pkt) {
        ExceptionHandler.run(() -> Automator.fromTag(Objects.requireNonNull(pkt.getTag()), getClass(), this, SerialClass.SerialField::toClient));
        super.onDataPacket(net, pkt);
    }

    public void sync() {
        if (level != null) {
            level.sendBlockUpdated(getBlockPos(), getBlockState(), getBlockState(), 3);
        }
    }

    public CompoundTag getUpdateTag() {
        return Objects.requireNonNull(ExceptionHandler.get(() -> Automator.toTag(new CompoundTag(), getClass(), this, SerialClass.SerialField::toClient)));
    }

}
