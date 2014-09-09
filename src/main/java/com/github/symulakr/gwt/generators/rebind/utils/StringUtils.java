package com.github.symulakr.gwt.generators.rebind.utils;

public class StringUtils
{

   public static boolean isEmpty(String str)
   {
      return str == null || str.length() == 0;
   }

   public static boolean isNotEmpty(String str)
   {
      return !StringUtils.isEmpty(str);
   }

   public static boolean equals(CharSequence cs1, CharSequence cs2)
   {
      return cs1 == null ? cs2 == null : cs1.equals(cs2);
   }

   public static String toString(Object... objects)
   {
      StringBuilder sb = new StringBuilder();
      String delimiter = "";
      for (Object object : objects)
      {
         sb.append(delimiter).
            append(object.toString());
         delimiter = ", ";
      }
      return sb.toString();
   }

}
