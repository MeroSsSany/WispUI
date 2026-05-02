package dev.merosssany.wispui.ui.base.layout.card;

import dev.merosssany.wispui.ui.base.Label;
import dev.merosssany.wispui.ui.base.layout.*;

public class TitledCard extends Panel implements Container {
    protected Label title;
    protected Label text;
    protected int textHeight, titleHeight;
    
    public TitledCard(Scene scene, String font) {
        super(scene);
        
        title = new Label(scene, font);
        title.setParent(this);
        
        text.setTextPosition(new Anchor(0.1f, 0), new Pivot());
    }
    
    @Override
    public void setWidth(int width) {
        super.setWidth(width);
        title.setWidth(width);
    }
    
    @Override
    public void draw() {
        title.draw();
        text.draw();
    }
}
