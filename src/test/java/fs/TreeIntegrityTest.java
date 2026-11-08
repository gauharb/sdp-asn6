package fs;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TreeIntegrityTest {
    @Test
    void rejectsNegativeSizesAndInvalidNames() {
        assertThrows(IllegalArgumentException.class, () -> new FileNode("bad.txt", -1));
        assertThrows(IllegalArgumentException.class, () -> new DirectoryNode(""));
        assertThrows(IllegalArgumentException.class, () -> new DirectoryNode("a/b"));
    }

    @Test
    void rejectsDuplicateSiblingNames() {
        DirectoryNode root = new DirectoryNode("root");
        root.addChild(new FileNode("same", 1));
        assertThrows(IllegalArgumentException.class,
                () -> root.addChild(new DirectoryNode("same")));
        assertEquals(1, root.getChildren().size());
    }

    @Test
    void rejectsMultipleParentsAndCycles() {
        DirectoryNode root = new DirectoryNode("root");
        DirectoryNode child = new DirectoryNode("child");
        DirectoryNode other = new DirectoryNode("other");
        root.addChild(child);
        assertThrows(IllegalArgumentException.class, () -> other.addChild(child));
        assertThrows(IllegalArgumentException.class, () -> child.addChild(root));
        assertThrows(IllegalArgumentException.class, () -> root.addChild(root));
    }

    @Test
    void childrenAreNotWritableFromOutside() {
        DirectoryNode root = new DirectoryNode("root");
        assertThrows(UnsupportedOperationException.class,
                () -> root.getChildren().add(new FileNode("x", 1)));
    }
}
