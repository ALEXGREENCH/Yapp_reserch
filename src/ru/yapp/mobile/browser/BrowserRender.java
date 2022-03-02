package ru.yapp.mobile.browser;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import javax.microedition.lcdui.Image;

import ru.yapp.mobile.ScreenCanvas;
import ru.yapp.mobile.Yapp;
import ru.yapp.mobile.core.*;
import ru.yapp.mobile.gui.*;
import ru.yapp.mobile.net.SendPackets;

public final class BrowserRender {
    
   private static ViewElement[] viewElementsArr = null;
   private static String titleYappBrowser = null;
   private static int c = 0;
   private static int d = 0;
   private static int e = 0;
   private static int f = 0;
   private static int browserFormBgColor = 0;
   private static int h = 0;
   private static boolean i = false;

   public static void readInForm() {
      if (viewElementsArr == null) {
         viewElementsArr = BrowserForm.getUiElements();
         titleYappBrowser = BrowserForm.titleYappBrowser;
         c = BrowserForm.d;
         d = Core1.b();
         e = BrowserForm.p;
         f = BrowserForm.q;
         browserFormBgColor = BrowserForm.bgColor;
         h = BrowserForm.o;
         i = BrowserForm.bool1;
      }

   }

   public static void writeToForm() {
      if (viewElementsArr != null) {
         BrowserForm.a(viewElementsArr);
         BrowserForm.titleYappBrowser = titleYappBrowser;
         BrowserForm.d = c;
         Core1.a(d);
         BrowserForm.p = e;
         BrowserForm.q = f;
         BrowserForm.bgColor = browserFormBgColor;
         BrowserForm.o = h;
         BrowserForm.bool1 = i;
         viewElementsArr = null;
      }

   }

   
   public static void renderScreenDemo(){
       try {
         ByteArrayOutputStream baos = new ByteArrayOutputStream();
         DataOutputStream dos = new DataOutputStream(baos);
         
         dos.writeUTF("Ну привет");
         dos.writeInt(0);
         dos.writeShort(1);
         
         dos.writeShort(2); // тип (текст)
         dos.writeByte(0); // цвет
         dos.writeByte(0); // игнорит
         dos.writeUTF("Номер Yapp!:"); // сам текст
         dos.writeByte(0);
         dos.writeByte(0);
         
         dos.writeShort(0);
         
         dos.flush();
         baos.flush();
        
         byte[] baosArr = baos.toByteArray();
         
         Core3.vectorData.addElement(baosArr);
         Core3.vectorInt1.addElement(new Integer(BrowserForm.o));
         Core3.vectorInt2.addElement(new Integer(BrowserForm.p));
         
         BrowserForm.bgColor = 0xe3e3e3;
         renderGUI(baosArr, false, -1, 0);
       } catch(Exception e){}
   }
   
