package dev.merosssany.wispui.ui;

import dev.merosssany.wispui.data.Constants;
import dev.merosssany.wispui.event.input.mouse.MouseButtonEvent;
import dev.merosssany.wispui.event.input.mouse.MouseHoverEvent;
import dev.merosssany.wispui.event.state.WindowResizedEvent;
import dev.merosssany.wispui.renderer.FontRenderer;
import dev.merosssany.wispui.ui.base.UI;
import dev.merosssany.wispui.ui.base.component.Scale;
import dev.merosssany.wispui.ui.base.component.Text;
import dev.merosssany.wispui.ui.base.layout.Anchor;
import dev.merosssany.wispui.ui.base.layout.Pivot;
import dev.merosssany.wispui.ui.base.layout.Scene;

public class Tooltip extends UI {
    protected Text text;
    protected Scale scale = new Scale(0.75f, 0.15f);
    protected FontRenderer fontRenderer;
    
    public Tooltip(Scene scene) {
        super(scene.getRenderer());
        
        fontRenderer = new FontRenderer(Constants.fontFilePath, 64);
        
        setBackgroundColor(0, 0, 0, 0.5f);
        
        setPosition(new Anchor(0.5f, 1), new Pivot(0.5f, 1));
        text = new Text(fontRenderer, scene);
        text.setPosition(new Anchor(0.5f, 0.5f), new Pivot(0.5f, 0.5f));
        text.setParent(this);
        
        scale.windowResize(new WindowResizedEvent(scene.getWindow()));
    }
    
    public void setText(String text) {
        this.text.setText(text);
    }
    
    public String getText() {
        return text.getText();
    }
    
    @Override
    public void onMouseClicked(MouseButtonEvent e, int x, int y) {}
    
    @Override
    public void onMouseHover(MouseHoverEvent e) {
    
    }
    
    @Override
    public void onMouseHoverEnded() {
    
    }
    
    @Override
    public void cleanup() {
        fontRenderer.cleanup();
    }
    
    @Override
    public void draw() {
        setSize(scale.getWidth(), scale.getHeight());
        super.draw();
    }
}
