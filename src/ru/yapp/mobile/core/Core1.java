package ru.yapp.mobile.core;

import java.util.Random;
import javax.microedition.lcdui.Image;
import ru.yapp.mobile.gui.Gui4;

public final class Core1 {
   public static Gui4[] a = null;
   private static Gui4 b = null;

   public static Image a() {
      return b != null ? b.image : null;
   }

   public static int b() {
      return b != null ? b.a : 0;
   }

   public static void a(int var0) {
      if (a != null) {
         if (var0 != 0) {
            b = null;

            for(int var1 = 0; var1 < a.length; ++var1) {
               if (a[var1].a == var0) {
                  b = a[var1];
               }
            }

            return;
         }

         int var2 = (new Random()).nextInt() % 100;
         int var3 = 0;

         for(int var4 = 0; var4 < a.length && var2 < var3; ++var4) {
            var3 += a[var4].d;
            if (var2 < var3) {
               b = a[var4];
            }
         }

         if (b == null) {
            b = a[0];
         }
      }

   }

   public static void c() {
      b = null;
   }

   public static int d() {
      return b != null ? b.b : 0;
   }
}