   public static void renderAuthScreen(byte[] data, String title) {
      try {
         ByteArrayOutputStream bos = new ByteArrayOutputStream();
         DataOutputStream dos = new DataOutputStream(bos);
         
         dos.writeUTF(title); // заголовок
         dos.writeInt(0);
         //dos.writeShort(8); // колличество
         dos.writeShort(9); // колличество
         
         dos.writeShort(5);
         dos.writeUTF("");
         dos.writeUTF("Выберите свой пол");
         dos.writeUTF("");
         dos.writeByte(3);
         dos.writeUTF("1");
         dos.writeUTF("2");
         dos.writeUTF("3");
         
         dos.writeShort(2); // тип (текст)
         dos.writeByte(0); // цвет
         dos.writeByte(0); // игнорит
         dos.writeUTF("Номер Yapp!:"); // сам текст
         dos.writeByte(0);
         dos.writeByte(0);
         
         dos.writeShort(4);
         dos.writeUTF("Введите здесь свой номер Yapp!");
         if (BdUtil.read("yapp-login") != null) {
            dos.writeUTF(new String(BdUtil.read("yapp-login")));
         } else {
            dos.writeUTF("");
         }
         dos.writeUTF("");
         dos.writeByte(0);
         dos.writeByte(1);
         
         
         dos.writeShort(2);
         dos.writeByte(0);
         dos.writeByte(0);
         dos.writeUTF("Пароль:");
         dos.writeByte(0);
         dos.writeByte(0);
         
         
         dos.writeShort(4);
         dos.writeUTF("Введите здесь свой пароль");
         if (BdUtil.read("yapp-password") != null) {
            dos.writeUTF(new String(BdUtil.read("yapp-password")));
         } else {
            dos.writeUTF("");
         }
         dos.writeUTF("");
         dos.writeByte(1);
         dos.writeByte(0);
         
         dos.writeShort(6);
         dos.writeUTF("Запомнить");
         dos.writeUTF("");
         if (BdUtil.read("yapp-login") != null) {
            dos.writeByte(1);
         } else {
            dos.writeByte(0);
         }
         
         
         dos.writeShort(8);
         dos.writeByte(0);
         dos.writeUTF("Подключиться");
         dos.writeInt(data.length);
         dos.write(data);
         
         dos.writeShort(3);
         dos.writeUTF("Регистрация");
         dos.writeByte(0);
         dos.writeByte(0);
         dos.writeByte(0);
         byte[] var4 = new byte[]{0, 6, -91, 0, 0, 0, 0};
         dos.writeInt(var4.length);
         dos.write(var4);
         
         
         dos.writeShort(3);
         dos.writeUTF("Забыли пароль?");
         dos.writeByte(0);
         dos.writeByte(0);
         dos.writeByte(0);
         byte[] var5 = new byte[]{0, 7, 109, 0, 0, 0, 0};
         dos.writeInt(var5.length);
         dos.write(var5);
         
         
         dos.writeShort(0);
         
         dos.flush();
         bos.flush();
         
         
         byte[] byteArr = bos.toByteArray();
         Core3.vectorData.addElement(byteArr);
         Core3.vectorInt1.addElement(new Integer(BrowserForm.o));
         Core3.vectorInt2.addElement(new Integer(BrowserForm.p));
         byte focusPosition = 6;
         if (BdUtil.read("yapp-login") != null) {
            focusPosition = 5;
         }

         BrowserForm.bgColor = 0xFFFFFF;
         renderGUI(byteArr, false, focusPosition, 0);
      } catch (Exception e) {
         e.printStackTrace();
      }
   }

   public static void renderHelpScreen(String text) {
      try {
         ByteArrayOutputStream baos = new ByteArrayOutputStream();
         DataOutputStream dos = new DataOutputStream(baos);
         
         dos.writeUTF("Помощь");
         dos.writeInt(0);
         dos.writeShort(1);
         
         
         dos.writeShort(2);
         dos.writeByte(0);
         dos.writeByte(0);
         dos.writeUTF(text);
         dos.writeByte(0);
         dos.writeByte(0);
         
         
         dos.writeShort(0);
         
         
         dos.flush();
         baos.flush();
         
         byte[] var3 = baos.toByteArray();
         Core3.vectorData.addElement(var3);
         Core3.vectorInt1.addElement(new Integer(BrowserForm.o));
         Core3.vectorInt2.addElement(new Integer(BrowserForm.p));
         BrowserForm.bgColor = 0xFFFFFF;
         renderGUI(var3, false, -1, 0);
      } catch (Exception e) {
         e.printStackTrace();
      }
   }

