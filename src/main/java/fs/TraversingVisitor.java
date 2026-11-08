package fs;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Objects;

public abstract class TraversingVisitor implements Visitor {

    private final Deque<String> path = new ArrayDeque<>();

    public final void traverse(Element root) {
        Objects.requireNonNull(root, "root");

        path.clear();
        reset();
        root.accept(this);
    }

    protected abstract void reset();

    protected void onFile(FileNode file, String relativePath, int depth) {
    }

    protected void onDirectory(DirectoryNode directory,
                               String relativePath, int depth) {
    }

    @Override
    public final void visit(FileNode file) {
        String relativePath = path.isEmpty()
                ? file.getName()
                : String.join("/", path);

        onFile(file, relativePath, path.size());
    }

    @Override
    public final void visit(DirectoryNode directory) {
        onDirectory(directory, String.join("/", path), path.size());

        for (Node child : directory.getChildren()) {
            path.addLast(child.getName());

            try {
                child.accept(this);
            } finally {
                path.removeLast();
            }
        }
    }
}
