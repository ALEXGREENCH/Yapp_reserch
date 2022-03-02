package ru.yapp.mobile.browser;

import ru.yapp.mobile.ScreenCanvas;
import ru.yapp.mobile.Yapp;
import ru.yapp.mobile.book.BookUtil;
import ru.yapp.mobile.core.*;
import ru.yapp.mobile.gui.*;
import ru.yapp.mobile.messenger.Messenger;
import ru.yapp.mobile.messenger.Messenger2;
import ru.yapp.mobile.net.NetworkUtil;
import ru.yapp.mobile.net.SendPackets;
import ru.yapp.mobile.net.ReceivePackets;

import javax.microedition.lcdui.Graphics;

public final class BrowserForm {

    public static String a = "";
    public static boolean bool1 = false;
    public static String titleYappBrowser = "yapp-browser";
    public static int d = 0;
    public static boolean bool2 = false;
    public static boolean bool3 = true;
    public static boolean bool4 = false;
    public static int bgColor = 0;
    public static int __messagerValue = -1;
    public static int __bookValue = 1;
    public static int _helpValue = 2;
    public static int _exitAppValue = 3;
    public static String[] authMenuVariants = new String[]{"На весь экран", "Библиотека", "Помощь", "Выход"};
    public static boolean prepareRender = true;
    public static int o = -1;
    public static int p = 0;
    public static int q = 0;
    public static int r = 0;
    public static int s = 0;
    private static int t = 0;
    private static boolean bool5 = false; // u
    private static ViewElement[] uiElementArr = null;

    public static void a(ViewElement[] value) {
        uiElementArr = value;
    }

    public static ViewElement[] getUiElements() {
        return uiElementArr;
    }

    public static int b() {
        if (bool3) {
            r = StaticData.screenHeight - d;
        } else {
            r = StaticData.screenHeight;
        }

        if (!bool4 || bool5) {
            r -= 34;
        }

        return r;
    }

    public static void setGraphics(Graphics graphics) {
        if (prepareRender) {
            prepareRender = false;
            graphics.setColor(bgColor);
            graphics.fillRect(0, 0, StaticData.screenWidth, StaticData.screenHeight);
            if (bool3) {
                r = StaticData.screenHeight - d;
                s = d;
            } else {
                r = StaticData.screenHeight;
                s = 0;
            }

            if (bool4 && !bool5) {
                if (d > 0 && bool2 && bool3) {
                    graphics.setColor(0);
                    graphics.fillRect(0, 0, StaticData.screenWidth, d);
                }

                if (d > 0 && bool3) {
                    graphics.drawImage(Core1.a(), 2, 2, 20);
                }
            } else {
                r -= 34;
                s += 17;
                if (d > 0 && bool2 && bool3) {
                    graphics.setColor(0);
                    graphics.fillRect(0, 17, StaticData.screenWidth, d);
                }

                if (d > 0 && bool3) {
                    graphics.drawImage(Core1.a(), StaticData.screenWidth / 2, 17 + d / 2, 3);
                }
            }

            int gClipX = graphics.getClipX();
            int gClipY = graphics.getClipY();
            int gClipW = graphics.getClipWidth();
            int gClipH = graphics.getClipHeight();
            int var5 = -p + s;
            graphics.setClip(0, s, StaticData.screenWidth, r);
            graphics.translate(0, var5);
            if (uiElementArr != null) {
                for (int i = 0; i < uiElementArr.length; ++i) {
                    if ((uiElementArr[i].l < p && uiElementArr[i].l + uiElementArr[i].imgH > p || uiElementArr[i].l < p + r && uiElementArr[i].l + uiElementArr[i].imgH > p + r || uiElementArr[i].l >= p && uiElementArr[i].l + uiElementArr[i].imgH <= p + r) && uiElementArr[i] != null) {
                        if (uiElementArr[i] instanceof TextView) {
                            TextView var7;
                            (var7 = (TextView) uiElementArr[i]).a = p;
                            var7.b = p + r;
                        }

                        if (i == o) {
                            uiElementArr[i].b(graphics);
                        } else {
                            uiElementArr[i].a(graphics);
                        }
                    }
                }
            }

            graphics.translate(0, -var5);
            graphics.setClip(gClipX, gClipY, gClipW, gClipH);
            c(graphics);
            setGraphics2(graphics);
        }

    }

