package ru.yapp.mobile.core.collection;

import java.util.Vector;

public final class MapArray {
   private Vector vector = new Vector();
   private int arraySize = 0;

   public final void add(String str, Object obj) {
      if (obj != null) {
         for(int index = this.arraySize - 1; index >= 0; --index) {
            MapItem mapItem;
            if ((mapItem = (MapItem)this.vector.elementAt(index)).key.equals(str)) {
               mapItem.obj = obj;
               return;
            }
         }

         MapItem var5 = new MapItem(str, obj);
         this.vector.addElement(var5);
         ++this.arraySize;
      }
   }

   public final Object get(String var1) {
      for(int var2 = this.arraySize - 1; var2 >= 0; --var2) {
         MapItem var3;
         if ((var3 = (MapItem)this.vector.elementAt(var2)).key.equals(var1)) {
            return var3.obj;
         }
      }

      return null;
   }

   public final void cleanAll() {
      this.vector.removeAllElements();
      this.arraySize = 0;
   }

   public final Vector getVector() {
      return this.vector;
   }
}
