package jp.bootware.product.testcasemigrationcore.domain.unittest.javadoc;

import jp.bootware.product.testcasemigrationcore.domain.unittest.UnitTestCase;
import jp.bootware.product.testcasemigrationcore.domain.unittest.UnitTestColumn;
import jp.bootware.product.testcasemigrationcore.domain.unittest.UnitTestStore;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class JavaDocStoreUnit extends UnitTestStore {

  public void addCase(final String javadocContent) {

    List<UnitTestColumn> unitTestColumns = new ArrayList<>();

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

      UnitTestColumn unitTestColumn = new UnitTestColumn(name, text);
      unitTestColumns.add(unitTestColumn);
    }

    UnitTestCase unitTestCase = new UnitTestCase();
    unitTestCase.setColumns(unitTestColumns);

    addCase(unitTestCase);
  }
}
