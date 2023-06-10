package tables.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import shared.dto.CellDTO;
import shared.dto.ColumnDTO;

import java.util.List;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TableDTO {
    private String name;
    private String type;
    private List<ColumnDTO> columns;
    private Map<String, Map<String, CellDTO>> data;
}
