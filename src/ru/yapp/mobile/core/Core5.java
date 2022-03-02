package ru.yapp.mobile.core;

import java.util.Vector;
import ru.yapp.mobile.gui.ImageView;
import ru.yapp.mobile.gui.TextView;
import ru.yapp.mobile.gui.ViewElement;

public final class Core5 {
   private static int a = 0;
   private static Vector vector1 = new Vector();
   private static Vector vector2 = new Vector();
   private static int int1;
   private static int int2;
   private static boolean boolean1 = false;
   private static int int3 = 0;
   private static int int4 = 0;
   private static int int5 = 0;
   private static int int6 = 0;

   public static int getSizeVector2() {
      return vector2.size();
   }

   public static void addGuiAbsToVector2(ViewElement guiBase) {
      vector2.addElement(guiBase);
   }

   public static void b() {
      a = StaticData.screenWidth - 4 - ResUI.d.getWidth();
      int1 = 2;
      int2 = 5;
      if (vector2.size() > 0) {
         int2 = ((ViewElement)vector2.elementAt(0)).l;
      }

      int var0 = int2;

      for(int var1 = 0; var1 < vector2.size(); ++var1) {
         ViewElement var2;
         DataCounter var4;
         if ((var2 = (ViewElement)vector2.elementAt(var1)) instanceof TextView) {
            TextView var3 = (TextView)var2;
            if (var2.image != null) {
               (var4 = new DataCounter()).a = var2.image.getWidth();
               var4.b = var2.image.getHeight();
               var4.c = 2;
               var4.d = var0;
               var3.d = var0 - int2;
               var0 = var4.d + var4.b + 2;
               vector1.addElement(var4);
            }

            var3.l = int2;
            var3.k = 2;
            a(var3);
         } else {
            ImageView var5;
            if (var2 instanceof ImageView && (var5 = (ImageView)var2).image != null) {
               (var4 = new DataCounter()).c = var5.k;
               var4.d = var0;
               var4.a = var5.image.getWidth();
               var4.b = var5.image.getHeight();
               var5.l = var4.d;
               var5.imgH = var4.b;
               var0 = var4.d + var4.b + 2;
               vector1.addElement(var4);
            }
         }

         if (int2 > var0) {
            var0 = int2;
         }

         ViewElement var6;
         if (var1 > 0 && (var6 = (ViewElement)vector2.elementAt(var1 - 1)).l + var6.imgH > var2.l + var2.imgH) {
            var2.imgH = var6.l + var6.imgH - var2.l;
         }
      }

      vector1.removeAllElements();
      vector2.removeAllElements();
   }

