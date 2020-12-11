package jp.bootware.product.testcasemigrationcore.application.javadoc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.opencsv.CSVWriter;
import jp.bootware.product.testcasemigrationcore.domain.javadoc.JavaDocAnnotation;
import jp.bootware.product.testcasemigrationcore.domain.javadoc.JavaDocCase;
import org.springframework.stereotype.Service;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;

@Service
public class TestCaseConverter {

  public void json2csv(Path input, Path output) throws IOException {

    JavaDocCase[] cases = new ObjectMapper().readValue(input.toFile(), JavaDocCase[].class);

    String[] columns = Arrays.stream(cases)
        .map(JavaDocCase::getAnnotations)
        .flatMap(List::stream)
        .sorted()
        .map(JavaDocAnnotation::getName)
        .distinct()
        .toArray(String[]::new);

    CSVWriter writer = new CSVWriter(new FileWriter(output.toFile()));
    writer.writeNext(columns);

    Arrays.stream(cases)
        .map(c -> c.getAnnotations().stream().map(JavaDocAnnotation::getText).toArray(String[]::new))
        .forEachOrdered(writer::writeNext);

    writer.flush();
  }
}
