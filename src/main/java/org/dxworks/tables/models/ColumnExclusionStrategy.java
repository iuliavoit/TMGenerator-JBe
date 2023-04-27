package org.dxworks.tables.models;


import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;

public class ColumnExclusionStrategy implements ExclusionStrategy {

    @Override
    public boolean shouldSkipField(FieldAttributes f) {
        return f.getName().equals("computer") || f.getName().equals("conditionalFormatter") || f.getName().equals("displayFormatter");
    }

    @Override
    public boolean shouldSkipClass(Class<?> clazz) {
        return false;
    }
}