   private static void a(TextView var0) {
      Vector var1 = new Vector();
      Vector var2 = new Vector();
      String var4 = var0.text;
      String var5 = "";
      a(int1, int2);
      int1 = int3;
      int var6 = int3;
      int var7 = int4;

      int var10;
      while(var4.length() > 0) {
         int var8;
         String var9;
         if ((var8 = a(var4, var7, var0.style)) > 0) {
            var9 = var4.substring(0, var8);
            var5 = var5 + var9;
            var4 = var4.substring(var8);
            var10 = Core9.a(var9, var0.style);
            int1 += var10;
            var7 -= var10;
         } else {
            a(2, int2 + ResUI.allFontImagesHeight[var0.style] + 2);
            if ((var8 = a(var4, int4, var0.style)) > 0) {
               int2 += ResUI.allFontImagesHeight[var0.style] + 2;
               int1 = 2;
               var1.addElement(var5);
               var2.addElement(new Integer(var6));
               var5 = "";
               var6 = int3;
               int1 = int3;
               var7 = int4;
               var9 = var4.substring(0, var8);
               var5 = var5 + var9;
               var4 = var4.substring(var8);
               var10 = Core9.a(var9, var0.style);
               int1 += var10;
               var7 -= var10;
            } else {
               int var15;
               if ((var15 = b(var4, var7, var0.style)) > 0) {
                  String var17 = var4.substring(0, var15);
                  var5 = var5 + var17;
                  var4 = var4.substring(var15);
                  int var11 = Core9.a(var17, var0.style);
                  int1 += var11;
                  var7 -= var11;
               } else {
                  int2 += ResUI.allFontImagesHeight[var0.style] + 2;
                  int1 = 2;
                  var1.addElement(var5);
                  var2.addElement(new Integer(var6));
                  var5 = "";
                  a(int1, int2);
                  var6 = int3;
                  int1 = int3;
                  var7 = int4;
               }
            }
         }

         if (boolean1) {
            int2 += ResUI.allFontImagesHeight[var0.style] + 2;
            int1 = 2;
            var1.addElement(var5);
            var2.addElement(new Integer(var6));
            var5 = "";
            a(int1, int2);
            var6 = int3;
            int1 = int3;
            var7 = int4;
            boolean1 = false;
         }
      }

      if (var5.length() > 0) {
         var1.addElement(var5);
         var2.addElement(new Integer(var6));
         int1 = var6 + Core9.a(var5, var0.style) + Core9.a;
      }

      var0.imgH = int2 + ResUI.allFontImagesHeight[var0.style] + 2 - var0.l;
      if (var0.image != null && var0.imgH < var0.image.getHeight()) {
         var0.imgH = var0.image.getHeight();
      }

      byte[][] var14 = new byte[var1.size()][];
      int[] var16 = new int[var2.size()];

      for(var10 = 0; var10 < var1.size(); ++var10) {
         String var18;
         byte[] var12 = new byte[(var18 = (String)var1.elementAt(var10)).length()];

         for(int var13 = 0; var13 < var18.length(); ++var13) {
            var12[var13] = (byte)Core9.a(var18.charAt(var13));
         }

         var14[var10] = var12;
         var16[var10] = ((Integer)var2.elementAt(var10)).intValue();
      }

      var1.removeAllElements();
      var2.removeAllElements();
      var0.f = var14;
      var0.e = var16;
   }

   private static void a(int var0, int var1) {
      int3 = var0;
      int4 = a - int3;

      for(int var2 = 0; var2 < vector1.size(); ++var2) {
         DataCounter var3;
         if ((var3 = (DataCounter)vector1.elementAt(var2)).d + var3.b + 2 < var1) {
            vector1.removeElementAt(var2);
            --var2;
         } else if (var3.d <= var1 + ResUI.allFontImagesHeight[0] + 2 && var3.d + var3.b >= var1 - 2) {
            if (int3 < var3.c - Core9.a) {
               int4 = var3.c - int3 - Core9.a;
            } else if (int3 < var3.c + var3.a + Core9.a) {
               int4 -= var3.c + var3.a + Core9.a - int3;
               int3 = var3.c + var3.a + Core9.a;
            }
         }
      }

   }

   private static int a(String var0, int var1, int var2) {
      boolean var3 = false;
      a(var0, var2);
      int var4 = int6;
      return int5 <= var1 ? var4 : 0;
   }

   private static int b(String var0, int var1, int var2) {
      int var3 = 0;
      int var4 = 0;
      boolean var5 = false;

      while(var3 < var0.length() && !var5) {
         char var6 = Core9.a(var0.charAt(var3));
         int var7;
         if ((var7 = ResUI.w[var6 + (var2 << 8)]) == 32) {
            var7 = Core9.a;
         }

         if (var4 + var7 < var1) {
            var4 += var7 + Core9.b;
            ++var3;
         } else {
            var5 = true;
         }
      }

      if (var3 < var0.length() && var0.charAt(var3) == ' ') {
         ++var3;
      }

      return var3;
   }

   private static void a(String var0, int var1) {
      int var2 = 0;
      boolean var3 = false;
      int5 = 0;

      while(var2 < var0.length() && !var3) {
         char var4;
         if ((var4 = Core9.a(var0.charAt(var2))) != ' ' && var4 != '\n') {
            ++var2;
            int5 += ResUI.w[var4 + (var1 << 8)] + Core9.b;
         } else {
            ++var2;
            var3 = true;
            if (var4 == '\n') {
               boolean1 = true;
            }
         }
      }

      int6 = var2;
   }
}
