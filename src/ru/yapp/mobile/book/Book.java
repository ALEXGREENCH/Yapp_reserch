package ru.yapp.mobile.book;

import ru.yapp.mobile.ScreenCanvas;
import ru.yapp.mobile.Yapp;
import ru.yapp.mobile.browser.BrowserForm;
import ru.yapp.mobile.browser.BrowserRender;
import ru.yapp.mobile.core.*;
import ru.yapp.mobile.messenger.Messenger1;
import ru.yapp.mobile.messenger.Messenger2;
import ru.yapp.mobile.net.SendPackets;

import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.util.Vector;

public final class Book {
    
    public static boolean a = true;
    public static String b = "";
    
   private static String[] strArrBookScreenMenu = new String[]{"Выбор части", "Общайся", "Назад"};
   private static boolean d = false;
   private static Image e = null;
   private static boolean f = false;
   private static int g = 0;
   private static String strBookName = "";
   private static String strBookAuthor = "";
   private static int intPartNum = 0;
   private static int intPartCount = 0;
   private static int strBookId = 0;
   private static byte[] bytesBookData = null;
   private static int n = 0;
   private static int intPartPos = 0;
   private static Vector p = new Vector();
   private static boolean q = false;
   private static int r = 0;
   private static int s = 0;
   private static int intPartNum__ = 1;
   private static int u = 0;
   private static int v = 0;
   private static int strBookId___ = 0;
   private static long x = 0L;
   private static int intPartCount___ = 0;
   private static int intPartNum___ = 0;
   private static String strBookAuthor___ = "";
   private static String strBookName___ = "";

