
package fs;

import java.util.Objects;

public abstract sealed class Node implements Element
        permits FileNode, DirectoryNode {

    private final String name;
    private DirectoryNode parent;

    protected Node(String name) {
        Objects.requireNonNull(name, "name must not be null");

        if (name.isBlank() || name.contains("/")
                || name.contains("\\")
                || name.equals(".") || name.equals("..")) {
            throw new IllegalArgumentException("Invalid name: " + name);
        }

        this.name = name;
    }

    @Override
    public final String getName() {
        return name;
    }

    public final boolean hasParent() {
        return parent != null;
    }

    final DirectoryNode parentOrNull() {
        return parent;
    }

    final void attachTo(DirectoryNode newParent) {
        Objects.requireNonNull(newParent);

        if (parent != null) {
            throw new IllegalStateException("Node already has a parent");
        }

        parent = newParent;
    }
}

