//package Models;
//
//import java.time.format.DateTimeFormatter;
//import java.util.Dictionary;
//import java.util.List;
//import java.util.function.BiFunction;
//import java.util.function.Function;
//
//public class DateColumn<T> extends Column<T, java.time.LocalDateTime> {
//    private String dateFormat;
//
//    public DateColumn(
//            String id,
//            String name,
//            String nameStyle,
//            String dateFormat,
//            Function<T, java.time.LocalDateTime> computer,
//            BiFunction<T, java.time.LocalDateTime, Dictionary<String, String>> conditionalFormatter,
//            String description,
//            String descriptionStyle,
//            Boolean sortable,
//            Boolean borderL,
//            Boolean borderR,
//            Boolean borderB,
//            List<ColumnBase> childrenColumns
//    ) {
//        super(
//                id,
//                name,
//                nameStyle,
//                computer,
//                conditionalFormatter,
//                description,
//                descriptionStyle,
//                sortable,
//                borderL,
//                borderR,
//                borderB,
//                childrenColumns
//        );
//        this.dateFormat = dateFormat;
//        this.displayFormatter = value -> value.format(DateTimeFormatter.ofPattern(dateFormat));
//    }
//
//    public String getDateFormat() {
//        return dateFormat;
//    }
//}
