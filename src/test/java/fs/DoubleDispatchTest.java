package fs;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class DoubleDispatchTest {
    @Test
    void eachElementSelectsItsOwnOverloadedVisitMethod() {
        class CountingVisitor implements Visitor {
            int files;
            int directories;
            public void visit(FileNode file) { files++; }
            public void visit(DirectoryNode directory) { directories++; }
        }
        CountingVisitor visitor = new CountingVisitor();
        Element file = new FileNode("hello.txt", 15);
        Element directory = new DirectoryNode("documents");
        file.accept(visitor);
        directory.accept(visitor);
        assertEquals(1, visitor.files);
        assertEquals(1, visitor.directories);
    }
}
