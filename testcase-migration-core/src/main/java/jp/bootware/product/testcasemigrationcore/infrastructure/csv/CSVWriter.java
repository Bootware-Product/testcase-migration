package jp.bootware.product.testcasemigrationcore.infrastructure.csv;

import org.springframework.stereotype.Component;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Collection;

@Component
public class CSVWriter {

  public Path write(CSVData data, Path output) throws IOException {

    com.opencsv.CSVWriter writer = new com.opencsv.CSVWriter(new FileWriter(output.toFile()));

    Collection<String> header = data.getHeader().getValues();
    writer.writeNext(header.toArray(String[]::new));

    Collection<CSVRecord> records = data.getRecords();
    for (CSVRecord record : records) {
      String[] values = header.stream()
          .map(record::getValue)
          .toArray(String[]::new);
      writer.writeNext(values);
    }

    writer.flush();

    return output;
  }
}
