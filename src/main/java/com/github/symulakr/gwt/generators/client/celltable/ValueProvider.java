package com.github.symulakr.gwt.generators.client.celltable;

public interface ValueProvider<T, V>
{
   V getValue(T object);
}
