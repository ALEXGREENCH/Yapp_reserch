package ru.yapp.mobile.gui;

import ru.yapp.mobile.core.Core9;
import ru.yapp.mobile.core.ResUI;

import javax.microedition.lcdui.Graphics;

public final class CheckBox extends ViewElement {
   public boolean isChecked = false;

   public CheckBox() {
      this.boolean1 = true;
      this.imgH = ResUI.e.getHeight();
      this.k = 2;
      this.imgW = this.imgH;
      this.style = 0;
   }

   public final void a(Graphics var1) {
      this.imgW = this.imgH;
      var1.setColor(0);
      var1.fillRect(this.k + 2, this.l + 2, this.imgW - 4, this.imgH - 4);
      if (!this.isChecked) {
         var1.setColor(255, 255, 255);
      } else {
         var1.setColor(36825);
      }

      var1.fillRect(this.k + 3, this.l + 3, this.imgW - 6, this.imgH - 6);
      Core9.a(var1, this.k + this.imgW + Core9.a, this.l + (this.imgH - ResUI.allFontImagesHeight[this.style]) / 2, this.text, this.style);
   }

   public final void b(Graphics var1) {
      this.imgW = this.imgH;
      var1.setColor(16711680);
      var1.fillRect(this.k, this.l, this.imgW, this.imgH);
      var1.setColor(0);
      var1.fillRect(this.k + 2, this.l + 2, this.imgW - 4, this.imgH - 4);
      if (!this.isChecked) {
         var1.setColor(255, 255, 255);
      } else {
         var1.setColor(36825);
      }

      var1.fillRect(this.k + 3, this.l + 3, this.imgW - 6, this.imgH - 6);
      Core9.a(var1, this.k + this.imgW + Core9.a, this.l + (this.imgH - ResUI.allFontImagesHeight[this.style]) / 2, this.text, this.style);
   }
}
