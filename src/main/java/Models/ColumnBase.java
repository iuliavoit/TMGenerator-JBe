package Models;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize(using = ColumnBaseSerializer.class)
public class ColumnBase {
}
