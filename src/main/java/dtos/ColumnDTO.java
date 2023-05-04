package dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ColumnDTO {
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
        protected List<ColumnDTO> childrenColumns;
}
