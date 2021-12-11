package cn.nulladev.vanillamagic.item;

import cn.nulladev.vanillamagic.VMCreativeTab;
import net.minecraft.world.item.Item;

public class ConceptCore extends Item {
    public ConceptCore(String type) {
        super(new Properties().tab(VMCreativeTab.INSTANCE));
        this.setRegistryName("concept_core_" + type);
    }
}
