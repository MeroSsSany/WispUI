package dev.merosssany.wispui.event.state;

import dev.merosssany.wispui.Window;
import dev.merosssany.wispui.event.Event;

public class WindowResizedEvent extends Event {
    public int width, height;
    public Window window;

    public WindowResizedEvent(int width, int height, Window window) {
        this.width = width;
        this.height = height;
        this.window = window;
    }

    public WindowResizedEvent(Window window) {
        this.width = window.getWidth();
        this.height = window.getHeight();
        this.window = window;
    }
}
