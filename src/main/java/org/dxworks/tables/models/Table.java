package org.dxworks.tables.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Table<T> {
    public String name;
    public List<Column<T, ?>> columns;

   /* public List<String> generateJson(Table<T> table) {
        List<String> outputJsons = new ArrayList<>();
        for (Column<T, ?> column : table.getColumns()) {
            JSONObject columnJson = createColumnObject(column);
            outputJsons.add(columnJson.toString());
        }
        return outputJsons;
    }

    private JSONObject createColumnObject(Column<T, ?> column) {
        JSONObject columnJson = new JSONObject();
        if (column.getId() != null) {
            columnJson.put("id", column.getId());
        }
        if (column.getName() != null) {
            columnJson.put("name", column.getName());
        }
        if (column.getNameStyle() != null) {
            columnJson.put("nameStyle", column.getNameStyle());
        }
        if (column.getDescription() != null) {
            columnJson.put("description", column.getDescription());
        }
        if (column.getDescriptionStyle() != null) {
            columnJson.put("descriptionStyle", column.getDescriptionStyle());
        }
        if (column.getSortable() != null) {
            columnJson.put("sortable", column.getSortable());
        }
        if (column.getBorderL() != null) {
            columnJson.put("borderL", column.getBorderL());
        }
        if (column.getBorderR() != null) {
            columnJson.put("borderR", column.getBorderR());
        }
        if (column.getBorderB() != null) {
            columnJson.put("borderB", column.getBorderB());
        }
        if (column.childrenColumns != null) {
            JSONArray childrenJsons = new JSONArray();
            for (ColumnBase childColumn : column.getChildrenColumns()) {
                if (childColumn instanceof Column) {
                    childrenJsons.put(createColumnObject((Column) childColumn));
                }
            }
            columnJson.put("childrenColumns", childrenJsons);
        }
        return columnJson;
    }*/

}
