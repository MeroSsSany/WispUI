package dev.merosssany.wispui.ui.base.interactive;

import dev.merosssany.wispui.event.input.mouse.MouseButtonEvent;

public interface Clickable {
    /**
     * Abstract callback for implementation-specific click logic.
     */
    void click(MouseButtonEvent e, int relativeX, int relativeY, int action, int button);
}
