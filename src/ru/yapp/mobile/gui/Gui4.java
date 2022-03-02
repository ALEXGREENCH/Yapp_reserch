package ru.yapp.mobile.gui;

import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

public final class Gui4 extends ViewElement {

    public int a = 0;
    public int b = 0;
    public Image image = null;
    public int d = 0;

    public final void a(Graphics g) {
    }

    public final void b(Graphics g) {
    }

    public final void a() {
        int height;
        if (this.image != null) {
            height = this.image.getHeight();
        } else {
            height = 0;
        }

        if (height == 0) {
            this.b = 0;
        } else if (height < 28) {
            this.b = 28;
        } else if (height < 44) {
            this.b = 44;
        } else {
            if (height < 64) {
                this.b = 64;
            }

        }
    }
}
