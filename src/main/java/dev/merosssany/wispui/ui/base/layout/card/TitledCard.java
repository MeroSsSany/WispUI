package dev.merosssany.wispui.ui.base.layout.card;

import dev.merosssany.wispui.data.RGBA;
import dev.merosssany.wispui.ui.base.Label;
import dev.merosssany.wispui.ui.base.layout.*;

public class TitledCard extends Panel implements Container {
    protected Label title;
    protected Label text;
    protected final int textHeight, titleHeight;
    
    public TitledCard(Scene scene, String font, int textHeight, int titleHeight) {
        super(scene);
        this.textHeight = textHeight;
        this.titleHeight = titleHeight;
        
        title = new Label(scene, font);
        title.setParent(this);
        
        text.setTextPosition(new Anchor(0.1f, 0), new Pivot());
    }
    
    @Override
    public void setHeight(int height) {
        super.setHeight(height);
        title.setHeight((int) (height * 0.1));
        text.setHeight((int) (height * 0.9));
    }
    
    @Override
    public void setWidth(int width) {
        super.setWidth(width);
        title.setWidth(width);
        text.setWidth(width);
    }
    
    @Override
    public void draw() {
        super.draw();
        title.draw();
        text.draw();
    }
    
    public String getTitleText() {
        return title.getText();
    }
    
    public void setTitleText(String text) {
        title.setText(text);
    }
    
    public RGBA getTitleColor() {
        return title.getColor();
    }
    
    public void setTitleColor(RGBA color) {
        title.setColor(color);
    }
    
    public void setTitleColor(float r, float g, float b, float a) {
        title.setColor(r, g, b, a);
    }
    
    public String getText() {
        return text.getText();
    }
    
    public void setText(String text) {
        this.text.setText(text);
    }
    
    public RGBA getColor() {
        return text.getColor();
    }
    
    public void setColor(RGBA color) {
        text.setColor(color);
    }
    
    public void setColor(float r, float g, float b, float a) {
        text.setColor(r, g, b, a);
    }
    
    public static class Builder {
        protected Label title;
        protected Label text;
        protected int textHeight, titleHeight;
        
        public Label title() {
            return title;
        }
        
        public Builder title(Label title) {
            this.title = title;
            return this;
        }
        
        public Label text() {
            return text;
        }
        
        public Builder text(Label text) {
            this.text = text;
            return this;
        }
        
        public int textHeight() {
            return textHeight;
        }
        
        public int titleHeight() {
            return titleHeight;
        }
        
        public Builder textHeight(int textHeight) {
            this.textHeight = textHeight;
            return this;
        }
        
        public Builder titleHeight(int titleHeight) {
            this.titleHeight = titleHeight;
            return this;
        }
    }
}
