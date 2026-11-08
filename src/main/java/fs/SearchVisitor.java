
package fs;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public final class SearchVisitor extends TraversingVisitor {

    private final String nameCriterion;
    private final long minSize;
    private final List<String> matches = new ArrayList<>();

    public SearchVisitor(String nameCriterion, long minSize) {
        this.nameCriterion = Objects.requireNonNull(nameCriterion);

        if (minSize < 0) {
            throw new IllegalArgumentException(
                    "Minimum size cannot be negative"
            );
        }

        this.minSize = minSize;
    }

    @Override
    protected void reset() {
        matches.clear();
    }

    @Override
    protected void onFile(FileNode file, String relativePath, int depth) {
        boolean nameMatches = file.getName().contains(nameCriterion);
        boolean sizeMatches = file.getSize() >= minSize;

        if (nameMatches && sizeMatches) {
            matches.add(relativePath);
        }
    }

    public List<String> getMatchingPaths() {
        return matches.stream()
                .sorted()
                .toList();
    }
}
