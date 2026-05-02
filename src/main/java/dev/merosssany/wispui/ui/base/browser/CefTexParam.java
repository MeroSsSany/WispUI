package dev.merosssany.wispui.ui.base.browser;

import dev.merosssany.wispui.data.template.texture.DefaultParam;

import static org.lwjgl.opengl.GL12.GL_BGRA;

public class CefTexParam extends DefaultParam {
    @Override
    public void generateMipmap() {
    
    }
    
    @Override
    public int format() {
        return GL_BGRA;
    }
}
