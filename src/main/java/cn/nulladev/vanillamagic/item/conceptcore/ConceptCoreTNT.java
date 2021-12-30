package cn.nulladev.vanillamagic.item.conceptcore;

import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.item.PrimedTnt;
import net.minecraft.world.item.context.UseOnContext;

public class ConceptCoreTNT extends ConceptCore {

    public ConceptCoreTNT(Properties props) {
        super(props);
        this.setUsingCD(5);
    }

    @Override
    public InteractionResult wandUse(UseOnContext ctx) {
        double x = ctx.getClickedPos().getX()+0.5D;
        double y = ctx.getClickedPos().getY()+1.5D;
        double z = ctx.getClickedPos().getZ()+0.5D;
        PrimedTnt primedtnt = new PrimedTnt(ctx.getLevel(), x, y, z, null);
        ctx.getLevel().addFreshEntity(primedtnt);
        return InteractionResult.SUCCESS;
    }
}