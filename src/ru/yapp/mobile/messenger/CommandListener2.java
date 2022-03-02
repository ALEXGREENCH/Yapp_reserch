package ru.yapp.mobile.messenger;

import javax.microedition.lcdui.Alert;
import javax.microedition.lcdui.AlertType;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;
import javax.microedition.lcdui.List;
import javax.microedition.lcdui.TextBox;

import ru.yapp.mobile.ScreenCanvas;
import ru.yapp.mobile.Yapp;
import ru.yapp.mobile.browser.BrowserForm;
import ru.yapp.mobile.browser.BrowserRender;
import ru.yapp.mobile.core.UiUtil;
import ru.yapp.mobile.core.StaticData;
import ru.yapp.mobile.core.StringUtils;
import ru.yapp.mobile.core.BdUtil;

public final class CommandListener2 implements CommandListener {
    public static Image[] a;
    public static int b;
    public static Image[] c;
    public static int d;
    public static Image[] e;
    private static int B = 10;

    static {
        a = new Image[B];
        b = 4;
        c = new Image[b];
        d = 3;
        e = new Image[d];
    }

    public Image f = null;
    public String h = "";
    public byte i;
    public int j = 0;
    public CommandListener1 k;
    public boolean l = true;
    public boolean m = false;
    public boolean n = true;
    public TextBox o;
    public Command p = new Command("Принять", 4, 0);
    public Command q = new Command("Принять", 4, 0);
    public Command r = new Command("Отмена", 3, 0);
    public Command s = new Command("Переместить", 4, 0);
    public Command t = new Command("Переместить", 4, 0);
    public Command u = new Command("Добавить", 4, 0);
    public int v = 0;
    public byte[] w;
    public int x = 5;
    boolean g = false;
    int y = 0;
    private String z;
    private byte A;
    private int C = 0;
    private int D = 0;
    private CommandListener4[] E = new CommandListener4[1];
    private int F = 0;
    private String[] G;
    private int[] H;
    private byte I;
    private int J = 0;
    private String[] K = new String[]{"Добавить пользователя", "Создать группу", "Мой статус", "Скрыть offline контакты", "Настройки"};
    private int[] L = new int[]{12, 13, 14, 15, 16};
    private int M = 0;
    private List N;

    public CommandListener2(String var1, byte var2) {
        this.J = StaticData.screenWidth > 220 ? 5 : 0;
        this.z = var1;
        this.A = var2;
        this.F = 0;
        this.i = 1;
        if (BdUtil.read(var1 + "-sound") != null) {
            this.m = BdUtil.read(var1 + "-sound")[0] == 1;
        }

        if (BdUtil.read(var1 + "-vibro") != null) {
            this.l = BdUtil.read(var1 + "-vibro")[0] == 1;
        }

        if (BdUtil.read(var1 + "-hide") != null) {
            this.n = BdUtil.read(var1 + "-hide")[0] == 1;
        }

        try {
            int var3;
            for (var3 = 0; var3 < a.length; ++var3) {
                a[var3] = Image.createImage("/messager/" + var3 + ".png");
            }

            for (var3 = 0; var3 < c.length; ++var3) {
                c[var3] = Image.createImage("/messager/status/" + var3 + ".png");
            }

            for (var3 = 0; var3 < e.length; ++var3) {
                e[var3] = Image.createImage("/messager/adstatus/" + var3 + ".png");
            }

            this.f = Image.createImage("/messager/service/messages/" + var2 + ".png");
            this.C = a[0].getWidth();
            this.D = a[0].getHeight();
        } catch (Exception var4) {
            var4.printStackTrace();
        }
    }

    public final int a() {
        return this.J;
    }

    public final void a(String var1, int var2, boolean var3, byte var4, boolean var5) {
        this.E[this.E.length - 1] = new CommandListener4(var1, var2, var3, var4, var5);
        CommandListener4[] var6 = new CommandListener4[this.E.length + 1];
        System.arraycopy(this.E, 0, var6, 0, this.E.length);
        this.E = null;
        this.E = var6;
    }

