package ru.yapp.mobile.messenger;

import ru.yapp.mobile.core.StaticData;
import ru.yapp.mobile.core.StringUtils;

public final class MessangeData {
    
   public int a = 0;
   public String[] strArr;
   public boolean c;
   public boolean d;
   public String time;

   public MessangeData(String str, boolean bool1, boolean bool2, long milisec) {
      this.c = bool1;
      this.d = bool2;
      long sec = milisec / 1000L;
      long min = sec / 60L % 60L;
      long hour = sec / 3600L % 24L;
      this.time = min < 10L ? "(" + hour + ":0" + min + "): " : "(" + hour + ":" + min + "): ";
      this.strArr = StringUtils.a(4, StaticData.screenWidth, str, Messenger.cmdListener2Arr[Messenger.b].a());
   }
}
