package dev.merosssany.wispui.ui;

import dev.merosssany.wispui.event.input.mouse.MouseButtonEvent;
import dev.merosssany.wispui.event.input.mouse.MouseHoverEvent;
import dev.merosssany.wispui.renderer.UIRenderer;
import dev.merosssany.wispui.ui.base.UI;

public class Rectangle extends UI {
    public Rectangle(UIRenderer renderer) {
        super(renderer);
    }
    
    @Override
    public void onMouseClicked(MouseButtonEvent e, int x, int y) {
    
    }
    
    @Override
    public void onMouseHover(MouseHoverEvent e) {
    
    }
    
    @Override
    public void onMouseHoverEnded() {
    
    }
    
    @Override
    public void cleanup() {
    
    }
    
    public static class Builder extends UI.Builder {}
}
