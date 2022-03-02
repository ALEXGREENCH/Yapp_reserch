package ru.yapp.mobile.messenger;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import javax.microedition.lcdui.Graphics;
import ru.yapp.mobile.ScreenCanvas;
import ru.yapp.mobile.Yapp;
import ru.yapp.mobile.browser.BrowserRender;
import ru.yapp.mobile.browser.BrowserForm;
import ru.yapp.mobile.core.BytesContentFactory;
import ru.yapp.mobile.core.ResUI;
import ru.yapp.mobile.core.StaticData;
import ru.yapp.mobile.net.SendPackets;

public final class Messenger1 {
   public static CommandListener2[] a = new CommandListener2[1];
   public static byte b = -1;

   private static int a(String var0, byte var1) {
      for(byte var2 = 0; var2 < a.length - 1; ++var2) {
         if (a[var2] == null) {
            a[var2] = new CommandListener2(var0, var1);
            return var2;
         }
      }

      a[a.length - 1] = new CommandListener2(var0, var1);
      CommandListener2[] var3 = new CommandListener2[a.length + 1];
      System.arraycopy(a, 0, var3, 0, a.length);
      a = null;
      a = var3;
      return a.length - 2;
   }

   public static void a() {
      byte var0;
      if ((var0 = a((byte)1)) != -1) {
         a[var0] = null;
      }

   }

   public static byte a(byte var0) {
      for(byte var2 = 0; var2 < a.length - 1; ++var2) {
         if (a[var2] != null && var0 == a[var2].l()) {
            return var2;
         }
      }

      return -1;
   }

   public static void b() {
      if (a[b] != null) {
         a[b].i();
      }

   }

   public static void c() {
      if (a[b] != null) {
         a[b].j();
      }

   }

   public static void d() {
      if (a[b] != null) {
         if (!a[b].h()) {
            a[b].e();
            return;
         }

         a[b].b(a[b].n());
      }

   }

   public static void e() {
      if (a[b] != null) {
         if (a[b].h()) {
            a[b].e();
            return;
         }

         if (Messenger2.a) {
            Messenger2.a = false;
            return;
         }

         --StaticData.g;
         if (StaticData.g >= 1) {
            return;
         }
      }

      BrowserRender.writeToForm();
      ScreenCanvas.screenMode = 1;
      BrowserForm.prepareRender = true;
   }

   public static void f() {
      if (a[b] != null) {
         if (!a[b].h()) {
            a[b].b();
            return;
         }

         a[b].b(a[b].n());
      }

   }

   public static void g() {
      if (StaticData.g == 2) {
         a[b].d();
      } else {
         ++b;
         if (b > 1) {
            b = 0;
         }

         if (a[b] == null) {
            BrowserRender.readInForm();
            Messenger2.a();
         } else {
            BrowserRender.writeToForm();
         }
      }
   }

   public static void h() {
      if (StaticData.g == 2) {
         a[b].c();
      } else {
         --b;
         if (b < 0) {
            b = 1;
         }

         if (a[b] == null) {
            BrowserRender.readInForm();
            Messenger2.a();
         } else {
            BrowserRender.writeToForm();
         }
      }
   }

   public static void a(String var0, String var1) {
      BytesContentFactory bcf = new BytesContentFactory();
      bcf.setType((short)104);
      bcf.addByte(a[b].l());
      bcf.addUtfString(var0);
      bcf.addUtfString(var1);
      SendPackets.addByteArrData(bcf.bytesArray());
   }

   public static void a(byte[] var0) {
      ByteArrayInputStream var1 = new ByteArrayInputStream(var0);

      try {
         DataInputStream var2;
         (var2 = new DataInputStream(var1)).readByte();
         String var4;
         if ((var4 = var2.readUTF()).length() > 0) {
            ScreenCanvas.textDialog = var4;
            ScreenCanvas.boolean2 = true;
         }
      } catch (Exception e) {
         e.printStackTrace();
      }

      Messenger2.b = true;
   }

   public static void b(byte var0) {
      BytesContentFactory bcf = new BytesContentFactory();
      bcf.setType((short)103);
      bcf.addByte(var0);
      SendPackets.addByteArrData(bcf.bytesArray());
   }

