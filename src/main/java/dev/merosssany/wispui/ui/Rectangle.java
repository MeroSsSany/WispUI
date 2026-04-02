package dev.merosssany.wispui.ui;

import dev.merosssany.wispui.data.RGBA;
import dev.merosssany.wispui.event.input.mouse.MouseButtonEvent;
import dev.merosssany.wispui.event.input.mouse.MouseHoverEvent;
import dev.merosssany.wispui.renderer.UIRenderer;
import dev.merosssany.wispui.ui.base.UI;
import dev.merosssany.wispui.ui.base.UIBuilder;

public class Rectangle extends UI {
    public Builder builder(UIRenderer renderer) {
        return new Builder(renderer);
    }
    
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
    
    public static class Builder extends UIBuilder<Rectangle> {
        public Builder(UIRenderer renderer) {
            super(new Rectangle(renderer));
        }
        
        @Override
        public UIBuilder<Rectangle> applyDefault() {
            return this;
        }
        
        public Rectangle build() {
            return ui;
        }
    }
    
    public static class RectangleBuilder extends UIBuilder<Rectangle> {
        public RectangleBuilder(UIRenderer renderer, Rectangle element) {
            super(element);
        }
        
        @Override
        public UIBuilder<Rectangle> applyDefault() {
            backgroundColor(new RGBA(0, 0, 0, 1));
            return this;
        }
    }
}
