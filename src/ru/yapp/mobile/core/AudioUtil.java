package ru.yapp.mobile.core;

import java.io.InputStream;
import javax.microedition.media.Manager;
import javax.microedition.media.Player;

public final class AudioUtil {
    private Player[] playerArr = new Player[1];
    private int b = -1;

    public final void init() {
        for (int i = 0; i < this.playerArr.length; ++i) {
            try {
                InputStream is = this.getClass().getResourceAsStream("/s" + i + ".mid");
                if (is != null) {
                    this.playerArr[i] = Manager.createPlayer(is, "audio/midi");
                }
            } catch (Exception e) {
                e.printStackTrace();
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

            InputStream is = this.getClass().getResourceAsStream("/messager/sound/s" + var1 + ".mid");
            if (is != null) {
                this.playerArr[var1] = Manager.createPlayer(is, "audio/midi");
                this.playerArr[var1].realize();
                this.playerArr[var1].prefetch();
                this.playerArr[var1].start();
                this.b = var1;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