    public final void a(String var1) {
        CommandListener3 var2;
        int var3 = (var2 = this.b(var1)).e();
        this.d(var3).a(var2.getUid_());
        --this.E[this.F].b;
        if (this.d(999999999) != null && this.d(999999999).a().length == 1) {
            this.a(999999999);
        }

    }

    public final void a(int var1) {
        CommandListener4[] var2 = new CommandListener4[this.E.length - 1];
        int var3 = 0;

        for (int var4 = 0; var4 < this.E.length - 1; ++var4) {
            if (this.E[var4].e() == var1) {
                this.E[var4].b = -1;
            } else {
                var2[var3] = this.E[var4];
                ++var3;
            }
        }

        this.E = null;
        this.E = new CommandListener4[var2.length];
        System.arraycopy(var2, 0, this.E, 0, this.E.length);
        this.F = 0;
        this.E[this.F].b = -1;
    }

    public final void b() {
        switch (StaticData.g) {
            case 1:
                int var1;
                if ((var1 = this.E[this.F].b) != -1) {
                    this.k = new CommandListener1(this.E[this.F].a()[var1]);
                    ScreenCanvas.boolean2 = true;
                    Messenger2.b = true;
                    StaticData.g = 2;
                    return;
                } else {
                    this.E[this.F].c();
                }
            default:
                return;
            case 2:
                Yapp.display.setCurrent(this.k.displayableTextBoxMessage(this.k.str1));
        }
    }

    public final void c() {
        CommandListener1.b(this.E[this.F].a()[this.E[this.F].b]);
    }

    public final void d() {
        CommandListener1.c(this.E[this.F].a()[this.E[this.F].b]);
    }

    public final void e() {
        this.g = !this.g;
        this.M = 0;
    }

    public final void f() {
        this.m = !this.m;
    }

    public final void g() {
        this.n = !this.n;
        StaticData.g = 1;
        this.E[this.F].b = -1;
        this.F = 0;
        this.j = 0;
    }

    public final boolean h() {
        return this.g;
    }

    public final void i() {
        int var1;
        if (this.g) {
            --this.M;
            var1 = this.G.length + this.K.length;
            if (this.M < 0) {
                this.M = var1 - 1;
            }

        } else {
            switch (StaticData.g) {
                case 1:
                    if (this.E == null) {
                        return;
                    }

                    if (this.y < UiUtil.formatedImgToolbar.getHeight() + this.D) {
                        this.j = this.j + this.D + 2;
                    }

                    int var2;
                    if (this.E[this.F].b != -1) {
                        var1 = --this.E[this.F].b;
                        if (!this.n) {
                            for (var2 = var1; var2 > -1 && this.E[this.F].a()[var2].f == 0; var1 = var2) {
                                --var2;
                            }
                        }

                        this.E[this.F].b = var1;
                        return;
                    }

                    --this.F;
                    if (this.F < 0) {
                        this.F = 0;
                        this.j = 0;
                        return;
                    }

                    if (this.E[this.F].a) {
                        var1 = this.E[this.F].b = this.E[this.F].a().length - 2;
                        if (!this.n) {
                            for (var2 = var1; var2 > -1 && this.E[this.F].a()[var2].f == 0; var1 = var2) {
                                --var2;
                            }
                        }

                        this.E[this.F].b = var1;
                        return;
                    }
                    break;
                case 2:
                    CommandListener1.d(this.E[this.F].a()[this.E[this.F].b]);
            }

        }
    }

