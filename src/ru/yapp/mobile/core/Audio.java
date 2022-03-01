package ru.yapp.mobile.core;

import java.io.InputStream;
import javax.microedition.media.Manager;
import javax.microedition.media.Player;

public final class Audio {
   private Player[] playerArr = new Player[1];
   private int b = -1;

   public final void init() {
      for(int var1 = 0; var1 < this.playerArr.length; ++var1) {
         try {
            InputStream var2;
            if ((var2 = this.getClass().getResourceAsStream("/s" + var1 + ".mid")) != null) {
               this.playerArr[var1] = Manager.createPlayer(var2, "audio/midi");
            }
         } catch (Exception var3) {
            var3.printStackTrace();
         }
      }

   }

   public final void a(int var1) {
      try {
         if (this.b != -1) {
            this.playerArr[this.b].deallocate();
            this.playerArr[this.b].stop();
            this.playerArr[this.b].realize();
            this.playerArr[this.b] = null;
         }

         InputStream var2;
         if ((var2 = this.getClass().getResourceAsStream("/messager/sound/s" + var1 + ".mid")) != null) {
            this.playerArr[var1] = Manager.createPlayer(var2, "audio/midi");
            this.playerArr[var1].realize();
            this.playerArr[var1].prefetch();
            this.playerArr[var1].start();
            this.b = var1;
         }

      } catch (Exception var3) {
         var3.printStackTrace();
      }
   }
}
