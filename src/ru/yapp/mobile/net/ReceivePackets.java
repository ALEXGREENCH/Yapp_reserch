package ru.yapp.mobile.net;

import java.io.DataInputStream;
import java.io.IOException;
import ru.yapp.mobile.ScreenCanvas;
import ru.yapp.mobile.Yapp;
import ru.yapp.mobile.book.Book;
import ru.yapp.mobile.browser.BrowserForm;
import ru.yapp.mobile.core.Core3;

public final class ReceivePackets implements Runnable {
   public static boolean a = false;
   public static boolean socketStoped = false;
   private static boolean initObject = false;
   private static DataInputStream dataInputStream;

   public static void addDIS(DataInputStream dis) {
      if (dataInputStream != null) {
         try {
            dataInputStream.close();
         } catch (IOException e) {
            e.printStackTrace();
         }
      }

      dataInputStream = dis;
   }

   public ReceivePackets() {
      initObject = true;
      socketStoped = false;
      (new Thread(this)).start();
   }

   public final void run() {
      while(initObject) {
         if (a && dataInputStream == null) {
            SocketConnector.connect();
            a = false;
         }

         if (dataInputStream != null) {
            try {
               receiveCommand();
            } catch (IOException ioe) {
               socketStoped = true;
               ScreenCanvas.boolean2 = true;
               SocketConnector.isRunning = false;
            }
         }

         if (socketStoped) {
            SocketConnector.reconnect();
         }

         try {
            Thread.sleep(1000L);
         } catch (InterruptedException e) {
            e.printStackTrace();
         }
      }

   }

   private static void receiveCommand() throws IOException {
      short commandByShort = dataInputStream.readShort();
      Yapp.Log("RECEIVE COMMAND->" + commandByShort);
      if (commandByShort > 0) {
         int var1 = dataInputStream.readInt();
         byte[] var2;
         int var3;
         int var4;
         int var5;
         int var10;
         if (commandByShort == 66) {
            var2 = new byte[var1];
            if ((var3 = var1 / 1024) == 0) {
               var3 = 1;
            }

            if (BrowserForm.bool1 && Core3.a == 0) {
               BrowserForm.a = "0/" + var3 + "кб";
               BrowserForm.prepareRender = true;
            }

            var4 = 0;
            boolean var9 = false;

            while(var4 < var1) {
               var10 = 1024;
               if (var1 - var4 < 1024) {
                  var10 = var1 - var4;
               }

               var5 = dataInputStream.read(var2, var4, var10);
               var4 += var5;
               if (BrowserForm.bool1 && Core3.a == 0) {
                  BrowserForm.a = var4 / 1024 + "/" + var3 + "кб";
                  BrowserForm.prepareRender = true;
               }
            }

            if (BrowserForm.bool1 && Core3.a == 0) {
               BrowserForm.a = var4 / 1024 + "/" + var3 + "кб";
               BrowserForm.prepareRender = true;
            }

            Core3.parsing(commandByShort, var2);
            return;
         }

         if (commandByShort == 704) {
            Yapp.Log("SYSTEM->" + dataInputStream.readUTF());
            var3 = dataInputStream.readInt();
            Yapp.Log("BOOK SIZE->" + var3);
            var2 = new byte[var3];
            if ((var4 = var3 / 1024) == 0) {
               var4 = 1;
            }

            Book.b = "0/" + var4 + "кб";
            Book.a = true;
            var5 = 0;

            for(boolean var6 = false; var5 < var3; Book.a = true) {
               int var7 = 1024;
               if (var3 - var5 < 1024) {
                  var7 = var3 - var5;
               }

               var10 = dataInputStream.read(var2, var5, var7);
               var5 += var10;
               Book.b = var5 / 1024 + "/" + var4 + "кб";
            }

            Book.b = var5 / 1024 + "/" + var4 + "кб";

            try {
               Book.bookLoaded(var2);
            } catch (Exception var8) {
               var8.printStackTrace();
            }

            Book.b = "";
            Book.a = true;
            return;
         }

         var2 = new byte[var1];
         dataInputStream.readFully(var2);
         Core3.parsing(commandByShort, var2);
      }
   }

   public static void close() {
      try {
         if (dataInputStream != null) {
            dataInputStream.close();
         }
      } catch (IOException e) {
         e.printStackTrace();
      }

      dataInputStream = null;
   }
}
