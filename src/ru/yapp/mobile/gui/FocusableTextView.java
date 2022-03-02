package ru.yapp.mobile.gui;

import ru.yapp.mobile.core.Core9;
import ru.yapp.mobile.core.ResUI;

import javax.microedition.lcdui.Graphics;

public final class FocusableTextView extends TextView {
   public FocusableTextView() {
      this.boolean1 = true;
      this.style = 1;
   }

   public final void a(Graphics var1) {
      if (this.image != null) {
         var1.drawImage(this.image, this.k, this.l + this.d, 20);
      }

      if (this.f != null) {
         int var2 = 0;

         for(int var3 = 0; var3 < this.f.length; ++var3) {
            if (this.l + var2 >= this.a && this.l < this.b) {
               Core9.a(var1, this.e[var3], this.l + var2, (byte[])this.f[var3], 1);
            }

            var2 += ResUI.allFontImagesHeight[this.style] + 2;
         }
      }

   }

   public final void b(Graphics var1) {
      if (this.image != null) {
         var1.drawImage(this.image, this.k, this.l + this.d, 20);
      }

      if (this.f != null) {
         int var2 = this.l;

         for(int var3 = 0; var3 < this.f.length; ++var3) {
            if (var2 >= this.a && var2 < this.b) {
               Core9.b(var1, this.e[var3], var2, this.f[var3], 4);
            }

            var2 += ResUI.allFontImagesHeight[this.style] + 2;
         }
      }

   }
}
