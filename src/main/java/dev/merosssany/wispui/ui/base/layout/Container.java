package dev.merosssany.wispui.ui.base.layout;

import dev.merosssany.wispui.ui.base.UI;

import java.util.List;

public interface Container {
    List<UI> getUIs();
    void addUI(UI ui);
    void draw();
    void setWidth(int width);
    void setHeight(int height);
}