   public static void init() throws Exception {
      c();
      if (DB.read("book-id") != null) {
         if (DB.read("book-author") != null) {
            strBookAuthor = new String(DB.read("book-author"));
         }

         if (DB.read("book-name") != null) {
            strBookName = new String(DB.read("book-name"));
         }

         intPartCount = Integer.parseInt(new String(DB.read("part-count")));
         bytesBookData = DB.read("book-data");
         strBookId = Integer.parseInt(new String(DB.read("book-id")));
         intPartNum = Integer.parseInt(new String(DB.read("part-num")));
         intPartPos = Integer.parseInt(new String(DB.read("part-pos")));
         intPartNum__ = intPartNum;
         byte[] bytesPosHistory = DB.read("pos-history");
         p.removeAllElements();
         if (bytesPosHistory != null) {
            for(int var1 = 0; var1 < bytesPosHistory.length / 2; ++var1) {
               p.addElement(new Integer(bytesPosHistory[var1 * 2] * 128 + bytesPosHistory[var1 * 2 + 1]));
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
         p.removeAllElements();
      }

   }

   private static void c() {
      int var0 = 2 * ResUI.allFontImagesHeight[0];
      int var1;
      int[] var2 = new int[(var1 = 7 * var0) * var0];

      for(int var3 = 0; var3 < var1; ++var3) {
         for(int var4 = 0; var4 < var0; ++var4) {
            if (var3 >= var1 / 2 - var0 / 2 && var3 <= var1 / 2 + var0 / 2) {
               var2[var4 * var1 + var3] = 0;
            } else {
               var2[var4 * var1 + var3] = -570425345;
            }
         }
      }

      e = Image.createRGBImage(var2, var1, var0, true);
   }

   public static void saveBookInBD() {
      DB.save("part-pos", Integer.toString(intPartPos).getBytes());
      if (p.size() > 0) {
         byte[] var0 = new byte[p.size() * 2];

         for(int var1 = 0; var1 < p.size(); ++var1) {
            var0[var1 * 2] = (byte)(((Integer)p.elementAt(var1)).intValue() / 128);
            var0[var1 * 2 + 1] = (byte)(((Integer)p.elementAt(var1)).intValue() % 128);
         }

         DB.save("pos-history", var0);
      }

   }

   private static int a(Graphics var0, int var1, int var2, int var3, int var4, int var5) {
      int var6 = 0;
      int var7 = 0;
      int var8 = intPartPos;
      boolean var9 = false;
      int var10 = ResUI.allFontImagesHeight[var5];
      if (bytesBookData != null) {
         while(var8 < bytesBookData.length && var7 + var10 <= var4) {
            int var13;
            for(var13 = var8 + 1; var13 < bytesBookData.length && bytesBookData[var13] != 32 && bytesBookData[var13] != 10; ++var13) {
               ;
            }

            if (var13 != var8) {
               byte[] var11;
               if (var13 >= bytesBookData.length) {
                  var11 = new byte[bytesBookData.length - var8 - 1];
                  System.arraycopy(bytesBookData, var8, var11, 0, bytesBookData.length - var8 - 1);
               } else {
                  var11 = new byte[var13 - var8];
                  System.arraycopy(bytesBookData, var8, var11, 0, var13 - var8);
               }

               int var12;
               if ((var12 = Core9.a(var11, var5)) > var3) {
                  if (var6 > 0) {
                     var6 = 0;
                     var7 = var7 + var10 + 2;
                  }
               } else if (var6 + var12 > var3) {
                  var6 = 0;
                  var7 = var7 + var10 + 2;
               }

               if (var7 + var10 <= var4) {
                  Core9.a(var0, var1 + var6, var2 + var7, var11, var5);
                  var6 += var12 + Core9.a;
                  var8 = var13 + 1;
               }

               if (var8 < bytesBookData.length && bytesBookData[var8] == 10) {
                  var6 = 0;
                  var7 = var7 + var10 + 2;
                  ++var8;
               }
            }
         }
      }

      return var8;
   }

   private static int d() {
      int var0;
      if ((var0 = p.size()) > 0) {
         int var1 = ((Integer)p.elementAt(var0 - 1)).intValue();
         p.removeElementAt(var0 - 1);
         return var1;
      } else {
         return 0;
      }
   }

   public static void setGraphics(Graphics var0) {
      ++s;
      if (a) {
         a = false;
         var0.setColor(255, 255, 255);
         var0.fillRect(0, 0, StaticData.screenWidth, StaticData.screenHeight);
         if (f) {
            c(var0);
            a = true;
         } else {
            a = false;
         }

         var0.drawImage(ResUI.c, StaticData.screenWidth - ResUI.c.getWidth(), 17, 20);
         String var1;
         if (!f && !d) {
            if (bytesBookData == null) {
               var1 = "Нет загруженных книг";
               Core9.a(var0, StaticData.screenWidth / 2 - Core9.a((String)var1, 0) / 2, (StaticData.screenHeight - e.getHeight()) / 2 - e.getHeight(), (String)var1, 0);
            } else {
               n = a(var0, 5, 23, StaticData.screenWidth - ResUI.c.getWidth() - 10, StaticData.screenHeight - 34 - 10, g);
               int var7 = n - intPartPos;
               if (bytesBookData != null && var7 < bytesBookData.length && bytesBookData.length > 0) {
                  int var2;
                  int var3 = (var2 = StaticData.screenHeight - 34) * var7 / bytesBookData.length;
                  int var4 = intPartPos * var2 / bytesBookData.length + 17;
                  int var5 = ResUI.d.getWidth();
                  int var6 = StaticData.screenWidth - var5;
                  var0.drawRegion(ResUI.d, 0, 0, var5, var3, 0, var6, var4, 20);
               }
            }
         }

         Core9.a(var1 = "Книга: " + strBookName + " / " + strBookAuthor + " ", g);
         boolean var8 = false;
         boolean var9 = false;
         var0.drawImage(ResUI.formatedImgToolbar, 0, 0, 20);
         var0.drawImage(ResUI.imgFormatedMenuBack, 0, StaticData.screenHeight - 17, 20);
         Core9.a(var0, 5, (17 - ResUI.allFontImagesHeight[g]) / 2, (String)var1, 4);
         b(var0);
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
                  switch(r) {
                  case 0:
                     if (!f && strBookId != 0) {
                        f = true;
                        a = true;
                     }
                     break;
                  case 1:
                     if (StaticData.b > 0) {
                        if (Messenger1.a[Messenger1.b] == null) {
                           BrowserRender.readInForm();
                           Messenger2.a();
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
                              if (StaticData.b > 0) {
                                 intPartNum___ = intPartNum__;
                                 BytesContentFactory var3;
                                 (var3 = new BytesContentFactory()).setType((short)704);
                                 var3.addInt(strBookId___);
                                 var3.addInt(intPartNum__);
                                 var3.addLong(x);
                                 SendPackets.addByteArrData(var3.bytesArray());
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
                        p.addElement(new Integer(intPartPos));
                        intPartPos = n;
                     }

                     a = true;
                  }
               } else if (f) {
                  v = 2;
               } else {
                  if ((var2 = d()) >= 0) {
                     n = intPartPos;
                     intPartPos = var2;
                  }

                  a = true;
               }
            } else {
               if (n < bytesBookData.length) {
                  p.addElement(new Integer(intPartPos));
                  intPartPos = n;
               }

               a = true;
            }
         } else {
            if ((var2 = d()) >= 0) {
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
         Core9.a(g, Core9.a, StaticData.screenHeight - 17 + (17 - ResUI.allFontImagesHeight[9]) / 2, (String)"Выбрать", 9);
      } else {
         Core9.a(g, Core9.a, StaticData.screenHeight - 17 + (17 - ResUI.allFontImagesHeight[9]) / 2, (String)"Меню", 9);
      }

      int var1;
      if (!q && !f) {
         var1 = Core9.a((String)"Назад", 9);
         Core9.a(g, StaticData.screenWidth - var1 - Core9.a, StaticData.screenHeight - 17 + (17 - ResUI.allFontImagesHeight[9]) / 2, (String)"Назад", 9);
      } else {
         var1 = Core9.a((String)"Отмена", 9);
         Core9.a(g, StaticData.screenWidth - var1 - Core9.a, StaticData.screenHeight - 17 + (17 - ResUI.allFontImagesHeight[9]) / 2, (String)"Отмена", 9);
      }

      String var3 = b;
      if (b.equals("")) {
         if (d) {
            var3 = "Загрузка";
         } else {
            var3 = ScreenCanvas.str1;
         }
      }

      int var2 = Core9.a((String)var3, 9);
      Core9.a(g, (StaticData.screenWidth - var2) / 2, StaticData.screenHeight - 17 + (17 - ResUI.allFontImagesHeight[9]) / 2, (String)var3, 9);
      if (q) {
         BrowserForm.a(g, strArrBookScreenMenu, r);
      }

   }

   public static void bookLoaded(byte[] var0) throws Exception {
      Yapp.Log("BOOK LOADED");
      f = false;
      q = false;
      strBookId = strBookId___;
      intPartNum = intPartNum___;
      intPartCount = intPartCount___;
      strBookAuthor = strBookAuthor___;
      strBookName = strBookName___;
      intPartPos = 0;
      bytesBookData = var0;
      DB.save("book-data", bytesBookData);
      DB.save("book-id", Integer.toString(strBookId).getBytes());
      DB.save("part-num", Integer.toString(intPartNum).getBytes());
      ByteArrayOutputStream var1 = new ByteArrayOutputStream();
      DataOutputStream var2;
      (var2 = new DataOutputStream(var1)).writeUTF(strBookAuthor);
      var2.flush();
      var1.flush();
      DB.save("book-author", var1.toByteArray());
      var1 = new ByteArrayOutputStream();
      (var2 = new DataOutputStream(var1)).writeUTF(strBookName);
      var2.flush();
      var1.flush();
      DB.save("book-name", var1.toByteArray());
      DB.save("part-count", Integer.toString(intPartCount).getBytes());
      DB.save("part-pos", Integer.toString(intPartPos).getBytes());
      p.removeAllElements();
      d = false;
      a = true;
   }

   private static void c(Graphics var0) {
      if (intPartCount___ >= 1) {
         int var1 = (StaticData.screenHeight - e.getHeight()) / 2;
         int var2 = StaticData.screenWidth / 2 - e.getWidth() / 2;
         int var3 = var0.getClipX();
         int var4 = var0.getClipY();
         int var5 = var0.getClipWidth();
         int var6 = var0.getClipHeight();
         var0.setColor(200, 200, 200);
         var0.fillRect(var2, var1, e.getWidth(), e.getHeight());
         String var7 = "Выберите часть книги";
         Core9.a(var0, StaticData.screenWidth / 2 - Core9.a((String)var7, 0) / 2, var1 - 3 * e.getHeight(), (String)var7, 0);
         Core9.a(var0, StaticData.screenWidth / 2 - Core9.a((String)strBookAuthor___, 0) / 2, var1 - 2 * e.getHeight(), (String)strBookAuthor___, 0);
         Core9.a(var0, StaticData.screenWidth / 2 - Core9.a((String)strBookName___, 0) / 2, var1 - e.getHeight(), (String)strBookName___, 0);
         var0.setClip(var2, var1, e.getWidth(), e.getHeight());
         if (u >= e.getHeight()) {
            u = 0;
            --intPartNum__;
            if (intPartNum__ < 1) {
               intPartNum__ = intPartCount___;
            }

            v = 0;
         } else if (u <= -e.getHeight()) {
            u = 0;
            ++intPartNum__;
            if (intPartNum__ > intPartCount___) {
               intPartNum__ = 1;
            }

            v = 0;
         }

         u += v;
         int var8 = e.getHeight();
         int var9 = StaticData.screenWidth / 2 - var8 / 2 + u;
         int var10 = var1 + ResUI.allFontImagesHeight[3] / 2;
         b(var0, intPartNum__, var9, var1, var10, var8);
         int var11 = intPartNum__;
         int var12 = intPartNum__;

         for(int var13 = 0; var13 < 3; ++var13) {
            --var11;
            ++var12;
            if (var11 < 1) {
               var11 = intPartCount___;
            }

            if (var12 > intPartCount___) {
               var12 = 1;
            }

            int var14 = (var13 + 1) * var8;
            b(var0, var11, var9 - var14, var1, var10, var8);
            b(var0, var12, var9 + var14, var1, var10, var8);
         }

         var0.drawImage(e, var2, var1, 20);
         var0.setClip(var3, var4, var5, var6);
      }

   }

   private static void b(Graphics var0, int var1, int var2, int var3, int var4, int var5) {
      if (var1 == intPartNum___) {
         var0.setColor(255, 0, 0);
         var0.fillRect(var2, var3, var5, var5);
      }

      String var6 = Integer.toString(var1);
      Core9.a(var0, var2 + (var5 - Core9.a((String)var6, 0)) / 2, var4, (String)var6, 0);
   }

   public static void b(byte[] var0) throws Exception {
      ByteArrayInputStream var1 = new ByteArrayInputStream(var0);
      DataInputStream var2;
      strBookId___ = (var2 = new DataInputStream(var1)).readInt();
      Yapp.Log("BOOK_ID " + strBookId___);
      x = var2.readLong();
      intPartCount___ = var2.readInt();
      Yapp.Log("PART_COUNT " + intPartCount___);
      strBookAuthor___ = var2.readUTF();
      strBookName___ = var2.readUTF();
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
