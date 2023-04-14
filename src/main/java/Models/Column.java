package Models;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;


public class Column<T, R> extends ColumnBase {
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getNameStyle() {
        return nameStyle;
    }

    public String getDescription() {
        return description;
    }

    public String getDescriptionStyle() {
        return descriptionStyle;
    }

    public Boolean getSortable() {
        return sortable;
    }

    public Boolean getBorderL() {
        return borderL;
    }

    public Boolean getBorderR() {
        return borderR;
    }

    public Boolean getBorderB() {
        return borderB;
    }

    public List<ColumnBase> getChildrenColumns() {
        return childrenColumns;
    }

    public Function<T, R> getComputer() {
        return computer;
    }

    public BiFunction<T, R, Dictionary<String, String>> getConditionalFormatter() {
        return conditionalFormatter;
    }

    public Function<R, String> getDisplayFormatter() {
        return displayFormatter;
    }

    public String getType() {
        return type;
    }

    private String id;
    private String name;
    private String nameStyle;
    private String description;
    private String descriptionStyle;
    private Boolean sortable;
    private Boolean borderL;
    private Boolean borderR;
    private Boolean borderB;
    private List<ColumnBase> childrenColumns;

    private Function<T, R> computer;
    private BiFunction<T, R, Dictionary<String, String>> conditionalFormatter;
    Function<R, String> displayFormatter;

    private String type;

    public Column(
            String id,
            String name,
            String nameStyle,
            Function<T, R> computer,
            BiFunction<T, R, Dictionary<String, String>> conditionalFormatter,
            String description,
            String descriptionStyle,
            Boolean sortable,
            Boolean borderL,
            Boolean borderR,
            Boolean borderB,
            List<ColumnBase> childrenColumns
    ) {
        this.id = id;
        this.computer = computer;
        this.name = name;
        this.nameStyle = nameStyle != null ? nameStyle : "";
        this.conditionalFormatter = conditionalFormatter;
        this.description = description;
        this.descriptionStyle = descriptionStyle != null ? descriptionStyle : "";
        this.sortable = sortable != null ? sortable : true;
        this.childrenColumns = childrenColumns != null ? childrenColumns : new ArrayList<ColumnBase>();

        R tempValue = null;
        try {
            tempValue = computer.apply(null);
        } catch (Exception e) {
            // Ignore exception
        }
        this.type = tempValue != null ? tempValue.getClass().getSimpleName() : "";
        this.borderL = borderL;
        this.borderR = borderR;
        this.borderB = borderB;
    }

}
