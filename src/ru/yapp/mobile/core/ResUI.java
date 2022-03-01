package ru.yapp.mobile.core;

import java.io.IOException;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;
import ru.yapp.mobile.Yapp;

public final class ResUI {
   public static Image a;
   public static Image b;
   public static Image c;
   public static Image d;
   public static Image e;
   public static Image f;
   public static Image g;
   public static Image h;
   public static Image i;
   public static Image j;
   public static Image k;
   public static Image l;
   public static Image m;
   public static Image n;
   public static Image o;
   public static Image p;
   public static Image imgFormatedMenuBack = null;
   public static Image formatedImgToolbar = null;
   public static Image imgMenuBack = null;
   public static Image imgSplash = null;
   private static final int[] tableFontMini = new int[]{0, 4, 8, 12, 16, 20, 24, 28, 32, 33, 36, 40, 44, 49, 53, 57, 61, 65, 69, 73, 76, 80, 84, 89, 93, 97, 101, 105, 109, 113, 117, 121, 124, 128, 132, 133, 135, 139, 140, 147, 151, 155, 159, 163, 166, 169, 172, 176, 180, 185, 189, 193, 196, 200, 204, 208, 211, 217, 221, 225, 230, 234, 238, 242, 246, 250, 255, 259, 263, 267, 271, 275, 278, 282, 287, 291, 296, 300, 305, 311, 316, 322, 326, 330, 336, 340, 344, 348, 352, 355, 360, 364, 368, 373, 377, 381, 385, 389, 393, 398, 402, 406, 410, 414, 418, 421, 425, 430, 434, 439, 443, 448, 454, 459, 465, 469, 473, 479, 483, 487, 489, 493, 497, 501, 505, 509, 513, 517, 521, 525, 526, 530, 535, 542, 547, 551, 556, 563, 567, 571, 575, 579, 581, 583, 588, 589, 591, 592, 594, 595, 599, 601, 603, 606, 611, 614, 616, 620, 627, 634, 642, 649, 653, 657};
   private static final int[] tableFontMaxi = new int[]{0, 7, 13, 19, 26, 32, 38, 45, 52, 56, 61, 67, 73, 81, 88, 95, 101, 108, 115, 121, 129, 136, 142, 152, 158, 164, 170, 176, 182, 187, 193, 199, 204, 210, 216, 218, 222, 228, 230, 240, 246, 252, 258, 264, 269, 274, 279, 285, 291, 299, 305, 311, 316, 323, 329, 335, 341, 350, 356, 362, 372, 378, 385, 392, 398, 406, 414, 421, 428, 435, 441, 447, 455, 462, 470, 476, 484, 491, 501, 512, 520, 530, 536, 542, 553, 560, 566, 572, 578, 583, 591, 597, 603, 613, 618, 623, 628, 634, 641, 648, 654, 660, 666, 672, 677, 683, 689, 697, 703, 710, 716, 724, 733, 741, 750, 756, 761, 770, 776, 782, 786, 792, 798, 804, 810, 816, 822, 828, 834, 840, 842, 848, 853, 865, 872, 878, 886, 895, 902, 909, 914, 919, 921, 923, 931, 933, 935, 937, 939, 941, 946, 950, 954, 958, 965, 972, 975, 981, 990, 999, 1008, 1020, 1027, 1034};
   public static Image[] allFontImages = new Image[10];
   public static int[] v = new int[2560];
   public static byte[] w = new byte[2560];
   public static int[] allFontImagesHeight = new int[10];
   public static Image formatedMenuSel;
   public static Image formatedMenuBg;
   public static Image A;
   public static Image B;
   public static Image C;
   public static Image D;
   public static Image formatedImgStatusbar;
   public static Image imgMessagerRightArrow;

   public static void createImageSplash() {
      try {
         imgSplash = Image.createImage("/splash.png");
      } catch (IOException e) {
         e.printStackTrace();
         imgSplash = Image.createImage(10, 10);
         Graphics var1 = imgSplash.getGraphics();
         var1.setColor(0);
         var1.fillRect(0, 0, 10, 10);
      }
   }

   public static void init() throws Exception {
      createImagesFont();
      createMenuAndToolbar();
      i();
      h();
      g();
      createStatusBar();
      createRightArrow();
   }

