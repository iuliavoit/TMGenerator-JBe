package matrices.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import matrices.dto.MatrixDTO;
import shared.dto.CellDTO;
import shared.dto.ColumnDTO;
import shared.model.Column;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Matrix<T1, T2> {
    private String name;
    public Function<T1, String> entity1IdGenerator;
    public Function<T2, String> entity2IdGenerator;

    private List<Column<T1, ?>> entity1PaddingColumns;
    private List<Column<T2, ?>> entity2PaddingColumns;

    private List<MatrixValueComputer<T1, T2>> valueComputers;

    public MatrixDTO generateMatrixDto(List<T1> entities1, List<T2> entities2) {
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

        return MatrixDTO.builder()
                .name(name)
                .type("matrix")
                .entity1PaddingColumns(entity1ColumnDTOs)
                .entity2PaddingColumns(entity2ColumnDTOs)
                .entity1PaddingData(generatePadding1Data(entities1))
                .entity2PaddingData(generatePadding2Data(entities2))
                .data(generateSourceTargetMatrixData(entities1, entities2))
                .build();
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

    private Map<String, Map<String, CellDTO>> generatePadding1Data(List<T1> entities) {
        Map<String, Map<String, CellDTO>> data = new LinkedHashMap<>();
        for (T1 entity : entities) {
            Map<String, CellDTO> rowData = new LinkedHashMap<>();
            for (Column<T1, ?> column : this.getEntity1PaddingColumns()) {
                CellDTO cellDTO = column.createCellDTO(entity);
                rowData.put(column.getId(), cellDTO);
            }
            data.put(entity1IdGenerator.apply(entity), rowData);
        }
        return data;
    }

    private Map<String, Map<String, CellDTO>> generatePadding2Data(List<T2> entities) {
        Map<String, Map<String, CellDTO>> data = new LinkedHashMap<>();
        for (T2 entity : entities) {
            Map<String, CellDTO> rowData = new LinkedHashMap<>();
            for (Column<T2, ?> column : this.getEntity2PaddingColumns()) {
                CellDTO cellDTO = column.createCellDTO(entity);
                rowData.put(column.getId(), cellDTO);
            }
            data.put(entity2IdGenerator.apply(entity), rowData);
        }
        return data;
    }

    private Map<String, List<Map<String, Object>>> generateSourceTargetMatrixData(List<T1> entities1, List<T2> entities2) {
        Map<String, List<Map<String, Object>>> data = new LinkedHashMap<>();
        for (MatrixValueComputer<T1, T2> valueComputer : this.valueComputers) {
            List<Map<String, Object>> valueComputerData = new ArrayList<>();
            for (T1 entity1 : entities1) {
                for (T2 entity2 : entities2) {
                    Number computerResult = valueComputer.getValueComputer() != null ? valueComputer.getValueComputer().apply(entity1, entity2) : null;
                    Map<String, String> conditionalFormatterResult = valueComputer.getConditionalFormatter() != null ?
                            valueComputer.getConditionalFormatter().format(entity1, entity2, computerResult) : null;
                    String displayResult = valueComputer.getDisplayFormatter() != null ? valueComputer.getDisplayFormatter().apply(computerResult) : computerResult != null ? computerResult.toString() : "";
                    Map<String, Object> cellData = new LinkedHashMap<>();
                    cellData.put("source", this.entity1IdGenerator.apply(entity1));
                    cellData.put("target", this.entity2IdGenerator.apply(entity2));
                    cellData.put("value", displayResult);
                    cellData.put("style", convertStyleToJsonString(conditionalFormatterResult));
                    valueComputerData.add(cellData);
                }
            }
            data.put(valueComputer.getName(), valueComputerData);
        }
        return data;
    }

    public String convertStyleToJsonString(Map<String, String> styleMap) {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, String> entry : styleMap.entrySet()) {
            sb.append(entry.getKey()).append(":").append(entry.getValue()).append("; ");
        }
        return sb.toString().trim();
    }
}



