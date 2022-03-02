package ru.yapp.mobile.gui;

import ru.yapp.mobile.core.StaticData;
import ru.yapp.mobile.core.StringUtils;
import ru.yapp.mobile.core.UiUtil;

import javax.microedition.lcdui.Graphics;

public final class Button extends ViewElement {
    private int a;
    private int b;
    private int c;

    public final void a() {
        this.boolean1 = true;
        int var1 = 0;
        if (this.image != null) {
            var1 = this.image.getWidth() + 1;
        }

        this.imgW = StaticData.screenWidth - 4 - UiUtil.img4.getWidth();
        this.imgH = UiUtil.img13.getHeight();
        this.c = this.k + UiUtil.img11.getWidth() + (var1 - 1) / 2;
        this.a = this.k + UiUtil.img11.getWidth() + var1 + 1;
        this.b = this.l + (this.imgH - UiUtil.allFontImagesHeight[0]) / 2;
    }

    public final void a(Graphics g) {
        g.drawImage(UiUtil.img17, this.k, this.l, 20);
        if (this.image != null) {
            g.drawImage(this.image, this.c, this.l + this.imgH / 2, 3);
        }

        StringUtils.a(g, this.a, this.b, (String) this.text, 4);
    }

    public final void b(Graphics g) {
        g.drawImage(UiUtil.img18, this.k, this.l, 20);
        if (this.image != null) {
            g.drawImage(this.image, this.c, this.l + this.imgH / 2, 3);
        }

        StringUtils.a(g, this.a, this.b, (String) this.text, 0);
    }
}
