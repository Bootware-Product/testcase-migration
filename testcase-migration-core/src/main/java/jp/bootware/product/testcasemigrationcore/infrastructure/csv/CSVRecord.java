package jp.bootware.product.testcasemigrationcore.infrastructure.csv;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

public class CSVRecord {

  private Map<String, String> map;

  public CSVRecord(String[] columns) {
    this(new CSVHeader(columns));
  }

  public CSVRecord(CSVHeader header) {
    map = new LinkedHashMap<>();
    header.getValues().forEach(c -> map.put(c, ""));
  }

  public void setValue(String column, String value) {
    if (!map.containsKey(column)) {
      throw new NullPointerException("Column does not exists: " + column);
    }
    map.put(column, value);
  }

  public String getValue(String key) {
    return map.getOrDefault(key, "");
  }

  public Set<String> getColumns() {
    return map.keySet();
  }

  public Collection<String> getValues() {
    return map.values();
  }
}
