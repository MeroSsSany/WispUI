package dev.merosssany.wispui.ui.base.browser;

import dev.merosssany.wispui.data.Texture;
import org.cef.browser.CefBrowser;
import org.cef.browser.CefPaintEvent;

import java.awt.*;
import java.nio.ByteBuffer;

public interface IWebpageRenderer {
    default void run(CefPaintEvent event) {
        paint(event.getBrowser(), event.getDirtyRects(), event.getWidth(), event.getHeight(), event.getRenderedFrame(), event.getPopup());
    }
    
    void paint(CefBrowser browser, Rectangle[] dirtyRects, int width, int height, ByteBuffer renderedFrame, boolean popup);
    void executeTask();
    void executeTasks();
    Texture getTexture();
}
