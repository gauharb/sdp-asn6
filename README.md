# Assignment 6 — Visitor Pattern: Advanced File System

Java 17 and Maven project implementing an in-memory hierarchical file system.

## Core model
- `Element`: `getName()` and `accept(Visitor)`
- `Node`: shared name, parent ownership, and name validation
- `FileNode`: nonnegative size
- `DirectoryNode`: named children, uniqueness and cycle prevention
- `Visitor`: overloaded `visit()` methods for double dispatch
- `TraversingVisitor`: traversal owner; `traverse(root)` resets state first
- `TotalSizeVisitor`: sum of file bytes
- `SearchVisitor`: case-sensitive filename fragment AND inclusive minimum byte size, sorted root-relative paths
- `StatisticsVisitor`: number of files/directories and depth (selected root = 0)

## Run
```bash
mvn test
mvn -q compile exec:java
```

The second command requires `exec-maven-plugin` configuration in pom.xml. Alternatively run `App.main()` from IntelliJ, or use:

```bash
mvn -q -DskipTests package
java -cp target/classes fs.App
```

## Architectural notes
Visitors own traversal via a shared `TraversingVisitor` base class. Each reachable node is visited once per `traverse` call. A new visitor adds an operation without altering existing elements; adding a new element type would require updating the `Visitor` interface and each concrete visitor. State is reset at the beginning of every traversal. Paths exclude the selected directory root and use `/`, and results are sorted lexicographically.

## Clean Code examples
1. **Naming:** `getMatchingPaths()` communicates the search result clearly.
2. **SRP:** `TotalSizeVisitor` only calculates the size; `SearchVisitor` only searches.
3. **Small methods:** `reset()` and `onFile()` have focused responsibilities.
4. **Encapsulation:** `DirectoryNode.getChildren()` returns an immutable copy.
5. **No type checks:** polymorphic `accept()` selects the appropriate `visit()` overload, avoiding `instanceof` chains.

## Verification
Six JUnit 5 test classes cover double dispatch, nested size, combined search conditions, deterministic paths, statistics, empty structures, repeated executions, and invalid mutations. Run `mvn test` locally and record the actual result in your PDF report.

## Required submission
Attach the GitHub repository URL, UML, results of locally run tests, sample console output and an up-to-three-page PDF report. Implement and explain the code independently in accordance with course academic-integrity requirements.
