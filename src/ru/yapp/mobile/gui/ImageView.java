package ru.yapp.mobile.gui;

import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

public final class ImageView extends ViewElement {
    public int a = 0;
    public Image img = null;

    public ImageView() {
        this.boolean1 = true;
    }

    public final void a(Graphics g) {
        if (this.img != null) {
            g.drawImage(this.img, this.k, this.l, 20);
        }

    }

    public final void b(Graphics g) {
        if (this.img != null) {
            g.drawImage(this.img, this.k, this.l, 20);
            this.imgW = this.img.getWidth();
        }

        g.setColor(0);
        g.drawRect(this.k - 1, this.l - 1, this.imgW + 1, this.imgH + 1);
    }
}
