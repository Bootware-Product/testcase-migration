package jp.bootware.product.testcasemigrationcore.application.javadoc;

import jp.bootware.product.testcasemigrationcore.domain.UnitTestCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Collection;

@Service
public class UnitTestMigrator {

  @Autowired
  UnitTestJavaDocAnalyzer analyzer;

  @Autowired
  UnitTestWriter writer;

  public Path migrateCsv(Path input, Path csvOutput) throws IOException {

    Collection<UnitTestCase> testCases = analyzer.parse(input);

    return writer.writeCsv(testCases, csvOutput);
  }
}