    public final void j() {
        int var1;
        if (this.g) {
            ++this.M;
            var1 = this.G.length + this.K.length;
            if (this.M > var1 - 1) {
                this.M = 0;
            }

        } else {
            switch (StaticData.g) {
                case 1:
                    if (this.E == null) {
                        break;
                    }

                    var1 = this.E[this.F].b + 1;
                    if (this.y >= StaticData.screenHeight - UiUtil.imgFormatedMenuBack.getHeight() - UiUtil.formatedImgStatusbar.getHeight() - this.D * 2) {
                        this.j = this.j - this.D - 2;
                    }

                    int var2;
                    if (!this.n) {
                        for (var2 = var1; var2 < this.E[this.F].a().length - 1 && this.E[this.F].a()[var2].f == 0; var1 = var2) {
                            ++var2;
                        }
                    }

                    if (var1 <= this.E[this.F].a().length - 2 && this.E[this.F].a) {
                        this.E[this.F].b = var1;
                        break;
                    }

                    var2 = this.E[this.F].b;
                    this.E[this.F].b = -1;
                    ++this.F;
                    if (this.F > this.E.length - 2) {
                        this.F = this.E.length - 2;
                        if (this.E[this.F].a) {
                            this.E[this.F].b = var2;
                        }
                    }

                    return;
                case 2:
                    CommandListener1.e(this.E[this.F].a()[this.E[this.F].b]);
            }

        }
    }

    private void a(Graphics g, String[] var2, int[] var3) {
        int var4 = var2.length + this.K.length;
        int var5 = var2.length;
        String[] var6 = new String[var4];

        for (int var7 = 0; var7 < var4; ++var7) {
            if (var7 < var5) {
                var6[var7] = var2[var7];
                if (var7 == this.M) {
                    this.I = (byte) var3[var7];
                }
            } else {
                var6[var7] = this.K[var7 - var5];
                if (var7 == this.M) {
                    this.I = (byte) this.L[var7 - var5];
                }
            }
        }

        BrowserForm.a(g, var6, this.M);
    }

    public final void b(int var1) {
        String[] var3;
        switch (var1) {
            case 1:
                this.b();
                break;
            case 2:
                Yapp.display.setCurrent(this.E[this.F].a()[this.E[this.F].b].displayableRenameContact(this.E[this.F].a()[this.E[this.F].b].g()));
                break;
            case 3:
                CommandListener3 var2 = this.k()[this.F].a()[this.k()[this.F].b];
                ScreenCanvas.textDialog = "Пользователь номер " + var2.getUid_();
                break;
            case 4:
                Messenger.b(this.E[this.F].a()[this.E[this.F].b].getUid_());
                break;
            case 5:
                var3 = this.f("");
                Yapp.display.setCurrent(this.a(var3));
                break;
            case 6:
                this.a(this.E[this.F].a()[this.E[this.F].b].getUid_());
                break;
            case 7:
                var3 = this.f(this.E[this.F].h());
                Yapp.display.setCurrent(this.c(var3));
                break;
            case 8:
                this.b();
                break;
            case 9:
                Yapp.display.setCurrent(this.E[this.F].d(this.E[this.F].h()));
                break;
            case 10:
                if (this.E[this.F].a().length > 1) {
                    Yapp.display.setCurrent(this.b(this.f(this.E[this.F].h())));
                } else {
                    Messenger.a(this.E[this.F].h(), -1, (byte) 1);
                }
                break;
            case 11:
                Yapp.display.setCurrent(this.b(this.f(this.E[this.F].h())));
                break;
            case 12:
                Yapp.display.setCurrent(this.e(""));
                break;
            case 13:
                Yapp.display.setCurrent(this.d(""));
                break;
            case 14:
                BrowserRender.readInForm();
                Messenger2.renderMessagerSettingsScreen2();
                break;
            case 15:
                this.g();
                break;
            case 16:
                BrowserRender.readInForm();
                Messenger2.renderMessagerSettingsScreen();
            case 17:
            default:
                break;
            case 18:
                CommandListener1.a(this.E[this.F].a()[this.E[this.F].b]);
                break;
            case 19:
                this.k.c();
                break;
            case 20:
                this.E[this.F].a()[this.E[this.F].b].c();
        }

        this.e();
    }

