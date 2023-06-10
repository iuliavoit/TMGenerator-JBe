package tables.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import tables.dto.TableDTO;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TableTabDTO {
    private String name;
    private TableDTO data;
}
