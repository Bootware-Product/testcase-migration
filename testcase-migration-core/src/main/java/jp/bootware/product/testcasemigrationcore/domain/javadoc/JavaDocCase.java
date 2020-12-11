package jp.bootware.product.testcasemigrationcore.domain.javadoc;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import jp.bootware.product.testcasemigrationcore.domain.Case;
import lombok.Data;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class JavaDocCase extends Case {

  @JsonProperty("columns")
  private List<JavaDocAnnotation> annotations;

  public JavaDocCase() {
    JavaDocAnnotation.resetPriorityCount();
  }
}
