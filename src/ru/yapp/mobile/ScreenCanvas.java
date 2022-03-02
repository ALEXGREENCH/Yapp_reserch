package ru.yapp.mobile;

import java.util.Calendar;
import java.util.Date;
import javax.microedition.lcdui.Canvas;
import javax.microedition.lcdui.Graphics;

import ru.yapp.mobile.book.BookUtil;
import ru.yapp.mobile.browser.BrowserForm;
import ru.yapp.mobile.core.UiUtil;
import ru.yapp.mobile.core.StaticData;
import ru.yapp.mobile.core.BytesContentFactory;
import ru.yapp.mobile.core.StringUtils;
import ru.yapp.mobile.messenger.Messenger;
import ru.yapp.mobile.messenger.Messenger2;
import ru.yapp.mobile.net.NetworkUtil;
import ru.yapp.mobile.net.SendPackets;

public final class ScreenCanvas extends Canvas implements Runnable {

    public static String textDialog = "";
    public static ScreenCanvas screenCanvas = null;
    public static boolean boolean2 = false;
    public static int screenMode = 0;

    public static String str1 = "";

    private static boolean boolean1 = true;
    private static boolean renderState;

    private long timeTrack1 = 0L;
    private long timeTrack2 = 0L;
    private Calendar calendar;

    public ScreenCanvas() {
        screenCanvas = this;
        boolean1 = true;
        renderState = true;
        screenMode = 0;
        this.setFullScreenMode(true);
        (new Thread(this)).start();
    }

    public static void destroy() {
        renderState = false;
    }

    protected final void paint(Graphics g) {
        int height;
        switch (screenMode) {
            case 0: // Сплэш
                int width = this.getWidth();
                height = this.getHeight();
                g.setColor(0xFFFFFF);
                g.fillRect(0, 0, width, height);
                g.drawImage(UiUtil.imgSplash, width / 2, height / 2, 3);
                break;
            case 1:
                BrowserForm.setGraphics(g);
                break;
            case 2:
                Messenger2.setGraphics(g);
                break;
            case 3:
                BookUtil.setGraphics(g);
        }

        if (boolean2 && screenMode != 0) {
            switch (screenMode) {
                case 1:
                    BrowserForm.setGraphics2(g);
                default:
                    if (textDialog.length() > 0) {
                        //boolean var7 = false; //todo: ?

                        // отрисовка диалога
                        height = StaticData.screenHeight / 2;
                        int var4 = (StaticData.screenWidth - 120) / 2;
                        int var5 = (StaticData.screenHeight - height) / 2;
                        int var6 = UiUtil.allFontImagesHeight[4];
                        g.setColor(140, 140, 140); // серый
                        g.fillRect(var4 + 3, var5 + 3, 120, height);
                        g.setColor(255, 255, 225); // светло-серый
                        g.fillRect(var4, var5, 120, height);
                        g.setColor(0); // чёрный
                        g.drawRect(var4, var5, 120, height);
                        g.fillRect(var4, var5, 120, var6);
                        g.setColor(200, 200, 200); // светло-серый
                        g.fillRect(var4 + 1, var5 + 1, 119, var6 / 2);
                        StringUtils.a(g, var4 + StringUtils.a, var5 + UiUtil.allFontImagesHeight[4] + 2, 120 - StringUtils.a * 2, height - var6 - 2, textDialog, 0);
                    }
                    boolean2 = false;
            }
        }
    }

