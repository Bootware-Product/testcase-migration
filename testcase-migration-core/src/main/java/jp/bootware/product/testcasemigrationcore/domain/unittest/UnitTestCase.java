package jp.bootware.product.testcasemigrationcore.domain.unittest;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class UnitTestCase {

  @JsonProperty("columns")
  private List<UnitTestColumn> columns = new ArrayList<>();

  public UnitTestCase() {
    UnitTestColumn.resetPriorityCount();
  }
}
