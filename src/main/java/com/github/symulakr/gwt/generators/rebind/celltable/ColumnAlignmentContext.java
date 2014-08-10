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

package com.github.symulakr.gwt.generators.rebind.celltable;

import com.github.symulakr.gwt.generators.client.celltable.annotation.ColumnAlignment;
import com.google.gwt.core.ext.typeinfo.JMethod;
import com.google.gwt.dom.client.Style.TextAlign;
import com.google.gwt.dom.client.Style.VerticalAlign;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasVerticalAlignment;

public class ColumnAlignmentContext
{

   private String horizontalAlignment;
   private String verticalAlignment;

   public ColumnAlignmentContext(JMethod method)
   {
      ColumnAlignment columnAlignment = method.getAnnotation(ColumnAlignment.class);
      setHorizontalAlignment(columnAlignment.horizontalAlignment());
      setVerticalAlignment(columnAlignment.verticalAlignment());
   }

   public String getHorizontalAlignment()
   {
      return horizontalAlignment;
   }

   private void setHorizontalAlignment(TextAlign align)
   {
      horizontalAlignment = HasHorizontalAlignment.class.getName();
      switch (align)
      {
         case CENTER:
            horizontalAlignment += ".ALIGN_CENTER";
            break;
         case JUSTIFY:
            horizontalAlignment += ".ALIGN_JUSTIFY";
            break;
         case LEFT:
            horizontalAlignment += ".ALIGN_LEFT";
            break;
         case RIGHT:
            horizontalAlignment += ".ALIGN_RIGHT";
            break;
      }
   }

   public String getVerticalAlignment()
   {
      return verticalAlignment;
   }

   private void setVerticalAlignment(VerticalAlign align)
   {
      verticalAlignment = HasVerticalAlignment.class.getName();
      switch (align)
      {
         case TOP:
            verticalAlignment += ".ALIGN_TOP";
            break;
         case BOTTOM:
            verticalAlignment += ".ALIGN_BOTTOM";
            break;
         case MIDDLE:
         default:
            verticalAlignment += ".ALIGN_MIDDLE";
            break;
      }
   }

}
