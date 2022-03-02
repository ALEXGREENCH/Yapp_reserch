package ru.yapp.mobile.core;

import java.util.Vector;
import javax.microedition.rms.RecordStore;
import javax.microedition.rms.RecordStoreException;

import ru.yapp.mobile.ScreenCanvas;
import ru.yapp.mobile.core.collection.MapArray;
import ru.yapp.mobile.core.collection.MapItem;


public final class BdUtil {
    private static RecordStore a = null;
    private static MapArray b = new MapArray();

    public static void init() throws Exception {
        if (a == null) {
            a = RecordStore.openRecordStore("Yapp", true);
            if (a.getNumRecords() > 0) {
                c();
            }
        }

    }

    public static void destroy() throws Exception {
        if (a != null) {
            d();
            a.closeRecordStore();
        }

        b.cleanAll();
        a = null;
    }

    private static void c() {
        b.cleanAll();
        byte[] var0 = null;

        try {
            var0 = a.getRecord(1);
        } catch (RecordStoreException var2) {
            ScreenCanvas.textDialog = "ошибка хранилища";
        }

        if (var0 != null) {
            b(new String(var0));
        }

    }

    private static void b(String var0) {
        int var1 = 0;
        String var2 = null;
        int var3 = 0;

        int var5 = var0.indexOf("|", var1);
        for (int i = 0; var1 >= 0 && var1 < var0.length(); var1 = var5 + 1) {
            if (var5 == -1) {
                var5 = var0.length() - 1;
            }

            if (i % 2 == 0) {
                var2 = var0.substring(var1, var5);
            } else if (i % 2 == 1) {
                var3 = Integer.parseInt(var0.substring(var1, var5));
            }

            if (var3 > 0 && var2 != null) {
                b.add(var2, new Integer(var3));
                System.out.println(" -->" + var2 + " <> " + var3);
                var2 = null;
                var3 = 0;
            }

            ++i;
        }

    }

    private static void d() throws Exception {
        Vector var0 = b.getVector();
        String var1 = "";

        for (int i = 0; i < var0.size(); ++i) {
            MapItem var3 = (MapItem) var0.elementAt(i);
            var1 = var1 + var3.key + "|" + var3.obj + "|";
        }

        System.out.println("writed ->" + var1);
        byte[] var4 = var1.getBytes();
        a.setRecord(1, var4, 0, var4.length);
    }

    public static byte[] read(String str) {
        if (b.get(str) != null) {
            try {
                return a.getRecord(((Integer) b.get(str)).intValue());
            } catch (RecordStoreException rse) {
                return null;
            }
        } else {
            return null;
        }
    }

    public static boolean save(String var0, byte[] var1) {
        boolean var2 = true;
        if (var0 != null && var1 != null) {
            try {
                if (a.getNumRecords() == 0) {
                    byte[] var3 = "test".getBytes();
                    a.addRecord(var3, 0, var3.length);
                    Integer var4 = new Integer(a.addRecord(var1, 0, var1.length));
                    b.add(var0, var4);
                } else {
                    Integer var6;
                    if (b.get(var0) != null) {
                        var6 = (Integer) b.get(var0);
                        a.setRecord(var6.intValue(), var1, 0, var1.length);
                    } else {
                        var6 = new Integer(a.addRecord(var1, 0, var1.length));
                        b.add(var0, var6);
                    }
                }
            } catch (Exception e) {
                var2 = false;
                e.printStackTrace();
            }
        }

        return var2;
    }
}
