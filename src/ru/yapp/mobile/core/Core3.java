package ru.yapp.mobile.core;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Vector;
import javax.microedition.lcdui.Image;

import ru.yapp.mobile.ScreenCanvas;
import ru.yapp.mobile.Yapp;
import ru.yapp.mobile.book.BookUtil;
import ru.yapp.mobile.browser.BrowserForm;
import ru.yapp.mobile.browser.BrowserRender;
import ru.yapp.mobile.gui.EditText;
import ru.yapp.mobile.gui.SelectableFormView;
import ru.yapp.mobile.gui.Gui4;
import ru.yapp.mobile.gui.CheckBox;
import ru.yapp.mobile.gui.ViewElement;
import ru.yapp.mobile.messenger.CommandListener2;
import ru.yapp.mobile.messenger.Messenger;
import ru.yapp.mobile.messenger.Messenger2;
import ru.yapp.mobile.net.NetworkUtil;
import ru.yapp.mobile.net.SendPackets;

public final class Core3 {
    public static int a = 0;
    public static Vector vectorInt2 = new Vector();
    public static Vector vectorData = new Vector();
    public static Vector vectorInt1 = new Vector();

    public static void a() {
        int sizeVectorData = vectorData.size();
        if (vectorData.size() > 1) {
            try {
                BrowserRender.renderGUI((byte[]) ((byte[]) vectorData.elementAt(sizeVectorData - 2)), // хз
                        true, // хз
                        ((Integer) vectorInt1.elementAt(sizeVectorData - 1)).intValue(), //хз
                        ((Integer) vectorInt2.elementAt(sizeVectorData - 1)).intValue() //хз
                );
                // дроп по последнему индексу 2 вектора
                vectorData.removeElementAt(sizeVectorData - 1);
                vectorInt1.removeElementAt(sizeVectorData - 1);
                vectorInt2.removeElementAt(sizeVectorData - 1);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /*
         Команды:
         66  -
         99  -
         101 -
         103 -
         104 -
         105 -
    `    106 -
         107 -
         108 -
         109 -
         110 -
         111 -
         112 -
         151 -
         152 -
         703 - книги
         901 - баннер

    */
    public static void parsingReceivedPackets(int idCommand, byte[] byteArr) {
        Yapp.Log("пришла команда " + idCommand);
        switch (idCommand) {
            case 66:
                if (a > 0) {
                    --a;
                    return;
                } else {
                    try {
                        vectorData.addElement(byteArr);
                        vectorInt1.addElement(new Integer(BrowserForm.o));
                        vectorInt2.addElement(new Integer(BrowserForm.p));
                        BrowserRender.renderGUI(byteArr, false, -1, 0);
                        return;
                    } catch (Exception e) {
                        Yapp.Log("ошибка парсинга");
                        e.printStackTrace();
                        return;
                    }
                }
            case 99:
                c(byteArr);
                return;
            case 101:
                Messenger.j(byteArr);
                return;
            case 103:
                Messenger.b(byteArr);
                return;
            case 104:
                Messenger.a(byteArr);
                return;
            case 105:
                Messenger.c(byteArr);
                return;
            case 106:
                Messenger.l(byteArr);
                return;
            case 107:
                Messenger.g(byteArr);
                return;
            case 108:
                Messenger.k(byteArr);
                return;
            case 109:
                Messenger.h(byteArr);
                return;
            case 110:
                Messenger.d(byteArr);
                return;
            case 111:
                Messenger.m(byteArr);
                return;
            case 112:
                Messenger.i(byteArr);
                return;
            case 151:
                Messenger.e(byteArr);
                return;
            case 152:
                Messenger.f(byteArr);
                return;
            case 703:
                try {
                    BookUtil.b(byteArr);
                    return;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            default:
                return;
            case 901:
                Yapp.Log("BANNERS COME IN");

                try {
                    loadingBanner(byteArr);
                } catch (Exception e) {
                    Yapp.Log("BANNER LOADING ERROR");
                    e.printStackTrace();
                }
        }
    }

    public static void parsingPostUiAction(byte[] byteArr) throws Exception {
        ByteArrayInputStream bais = new ByteArrayInputStream(byteArr);
        DataInputStream dis = new DataInputStream(bais);
        switch (dis.readShort()) {
            case 1:
                c();
                return;
            case 50:
                BrowserRender.renderAuthScreen(new byte[]{1, 0, 99, 0, 0, 0, 0}, "Yapp! ");
                return;
            case 98:
                byte[] var10 = new byte[dis.readInt()];
                dis.read(var10);
                b(var10);
                return;
            case 99:
                BrowserForm.c();
                return;
            case 101:
                BrowserForm.d();
                return;
            case 107:
                b();
                return;
            case 160:
                dis.readInt();
                int var15 = dis.readInt();
                Messenger.cmdListener2Arr[0].c(var15);
                break;
            case 199:
                if (StaticData.connectID > 0) {
                    if (Messenger.cmdListener2Arr[Messenger.b] == null) {
                        BrowserRender.readInForm();
                        Messenger2.renderIcqAuthScreen();
                    } else {
                        StaticData.g = 1;
                    }

                    ScreenCanvas.screenMode = 2;
                    Messenger2.b = true;
                    return;
                }

                ScreenCanvas.textDialog = "Сначала необходимо подключиться к Yapp";
                ScreenCanvas.boolean2 = true;
                return;
            case 651:
                dis.readInt(); // игнор инта
                String str1 = dis.readUTF();
                String str2 = dis.readUTF();
                String str3 = dis.readUTF();
                a(str3, str2, str1);
                return;
            case 703: // отправка информации о размере рам памяти?
                dis.readInt(); // игнор инта
                BrowserForm.bool1 = true;
                BrowserForm.prepareRender = true;
                int var11 = dis.readInt();
                long totalMemory = Runtime.getRuntime().totalMemory();
                BytesContentFactory bfc = new BytesContentFactory();
                bfc.setType((short) 703);
                bfc.addInt(var11);
                bfc.addLong(totalMemory);
                SendPackets.addByteArrData(bfc.bytesArray());
                return;
            case 1001:
                dis.readInt();
                String url = dis.readUTF();
                if (url.length() > 0) {
                    a(url, dis.readByte());
                }
        }
    }

    private static void b() {
        if (Messenger.b != -1 && Messenger.cmdListener2Arr[Messenger.b] != null) {
            SelectableFormView gui3 = (SelectableFormView) BrowserForm.getUiElements()[1];
            byte var2 = 0;

            for (byte i = 0; i < gui3.vars.length; ++i) {
                if (gui3.text.equals(gui3.vars[i])) {
                    var2 = i;
                }
            }

            Messenger.c(var2);
        }

        BrowserForm.prepareRender = true;
        Messenger2.a = false;
        Messenger2.b = true;
        BrowserRender.writeToForm();
    }

    private static void c() {
        if (Messenger.b != -1 && Messenger.cmdListener2Arr[Messenger.b] != null) {
            ViewElement[] var0 = BrowserForm.getUiElements();
            CommandListener2 cmdListener2 = Messenger.cmdListener2Arr[Messenger.b];
            cmdListener2.l = ((CheckBox) var0[0]).isChecked;
            if (cmdListener2.m != ((CheckBox) var0[1]).isChecked) {
                cmdListener2.f();
            }

            if (cmdListener2.n != ((CheckBox) var0[2]).isChecked) {
                cmdListener2.g();
            }

            String var2 = cmdListener2.m();
            byte[] var3 = new byte[]{1};
            byte[] var4 = new byte[]{0};
            if (cmdListener2.m) {
                BdUtil.save(var2 + "-sound", var3);
            } else {
                BdUtil.save(var2 + "-sound", var4);
            }

            if (cmdListener2.l) {
                BdUtil.save(var2 + "-vibro", var3);
            } else {
                BdUtil.save(var2 + "-vibro", var4);
            }

            if (cmdListener2.n) {
                BdUtil.save(var2 + "-hide", var3);
            } else {
                BdUtil.save(var2 + "-hide", var4);
            }
        }

        BrowserForm.prepareRender = true;
        Messenger2.a = false;
        Messenger2.b = true;
        BrowserRender.writeToForm();
    }

    private static void b(byte[] var0) throws IOException {
        BrowserForm.bool1 = true;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        DataOutputStream dos = new DataOutputStream(baos);
        ViewElement[] viewArrInBrowserForm = BrowserForm.getUiElements();

        for (int i = 0; i < viewArrInBrowserForm.length; ++i) {
            if (viewArrInBrowserForm[i].boolean1) {
                if (viewArrInBrowserForm[i] instanceof EditText) {
                    dos.writeUTF(viewArrInBrowserForm[i].text);
                } else if (viewArrInBrowserForm[i] instanceof SelectableFormView) {
                    dos.writeUTF(viewArrInBrowserForm[i].text);
                } else if (viewArrInBrowserForm[i] instanceof CheckBox) {
                    if (((CheckBox) viewArrInBrowserForm[i]).isChecked) {
                        dos.writeByte(1);
                    } else {
                        dos.writeByte(0);
                    }
                }
            }
        }

        dos.flush();
        baos.flush();
        byte[] byteArr = baos.toByteArray();
        dos.close();
        baos.close();
        baos = new ByteArrayOutputStream();
        dos = new DataOutputStream(baos);
        dos.write(var0, 0, 2);
        dos.writeInt(var0.length - 6 + byteArr.length);
        dos.write(var0, 6, var0.length - 6);
        dos.write(byteArr);
        dos.flush();
        baos.flush();
        SendPackets.b(baos.toByteArray());
        dos.close();
        baos.close();
    }

    private static void a(String var0, String var1, String var2) throws Exception {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        DataOutputStream dos = new DataOutputStream(baos);
        dos.writeUTF(var2);
        dos.writeInt(0);
        dos.writeShort(2);
        dos.writeShort(2);
        dos.writeByte(0);
        dos.writeByte(0);
        dos.writeUTF(var1);
        dos.writeByte(0);
        dos.writeByte(0);
        dos.writeShort(2);
        dos.writeByte(1);
        dos.writeByte(0);
        dos.writeUTF(var0);
        dos.writeByte(0);
        dos.writeByte(0);
        dos.writeShort(0);
        dos.flush();
        baos.flush();
        byte[] var5 = baos.toByteArray();
        vectorData.addElement(var5);
        vectorInt1.addElement(new Integer(BrowserForm.o));
        vectorInt2.addElement(new Integer(BrowserForm.p));
        BrowserRender.renderGUI(var5, false, -1, 0);
    }

    private static void a(String url, byte var1) throws Exception {
        Yapp.Log("URL->" + url);
        boolean var2 = Yapp.yappMidlet.platformRequest(url);
        System.out.println("needClose: " + var2);
        if (var2 || var1 == 1) {
            Yapp.yappMidlet.midletDestroy();
        }

    }

    private static void c(byte[] byteArr) {
        ByteArrayInputStream bais = new ByteArrayInputStream(byteArr);
        DataInputStream dis = new DataInputStream(bais);
        String str = "";

        try {
            dis.readUTF();
            str = dis.readUTF();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (str.equals("")) {
            vectorInt1.removeAllElements();
            vectorData.removeAllElements();
            vectorInt2.removeAllElements();
            BytesContentFactory var5;
            (var5 = new BytesContentFactory()).setType((short) 901);
            var5.addInt(StaticData.screenWidth);
            var5.addInt(StaticData.screenHeight);
            SendPackets.addByteArrData(var5.bytesArray());
            Messenger.b((byte) 0);
            BrowserForm.authMenuVariants = new String[]{"На весь экран", "Общайся", "Библиотека", "Помощь", "Выход"};
            BrowserForm.__messagerValue = 1;
            BrowserForm.__bookValue = 2;
            BrowserForm._helpValue = 3;
            BrowserForm._exitAppValue = 4;
        } else {
            NetworkUtil.destroy();
            ScreenCanvas.textDialog = str;
            BrowserForm.bool1 = false;
            BrowserForm.prepareRender = true;
            ScreenCanvas.boolean2 = true;
        }
    }

    private static void loadingBanner(byte[] byteArr) throws Exception {
        Yapp.Log("LEN->" + byteArr.length);
        ByteArrayInputStream var1 = new ByteArrayInputStream(byteArr);
        DataInputStream var2 = new DataInputStream(var1);
        short var3 = var2.readShort();
        Gui4[] var4 = new Gui4[var3];

        for (int i = 0; i < var3; ++i) {
            var4[i] = new Gui4();
            var4[i].a = var2.readInt();
            var4[i].d = var2.readByte();
            BrowserRender.a((ViewElement) var4[i], (DataInputStream) var2);
            byte[] var7 = new byte[var2.readInt()];
            var2.readFully(var7);
            ByteArrayInputStream var8;
            Image var9 = Image.createImage(var8 = new ByteArrayInputStream(var7));
            var8.close();
            var4[i].image = var9;
            var4[i].a();
        }

        var1.close();
        Core1.gElemArr = var4;
        BytesContentFactory var10 = new BytesContentFactory();
        var10.setType((short) 801);
        var10.addByte((byte) 0);
        SendPackets.addByteArrData(var10.bytesArray());
    }
}