    public final void c(int var1) {
        boolean var2 = false;
        this.j = 0;
        this.F = 0;
        this.E[this.F].b = 0;

        for (int var3 = 0; var3 < this.E.length - 1 && !var2; ++var3) {
            for (int var4 = 0; var4 < this.E[var3].a().length - 1; ++var4) {
                if (this.E[var3].a()[var4].getUid_().equals(Integer.toString(var1))) {
                    StaticData.g = 1;
                    this.F = var3;
                    this.E[this.F].a = true;
                    this.n = true;
                    this.E[this.F].b = var4;
                    var2 = true;
                    this.k = new CommandListener1(this.E[var3].a()[var4]);
                    break;
                }

                this.j();
            }
        }

        if (!var2) {
            if (this.d(999999999) == null) {
                this.a("Не в списке", 999999999, true, (byte) 2, true);
                this.j();
            }

            this.d(999999999).a(Integer.toString(var1), Integer.toString(var1), (byte) 13, 999999999);
            this.j();
            this.k = new CommandListener1(this.E[this.F].a()[this.E[this.F].b]);
        }

        ScreenCanvas.screenMode = 2;
        Messenger2.b = true;
        StaticData.g = 2;
    }

    public final void a(Graphics g) {
        this.K[3] = this.n ? "Скрыть offline контакты" : "Показать offline контакты";
        switch (StaticData.g) {
            case 1:
                int var3 = UiUtil.formatedImgToolbar.getHeight() + this.j + 1;

                int var2;
                int var4;
                int var7;
                for (var4 = 0; var4 < this.E.length - 1; ++var4) {
                    if (this.E[var4] != null) {
                        if (this.F == var4 && this.E[var4].b == -1) {
                            g.setColor(0x3A3A3A); //светловато-чёрный
                            g.drawRect(0, var3 - 1, StaticData.screenWidth - 1, this.D);
                            this.G = this.E[var4].d();
                            this.H = this.E[var4].g();
                            this.y = var3;
                        }

                        if (var3 > 0) {
                            if (var3 >= StaticData.screenHeight - UiUtil.imgFormatedMenuBack.getHeight() - UiUtil.formatedImgStatusbar.getHeight()) {
                                continue;
                            }

                            g.setColor(0);
                            g.drawImage(a[this.E[var4].f()], 10, var3, 0);
                            int var5 = this.E[var4].a().length - 1;
                            StringUtils.a(g, 10 + this.C + 2, var3 + UiUtil.allFontImagesHeight[0] % 2 + 1, (String) (this.E[var4].h() + " (" + this.E[var4].b() + "/" + var5 + ")"), 5);
                        }

                        var3 = var3 + this.D + 2;
                        if (this.E[var4].a) {
                            var2 = this.C;
                            CommandListener3[] var10 = this.E[var4].a();

                            for (int var6 = 0; var6 < var10.length - 1; ++var6) {
                                if (this.n || var10[var6].f != 0) {
                                    if (var4 == this.F && var6 == this.E[var4].b) {
                                        g.setColor(0x3A3A3A); //светловато-чёрный
                                        g.drawRect(0, var3 - 1, StaticData.screenWidth - 1, this.D);
                                        this.G = var10[var6].a();
                                        this.H = var10[var6].b();
                                        this.y = var3;
                                    }

                                    if (var3 > 0) {
                                        if (var3 >= StaticData.screenHeight - UiUtil.imgFormatedMenuBack.getHeight() - UiUtil.formatedImgStatusbar.getHeight()) {
                                            continue;
                                        }

                                        g.setColor(0);
                                        g.drawImage(c[CommandListener3.a(this.E[var4].a()[var6].f)], var2, var3, 0);
                                        StringUtils.a(g, var2 + this.C + 2, var3 + UiUtil.allFontImagesHeight[0] % 2 + 1, (String) var10[var6].g(), 0);
                                        if ((var7 = var10[var6].d()) > 0) {
                                            int var8 = var2 + this.C + 2 + StringUtils.a((String) var10[var6].g(), 0) + 5;
                                            StringUtils.a(g, var8, var3 + UiUtil.allFontImagesHeight[0] % 2 + 1, (String) Integer.toString(var7), 0);
                                            g.drawImage(a[0], var8 + StringUtils.a((String) (Integer.toString(var7) + 1), 0), var3, 0);
                                        }
                                    }

                                    var3 = var3 + this.D + 2;
                                }
                            }
                        }
                    }
                }

                var3 = StaticData.screenHeight - UiUtil.imgFormatedMenuBack.getHeight() - UiUtil.formatedImgStatusbar.getHeight() + 1;
                g.drawImage(UiUtil.formatedImgStatusbar, 0, var3, 0);
                byte var9 = CommandListener3.a(this.i);
                g.drawImage(e[var9], 2, var3, 0);
                var2 = 2 + this.C;
                var4 = this.n ? 6 : 7;
                g.drawImage(a[var4], var2, var3, 0);
                var2 += this.C;
                var4 = this.m ? 4 : 5;
                g.drawImage(a[var4], var2, var3, 0);
                var2 += this.C;
                var4 = this.l ? 8 : 9;
                g.drawImage(a[var4], var2, var3, 0);
                byte var11 = Messenger.b;
                if ((var11 = (byte) (Messenger.b + 1)) > StaticData.messagerType.length - 1) {
                    var11 = 0;
                }

                var7 = StringUtils.a((String) StaticData.messagerType[var11], 4);
                var7 -= 2;
                StringUtils.a(g, StaticData.screenWidth - var7 - UiUtil.imgMessagerRightArrow.getWidth() - 5, var3 + UiUtil.imgMessagerRightArrow.getHeight() / 2 - UiUtil.allFontImagesHeight[4] / 2, (String) StaticData.messagerType[var11], 4);
                g.drawImage(UiUtil.imgMessagerRightArrow, StaticData.screenWidth - UiUtil.imgMessagerRightArrow.getWidth(), var3, 0);
                break;
            case 2:
                this.G = CommandListener1.a();
                this.H = CommandListener1.b();
                this.k.a(g, this.E[this.F].a()[this.E[this.F].b]);
        }

        if (this.g) {
            this.a(g, this.G, this.H);
        }

    }

