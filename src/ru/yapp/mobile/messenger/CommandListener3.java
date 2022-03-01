package ru.yapp.mobile.messenger;

import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.TextBox;

import ru.yapp.mobile.ScreenCanvas;
import ru.yapp.mobile.Yapp;

public final class CommandListener3 implements CommandListener {
   public static String[] a = new String[]{"Offline (Не в сети)", "Online (В сети)", "Away (Отошел)", "Invisible (Невидимый)", "DND (Не беспокоить)", "NA (Не доступен)", "Occupied (Занят)", "Free For Chat (Готов поболтать)", "Lunch (Кушаю)", "Evil (Злой)", "Depression (Депрессия)", "Home (Дома)", "Work (На работе)", "Not In List (Не в списке)"};
   public static String[] b = new String[]{"Сообщение", "Переименовать", "Информация", "Удалить", "Переместить в группу"};
   public static int[] c = new int[]{1, 2, 3, 4, 5};
   public static String[] d = new String[]{"Сообщение", "Добавить", "Удалить"};
   public static int[] e = new int[]{1, 7, 6};
   private String l;
   private String m;
   public byte f;
   private int n;
   public Messenger3[] g = null;
   public int h = 0;
   public TextBox i;
   public Command j = new Command("Принять", 4, 0);
   public Command k = new Command("Отмена", 3, 0);

   public CommandListener3(String var1, String var2, byte var3, int var4) {
      this.l = var1;
      this.m = var2;
      this.f = var3;
      this.n = var4;
   }

   public final String[] a() {
      return this.n == 999999999 ? d : b;
   }

   public final int[] b() {
      return this.n == 999999999 ? e : c;
   }

   public final void a(String var1, boolean var2, boolean var3, long var4) {
      if (this.g == null) {
         this.g = new Messenger3[1];
      }

      this.g[this.g.length - 1] = new Messenger3(var1, var2, var3, var4);
      Messenger3[] var6 = new Messenger3[this.g.length + 1];
      System.arraycopy(this.g, 0, var6, 0, this.g.length);
      this.g = null;
      this.g = var6;
      this.h = this.g.length - 2;
   }

   public final void c() {
      this.h = 0;
      this.g = null;
   }

   public final int d() {
      if (this.g == null) {
         return 0;
      } else {
         int var1 = 0;

         for(int var2 = 0; var2 < this.g.length - 1; ++var2) {
            if (!this.g[var2].d) {
               ++var1;
            }
         }

         return var1;
      }
   }

   public final int e() {
      return this.n;
   }

   public static byte a(byte var0) {
      if (var0 == 7) {
         return 1;
      } else if (var0 == 11) {
         return 1;
      } else if (var0 == 12) {
         return 1;
      } else if (var0 == 9) {
         return 2;
      } else if (var0 == 10) {
         return 2;
      } else if (var0 == 2) {
         return 2;
      } else if (var0 == 4) {
         return 2;
      } else if (var0 == 5) {
         return 2;
      } else if (var0 == 8) {
         return 2;
      } else if (var0 == 13) {
         return 3;
      } else if (var0 == 6) {
         return 2;
      } else {
         return var0 == 3 ? 0 : var0;
      }
   }

   public final String f() {
      return this.m;
   }

   public final String g() {
      return this.l;
   }

   public final void a(String var1) {
      this.l = var1;
   }

   public final Displayable b(String var1) {
      this.i = new TextBox("Переименовать пользователя", var1, 25, 0);
      this.i.addCommand(this.k);
      this.i.addCommand(this.j);
      this.i.setCommandListener(this);
      return this.i;
   }

   public final void commandAction(Command var1, Displayable var2) {
      if (var1 == this.j) {
         Messenger1.a(this, this.i.getString());
         Yapp.display.setCurrent(ScreenCanvas.screenCanvas);
      } else {
         if (var1 == this.k) {
            Yapp.display.setCurrent(ScreenCanvas.screenCanvas);
         }

      }
   }
}
