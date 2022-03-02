package ru.yapp.mobile.messenger;

import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.TextBox;

import ru.yapp.mobile.ScreenCanvas;
import ru.yapp.mobile.Yapp;

public final class CommandListener3 implements CommandListener {
    
   public static String[] messagerStatusArr = new String[]{"Offline (Не в сети)", "Online (В сети)", "Away (Отошел)", "Invisible (Невидимый)", "DND (Не беспокоить)", "NA (Не доступен)", "Occupied (Занят)", "Free For Chat (Готов поболтать)", "Lunch (Кушаю)", "Evil (Злой)", "Depression (Депрессия)", "Home (Дома)", "Work (На работе)", "Not In List (Не в списке)"};
   public static String[] contactActionArr = new String[]{"Сообщение", "Переименовать", "Информация", "Удалить", "Переместить в группу"};
   public static int[] c = new int[]{1, 2, 3, 4, 5};
   public static String[] contact2ActionArr = new String[]{"Сообщение", "Добавить", "Удалить"};
   public static int[] e = new int[]{1, 7, 6};
   private String l;
   private String uid__;
   public byte f;
   private int n;
   public MessangeData[] messageDataArr = null;
   public int h = 0;
   public TextBox textBox;
   public Command cmdApply = new Command("Принять", 4, 0);
   public Command cmdCancel = new Command("Отмена", 3, 0);

   public CommandListener3(String str, String uid__, byte byte1, int int1) {
      this.l = str;
      this.uid__ = uid__;
      this.f = byte1;
      this.n = int1;
   }

   public final String[] a() {
      return this.n == 999999999 ? contact2ActionArr : contactActionArr;
   }

   public final int[] b() {
      return this.n == 999999999 ? e : c;
   }

   public final void addMessageData(String msg, boolean var2, boolean var3, long timeInMilisec) {
      if (this.messageDataArr == null) {
         this.messageDataArr = new MessangeData[1];
      }

      this.messageDataArr[this.messageDataArr.length - 1] = new MessangeData(msg, var2, var3, timeInMilisec);
      MessangeData[] messegeDataArr = new MessangeData[this.messageDataArr.length + 1];
      System.arraycopy(this.messageDataArr, 0, messegeDataArr, 0, this.messageDataArr.length);
      this.messageDataArr = null;
      this.messageDataArr = messegeDataArr;
      this.h = this.messageDataArr.length - 2;
   }

   public final void c() {
      this.h = 0;
      this.messageDataArr = null;
   }

   public final int d() {
      if (this.messageDataArr == null) {
         return 0;
      } else {
         int var1 = 0;

         for(int i = 0; i < this.messageDataArr.length - 1; ++i) {
            if (!this.messageDataArr[i].d) {
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

   public final String getUid_() {
      return this.uid__;
   }

   public final String g() {
      return this.l;
   }

   public final void a(String str) {
      this.l = str;
   }

   public final Displayable displayableRenameContact(String nameContact) {
      this.textBox = new TextBox("Переименовать пользователя", nameContact, 25, 0);
      this.textBox.addCommand(this.cmdCancel);
      this.textBox.addCommand(this.cmdApply);
      this.textBox.setCommandListener(this);
      return this.textBox;
   }

   public final void commandAction(Command cmd, Displayable disp) {
      if (cmd == this.cmdApply) {
         Messenger.a(this, this.textBox.getString());
         Yapp.display.setCurrent(ScreenCanvas.screenCanvas);
      } else {
         if (cmd == this.cmdCancel) {
            Yapp.display.setCurrent(ScreenCanvas.screenCanvas);
         }

      }
   }
}
