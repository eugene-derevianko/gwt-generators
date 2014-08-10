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

package com.github.symulakr.gwt.generators.client.celltable.annotation;

import com.github.symulakr.gwt.generators.client.celltable.DefaultFieldUpdater;
import com.google.gwt.cell.client.FieldUpdater;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static com.github.symulakr.gwt.generators.client.celltable.DefaultValue.UNSPECIFIED;


@Retention(RetentionPolicy.CLASS)
@Target(ElementType.METHOD)
public @interface ColumnActions
{

   Class<? extends FieldUpdater> fieldUpdater() default DefaultFieldUpdater.class;

   int position() default UNSPECIFIED;

   boolean enableSorting() default false;

}
