package ru.yapp.mobile.core;

import javax.microedition.lcdui.Graphics;

public final class Core9 {
   public static int a = 5;
   public static int b = 1;

   public static int a(String var0, int var1) {
      int var2 = 0;
      int var3 = var0.length();

      for(int var4 = 0; var4 < var3; ++var4) {
         char var5;
         if ((var5 = var0.charAt(var4)) != ' ') {
            int var6 = a(var5) + (var1 << 8);
            byte var7 = ResUI.w[var6];
            var2 = var2 + var7 + b;
         } else {
            var2 += a;
         }
      }

      return var2;
   }

   public static int a(byte[] var0, int var1) {
      int var2 = 0;
      int var3 = var0.length;

      for(int var4 = 0; var4 < var3; ++var4) {
         int var5;
         if ((var5 = var0[var4]) < 0) {
            var5 += 256;
         }

         if (var5 != 32) {
            var5 += var1 << 8;
            byte var6 = ResUI.w[var5];
            var2 = var2 + var6 + b;
         } else {
            var2 += a;
         }
      }

      return var2;
   }

   public static void a(Graphics var0, int var1, int var2, String var3, int var4) {
      int var5 = 0;
      int var6 = var3.length();
      char[] var7 = var3.toCharArray();

      for(int var8 = 0; var8 < var6; ++var8) {
         char var9;
         if ((var9 = var7[var8]) != ' ') {
            int var10 = a(var9) + (var4 << 8);
            int var11 = ResUI.v[var10];
            byte var12 = ResUI.w[var10];
            byte var13 = (byte) ResUI.allFontImagesHeight[var4];
            if (var12 > 0) {
               var0.drawRegion(ResUI.allFontImages[var4], var11, 0, var12, var13, 0, var1 + var5, var2, 20);
               var5 = var5 + var12 + b;
            }
         } else {
            var5 += a;
         }
      }

   }

   public static void a(Graphics var0, int var1, int var2, byte[] var3, int var4) {
      if (var3 != null) {
         int var5 = 0;
         int var6 = var3.length;

         for(int var7 = 0; var7 < var6; ++var7) {
            int var8;
            if ((var8 = var3[var7]) < 0) {
               var8 += 256;
            }

            if (var8 != 32) {
               var8 += var4 << 8;
               int var9 = ResUI.v[var8];
               byte var10 = ResUI.w[var8];
               byte var11 = (byte) ResUI.allFontImagesHeight[var4];
               if (var10 > 0) {
                  var0.drawRegion(ResUI.allFontImages[var4], var9, 0, var10, var11, 0, var1 + var5, var2, 20);
                  var5 = var5 + var10 + b;
               }
            } else {
               var5 += a;
            }
         }

      }
   }

   public static void b(Graphics var0, int var1, int var2, byte[] var3, int var4) {
      int var5 = 0;
      int var6 = var3.length;

      for(int var7 = 0; var7 < var6; ++var7) {
         int var8;
         if ((var8 = var3[var7]) < 0) {
            var8 += 256;
         }

         byte var9 = ResUI.w[var8];
         byte var10 = (byte) ResUI.allFontImagesHeight[var4];
         if (var8 != 32) {
            var8 += var4 << 8;
            int var11 = ResUI.v[var8];
            if (var9 > 0) {
               var0.setColor(255);
               var0.fillRect(var1 + var5, var2 - 1, var9 + 1, var10 + 2);
               var0.drawRegion(ResUI.allFontImages[var4], var11, 0, var9, var10, 0, var1 + var5, var2, 20);
               var5 = var5 + var9 + b;
            }
         } else {
            var0.setColor(255);
            var0.fillRect(var1 + var5, var2 - 1, a, var10 + 2);
            var5 += a;
         }
      }

   }

   public static String[] a(int var0, int var1, String var2, int var3) {
      boolean var4 = false;
      int var5 = var2.length();
      int var6 = 0;
      int var7 = var1 -= 8;
      int var13 = var0 + var1 / 2;
      String[] var8;
      (var8 = new String[5120])[0] = "";
      int var10;
      if (var5 > 0) {
         String var9 = null;

         int var12;
         for(var10 = 0; var10 < var5; var13 += var12 + a) {
            int var11;
            if ((var11 = var2.indexOf(" ", var10)) == -1) {
               var9 = var2.substring(var10, var5);
               var10 = var5;
            } else {
               var9 = var2.substring(var10, var11);
               var10 = var11 + 1;
            }

            if ((var12 = a(var9, var3)) > var1) {
               if (var13 > var0) {
                  var13 = var0;
                  ++var6;
                  var8[var6] = "";
                  var1 = var7;
               }
            } else if (var13 + var12 > var0 + var1) {
               var13 = var0;
               ++var6;
               var8[var6] = "";
               var1 = var7;
            }

            var8[var6] = var8[var6] + " " + var9;
         }
      }

      String[] var14 = new String[var6 + 1];

      for(var10 = 0; var10 < var8.length && var8[var10] != null; ++var10) {
         var14[var10] = var8[var10];
      }

      return var14;
   }

   public static void a(Graphics var0, int var1, int var2, int var3, int var4, String var5, int var6) {
      int var7 = var1;
      int var8 = var2;
      int var9;
      int var13;
      if ((var9 = var5.length()) > 0) {
         for(int var10 = 0; var10 < var9; var7 += var13 + a) {
            int var11;
            String var12;
            if ((var11 = var5.indexOf(" ", var10)) == -1) {
               var12 = var5.substring(var10, var9);
               var10 = var9;
            } else {
               var12 = var5.substring(var10, var11);
               var10 = var11 + 1;
            }

            if ((var13 = a(var12, var6)) > var3 - (var7 - var1)) {
               if (var7 > var1) {
                  var7 = var1;
                  var8 = var8 + ResUI.allFontImagesHeight[var6] + 2;
               }
            } else if (var7 + var13 > var1 + var3) {
               var7 = var1;
               var8 = var8 + ResUI.allFontImagesHeight[var6] + 2;
            }

            if (var8 - var2 + ResUI.allFontImagesHeight[var6] < var4) {
               a(var0, var7, var8, var12, var6);
            }
         }
      }

   }

   public static char a(char var0) {
      char var1;
      char var10000 = (var1 = var0 == 8470 ? 140 : var0) == 8364 ? 141 : var1;
      var1 = var10000;
      var10000 = var10000 == 8482 ? 142 : var1;
      var1 = var10000;
      int var2;
      return (char)((var2 = var10000 > 1000 ? var1 - 850 : var1) > 255 ? 0 : var2);
   }
}
