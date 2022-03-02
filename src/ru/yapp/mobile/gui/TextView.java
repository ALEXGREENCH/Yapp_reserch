package ru.yapp.mobile.gui;

import ru.yapp.mobile.core.StringUtils;
import ru.yapp.mobile.core.UiUtil;

import javax.microedition.lcdui.Graphics;

public class TextView extends ViewElement {
    public int a = 0;
    public int b = 0;
    public byte c = 0;
    public int d = 0;
    public int[] e = null;
    public byte[][] f = (byte[][]) null;

    public void a(Graphics g) {
        if (this.image != null) {
            g.drawImage(this.image, this.k, this.l + this.d, 20);
        }

        if (this.f != null) {
            int var2 = this.l;

            for (int i = 0; i < this.f.length; ++i) {
                if (var2 >= this.a && var2 < this.b) {
                    StringUtils.a(g, this.e[i], var2, this.f[i], this.style);
                }

                var2 += UiUtil.allFontImagesHeight[this.style] + 2;
            }
        }

    }

    public void b(Graphics g) {
    }
}
