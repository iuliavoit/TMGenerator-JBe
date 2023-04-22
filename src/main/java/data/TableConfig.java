//package TableData;
//
//import Models.Column;
////import org.dxworks.dx.entities.code.component.Component;
//
//import java.util.Arrays;
//import java.util.Collections;
//import java.util.List;
//import java.util.Map;
//
//public class TableConfig {
//
//    private static int FILES_THRESHOLD = 100;
//
//    public static final List<Column<Component, String>> COLUMNS = Arrays.asList(
//            Column.<Component, String>builder()
//                    .id("fullpath")
//                    .name("Full Path")
//                    .computer(component -> component.name())
//                    .conditionalFormatter((component, s) -> {
//                        if(component.allFiles().size() > FILES_THRESHOLD)
//                            return Map.of("font-weight", "bold");
//                        return Collections.emptyMap();
//                    })
//                    .build()
////            new Column<Component, String>("component", "Component", null),
////            new Column<Component, String>("file", "File", null),
////            new Column<Component, Integer>("size", "Size", null),
////            new Column<Component, Integer>("atc", "ATC", null),
////            new Column<Component, Date>("firstChange", "First<br>Change", null),
////            new Column<Component, Date>("lastChange", "Last<br>Change", null),
////            new Column<Component, Integer>("allChanges", "All<br>Changes", null),
////            new Column<Component, Integer>("lastYearChanges", "Last Year<br>Changes", null),
////            new Column<Component, Integer>("lastYearGrowth", "Last Year<br>Growth", null),
////            new Column<Component, Integer>("lastYearChangeVolume", "Last Year<br>Change Volume", null),
////            new Column<Component, Integer>("lastYearAuthors", "Last Year<br>Authors", null),
////            new Column<Component, Integer>("pivotFile", "Pivot<br>File", null),
////            new Column<Component, Integer>("taskBottleneck", "Task<br>Bottleneck", null),
////            new Column<Component, Integer>("bazaar", "Bazaar", null),
////            new Column<Component, Integer>("bugMagnet", "Bug<br>Magnet", null),
////            new Column<Component, Integer>("weakOwnership", "Weak<br>Ownership", null)
//    );
//}
