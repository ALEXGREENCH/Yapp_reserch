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
import ru.yapp.mobile.book.Book;
import ru.yapp.mobile.browser.BrowserForm;
import ru.yapp.mobile.browser.BrowserRender;
import ru.yapp.mobile.gui.EditText;
import ru.yapp.mobile.gui.SelectableFormView;
import ru.yapp.mobile.gui.Gui4;
import ru.yapp.mobile.gui.CheckBox;
import ru.yapp.mobile.gui.ViewElement;
import ru.yapp.mobile.messenger.CommandListener2;
import ru.yapp.mobile.messenger.Messenger1;
import ru.yapp.mobile.messenger.Messenger2;
import ru.yapp.mobile.net.SocketConnector;
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
            BrowserRender.renderGUI((byte[])((byte[])vectorData.elementAt(sizeVectorData - 2)), // хз
                    true, // хз
((Integer)vectorInt1.elementAt(sizeVectorData - 1)).intValue(), //хз
((Integer)vectorInt2.elementAt(sizeVectorData - 1)).intValue() //хз
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

   public static void parsing(int idCommand, byte[] byteArr) {
      Yapp.Log("пришла команда " + idCommand);
      switch(idCommand) {
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
         Messenger1.j(byteArr);
         return;
      case 103:
         Messenger1.b(byteArr);
         return;
      case 104:
         Messenger1.a(byteArr);
         return;
      case 105:
         Messenger1.c(byteArr);
         return;
      case 106:
         Messenger1.l(byteArr);
         return;
      case 107:
         Messenger1.g(byteArr);
         return;
      case 108:
         Messenger1.k(byteArr);
         return;
      case 109:
         Messenger1.h(byteArr);
         return;
      case 110:
         Messenger1.d(byteArr);
         return;
      case 111:
         Messenger1.m(byteArr);
         return;
      case 112:
         Messenger1.i(byteArr);
         return;
      case 151:
         Messenger1.e(byteArr);
         return;
      case 152:
         Messenger1.f(byteArr);
         return;
      case 703:
         try {
            Book.b(byteArr);
            return;
         } catch (Exception e) {
            e.printStackTrace();
         }
      default:
         return;
      case 901:
         Yapp.Log("BANNERS COME IN");

         try {
            d(byteArr);
         } catch (Exception e) {
            Yapp.Log("BANNER LOADING ERROR");
            e.printStackTrace();
         }
      }
   }

   public static void parsing(byte[] var0) throws Exception {
      ByteArrayInputStream var1 = new ByteArrayInputStream(var0);
      DataInputStream var2 = new DataInputStream(var1);
      switch(var2.readShort()) {
      case 1:
         c();
         return;
      case 50:
         BrowserRender.renderAuthScreen(new byte[]{1, 0, 99, 0, 0, 0, 0}, "Yapp! ");
         return;
      case 98:
         byte[] var10 = new byte[var2.readInt()];
         var2.read(var10);
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
         var2.readInt();
         int var15 = var2.readInt();
         Messenger1.a[0].c(var15);
         break;
      case 199:
         if (StaticData.b > 0) {
            if (Messenger1.a[Messenger1.b] == null) {
               BrowserRender.readInForm();
               Messenger2.a();
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
         var2.readInt();
         String var6 = var2.readUTF();
         String var7 = var2.readUTF();
         a(var2.readUTF(), var7, var6);
         return;
      case 703:
         var2.readInt();
         BrowserForm.bool1 = true;
         BrowserForm.prepareRender = true;
         int var11 = var2.readInt();
         long var12 = Runtime.getRuntime().totalMemory();
         BytesContentFactory var14;
         (var14 = new BytesContentFactory()).setType((short)703);
         var14.addInt(var11);
         var14.addLong(var12);
         SendPackets.addByteArrData(var14.bytesArray());
         return;
      case 1001:
         var2.readInt();
         String var5;
         if ((var5 = var2.readUTF()).length() > 0) {
            a(var5, var2.readByte());
         }
      }

   }

   private static void b() {
      if (Messenger1.b != -1 && Messenger1.a[Messenger1.b] != null) {
         SelectableFormView gui3 = (SelectableFormView)BrowserForm.getUiElements()[1];
         byte var2 = 0;

         for(byte var3 = 0; var3 < gui3.vars.length; ++var3) {
            if (gui3.text.equals(gui3.vars[var3])) {
               var2 = var3;
            }
         }

         Messenger1.c(var2);
      }

      BrowserForm.prepareRender = true;
      Messenger2.a = false;
      Messenger2.b = true;
      BrowserRender.writeToForm();
   }

   private static void c() {
      if (Messenger1.b != -1 && Messenger1.a[Messenger1.b] != null) {
         ViewElement[] var0 = BrowserForm.getUiElements();
         CommandListener2 var1;
         (var1 = Messenger1.a[Messenger1.b]).l = ((CheckBox)var0[0]).isChecked;
         if (var1.m != ((CheckBox)var0[1]).isChecked) {
            var1.f();
         }

         if (var1.n != ((CheckBox)var0[2]).isChecked) {
            var1.g();
         }

         String var2 = var1.m();
         byte[] var3 = new byte[]{1};
         byte[] var4 = new byte[]{0};
         if (var1.m) {
            DB.save(var2 + "-sound", var3);
         } else {
            DB.save(var2 + "-sound", var4);
         }

         if (var1.l) {
            DB.save(var2 + "-vibro", var3);
         } else {
            DB.save(var2 + "-vibro", var4);
         }

         if (var1.n) {
            DB.save(var2 + "-hide", var3);
         } else {
            DB.save(var2 + "-hide", var4);
         }
      }

      BrowserForm.prepareRender = true;
      Messenger2.a = false;
      Messenger2.b = true;
      BrowserRender.writeToForm();
   }

   private static void b(byte[] var0) throws IOException {
      BrowserForm.bool1 = true;
      ByteArrayOutputStream var1 = new ByteArrayOutputStream();
      DataOutputStream var2 = new DataOutputStream(var1);
      ViewElement[] var3 = BrowserForm.getUiElements();

      for(int var4 = 0; var4 < var3.length; ++var4) {
         if (var3[var4].boolean1) {
            if (var3[var4] instanceof EditText) {
               var2.writeUTF(var3[var4].text);
            } else if (var3[var4] instanceof SelectableFormView) {
               var2.writeUTF(var3[var4].text);
            } else if (var3[var4] instanceof CheckBox) {
               if (((CheckBox)var3[var4]).isChecked) {
                  var2.writeByte(1);
               } else {
                  var2.writeByte(0);
               }
            }
         }
      }

      var2.flush();
      var1.flush();
      byte[] var5 = var1.toByteArray();
      var2.close();
      var1.close();
      var1 = new ByteArrayOutputStream();
      (var2 = new DataOutputStream(var1)).write(var0, 0, 2);
      var2.writeInt(var0.length - 6 + var5.length);
      var2.write(var0, 6, var0.length - 6);
      var2.write(var5);
      var2.flush();
      var1.flush();
      SendPackets.b(var1.toByteArray());
      var2.close();
      var1.close();
   }

   private static void a(String var0, String var1, String var2) throws Exception {
      ByteArrayOutputStream var3 = new ByteArrayOutputStream();
      DataOutputStream var4;
      (var4 = new DataOutputStream(var3)).writeUTF(var2);
      var4.writeInt(0);
      var4.writeShort(2);
      var4.writeShort(2);
      var4.writeByte(0);
      var4.writeByte(0);
      var4.writeUTF(var1);
      var4.writeByte(0);
      var4.writeByte(0);
      var4.writeShort(2);
      var4.writeByte(1);
      var4.writeByte(0);
      var4.writeUTF(var0);
      var4.writeByte(0);
      var4.writeByte(0);
      var4.writeShort(0);
      var4.flush();
      var3.flush();
      byte[] var5 = var3.toByteArray();
      vectorData.addElement(var5);
      vectorInt1.addElement(new Integer(BrowserForm.o));
      vectorInt2.addElement(new Integer(BrowserForm.p));
      BrowserRender.renderGUI(var5, false, -1, 0);
   }

   private static void a(String var0, byte var1) throws Exception {
      Yapp.Log("URL->" + var0);
      boolean var2 = Yapp.yappMidlet.platformRequest(var0);
      System.out.println("needClose: " + var2);
      if (var2 || var1 == 1) {
         Yapp.yappMidlet.midletDestroy();
      }

   }

   private static void c(byte[] var0) {
      ByteArrayInputStream var1 = new ByteArrayInputStream(var0);
      DataInputStream var2 = new DataInputStream(var1);
      String var4 = "";

      try {
         var2.readUTF();
         var4 = var2.readUTF();
      } catch (IOException var7) {
         var7.printStackTrace();
      }

      if (var4.equals("")) {
         vectorInt1.removeAllElements();
         vectorData.removeAllElements();
         vectorInt2.removeAllElements();
         BytesContentFactory var5;
         (var5 = new BytesContentFactory()).setType((short)901);
         var5.addInt(StaticData.screenWidth);
         var5.addInt(StaticData.screenHeight);
         SendPackets.addByteArrData(var5.bytesArray());
         Messenger1.b((byte)0);
         BrowserForm.authMenuVariants = new String[]{"На весь экран", "Общайся", "Библиотека", "Помощь", "Выход"};
         BrowserForm.__messagerValue = 1;
         BrowserForm.__bookValue = 2;
         BrowserForm._helpValue = 3;
         BrowserForm._exitAppValue = 4;
      } else {
         SocketConnector.disconnect();
         ScreenCanvas.textDialog = var4;
         BrowserForm.bool1 = false;
         BrowserForm.prepareRender = true;
         ScreenCanvas.boolean2 = true;
      }
   }

   private static void d(byte[] var0) throws Exception {
      Yapp.Log("LEN->" + var0.length);
      ByteArrayInputStream var1 = new ByteArrayInputStream(var0);
      DataInputStream var2;
      short var3;
      Gui4[] var4 = new Gui4[var3 = (var2 = new DataInputStream(var1)).readShort()];

      for(int var5 = 0; var5 < var3; ++var5) {
         var4[var5] = new Gui4();
         var4[var5].a = var2.readInt();
         var4[var5].d = var2.readByte();
         BrowserRender.a((ViewElement)var4[var5], (DataInputStream)var2);
         byte[] var7 = new byte[var2.readInt()];
         var2.readFully(var7);
         ByteArrayInputStream var8;
         Image var9 = Image.createImage(var8 = new ByteArrayInputStream(var7));
         var8.close();
         var4[var5].image = var9;
         var4[var5].a();
      }

      var1.close();
      Core1.a = var4;
      BytesContentFactory var10;
      (var10 = new BytesContentFactory()).setType((short)801);
      var10.addByte((byte)0);
      SendPackets.addByteArrData(var10.bytesArray());
   }
}
