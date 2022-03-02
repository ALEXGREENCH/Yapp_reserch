package ru.yapp.mobile.gui;

import ru.yapp.mobile.core.StaticData;
import ru.yapp.mobile.core.Core9;
import ru.yapp.mobile.core.ResUI;

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

      this.imgW = StaticData.screenWidth - 4 - ResUI.d.getWidth();
      this.imgH = ResUI.m.getHeight();
      this.c = this.k + ResUI.k.getWidth() + (var1 - 1) / 2;
      this.a = this.k + ResUI.k.getWidth() + var1 + 1;
      this.b = this.l + (this.imgH - ResUI.allFontImagesHeight[0]) / 2;
   }

   public final void a(Graphics var1) {
      var1.drawImage(ResUI.A, this.k, this.l, 20);
      if (this.image != null) {
         var1.drawImage(this.image, this.c, this.l + this.imgH / 2, 3);
      }

      Core9.a(var1, this.a, this.b, (String)this.text, 4);
   }

   public final void b(Graphics var1) {
      var1.drawImage(ResUI.B, this.k, this.l, 20);
      if (this.image != null) {
         var1.drawImage(this.image, this.c, this.l + this.imgH / 2, 3);
      }

      Core9.a(var1, this.a, this.b, (String)this.text, 0);
   }
}
