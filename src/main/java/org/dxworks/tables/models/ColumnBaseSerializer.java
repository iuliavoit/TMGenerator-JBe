package org.dxworks.tables.models;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;


public class ColumnBaseSerializer extends JsonSerializer<Column<?, ?>> {
    @Override
    public void serialize(Column<?, ?> column, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeStringField("id", column.getId());
        jsonGenerator.writeStringField("name", column.getName());
        jsonGenerator.writeBooleanField("sortable", column.getSortable());
        // Add other fields here

        jsonGenerator.writeStringField("type", column.getType());
        jsonGenerator.writeObjectField("childrenColumns", column.getChildrenColumns());

        jsonGenerator.writeEndObject();
    }
}
