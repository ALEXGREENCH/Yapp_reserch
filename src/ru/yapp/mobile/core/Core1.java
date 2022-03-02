package ru.yapp.mobile.core;

import java.util.Random;
import javax.microedition.lcdui.Image;

import ru.yapp.mobile.gui.Gui4;

public final class Core1 {
    public static Gui4[] gElemArr = null;
    private static Gui4 gElem = null;

    public static Image a() {
        return gElem != null ? gElem.image : null;
    }

    public static int b() {
        return gElem != null ? gElem.a : 0;
    }

    public static void a(int var0) {
        if (gElemArr != null) {
            if (var0 != 0) {
                gElem = null;

                for (int i = 0; i < gElemArr.length; ++i) {
                    if (gElemArr[i].a == var0) {
                        gElem = gElemArr[i];
                    }
                }

                return;
            }

            int l = (new Random()).nextInt() % 100;
            int counter = 0;

            for (int i = 0; i < gElemArr.length && l < counter; ++i) {
                counter += gElemArr[i].d;
                if (l < counter) {
                    gElem = gElemArr[i];
                }
            }

            if (gElem == null) {
                gElem = gElemArr[0];
            }
        }

    }

    public static void c() {
        gElem = null;
    }

    public static int d() {
        return gElem != null ? gElem.b : 0;
    }
}
