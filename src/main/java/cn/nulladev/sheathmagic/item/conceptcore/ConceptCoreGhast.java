package cn.nulladev.sheathmagic.item.conceptcore;

public class ConceptCoreGhast extends BaseConceptCore{
    public ConceptCoreGhast(Properties properties) {
        super(properties);
    }

    @Override
    public int getCooldown() {
        return 200;
    }
}
