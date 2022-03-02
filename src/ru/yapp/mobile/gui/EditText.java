package ru.yapp.mobile.gui;

import ru.yapp.mobile.core.StaticData;
import ru.yapp.mobile.core.StringUtils;
import ru.yapp.mobile.core.UiUtil;

import javax.microedition.lcdui.Graphics;

public final class EditText extends ViewElement {
    public String hint = "";
    public boolean isPassword = false;
    public boolean isNumeric = false;
    private int d = 0;
    private int e = 0;

    public EditText() {
        this.boolean1 = true;
        this.imgW = StaticData.screenWidth - 4 - UiUtil.img4.getWidth();
        this.imgH = UiUtil.img5.getHeight();
        this.d = UiUtil.img5.getWidth() + 1;
        this.e = (this.imgH - UiUtil.allFontImagesHeight[0]) / 2;
    }

    public final void a(Graphics var1) {
        String var2 = this.hint;
        if (this.text != null && this.text.length() > 0) {
            var2 = this.text;
            this.style = 0;
            if (this.isPassword) {
                var2 = "**********";
            }
        }

        var1.drawImage(UiUtil.img19, this.k, this.l, 20);
        if (StringUtils.a(var2, this.style) > this.imgW - UiUtil.img5.getWidth() - UiUtil.img6.getWidth() - 2) {
            int var3 = var1.getClipX();
            int var4 = var1.getClipY();
            int var5 = var1.getClipWidth();
            int var6 = var1.getClipHeight();
            String var7 = "...";
            var1.setClip(this.k, this.l, this.imgW - UiUtil.img6.getWidth() - 1 - StringUtils.a(var7, this.style), this.imgH);
            StringUtils.a(var1, this.k + this.d, this.l + this.e, (String) var2, 0);
            var1.setClip(var3, var4, var5, var6);
            StringUtils.a(var1, this.k + this.imgW - UiUtil.img6.getWidth() - 1 - StringUtils.a(var7, this.style), this.l + this.e, var7, this.style);
        } else {
            StringUtils.a(var1, this.k + this.d, this.l + this.e, var2, this.style);
        }
    }

    public final void b(Graphics var1) {
        String var2 = this.hint;
        if (this.text != null && this.text.length() > 0) {
            this.style = 0;
            var2 = this.text;
            if (this.isPassword) {
                var2 = "**********";
            }
        }

        var1.drawImage(UiUtil.img20, this.k, this.l, 20);
        if (StringUtils.a(var2, this.style) > this.imgW - UiUtil.img5.getWidth() - UiUtil.img6.getWidth() - 2) {
            int var3 = var1.getClipX();
            int var4 = var1.getClipY();
            int var5 = var1.getClipWidth();
            int var6 = var1.getClipHeight();
            String var7 = "...";
            var1.setClip(this.k, this.l, this.imgW - UiUtil.img6.getWidth() - 1 - StringUtils.a(var7, this.style), this.imgH);
            StringUtils.a(var1, this.k + this.d, this.l + this.e, var2, this.style);
            var1.setClip(var3, var4, var5, var6);
            StringUtils.a(var1, this.k + this.imgW - UiUtil.img6.getWidth() - 1 - StringUtils.a(var7, this.style), this.l + this.e, var7, this.style);
        } else {
            StringUtils.a(var1, this.k + this.d, this.l + this.e, var2, this.style);
        }
    }
}
