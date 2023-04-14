package Models;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.util.ArrayList;
import java.util.List;

public class Table<T> {
    public String name;
    public List<ColumnBase> columns;

    //private PaceNature t = new PaceNature();

    public Table(String name, List<ColumnBase> columns) {
        this.name = name;
        this.columns = columns;
    }

    public String generateJson() {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.writeValueAsString(columns);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }


}
