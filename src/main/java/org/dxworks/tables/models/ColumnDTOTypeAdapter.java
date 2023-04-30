package org.dxworks.tables.models;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import dtos.ColumnDTO;

import java.io.IOException;

public class ColumnDTOTypeAdapter extends TypeAdapter<ColumnDTO> {
    @Override
    public void write(JsonWriter out, ColumnDTO columnDTO) throws IOException {
        out.beginObject();
        out.name("id").value(columnDTO.getId());
        out.name("type").value(columnDTO.getType());
        out.name("name").value(columnDTO.getName());
        out.name("nameStyle").value(columnDTO.getNameStyle());
        out.name("description").value(columnDTO.getDescription());
        out.name("descriptionStyle").value(columnDTO.getDescriptionStyle());
        out.name("sortable").value(columnDTO.getSortable());
        out.name("borderL").value(columnDTO.getBorderL());
        out.name("borderR").value(columnDTO.getBorderR());
        out.name("borderB").value(columnDTO.getBorderB());

        out.name("childrenColumns").beginArray();
        for (ColumnDTO childColumnDTO : columnDTO.getChildrenColumns()) {
            write(out, childColumnDTO);
        }
        out.endArray();

        out.endObject();
    }

    @Override
    public ColumnDTO read(JsonReader in) throws IOException {
        // Implementation for deserialization, if needed
        return null;
    }


}

