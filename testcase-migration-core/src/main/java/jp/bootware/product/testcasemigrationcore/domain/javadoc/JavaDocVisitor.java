package jp.bootware.product.testcasemigrationcore.domain.javadoc;

import com.github.javaparser.ast.comments.JavadocComment;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

public class JavaDocVisitor extends VoidVisitorAdapter<JavaDocStore> {

  public void visit(final JavadocComment n, final JavaDocStore arg) {
    String content = n.getContent();
    arg.addCase(content);
    n.getComment().ifPresent((l) -> {
      l.accept(this, arg);
    });
  }
}

