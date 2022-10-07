package cn.nulladev.sheathmagic.item.conceptcore;

public class ConceptCoreShulkerShell extends BaseConceptCore {
    public ConceptCoreShulkerShell(Properties properties) {
        super(properties);
    }

    @Override
    public int getCooldown() {
        return 200;
    }
}
