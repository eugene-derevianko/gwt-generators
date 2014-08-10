/*
 * Copyright (c) 2014 Eugene
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

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