    private static void c(Graphics graphics) {
        if (q > r || p > 0) {
            int var1 = r * r / q;
            int var2;
            if ((var2 = p * r / q) + var1 > r) {
                var2 = r - var1;
            }

            int var3 = UiUtil.img3.getWidth();
            int var4 = StaticData.screenWidth - var3;
            graphics.drawRegion(UiUtil.img3, 0, 0, var3, r, 0, var4, s, 20);
            var3 = UiUtil.img4.getWidth();
            var4 = StaticData.screenWidth - var3;
            graphics.drawRegion(UiUtil.img4, 0, 0, var3, var1, 0, var4, s + var2, 20);
        }

    }

    public static void setGraphics2(Graphics g) {
        int colorNetworkState;
        if (!bool4 || bool5) {
            g.drawImage(UiUtil.formatedImgToolbar, 0, 0, 20);
            StringUtils.a(g, StringUtils.a, (17 - UiUtil.allFontImagesHeight[9]) / 2, (String) titleYappBrowser, 9);
            g.drawImage(UiUtil.imgFormatedMenuBack, 0, StaticData.screenHeight - 17, 20);
            if (bool5) {
                StringUtils.a(g, StringUtils.a, StaticData.screenHeight - 17 + (17 - UiUtil.allFontImagesHeight[9]) / 2, (String) "Выбрать", 9);
            } else {
                StringUtils.a(g, StringUtils.a, StaticData.screenHeight - 17 + (17 - UiUtil.allFontImagesHeight[9]) / 2, (String) "Меню", 9);
            }

            if (!bool5 && !bool1) {
                if (Core3.vectorData.size() > 1) {
                    colorNetworkState = StringUtils.a((String) "Назад", 9);
                    StringUtils.a(g, StaticData.screenWidth - colorNetworkState - StringUtils.a, StaticData.screenHeight - 17 + (17 - UiUtil.allFontImagesHeight[9]) / 2, (String) "Назад", 9);
                }
            } else {
                colorNetworkState = StringUtils.a((String) "Отмена", 9);
                StringUtils.a(g, StaticData.screenWidth - colorNetworkState - StringUtils.a, StaticData.screenHeight - 17 + (17 - UiUtil.allFontImagesHeight[9]) / 2, (String) "Отмена", 9);
            }

            String msg___ = a;
            if (a.equals("")) {
                if (bool1) {
                    msg___ = "Загрузка";
                } else {
                    msg___ = ScreenCanvas.str1;
                    if (Messenger2.c) {
                        msg___ = "";
                    }
                }
            }

            if (msg___.length() > 0) {
                int var2 = StringUtils.a((String) msg___, 9);
                StringUtils.a(g, (StaticData.screenWidth - var2) / 2, StaticData.screenHeight - 17 + (17 - UiUtil.allFontImagesHeight[9]) / 2, (String) msg___, 9);
            }
        }

        if (!bool1) {
            Messenger2.a(g);
        }

        if (bool4) {
            if (Messenger2.currentTimeMillis == 0L) {
                Messenger2.currentTimeMillis = System.currentTimeMillis();
            }

            if (System.currentTimeMillis() - Messenger2.currentTimeMillis > 2000L) {
                Messenger2.d = false;
                Messenger2.currentTimeMillis = 0L;
            }
        } else {
            Messenger2.d = true;
        }

        if (bool5) {
            a(g, authMenuVariants, t);
        }

        colorNetworkState = NetworkUtil.isRunning ? '\uff00' : 0xFF0000;
        g.setColor(colorNetworkState); // красный если сеть не активна
        g.fillRect(StaticData.screenWidth / 2 - 1, 0, 2, UiUtil.formatedImgToolbar.getHeight());
    }

