package jp.bootware.product.testcasemigrationcore.domain.javadoc;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class JavaDocAnnotation {

  @JsonProperty("name")
  private String name;

  @JsonProperty("content")
  private String text;
}
