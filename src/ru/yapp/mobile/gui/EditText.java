package ru.yapp.mobile.gui;

import ru.yapp.mobile.core.StaticData;
import ru.yapp.mobile.core.Core9;
import ru.yapp.mobile.core.ResUI;

import javax.microedition.lcdui.Graphics;

public final class EditText extends ViewElement {
   public String a = "";
   private int d = 0;
   private int e = 0;
   public boolean isPassword = false;
   public boolean isNumeric = false;

   public EditText() {
      this.boolean1 = true;
      this.imgW = StaticData.screenWidth - 4 - ResUI.d.getWidth();
      this.imgH = ResUI.e.getHeight();
      this.d = ResUI.e.getWidth() + 1;
      this.e = (this.imgH - ResUI.allFontImagesHeight[0]) / 2;
   }

   public final void a(Graphics var1) {
      String var2 = this.a;
      if (this.text != null && this.text.length() > 0) {
         var2 = this.text;
         this.style = 0;
         if (this.isPassword) {
            var2 = "**********";
         }
      }

      var1.drawImage(ResUI.C, this.k, this.l, 20);
      if (Core9.a(var2, this.style) > this.imgW - ResUI.e.getWidth() - ResUI.f.getWidth() - 2) {
         int var3 = var1.getClipX();
         int var4 = var1.getClipY();
         int var5 = var1.getClipWidth();
         int var6 = var1.getClipHeight();
         String var7 = "...";
         var1.setClip(this.k, this.l, this.imgW - ResUI.f.getWidth() - 1 - Core9.a(var7, this.style), this.imgH);
         Core9.a(var1, this.k + this.d, this.l + this.e, (String)var2, 0);
         var1.setClip(var3, var4, var5, var6);
         Core9.a(var1, this.k + this.imgW - ResUI.f.getWidth() - 1 - Core9.a(var7, this.style), this.l + this.e, var7, this.style);
      } else {
         Core9.a(var1, this.k + this.d, this.l + this.e, var2, this.style);
      }
   }

   public final void b(Graphics var1) {
      String var2 = this.a;
      if (this.text != null && this.text.length() > 0) {
         this.style = 0;
         var2 = this.text;
         if (this.isPassword) {
            var2 = "**********";
         }
      }

      var1.drawImage(ResUI.D, this.k, this.l, 20);
      if (Core9.a(var2, this.style) > this.imgW - ResUI.e.getWidth() - ResUI.f.getWidth() - 2) {
         int var3 = var1.getClipX();
         int var4 = var1.getClipY();
         int var5 = var1.getClipWidth();
         int var6 = var1.getClipHeight();
         String var7 = "...";
         var1.setClip(this.k, this.l, this.imgW - ResUI.f.getWidth() - 1 - Core9.a(var7, this.style), this.imgH);
         Core9.a(var1, this.k + this.d, this.l + this.e, var2, this.style);
         var1.setClip(var3, var4, var5, var6);
         Core9.a(var1, this.k + this.imgW - ResUI.f.getWidth() - 1 - Core9.a(var7, this.style), this.l + this.e, var7, this.style);
      } else {
         Core9.a(var1, this.k + this.d, this.l + this.e, var2, this.style);
      }
   }
}
