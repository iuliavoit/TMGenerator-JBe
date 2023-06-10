package matrices.dto;

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
public class MatrixDTO {
    private String name;
    private String type;
    private List<ColumnDTO> entity1PaddingColumns;
    private List<ColumnDTO> entity2PaddingColumns;
    private Map<String, Map<String, CellDTO>> entity1PaddingData;
    private Map<String, Map<String, CellDTO>> entity2PaddingData;
    private Map<String, List<Map<String, Object>>> data;

}
