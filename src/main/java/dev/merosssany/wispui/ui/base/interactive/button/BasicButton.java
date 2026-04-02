package dev.merosssany.wispui.ui.base.interactive.button;


import dev.merosssany.wispui.VectorMath;
import dev.merosssany.wispui.data.RGBA;
import dev.merosssany.wispui.event.input.mouse.MouseButtonEvent;
import dev.merosssany.wispui.event.input.mouse.MouseHoverEvent;
import dev.merosssany.wispui.renderer.UIRenderer;
import dev.merosssany.wispui.ui.base.UI;

import static org.lwjgl.glfw.GLFW.GLFW_RELEASE;

/**
 * A lightweight, text-less interactive component.
 * <p>
 * This class provides the bare essentials for mouse interaction: hover detection
 * with visual feedback and a safe click-release trigger. It is ideal for
 * use cases where text rendering is not required or is handled by child elements.
 * </p>
 *
 * <h2>Interaction Flow</h2>
 * <ul>
 * <li><b>Release-to-Trigger:</b> To prevent accidental clicks, the {@link #clicked}
 * callback only fires if the mouse button is released while still inside
 * the button's boundaries.</li>
 * <li><b>Visual Dimming:</b> Uses a direct color subtraction logic to darken
 * the background upon hover, providing immediate user feedback.</li>
 * </ul>
 *
 * @author Infinity Two Games
 */
public abstract class BasicButton extends UI {
    protected RGBA original = backgroundColor.copy();
    
    public BasicButton(UIRenderer renderer) {
        super(renderer);
    }
    
    @Override
    public void onMouseClicked(MouseButtonEvent e, int x, int y) {
        if (e.action == GLFW_RELEASE) {
            if (VectorMath.isPointWithinRectangle(getLastDrawPosition(),e.x,e.y, getLastDrawEndPoint())) {
                clicked(e);
            }
        }
    }
    
    @Override
    public void onMouseHover(MouseHoverEvent e) {
        super.setBackgroundColor(
                original.r() - 0.25f,
                original.g() - 0.25f,
                original.b() - 0.25f,
                original.a()
        );
    }
    
    @Override
    public void setBackgroundColor(float r, float g, float b, float a) {
        super.setBackgroundColor(r, g, b, a);
        original.set(r,g,b,a);
    }
    
    @Override
    public void onMouseHoverEnded() {
        backgroundColor.set(original);
    }
    
    @Override
    public void cleanup() {
    
    }
    
    public abstract void clicked(MouseButtonEvent e);
}
