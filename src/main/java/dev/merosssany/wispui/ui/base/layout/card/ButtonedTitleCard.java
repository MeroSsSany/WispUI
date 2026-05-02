package dev.merosssany.wispui.ui.base.layout.card;

import dev.merosssany.wispui.ui.base.interactive.Clickable;
import dev.merosssany.wispui.ui.base.interactive.button.Button;
import dev.merosssany.wispui.ui.base.layout.Scene;

import java.util.ArrayList;
import java.util.List;

public class ButtonedTitleCard extends TitledCard {
    protected List<Clickable> buttons;
    protected float anchorX;
    
    private boolean layoutDirty;
    
    public ButtonedTitleCard(Scene scene, String font, int textHeight, int titleHeight) {
        super(scene, font, textHeight, titleHeight);
        
        buttons = new ArrayList<>();
    }
    
    public void addButton(Button button) {
        layoutDirty = true;
        buttons.add(button);
    }
    
    public void removeButton(Button button) {
        layoutDirty = true;
        buttons.remove(button);
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
}
