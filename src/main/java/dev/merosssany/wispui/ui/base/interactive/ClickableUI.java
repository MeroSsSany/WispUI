package dev.merosssany.wispui.ui.base.interactive;

import dev.merosssany.wispui.event.input.mouse.MouseButtonEvent;
import dev.merosssany.wispui.ui.base.AbstractUI;

public interface ClickableUI extends AbstractUI {
    /**
     * Abstract callback for implementation-specific click logic.
     */
    void click(MouseButtonEvent e, int relativeX, int relativeY, int action, int button);
}