   public static void b(byte[] data) {
      ByteArrayInputStream bais = new ByteArrayInputStream(data);

      try {
         DataInputStream var3 = new DataInputStream(bais);
         byte var2 = var3.readByte();
         byte var4 = a(var2);
         String var5 = var3.readUTF();
         if (var5.equals("system")) {
            return;
         }

         if (!var5.equals("")) {
            ScreenCanvas.textDialog = var5;
            ScreenCanvas.boolean2 = true;
            return;
         }

         if (var4 == -1) {
            var4 = (byte)a(StaticData.messagerType[var2], var2);
            if (StaticData.messagerType[var4].equals("Yapp!")) {
               a[var4].h = Integer.toString(StaticData.b);
            } else if (StaticData.messagerType[var2].equals("ICQ")) {
               a[var4].h = StaticData.icqLogin;
            }

            b = var4;
         }

         short var6 = var3.readShort();
         boolean var7 = true;

         int var8;
         String var9;
         for(var8 = 0; var8 < var6; ++var8) {
            var9 = var3.readUTF();
            int var10 = var3.readInt();
            a[var4].a(var9, var10, false, (byte)1, var7);
            var7 = !var7;
         }

         var6 = var3.readShort();

         for(var8 = 0; var8 < var6; ++var8) {
            var9 = var3.readUTF();
            String var16 = var3.readUTF();
            byte var11 = var3.readByte();
            int var12 = var3.readInt();

            for(int var13 = 0; var13 < a[var4].k().length - 1; ++var13) {
               if (a[var4].k()[var13].e() == var12) {
                  a[var4].k()[var13].a(var16, var9, var11, var12);
               }
            }
         }

         a[var4].x = 5;
         if (var3.readBoolean()) {
            byte[] var17 = new byte[var3.readInt()];
            var3.readFully(var17);
            if (a[var4].w != null) {
               if (a[var4].w.length > 0) {
                  e(var17);
               } else {
                  a[var4].w = var17;
               }
            } else {
               a[var4].w = var17;
            }
         }

         StaticData.g = 1;
      } catch (Exception e) {
         e.printStackTrace();
      }

      Messenger2.b = true;
   }

   public static void c(byte[] data) {
      ByteArrayInputStream bais = new ByteArrayInputStream(data);

      try {
         DataInputStream var2;
         byte var4 = a((var2 = new DataInputStream(bais)).readByte());
         String var5 = var2.readUTF();
         String var6 = var2.readUTF();
         int var7 = var2.readInt();
         byte var8 = var2.readByte();
         String var9;
         if ((var9 = var2.readUTF()).equals("")) {
            if (a[var4].b(var5) == null) {
               a[var4].d(var7).a(var6, var5, var8, var7);
            } else {
               a[var4].a(var5);
               a[var4].d(var7).a(var6, var5, var8, var7);
            }
         } else {
            ScreenCanvas.textDialog = var9;
            ScreenCanvas.boolean2 = true;
         }
      } catch (Exception e) {
         e.printStackTrace();
      }

      Messenger2.b = true;
   }

   public static void d(byte[] var0) {
      ByteArrayInputStream var1 = new ByteArrayInputStream(var0);

      try {
         DataInputStream var2;
         byte var4 = a((var2 = new DataInputStream(var1)).readByte());
         int var5 = var2.readInt();
         String var6 = var2.readUTF();
         String var7;
         if ((var7 = var2.readUTF()).equals("")) {
            a[var4].a(var6, var5, false, (byte)1, false);
         } else {
            ScreenCanvas.textDialog = var7;
            ScreenCanvas.boolean2 = true;
         }
      } catch (Exception var8) {
         var8.printStackTrace();
      }

      Messenger2.b = true;
   }

   public static void e(byte[] var0) {
      ByteArrayInputStream var1 = new ByteArrayInputStream(var0);

      try {
         DataInputStream var2;
         byte var3 = (var2 = new DataInputStream(var1)).readByte();
         short var4 = var2.readShort();

         for(int var5 = 0; var5 < var4; ++var5) {
            String var6 = var2.readUTF();
            String var7 = var2.readUTF();
            long var8 = var2.readLong();
            byte var10 = a(var3);
            if (a[var10] != null) {
               CommandListener4[] var11 = a[var10].k();
               boolean var12 = false;

               for(int var13 = 0; var13 < var11.length - 1 && !var12; ++var13) {
                  CommandListener3[] var14 = var11[var13].a();

                  for(int var15 = 0; var15 < var14.length - 1; ++var15) {
                     if (var14[var15].f().equals(var6)) {
                        a[var10].k()[var13].a()[var15].a(var7, false, false, var8);
                        ++a[var10].v;
                        Messenger2.c = true;
                        StaticData.j = d(var3);
                        var12 = true;
                        break;
                     }
                  }
               }

               if (!var12) {
                  if (a[var10].d(999999999) == null) {
                     a[var10].a("Не в списке", 999999999, false, (byte)1, true);
                  }

                  a[var10].d(999999999).a(var6, var6, (byte)13, 999999999);
                  a[var10].d(999999999).b(var6).a(var7, false, false, var8);
                  ++a[var10].v;
                  Messenger2.c = true;
                  StaticData.j = d(var3);
               }

               if (a[var10].l) {
                  Yapp.display.vibrate(250);
               }

               if (a[var10].m) {
                  Yapp.audio.a(0);
               }
            }
         }
      } catch (Exception e) {
         e.printStackTrace();
      }

      Messenger2.b = true;
   }

