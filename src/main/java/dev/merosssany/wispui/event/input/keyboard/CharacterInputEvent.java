package dev.merosssany.wispui.event.input.keyboard;

import dev.merosssany.wispui.event.Event;

public class CharacterInputEvent extends Event {
    public final int codepoint;
    public final String character;

    public CharacterInputEvent(int codepoint, char[] chars) {
        this.codepoint = codepoint;
        character = String.valueOf(chars);
    }
}
