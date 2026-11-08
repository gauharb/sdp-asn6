package fs;

import java.util.List;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class SearchVisitorTest {
    @Test
    void combinesNameAndInclusiveSizeConditions() {
        DirectoryNode root = new DirectoryNode("root");
        root.addChild(new FileNode("report.txt", 300));
        root.addChild(new FileNode("small.txt", 299));
        root.addChild(new FileNode("image.png", 1000));
        SearchVisitor search = new SearchVisitor(".txt", 300);
        search.traverse(root);
        assertEquals(List.of("report.txt"), search.getMatchingPaths());
    }

    @Test
    void resultsAreSortedAndRelativeToSelectedRoot() {
        DirectoryNode root = new DirectoryNode("root");
        root.addChild(new FileNode("z.txt", 1));
        DirectoryNode sub = new DirectoryNode("alpha");
        root.addChild(sub);
        sub.addChild(new FileNode("a.txt", 1));
        SearchVisitor search = new SearchVisitor(".txt", 0);
        search.traverse(root);
        assertEquals(List.of("alpha/a.txt", "z.txt"), search.getMatchingPaths());
    }

    @Test
    void emptyAndNoMatchCasesReturnEmptyListAndResetBetweenRuns() {
        DirectoryNode root = new DirectoryNode("root");
        SearchVisitor search = new SearchVisitor(".java", 0);
        search.traverse(root);
        assertTrue(search.getMatchingPaths().isEmpty());
        root.addChild(new FileNode("Main.java", 10));
        search.traverse(root);
        assertEquals(List.of("Main.java"), search.getMatchingPaths());
        search.traverse(new DirectoryNode("other"));
        assertTrue(search.getMatchingPaths().isEmpty());
    }
}