   private static byte[] d(byte var0) {
      byte[] var1 = StaticData.j;
      byte var2 = var0;

      for(byte var3 = 0; var3 < var1.length; ++var3) {
         if (var2 == var1[var3]) {
            return var1;
         }

         byte var4 = StaticData.j[var3];
         var1[var3] = var2;
         var2 = var4;
      }

      return var1;
   }

   public static void f(byte[] data) {
      ByteArrayInputStream bais = new ByteArrayInputStream(data);

      try {
         DataInputStream var2;
         byte var4;
         if ((var4 = a((var2 = new DataInputStream(bais)).readByte())) == -1) {
            return;
         }

         String var5 = var2.readUTF();
         if (a[var4].b(var5) != null) {
            a[var4].b(var5).f = var2.readByte();
         }
      } catch (Exception var6) {
         var6.printStackTrace();
      }

      Messenger2.b = true;
   }

   public static void c(byte var0) {
      BytesContentFactory var1;
      (var1 = new BytesContentFactory()).setType((short)107);
      var1.addByte(b);
      var1.addByte(var0);
      SendPackets.addByteArrData(var1.bytesArray());
   }

   public static void g(byte[] var0) {
      ByteArrayInputStream bais = new ByteArrayInputStream(var0);

      try {
         DataInputStream var2;
         byte var4 = a((var2 = new DataInputStream(bais)).readByte());
         a[var4].i = var2.readByte();
      } catch (Exception e) {
         e.printStackTrace();
      }

      Messenger2.b = true;
   }

   public static void h(byte[] var0) {
      ByteArrayInputStream var1 = new ByteArrayInputStream(var0);

      try {
         DataInputStream var2;
         byte var4 = a((var2 = new DataInputStream(var1)).readByte());
         String var5 = var2.readUTF();
         String var6 = var2.readUTF();
         String var7;
         if ((var7 = var2.readUTF()).equals("")) {
            a[var4].b(var5).a(var6);
         } else {
            ScreenCanvas.textDialog = var7;
            ScreenCanvas.boolean2 = true;
         }
      } catch (Exception e) {
         e.printStackTrace();
      }

      Messenger2.b = true;
   }

   public static void i(byte[] var0) {
      ByteArrayInputStream var1 = new ByteArrayInputStream(var0);

      try {
         DataInputStream var2;
         byte var4 = a((var2 = new DataInputStream(var1)).readByte());
         int var5 = var2.readInt();
         String var6 = var2.readUTF();
         String var7;
         if ((var7 = var2.readUTF()).equals("")) {
            a[var4].d(var5).c(var6);
         } else {
            ScreenCanvas.textDialog = var7;
            ScreenCanvas.boolean2 = true;
         }
      } catch (Exception var8) {
         var8.printStackTrace();
      }

      Messenger2.b = true;
   }

   public static void a(CommandListener3 var0, String var1) {
      BytesContentFactory var2;
      (var2 = new BytesContentFactory()).setType((short)109);
      var2.addByte(a[b].l());
      var2.addUtfString(var0.f());
      var2.addUtfString(var1);
      SendPackets.addByteArrData(var2.bytesArray());
   }

   public static void a(CommandListener4 var0, String var1) {
      BytesContentFactory var2;
      (var2 = new BytesContentFactory()).setType((short)112);
      var2.addByte(a[b].l());
      var2.addInt(var0.e());
      var2.addUtfString(var1);
      SendPackets.addByteArrData(var2.bytesArray());
   }

   public static void a(String var0, int var1) {
      BytesContentFactory var2;
      (var2 = new BytesContentFactory()).setType((short)105);
      var2.addByte(b);
      var2.addUtfString(var0);
      var2.addInt(var1);
      SendPackets.addByteArrData(var2.bytesArray());
   }

   public static void a(String var0) {
      BytesContentFactory var1;
      (var1 = new BytesContentFactory()).setType((short)110);
      var1.addByte(b);
      var1.addUtfString(var0);
      SendPackets.addByteArrData(var1.bytesArray());
   }

   public static void a(byte var0, String var1, String var2) {
      BytesContentFactory bcf = new BytesContentFactory();
      bcf.setType((short)101);
      bcf.addByte(var0);
      bcf.addUtfString(var1);
      bcf.addUtfString(var2);
      SendPackets.addByteArrData(bcf.bytesArray());
   }

