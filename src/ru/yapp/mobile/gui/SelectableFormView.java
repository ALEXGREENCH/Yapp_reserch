package ru.yapp.mobile.gui;

import ru.yapp.mobile.core.StaticData;
import ru.yapp.mobile.core.StringUtils;
import ru.yapp.mobile.core.UiUtil;

import javax.microedition.lcdui.Graphics;

public final class SelectableFormView extends ViewElement {
   public String a = "";
   public String[] vars = null;
   private int c = 0;
   private int d = 0;

   public SelectableFormView() {
      this.boolean1 = true;
      this.imgW = StaticData.screenWidth - 4 - UiUtil.img4.getWidth();
      this.imgH = UiUtil.img5.getHeight();
      this.c = UiUtil.img5.getWidth() + 1;
      this.d = (this.imgH - UiUtil.allFontImagesHeight[0]) / 2;
   }

   public final void a(Graphics var1) {
      String var2 = this.a;
      if (this.text != null && this.text.length() > 0) {
         var2 = this.text;
         this.style = 0;
      }

      var1.drawImage(UiUtil.img19, this.k, this.l, 20);
      if (StringUtils.a(var2, this.style) > this.imgW - UiUtil.img5.getWidth() - UiUtil.img6.getWidth() - 2) {
         int var3 = var1.getClipX();
         int var4 = var1.getClipY();
         int var5 = var1.getClipWidth();
         int var6 = var1.getClipHeight();
         String var7 = "...";
         var1.setClip(this.k, this.l, this.imgW - UiUtil.img6.getWidth() - 1 - StringUtils.a(var7, this.style), this.imgH);
         StringUtils.a(var1, this.k + this.c, this.l + this.d, (String)var2, 0);
         var1.setClip(var3, var4, var5, var6);
         StringUtils.a(var1, this.k + this.imgW - UiUtil.img6.getWidth() - 1 - StringUtils.a(var7, this.style), this.l + this.d, var7, this.style);
      } else {
         StringUtils.a(var1, this.k + this.c, this.l + this.d, var2, this.style);
      }
   }

   public final void b(Graphics var1) {
      String var2 = this.a;
      if (this.text != null && this.text.length() > 0) {
         this.style = 0;
         var2 = this.text;
      }

      var1.drawImage(UiUtil.img20, this.k, this.l, 20);
      if (StringUtils.a(var2, this.style) > this.imgW - UiUtil.img5.getWidth() - UiUtil.img6.getWidth() - 2) {
         int var3 = var1.getClipX();
         int var4 = var1.getClipY();
         int var5 = var1.getClipWidth();
         int var6 = var1.getClipHeight();
         String var7 = "...";
         var1.setClip(this.k, this.l, this.imgW - UiUtil.img6.getWidth() - 1 - StringUtils.a(var7, this.style), this.imgH);
         StringUtils.a(var1, this.k + this.c, this.l + this.d, var2, this.style);
         var1.setClip(var3, var4, var5, var6);
         StringUtils.a(var1, this.k + this.imgW - UiUtil.img6.getWidth() - 1 - StringUtils.a(var7, this.style), this.l + this.d, var7, this.style);
      } else {
         StringUtils.a(var1, this.k + this.c, this.l + this.d, var2, this.style);
      }
   }
}
