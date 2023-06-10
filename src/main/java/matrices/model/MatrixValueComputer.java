package matrices.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.function.BiFunction;
import java.util.function.Function;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MatrixValueComputer<T1, T2> {
    private String id;
    private String name;

    private BiFunction<T1, T2, Number> valueComputer = (t1, t2) -> null;

    private Function<Number, String> displayFormatter = (number) -> null;

    private MatrixConditionalFormatter<T1, T2, Number> conditionalFormatter = (t1, t2, number) -> null;
}

