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

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ReflectUtilsTest
{

   public static class ClassWithPublicMember
   {
      public ClassWithPublicMember()
      {
      }

      public String publicField = "";

      public int publicMethod()
      {
         return 0;
      }
   }

   public static final class FinalClass
   {
   }

   public static class ClassWithFinalMember
   {
      final String finalField = "";

      final int finalMethod()
      {
         return 0;
      }
   }

   public static class ClassWithProtectedMember
   {
      protected ClassWithProtectedMember()
      {
      }

      protected String protectedField;

      protected int protectedMethod()
      {
         return 0;
      }
   }

   @Test
   public void shouldPassForFinalClass() throws Exception
   {
      assertTrue(ReflectUtils.isFinal(FinalClass.class));
      assertFalse(ReflectUtils.isFinal(ClassWithPublicMember.class));
   }

   @Test
   public void shouldPassForFinalMember() throws Exception
   {
      assertTrue(ReflectUtils.isFinal(ClassWithFinalMember.class.getDeclaredField("finalField")));
      assertTrue(ReflectUtils.isFinal(ClassWithFinalMember.class.getDeclaredMethod("finalMethod")));
   }

   @Test
   public void shouldPassForPublicMember() throws Exception
   {
      assertTrue(ReflectUtils.isPublic(ClassWithPublicMember.class.getDeclaredField("publicField")));
      assertTrue(ReflectUtils.isPublic(ClassWithPublicMember.class.getDeclaredMethod("publicMethod")));
      assertTrue(ReflectUtils.isPublic(ClassWithPublicMember.class.getDeclaredConstructor()));
   }

   @Test
   public void shouldPassForProtectedMember() throws Exception
   {
      assertTrue(ReflectUtils.isProtected(ClassWithProtectedMember.class.getDeclaredField("protectedField")));
      assertTrue(ReflectUtils.isProtected(ClassWithProtectedMember.class.getDeclaredMethod("protectedMethod")));
      assertTrue(ReflectUtils.isProtected(ClassWithProtectedMember.class.getDeclaredConstructor()));
   }
}
