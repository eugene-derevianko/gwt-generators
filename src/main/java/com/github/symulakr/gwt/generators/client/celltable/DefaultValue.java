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

package com.github.symulakr.gwt.generators.client.celltable;

import com.google.gwt.cell.client.NumberCell;
import com.google.gwt.cell.client.SafeHtmlCell;
import com.google.gwt.cell.client.TextCell;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.CellTable.Resources;
import com.google.gwt.user.cellview.client.SafeHtmlHeader;
import com.google.gwt.user.client.ui.Widget;

public class DefaultValue
{

   public static final Class DEFAULT_CELL_TYPE = TextCell.class;
   public static final Class SAFE_HTML_CELL_TYPE = SafeHtmlCell.class;
   public static final Class NUMBER_CELL_TYPE = NumberCell.class;
   public static final int UNSPECIFIED = -1;
   public static final String EMPTY_STRING = "";
   public static final Class DEFAULT_RESOURCES = Resources.class;
   public static final Class DEFAULT_TABLE_TYPE = CellTable.class;
   public static final Class DEFAULT_HTML_HEADER_TYPE = SafeHtmlHeader.class;
   public static final Class DEFAULT_UIOBJECT_TYPE = Widget.class;

}
