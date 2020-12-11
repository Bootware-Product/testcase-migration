package jp.bootware.product.testcasemigrationcore.application.javadoc;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javaparser.JavaParser;
import com.github.javaparser.ParseResult;
import com.github.javaparser.ast.CompilationUnit;
import jp.bootware.product.testcasemigrationcore.domain.javadoc.JavaDocStore;
import jp.bootware.product.testcasemigrationcore.domain.javadoc.JavaDocVisitor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

@Service
public class TestCaseJavaDocAnalyzer {

  public Path parse(Path input, Path output) throws IOException {

    ParseResult<CompilationUnit> result = new JavaParser().parse(input);

    if (result.isSuccessful() && result.getResult().isPresent()) {
      String json = parse(result.getResult().get());
      Files.deleteIfExists(output);
      Files.createDirectories(output.getParent());
      return Files.write(output, json.getBytes(), StandardOpenOption.CREATE_NEW);
    }

    return null;
  }

  public String parse(CompilationUnit unit) throws JsonProcessingException {

    JavaDocStore store = new JavaDocStore();
    new JavaDocVisitor().visit(unit, store);

    ObjectMapper mapper = new ObjectMapper();
    String json = mapper.writeValueAsString(store.getCases());

    return json;
  }
}
