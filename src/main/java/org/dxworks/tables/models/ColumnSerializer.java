package org.dxworks.tables.models;

import com.google.gson.*;

import java.lang.reflect.Type;

public class ColumnSerializer implements JsonSerializer<Column> {
    @Override
    public JsonElement serialize(Column column, Type type, JsonSerializationContext context) {
        Gson gson = new GsonBuilder().serializeNulls().create();
        JsonElement jsonElement = gson.toJsonTree(column);

        jsonElement.getAsJsonObject().entrySet().removeIf(entry -> entry.getValue().isJsonNull());
        return jsonElement;
    }
    /*@Override
    public JsonElement serialize(Column column, Type type, JsonSerializationContext context) {
        JsonObject jsonObject = new JsonObject();

        if (column.getId() != null) {
            jsonObject.addProperty("id", column.getId());
        }
        if (column.getName() != null) {
            jsonObject.addProperty("name", column.getName());
        }
        if (column.getNameStyle() != null) {
            jsonObject.addProperty("nameStyle", column.getNameStyle());
        }
        if (column.getDescription() != null) {
            jsonObject.addProperty("description", column.getDescription());
        }
        if (column.getDescriptionStyle() != null) {
            jsonObject.addProperty("descriptionStyle", column.getDescriptionStyle());
        }
        if (column.getSortable() != null) {
            jsonObject.addProperty("sortable", column.getSortable());
        }
        if (column.getBorderL() != null) {
            jsonObject.addProperty("borderL", column.getBorderL());
        }
        if (column.getBorderR() != null) {
            jsonObject.addProperty("borderR", column.getBorderR());
        }
        if (column.getBorderB() != null) {
            jsonObject.addProperty("borderB", column.getBorderB());
        }
        if (column.getChildrenColumns() != null) {
            jsonObject.add("childrenColumns", context.serialize(column.getChildrenColumns()));
        }

        return jsonObject;
    }*/



}