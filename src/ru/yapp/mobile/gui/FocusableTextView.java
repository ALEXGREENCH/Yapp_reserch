package ru.yapp.mobile.gui;

import ru.yapp.mobile.core.StringUtils;
import ru.yapp.mobile.core.UiUtil;

import javax.microedition.lcdui.Graphics;

public final class FocusableTextView extends TextView {
   
    public FocusableTextView() {
      this.boolean1 = true;
      this.style = 1;
   }

   public final void a(Graphics g) {
      if (this.image != null) {
         g.drawImage(this.image, this.k, this.l + this.d, 20);
      }

      if (this.f != null) {
         int var2 = 0;

         for(int i = 0; i < this.f.length; ++i) {
            if (this.l + var2 >= this.a && this.l < this.b) {
               StringUtils.a(g, this.e[i], this.l + var2, (byte[])this.f[i], 1);
            }

            var2 += UiUtil.allFontImagesHeight[this.style] + 2;
         }
      }

   }

   public final void b(Graphics g) {
      if (this.image != null) {
         g.drawImage(this.image, this.k, this.l + this.d, 20);
      }

      if (this.f != null) {
         int var2 = this.l;

         for(int i = 0; i < this.f.length; ++i) {
            if (var2 >= this.a && var2 < this.b) {
               StringUtils.b(g, this.e[i], var2, this.f[i], 4);
            }

            var2 += UiUtil.allFontImagesHeight[this.style] + 2;
         }
      }

   }
}
