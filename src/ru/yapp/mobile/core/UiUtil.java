package ru.yapp.mobile.core;

import java.io.IOException;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

import ru.yapp.mobile.Yapp;

public final class UiUtil {
    private static final int[] tableFontMini = new int[]{0, 4, 8, 12, 16, 20, 24, 28, 32, 33, 36, 40, 44, 49, 53, 57, 61, 65, 69, 73, 76, 80, 84, 89, 93, 97, 101, 105, 109, 113, 117, 121, 124, 128, 132, 133, 135, 139, 140, 147, 151, 155, 159, 163, 166, 169, 172, 176, 180, 185, 189, 193, 196, 200, 204, 208, 211, 217, 221, 225, 230, 234, 238, 242, 246, 250, 255, 259, 263, 267, 271, 275, 278, 282, 287, 291, 296, 300, 305, 311, 316, 322, 326, 330, 336, 340, 344, 348, 352, 355, 360, 364, 368, 373, 377, 381, 385, 389, 393, 398, 402, 406, 410, 414, 418, 421, 425, 430, 434, 439, 443, 448, 454, 459, 465, 469, 473, 479, 483, 487, 489, 493, 497, 501, 505, 509, 513, 517, 521, 525, 526, 530, 535, 542, 547, 551, 556, 563, 567, 571, 575, 579, 581, 583, 588, 589, 591, 592, 594, 595, 599, 601, 603, 606, 611, 614, 616, 620, 627, 634, 642, 649, 653, 657};
    private static final int[] tableFontMaxi = new int[]{0, 7, 13, 19, 26, 32, 38, 45, 52, 56, 61, 67, 73, 81, 88, 95, 101, 108, 115, 121, 129, 136, 142, 152, 158, 164, 170, 176, 182, 187, 193, 199, 204, 210, 216, 218, 222, 228, 230, 240, 246, 252, 258, 264, 269, 274, 279, 285, 291, 299, 305, 311, 316, 323, 329, 335, 341, 350, 356, 362, 372, 378, 385, 392, 398, 406, 414, 421, 428, 435, 441, 447, 455, 462, 470, 476, 484, 491, 501, 512, 520, 530, 536, 542, 553, 560, 566, 572, 578, 583, 591, 597, 603, 613, 618, 623, 628, 634, 641, 648, 654, 660, 666, 672, 677, 683, 689, 697, 703, 710, 716, 724, 733, 741, 750, 756, 761, 770, 776, 782, 786, 792, 798, 804, 810, 816, 822, 828, 834, 840, 842, 848, 853, 865, 872, 878, 886, 895, 902, 909, 914, 919, 921, 923, 931, 933, 935, 937, 939, 941, 946, 950, 954, 958, 965, 972, 975, 981, 990, 999, 1008, 1020, 1027, 1034};
    public static Image img1;
    public static Image img2;
    public static Image img3;
    public static Image img4;
    public static Image img5;
    public static Image img6;
    public static Image img7;
    public static Image img8;
    public static Image img9;
    public static Image img10;
    public static Image img11;
    public static Image img12;
    public static Image img13;
    public static Image img14;
    public static Image img15;
    public static Image img16;
    public static Image imgFormatedMenuBack = null;
    public static Image formatedImgToolbar = null;
    public static Image imgMenuBack = null;
    public static Image imgSplash = null;
    public static Image[] allFontImages = new Image[10];
    public static int[] intArr2560 = new int[2560];
    public static byte[] byteArr2560 = new byte[2560];
    public static int[] allFontImagesHeight = new int[10];
    public static Image formatedMenuSel;
    public static Image formatedMenuBg;
    public static Image img17;
    public static Image img18;
    public static Image img19;
    public static Image img20;
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

