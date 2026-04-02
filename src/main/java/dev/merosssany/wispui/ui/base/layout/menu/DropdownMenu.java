package dev.merosssany.wispui.ui.base.layout.menu;

import dev.merosssany.wispui.Display;
import dev.merosssany.wispui.VectorMath;
import dev.merosssany.wispui.Window;
import dev.merosssany.wispui.event.SubscribeEvent;
import dev.merosssany.wispui.event.bus.EventBus;
import dev.merosssany.wispui.event.input.mouse.MouseButtonEvent;
import dev.merosssany.wispui.ui.base.UI;
import dev.merosssany.wispui.ui.base.interactive.button.Button;
import dev.merosssany.wispui.ui.base.layout.Anchor;
import dev.merosssany.wispui.ui.base.layout.Container;
import dev.merosssany.wispui.ui.base.layout.Scene;
import dev.merosssany.wispui.ui.base.layout.grid.StatelessGrid;
import dev.merosssany.wispui.ui.base.layout.menu.scroll.ScrollableMenu;
import org.joml.Vector2i;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * An interactive selection component that reveals a list of options when clicked.
 * <p>
 * The {@code DropdownMenu} functions as a specialized button that toggles the
 * visibility of an attached {@link ScrollableMenu}. It uses a {@link StatelessGrid}
 * to organize the options vertically within the dropdown list.
 * </p>
 *
 *
 *
 * <h2>Input & Focus Handling</h2>
 * <p>
 * This component utilizes global event listening via {@link EventBus} to implement
 * "Click-to-Dismiss" behavior. If a user clicks anywhere outside both the header
 * and the expanded menu, the dropdown automatically closes.
 * </p>
 *
 * @author Infinity Two Games
 */
public abstract class DropdownMenu extends Button implements Container {
    protected final Scene scene;
    protected final StatelessGrid grid;
    protected ScrollableMenu menu;
    protected Map<String, Button> selections = new ConcurrentHashMap<>();
    protected String selected;
    
    private final List<UI> temp;
    
    public DropdownMenu(Scene scene, String path, Window window) {
        super(scene, path, "> None");
        menu = new ScrollableMenu(scene, window);
        temp = List.of(menu);
        
        tip = "None";
        this.scene = scene;
        
        menu.setParent(this);
        menu.setAnchor(new Anchor(0,1));
        menu.setDrawOrder(100);
        menu.setHidden(true);
        
        grid = new StatelessGrid(1, 0, 16, new Vector2i());
        
        EventBus.connect(this);
    }
    
    /**
     * Adds a new selectable option to the dropdown list.
     * <p>
     * Automatically creates a child {@link Button} that, when clicked,
     * triggers the {@link #select(String)} logic.
     * </p>
     * @param selection The text label and ID for the option.
     */
    public void addOption(String selection) {
        Button button = new Button(scene, path, selection) {
            @Override
            public void click(int x, int y, int action, int button) {
                select(selection);
            }
        };
        button.setSize(getWidth(), getHeight());
        button.setColor(1,1,1,1);
        button.setDrawOrder(menu.getDrawOrder()+1);
        
        grid.add(button);
        menu.addUI(button);
        selections.put(selection, button);
    }
    
    /**
     * Updates the component state to reflect a chosen option.
     * <p>
     * Sets the header text to the selected option, closes the menu,
     * and triggers the abstract {@link #onSelect(String)} callback.
     * </p>
     * @param selection The string ID of the option to select.
     */
    public void select(String selection) {
        Button button = selections.get(selection);
        if (button != null) {
            menu.setHidden(true);
            selected = selection;
            setText(selection);
            onSelect(selected);
        }
    }
    
    public void setMenuHeight(int height) {
        menu.setHeight(height);
    }
    
    @Override
    public void setWidth(int width) {
        super.setWidth(width);
        menu.setWidth(width);
        grid.setCellSizeX(width);
    }
    
    @Override
    public void setHeight(int height) {
        super.setHeight(height);
        grid.setCellSizeY(height);
    }
    
    @Override
    public void draw() {
        super.draw();
        menu.draw();
    }
    
    @Override
    public void setBackgroundColor(float r, float g, float b, float a) {
        super.setBackgroundColor(r, g, b, a);
        menu.setBackgroundColor(r,g,b,a);
    }
    
    @Override
    public void setBorderColor(float r, float g, float b, float a) {
        super.setBorderColor(r, g, b, a);
        menu.setBorderColor(r,g,b,a);
    }
    
    @Override
    public void setBorderThickness(float borderThickness) {
        super.setBorderThickness(borderThickness);
        menu.setBorderThickness(borderThickness);
    }
    
    @Override
    public void setCornerRadius(float cornerRadius) {
        super.setCornerRadius(cornerRadius);
        menu.setCornerRadius(cornerRadius);
    }
    
    /**
     * Global mouse listener to handle menu dismissal.
     * <p>
     * If the click occurs outside the bounds of the dropdown and its header,
     * the menu is hidden to maintain a clean UI state.
     * </p>
     */
    @SubscribeEvent
    public void onMouseClickGlobal(MouseButtonEvent e) {
        Vector2i point = Display.transformWindowToVirtual(e.window, e.x, e.y);
        if (
                !VectorMath.isPointWithinRectangle(getPosition(), point, getEndPoint())
                && (!VectorMath.isPointWithinRectangle(menu.getPosition(), point, menu.getEndPoint()))
        ) {
            menu.setHidden(true);
        }
    }
    
    @Override
    public Vector2i getLastDrawEndPoint() {
        // If the menu is closed, use the normal button bounds
        if (menu.isHidden()) {
            return super.getLastDrawEndPoint();
        }
        
        // If open, the clickable area extends to the bottom of the menu
        return new Vector2i(
                super.getEndPoint().x,
                menu.getLastDrawPosition().y + menu.getHeight()
        );
    }
    
    @Override
    public void click(int x, int y, int action, int button) {
        menu.setHidden(!menu.isHidden());
    }
    
    @Override
    public void cleanup() {
        super.cleanup();
        menu.close();
    }
    
    @Override
    public List<UI> getUIs() {
        return temp;
    }
    
    public void clear() {
        grid.clear();
        menu.clear(); // This is correct, because this calls close() on UIs
        selections.clear();
    }
    
    /**
     * Callback triggered when a user makes a selection from the dropdown.
     * @param selected The text of the chosen option.
     */
    public abstract void onSelect(String selected);
}
