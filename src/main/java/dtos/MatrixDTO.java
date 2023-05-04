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
public class MatrixDTO<T1, T2>  {
    protected String name;
    private List<ColumnDTO> entity1PaddingColumns;
    private List<ColumnDTO> entity2PaddingColumns;
}
