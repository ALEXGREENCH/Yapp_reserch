package ru.yapp.mobile.messenger;

import javax.microedition.lcdui.Graphics;

import ru.yapp.mobile.ScreenCanvas;
import ru.yapp.mobile.Yapp;
import ru.yapp.mobile.browser.BrowserForm;
import ru.yapp.mobile.browser.BrowserRender;
import ru.yapp.mobile.core.*;
import ru.yapp.mobile.gui.*;
import ru.yapp.mobile.net.SocketConnector;

public final class Messenger2 {
   public static boolean a = false;
   public static boolean b = true;
   public static boolean c = false;
   public static boolean d = true;
   public static long e;

   public static void a(int var0, int var1) {
      CommandListener2 var2 = Messenger1.a[Messenger1.b];
      if (var0 == StaticData.rightSoftKey && !a) {
         Messenger1.d();
      } else if (var0 == StaticData.leftSoftKey) {
         if (a) {
            a = false;
            BrowserRender.writeToForm();
            BrowserForm.prepareRender = true;
         } else {
            Messenger1.e();
         }
      } else if (var1 == 8) {
         if (var2 != null && !a) {
            Messenger1.f();
         } else {
            BrowserForm.a(var0, var1);
         }
      } else {
         label60:
         switch(var1) {
         case 1:
            if (var2 != null && !a) {
               switch(StaticData.g) {
               case 1:
               case 2:
                  Messenger1.b();
               default:
                  break label60;
               }
            }

            BrowserForm.a(var0, var1);
            break;
         case 2:
            a = false;
            Messenger1.h();
         case 3:
         case 4:
         default:
            break;
         case 5:
            a = false;
            Messenger1.g();
            break;
         case 6:
            if (var2 != null && !a) {
               switch(StaticData.g) {
               case 1:
               case 2:
                  Messenger1.c();
               }
            } else {
               BrowserForm.a(var0, var1);
            }
         }

         switch(var0) {
         case 49:
         case 50:
         case 51:
         case 52:
         case 53:
         case 54:
         case 55:
         case 56:
         case 57:
            if (StaticData.g == 2) {
               Yapp.display.setCurrent(Messenger1.a[Messenger1.b].k.a(Messenger1.a[Messenger1.b].k.c));
            }
         }
      }

      if (var2 == null || a) {
         BrowserForm.prepareRender = true;
      }

      b = true;
   }

   public static void a() {
      ViewElement[] var0 = new ViewElement[6];
      TextView var1;
      (var1 = new TextView()).k = 2;
      var1.l = 5;
      var1.text = "Ваш UIN:";
      var1.imgW = StaticData.screenWidth - 4 - ResUI.d.getWidth();
      var1.style = 0;
      var1.image = null;
      Core5.addGuiAbsToVector2((ViewElement)var1);
      Core5.b();
      var0[0] = var1;
      EditText var2;
      (var2 = new EditText()).k = 2;
      var2.l = var1.l + var1.imgH + 5;
      var2.a = "Введите здесь свой UIN";
      var2.isNumeric = true;
      if (DB.read("icq-login") != null) {
         var2.text = new String(DB.read("icq-login"));
      }

      var0[1] = var2;
      TextView var3;
      (var3 = new TextView()).k = 2;
      var3.l = var2.l + var2.imgH + 5;
      var3.text = "Пароль:";
      var3.imgW = StaticData.screenWidth - 4 - ResUI.d.getWidth();
      var3.style = 0;
      var3.image = null;
      Core5.addGuiAbsToVector2((ViewElement)var3);
      Core5.b();
      var0[2] = var3;
      EditText var4;
      (var4 = new EditText()).k = 2;
      var4.l = var3.l + var3.imgH + 5;
      var4.a = "Введите здесь свой пароль";
      var4.isPassword = true;
      if (DB.read("icq-password") != null) {
         var4.text = new String(DB.read("icq-password"));
      }

      var0[3] = var4;
      CheckBox var5;
      (var5 = new CheckBox()).text = "Запомнить";
      var5.k = 2;
      var5.l = var4.l + var4.imgH + 5;
      if (DB.read("icq-login") != null) {
         var5.isChecked = true;
      }

      var0[4] = var5;
      Button var6;
      (var6 = new Button()).k = 2;
      var6.l = var5.l + var5.imgH + 5;
      var6.text = "Подключиться";
      var6.image = null;
      byte[] var7 = new byte[]{0, 101, 0, 0, 0, 0};
      var6.biteArr1 = var7;
      var6.a();
      var0[5] = var6;
      Core1.c();
      BrowserForm.bool2 = false;
      BrowserForm.d = 0;
      BrowserForm.p = 0;
      BrowserForm.q = var6.l + var6.imgH + 5;
      BrowserForm.a(var0);
      BrowserForm.bgColor = 16777215;
      BrowserForm.o = 1;
      BrowserForm.bool1 = false;
      BrowserForm.prepareRender = true;
      b = true;
   }