    public static void a(Graphics g, String[] var1, int var2) {
        int var3 = (12 - UiUtil.allFontImagesHeight[0]) / 2;
        int var4;
        int var5;
        if ((var5 = (var4 = StaticData.screenHeight - var1.length * 12 - 3 - 17) + var2 * 12) < 20) {
            var4 -= var5 - 20;
        }

        g.setColor(255, 255, 255); // белый
        g.fillRect(0, var4 - 3, 120, var1.length * 12 + 6);
        int var6 = g.getClipX();
        int var7 = g.getClipY();
        int var8 = g.getClipWidth();
        int var9 = g.getClipHeight();
        g.setClip(0, var4 - 3, 120, var1.length * 12 + 6);
        g.drawImage(UiUtil.formatedMenuBg, 0, var4 - 3, 20);
        g.setClip(var6, var7, var8, var9);
        g.setColor(0xBE403E); // красно-гранатовый
        g.drawRect(0, var4 - 3, 120, var1.length * 12 + 6);

        for (int i = 0; i < var1.length; ++i) {
            if (i == var2) {
                g.drawImage(UiUtil.formatedMenuSel, 3, var4 + i * 12 + 6, 6);
                StringUtils.a(g, StringUtils.a, var4 + i * 12 + var3, (String) var1[i], 4);
            } else {
                StringUtils.a(g, StringUtils.a, var4 + i * 12 + var3, (String) var1[i], 0);
            }
        }

    }

