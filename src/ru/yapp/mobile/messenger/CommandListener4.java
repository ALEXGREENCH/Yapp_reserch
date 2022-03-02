package ru.yapp.mobile.messenger;

import javax.microedition.lcdui.Alert;
import javax.microedition.lcdui.AlertType;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Image;
import javax.microedition.lcdui.TextBox;

import ru.yapp.mobile.ScreenCanvas;
import ru.yapp.mobile.Yapp;

public final class CommandListener4 implements CommandListener {
    public boolean a;
    public int b;
    public boolean c;
    public String[] d = new String[]{"Развернуть", "Переименовать", "Удалить"};
    public int[] e = new int[]{8, 9, 10};
    public String[] f = new String[]{"Развернуть", "Очистить группу"};
    public int[] g = new int[]{8, 11};
    public TextBox h;
    public Command i = new Command("Принять", 4, 0);
    public Command j = new Command("Отмена", 3, 0);
    private String k;
    private int l;
    private byte m;
    private CommandListener3[] n = new CommandListener3[1];

    public CommandListener4(String var1, int var2, boolean var3, byte var4, boolean var5) {
        this.k = var1;
        this.l = var2;
        this.a = var3;
        this.m = var4;
        this.b = -1;
        this.c = var5;
    }

    public final void a(String uid__, String str2, byte var3, int var4) {
        this.n[this.n.length - 1] = new CommandListener3(uid__, str2, var3, var4);
        CommandListener3[] var5 = new CommandListener3[this.n.length + 1];
        System.arraycopy(this.n, 0, var5, 0, this.n.length);
        this.n = null;
        this.n = var5;
    }

    public final void a(String var1) {
        CommandListener3[] var2 = new CommandListener3[this.n.length - 1];
        int var3 = 0;

        for (int var4 = 0; var4 < this.a().length - 1; ++var4) {
            if (!this.a()[var4].getUid_().equals(var1)) {
                var2[var3] = this.a()[var4];
                ++var3;
            }
        }

        this.n = null;
        this.n = new CommandListener3[var2.length];
        System.arraycopy(var2, 0, this.n, 0, this.n.length);
    }

    public final CommandListener3[] a() {
        return this.n;
    }

    public final CommandListener3 b(String var1) {
        for (int var2 = 0; var2 < this.n.length - 1; ++var2) {
            if (this.n[var2].getUid_().equals(var1)) {
                return this.n[var2];
            }
        }

        return null;
    }

    public final int b() {
        int var1 = 0;

        for (int var2 = 0; var2 < this.n.length - 1; ++var2) {
            if (this.n[var2].f != 0) {
                ++var1;
            }
        }

        return var1;
    }

    public final void c() {
        if (this.a) {
            this.a = false;
            this.d[0] = "Развернуть";
            this.f[0] = "Развернуть";
            this.m = 1;
        } else {
            this.a = true;
            this.d[0] = "Свернуть";
            this.f[0] = "Свернуть";
            this.m = 2;
        }
    }

    public final String[] d() {
        return this.c ? this.f : this.d;
    }

    public final int e() {
        return this.l;
    }

    public final int f() {
        if (!this.a) {
            for (int var1 = 0; var1 < this.n.length - 1; ++var1) {
                if (this.n[var1].d() > 0) {
                    return 3;
                }
            }
        }

        return this.m;
    }

    public final int[] g() {
        return this.c ? this.g : this.e;
    }

    public final String h() {
        return this.k;
    }

    public final void c(String var1) {
        this.k = var1;
    }

    public final Displayable d(String var1) {
        this.h = new TextBox("Переименовать группу", var1, 25, 0);
        this.h.addCommand(this.j);
        this.h.addCommand(this.i);
        this.h.setCommandListener(this);
        return this.h;
    }

    public final void commandAction(Command var1, Displayable var2) {
        if (var1 == this.i) {
            if (Messenger.cmdListener2Arr[Messenger.b].c(this.h.getString()) == -1) {
                Messenger.a(this, this.h.getString());
                Yapp.display.setCurrent(ScreenCanvas.screenCanvas);
            } else {
                Alert var4 = new Alert("Ошибка", "Такая группа уже существует.", (Image) null, AlertType.ERROR);
                Yapp.display.setCurrent(var4);
            }
        }

        if (var1 == this.j) {
            Yapp.display.setCurrent(ScreenCanvas.screenCanvas);
        }

    }
}
