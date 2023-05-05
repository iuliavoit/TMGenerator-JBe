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
import java.util.function.BiFunction;
import java.util.function.Function;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Matrix<T1, T2> {
    private String name;
    private List<Column<T1, ?>> entity1PaddingColumns;
    private List<Column<T2, ?>> entity2PaddingColumns;
    private List<MatrixValueComputer<T1,T2>> valueComputers;

    public JsonObject generateJson() {
        List<ColumnDTO> entity1ColumnDTOs = new ArrayList<>();
        for (Column<T1, ?> column : entity1PaddingColumns) {
            ColumnDTO columnDTO = createColumnDTO(column);
            entity1ColumnDTOs.add(columnDTO);
        }

        List<ColumnDTO> entity2ColumnDTOs = new ArrayList<>();
        for (Column<T2, ?> column : entity2PaddingColumns) {
            ColumnDTO columnDTO = createColumnDTO(column);
            entity2ColumnDTOs.add(columnDTO);
        }

        Map<String, Object> jsonMap = new HashMap<>();
        jsonMap.put("name", name);
        jsonMap.put("entity1PaddingColumns", entity1ColumnDTOs);
        jsonMap.put("entity2PaddingColumns", entity2ColumnDTOs);

        Gson gson = new GsonBuilder()
                .registerTypeAdapter(ColumnDTO.class, new ColumnDTOTypeAdapter())
                .disableHtmlEscaping()
                .setPrettyPrinting()
                .create();
        JsonObject result = gson.fromJson(gson.toJson(jsonMap), JsonObject.class);
        return result;
    }

    private ColumnDTO createColumnDTO(Column<?, ?> column) {
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
        if (column.getChildrenColumns() != null) {
            for (Column<?, ?> childColumn : column.getChildrenColumns()) {
                ColumnDTO childColumnDTO = createColumnDTO(childColumn);
                childColumnDTOs.add(childColumnDTO);
            }
        }
        columnDTO.setChildrenColumns(childColumnDTOs);
        return columnDTO;
    }
}

interface MatrixConditionalFormatter<T1, T2, R> {
    Map<String, String> format(T1 t1, T2 t2, R value);
}