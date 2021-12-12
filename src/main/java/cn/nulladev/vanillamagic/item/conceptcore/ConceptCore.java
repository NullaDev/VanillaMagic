package cn.nulladev.vanillamagic.item.conceptcore;

import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.UseOnContext;

public abstract class ConceptCore extends Item {

    public int CD;

    public ConceptCore(Properties props) {
        super(props.stacksTo(1));
        this.setCD(10);
    }

    public abstract InteractionResult wandUse(UseOnContext ctx);

    public void setCD(int CD) {
        this.CD = CD;
    }
}
