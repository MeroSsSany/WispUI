package dev.merosssany.wispui.ui.base.layout.card;

import dev.merosssany.wispui.ui.base.interactive.ClickableUI;
import dev.merosssany.wispui.ui.base.interactive.button.Button;
import dev.merosssany.wispui.ui.base.layout.Scene;

import java.util.ArrayList;
import java.util.List;

public class ButtonedTitleCard extends TitledCard {
    protected List<ClickableUI> buttons;
    protected float anchorX;
    
    private boolean layoutDirty;
    
    public ButtonedTitleCard(Scene scene, String font, int textHeight, int titleHeight) {
        super(scene, font, textHeight, titleHeight);
        
        buttons = new ArrayList<>();
    }
    
    public void addButton(Button button) {
        layoutDirty = true;
        button.setParent(this);
        buttons.add(button);
    }
    
    public void removeButton(Button button) {
        layoutDirty = true;
        button.setParent(this);
        buttons.remove(button);
    }
    
    public void rebuild() {
        layoutDirty = true;
    }
    
    public boolean isLayoutDirty() {
        return layoutDirty;
    }
    
    @Override
    public void setHeight(int height) {
        super.setHeight(height);
        layoutDirty = true;
    }
    
    @Override
    public void setWidth(int width) {
        super.setWidth(width);
        layoutDirty = true;
    }
    
    @Override
    public void draw() {
        super.draw();
        update();
        
        buttons.forEach(ClickableUI::draw);
    }
    
    protected void update() {
        if (layoutDirty) {
            int totalWidth = 0;
            for (ClickableUI button : buttons) {
                button.setAnchor(anchorX, 1);
                button.setPivot(anchorX, 1);
                button.setOffset(totalWidth);
                
                totalWidth += 32 + button.getWidth();
            }
        }
    }
    
    public static class Builder extends TitledCard.Builder {
        protected List<ClickableUI> buttons;
        protected float anchorX;
        
        private boolean layoutDirty;
        
        public List<ClickableUI> buttons() {
            return buttons;
        }
        
        public Builder buttons(List<ClickableUI> buttons) {
            this.buttons = buttons;
            return this;
        }
        
        public float anchorX() {
            return anchorX;
        }
        
        public Builder anchorX(float anchorX) {
            this.anchorX = anchorX;
            return this;
        }
        
        public boolean layoutDirty() {
            return layoutDirty;
        }
        
        public Builder layoutDirty(boolean layoutDirty) {
            this.layoutDirty = layoutDirty;
            return this;
        }
    }
}
