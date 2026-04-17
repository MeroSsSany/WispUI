package dev.merosssany.wispui.ui.menu;

import dev.merosssany.wispui.Window;
import dev.merosssany.wispui.ui.base.UI;
import dev.merosssany.wispui.ui.base.layout.Scene;
import dev.merosssany.wispui.ui.base.layout.grid.Grid;
import dev.merosssany.wispui.ui.base.layout.grid.VerticalGrid;
import dev.merosssany.wispui.ui.base.layout.menu.scroll.ScrollableMenu;

import java.util.Collection;

public class VerticalList extends ScrollableMenu {
    protected Grid items;
    
    public VerticalList(Scene scene, Window window) {
        super(scene, window);
        items = new VerticalGrid(scene);
        
        super.addUI(items);
    }
    
    @Override
    public void setHeight(int height) {
        super.setHeight(height);
        items.setHeight(height - getPadding() *2);
    }
    
    @Override
    public void setWidth(int width) {
        super.setWidth(width);
        items.setWidth(width - getPadding() *2);
    }
    
    @Override
    public void addUI(UI ui) {
        items.addUI(ui);
    }
    
    @Override
    public void removeUI(UI ui) {
        items.remove(ui);
    }
    
    @Override
    public void removeAll(Collection<? extends UI> collection) {
        super.removeAll(collection);
    }
    
    @Override
    public void clear() {
        super.clear();
    }
}