   public static void b() {
      ViewElement[] var0 = new ViewElement[4];
      CheckBox var1;
      (var1 = new CheckBox()).k = 2;
      var1.l = 5;
      var1.text = "Вибрация";
      var1.isChecked = Messenger1.a[Messenger1.b].l;
      var0[0] = var1;
      CheckBox var2;
      (var2 = new CheckBox()).k = 2;
      var2.l = var1.l + var1.imgH + 5;
      var2.text = "Звук";
      var2.isChecked = Messenger1.a[Messenger1.b].m;
      var0[1] = var2;
      CheckBox var3;
      (var3 = new CheckBox()).k = 2;
      var3.l = var2.l + var2.imgH + 5;
      var3.text = "Показывать Offline контакты";
      var3.isChecked = Messenger1.a[Messenger1.b].n;
      var0[2] = var3;
      Button var4;
      (var4 = new Button()).k = 2;
      var4.l = var3.l + var3.imgH + 5;
      var4.text = "Сохранить";
      var4.image = null;
      byte[] var5 = new byte[]{0, 1, 0, 0, 0, 0};
      var4.biteArr1 = var5;
      var4.a();
      var0[3] = var4;
      Core1.c();
      BrowserForm.bool2 = false;
      BrowserForm.d = 0;
      BrowserForm.p = 0;
      BrowserForm.q = var3.l + var3.imgH + 5;
      BrowserForm.a(var0);
      BrowserForm.bgColor = 16777215;
      BrowserForm.o = 0;
      BrowserForm.bool1 = false;
      BrowserForm.prepareRender = true;
      b = true;
      a = true;
   }

   public static void c() {
      ViewElement[] var0 = new ViewElement[3];
      TextView var1;
      (var1 = new TextView()).k = 2;
      var1.l = 5;
      var1.text = "Ваш текущий статус:";
      var1.imgW = StaticData.screenWidth - 4 - ResUI.d.getWidth();
      var1.style = 0;
      var1.image = null;
      Core5.addGuiAbsToVector2((ViewElement)var1);
      Core5.b();
      var0[0] = var1;
      SelectableFormView var2;
      (var2 = new SelectableFormView()).k = 2;
      var2.l = var2.l + var2.imgH + 5;
      var2.text = "Вибрация";
      var2.vars = CommandListener3.a;
      var2.text = CommandListener3.a[Messenger1.a[Messenger1.b].i];
      var0[1] = var2;
      Button var3;
      (var3 = new Button()).k = 2;
      var3.l = var2.l + var2.imgH + 5;
      var3.text = "Изменить";
      var3.image = null;
      byte[] var4 = new byte[]{0, 107, 0, 0, 0, 0};
      var3.biteArr1 = var4;
      var3.a();
      var0[2] = var3;
      Core1.c();
      BrowserForm.bool2 = false;
      BrowserForm.d = 0;
      BrowserForm.p = 0;
      BrowserForm.q = var3.l + var3.imgH + 5;
      BrowserForm.a(var0);
      BrowserForm.bgColor = 16777215;
      BrowserForm.o = 1;
      BrowserForm.prepareRender = true;
      b = true;
      a = true;
   }

   private static void c(Graphics var0) {
      if (ResUI.formatedImgToolbar != null && Messenger1.b != -1) {
         var0.drawImage(ResUI.formatedImgToolbar, 0, 0, 0);
         var0.drawImage(ResUI.imgFormatedMenuBack, 0, StaticData.screenHeight - ResUI.imgFormatedMenuBack.getHeight(), 0);
         String var1 = "ICQ Login";
         if (Messenger1.a[Messenger1.b] != null) {
            var1 = Messenger1.a[Messenger1.b].m();
            Core9.a(var0, Core9.a * 2 + Core9.a((String)var1, 9), (17 - ResUI.allFontImagesHeight[4]) / 2, (String)Messenger1.a[Messenger1.b].h, 4);
         }

         Core9.a(var0, Core9.a, (17 - ResUI.allFontImagesHeight[9]) / 2, (String)var1, 9);
         String var2 = "Меню";
         String var3 = "Назад";
         if (Messenger1.a[Messenger1.b] != null && !a && Messenger1.a[Messenger1.b].h()) {
            var3 = "Отмена";
         }

         if (Messenger1.a[Messenger1.b] != null && !a) {
            Core9.a(var0, Core9.a, StaticData.screenHeight - 17 + (17 - ResUI.allFontImagesHeight[9]) / 2, (String)var2, 9);
         }

         if (a) {
            var3 = "Отмена";
         }

         int var4 = Core9.a((String)var3, 9);
         Core9.a(var0, StaticData.screenWidth - var4 - Core9.a, StaticData.screenHeight - 17 + (17 - ResUI.allFontImagesHeight[9]) / 2, (String)var3, 9);
         String var5 = ScreenCanvas.str1;
         int var6;
         if (c) {
            a(var0);
         } else {
            var6 = Core9.a((String)var5, 9);
            Core9.a(var0, (StaticData.screenWidth - var6) / 2, StaticData.screenHeight - 17 + (17 - ResUI.allFontImagesHeight[9]) / 2, (String)var5, 9);
         }

         var6 = SocketConnector.isRunning ? '\uff00' : 16711680;
         var0.setColor(var6);
         var0.fillRect(StaticData.screenWidth / 2 - 1, 0, 2, ResUI.formatedImgToolbar.getHeight());
      }

   }

   public static void a(Graphics var0) {
      if (c && d) {
         int var1 = CommandListener2.a[0].getWidth();
         int var2 = CommandListener2.a[0].getHeight();
         int var3 = 0;

         for(int var4 = StaticData.j.length - 1; var4 > -1; --var4) {
            if (StaticData.j[var4] != -1) {
               byte var5 = Messenger1.a(StaticData.j[var4]);
               if (Messenger1.a[var5].v > 0) {
                  var0.drawImage(Messenger1.a[var5].f, StaticData.screenWidth / 2 - var1 / 2 + var3, StaticData.screenHeight - ResUI.imgFormatedMenuBack.getHeight() / 2 - var2 / 2, 0);
                  var3 -= 5;
               }
            }
         }
      }

   }

   public static void setGraphics(Graphics g) {
      if (b) {
         Messenger1.a(g);
         c(g);
         b = false;
      }

   }
}
