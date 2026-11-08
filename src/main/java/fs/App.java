package fs;

import java.io.PrintStream;

public final class App {
    private App() { }

    public static DirectoryNode buildSampleTree() {
        DirectoryNode root = new DirectoryNode("project");
        root.addChild(new FileNode("README.md", 1200));

        DirectoryNode docs = new DirectoryNode("docs");
        root.addChild(docs);
        docs.addChild(new FileNode("report.pdf", 250000));
        docs.addChild(new FileNode("notes.txt", 800));

        DirectoryNode archive = new DirectoryNode("archive");
        docs.addChild(archive);
        archive.addChild(new FileNode("old.pdf", 90000));
        archive.addChild(new DirectoryNode("empty-archive"));

        DirectoryNode src = new DirectoryNode("src");
        root.addChild(src);
        DirectoryNode main = new DirectoryNode("main");
        src.addChild(main);
        main.addChild(new FileNode("Main.java", 4200));
        main.addChild(new FileNode("Util.java", 1800));

        DirectoryNode test = new DirectoryNode("test");
        src.addChild(test);
        test.addChild(new FileNode("MainTest.java", 2100));
        root.addChild(new DirectoryNode("empty"));
        return root;
    }

    public static void run(PrintStream out) {
        DirectoryNode root = buildSampleTree();
        TotalSizeVisitor size = new TotalSizeVisitor();
        size.traverse(root);
        out.println("Total size: " + size.getTotalSize() + " bytes");

        SearchVisitor search = new SearchVisitor(".pdf", 100000);
        search.traverse(root);
        out.println("PDF files >= 100000 bytes: " + search.getMatchingPaths());

        StatisticsVisitor stats = new StatisticsVisitor();
        stats.traverse(root);
        out.println("Statistics: files=" + stats.getFileCount()
                + ", directories=" + stats.getDirectoryCount()
                + ", maxDepth=" + stats.getMaxDepth());
    }

    public static void main(String[] args) {
        run(System.out);
    }
}
