package dev.merosssany.wispui.ui.base.browser;

import dev.merosssany.wispui.AWTExceptionHandler;
import dev.merosssany.wispui.event.input.mouse.MouseButtonEvent;
import dev.merosssany.wispui.event.input.mouse.MouseHoverEvent;
import dev.merosssany.wispui.ui.base.Label;
import dev.merosssany.wispui.ui.base.UI;
import dev.merosssany.wispui.ui.base.component.Image;
import dev.merosssany.wispui.ui.base.layout.Scene;
import me.friwi.jcefmaven.*;
import org.cef.CefApp;
import org.cef.CefClient;
import org.cef.CefSettings;
import org.cef.browser.CefBrowser;

import java.awt.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Consumer;

public class Browser extends UI {
    private static final AtomicBoolean created = new AtomicBoolean();
    
    private static volatile CefApp app;
    private static volatile EnumProgress progress;
    private static volatile float downloaded;
    
    private boolean initialized;
    
    protected String url;
    protected CefClient client;
    protected CefBrowser browser;
    protected final IWebpageRenderer webpageRenderer;
    protected final Label progressLabel;
    protected final Image frame;
    
    public static void init(Consumer<Throwable> exceptionConsumer) {
        if (created.get()) return;
        
        System.setProperty("java.awt.headless", "false");
        System.setProperty("sun.awt.exception.handler", AWTExceptionHandler.class.getName());
        Toolkit.getDefaultToolkit(); // Forces AWT init
        Thread.currentThread().setUncaughtExceptionHandler((t, e) -> e.printStackTrace());
        Thread.setDefaultUncaughtExceptionHandler((t, e) -> e.printStackTrace());
        
        EventQueue.invokeLater(() ->
                Thread.currentThread().setUncaughtExceptionHandler((t, e) -> e.printStackTrace())
        );
        
        Thread thread = getThread(exceptionConsumer);
        thread.start();
    }
    
    private static Thread getThread(Consumer<Throwable> exceptionConsumer) {
        Thread thread = new Thread(() -> {
            try {
                CefAppBuilder builder = new CefAppBuilder();
                
                // 1. PROJECT-SPECIFIC CACHE (Avoids singleton conflicts)
                builder.getCefSettings().root_cache_path = System.getProperty("user.dir") + "/.jcef_cache";
                
                builder.getCefSettings().windowless_rendering_enabled = true;
                
                builder.setProgressHandler((enumProgress, v) -> {
                    progress = enumProgress;
                    downloaded = v;
                });
                
                // This is where the magic (or crash) happens
                app = builder.build();
                created.set(true);
                
            } catch (Throwable e) {
                e.printStackTrace(); // Log it to console immediately
                exceptionConsumer.accept(e);
            }
        });
        
        // Don't make it a daemon if it's the primary engine for your UI
        // thread.setDaemon(true);
        return thread;
    }
    
    public Browser(Scene scene, IWebpageRenderer webpage, String url, String fontUrl) {
        super(scene.getRenderer());
        
        initApp(url);
        this.url = url;
        webpageRenderer = webpage;
        
        progressLabel = new Label(scene, fontUrl);
        progressLabel.setParent(this);
        progressLabel.setAnchor(0.5f);
        progressLabel.setSize(512, 64);
        
        frame = new Image(scene.getRenderer());
        frame.setTexture(webpage.getTexture());
    }
    
    private void initApp(String url) {
        if (app == null) return;
        Thread.setDefaultUncaughtExceptionHandler((t, e) -> e.printStackTrace());
        
        client = app.createClient();
        browser = client.createBrowser(url, true, false);
        browser.createImmediately();
        
        EventQueue.invokeLater(() -> {
            browser.getUIComponent().setBounds(0, 0, 512, 128);
            browser.getUIComponent().setSize(512, 128);
        browser.getRenderHandler().getViewRect(browser).setBounds(0, 0, 512, 128);
        });
        
        browser.getRenderHandler().addOnPaintListener(webpageRenderer::run);
    }
    
    @Override
    public void setWidth(int width) {
        super.setWidth(width);
        updateSize();
    }
    
    @Override
    public void setHeight(int height) {
        super.setHeight(height);
        updateSize();
    }
    
    private void updateSize() {
        if (!initialized) return;
        EventQueue.invokeLater(() -> {
            var comp = browser.getUIComponent();
            comp.setSize(getWidth(), getHeight());
            comp.setBounds(0, 0, getWidth(), getHeight());
        });
        
    }
    
    @Override
    public void draw() {
        super.draw();
        webpageRenderer.executeTask();
        frame.draw();
        
        if (!initialized) {
            String status = progress != null ? progress.name().toLowerCase() : "initializing";
            progressLabel.setText(status + "... " + (int) downloaded);
            progressLabel.draw();
            
            if (app != null) {
                initApp(url);
                initialized = true;
            }
        }
    }
    
    @Override
    public void onMouseClicked(MouseButtonEvent event, int relativeX, int relativeY) {
    
    }
    
    @Override
    public void onMouseHover(MouseHoverEvent e) {
    
    }
    
    @Override
    public void onMouseHoverEnded() {
    
    }
    
    @Override
    public void cleanup() {
    
    }
}
