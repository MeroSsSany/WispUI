package dev.merosssany.wispui.ui.base.component;

import dev.merosssany.wispui.ui.base.UI;

// MODIFIED
public interface Component {
    void draw();
    void setAngle(float angle);
    void setDrawOrder(int z);
    int getDrawOrder();
    void cleanup();
    
    void setParent(UI ui);
    
    Component copy();
}
