
package org.dxworks.tables.models;
import dtos.ColumnDTO;
import lombok.*;

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
}

