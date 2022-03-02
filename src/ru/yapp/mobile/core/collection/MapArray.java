package ru.yapp.mobile.core.collection;

import java.util.Vector;

public final class MapArray {
   private Vector vector = new Vector();
   private int arraySize = 0;

   public final void add(String str, Object obj) {
      if (obj != null) {
         for(int i = this.arraySize - 1; i >= 0; --i) {
            MapItem mapItem;
            if ((mapItem = (MapItem)this.vector.elementAt(i)).key.equals(str)) {
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
      for(int i = this.arraySize - 1; i >= 0; --i) {
         MapItem mapItem = (MapItem)this.vector.elementAt(i);
         if (mapItem.key.equals(var1)) {
            return mapItem.obj;
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
