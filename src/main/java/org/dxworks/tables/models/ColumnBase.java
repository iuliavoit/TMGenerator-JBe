package org.dxworks.tables.models;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize(using = ColumnBaseSerializer.class)
public class ColumnBase {
}