    public final CommandListener4[] k() {
        return this.E;
    }

    public final CommandListener3 b(String var1) {
        for (int var2 = 0; var2 < this.E.length - 1; ++var2) {
            for (int var3 = 0; var3 < this.E[var2].a().length - 1; ++var3) {
                if (this.E[var2].a()[var3].getUid_().equals(var1)) {
                    return this.E[var2].a()[var3];
                }
            }
        }

        return null;
    }

    public final CommandListener4 d(int var1) {
        for (int var2 = 0; var2 < this.E.length - 1; ++var2) {
            if (this.E[var2].e() == var1) {
                return this.E[var2];
            }
        }

        return null;
    }

    public final int c(String var1) {
        for (int var2 = 0; var2 < this.E.length - 1; ++var2) {
            if (this.E[var2].h().equals(var1)) {
                return this.E[var2].e();
            }
        }

        return -1;
    }

    public final byte l() {
        return this.A;
    }

    public final String m() {
        return this.z;
    }

    public final byte n() {
        return this.I;
    }

    private Displayable d(String var1) {
        this.o = new TextBox("Создать группу", var1, 26, 0);
        this.o.addCommand(this.q);
        this.o.addCommand(this.r);
        this.o.setCommandListener(this);
        return this.o;
    }

    private Displayable e(String var1) {
        this.o = new TextBox("Создать пользователя", var1, 26, 2);
        this.o.addCommand(this.p);
        this.o.addCommand(this.r);
        this.o.setCommandListener(this);
        return this.o;
    }

