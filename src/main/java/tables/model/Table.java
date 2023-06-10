package tables.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import shared.dto.CellDTO;
import shared.dto.ColumnDTO;
import shared.model.Column;
import tables.dto.TableDTO;

import java.util.*;
import java.util.function.Function;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Table<T> {
    public String name;
    public List<Column<T, ?>> columns;
    public Function<T, String> idGenerator;

    public TableDTO generateTableDTO(List<T> entities) {
        List<ColumnDTO> columnDTOs = new ArrayList<>();
        for (Column<T, ?> column : columns) {
            ColumnDTO columnDTO = createColumnDTO(column);
            columnDTOs.add(columnDTO);
        }

        return TableDTO.builder()
                .name(name)
                .type("table")
                .columns(columnDTOs)
                .data(generateData(entities))
                .build();
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
        for (Column childColumn : column.getChildrenColumns()) {
            ColumnDTO childColumnDTO = createColumnDTO(childColumn);
            childColumnDTOs.add(childColumnDTO);
        }
        columnDTO.setChildrenColumns(childColumnDTOs);
        return columnDTO;
    }

    private Map<String, Map<String, CellDTO>> generateData(List<T> entities) {
        Map<String, Map<String, CellDTO>> data = new LinkedHashMap<>();
        for (T entity : entities) {
            Map<String, CellDTO> rowData = new LinkedHashMap<>();
            for (Column<T, ?> column : extractLeafColumns()) {
                CellDTO cellDTO = column.createCellDTO(entity);
                rowData.put(column.getId(), cellDTO);
            }
            data.put(idGenerator.apply(entity), rowData);
        }
        return data;
    }


    public List<Column<T, ?>> extractLeafColumns() {
        List<Column<T, ?>> leafColumns = new ArrayList<>();
        for (Column<T, ?> column : columns) {
            extractLeafColumnsHelper(column, leafColumns);
        }
        return leafColumns;
    }

    private void extractLeafColumnsHelper(Column<T, ?> column, List<Column<T, ?>> leafColumns) {
        if (column.getChildrenColumns().isEmpty()) {
            leafColumns.add(column);
        } else {
            for (Column child : column.getChildrenColumns()) {
                extractLeafColumnsHelper(child, leafColumns);
            }
        }
    }


}
