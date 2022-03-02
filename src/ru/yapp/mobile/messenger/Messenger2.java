package ru.yapp.mobile.messenger;

import javax.microedition.lcdui.Graphics;

import ru.yapp.mobile.ScreenCanvas;
import ru.yapp.mobile.Yapp;
import ru.yapp.mobile.browser.BrowserForm;
import ru.yapp.mobile.browser.BrowserRender;
import ru.yapp.mobile.core.*;
import ru.yapp.mobile.gui.*;
import ru.yapp.mobile.net.NetworkUtil;

public final class Messenger2 {
    
   public static boolean a = false;
   public static boolean b = true;
   public static boolean c = false;
   public static boolean d = true;
   public static long currentTimeMillis;

   public static void a(int var0, int var1) {
      CommandListener2 var2 = Messenger.cmdListener2Arr[Messenger.b];
      if (var0 == StaticData.rightSoftKey && !a) {
         Messenger.d();
      } else if (var0 == StaticData.leftSoftKey) {
         if (a) {
            a = false;
            BrowserRender.writeToForm();
            BrowserForm.prepareRender = true;
         } else {
            Messenger.e();
         }
      } else if (var1 == 8) {
         if (var2 != null && !a) {
            Messenger.f();
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
                  Messenger.b();
               default:
                  break label60;
               }
            }

            BrowserForm.a(var0, var1);
            break;
         case 2:
            a = false;
            Messenger.h();
         case 3:
         case 4:
         default:
            break;
         case 5:
            a = false;
            Messenger.g();
            break;
         case 6:
            if (var2 != null && !a) {
               switch(StaticData.g) {
               case 1:
               case 2:
                  Messenger.c();
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
               Yapp.display.setCurrent(Messenger.cmdListener2Arr[Messenger.b].k.displayableTextBoxMessage(Messenger.cmdListener2Arr[Messenger.b].k.str1));
            }
         }
      }

      if (var2 == null || a) {
         BrowserForm.prepareRender = true;
      }

