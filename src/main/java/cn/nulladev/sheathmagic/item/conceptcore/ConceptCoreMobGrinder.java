package cn.nulladev.sheathmagic.item.conceptcore;

public class ConceptCoreMobGrinder extends BaseConceptCore{
    public ConceptCoreMobGrinder(Properties properties) {
        super(properties);
    }

    @Override
    public int getCooldown() {
        return 100;
    }
}
