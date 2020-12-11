package jp.bootware.product.testcasemigrationcore.domain.javadoc;

import jp.bootware.product.testcasemigrationcore.domain.CaseStore;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class JavaDocStore extends CaseStore<JavaDocCase> {

  public void addCase(final String javadocContent) {

    List<JavaDocAnnotation> annotations = new ArrayList<>();

    String content = javadocContent;
    while (content.contains("@")) {

      String find = "";

      if (StringUtils.countMatches(content, "@") >= 2) {
        find = StringUtils.substringBetween(content, "@", "@");
      } else if (StringUtils.countMatches(content, "@") == 1) {
        find = StringUtils.substringAfter(content, "@");
      }

      content = content.replaceFirst("@", "");
      find = find.trim().replace("*", "");

      if (StringUtils.isEmpty(find)) {
        continue;
      }

      String name = StringUtils.substringBefore(find, " ")
          .trim()
          .replace(System.lineSeparator(), "");

      String text = Arrays.stream(
          StringUtils.substringAfter(find, " ").trim().split("(\n|\r\n)"))
          .map(String::trim)
          .collect(Collectors.joining(System.lineSeparator()));

      JavaDocAnnotation annotation = new JavaDocAnnotation(name, text);
      annotations.add(annotation);
    }

    JavaDocCase javaDocCase = new JavaDocCase();
    javaDocCase.setAnnotations(annotations);

    addCase(javaDocCase);
  }
}
