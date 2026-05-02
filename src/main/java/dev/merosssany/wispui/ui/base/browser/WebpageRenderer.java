package dev.merosssany.wispui.ui.base.browser;

import dev.merosssany.wispui.data.Texture;
import dev.merosssany.wispui.data.template.texture.TextureParameter;
import org.cef.browser.CefBrowser;
import org.lwjgl.BufferUtils;

import java.awt.*;
import java.nio.ByteBuffer;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class WebpageRenderer implements IWebpageRenderer {
    protected Texture texture;
    protected final TextureParameter parameter;
    private final Queue<Runnable> tasks = new ConcurrentLinkedQueue<>();
    
    public WebpageRenderer(int width, int height) {
        parameter = new CefTexParam();
        texture = new Texture(width, height, parameter);
    }
    
    @Override
    public void paint(
            CefBrowser browser,
            Rectangle[] dirtyRects,
            int width, int height,
            ByteBuffer renderedFrame,
            boolean popup
    ) {
        System.out.println("RENDERED");
        ByteBuffer copy = BufferUtils.createByteBuffer(renderedFrame.remaining());
        copy.put(renderedFrame);
        copy.flip();
        
        tasks.add(() -> texture.update(copy, width, height, parameter));
    }
    
    public void executeTask() {
        Runnable runnable = tasks.poll();
        
        if (runnable != null) runnable.run();
    }
    
    public void executeTasks() {
        Runnable runnable;
        while ((runnable = tasks.poll()) != null) runnable.run();
    }
    
    @Override
    public Texture getTexture() {
        return texture;
    }
}