   public static void renderGUI(byte[] bytesDataArr, boolean var1, int focusPos, int var3) throws Exception {
      ByteArrayInputStream var4 = new ByteArrayInputStream(bytesDataArr);
      DataInputStream dis = new DataInputStream(var4);
      
      String title = dis.readUTF();
      int bannerID = dis.readInt();
      short countViewElements = dis.readShort();
      ViewElement[] arrViews = new ViewElement[countViewElements];

      for(int i = 0; i < countViewElements; ++i) {
         short type = dis.readShort();
         Yapp.Log("READED->" + i + " component->" + type);
         switch(type) {
         case 1:
            Gui7 g7 = new Gui7(); // IMAGE ??
            g7.j = dis.readByte();
            g7.str1 = dis.readUTF();
            g7.str2 = dis.readUTF();
            a((ViewElement)g7, (DataInputStream)dis);
            arrViews[i] = g7;
            break;
         case 2:
            TextView textView = new TextView();
            /*
            / 0 чёрный, 1 синий, 2 красный, 3 зелёный, 4 белый
            / 5 черный жирный, 6 синиц жирный, 7 красный жирный, 8 зелёный жирный, 9 белый жтоный
            / 10 
            */
            textView.style = dis.readByte(); // byte - Цвет 
            dis.readByte(); // byte
            textView.text = dis.readUTF(); // string UTF-8
            textView.j = dis.readByte(); // byte
            textView.c = dis.readByte(); // byte
            arrViews[i] = textView;
            break;
         case 3:
            FocusableTextView focusableTextView = new FocusableTextView();
            focusableTextView.text = dis.readUTF();
            focusableTextView.style = dis.readByte();
            focusableTextView.j = dis.readByte();
            focusableTextView.c = dis.readByte();
            a((ViewElement)focusableTextView, (DataInputStream)dis);
            arrViews[i] = focusableTextView;
            break;
         case 4:
            EditText editText = new EditText();
            editText.hint = dis.readUTF();
            editText.text = dis.readUTF();
            dis.readUTF();
            if (dis.readByte() == 1) {
               editText.isPassword = true;
            }
            if (dis.readByte() == 1) {
               editText.isNumeric = true;
            }
            arrViews[i] = editText;
            break;
         case 5:
            SelectableFormView selectableFormView = new SelectableFormView();
            selectableFormView.a = dis.readUTF(); // utf8
            selectableFormView.text = dis.readUTF(); // utf8
            dis.readUTF(); // ignote
            byte strSize = dis.readByte(); // byte
            String[] strArr = new String[strSize]; // колличество строк

            for(int j = 0; j < strSize; ++j) {
               strArr[j] = dis.readUTF(); // читаем строки
            }
            selectableFormView.vars = strArr;
            arrViews[i] = selectableFormView;
            break;
         case 6:
            CheckBox checkBox = new CheckBox();
            checkBox.text = dis.readUTF();
            dis.readUTF();
            if (dis.readByte() == 1) {
               checkBox.isChecked = true;
            }
            arrViews[i] = checkBox;
            break;
         case 7:
            ImageView imageView = new ImageView();
            imageView.a = dis.readByte(); // byte
            a((ViewElement)imageView, (DataInputStream)dis);
            byte[] var21 = new byte[dis.readInt()];
            dis.readFully(var21);
            ByteArrayInputStream bais = new ByteArrayInputStream(var21);
            Image image = Image.createImage(bais);
            bais.close();
            imageView.img = image;
            imageView.imgW = image.getWidth();
            imageView.imgH = image.getHeight();
            arrViews[i] = imageView;
            break;
         case 8:
            Button botton = new Button();
            botton.j = dis.readByte();
            botton.text = dis.readUTF();
            a((ViewElement)botton, (DataInputStream)dis);
            arrViews[i] = botton;
            break;
         default:
            Yapp.Log("Unknown Component " + type);
         }
      }

      countViewElements = dis.readShort();

      for(int i = 0; i < countViewElements; ++i) {
         switch(dis.readShort()) {
         case 9:
            byte var31 = dis.readByte();
            byte[] var33 = new byte[dis.readInt()];
            dis.readFully(var33);
            ByteArrayInputStream bais = new ByteArrayInputStream(var33);
            Image image = Image.createImage(bais);
            bais.close();

            for(int j = 0; j < arrViews.length; ++j) {
               if (arrViews[j] != null && arrViews[j].j == var31) {
                  arrViews[j].image = image;
                  if (arrViews[j] instanceof Button) {
                     ((Button)arrViews[j]).a();
                  }
               }
            }
         }
      }

      int var10 = 5;

      for(int i = 0; i < arrViews.length; ++i) {
         int var32 = 2;
         if (arrViews[i] instanceof ImageView) {
            ImageView var34;
            switch((var34 = (ImageView)arrViews[i]).a) {
            case 0:
               var32 = 2;
               break;
            case 1:
               var32 = (StaticData.screenWidth - var34.img.getWidth()) / 2 - UiUtil.img4.getWidth();
               break;
            case 2:
               var32 = StaticData.screenWidth - 2 - UiUtil.img4.getWidth() - var34.img.getWidth();
            }
         }

         if (arrViews[i] instanceof TextView) {
            TextView var35;
            if ((var35 = (TextView)arrViews[i]).c == 0) {
               if (Core5.getSizeVector2() > 0) {
                  Core5.b();
                  var35.l = arrViews[i - 1].l + arrViews[i - 1].imgH + 5;
               } else {
                  var35.l = var10;
               }

               Core5.addGuiAbsToVector2((ViewElement)var35);
            } else {
               var35.l = var10;
               Core5.addGuiAbsToVector2((ViewElement)var35);
            }
         } else if (Core5.getSizeVector2() > 0) {
            Core5.b();
            arrViews[i].k = var32;
            arrViews[i].l = arrViews[i - 1].l + arrViews[i - 1].imgH + 5;
            arrViews[i].imgW = StaticData.screenWidth - 4 - UiUtil.img4.getWidth();
            if (arrViews[i] instanceof Button) {
               ((Button)arrViews[i]).a();
            }

            var10 = arrViews[i].l + arrViews[i].imgH + 5;
            if (arrViews[i] instanceof ImageView) {
               Core5.addGuiAbsToVector2(arrViews[i]);
            }
         } else {
            arrViews[i].k = var32;
            arrViews[i].l = var10;
            arrViews[i].imgW = StaticData.screenWidth - 4 - UiUtil.img4.getWidth();
            if (arrViews[i] instanceof Button) {
               ((Button)arrViews[i]).a();
            }

            var10 = arrViews[i].l + arrViews[i].imgH + 5;
         }

         Yapp.Log(i + " component " + arrViews[i].toString() + " " + var32 + " " + var10);
      }

      if (Core5.getSizeVector2() > 0) {
         Core5.b();
      }

      Yapp.Log("BANNER ID->" + bannerID);
      BrowserForm.p = 0;
      if (var1) {
         BrowserForm.p = var3;
         Core1.a(0);
         BytesContentFactory var30;
         (var30 = new BytesContentFactory()).setType((short)903);
         var30.addInt(Core1.b());
         SendPackets.addByteArrData(var30.bytesArray());
      } else {
         Core1.a(bannerID);
      }

      BrowserForm.titleYappBrowser = title;
      BrowserForm.d = Core1.d();
      BrowserForm.a(arrViews);
      BrowserForm.q = arrViews[arrViews.length - 1].l + arrViews[arrViews.length - 1].imgH + 5;
      BrowserForm.bgColor = 0xFFFFFF;
      BrowserForm.o = -1;
      if (BrowserForm.d > 0) {
         BrowserForm.bool3 = true;
      }

      BrowserForm.bool2 = true;
      if (focusPos > -1) {
         BrowserForm.o = focusPos;
         BrowserForm.bool2 = false;
         if (arrViews[focusPos].l + arrViews[focusPos].imgH > BrowserForm.b()) {
            BrowserForm.bool3 = false;
         }
      }

      BrowserForm.a = "";
      BrowserForm.bool1 = false;
      BrowserForm.prepareRender = true;
      ScreenCanvas.boolean2 = true;
   }

   public static void a(ViewElement viewElement, DataInputStream dis) throws Exception {
      int var2 = dis.readInt();
      if (var2 > 1) {
         byte var3 = dis.readByte();
         Yapp.Log("Count->" + var2 + " ->" + var3);
         byte[] var4 = new byte[var2 - 1];
         dis.readFully(var4);
         if (var3 == 0) {
            viewElement.byteArr2 = var4;
            Yapp.Log("WRITED ACTION");
            return;
         }

         viewElement.biteArr1 = var4;
         Yapp.Log("WRITED COMMAND");
      }

   }
}
