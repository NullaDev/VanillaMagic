package cn.nulladev.sheathmagic.item.conceptcore;

public class ConceptCoreFarm extends BaseConceptCore {
    public ConceptCoreFarm(Properties properties) {
        super(properties);
    }

    @Override
    public int getCooldown() {
        return 600;
    }
}