   public static void j(byte[] var0) {
      ByteArrayInputStream bais = new ByteArrayInputStream(var0);

      try {
         DataInputStream var2;
         byte var3 = (var2 = new DataInputStream(bais)).readByte();
         String var4;
         if ((var4 = var2.readUTF()).equals("")) {
            b(var3);
         } else {
            ScreenCanvas.textDialog = var4;
            ScreenCanvas.boolean2 = true;
         }
      } catch (Exception e) {
         e.printStackTrace();
      }

      ScreenCanvas.screenMode = 2;
      ScreenCanvas.boolean2 = true;
   }

   public static void b(String var0, int var1) {
      BytesContentFactory var2 = new BytesContentFactory();
      var2.setType((short)108);
      var2.addByte(a[b].l());
      var2.addUtfString(var0);
      var2.addInt(var1);
      SendPackets.addByteArrData(var2.bytesArray());
   }

   public static void k(byte[] var0) {
      ByteArrayInputStream bais = new ByteArrayInputStream(var0);

      try {
         DataInputStream var2;
         byte var4 = a((var2 = new DataInputStream(bais)).readByte());
         String var5 = var2.readUTF();
         int var6 = var2.readInt();
         String var7;
         if ((var7 = var2.readUTF()).equals("")) {
            a[var4].a(var5, var6);
         } else {
            ScreenCanvas.textDialog = var7;
            ScreenCanvas.boolean2 = true;
         }
      } catch (Exception e) {
         e.printStackTrace();
      }

      Messenger2.b = true;
   }

   public static void b(String var0) {
      BytesContentFactory var1 = new BytesContentFactory();
      var1.setType((short)106);
      var1.addByte(a[b].l());
      var1.addUtfString(var0);
      SendPackets.addByteArrData(var1.bytesArray());
   }

   public static void l(byte[] var0) {
      ByteArrayInputStream bais = new ByteArrayInputStream(var0);

      try {
         DataInputStream var2;
         byte var4 = a((var2 = new DataInputStream(bais)).readByte());
         String var5 = var2.readUTF();
         String var6;
         if ((var6 = var2.readUTF()).equals("")) {
            a[var4].a(var5);
         } else {
            ScreenCanvas.textDialog = var6;
            ScreenCanvas.boolean2 = true;
         }
      } catch (Exception e) {
         e.printStackTrace();
      }

      Messenger2.b = true;
   }

   public static void a(String var0, int var1, byte var2) {
      BytesContentFactory bcf = new BytesContentFactory();
      bcf.setType((short)111);
      bcf.addByte(b);
      bcf.addInt(a[b].c(var0));
      bcf.addInt(var1);
      bcf.addByte(var2);
      SendPackets.addByteArrData(bcf.bytesArray());
   }

   public static void m(byte[] var0) {
      ByteArrayInputStream bais = new ByteArrayInputStream(var0);

      try {
         DataInputStream var2;
         byte var4 = a((var2 = new DataInputStream(bais)).readByte());
         int var5 = var2.readInt();
         int var6 = var2.readInt();
         String var7;
         if ((var7 = var2.readUTF()).equals("")) {
            int var8;
            CommandListener3[] var10;
            int var11;
            if (var6 != -1) {
               var8 = a[var4].d(var5).a().length - 1;
               var10 = a[var4].d(var5).a();

               for(var11 = 0; var11 < var8; ++var11) {
                  a[var4].a(var10[var11].f(), var6);
               }
            } else {
               var8 = a[var4].d(var5).a().length - 1;
               var10 = a[var4].d(var5).a();

               for(var11 = 0; var11 < var8; ++var11) {
                  a[var4].a(var10[var11].f());
               }

               a[var4].d(var5).b = -1;
            }

            if (var5 != 0 && var5 != 999999999) {
               a[var4].a(var5);
            }
         } else {
            ScreenCanvas.textDialog = var7;
            ScreenCanvas.boolean2 = true;
         }
      } catch (Exception e) {
         e.printStackTrace();
      }

      Messenger2.b = true;
   }

   public static void a(Graphics g) {
      if (ResUI.formatedImgToolbar != null) {
         g.setColor(16777215);
         g.fillRect(
                 0,
                 ResUI.formatedImgToolbar.getHeight(),
                 StaticData.screenWidth,
                 StaticData.screenHeight - ResUI.formatedImgToolbar.getHeight() - ResUI.imgFormatedMenuBack.getHeight()
         );
      }

      if (b != -1 && a[b] != null && !Messenger2.a) {
         a[b].a(g);
      } else {
         if (b != -1 || Messenger2.a) {
            BrowserForm.prepareRender = true;
            BrowserForm.setGraphics(g);
         }
      }
   }
}
