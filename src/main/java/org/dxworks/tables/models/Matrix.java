package org.dxworks.tables.models;

import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Function;

public class Matrix<T1, T2> {

    private List<Column<T1, ?>> entity1PaddingColumns;
    private List<Column<T2, ?>> entity2PaddingColumns;
    private List<MatrixValueComputer<T1,T2>> valueComputers;

}

class MatrixValueComputer<T1,T2> {
    private String id;
    private String name;

    private BiFunction<T1,T2, Number> valueComputer = (t1, t2) -> null;

    private Function<Number, String> displayFormatter = (number) -> null;

    private MatrixConditionalFormatter<T1,T2,Number> conditionalFormatter = (t1, t2, number) -> null;
}

interface MatrixConditionalFormatter<T1, T2, R> {
    Map<String, String> format(T1 t1, T2 t2, R value);
}