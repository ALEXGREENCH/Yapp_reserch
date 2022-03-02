package ru.yapp.mobile.book;

import ru.yapp.mobile.ScreenCanvas;
import ru.yapp.mobile.Yapp;
import ru.yapp.mobile.browser.BrowserForm;
import ru.yapp.mobile.browser.BrowserRender;
import ru.yapp.mobile.core.*;
import ru.yapp.mobile.messenger.Messenger;
import ru.yapp.mobile.messenger.Messenger2;
import ru.yapp.mobile.net.SendPackets;

import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.util.Vector;

public final class BookUtil {

    private static final String[] strArrBookScreenMenu = new String[]{
            "Выбор части",
            "Общайся",
            "Назад"
    };
    private static final Vector bookPages = new Vector();
    public static String downloadBookInfo = "";
    public static boolean a = true;
    private static byte[] bytesBookData = null;
    private static int intPartCount___ = 0;
    private static int intPartNum___ = 0;
    private static int strBookId___ = 0;
    private static int intPartNum = 0;
    private static int intPartCount = 0;
    private static int strBookId = 0;
    private static int intPartNum__ = 1;
    private static int intPartPos = 0;
    private static String strBookAuthor___ = "";
    private static String strBookName___ = "";
    private static String strBookName = "";
    private static String strBookAuthor = "";
    private static Image bookBackgroundImage = null;
    private static long responseID = 0L;


    private static boolean d = false;
    private static boolean f = false;
    private static int int1 = 0;
    private static int n = 0;
    private static boolean q = false;
    private static int r = 0;
    private static int s = 0;
    private static int u = 0;
    private static int v = 0;


    public static void init() throws Exception {
        createBookBackground();
        if (BdUtil.read("book-id") != null) {
            if (BdUtil.read("book-author") != null) {
                strBookAuthor = new String(BdUtil.read("book-author"));
            }

            if (BdUtil.read("book-name") != null) {
                strBookName = new String(BdUtil.read("book-name"));
            }

            intPartCount = Integer.parseInt(new String(BdUtil.read("part-count")));
            bytesBookData = BdUtil.read("book-data");
            strBookId = Integer.parseInt(new String(BdUtil.read("book-id")));
            intPartNum = Integer.parseInt(new String(BdUtil.read("part-num")));
            intPartPos = Integer.parseInt(new String(BdUtil.read("part-pos")));
            intPartNum__ = intPartNum;
            byte[] bytesPosHistory = BdUtil.read("pos-history");
            bookPages.removeAllElements();
            if (bytesPosHistory != null) {
                for (int i = 0; i < bytesPosHistory.length / 2; ++i) {
                    bookPages.addElement(new Integer(bytesPosHistory[i * 2] * 128 + bytesPosHistory[i * 2 + 1]));
                }
            }
        }

        strBookId___ = strBookId;
        strBookAuthor___ = strBookAuthor;
        strBookName___ = strBookName;
        intPartCount___ = intPartCount;
        intPartNum___ = intPartNum;
        if (bytesBookData == null || intPartPos > bytesBookData.length) {
            intPartPos = 0;
            bookPages.removeAllElements();
        }
    }

    // TODO: ........
    private static void createBookBackground() {
        int allFontImagesHeightX2 = 2 * UiUtil.allFontImagesHeight[0];
        int allFontImagesHeightX14 = 7 * allFontImagesHeightX2;

        int[] rgb = new int[allFontImagesHeightX14 * allFontImagesHeightX2];

        for (int i = 0; i < allFontImagesHeightX14; ++i) {
            for (int j = 0; j < allFontImagesHeightX2; ++j) {
                if (i >= allFontImagesHeightX14 / 2 - allFontImagesHeightX2 / 2 &&
                        i <= allFontImagesHeightX14 / 2 + allFontImagesHeightX2 / 2) {
                    rgb[j * allFontImagesHeightX14 + i] = 0;
                } else {
                    rgb[j * allFontImagesHeightX14 + i] = -570425345;
                }
            }
        }

        bookBackgroundImage = Image.createRGBImage(rgb, allFontImagesHeightX14, allFontImagesHeightX2, true);
    }

