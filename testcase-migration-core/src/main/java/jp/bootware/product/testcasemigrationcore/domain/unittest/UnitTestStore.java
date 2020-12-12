package jp.bootware.product.testcasemigrationcore.domain.unittest;

import jp.bootware.product.testcasemigrationcore.infrastructure.csv.CSVData;
import jp.bootware.product.testcasemigrationcore.infrastructure.csv.CSVHeader;
import jp.bootware.product.testcasemigrationcore.infrastructure.csv.CSVRecord;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor
public class UnitTestStore {

  private Collection<UnitTestCase> cases = new ArrayList<>();

  public UnitTestStore(Collection<UnitTestCase> cases) {
    this.cases = cases;
  }

  public void addCase(UnitTestCase c) {
    cases.add(c);
  }

  public String[] getColumns() {

    String[] columns = cases.stream()
        .map(UnitTestCase::getColumns)
        .flatMap(List::stream)
        .sorted()
        .map(UnitTestColumn::getName)
        .distinct()
        .toArray(String[]::new);

    return columns;
  }

  public CSVData toCSVData() {
    CSVHeader header = new CSVHeader(getColumns());
    Collection<CSVRecord> records = getCases().stream()
        .map(UnitTestCase::getColumns)
        .map(columns -> {
          CSVRecord record = new CSVRecord(header);
          columns.forEach(c -> record.setValue(c.getName(), c.getText()));
          return record;
        })
        .collect(Collectors.toList());
    return new CSVData(header, records);
  }
}
