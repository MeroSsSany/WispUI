import dev.merosssany.wispui.Display;
import dev.merosssany.wispui.Window;
import dev.merosssany.wispui.manager.Mouse;
import dev.merosssany.wispui.renderer.UIRenderer;
import dev.merosssany.wispui.ui.base.browser.Browser;
import dev.merosssany.wispui.ui.base.browser.WebpageRenderer;
import dev.merosssany.wispui.ui.base.layout.Scene;

public class Test {
    public static void main(String[] args) {
        Window window = new Window(1024, 512, "Test");
        Display.init();
        Mouse.init(window);
        UIRenderer renderer = new UIRenderer(window);
        
        Browser.init(e -> System.exit(1));
        
        Scene scene = new Scene(renderer, window);
        Browser browser = new Browser(scene, new WebpageRenderer(1024,512), "google.com", "main.ttf");
        browser.setSize(512);
        
        scene.register(browser);
        
        while (!window.isShouldClose()) {
            Display.clearBufferBits();
            
            scene.draw();
            
            window.update();
        }
        
        scene.cleanup();
        window.cleanup();
    }
}