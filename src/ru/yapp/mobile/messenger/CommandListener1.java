package ru.yapp.mobile.messenger;

import javax.microedition.lcdui.Alert;
import javax.microedition.lcdui.AlertType;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;
import javax.microedition.lcdui.TextBox;
import ru.yapp.mobile.Yapp;
import ru.yapp.mobile.core.Core9;
import ru.yapp.mobile.core.ResUI;

public final class CommandListener1 implements CommandListener {
   private Command e;
   private Command f;
   static TextBox a;
   public int b;
   public String c;
   private static String[] g = new String[]{"Написать", "В начало", "В конец", "Очистить сообщения"};
   private static int[] h = new int[]{1, 18, 19, 20};
   public CommandListener3 d;

   public static String[] a() {
      return g;
   }

   public static int[] b() {
      return h;
   }

   public static void a(CommandListener3 var0) {
      var0.h = 0;
   }

   public final void c() {
      Messenger1.a[Messenger1.b].b(this.d.f()).h = this.d.g.length - 2;
   }

   public static void b(CommandListener3 var0) {
      if (var0.g != null) {
         var0.g[var0.h].a = 0;
         --var0.h;
         if (var0.h < 0) {
            var0.h = 0;
         }
      }

   }

   public static void c(CommandListener3 var0) {
      if (var0.g != null) {
         var0.g[var0.h].a = 0;
         ++var0.h;
         if (var0.h > var0.g.length - 2) {
            var0.h = var0.g.length - 2;
         }
      }

   }

   public CommandListener1(CommandListener3 var1) {
      new Command("Смайлики", 7, 0);
      this.e = new Command("Отправить", 4, 0);
      this.f = new Command("Назад", 2, 0);
      this.c = "";
      this.d = var1;
      ResUI.formatedImgToolbar.getHeight();
      ResUI.imgFormatedMenuBack.getHeight();
   }

   public static void d(CommandListener3 var0) {
      if (var0.g != null) {
         --var0.g[var0.h].a;
         if (var0.g[var0.h].a < 0) {
            int var1 = var0.h--;
            if (var0.h < 0) {
               var0.h = 0;
               var0.g[var0.h].a = 0;
               return;
            }

            var0.g[var1].a = 0;
            var0.g[var0.h].a = var0.g[var0.h].b.length - 1;
         }
      }

   }

   public static void e(CommandListener3 var0) {
      if (var0.g != null) {
         ++var0.g[var0.h].a;
         if (var0.g[var0.h].a > var0.g[var0.h].b.length - 1) {
            int var1 = var0.h++;
            if (var0.h > var0.g.length - 2) {
               var0.h = var0.g.length - 2;
               var0.g[var0.h].a = var0.g[var0.h].b.length - 1;
               return;
            }

            var0.g[var1].a = 0;
         }
      }

   }

   public final void a(Graphics var1, CommandListener3 var2) {
      byte var3 = ru.yapp.mobile.messenger.CommandListener3.a(var2.f);
      int var4 = Messenger1.a[Messenger1.b].a();
      int var5 = ResUI.formatedImgToolbar.getHeight();
      int var6 = ResUI.imgFormatedMenuBack.getHeight();
      var1.setColor(16777215);
      var1.fillRect(0, var5, ru.yapp.mobile.core.StaticData.screenWidth, ru.yapp.mobile.core.StaticData.screenHeight - var5 - var6);
      boolean var7 = false;
      var1.drawImage(ru.yapp.mobile.messenger.CommandListener2.c[var3], 5, var5, 0);
      int var14 = 5 + ru.yapp.mobile.messenger.CommandListener2.c[var3].getWidth() + 5;
      int var8 = Core9.a((String)var2.g(), 5);
      this.b = var5 + ru.yapp.mobile.messenger.CommandListener2.c[var3].getHeight() / 2 - ResUI.allFontImagesHeight[5] / 2;
      Core9.a(var1, var14, this.b, (String)var2.g(), 5);
      this.b = var5 + ru.yapp.mobile.messenger.CommandListener2.c[var3].getHeight() / 2 - ResUI.allFontImagesHeight[0] / 2;
      Core9.a(var1, var14 + var8 + 5, this.b, (String)ru.yapp.mobile.messenger.CommandListener3.a[var2.f], 0);
      this.b = var5 + ru.yapp.mobile.messenger.CommandListener2.a[var3].getHeight();
      if (var2.g != null) {
         for(int var9 = var2.h; var9 < var2.g.length - 1; ++var9) {
            if (this.b <= ru.yapp.mobile.core.StaticData.screenHeight - var6) {
               int var10 = var2.g[var9].c ? 14803941 : 16777215;
               int var11 = var2.g[var9].c ? var4 + 1 : var4 + 2;
               String var12 = var2.g[var9].e;

               for(int var13 = var2.g[var9].a; var13 < var2.g[var9].b.length; ++var13) {
                  var12 = var13 > 0 ? "" : var12;
                  var1.setColor(var10);
                  var1.fillRect(2, this.b - 1, ru.yapp.mobile.core.StaticData.screenWidth, ResUI.allFontImagesHeight[var4] + 2);
                  Core9.a(var1, 2, this.b, (String)(var12 + var2.g[var9].b[var13]), var11);
                  this.b = this.b + ResUI.allFontImagesHeight[var4] + 2;
                  var12 = "";
               }

               if (!var2.g[var9].d) {
                  var2.g[var9].d = true;
                  --Messenger1.a[Messenger1.b].v;
                  if (Messenger1.a[Messenger1.b].v <= 0) {
                     Messenger1.a[Messenger1.b].v = 0;
                     ru.yapp.mobile.messenger.Messenger2.c = false;
                  }
               }
            }
         }

      }
   }

   public final Displayable a(String var1) {
      this.c = "";
      a = new TextBox("Сообщение", var1, 512, 0);
      a.addCommand(this.e);
      a.addCommand(this.f);
      a.setCommandListener(this);
      return a;
   }

   public final void commandAction(Command var1, Displayable var2) {
      if (var1 == this.e) {
         if (a.size() == 0) {
            Alert var3 = new Alert("Ошибка", "Нельзя отправить пустое сообщение.", (Image)null, AlertType.ERROR);
            Yapp.display.setCurrent(var3);
         } else {
            Messenger1.a(this.d.f(), a.getString());
            Messenger1.a[Messenger1.b].b(this.d.f()).a(a.getString(), true, true, System.currentTimeMillis());
            Yapp.display.setCurrent(ru.yapp.mobile.ScreenCanvas.screenCanvas);
         }
      } else {
         if (var1 == this.f) {
            Yapp.display.setCurrent(ru.yapp.mobile.ScreenCanvas.screenCanvas);
         }

      }
   }
}