    private String[] f(String var1) {
        int var2 = this.E.length - 1;
        if (!var1.equals("")) {
            --var2;
        }

        String[] var3 = new String[var2];
        int var4 = 0;

        for (int var5 = 0; var5 < this.E.length - 1; ++var5) {
            if (!this.E[var5].h().equals(var1) && this.E[var5].e() != 999999999) {
                var3[var4] = this.E[var5].h();
                ++var4;
            }
        }

        return var3;
    }

    private Displayable a(String[] var1) {
        this.N = new List("Выберите группу", 3, var1, (Image[]) null);
        this.N.addCommand(this.s);
        this.N.addCommand(this.r);
        this.N.setCommandListener(this);
        return this.N;
    }

    private Displayable b(String[] var1) {
        this.N = new List("Выберите группу", 3, var1, (Image[]) null);
        this.N.append("Удалить всех пользователей", (Image) null);
        this.N.addCommand(this.t);
        this.N.addCommand(this.r);
        this.N.setCommandListener(this);
        return this.N;
    }

    public final void a(String var1, int var2) {
        CommandListener3 var3 = this.b(var1);
        this.a(var1);
        CommandListener4 var4;
        (var4 = this.d(var2)).a(var3.g(), var3.getUid_(), var3.f, var4.e());
    }

    private Displayable c(String[] var1) {
        this.N = new List("Выберите группу", 3, var1, (Image[]) null);
        this.N.addCommand(this.u);
        this.N.addCommand(this.r);
        this.N.setCommandListener(this);
        return this.N;
    }

    public final void commandAction(Command var1, Displayable var2) {
        int var3;
        Alert var5;
        if (var1 == this.p) {
            if (this.o.size() == 0) {
                var5 = new Alert("Ошибка", "Необходимо ввести имя пользователя.", (Image) null, AlertType.ERROR);
                Yapp.display.setCurrent(var5);
                return;
            }

            var3 = this.E[this.F].e();
            Messenger.a(this.o.getString(), var3);
        } else {
            if (var1 == this.q) {
                if (this.o.size() == 0) {
                    var5 = new Alert("Ошибка", "Необходимо ввести имя группы.", (Image) null, AlertType.ERROR);
                    Yapp.display.setCurrent(var5);
                    return;
                }

                if (this.c(this.o.getString()) != -1) {
                    Alert var6 = new Alert("Ошибка", "Такая группа уже существует.", (Image) null, AlertType.ERROR);
                    Yapp.display.setCurrent(var6);
                    return;
                }

                Messenger.a(this.o.getString());
                Yapp.display.setCurrent(ScreenCanvas.screenCanvas);
                return;
            }

            if (var1 != this.r) {
                if (var1 == this.s) {
                    var3 = this.c(this.N.getString(this.N.getSelectedIndex()));
                    Messenger.b(this.E[this.F].a()[this.E[this.F].b].getUid_(), var3);
                } else if (var1 == this.u) {
                    var3 = this.c(this.N.getString(this.N.getSelectedIndex()));
                    Messenger.a(this.E[this.F].a()[this.E[this.F].b].getUid_(), var3);
                } else {
                    if (var1 != this.t) {
                        return;
                    }

                    if (this.N.getSelectedIndex() == this.N.size() - 1) {
                        var3 = this.E[this.F].c ? 0 : 1;
                        Messenger.a(this.E[this.F].h(), -1, (byte) var3);
                    } else {
                        var3 = this.c(this.N.getString(this.N.getSelectedIndex()));
                        int var4 = this.E[this.F].c ? 0 : 1;
                        Messenger.a(this.E[this.F].h(), var3, (byte) var4);
                    }
                }
            }
        }

        Yapp.display.setCurrent(ScreenCanvas.screenCanvas);
    }
}