    public static void a(int var0, int var1) {
        if (!bool5) {
            if (var0 == StaticData.rightSoftKey) {
                bool5 = true;
                t = 0;
                prepareRender = true;
                return;
            }

            if (!bool1) {
                if (var0 == StaticData.leftSoftKey) {
                    if (Core3.vectorData.size() > 1) {
                        bool1 = true;
                        Core3.a();
                        prepareRender = true;
                        return;
                    }
                } else {
                    if (var0 != 50 && var1 != 1) {
                        if (var0 != 56 && var1 != 6) {
                            if (var0 == 52 || var1 == 2) {
                                f();
                                return;
                            }

                            if (var0 == 54 || var1 == 5) {
                                e();
                                return;
                            }

                            if (var0 == 48) {
                                bool4 = !bool4;
                                prepareRender = true;
                                return;
                            }

                            if (var1 == 8) {
                                if (bool2) {
                                    n();
                                    return;
                                }

                                if (o >= 0 && uiElementArr[o] != null && uiElementArr[o].boolean1) {
                                    a(uiElementArr[o]);
                                    return;
                                }
                            }

                            return;
                        }

                        h();
                        return;
                    }

                    g();
                    return;
                }
            } else if (var0 == StaticData.leftSoftKey) {
                ++Core3.a;
                bool1 = false;
                a = "";
                prepareRender = true;
                return;
            }
        } else {
            if (var0 == StaticData.rightSoftKey) {
                var0 = 53;
            }

            switch (var0) {
                case 50:
                    --t;
                    if (t < 0) {
                        t = authMenuVariants.length - 1;
                    }

                    prepareRender = true;
                    return;
                case 53:
                    if (t == _helpValue) {
                        BrowserRender.renderHelpScreen("Добро пожаловать в мир Yapp!\n\nВ Yapp! Вы найдете целый перечень удобных и полезных услуг:\n\nВ разделе «Общайся!» Вы можете переписываться с другими пользователями в Yapp! мессенджере.\n \nИз мессенджера вы можете перейти в мобильную ICQ. Для этого нажмите джойстик «вправо». \n\nВ разделе «Будь в курсе!» Вас ждут новости, прогноз погоды, курсы валют, гороскопы и другая полезная информация.\n\nRSS –  здесь можно получить краткие описания новостей, анонсы, информацию об изменениях в Интернет-журналах (блогах) – содержание зависит от источника RSS.   \n\nВы можете сыграть со своим другом партию в «Сота» или сами испытать удачу в играх «Слотс» и «Видеопокер». Ссылки на эти игры вы найдете в разделе «Играй!».\n\nВ «Читай!» вы найдете библиотеку. Там вы сможете, как в интернете, читать разнообразные книги любых жанров - от фантастики до учебников. \n\nЖелаем Вам приятного времяпрепровождения и общения! \n\n");
                    }

                    if (t == _exitAppValue) {
                        Yapp.yappMidlet.midletDestroy();
                    }

                    if (t == -1 && o >= 0 && uiElementArr[o] != null && uiElementArr[o].boolean1) {
                        a(uiElementArr[o]);
                    }

                    if (t == __messagerValue) {
                        if (StaticData.connectID > 0 && Messenger.b > -1) {
                            if (Messenger.cmdListener2Arr[Messenger.b] == null) {
                                BrowserRender.readInForm();
                                Messenger2.renderIcqAuthScreen();
                            } else {
                                StaticData.g = 1;
                            }

                            ScreenCanvas.screenMode = 2;
                            Messenger2.b = true;
                        } else {
                            ScreenCanvas.textDialog = "Сначала необходимо подключиться к Yapp";
                            ScreenCanvas.boolean2 = true;
                        }
                    }

                    if (t == 0) {
                        bool4 = !bool4;
                    }

                    if (t == __bookValue) {
                        BookUtil.a = true;
                        ScreenCanvas.screenMode = 3;
                        ScreenCanvas.boolean2 = true;
                    }

                    bool5 = false;
                    prepareRender = true;
                    return;
                case 56:
                    ++t;
                    if (t >= authMenuVariants.length) {
                        t = 0;
                    }

                    prepareRender = true;
                    return;
                default:
                    if (var0 == StaticData.leftSoftKey) {
                        bool5 = false;
                        prepareRender = true;
                    }

                    switch (var1) {
                        case 1:
                            --t;
                            if (t < 0) {
                                t = authMenuVariants.length - 1;
                            }

                            prepareRender = true;
                            return;
                        case 6:
                            ++t;
                            if (t >= authMenuVariants.length) {
                                t = 0;
                            }

                            prepareRender = true;
                            return;
                        case 8:
                            if (t == _helpValue) {
                                BrowserRender.renderHelpScreen("Добро пожаловать в мир Yapp!\n\nВ Yapp! Вы найдете целый перечень удобных и полезных услуг:\n\nВ разделе «Общайся!» Вы можете переписываться с другими пользователями в Yapp! мессенджере.\n \nИз мессенджера вы можете перейти в мобильную ICQ. Для этого нажмите джойстик «вправо». \n\nВ разделе «Будь в курсе!» Вас ждут новости, прогноз погоды, курсы валют, гороскопы и другая полезная информация.\n\nRSS –  здесь можно получить краткие описания новостей, анонсы, информацию об изменениях в Интернет-журналах (блогах) – содержание зависит от источника RSS.   \n\nВы можете сыграть со своим другом партию в «Сота» или сами испытать удачу в играх «Слотс» и «Видеопокер». Ссылки на эти игры вы найдете в разделе «Играй!».\n\nВ «Читай!» вы найдете библиотеку. Там вы сможете, как в интернете, читать разнообразные книги любых жанров - от фантастики до учебников. \n\nЖелаем Вам приятного времяпрепровождения и общения! \n\n");
                            }

                            if (t == _exitAppValue) {
                                Yapp.yappMidlet.midletDestroy();
                            }

                            if (t == -1 && o >= 0 && uiElementArr[o] != null && uiElementArr[o].boolean1) {
                                a(uiElementArr[o]);
                            }

                            if (t == __messagerValue) {
                                if (StaticData.connectID > 0 && Messenger.b > -1) {
                                    if (Messenger.cmdListener2Arr[Messenger.b] == null) {
                                        BrowserRender.readInForm();
                                        Messenger2.renderIcqAuthScreen();
                                    } else {
                                        StaticData.g = 1;
                                    }

                                    ScreenCanvas.screenMode = 2;
                                    Messenger2.b = true;
                                } else {
                                    ScreenCanvas.textDialog = "Сначала необходимо подключиться к Yapp";
                                    ScreenCanvas.boolean2 = true;
                                }
                            }

                            if (t == __bookValue) {
                                BookUtil.a = true;
                                ScreenCanvas.screenMode = 3;
                                ScreenCanvas.boolean2 = true;
                            }

                            if (t == 0) {
                                bool4 = !bool4;
                            }

                            bool5 = false;
                            prepareRender = true;
                    }
            }
        }

    }