    public final void run() {
        while (renderState) {
            this.repaint();
            this.renderTime__();
            try {
                Thread.sleep(30L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void renderTime__() {
        long currentTime = System.currentTimeMillis();
        if (boolean1) {
            StaticData.screenHeight = 382;
            StaticData.screenWidth = 416;
            this.detectionHardwareButton();
            this.calendar = Calendar.getInstance();
            boolean1 = false;
        }
        if (currentTime - this.timeTrack1 >= 50000L && NetworkUtil.isRunning) {
            BytesContentFactory bitesContentFactory = new BytesContentFactory();
            bitesContentFactory.setType((short) 100);
            SendPackets.addByteArrData(bitesContentFactory.bytesArray());
            this.timeTrack1 = currentTime;
        }
        if (currentTime - this.timeTrack2 >= 1000L) {
            this.timeTrack2 = currentTime;
            BrowserForm.prepareRender = true;
            BookUtil.a = true;
            Messenger2.b = true;
            boolean2 = true;
            this.calendar.setTime(new Date(currentTime));
            int var6 = this.calendar.get(11);
            int var4 = this.calendar.get(12);
            if (var6 < 10) {
                str1 = "0" + var6;
            } else {
                str1 = "" + var6;
            }
            if (var4 < 10) {
                str1 = str1 + ":0" + var4;
            } else {
                str1 = str1 + ":" + var4;
            }
            for (int i = 0; i < Messenger.cmdListener2Arr.length - 1; ++i) {
                if (Messenger.cmdListener2Arr[i] != null && Messenger.cmdListener2Arr[i].w != null && Messenger.cmdListener2Arr[i].w.length > 1) {
                    --Messenger.cmdListener2Arr[i].x;
                    if (Messenger.cmdListener2Arr[i].x < 0) {
                        Messenger.e(Messenger.cmdListener2Arr[i].w);
                        Messenger.cmdListener2Arr[i].w = null;
                    }
                }
            }
        }
    }

    protected final void keyPressed(int var1) {
        if (textDialog.length() > 0) {
            textDialog = "";
            boolean2 = true;
            BrowserForm.prepareRender = true;
            Messenger2.b = true;
            BookUtil.a = true;
        } else {
            int var2 = this.getGameAction(var1);
            switch (screenMode) {
                case 1:
                    BrowserForm.a(var1, var2);
                    return;
                case 2:
                    Messenger2.a(var1, var2);
                    return;
                case 3:
                    BookUtil.a(var1, var2);
                default:
            }
        }
    }

    private void detectionHardwareButton() {
        int sumKeyCodes = 0;

        for (int btnIndex = -205; btnIndex < 205; ++btnIndex) {
            try {
                String nameBtnKey = this.getKeyName(btnIndex).toUpperCase();
                if (nameBtnKey.indexOf("SOFT") >= 0 || nameBtnKey.indexOf("СОФТ") >= 0) {

                    if (this.getKeyName(btnIndex).indexOf("1") < 0
                            && this.getKeyName(btnIndex).toUpperCase().indexOf("ЛЕВАЯ") < 0
                            && this.getKeyName(btnIndex).toUpperCase().indexOf("LEFT") < 0) {

                        if (this.getKeyName(btnIndex).indexOf("2") < 0
                                && this.getKeyName(btnIndex).toUpperCase().indexOf("ПРАВАЯ") < 0
                                && this.getKeyName(btnIndex).toUpperCase().indexOf("RIGHT") < 0) {

                            if (this.getKeyName(btnIndex).indexOf("3") >= 0) {
                                sumKeyCodes += btnIndex;
                            } else if (this.getKeyName(btnIndex).indexOf("4") >= 0) {
                                sumKeyCodes += btnIndex;
                            }
                        } else {
                            StaticData.leftSoftKey = btnIndex; // КОДЫ СОФТ КЛАВИШ
                            sumKeyCodes += btnIndex;
                        }
                    } else {
                        StaticData.rightSoftKey = btnIndex; // КОДЫ СОФТ КЛАВИШ
                        sumKeyCodes += btnIndex;
                    }
                }
            } catch (Exception e) {
            }
        }
        // КОДЫ СОФТ КЛАВИШ
        if (sumKeyCodes == -10) {
            StaticData.leftSoftKey = -4;
            StaticData.rightSoftKey = -1;
        }

        // Если ничего не найдено
        if (StaticData.rightSoftKey == 0) {
            StaticData.rightSoftKey = -6;
        }
        if (StaticData.leftSoftKey == 0) {
            StaticData.leftSoftKey = -7;
        }
    }
}
