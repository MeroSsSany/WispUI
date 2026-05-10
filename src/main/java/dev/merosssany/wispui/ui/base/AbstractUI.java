package dev.merosssany.wispui.ui.base;

import dev.merosssany.wispui.data.RGBA;
import dev.merosssany.wispui.event.input.mouse.MouseButtonEvent;
import dev.merosssany.wispui.event.input.mouse.MouseHoverEvent;
import dev.merosssany.wispui.manager.Mouse;
import dev.merosssany.wispui.renderer.UIRenderer;
import dev.merosssany.wispui.ui.base.component.Component;
import dev.merosssany.wispui.ui.base.layout.Anchor;
import dev.merosssany.wispui.ui.base.layout.Pivot;
import org.joml.Vector2i;

import java.util.Map;

public interface AbstractUI extends Comparable<AbstractUI>, AutoCloseable {
    Mouse.CursorType getCursorType();
    void setCursorType(Mouse.CursorType type);
    
    void addComponent(Map<String, Component> components);
    void addComponent(String name, Component component);
    void addComponent(Component component);
    <T extends Component> T getComponent(String name);
    <T extends Component> T getComponent(Class<T> componentClass);
    
    void removeComponent(String component);
    void removeAllComponents();
    
    boolean isHovering();
    void setHovering(boolean hovering);
    
    Vector2i getPosition();
    Vector2i getLastDrawPosition();
    
    void setRenderer(UIRenderer renderer);
    
    void setPosition(Anchor anchor, Pivot pivot);
    void setPosition(Anchor anchor, Pivot pivot, Vector2i offset);
    
    AbstractUI getParent();
    void setParent(AbstractUI ui);
    
    Vector2i getOffset();
    Vector2i getMutableOffset();
    
    void setOffset(int same);
    void setOffset(Vector2i offset);
    void setOffset(int x, int y);
    
    void addOffset(Vector2i v);
    void addOffset(int x, int y);
    void addOffset(int same);
    
    Anchor getAnchor();
    
    void setAnchor(Anchor anchor);
    void setAnchor(float same);
    void setAnchor(float x, float y);
    
    Pivot getPivot();
    
    void setPivot(float x, float y);
    void setPivot(Pivot pivot);
    void setPivot(float same);
    
    int getWidth();
    void setWidth(int width);
    
    int getHeight();
    void setHeight(int height);
    
    @Deprecated String getTip();
    @Deprecated void setTip(String tip);
    @Deprecated boolean isDisplayTip();
    @Deprecated void setDisplayTip(boolean displayTip);
    
    RGBA getBackgroundColor();
    
    void setBackgroundColor(RGBA backgroundColor);
    void setBackgroundColor(float r, float g, float b, float a);
    
    void draw();
    
    Vector2i getEndPoint();
    Vector2i getLastDrawEndPoint();
    
    void setSize(Vector2i size);
    void setSize(int width, int height);
    void setSize(int same);
    
    void set(AbstractUI ui);
    
    boolean contains(int px, int py);
    
    float getAngle();
    void setAngle(float angle);
    
    boolean isHidden();
    void setHidden(boolean hidden);
    
    int getDrawOrder();
    void setDrawOrder(int drawOrder);
    
    float getBorderThickness();
    void setBorderThickness(float borderThickness);
    
    RGBA getBorderColor();
    
    void setBorderColor(RGBA borderColor);
    void setBorderColor(float r, float g, float b, float a);
    
    float getCornerRadius();
    void setCornerRadius(float cornerRadius);
    
    @Override
    default int compareTo(AbstractUI ui) {
        return Integer.compare(getDrawOrder(), ui.getDrawOrder());
    }
    
    @Override
    default void close() {
        removeAllComponents();
        cleanup();
    }
    
    void onMouseClicked(MouseButtonEvent event, int relativeX, int relativeY);
    void onMouseHover(MouseHoverEvent e);
    void onMouseHoverEnded();
    void cleanup();
    
}