    public static void saveBookInBD() {
        BdUtil.save("part-pos", Integer.toString(intPartPos).getBytes());
        if (bookPages.size() > 0) {
            byte[] var0 = new byte[bookPages.size() * 2];

            for (int i = 0; i < bookPages.size(); ++i) {
                var0[i * 2] = (byte) (((Integer) bookPages.elementAt(i)).intValue() / 128);
                var0[i * 2 + 1] = (byte) (((Integer) bookPages.elementAt(i)).intValue() % 128);
            }

            BdUtil.save("pos-history", var0);
        }

    }

    private static int a(Graphics g, int var1, int var2, int var3, int var4, int var5) {
        int var6 = 0;
        int var7 = 0;
        int __intPartPos = intPartPos;
        //boolean var9 = false;
        int var10 = UiUtil.allFontImagesHeight[var5];
        if (bytesBookData != null) {
            while (__intPartPos < bytesBookData.length && var7 + var10 <= var4) {
                // TODO: ??????????????????????
                int i;
                for (i = __intPartPos + 1; i < bytesBookData.length && bytesBookData[i] != 32 && bytesBookData[i] != 10; ++i) {
                    ;
                }

                if (i != __intPartPos) {
                    byte[] var11;
                    if (i >= bytesBookData.length) {
                        var11 = new byte[bytesBookData.length - __intPartPos - 1];
                        System.arraycopy(bytesBookData, __intPartPos, var11, 0, bytesBookData.length - __intPartPos - 1);
                    } else {
                        var11 = new byte[i - __intPartPos];
                        System.arraycopy(bytesBookData, __intPartPos, var11, 0, i - __intPartPos);
                    }

                    int var12 = StringUtils.a(var11, var5);
                    if (var12 > var3) {
                        if (var6 > 0) {
                            var6 = 0;
                            var7 = var7 + var10 + 2;
                        }
                    } else if (var6 + var12 > var3) {
                        var6 = 0;
                        var7 = var7 + var10 + 2;
                    }

                    if (var7 + var10 <= var4) {
                        StringUtils.a(g, var1 + var6, var2 + var7, var11, var5);
                        var6 += var12 + StringUtils.a;
                        __intPartPos = i + 1;
                    }

                    if (__intPartPos < bytesBookData.length && bytesBookData[__intPartPos] == 10) {
                        var6 = 0;
                        var7 = var7 + var10 + 2;
                        ++__intPartPos;
                    }
                }
            }
        }

        return __intPartPos;
    }

    private static int removeLastPageBook() {
        int BookPagesSize = bookPages.size();
        if (BookPagesSize > 0) {
            int lastPage = ((Integer) bookPages.elementAt(BookPagesSize - 1)).intValue();
            bookPages.removeElementAt(BookPagesSize - 1);
            return lastPage;
        } else {
            return 0;
        }
    }

