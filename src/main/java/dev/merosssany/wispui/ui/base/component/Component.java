package dev.merosssany.wispui.ui.base.component;

import dev.merosssany.wispui.ui.base.AbstractUI;

public interface Component {
    void draw();
    void setAngle(float angle);
    void setDrawOrder(int z);
    int getDrawOrder();
    void cleanup();
    
    void setParent(AbstractUI ui);
    
    Component copy();
}
