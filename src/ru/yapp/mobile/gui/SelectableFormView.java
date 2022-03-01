package ru.yapp.mobile.gui;

import ru.yapp.mobile.core.StaticData;
import ru.yapp.mobile.core.Core9;
import ru.yapp.mobile.core.ResUI;

import javax.microedition.lcdui.Graphics;

public final class SelectableFormView extends ViewElement {
   public String a = "";
   public String[] vars = null;
   private int c = 0;
   private int d = 0;

   public SelectableFormView() {
      this.boolean1 = true;
      this.imgW = StaticData.screenWidth - 4 - ResUI.d.getWidth();
      this.imgH = ResUI.e.getHeight();
      this.c = ResUI.e.getWidth() + 1;
      this.d = (this.imgH - ResUI.allFontImagesHeight[0]) / 2;
   }

   public final void a(Graphics var1) {
      String var2 = this.a;
      if (this.text != null && this.text.length() > 0) {
         var2 = this.text;
         this.style = 0;
      }

      var1.drawImage(ResUI.C, this.k, this.l, 20);
      if (Core9.a(var2, this.style) > this.imgW - ResUI.e.getWidth() - ResUI.f.getWidth() - 2) {
         int var3 = var1.getClipX();
         int var4 = var1.getClipY();
         int var5 = var1.getClipWidth();
         int var6 = var1.getClipHeight();
         String var7 = "...";
         var1.setClip(this.k, this.l, this.imgW - ResUI.f.getWidth() - 1 - Core9.a(var7, this.style), this.imgH);
         Core9.a(var1, this.k + this.c, this.l + this.d, (String)var2, 0);
         var1.setClip(var3, var4, var5, var6);
         Core9.a(var1, this.k + this.imgW - ResUI.f.getWidth() - 1 - Core9.a(var7, this.style), this.l + this.d, var7, this.style);
      } else {
         Core9.a(var1, this.k + this.c, this.l + this.d, var2, this.style);
      }
   }

   public final void b(Graphics var1) {
      String var2 = this.a;
      if (this.text != null && this.text.length() > 0) {
         this.style = 0;
         var2 = this.text;
      }

      var1.drawImage(ResUI.D, this.k, this.l, 20);
      if (Core9.a(var2, this.style) > this.imgW - ResUI.e.getWidth() - ResUI.f.getWidth() - 2) {
         int var3 = var1.getClipX();
         int var4 = var1.getClipY();
         int var5 = var1.getClipWidth();
         int var6 = var1.getClipHeight();
         String var7 = "...";
         var1.setClip(this.k, this.l, this.imgW - ResUI.f.getWidth() - 1 - Core9.a(var7, this.style), this.imgH);
         Core9.a(var1, this.k + this.c, this.l + this.d, var2, this.style);
         var1.setClip(var3, var4, var5, var6);
         Core9.a(var1, this.k + this.imgW - ResUI.f.getWidth() - 1 - Core9.a(var7, this.style), this.l + this.d, var7, this.style);
      } else {
         Core9.a(var1, this.k + this.c, this.l + this.d, var2, this.style);
      }
   }
}
