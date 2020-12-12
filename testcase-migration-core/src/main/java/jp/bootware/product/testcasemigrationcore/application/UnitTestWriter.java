package jp.bootware.product.testcasemigrationcore.application;

import com.fasterxml.jackson.databind.ObjectMapper;
import jp.bootware.product.testcasemigrationcore.domain.unittest.UnitTestCase;
import jp.bootware.product.testcasemigrationcore.domain.unittest.UnitTestStore;
import jp.bootware.product.testcasemigrationcore.infrastructure.csv.CSVData;
import jp.bootware.product.testcasemigrationcore.infrastructure.csv.CSVWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Collection;

@Service
public class UnitTestWriter {

  @Autowired
  CSVWriter csvWriter;

  public Path writeCsv(Path jsonInput, Path csvOutput) throws IOException {

    ObjectMapper mapper = new ObjectMapper();

    Collection<UnitTestCase> testCases = Arrays.asList(mapper.readValue(jsonInput.toFile(), UnitTestCase[].class));

    return writeCsv(testCases, csvOutput);
  }

  public Path writeCsv(Collection<UnitTestCase> testCases, Path csvOutput) throws IOException {

    UnitTestStore store = new UnitTestStore(testCases);

    CSVData csvData = store.toCSVData();

    return csvWriter.write(csvData, csvOutput);
  }
}
