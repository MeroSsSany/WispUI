package dev.merosssany.wispui.ui.base;

public interface UIBuilder {
    <T extends AbstractUI> void apply(T ui);
    <T extends AbstractUI> T build();
}
