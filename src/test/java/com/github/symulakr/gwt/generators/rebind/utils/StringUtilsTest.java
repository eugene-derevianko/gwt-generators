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

import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;

public class StringUtilsTest
{

   private String nullString = null;
   private String emptyString = "";

   @Test
   public void testIsEmpty() throws Exception
   {
      String string = "string";
      assertTrue(StringUtils.isEmpty(nullString));
      assertTrue(StringUtils.isEmpty(emptyString));
      assertFalse(StringUtils.isEmpty(string));
   }

   @Test
   public void testIsNotEmpty() throws Exception
   {
      String string = "string";
      assertFalse(StringUtils.isNotEmpty(nullString));
      assertFalse(StringUtils.isNotEmpty(emptyString));
      assertTrue(StringUtils.isNotEmpty(string));
   }
   
}
