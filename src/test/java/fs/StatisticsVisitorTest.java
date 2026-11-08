package fs;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class StatisticsVisitorTest {
    @Test
    void countsRootDirectoriesFilesAndMaximumDepth() {
        DirectoryNode root = new DirectoryNode("root");
        root.addChild(new FileNode("a", 0));
        DirectoryNode sub = new DirectoryNode("sub");
        root.addChild(sub);
        DirectoryNode deep = new DirectoryNode("deep");
        sub.addChild(deep);
        deep.addChild(new FileNode("b", 1));
        StatisticsVisitor visitor = new StatisticsVisitor();
        visitor.traverse(root);
        assertEquals(2, visitor.getFileCount());
        assertEquals(3, visitor.getDirectoryCount());
        assertEquals(3, visitor.getMaxDepth());
    }

    @Test
    void emptyDirectoryAndStandaloneFileHaveDepthZero() {
        StatisticsVisitor visitor = new StatisticsVisitor();
        visitor.traverse(new DirectoryNode("empty"));
        assertEquals(0, visitor.getFileCount());
        assertEquals(1, visitor.getDirectoryCount());
        assertEquals(0, visitor.getMaxDepth());
        visitor.traverse(new FileNode("single.txt", 2));
        assertEquals(1, visitor.getFileCount());
        assertEquals(0, visitor.getDirectoryCount());
        assertEquals(0, visitor.getMaxDepth());
    }
}
