package shared.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import shared.dto.CellDTO;
import shared.dto.ColumnDTO;

import java.util.*;
import java.util.function.BiFunction;
import java.util.function.Function;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Column<T, R> {

    protected String id;
    protected String type;
    protected String name;
    protected String nameStyle;
    protected String description;
    protected String descriptionStyle;
    protected Boolean sortable;
    protected Boolean borderL;
    protected Boolean borderR;
    protected Boolean borderB;
    protected List<Column> childrenColumns = new ArrayList<>();

    protected Function<T, R> computer;
    protected BiFunction<T, R, Map<String, String>> conditionalFormatter;
    protected Function<R, String> displayFormatter;

    public ColumnDTO toDTO() {
        ColumnDTO dto = new ColumnDTO();
        dto.setId(this.id);
        dto.setType(this.type);
        dto.setName(this.name);
        dto.setNameStyle(this.nameStyle);
        dto.setDescription(this.description);
        dto.setDescriptionStyle(this.descriptionStyle);
        dto.setSortable(this.sortable);
        dto.setBorderL(this.borderL);
        dto.setBorderR(this.borderR);
        dto.setBorderB(this.borderB);

        List<ColumnDTO> childDTOs = new ArrayList<>();
        for (Column child : this.childrenColumns) {
            childDTOs.add(child.toDTO());
        }
        dto.setChildrenColumns(childDTOs);

        return dto;
    }

    public CellDTO createCellDTO(T entity) {
        //applying the computer
        R computerResult = computer!= null?computer.apply(entity): null;
        //applying the conditional formatter to the computed result
        Map<String, String> conditionalFormatterResult = conditionalFormatter != null ?
                conditionalFormatter.apply(entity, computerResult) : new HashMap<>();
        //checks the boolean properties defining the column cell borders and adds them to the
        //formatter if true
        Map<String, String> formatter = addBorders(conditionalFormatterResult);
        //applying the display formatter to the computed result
        String displayResult = displayFormatter != null ? displayFormatter.apply(computerResult) : computerResult != null ? computerResult.toString() : "";
        //creates a cell having properties value and style
        return new CellDTO(displayResult, convertStyleToJsonString(formatter));
    }

    private Map<String, String> addBorders(Map<String, String> conditionalFormatterResult) {
        Map<String, String> formatter = new HashMap<>(conditionalFormatterResult);
        if (borderB != null) {
            formatter.put("border-bottom", "1px solid");
        }
        if (borderR != null) {
            formatter.put("border-right", "1px solid");
        }
        if (borderL != null) {
            formatter.put("border-left", "1px solid");
        }

        return formatter;
    }

    public String convertStyleToJsonString(Map<String, String> styleMap) {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, String> entry : styleMap.entrySet()) {
            sb.append(entry.getKey()).append(":").append(entry.getValue()).append("; ");
        }
        return sb.toString().trim();
    }
}

