package jp.bootware.product.testcasemigrationcore.infrastructure.csv;

import lombok.Getter;

import javax.annotation.PostConstruct;
import java.util.Collection;

@Getter
public class CSVData {

  private CSVHeader header;
  private Collection<CSVRecord> records;

  public CSVData(CSVHeader header, Collection<CSVRecord> records) {
    this.header = header;
    this.records = records;
  }

  @PostConstruct
  void validation() {
    boolean ng = records.stream().anyMatch(record -> !header.getValues().containsAll(record.getColumns()));
    if (ng) {
      throw new RuntimeException();
    }
  }
}
