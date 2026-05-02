package dev.merosssany.wispui.ui.base.layout;

import dev.merosssany.wispui.ui.base.AbstractUI;
import dev.merosssany.wispui.ui.base.UI;

import java.util.List;

public interface Container {
    List<AbstractUI> getUIs();
    void addUI(AbstractUI ui);
    void draw();
    void setWidth(int width);
    void setHeight(int height);
}