   private static void createImagesFont() {
      try {
         Image imgFontMini = Image.createImage("/font_mini.png");
         cteateGraphicsChars(0, tableFontMini, imgFontMini);
         imgFontMini = Image.createImage("/font_mini_blue.png");
         cteateGraphicsChars(1, tableFontMini, imgFontMini);
         imgFontMini = Image.createImage("/font_mini_red.png");
         cteateGraphicsChars(2, tableFontMini, imgFontMini);
         imgFontMini = Image.createImage("/font_mini_green.png");
         cteateGraphicsChars(3, tableFontMini, imgFontMini);
         imgFontMini = Image.createImage("/font_mini_white.png");
         cteateGraphicsChars(4, tableFontMini, imgFontMini);
         Image imageFontMaxi = Image.createImage("/font_maxi.png");
         cteateGraphicsChars(5, tableFontMaxi, imageFontMaxi);
         imageFontMaxi = Image.createImage("/font_maxi_blue.png");
         cteateGraphicsChars(6, tableFontMaxi, imageFontMaxi);
         imageFontMaxi = Image.createImage("/font_maxi_red.png");
         cteateGraphicsChars(7, tableFontMaxi, imageFontMaxi);
         imageFontMaxi = Image.createImage("/font_maxi_green.png");
         cteateGraphicsChars(8, tableFontMaxi, imageFontMaxi);
         imageFontMaxi = Image.createImage("/font_maxi_white.png");
         cteateGraphicsChars(9, tableFontMaxi, imageFontMaxi);
      } catch (Exception e) {
         e.printStackTrace();
      }
   }

   private static void cteateGraphicsChars(int type, int[] charTable, Image img) {
      allFontImages[type] = img;
      allFontImagesHeight[type] = img.getHeight();
      Yapp.Log(charTable.length + " " + "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyzАБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯабвгдеёжзийклмнопрстуфхцчшщъыьэюя0123456789/|\\*№#$€@«»\"\"''&:;.,!?()-+=`§©®™%<>".length());

      for(int i = 0; i < "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyzАБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯабвгдеёжзийклмнопрстуфхцчшщъыьэюя0123456789/|\\*№#$€@«»\"\"''&:;.,!?()-+=`§©®™%<>".length(); ++i) {
         char charFromIndex = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyzАБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯабвгдеёжзийклмнопрстуфхцчшщъыьэюя0123456789/|\\*№#$€@«»\"\"''&:;.,!?()-+=`§©®™%<>".charAt(i);
         int var6 = charTable[i];
         int var7 = charTable[i + 1];
         int var8 = type << 8;
         char var9 = Core9.a(charFromIndex);
         v[var9 + var8] = var6;
         w[var9 + var8] = (byte)(var7 - var6);
      }

   }

   private static void createMenuAndToolbar() throws Exception {
      imgMenuBack = Image.createImage("/menuback.png");
      imgFormatedMenuBack = Image.createImage(StaticData.screenWidth, 17);
      Graphics graphicsImgFormatedMenuBack = imgFormatedMenuBack.getGraphics();

      for(int i = 0; i < StaticData.screenWidth; i += imgMenuBack.getWidth()) {
         graphicsImgFormatedMenuBack.drawImage(imgMenuBack, i, 0, 20);
      }

      Image menuBg = Image.createImage("/menu_bg.png");
      formatedMenuBg = Image.createImage(120, menuBg.getHeight());
      graphicsImgFormatedMenuBack = formatedMenuBg.getGraphics();

      for(int i = 0; i < 120; i += menuBg.getWidth()) {
         graphicsImgFormatedMenuBack.drawImage(menuBg, i, 0, 20);
      }

      Image menuSel = Image.createImage("/menu_sel.png");
      formatedMenuSel = Image.createImage(114, menuSel.getHeight());
      graphicsImgFormatedMenuBack = formatedMenuSel.getGraphics();

      for(int i = 0; i < 120; i += menuSel.getWidth()) {
         graphicsImgFormatedMenuBack.drawImage(menuSel, i, 0, 20);
      }

      Image topbar = Image.createImage("/topbar.png");
      formatedImgToolbar = Image.createImage(StaticData.screenWidth, 19);
      graphicsImgFormatedMenuBack = formatedImgToolbar.getGraphics();

      for(int i = 0; i < StaticData.screenWidth; ++i) {
         graphicsImgFormatedMenuBack.drawRegion(topbar, 0, 0, 1, topbar.getHeight(), 0, i, 0, 20);
         graphicsImgFormatedMenuBack.drawImage(topbar, formatedImgToolbar.getWidth() - topbar.getWidth(), 0, 20);
      }

   }

   private static void createRightArrow() throws Exception {
      imgMessagerRightArrow = Image.createImage("/messager/right_arrow.png");
   }

   private static void createStatusBar() throws Exception {
      Image imgMessagerStatusBar = Image.createImage("/messager/status_bar.png");
      formatedImgStatusbar = Image.createImage(StaticData.screenWidth, 17);
      Graphics graphicsformatedImgStatusbar = formatedImgStatusbar.getGraphics();

      for(int i = 0; i < StaticData.screenWidth; i += imgMessagerStatusBar.getWidth()) {
         graphicsformatedImgStatusbar.drawImage(imgMessagerStatusBar, i, 0, 20);
      }
   }