    public static void setGraphics(Graphics g) {
        ++s;
        if (a) {
            a = false;
            g.setColor(255, 255, 255);
            g.fillRect(0, 0, StaticData.screenWidth, StaticData.screenHeight);
            if (f) {
                createBookBackground(g);
                a = true;
            } else {
                a = false;
            }

            g.drawImage(UiUtil.img3, StaticData.screenWidth - UiUtil.img3.getWidth(), 17, 20);
            String var1;
            if (!f && !d) {
                if (bytesBookData == null) {
                    var1 = "Нет загруженных книг";
                    StringUtils.a(g, StaticData.screenWidth / 2 - StringUtils.a((String) var1, 0) / 2, (StaticData.screenHeight - bookBackgroundImage.getHeight()) / 2 - bookBackgroundImage.getHeight(), (String) var1, 0);
                } else {
                    n = a(g, 5, 23, StaticData.screenWidth - UiUtil.img3.getWidth() - 10, StaticData.screenHeight - 34 - 10, int1);
                    int var7 = n - intPartPos;
                    if (bytesBookData != null && var7 < bytesBookData.length && bytesBookData.length > 0) {
                        int var2;
                        int var3 = (var2 = StaticData.screenHeight - 34) * var7 / bytesBookData.length;
                        int var4 = intPartPos * var2 / bytesBookData.length + 17;
                        int var5 = UiUtil.img4.getWidth();
                        int var6 = StaticData.screenWidth - var5;
                        g.drawRegion(UiUtil.img4, 0, 0, var5, var3, 0, var6, var4, 20);
                    }
                }
            }

            StringUtils.a(var1 = "Книга: " + strBookName + " / " + strBookAuthor + " ", int1);
            boolean var8 = false;
            boolean var9 = false;
            g.drawImage(UiUtil.formatedImgToolbar, 0, 0, 20);
            g.drawImage(UiUtil.imgFormatedMenuBack, 0, StaticData.screenHeight - 17, 20);
            StringUtils.a(g, 5, (17 - UiUtil.allFontImagesHeight[int1]) / 2, (String) var1, 4);
            b(g);
        }

    }

