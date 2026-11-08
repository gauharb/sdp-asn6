package fs;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class TotalSizeVisitorTest {
    @Test
    void sumsFilesAcrossDifferentNestingLevels() {
        DirectoryNode root = new DirectoryNode("root");
        root.addChild(new FileNode("a.txt", 12));
        DirectoryNode nested = new DirectoryNode("nested");
        root.addChild(nested);
        nested.addChild(new FileNode("b.txt", 25));
        DirectoryNode deeper = new DirectoryNode("deeper");
        nested.addChild(deeper);
        deeper.addChild(new FileNode("c.txt", 13));
        TotalSizeVisitor visitor = new TotalSizeVisitor();
        visitor.traverse(root);
        assertEquals(50, visitor.getTotalSize());
    }

    @Test
    void emptyDirectoryHasZeroSizeAndRepeatedRunsResetState() {
        DirectoryNode root = new DirectoryNode("root");
        TotalSizeVisitor visitor = new TotalSizeVisitor();
        visitor.traverse(root);
        assertEquals(0, visitor.getTotalSize());
        root.addChild(new FileNode("a.txt", 6));
        visitor.traverse(root);
        assertEquals(6, visitor.getTotalSize());
        visitor.traverse(root);
        assertEquals(6, visitor.getTotalSize());
    }
}
