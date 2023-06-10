package matrices.model;

import java.util.Map;

public interface MatrixConditionalFormatter<T1, T2, R> {
    Map<String, String> format(T1 t1, T2 t2, R value);
}