    public static void a(int var0, int var1) {
        if (q) {
            if (var0 == StaticData.leftSoftKey) {
                q = false;
                a = true;
            } else if (var0 != 50 && var1 != 1) {
                if (var0 != 56 && var1 != 6) {
                    if (var1 == 8 || var0 == StaticData.rightSoftKey || var0 == 53) {
                        q = false;
                        switch (r) {
                            case 0:
                                if (!f && strBookId != 0) {
                                    f = true;
                                    a = true;
                                }
                                break;
                            case 1:
                                if (StaticData.connectID > 0) {
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
                                break;
                            case 2:
                                BrowserForm.prepareRender = true;
                                ScreenCanvas.screenMode = 1;
                                ScreenCanvas.boolean2 = true;
                        }
                    }
                } else {
                    ++r;
                    if (r > strArrBookScreenMenu.length - 1) {
                        r = 0;
                    }
                }
            } else {
                --r;
                if (r < 0) {
                    r = strArrBookScreenMenu.length - 1;
                }
            }
        } else if (var0 == StaticData.rightSoftKey) {
            q = true;
            r = 0;
            a = true;
        } else if (var0 == StaticData.leftSoftKey) {
            if (f) {
                f = false;
                strBookId___ = strBookId;
                strBookAuthor___ = strBookAuthor;
                strBookName___ = strBookName;
                intPartCount___ = intPartCount;
                intPartNum___ = intPartNum;
                BrowserForm.prepareRender = true;
                ScreenCanvas.screenMode = 1;
                ScreenCanvas.boolean2 = true;
                a = true;
            } else {
                BrowserForm.prepareRender = true;
                q = false;
                ScreenCanvas.screenMode = 1;
                ScreenCanvas.boolean2 = true;
            }
        } else {
            int var2;
            if (var1 != 1 && var0 != 50) {
                if (var1 != 6 && var0 != 56) {
                    if (var1 != 2 && var0 != 52) {
                        if (var1 != 5 && var0 != 54) {
                            if (var1 == 8 || var0 == 53) {
                                if (f) {
                                    Yapp.Log("PRESSED LOAD PART, BOOK_ID->" + strBookId + " NEW BOOK ID->" + strBookId___);
                                    if (strBookId___ != 0 && (strBookId != strBookId___ || intPartNum != intPartNum__)) {
                                        if (StaticData.connectID > 0) {
                                            intPartNum___ = intPartNum__;
                                            BytesContentFactory bcf = new BytesContentFactory();
                                            bcf.setType((short) 704);
                                            bcf.addInt(strBookId___);
                                            bcf.addInt(intPartNum__);
                                            bcf.addLong(responseID);
                                            SendPackets.addByteArrData(bcf.bytesArray());
                                            Yapp.Log("SEND REQUEST");
                                            d = true;
                                        } else {
                                            ScreenCanvas.screenMode = 1;
                                            ScreenCanvas.textDialog = "Сначала необходимо подключиться к Yapp";
                                            ScreenCanvas.boolean2 = true;
                                        }
                                    }

                                    f = false;
                                    a = true;
                                } else if (strBookId___ != 0) {
                                    f = true;
                                    a = true;
                                }
                            }
                        } else if (f) {
                            v = -2;
                        } else {
                            if (n < bytesBookData.length) {
                                bookPages.addElement(new Integer(intPartPos));
                                intPartPos = n;
                            }

                            a = true;
                        }
                    } else if (f) {
                        v = 2;
                    } else {
                        if ((var2 = removeLastPageBook()) >= 0) {
                            n = intPartPos;
                            intPartPos = var2;
                        }

                        a = true;
                    }
                } else {
                    if (n < bytesBookData.length) {
                        bookPages.addElement(new Integer(intPartPos));
                        intPartPos = n;
                    }

                    a = true;
                }
            } else {
                if ((var2 = removeLastPageBook()) >= 0) {
                    n = intPartPos;
                    intPartPos = var2;
                }

                a = true;
            }
        }

        a = true;
    }

    private static void b(Graphics g) {
        if (q) {
            StringUtils.a(g, StringUtils.a, StaticData.screenHeight - 17 + (17 - UiUtil.allFontImagesHeight[9]) / 2, (String) "Выбрать", 9);
        } else {
            StringUtils.a(g, StringUtils.a, StaticData.screenHeight - 17 + (17 - UiUtil.allFontImagesHeight[9]) / 2, (String) "Меню", 9);
        }

        int var1;
        if (!q && !f) {
            var1 = StringUtils.a((String) "Назад", 9);
            StringUtils.a(g, StaticData.screenWidth - var1 - StringUtils.a, StaticData.screenHeight - 17 + (17 - UiUtil.allFontImagesHeight[9]) / 2, (String) "Назад", 9);
        } else {
            var1 = StringUtils.a((String) "Отмена", 9);
            StringUtils.a(g, StaticData.screenWidth - var1 - StringUtils.a, StaticData.screenHeight - 17 + (17 - UiUtil.allFontImagesHeight[9]) / 2, (String) "Отмена", 9);
        }

        String var3 = downloadBookInfo;
        if (downloadBookInfo.equals("")) {
            if (d) {
                var3 = "Загрузка";
            } else {
                var3 = ScreenCanvas.str1;
            }
        }

        int var2 = StringUtils.a((String) var3, 9);
        StringUtils.a(g, (StaticData.screenWidth - var2) / 2, StaticData.screenHeight - 17 + (17 - UiUtil.allFontImagesHeight[9]) / 2, (String) var3, 9);
        if (q) {
            BrowserForm.a(g, strArrBookScreenMenu, r);
        }

    }

    public static void bookLoaded(byte[] biteArr) throws Exception {
        Yapp.Log("BOOK LOADED");
        f = false;
        q = false;
        strBookId = strBookId___;
        intPartNum = intPartNum___;
        intPartCount = intPartCount___;
        strBookAuthor = strBookAuthor___;
        strBookName = strBookName___;
        intPartPos = 0;
        bytesBookData = biteArr;
        BdUtil.save("book-data", bytesBookData);
        BdUtil.save("book-id", Integer.toString(strBookId).getBytes());
        BdUtil.save("part-num", Integer.toString(intPartNum).getBytes());
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        DataOutputStream dos = new DataOutputStream(baos);
        dos.writeUTF(strBookAuthor);
        dos.flush();
        baos.flush();
        BdUtil.save("book-author", baos.toByteArray());
        baos = new ByteArrayOutputStream();
        dos = new DataOutputStream(baos);
        dos.writeUTF(strBookName);
        dos.flush();
        baos.flush();
        BdUtil.save("book-name", baos.toByteArray());
        BdUtil.save("part-count", Integer.toString(intPartCount).getBytes());
        BdUtil.save("part-pos", Integer.toString(intPartPos).getBytes());
        bookPages.removeAllElements();
        d = false;
        a = true;
    }

    private static void createBookBackground(Graphics g) {
        if (intPartCount___ >= 1) {
            int var1 = (StaticData.screenHeight - bookBackgroundImage.getHeight()) / 2;
            int var2 = StaticData.screenWidth / 2 - bookBackgroundImage.getWidth() / 2;
            int var3 = g.getClipX();
            int var4 = g.getClipY();
            int var5 = g.getClipWidth();
            int var6 = g.getClipHeight();
            g.setColor(200, 200, 200);
            g.fillRect(var2, var1, bookBackgroundImage.getWidth(), bookBackgroundImage.getHeight());
            String var7 = "Выберите часть книги";
            StringUtils.a(g, StaticData.screenWidth / 2 - StringUtils.a((String) var7, 0) / 2, var1 - 3 * bookBackgroundImage.getHeight(), (String) var7, 0);
            StringUtils.a(g, StaticData.screenWidth / 2 - StringUtils.a((String) strBookAuthor___, 0) / 2, var1 - 2 * bookBackgroundImage.getHeight(), (String) strBookAuthor___, 0);
            StringUtils.a(g, StaticData.screenWidth / 2 - StringUtils.a((String) strBookName___, 0) / 2, var1 - bookBackgroundImage.getHeight(), (String) strBookName___, 0);
            g.setClip(var2, var1, bookBackgroundImage.getWidth(), bookBackgroundImage.getHeight());
            if (u >= bookBackgroundImage.getHeight()) {
                u = 0;
                --intPartNum__;
                if (intPartNum__ < 1) {
                    intPartNum__ = intPartCount___;
                }

                v = 0;
            } else if (u <= -bookBackgroundImage.getHeight()) {
                u = 0;
                ++intPartNum__;
                if (intPartNum__ > intPartCount___) {
                    intPartNum__ = 1;
                }

                v = 0;
            }

            u += v;
            int var8 = bookBackgroundImage.getHeight();
            int var9 = StaticData.screenWidth / 2 - var8 / 2 + u;
            int var10 = var1 + UiUtil.allFontImagesHeight[3] / 2;
            b(g, intPartNum__, var9, var1, var10, var8);
            int var11 = intPartNum__;
            int var12 = intPartNum__;

            for (int i = 0; i < 3; ++i) {
                --var11;
                ++var12;
                if (var11 < 1) {
                    var11 = intPartCount___;
                }

                if (var12 > intPartCount___) {
                    var12 = 1;
                }

                int var14 = (i + 1) * var8;
                b(g, var11, var9 - var14, var1, var10, var8);
                b(g, var12, var9 + var14, var1, var10, var8);
            }

            g.drawImage(bookBackgroundImage, var2, var1, 20);
            g.setClip(var3, var4, var5, var6);
        }

    }

    private static void b(Graphics g, int var1, int var2, int var3, int var4, int var5) {
        if (var1 == intPartNum___) {
            g.setColor(255, 0, 0);
            g.fillRect(var2, var3, var5, var5);
        }

        String var6 = Integer.toString(var1);
        StringUtils.a(g, var2 + (var5 - StringUtils.a((String) var6, 0)) / 2, var4, (String) var6, 0);
    }

    public static void b(byte[] bitesArr) throws Exception {
        ByteArrayInputStream bais = new ByteArrayInputStream(bitesArr);
        DataInputStream bis = new DataInputStream(bais);
        strBookId___ = bis.readInt();
        Yapp.Log("BOOK_ID " + strBookId___);
        responseID = bis.readLong();
        intPartCount___ = bis.readInt();
        Yapp.Log("PART_COUNT " + intPartCount___);
        strBookAuthor___ = bis.readUTF();
        strBookName___ = bis.readUTF();
        intPartNum___ = 0;
        intPartNum__ = 1;
        if (strBookId == strBookId___) {
            intPartNum___ = intPartNum;
            intPartNum__ = intPartNum;
        }

        f = true;
        BrowserForm.bool1 = false;
        ScreenCanvas.screenMode = 3;
        ScreenCanvas.boolean2 = true;
    }
}
