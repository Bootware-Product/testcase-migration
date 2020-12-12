package jp.bootware.product.testcasemigrationcore.application.javadoc;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javaparser.JavaParser;
import com.github.javaparser.ParseResult;
import com.github.javaparser.ast.CompilationUnit;
import jp.bootware.product.testcasemigrationcore.domain.unittest.UnitTestCase;
import jp.bootware.product.testcasemigrationcore.domain.unittest.javadoc.JavaDocStoreUnit;
import jp.bootware.product.testcasemigrationcore.domain.unittest.javadoc.JavaDocVisitor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Collection;
import java.util.Collections;

@Service
public class UnitTestJavaDocAnalyzer {

  public Path parse(Path input, Path output) throws IOException {

    Collection<UnitTestCase> testCases = parse(input);

    String json = parse(testCases);
    Files.deleteIfExists(output);
    Files.createDirectories(output.getParent());
    return Files.write(output, json.getBytes(), StandardOpenOption.CREATE_NEW);
  }

  public String parse(Collection<UnitTestCase> testCases) throws JsonProcessingException {

    ObjectMapper mapper = new ObjectMapper();
    String json = mapper.writeValueAsString(testCases);

    return json;
  }

  public Collection<UnitTestCase> parse(Path input) throws IOException {

    ParseResult<CompilationUnit> result = new JavaParser().parse(input);

    if (result.isSuccessful() && result.getResult().isPresent()) {
      JavaDocStoreUnit store = new JavaDocStoreUnit();
      new JavaDocVisitor().visit(result.getResult().get(), store);
      return store.getCases();
    }

    return Collections.emptyList();
  }
}