   private static void g() throws Exception {
      int var0 = StaticData.screenWidth - 4 - c.getWidth();
      Image imgButton = Image.createImage("/button.png");
      int var2 = imgButton.getHeight();
      int var3 = imgButton.getWidth() / 6;
      k = a(imgButton, 0, 0, var3, var2);
      m = a(imgButton, var3, 0, var3, var2);
      l = a(imgButton, var3 * 2, 0, var3, var2);
      n = a(imgButton, var3 * 3, 0, var3, var2);
      p = a(imgButton, var3 * 4, 0, var3, var2);
      o = a(imgButton, var3 * 5, 0, var3, var2);
      A = Image.createImage(var0, imgButton.getHeight());
      B = Image.createImage(var0, imgButton.getHeight());
      Graphics var4 = A.getGraphics();
      Graphics var5 = B.getGraphics();
      var4.drawImage(k, 0, 0, 20);
      var5.drawImage(n, 0, 0, 20);

      int var6 = var3;
      
      while(var6 < var0 - var3) {
         var4.drawImage(m, var6, 0, 20);
         var5.drawImage(p, var6, 0, 20);
         var6 += var3;
      }

      var4.drawImage(l, 0 + var0 - var3, 0, 20);
      var5.drawImage(o, 0 + var0 - var3, 0, 20);
      Image imgAdvButton = Image.createImage("/adv_button.png");
      b = Image.createImage(var0, imgAdvButton.getHeight());
      a = Image.createImage(var0, imgAdvButton.getHeight());
      Graphics var8 = b.getGraphics();
      Graphics var9 = a.getGraphics();
      int var10 = imgAdvButton.getHeight();
      var6 = var3 = imgAdvButton.getWidth() / 6;
      var9.drawRegion(imgAdvButton, 3 * var3, 0, var3, var10, 0, 0, 0, 20);
      var8.drawRegion(imgAdvButton, 0 * var3, 0, var3, var10, 0, 0, 0, 20);

      while(var6 < var0 - var3) {
         var8.drawRegion(imgAdvButton, var3, 0, var3, var10, 0, var6, 0, 20);
         var9.drawRegion(imgAdvButton, 4 * var3, 0, var3, var10, 0, var6, 0, 20);
         var6 += var3;
      }

      var9.drawRegion(imgAdvButton, 5 * var3, 0, var3, var10, 0, 0 + var0 - var3, 0, 20);
      var8.drawRegion(imgAdvButton, 2 * var3, 0, var3, var10, 0, 0 + var0 - var3, 0, 20);
   }

   private static void h() throws Exception {
      int var0 = StaticData.screenWidth - 4 - c.getWidth();
      Image imgTextField = Image.createImage("/textfield.png");
      int var2 = imgTextField.getHeight();
      int var3 = imgTextField.getWidth() / 6;
      e = a(imgTextField, 0, 0, var3, var2);
      g = a(imgTextField, var3, 0, var3, var2);
      f = a(imgTextField, var3 * 2, 0, var3, var2);
      h = a(imgTextField, var3 * 3, 0, var3, var2);
      j = a(imgTextField, var3 * 4, 0, var3, var2);
      i = a(imgTextField, var3 * 5, 0, var3, var2);
      C = Image.createImage(var0, var2);
      D = Image.createImage(var0, var2);
      Graphics var4 = C.getGraphics();
      Graphics var5 = D.getGraphics();
      int var6 = var3;
      var4.drawImage(e, 0, 0, 20);
      var5.drawImage(h, 0, 0, 20);

      while(var6 < var0 - var3) {
         var4.drawImage(g, var6, 0, 20);
         var5.drawImage(j, var6, 0, 20);
         var6 += var3;
      }

      var4.drawImage(f, 0 + var0 - var3, 0, 20);
      var5.drawImage(i, 0 + var0 - var3, 0, 20);
   }

   private static void i() throws Exception {
      Image imgScrollBack = Image.createImage("/scroll_back.png");
      c = Image.createImage(imgScrollBack.getWidth(), StaticData.screenHeight);
      Graphics var1 = c.getGraphics();

      for(int var2 = 0; var2 < StaticData.screenHeight; var2 += imgScrollBack.getHeight()) {
         var1.drawImage(imgScrollBack, 0, var2, 20);
      }

      Image imgScrollFront = Image.createImage("/scroll_front.png");
      d = Image.createImage(imgScrollFront.getWidth(), StaticData.screenHeight);
      var1 = d.getGraphics();

      for(int var3 = 0; var3 < StaticData.screenHeight; var3 += imgScrollFront.getHeight()) {
         var1.drawImage(imgScrollFront, 0, var3, 20);
      }

   }

   private static Image a(Image var0, int var1, int var2, int var3, int var4) {
      return var0 != null ? Image.createImage(var0, var1, var2, var3, var4, 0) : null;
   }
}
