package ru.yapp.mobile.core;

import javax.microedition.lcdui.Graphics;

public final class StringUtils {

    public static int a = 5;
    public static int b = 1;

    public static int a(String str, int var1) {
        int var2 = 0;
        int var3 = str.length();

        for (int i = 0; i < var3; ++i) {
            char var5 = str.charAt(i);
            if (var5 != ' ') {
                int var6 = a(var5) + (var1 << 8);
                byte var7 = UiUtil.byteArr2560[var6];
                var2 = var2 + var7 + b;
            } else {
                var2 += a;
            }
        }

        return var2;
    }

    public static int a(byte[] byteArr, int var1) {
        int count = 0;
        int byteArrLength = byteArr.length;

        for (int i = 0; i < byteArrLength; ++i) {
            int var5 = byteArr[i];
            if (var5 < 0) {
                var5 += 256;
            }
            if (var5 != 32) {
                var5 += var1 << 8;
                byte var6 = UiUtil.byteArr2560[var5];
                count = count + var6 + b;
            } else {
                count += a;
            }
        }

        return count;
    }

    public static void a(Graphics g, int var1, int var2, String str, int var4) {
        int var5 = 0;
        int var6 = str.length();
        char[] var7 = str.toCharArray();

        for (int i = 0; i < var6; ++i) {
            char var9 = var7[i];
            if (var9 != ' ') {
                int var10 = a(var9) + (var4 << 8);
                int var11 = UiUtil.intArr2560[var10];
                byte var12 = UiUtil.byteArr2560[var10];
                byte var13 = (byte) UiUtil.allFontImagesHeight[var4];
                if (var12 > 0) {
                    g.drawRegion(UiUtil.allFontImages[var4], var11, 0, var12, var13, 0, var1 + var5, var2, 20);
                    var5 = var5 + var12 + b;
                }
            } else {
                var5 += a;
            }
        }

    }

    public static void a(Graphics g, int var1, int var2, byte[] byteArr, int var4) {
        if (byteArr != null) {
            int var5 = 0;
            int var6 = byteArr.length;

            for (int i = 0; i < var6; ++i) {
                int var8 = byteArr[i];
                if (var8 < 0) {
                    var8 += 256;
                }

                if (var8 != 32) {
                    var8 += var4 << 8;
                    int var9 = UiUtil.intArr2560[var8];
                    byte var10 = UiUtil.byteArr2560[var8];
                    byte var11 = (byte) UiUtil.allFontImagesHeight[var4];
                    if (var10 > 0) {
                        g.drawRegion(UiUtil.allFontImages[var4], var9, 0, var10, var11, 0, var1 + var5, var2, 20);
                        var5 = var5 + var10 + b;
                    }
                } else {
                    var5 += a;
                }
            }

        }
    }

    public static void b(Graphics g, int var1, int var2, byte[] byteArr, int var4) {
        int var5 = 0;
        int var6 = byteArr.length;

        for (int i = 0; i < var6; ++i) {
            int var8 = byteArr[i];
            if (var8 < 0) {
                var8 += 256;
            }

            byte var9 = UiUtil.byteArr2560[var8];
            byte var10 = (byte) UiUtil.allFontImagesHeight[var4];
            if (var8 != 32) {
                var8 += var4 << 8;
                int var11 = UiUtil.intArr2560[var8];
                if (var9 > 0) {
                    g.setColor(0xFF); // белый
                    g.fillRect(var1 + var5, var2 - 1, var9 + 1, var10 + 2);
                    g.drawRegion(UiUtil.allFontImages[var4], var11, 0, var9, var10, 0, var1 + var5, var2, 20);
                    var5 = var5 + var9 + b;
                }
            } else {
                g.setColor(0xFF); // белый
                g.fillRect(var1 + var5, var2 - 1, a, var10 + 2);
                var5 += a;
            }
        }

    }

    public static String[] a(int var0, int var1, String var2, int var3) {
        //boolean var4 = false;
        int var5 = var2.length();
        int var6 = 0;
        int var7 = var1 -= 8;
        int var13 = var0 + var1 / 2;
        String[] var8 = new String[5120];
        var8[0] = "";
        //int var10;
        if (var5 > 0) {
            String var9 = null;

            int var12;
            for (int i = 0; i < var5; var13 += var12 + a) {
                int var11 = var2.indexOf(" ", i);
                if (var11 == -1) {
                    var9 = var2.substring(i, var5);
                    i = var5;
                } else {
                    var9 = var2.substring(i, var11);
                    i = var11 + 1;
                }

                var12 = a(var9, var3);

                if (var12 > var1) {
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

        for (int i = 0; i < var8.length && var8[i] != null; ++i) {
            var14[i] = var8[i];
        }

        return var14;
    }

    public static void a(Graphics g, int var1, int var2, int var3, int var4, String var5, int var6) {
        int var7 = var1;
        int var8 = var2;
        int var9 = var5.length();
        int var13;
        if (var9 > 0) {
            for (int i = 0; i < var9; var7 += var13 + a) {
                int var11 = var5.indexOf(" ", i);
                String var12;
                if (var11 == -1) {
                    var12 = var5.substring(i, var9);
                    i = var9;
                } else {
                    var12 = var5.substring(i, var11);
                    i = var11 + 1;
                }

                var13 = a(var12, var6);

                if (var13 > var3 - (var7 - var1)) {
                    if (var7 > var1) {
                        var7 = var1;
                        var8 = var8 + UiUtil.allFontImagesHeight[var6] + 2;
                    }
                } else if (var7 + var13 > var1 + var3) {
                    var7 = var1;
                    var8 = var8 + UiUtil.allFontImagesHeight[var6] + 2;
                }

                if (var8 - var2 + UiUtil.allFontImagesHeight[var6] < var4) {
                    a(g, var7, var8, var12, var6);
                }
            }
        }

    }

    public static char a(char var0) {
        char var1 = var0;
        char var10000 = (var1 == 8470 ? 140 : var0) == 8364 ? 141 : var1;
        var1 = var10000;
        var10000 = var10000 == 8482 ? 142 : var1;
        var1 = var10000;
        int var2;
        return (char) ((var2 = var10000 > 1000 ? var1 - 850 : var1) > 255 ? 0 : var2);
    }
}
