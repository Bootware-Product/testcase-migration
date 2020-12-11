package jp.bootware.product.testcasemigrationcore.application.javadoc;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.nio.file.Path;

@SpringBootTest
class TestCaseConverterTest {

  @Autowired
  TestCaseJavaDocAnalyzer parser;

  @Autowired
  TestCaseConverter converter;

  Path input = Path.of("src/test/java/jp/bootware/product/testcasemigrationcore")
      .resolve("TestcaseMigrationApplicationTests.java")
      .toAbsolutePath()
      .normalize();

  Path output = Path.of("target/output").toAbsolutePath().normalize();

  @Test
  public void test() throws IOException {
    Path jsonPath = parser.parse(input, output.resolve("testConverter.json"));
    converter.json2csv(jsonPath, output.resolve("testConverter.csv"));
  }
}