    private static void e() {
        int var0;
        if ((var0 = b()) < q) {
            if (p + var0 >= q - var0) {
                p = q - var0;
            } else {
                p += var0;
            }
        }

        if (p > 0) {
            bool3 = false;
            bool2 = false;
            var0 = b();
        }

        if (var0 < q) {
            if (p + var0 >= q - var0) {
                p = q - var0;
            }
        } else {
            p = 0;
        }

        prepareRender = true;
    }

    private static void f() {
        int var0 = b();
        if (p >= var0) {
            p -= var0;
        } else {
            p = 0;
            bool3 = true;
        }

        if (p == 0) {
            bool3 = true;
        }

        prepareRender = true;
    }

    private static void g() {
        int var0 = b();
        boolean var1 = false;
        if (!bool2) {
            boolean var2;
            int var3;
            if (var2 = k()) {
                var3 = m();
            } else {
                var3 = c(p, var0);
            }

            if (var3 != -1 && uiElementArr[var3].l > p) {
                o = var3;
            } else if (p > 0) {
                p -= 40;
                if (p <= 0) {
                    p = 0;
                    if (d > 0) {
                        bool3 = true;
                    }
                }

                if (!j() && bool3 && d > 0) {
                    bool2 = true;
                    o = -1;
                }
            } else {
                if (d > 0) {
                    bool3 = true;
                }

                if (bool3 && d > 0 && (var2 || o == -1)) {
                    o = -1;
                    bool2 = true;
                }
            }

            prepareRender = true;
        }
    }

    private static void h() {
        if (bool2) {
            bool2 = false;
        }

        int var0 = b();
        boolean var1 = false;
        int var2;
        if (k()) {
            var2 = l();
        } else {
            var2 = b(p, var0);
        }

        if (var2 != -1 && uiElementArr[var2].l < p + var0) {
            o = var2;
            if (uiElementArr[var2].l + uiElementArr[var2].imgH > p + var0) {
                if (bool3 && d > 0) {
                    bool2 = false;
                    bool3 = false;
                }

                var0 = b();
                if (uiElementArr[var2].l + uiElementArr[var2].imgH > p + var0) {
                    p = uiElementArr[var2].l + uiElementArr[var2].imgH - var0;
                }
            }
        } else {
            if (bool3 && d > 0 && var0 < q) {
                bool2 = false;
                bool3 = false;
            } else {
                if (var0 < q) {
                    p += 40;
                }

                if (p + var0 > q && var0 < q) {
                    p = q - var0;
                }
            }

            i();
        }

        prepareRender = true;
    }

    private static void i() {
        int var0 = b();
        int var1;
        if ((var1 = l()) != -1 && (o == -1 || o != -1 && uiElementArr[var1].l + uiElementArr[var1].imgH <= p + var0)) {
            o = var1;
        }

    }

    private static boolean j() {
        int var0;
        if ((var0 = m()) == -1 || o != -1 && (o == -1 || uiElementArr[var0].l < p)) {
            return false;
        } else {
            o = var0;
            return true;
        }
    }

    private static boolean k() {
        return o > -1 && uiElementArr[o].l + uiElementArr[o].imgH > p && uiElementArr[o].l < p + b();
    }

    private static int l() {
        if (uiElementArr != null) {
            boolean var0 = false;
            int var1 = o + 1;

            while (true) {
                while (var1 < uiElementArr.length && !var0) {
                    if (uiElementArr[var1] != null && uiElementArr[var1].boolean1) {
                        var0 = true;
                    } else {
                        ++var1;
                    }
                }

                if (var0) {
                    return var1;
                }
                break;
            }
        }

        return -1;
    }

    private static int b(int var0, int var1) {
        if (uiElementArr != null) {
            boolean var2 = false;
            int var3 = 0;

            while (true) {
                while (var3 < uiElementArr.length && !var2) {
                    if (uiElementArr[var3].l + uiElementArr[var3].imgH > var0 && uiElementArr[var3].l < var0 + var1 && uiElementArr[var3].boolean1) {
                        var2 = true;
                    } else {
                        ++var3;
                    }
                }

                if (var2) {
                    return var3;
                }
                break;
            }
        }

        return -1;
    }

