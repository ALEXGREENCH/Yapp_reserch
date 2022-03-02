package ru.yapp.mobile.messenger;

import javax.microedition.lcdui.Alert;
import javax.microedition.lcdui.AlertType;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;
import javax.microedition.lcdui.TextBox;
import ru.yapp.mobile.ScreenCanvas;
import ru.yapp.mobile.Yapp;
import ru.yapp.mobile.core.StaticData;
import ru.yapp.mobile.core.StringUtils;
import ru.yapp.mobile.core.UiUtil;

public final class CommandListener1 implements CommandListener {
   private Command cmd1;
   private Command cmd2;
   static TextBox textBoxMessage;
   public int int1;
   public String str1;
   private static String[] strArr = new String[]{"Написать", "В начало", "В конец", "Очистить сообщения"};
   private static int[] intArr1 = new int[]{1, 18, 19, 20};
   public CommandListener3 cmdListener3;

   public static String[] a() {
      return strArr;
   }

   public static int[] b() {
      return intArr1;
   }

   public static void a(CommandListener3 cmdListener3) {
      cmdListener3.h = 0;
   }

   public final void c() {
      Messenger.cmdListener2Arr[Messenger.b]
            .b(this.cmdListener3.getUid_())
            .h = this.cmdListener3.messageDataArr.length - 2;
   }

   public static void b(CommandListener3 listener) {
      if (listener.messageDataArr != null) {
         listener.messageDataArr[listener.h].a = 0;
         --listener.h;
         if (listener.h < 0) {
            listener.h = 0;
         }
      }

   }

   public static void c(CommandListener3 listener) {
      if (listener.messageDataArr != null) {
         listener.messageDataArr[listener.h].a = 0;
         ++listener.h;
         if (listener.h > listener.messageDataArr.length - 2) {
            listener.h = listener.messageDataArr.length - 2;
         }
      }

   }

   public CommandListener1(CommandListener3 listener) {
      new Command("Смайлики", 7, 0);
      this.cmd1 = new Command("Отправить", 4, 0);
      this.cmd2 = new Command("Назад", 2, 0);
      this.str1 = "";
      this.cmdListener3 = listener;
      UiUtil.formatedImgToolbar.getHeight();
      UiUtil.imgFormatedMenuBack.getHeight();
   }

   public static void d(CommandListener3 listener) {
      if (listener.messageDataArr != null) {
         --listener.messageDataArr[listener.h].a;
         if (listener.messageDataArr[listener.h].a < 0) {
            int var1 = listener.h--;
            if (listener.h < 0) {
               listener.h = 0;
               listener.messageDataArr[listener.h].a = 0;
               return;
            }

            listener.messageDataArr[var1].a = 0;
            listener.messageDataArr[listener.h].a = listener.messageDataArr[listener.h].strArr.length - 1;
         }
      }

   }

   public static void e(CommandListener3 var0) {
      if (var0.messageDataArr != null) {
         ++var0.messageDataArr[var0.h].a;
         if (var0.messageDataArr[var0.h].a > var0.messageDataArr[var0.h].strArr.length - 1) {
            int var1 = var0.h++;
            if (var0.h > var0.messageDataArr.length - 2) {
               var0.h = var0.messageDataArr.length - 2;
               var0.messageDataArr[var0.h].a = var0.messageDataArr[var0.h].strArr.length - 1;
               return;
            }

            var0.messageDataArr[var1].a = 0;
         }
      }

   }

   public final void a(Graphics g, CommandListener3 listener) {
      byte var3 = CommandListener3.a(listener.f);
      int var4 = Messenger.cmdListener2Arr[Messenger.b].a();
      int var5 = UiUtil.formatedImgToolbar.getHeight();
      int var6 = UiUtil.imgFormatedMenuBack.getHeight();
      g.setColor(0xFFFFFF);
      g.fillRect(0, var5, StaticData.screenWidth, StaticData.screenHeight - var5 - var6);
      //boolean var7 = false; // todo...
      g.drawImage(CommandListener2.c[var3], 5, var5, 0);
      int var14 = 5 + CommandListener2.c[var3].getWidth() + 5;
      int var8 = StringUtils.a((String)listener.g(), 5);
      this.int1 = var5 + CommandListener2.c[var3].getHeight() / 2 - UiUtil.allFontImagesHeight[5] / 2;
      StringUtils.a(g, var14, this.int1, (String)listener.g(), 5);
      this.int1 = var5 + CommandListener2.c[var3].getHeight() / 2 - UiUtil.allFontImagesHeight[0] / 2;
      StringUtils.a(g, var14 + var8 + 5, this.int1, (String)CommandListener3.messagerStatusArr[listener.f], 0);
      this.int1 = var5 + CommandListener2.a[var3].getHeight();
      if (listener.messageDataArr != null) {
         for(int i = listener.h; i < listener.messageDataArr.length - 1; ++i) {
            if (this.int1 <= StaticData.screenHeight - var6) {
               int colorStateMessage = listener.messageDataArr[i].c ? 0xE1E3E5 : 0xFFFFFF;
               int var11 = listener.messageDataArr[i].c ? var4 + 1 : var4 + 2;
               String var12 = listener.messageDataArr[i].time;

               for(int j = listener.messageDataArr[i].a; j < listener.messageDataArr[i].strArr.length; ++j) {
                  var12 = j > 0 ? "" : var12;
                  g.setColor(colorStateMessage);
                  g.fillRect(2, this.int1 - 1, StaticData.screenWidth, UiUtil.allFontImagesHeight[var4] + 2);
                  StringUtils.a(g, 2, this.int1, (String)(var12 + listener.messageDataArr[i].strArr[j]), var11);
                  this.int1 = this.int1 + UiUtil.allFontImagesHeight[var4] + 2;
                  var12 = "";
               }

               if (!listener.messageDataArr[i].d) {
                  listener.messageDataArr[i].d = true;
                  --Messenger.cmdListener2Arr[Messenger.b].v;
                  if (Messenger.cmdListener2Arr[Messenger.b].v <= 0) {
                     Messenger.cmdListener2Arr[Messenger.b].v = 0;
                     Messenger2.c = false;
                  }
               }
            }
         }

      }
   }

   public final Displayable displayableTextBoxMessage(String msg) {
      this.str1 = "";
      textBoxMessage = new TextBox("Сообщение", msg, 512, 0);
      textBoxMessage.addCommand(this.cmd1);
      textBoxMessage.addCommand(this.cmd2);
      textBoxMessage.setCommandListener(this);
      return textBoxMessage;
   }

   public final void commandAction(Command cmd, Displayable disp) {
      if (cmd == this.cmd1) {
         if (textBoxMessage.size() == 0) {
            Alert alert = new Alert("Ошибка", "Нельзя отправить пустое сообщение.", (Image)null, AlertType.ERROR);
            Yapp.display.setCurrent(alert);
         } else {
            Messenger.sendMessage(this.cmdListener3.getUid_(), textBoxMessage.getString());
            Messenger.cmdListener2Arr[Messenger.b]
                    .b(this.cmdListener3.getUid_())
                    .addMessageData(
                            textBoxMessage.getString(), 
                            true,
                            true,
                            System.currentTimeMillis()
                    );
            Yapp.display.setCurrent(ScreenCanvas.screenCanvas);
         }
      } else {
         if (cmd == this.cmd2) {
            Yapp.display.setCurrent(ScreenCanvas.screenCanvas);
         }
      }
   }
}
