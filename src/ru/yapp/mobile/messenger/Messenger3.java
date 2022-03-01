package ru.yapp.mobile.messenger;

import ru.yapp.mobile.core.StaticData;
import ru.yapp.mobile.core.Core9;

public final class Messenger3 {
   public int a = 0;
   public String[] b;
   public boolean c;
   public boolean d;
   public String e;

   public Messenger3(String var1, boolean var2, boolean var3, long var4) {
      this.c = var2;
      this.d = var3;
      long var6;
      long var8 = (var6 = var4 / 1000L) / 60L % 60L;
      long var10 = var6 / 3600L % 24L;
      this.e = var8 < 10L ? "(" + var10 + ":0" + var8 + "): " : "(" + var10 + ":" + var8 + "): ";
      this.b = Core9.a(4, StaticData.screenWidth, var1, Messenger1.a[Messenger1.b].a());
   }
}
