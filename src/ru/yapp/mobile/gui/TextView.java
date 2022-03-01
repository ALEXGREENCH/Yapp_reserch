package ru.yapp.mobile.gui;

import ru.yapp.mobile.core.Core9;
import ru.yapp.mobile.core.ResUI;

import javax.microedition.lcdui.Graphics;

public class TextView extends ViewElement {
   public int a = 0;
   public int b = 0;
   public byte c = 0;
   public int d = 0;
   public int[] e = null;
   public byte[][] f = (byte[][])null;

   public void a(Graphics g) {
      if (this.image != null) {
         g.drawImage(this.image, this.k, this.l + this.d, 20);
      }

      if (this.f != null) {
         int var2 = this.l;

         for(int var3 = 0; var3 < this.f.length; ++var3) {
            if (var2 >= this.a && var2 < this.b) {
               Core9.a(g, this.e[var3], var2, this.f[var3], this.style);
            }

            var2 += ResUI.allFontImagesHeight[this.style] + 2;
         }
      }

   }

   public void b(Graphics g) {}
}
