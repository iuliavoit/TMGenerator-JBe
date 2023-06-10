package matrices.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import matrices.dto.MatrixDTO;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MatrixTabDTO {
    private String name;
    private MatrixDTO data;
}
