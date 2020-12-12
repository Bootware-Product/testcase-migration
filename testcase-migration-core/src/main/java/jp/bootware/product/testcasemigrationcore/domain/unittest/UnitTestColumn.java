package jp.bootware.product.testcasemigrationcore.domain.unittest;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.concurrent.atomic.AtomicInteger;

@AllArgsConstructor
@EqualsAndHashCode
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor
public class UnitTestColumn implements Comparable<UnitTestColumn> {

  @JsonIgnore
  private static AtomicInteger priorityCount = new AtomicInteger(0);

  @JsonProperty("priority")
  private final int priority = priorityCount.addAndGet(1);

  @JsonProperty("name")
  private String name;

  @JsonProperty("content")
  private String text;

  public static void resetPriorityCount() {
    priorityCount.set(0);
  }

  @Override
  public int compareTo(UnitTestColumn o) {
    return this.priority - o.priority;
  }
}