      b = true;
   }

   public static void renderIcqAuthScreen() {
      ViewElement[] viewArr = new ViewElement[6];
      
      TextView yourUintextView=  new TextView();
      yourUintextView.k = 2;
      yourUintextView.l = 5;
      yourUintextView.text = "Ваш UIN:";
      yourUintextView.imgW = StaticData.screenWidth - 4 - UiUtil.img4.getWidth();
      yourUintextView.style = 0;
      yourUintextView.image = null;
      Core5.addGuiAbsToVector2((ViewElement)yourUintextView);
      Core5.b();
      viewArr[0] = yourUintextView;
      
      
      EditText interYourUinHereEditText = new EditText();
      interYourUinHereEditText.k = 2;
      interYourUinHereEditText.l = yourUintextView.l + yourUintextView.imgH + 5;
      interYourUinHereEditText.hint = "Введите здесь свой UIN";
      interYourUinHereEditText.isNumeric = true;
      if (BdUtil.read("icq-login") != null) {
         interYourUinHereEditText.text = new String(BdUtil.read("icq-login"));
      }
      viewArr[1] = interYourUinHereEditText;
      
      
      TextView passwordTextView = new TextView();
      passwordTextView.k = 2;
      passwordTextView.l = interYourUinHereEditText.l + interYourUinHereEditText.imgH + 5;
      passwordTextView.text = "Пароль:";
      passwordTextView.imgW = StaticData.screenWidth - 4 - UiUtil.img4.getWidth();
      passwordTextView.style = 0;
      passwordTextView.image = null;
      Core5.addGuiAbsToVector2((ViewElement)passwordTextView);
      Core5.b();
      viewArr[2] = passwordTextView;
      
      
      EditText interYourPasswordHereEditText = new EditText();
      interYourPasswordHereEditText.k = 2;
      interYourPasswordHereEditText.l = passwordTextView.l + passwordTextView.imgH + 5;
      interYourPasswordHereEditText.hint = "Введите здесь свой пароль";
      interYourPasswordHereEditText.isPassword = true;
      if (BdUtil.read("icq-password") != null) {
         interYourPasswordHereEditText.text = new String(BdUtil.read("icq-password"));
      }
      viewArr[3] = interYourPasswordHereEditText;
      

      CheckBox rememberCheckBox = new CheckBox();
      rememberCheckBox.text = "Запомнить";
      rememberCheckBox.k = 2;
      rememberCheckBox.l = interYourPasswordHereEditText.l + interYourPasswordHereEditText.imgH + 5;
      if (BdUtil.read("icq-login") != null) {
         rememberCheckBox.isChecked = true;
      }
      viewArr[4] = rememberCheckBox;
      
      Button connectButton = new Button();
      connectButton.k = 2;
      connectButton.l = rememberCheckBox.l + rememberCheckBox.imgH + 5;
      connectButton.text = "Подключиться";
      connectButton.image = null;
      byte[] var7 = new byte[]{0, 101, 0, 0, 0, 0};
      connectButton.biteArr1 = var7;
      connectButton.a();
      viewArr[5] = connectButton;
      
      
      Core1.c();
      BrowserForm.bool2 = false;
      BrowserForm.d = 0;
      BrowserForm.p = 0;
      BrowserForm.q = connectButton.l + connectButton.imgH + 5;
      BrowserForm.a(viewArr);
      BrowserForm.bgColor = 0xFFFFFF;
      BrowserForm.o = 1;
      BrowserForm.bool1 = false;
      BrowserForm.prepareRender = true;
      b = true;
   }

   public static void renderMessagerSettingsScreen() {
      ViewElement[] viewArr = new ViewElement[4];
      
      CheckBox vibrationCheckBox = new CheckBox();
      vibrationCheckBox.k = 2;
      vibrationCheckBox.l = 5;
      vibrationCheckBox.text = "Вибрация";
      vibrationCheckBox.isChecked = Messenger.cmdListener2Arr[Messenger.b].l;
      viewArr[0] = vibrationCheckBox;
      
      
      CheckBox audioCheckBox = new CheckBox();
      audioCheckBox.k = 2;
      audioCheckBox.l = vibrationCheckBox.l + vibrationCheckBox.imgH + 5;
      audioCheckBox.text = "Звук";
      audioCheckBox.isChecked = Messenger.cmdListener2Arr[Messenger.b].m;
      viewArr[1] = audioCheckBox;
      
      
      CheckBox visibleOfflineContactsCheckBox = new CheckBox();
      visibleOfflineContactsCheckBox.k = 2;
      visibleOfflineContactsCheckBox.l = audioCheckBox.l + audioCheckBox.imgH + 5;
      visibleOfflineContactsCheckBox.text = "Показывать Offline контакты";
      visibleOfflineContactsCheckBox.isChecked = Messenger.cmdListener2Arr[Messenger.b].n;
      viewArr[2] = visibleOfflineContactsCheckBox;
      
      
      Button saveButton = new Button();
      saveButton.k = 2;
      saveButton.l = visibleOfflineContactsCheckBox.l + visibleOfflineContactsCheckBox.imgH + 5;
      saveButton.text = "Сохранить";
      saveButton.image = null;
      saveButton.biteArr1 = new byte[]{0, 1, 0, 0, 0, 0};;
      saveButton.a();
      viewArr[3] = saveButton;
      
      
      Core1.c();
      BrowserForm.bool2 = false;
      BrowserForm.d = 0;
      BrowserForm.p = 0;
      BrowserForm.q = visibleOfflineContactsCheckBox.l + visibleOfflineContactsCheckBox.imgH + 5;
      BrowserForm.a(viewArr);
      BrowserForm.bgColor = 0xFFFFFF;
      BrowserForm.o = 0;
      BrowserForm.bool1 = false;
      BrowserForm.prepareRender = true;
      b = true;
      a = true;
   }

   public static void renderMessagerSettingsScreen2() {
      ViewElement[] viewArr = new ViewElement[3];
      
      TextView var1 = new TextView();
      var1.k = 2;
      var1.l = 5;
      var1.text = "Ваш текущий статус:";
      var1.imgW = StaticData.screenWidth - 4 - UiUtil.img4.getWidth();
      var1.style = 0;
      var1.image = null;
      Core5.addGuiAbsToVector2((ViewElement)var1);
      Core5.b();
      viewArr[0] = var1;
      
      
      SelectableFormView var2 = new SelectableFormView();
      var2.k = 2;
      var2.l = var2.l + var2.imgH + 5;
      var2.text = "Вибрация";
      var2.vars = CommandListener3.messagerStatusArr;
      var2.text = CommandListener3.messagerStatusArr[Messenger.cmdListener2Arr[Messenger.b].i];
      viewArr[1] = var2;
      
      
      Button var3 = new Button();
      var3.k = 2;
      var3.l = var2.l + var2.imgH + 5;
      var3.text = "Изменить";
      var3.image = null;
      byte[] var4 = new byte[]{0, 107, 0, 0, 0, 0};
      var3.biteArr1 = var4;
      var3.a();
      viewArr[2] = var3;
      
      
      Core1.c();
      BrowserForm.bool2 = false;
      BrowserForm.d = 0;
      BrowserForm.p = 0;
      BrowserForm.q = var3.l + var3.imgH + 5;
      BrowserForm.a(viewArr);
      BrowserForm.bgColor = 0xFFFFFF;
      BrowserForm.o = 1;
      BrowserForm.prepareRender = true;
      b = true;
      a = true;
   }

   private static void c(Graphics g) {
      if (UiUtil.formatedImgToolbar != null && Messenger.b != -1) {
         g.drawImage(UiUtil.formatedImgToolbar, 0, 0, 0);
         g.drawImage(UiUtil.imgFormatedMenuBack, 0, StaticData.screenHeight - UiUtil.imgFormatedMenuBack.getHeight(), 0);
         String var1 = "ICQ Login";
         if (Messenger.cmdListener2Arr[Messenger.b] != null) {
            var1 = Messenger.cmdListener2Arr[Messenger.b].m();
            StringUtils.a(g, StringUtils.a * 2 + StringUtils.a((String)var1, 9), (17 - UiUtil.allFontImagesHeight[4]) / 2, (String)Messenger.cmdListener2Arr[Messenger.b].h, 4);
         }

         StringUtils.a(g, StringUtils.a, (17 - UiUtil.allFontImagesHeight[9]) / 2, (String)var1, 9);
         String leftNav = "Меню";
         String rightNav = "Назад";
         if (Messenger.cmdListener2Arr[Messenger.b] != null && !a && Messenger.cmdListener2Arr[Messenger.b].h()) {
            rightNav = "Отмена";
         }

         if (Messenger.cmdListener2Arr[Messenger.b] != null && !a) {
            StringUtils.a(g, StringUtils.a, StaticData.screenHeight - 17 + (17 - UiUtil.allFontImagesHeight[9]) / 2, (String)leftNav, 9);
         }

         if (a) {
            rightNav = "Отмена";
         }

         int var4 = StringUtils.a((String)rightNav, 9);
         StringUtils.a(g, StaticData.screenWidth - var4 - StringUtils.a, StaticData.screenHeight - 17 + (17 - UiUtil.allFontImagesHeight[9]) / 2, (String)rightNav, 9);
         String var5 = ScreenCanvas.str1;
         int colorNetworkState;
         if (c) {
            a(g);
         } else {
            colorNetworkState = StringUtils.a((String)var5, 9);
            StringUtils.a(g, (StaticData.screenWidth - colorNetworkState) / 2, StaticData.screenHeight - 17 + (17 - UiUtil.allFontImagesHeight[9]) / 2, (String)var5, 9);
         }

         colorNetworkState = NetworkUtil.isRunning ? '\uff00' : 0xFF0000;
         g.setColor(colorNetworkState); // красный если сеть не активна
         g.fillRect(StaticData.screenWidth / 2 - 1, 0, 2, UiUtil.formatedImgToolbar.getHeight());
      }

   }

   public static void a(Graphics g) {
      if (c && d) {
         int var1 = CommandListener2.a[0].getWidth();
         int var2 = CommandListener2.a[0].getHeight();
         int var3 = 0;

         for(int i = StaticData.j.length - 1; i > -1; --i) {
            if (StaticData.j[i] != -1) {
               byte var5 = Messenger.a(StaticData.j[i]);
               if (Messenger.cmdListener2Arr[var5].v > 0) {
                  g.drawImage(Messenger.cmdListener2Arr[var5].f, StaticData.screenWidth / 2 - var1 / 2 + var3, StaticData.screenHeight - UiUtil.imgFormatedMenuBack.getHeight() / 2 - var2 / 2, 0);
                  var3 -= 5;
               }
            }
         }
      }

   }

   public static void setGraphics(Graphics g) {
      if (b) {
         Messenger.a(g);
         c(g);
         b = false;
      }

   }
}
