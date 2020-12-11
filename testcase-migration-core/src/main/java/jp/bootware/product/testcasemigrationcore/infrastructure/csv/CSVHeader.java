package jp.bootware.product.testcasemigrationcore.infrastructure.csv;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class CSVHeader {

  private List<String> header;

  public CSVHeader(String[] columns) {
    header = Arrays.asList(columns);
  }

  public Collection<String> getValues() {
    return header;
  }
}