        for (int i = 0; i < "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyzАБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯабвгдеёжзийклмнопрстуфхцчшщъыьэюя0123456789/|\\*№#$€@«»\"\"''&:;.,!?()-+=`§©®™%<>".length(); ++i) {
            char charFromIndex = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyzАБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯабвгдеёжзийклмнопрстуфхцчшщъыьэюя0123456789/|\\*№#$€@«»\"\"''&:;.,!?()-+=`§©®™%<>".charAt(i);
            int var6 = charTable[i];
            int var7 = charTable[i + 1];
            int var8 = type << 8;
            char var9 = StringUtils.a(charFromIndex);
            intArr2560[var9 + var8] = var6;
            byteArr2560[var9 + var8] = (byte) (var7 - var6);
        }

    }

    private static void createMenuAndToolbar() throws Exception {
        imgMenuBack = Image.createImage("/menuback.png");
        imgFormatedMenuBack = Image.createImage(StaticData.screenWidth, 17);
        Graphics graphicsImgFormatedMenuBack = imgFormatedMenuBack.getGraphics();

        for (int i = 0; i < StaticData.screenWidth; i += imgMenuBack.getWidth()) {
            graphicsImgFormatedMenuBack.drawImage(imgMenuBack, i, 0, 20);
        }

        Image menuBg = Image.createImage("/menu_bg.png");
        formatedMenuBg = Image.createImage(120, menuBg.getHeight());
        graphicsImgFormatedMenuBack = formatedMenuBg.getGraphics();

        for (int i = 0; i < 120; i += menuBg.getWidth()) {
            graphicsImgFormatedMenuBack.drawImage(menuBg, i, 0, 20);
        }

        Image menuSel = Image.createImage("/menu_sel.png");
        formatedMenuSel = Image.createImage(114, menuSel.getHeight());
        graphicsImgFormatedMenuBack = formatedMenuSel.getGraphics();

        for (int i = 0; i < 120; i += menuSel.getWidth()) {
            graphicsImgFormatedMenuBack.drawImage(menuSel, i, 0, 20);
        }

        Image topbar = Image.createImage("/topbar.png");
        formatedImgToolbar = Image.createImage(StaticData.screenWidth, 19);
        graphicsImgFormatedMenuBack = formatedImgToolbar.getGraphics();

        for (int i = 0; i < StaticData.screenWidth; ++i) {
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

        for (int i = 0; i < StaticData.screenWidth; i += imgMessagerStatusBar.getWidth()) {
            graphicsformatedImgStatusbar.drawImage(imgMessagerStatusBar, i, 0, 20);
        }
    }

    private static void g() throws Exception {
        int var0 = StaticData.screenWidth - 4 - img3.getWidth();
        Image imgButton = Image.createImage("/button.png");
        int buttonHeight = imgButton.getHeight();
        int sizeWidth_1_in_6 = imgButton.getWidth() / 6;
        img11 = createImage(imgButton, 0, 0, sizeWidth_1_in_6, buttonHeight);
        img13 = createImage(imgButton, sizeWidth_1_in_6, 0, sizeWidth_1_in_6, buttonHeight);
        img12 = createImage(imgButton, sizeWidth_1_in_6 * 2, 0, sizeWidth_1_in_6, buttonHeight);
        img14 = createImage(imgButton, sizeWidth_1_in_6 * 3, 0, sizeWidth_1_in_6, buttonHeight);
        img16 = createImage(imgButton, sizeWidth_1_in_6 * 4, 0, sizeWidth_1_in_6, buttonHeight);
        img15 = createImage(imgButton, sizeWidth_1_in_6 * 5, 0, sizeWidth_1_in_6, buttonHeight);
        img17 = Image.createImage(var0, imgButton.getHeight());
        img18 = Image.createImage(var0, imgButton.getHeight());
        Graphics var4 = img17.getGraphics();
        Graphics var5 = img18.getGraphics();
        var4.drawImage(img11, 0, 0, 20);
        var5.drawImage(img14, 0, 0, 20);

        int var6 = sizeWidth_1_in_6;

        while (var6 < var0 - sizeWidth_1_in_6) {
            var4.drawImage(img13, var6, 0, 20);
            var5.drawImage(img16, var6, 0, 20);
            var6 += sizeWidth_1_in_6;
        }

        var4.drawImage(img12, 0 + var0 - sizeWidth_1_in_6, 0, 20);
        var5.drawImage(img15, 0 + var0 - sizeWidth_1_in_6, 0, 20);
        Image imgAdvButton = Image.createImage("/adv_button.png");
        img2 = Image.createImage(var0, imgAdvButton.getHeight());
        img1 = Image.createImage(var0, imgAdvButton.getHeight());
        Graphics var8 = img2.getGraphics();
        Graphics var9 = img1.getGraphics();
        int advButtonHeight = imgAdvButton.getHeight();
        var6 = sizeWidth_1_in_6 = imgAdvButton.getWidth() / 6;
        var9.drawRegion(imgAdvButton, 3 * sizeWidth_1_in_6, 0, sizeWidth_1_in_6, advButtonHeight, 0, 0, 0, 20);
        var8.drawRegion(imgAdvButton, 0 * sizeWidth_1_in_6, 0, sizeWidth_1_in_6, advButtonHeight, 0, 0, 0, 20);

        while (var6 < var0 - sizeWidth_1_in_6) {
            var8.drawRegion(imgAdvButton, sizeWidth_1_in_6, 0, sizeWidth_1_in_6, advButtonHeight, 0, var6, 0, 20);
            var9.drawRegion(imgAdvButton, 4 * sizeWidth_1_in_6, 0, sizeWidth_1_in_6, advButtonHeight, 0, var6, 0, 20);
            var6 += sizeWidth_1_in_6;
        }

        var9.drawRegion(imgAdvButton, 5 * sizeWidth_1_in_6, 0, sizeWidth_1_in_6, advButtonHeight, 0, 0 + var0 - sizeWidth_1_in_6, 0, 20);
        var8.drawRegion(imgAdvButton, 2 * sizeWidth_1_in_6, 0, sizeWidth_1_in_6, advButtonHeight, 0, 0 + var0 - sizeWidth_1_in_6, 0, 20);
    }

    private static void h() throws Exception {
        int var0 = StaticData.screenWidth - 4 - img3.getWidth();
        Image imgTextField = Image.createImage("/textfield.png");
        int var2 = imgTextField.getHeight();
        int var3 = imgTextField.getWidth() / 6;
        img5 = createImage(imgTextField, 0, 0, var3, var2);
        img7 = createImage(imgTextField, var3, 0, var3, var2);
        img6 = createImage(imgTextField, var3 * 2, 0, var3, var2);
        img8 = createImage(imgTextField, var3 * 3, 0, var3, var2);
        img10 = createImage(imgTextField, var3 * 4, 0, var3, var2);
        img9 = createImage(imgTextField, var3 * 5, 0, var3, var2);
        img19 = Image.createImage(var0, var2);
        img20 = Image.createImage(var0, var2);
        Graphics var4 = img19.getGraphics();
        Graphics var5 = img20.getGraphics();
        int var6 = var3;
        var4.drawImage(img5, 0, 0, 20);
        var5.drawImage(img8, 0, 0, 20);

        while (var6 < var0 - var3) {
            var4.drawImage(img7, var6, 0, 20);
            var5.drawImage(img10, var6, 0, 20);
            var6 += var3;
        }

        var4.drawImage(img6, 0 + var0 - var3, 0, 20);
        var5.drawImage(img9, 0 + var0 - var3, 0, 20);
    }

    private static void i() throws Exception {
        Image imgScrollBack = Image.createImage("/scroll_back.png");
        img3 = Image.createImage(imgScrollBack.getWidth(), StaticData.screenHeight);
        Graphics var1 = img3.getGraphics();

        for (int i = 0; i < StaticData.screenHeight; i += imgScrollBack.getHeight()) {
            var1.drawImage(imgScrollBack, 0, i, 20);
        }

        Image imgScrollFront = Image.createImage("/scroll_front.png");
        img4 = Image.createImage(imgScrollFront.getWidth(), StaticData.screenHeight);
        var1 = img4.getGraphics();

        for (int i = 0; i < StaticData.screenHeight; i += imgScrollFront.getHeight()) {
            var1.drawImage(imgScrollFront, 0, i, 20);
        }

    }

    private static Image createImage(Image img, int x, int y, int w, int h) {
        return img != null ? Image.createImage(img, x, y, w, h, 0) : null;
    }
}
