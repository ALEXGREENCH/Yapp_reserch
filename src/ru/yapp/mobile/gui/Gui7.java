package ru.yapp.mobile.gui;

import ru.yapp.mobile.core.StringUtils;
import ru.yapp.mobile.core.UiUtil;

import javax.microedition.lcdui.Graphics;

public final class Gui7 extends ViewElement {
    
   private int c = 0;
   private int d = 4;
   public String str1 = "";
   public String str2 = "";

   public final void a(Graphics g) {
      this.a(g, false);
   }

   public final void b(Graphics g) {
      this.a(g, true);
   }

   public Gui7() {
      this.boolean1 = true;
      this.imgH = UiUtil.img1.getHeight();
   }

   private void a(Graphics g, boolean var2) {
      if (var2) {
         this.c += 2;
      } else {
         this.c = 0;
      }

      if (var2) {
         g.drawImage(UiUtil.img1, this.k, this.l, 20);
      } else {
         g.drawImage(UiUtil.img2, this.k, this.l, 20);
      }

      int var4 = 0;
      int var5;
      if (this.image != null) {
         var4 = this.image.getWidth();
         var5 = (this.imgH - this.image.getHeight()) / 2;
         g.drawRegion(this.image, 0 * var4, 0, var4, this.image.getHeight(), 0, this.k + this.d, this.l + var5, 20);
      }

      boolean var9 = false;
      var5 = (UiUtil.img1.getHeight() / 2 - UiUtil.allFontImagesHeight[5]) / 2;
      int var6 = var2 ? 5 : 9;
      int var7;
      if (this.str1 != null) {
         var7 = StringUtils.a(this.str1, var6);
         int var8 = var4 + 2 * this.d;
         if (var7 > this.imgW - var8 - this.d) {
            this.a(g, this.k + var8, this.l + var5, this.imgW - var8 - this.d, this.str1, var6);
         } else {
            StringUtils.a(g, this.k + var8, this.l + var5, this.str1, var6);
         }
      }

      var5 = UiUtil.img1.getHeight() / 2 + (UiUtil.img1.getHeight() / 2 - UiUtil.allFontImagesHeight[0]) / 2;
      if (this.str2 != null) {
         var6 = var2 ? 0 : 4;
         var7 = var4 + 2 * this.d;
         if (StringUtils.a(this.str2, var6) > this.imgW - var7 - this.d) {
            this.a(g, this.k + var7, this.l + var5, this.imgW - var7 - this.d, this.str2, var6);
            return;
         }

         StringUtils.a(g, this.k + var7, this.l + var5, this.str2, var6);
      }

   }

   private void a(Graphics g, int var2, int var3, int var4, String var5, int var6) {
      int var7 = g.getClipX();
      int var8 = g.getClipY();
      int var9 = g.getClipWidth();
      int var10 = g.getClipHeight();
      String var11 = "...";
      int var12 = StringUtils.a("...", var6);
      g.setClip(var2, var3, var4 - var12, UiUtil.allFontImagesHeight[var6]);
      int var13 = StringUtils.a(var5, var6) - var4;
      int var14 = Math.abs((this.c - 40 + var13) % (var13 * 2) - var13);
      if (this.c < 40) {
         var14 = 0;
      }

      StringUtils.a(g, var2 - var14, var3, var5, var6);
      g.setClip(var7, var8, var9, var10);
      if (this.c < 2) {
         StringUtils.a(g, var2 + var4 - var12, var3, var11, var6);
      }

   }
}
