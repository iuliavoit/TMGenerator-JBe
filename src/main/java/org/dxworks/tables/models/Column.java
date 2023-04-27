package org.dxworks.tables.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    protected List<Column> childrenColumns;

    protected Function<T, R> computer;
    protected BiFunction<T, R, Map<String, String>> conditionalFormatter;
    protected Function<R, String> displayFormatter;


}
