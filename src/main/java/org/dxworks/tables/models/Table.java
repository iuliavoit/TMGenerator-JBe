package org.dxworks.tables.models;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import dtos.ColumnDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Table<T> {
    public String name;
    public List<Column<T, ?>> columns;

    public JsonObject generateJson() {
        List<ColumnDTO> columnDTOs = new ArrayList<>();
        for (Column<T, ?> column : columns) {
            ColumnDTO columnDTO = createColumnDTO(column);
            columnDTOs.add(columnDTO);
        }

        Map<String, Object> jsonMap = new HashMap<>();
        jsonMap.put("name", name);
        jsonMap.put("columns", columnDTOs);

        Gson gson = new GsonBuilder()
                .registerTypeAdapter(ColumnDTO.class, new ColumnDTOTypeAdapter())
                .disableHtmlEscaping()
                .setPrettyPrinting()
                .create();
        JsonObject result = gson.fromJson(gson.toJson(jsonMap), JsonObject.class);
        return result;
    }

    private ColumnDTO createColumnDTO(Column<T, ?> column) {
        ColumnDTO columnDTO = new ColumnDTO();
        columnDTO.setId(column.getId());
        columnDTO.setType(column.getType());
        columnDTO.setName(column.getName());
        columnDTO.setNameStyle(column.getNameStyle());
        columnDTO.setDescription(column.getDescription());
        columnDTO.setDescriptionStyle(column.getDescriptionStyle());
        columnDTO.setSortable(column.getSortable());
        columnDTO.setBorderL(column.getBorderL());
        columnDTO.setBorderR(column.getBorderR());
        columnDTO.setBorderB(column.getBorderB());

        List<ColumnDTO> childColumnDTOs = new ArrayList<>();
        for (Column<T, ?> childColumn : column.getChildrenColumns()) {
            ColumnDTO childColumnDTO = createColumnDTO(childColumn);
            childColumnDTOs.add(childColumnDTO);
        }
        columnDTO.setChildrenColumns(childColumnDTOs);
        return columnDTO;
    }


    public static <T, R> List<Column<T, ?>> extractLeafColumns(Table<T> table) {
        List<Column<T, ?>> leafColumns = new ArrayList<>();
        for (Column<T, ?> column : table.columns) {
            extractLeafColumnsHelper(column, leafColumns);
        }
        return leafColumns;
    }

    private static <T, R> void extractLeafColumnsHelper(Column<T, ?> column, List<Column<T, ?>> leafColumns) {
        if (column.childrenColumns.isEmpty()) {
            leafColumns.add(column);
        } else {
            for (Column<T, ?> child : column.childrenColumns) {
                extractLeafColumnsHelper(child, leafColumns);
            }
        }
    }


}
