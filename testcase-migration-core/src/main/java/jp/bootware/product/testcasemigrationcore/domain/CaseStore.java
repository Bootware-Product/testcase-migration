package jp.bootware.product.testcasemigrationcore.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public abstract class CaseStore<C extends Case> {

  private List<C> cases = new ArrayList<>();

  public void addCase(C c) {
    cases.add(c);
  }
}
