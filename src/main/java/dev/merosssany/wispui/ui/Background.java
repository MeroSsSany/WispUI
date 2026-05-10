package dev.merosssany.wispui.ui;

import dev.merosssany.wispui.event.input.mouse.MouseButtonEvent;
import dev.merosssany.wispui.event.input.mouse.MouseHoverEvent;
import dev.merosssany.wispui.renderer.UIRenderer;
import dev.merosssany.wispui.ui.base.UI;
import dev.merosssany.wispui.ui.base.component.Scale;

/**
 * A built-in UI component that is designed to always cover the entire window.
 * It automatically subscribes to WindowResizedEvent to maintain full-screen size.
 */
public class Background extends UI {
    public Scale scale = new Scale(1, 1);
    
    public Background(UIRenderer renderer) {
        super(renderer);
    }
    
    @Override
    public void onMouseClicked(MouseButtonEvent e, int x, int y) {
        // Background usually doesn't handle clicks, unless it's blocking
    }
    
    @Override
    public void onMouseHover(MouseHoverEvent e) {
        // Background usually doesn't need hover logic
    }
    
    @Override
    public void onMouseHoverEnded() {
        // Background usually doesn't need hover logic
    }
    
    @Override
    public void cleanup() {
    
    }
    
    @Override
    public void draw() {
        setWidth(scale.getWidth());
        setHeight(scale.getHeight());
        super.draw();
    }
    
    /**
     * Builder for the Background UI element, enforcing full-screen and centered properties.
     */
    public static class Builder extends UI.Builder {}
}
