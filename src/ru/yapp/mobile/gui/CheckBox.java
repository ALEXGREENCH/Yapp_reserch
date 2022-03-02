package ru.yapp.mobile.gui;

import ru.yapp.mobile.core.StringUtils;
import ru.yapp.mobile.core.UiUtil;

import javax.microedition.lcdui.Graphics;

public final class CheckBox extends ViewElement {
    public boolean isChecked = false;

    public CheckBox() {
        this.boolean1 = true;
        this.imgH = UiUtil.img5.getHeight();
        this.k = 2;
        this.imgW = this.imgH;
        this.style = 0;
    }

    public final void a(Graphics g) {
        this.imgW = this.imgH;
        g.setColor(0); // black
        g.fillRect(this.k + 2, this.l + 2, this.imgW - 4, this.imgH - 4);
        if (!this.isChecked) {
            g.setColor(255, 255, 255); // white
        } else {
            g.setColor(0x008FD9); // синий
        }

        g.fillRect(this.k + 3, this.l + 3, this.imgW - 6, this.imgH - 6);
        StringUtils.a(g, this.k + this.imgW + StringUtils.a, this.l + (this.imgH - UiUtil.allFontImagesHeight[this.style]) / 2, this.text, this.style);
    }

    public final void b(Graphics g) {
        this.imgW = this.imgH;
        g.setColor(0xFF0000); // red
        g.fillRect(this.k, this.l, this.imgW, this.imgH);
        g.setColor(0); // black
        g.fillRect(this.k + 2, this.l + 2, this.imgW - 4, this.imgH - 4);
        if (!this.isChecked) {
            g.setColor(255, 255, 255); // white
        } else {
            g.setColor(0x008FD9); // синий
        }

        g.fillRect(this.k + 3, this.l + 3, this.imgW - 6, this.imgH - 6);
        StringUtils.a(g, this.k + this.imgW + StringUtils.a, this.l + (this.imgH - UiUtil.allFontImagesHeight[this.style]) / 2, this.text, this.style);
    }
}
