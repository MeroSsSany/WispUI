package dev.merosssany.wispui.ui.base.component;

import dev.merosssany.wispui.data.RGBA;
import dev.merosssany.wispui.data.Texture;
import dev.merosssany.wispui.event.input.mouse.MouseButtonEvent;
import dev.merosssany.wispui.event.input.mouse.MouseHoverEvent;
import dev.merosssany.wispui.renderer.UIRenderer;
import dev.merosssany.wispui.ui.base.UI;

/**
 * A UI component dedicated to rendering full, standalone textures.
 * <p>
 * This class is ideal for large-scale graphics like backgrounds or portraits.
 * It supports a {@code tint} color for dynamic color modulation (e.g., fading
 * an image to red or transparency).
 * </p>
 *
 *
 *
 * <h2>Key Features</h2>
 * <ul>
 * <li><b>Direct Rendering:</b> Uses {@code queueTextureDirect} to render the
 * texture in its entirety, mapped to the component's current bounds.</li>
 * <li><b>Color Modulation:</b> The {@code tint} (stored as {@code foregroundColor})
 * acts as a multiplier for the texture's colors.</li>
 * <li><b>Lifecycle Management:</b> Automatically calls {@code texture.cleanup()}
 * to free GPU memory when the UI component is closed.</li>
 * </ul>
 */
public class Image extends UI implements Component {
    protected Texture texture;
    protected RGBA tint = new RGBA(1,1,1,1);
    
    public Image(UIRenderer renderer) {
        super(renderer);
    }
    
    public void setForegroundColor(float r, float g, float b, float a) {
        tint.set(r, g, b, a);
    }
    
    public void setForegroundColor(RGBA color) {
        tint.set(color);
    }
    
    public Texture getTexture() {
        return texture;
    }
    
    public void setTexture(Texture texture) {
        this.texture = texture;
    }
    
    @Override
    public void draw() {
        if (texture == null) return;
        renderer.queueTextureDirect(
                texture,
                tint,
                this
        );
    }
    
    @Override
    public void onMouseClicked(MouseButtonEvent e, int x, int y) {
    
    }
    
    @Override
    public void onMouseHover(MouseHoverEvent e) {
    
    }
    
    @Override
    public void onMouseHoverEnded() {
    
    }
    
    @Override
    public void cleanup() {
        if (texture == null) return;
        texture.cleanup();
    }
    
    @Override
    public Component copy() {
        return new Image(renderer);
    }
    
    public static class Builder extends UI.Builder {
        protected Texture texture;
        protected RGBA tint = new RGBA(1,1,1,1);
        private UIRenderer renderer;
        
        public Texture texture() {
            return texture;
        }
        
        public Builder texture(Texture texture) {
            this.texture = texture;
            return this;
        }
        
        public RGBA tint() {
            return tint;
        }
        
        public Builder tint(RGBA tint) {
            this.tint = tint;
            return this;
        }
        
        public UIRenderer renderer() {
            return renderer;
        }
        
        public Builder renderer(UIRenderer renderer) {
            this.renderer = renderer;
            return this;
        }
        
        public Image build() {
            Image image = new Image(renderer);
            apply(image);
            image.tint = tint;
            image.texture = texture;
            return image;
        }
    }
}