    private static int m() {
        if (uiElementArr != null) {
            boolean var0 = false;
            int var1 = o - 1;

            while (true) {
                while (var1 >= 0 && !var0) {
                    if (uiElementArr[var1] != null && uiElementArr[var1].boolean1) {
                        var0 = true;
                    } else {
                        --var1;
                    }
                }

                if (var0) {
                    return var1;
                }
                break;
            }
        }

        return -1;
    }

    private static int c(int var0, int var1) {
        if (uiElementArr != null) {
            boolean var2 = false;
            int var3 = uiElementArr.length - 1;

            while (true) {
                while (var3 >= 0 && !var2) {
                    if (uiElementArr[var3].l + uiElementArr[var3].imgH > var0 && uiElementArr[var3].l < var0 + var1 && uiElementArr[var3].boolean1) {
                        var2 = true;
                    } else {
                        --var3;
                    }
                }

                if (var2) {
                    return var3;
                }
                break;
            }
        }

        return -1;
    }

    // TODO: !!!!!!!
    private static void a(ViewElement viewElement) {
        a = "";
        CheckBox var1 = null;
        if (viewElement instanceof EditText) {
            new TextBoxForEditText((EditText) viewElement);
            var1 = null;
        } else if (viewElement instanceof SelectableFormView) {
            new FormForSelectableView((SelectableFormView) viewElement);
            var1 = null;
        } else if (viewElement instanceof CheckBox) {
            (var1 = (CheckBox) viewElement).isChecked = !var1.isChecked;
            prepareRender = true;
        } else {
            if (viewElement.boolean1) {
                if (viewElement.biteArr1 != null && viewElement.biteArr1.length > 0) {
                    try {
                        Core3.parsingPostUiAction(viewElement.biteArr1);
                        return;
                    } catch (Exception e) {
                        e.printStackTrace();
                        return;
                    }
                }

                if (viewElement.byteArr2 != null) {
                    SendPackets.b(viewElement.byteArr2);
                    bool1 = true;
                    prepareRender = true;
                }
            }

        }
    }

    private static void n() {
        if (d > 0 && bool3 && bool2) {
            BytesContentFactory var0;
            (var0 = new BytesContentFactory()).setType((short) 902);
            var0.addInt(Core1.b());
            SendPackets.addByteArrData(var0.bytesArray());
        }

    }

    public static void c() {
        try {
            StaticData.connectID = Integer.parseInt(uiElementArr[1].text);
        } catch (Exception e) {
            StaticData.connectID = 0;
        }

        if (uiElementArr[3].text != null) {
            StaticData.a = uiElementArr[3].text;
        } else {
            StaticData.a = "";
        }

        if (((CheckBox) getUiElements()[4]).isChecked) {
            BdUtil.save("yapp-login", Integer.toString(StaticData.connectID).getBytes());
            BdUtil.save("yapp-password", StaticData.a.getBytes());
        } else {
            BdUtil.save("yapp-login", "".getBytes());
            BdUtil.save("yapp-password", "".getBytes());
        }

        BytesContentFactory bcf = new BytesContentFactory();
        bcf.setType((short) 99);
        bcf.addByte((byte) 1);
        bcf.addUtfString("1.7"); // версия приложения
        SendPackets.addByteArrData(bcf.bytesArray());
        if (!NetworkUtil.isRunning) {
            ReceivePackets.a = true;
        }

        a = "";
        bool1 = true;
        prepareRender = true;
    }

    public static void d() {
        boolean isSaveIcqAccount = ((CheckBox) uiElementArr[4]).isChecked;
        String icqLogin = "";
        if (uiElementArr[1].text != null) {
            icqLogin = uiElementArr[1].text;
        }

        if (isSaveIcqAccount) {
            BdUtil.save("icq-login", icqLogin.getBytes());
        } else {
            BdUtil.save("icq-login", "".getBytes());
        }

        String icqPassword = "";
        if (uiElementArr[3].text != null) {
            icqPassword = uiElementArr[3].text;
        }

        if (isSaveIcqAccount) {
            BdUtil.save("icq-password", icqPassword.getBytes());
        } else {
            BdUtil.save("icq-password", "".getBytes());
        }

        Messenger.a((byte) 1, icqLogin, icqPassword);
        StaticData.icqLogin = icqLogin;
        bool1 = true;
        prepareRender = true;
        ScreenCanvas.boolean2 = true;
    }
}
