
package fs;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public final class DirectoryNode extends Node {

    private final Map<String, Node> children = new LinkedHashMap<>();

    public DirectoryNode(String name) {
        super(name);
    }

    public void addChild(Node child) {
        Objects.requireNonNull(child, "child");

        if (children.containsKey(child.getName())) {
            throw new IllegalArgumentException(
                    "Duplicate name: " + child.getName()
            );
        }

        if (child.hasParent()) {
            throw new IllegalArgumentException(
                    "Node already belongs to another directory"
            );
        }

        for (Node current = this; current != null;
             current = current.parentOrNull()) {

            if (current == child) {
                throw new IllegalArgumentException(
                        "Cycle detected"
                );
            }
        }

        child.attachTo(this);
        children.put(child.getName(), child);
    }

    public List<Node> getChildren() {
        return List.copyOf(children.values());
    }

    @Override
    public void accept(Visitor visitor) {
        Objects.requireNonNull(visitor, "visitor").visit(this);
    }
}
