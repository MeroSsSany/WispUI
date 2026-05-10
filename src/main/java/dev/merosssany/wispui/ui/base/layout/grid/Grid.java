package dev.merosssany.wispui.ui.base.layout.grid;

import dev.merosssany.wispui.event.input.mouse.MouseButtonEvent;
import dev.merosssany.wispui.event.input.mouse.MouseHoverEvent;
import dev.merosssany.wispui.renderer.UIRenderer;
import dev.merosssany.wispui.ui.base.AbstractUI;
import dev.merosssany.wispui.ui.base.UI;
import dev.merosssany.wispui.ui.base.layout.Container;
import dev.merosssany.wispui.ui.base.layout.Scene;
import org.joml.Vector2i;

import java.util.*;

/**
 * A heavy-duty UI container that arranges elements into a rigid row-and-column structure.
 * <p>
 * The {@code Grid} class is responsible for both the positioning of its children
 * and its own visual representation. It supports dynamic row calculation and
 * relative cell sizing.
 * </p>
 *
 *
 *
 * <h2>Dynamic Sizing</h2>
 * <p>
 * If {@code cellSize} values are set to negative numbers, the grid will
 * automatically calculate cell dimensions based on the grid's total size
 * divided by the number of columns/rows (Percentage-based sizing).
 * </p>
 */
public class Grid extends UI implements Container {
    protected Map<AbstractUI, Cell> uis = new LinkedHashMap<>();
    protected int columns;
    protected int rows;
    protected int space;
    protected int padding;
    protected Vector2i cellSize = new Vector2i();
    
    protected boolean layoutDirty = true;
    private final List<AbstractUI> temp = Collections.synchronizedList(new ArrayList<>());
    
    public Grid(Scene renderer) {
        this(renderer.getRenderer());
    }
    
    public Grid(UIRenderer renderer) {
        super(renderer);
    }
    
    public int getColumns() {
        return columns;
    }
    
    public int getRows() {
        return rows;
    }
    
    public int getSpace() {
        return space;
    }
    
    public int getPadding() {
        return padding;
    }
    
    public AbstractUI get(int row, int column) {
        return get(row * columns + column);
    }
    
    public AbstractUI get(int index) {
        int i = 0;
        for (AbstractUI ui : uis.keySet()) {
            if (i++ == index)
                return ui;
        }
        return null;
    }
    
    public void updateSize() {
        int totalWidth = (columns * cellSize.x) + ((columns - 1) * space) + (padding * 2);
        int totalHeight = (rows * cellSize.y) + ((rows - 1) * space) + (padding * 2);
        super.setSize(totalWidth, totalHeight);
    }
    
    @Override
    public void setSize(int width, int height) {
        super.setSize(width, height);
        layoutDirty = true;
    }
    
    @Override
    public void onMouseClicked(MouseButtonEvent e, int x, int y) {
    
    }
    
    private List<AbstractUI> getTempUIs() {
        temp.clear();
        temp.addAll(uis.keySet());
        return temp;
    }
    
    @Override
    public void onMouseHover(MouseHoverEvent e) {
    
    }
    
    @Override
    public void onMouseHoverEnded() {
    
    }
    
    @Override
    public void cleanup() {
        for (AbstractUI ui : uis.keySet()) {
            ui.close();
        }
        
        uis.clear();
        temp.clear();
    }
    
    @Override
    public void draw() {
        if (layoutDirty) {
            System.out.println("Updating layout");
            layout();
            updateSize();
            layoutDirty = false;
        }
        
        super.draw(); // Draw the grid's background/border
        
        for (AbstractUI ui : uis.keySet()) {
            ui.draw();
        }
    }
    
    /**
     * The core positioning logic. Calculates exact pixel offsets for
     * every child based on padding, spacing, and cell dimensions.
     */
    private void layout() {
        for (Map.Entry<AbstractUI, Cell> entry : uis.entrySet()) {
            AbstractUI ui = entry.getKey();
            Cell cell = entry.getValue();
            
            // Calculate dimensions
            int w = cellSize.x < 0? getWidth() / Math.max(1, columns) : cellSize.x;
            int h = cellSize.y < 0? getHeight() / Math.max(1, rows) : cellSize.y;
            
            ui.setSize(w, h);
            ui.setOffset(
                    (cell.x * (w + space)) + padding,
                    (cell.y * (h + space)) + padding
            );
        }
    }
    
    /**
     * Adds a UI element to a specific coordinate.
     *
     * @param ui
     *         The element to add.
     * @param row
     *         The vertical index.
     * @param column
     *         The horizontal index.
     *
     * @return The new size of the child map.
     */
    public int put(AbstractUI ui, int row, int column) {
        ui.setParent(this);
        layoutDirty = true;
        
        uis.put(ui, new Cell(column, row)); // column = x, row = y
        return uis.size() - 1;
    }
    
    public void addUI(AbstractUI ui) {
        int index = uis.size();
        int row = index / columns;
        int col = index % columns;
        put(ui, row, col);
        
        // Update rows count dynamically if needed
        this.rows = (int) Math.ceil((double) uis.size() / columns);
        this.layoutDirty = true;
    }
    
    public void setCellSize(int size) {
        setCellSize(size, size);
    }
    
    public void setCellSize(int width, int height) {
        cellSize.set(width, height);
    }
    
    public List<AbstractUI> getUIs() {
        return getTempUIs();
    }
    
    public void setPadding(int padding) {
        this.padding = padding;
        updateSize();
    }
    
    public void remove(AbstractUI ui) {
        uis.remove(ui);
    }
    
    public void clearCells() {
        uis.clear();
    }
    
    protected record Cell(int x, int y) {
    }
    
    public static class Builder extends UI.Builder {
        protected Map<AbstractUI, Cell> uis = new LinkedHashMap<>();
        protected int columns;
        protected int rows;
        protected int space;
        protected int padding;
        protected Vector2i cellSize = new Vector2i();
        
        public int put(AbstractUI ui, int row, int column) {
            uis.put(ui, new Cell(column, row)); // column = x, row = y
            return uis.size() - 1;
        }
        
        public int columns() {
            return columns;
        }
        
        public Builder columns(int columns) {
            this.columns = columns;
            return this;
        }
        
        public int rows() {
            return rows;
        }
        
        public Builder rows(int rows) {
            this.rows = rows;
            return this;
        }
        
        public int space() {
            return space;
        }
        
        public Builder space(int space) {
            this.space = space;
            return this;
        }
        
        public int padding() {
            return padding;
        }
        
        public Builder padding(int padding) {
            this.padding = padding;
            return this;
        }
        
        public Vector2i cellSize() {
            return cellSize;
        }
        
        public Builder cellSize(Vector2i cellSize) {
            this.cellSize.set(cellSize);
            return this;
        }
        
        public void apply(Grid g) {
            uis.keySet().forEach(ui -> ui.setParent(g));
            g.columns = columns;
            g.cellSize = cellSize;
            g.padding = padding;
            g.layoutDirty = true;
            g.rows = rows;
            g.space = space;
            g.uis = uis;
        }
        
        public Builder cellSize(int sizeX, int sizeY) {
            cellSize.set(sizeX, sizeY);
            return this;
        }
    }
}
