# Visitor Pattern — Advanced File System

## Overview
This project implements the Visitor Design Pattern in Java 17 using an in-memory hierarchical file system. It demonstrates double dispatch, recursive traversal, and extensibility.

## Features
- **TotalSizeVisitor** — calculates the total size of files.
- **SearchVisitor** — searches files by name and minimum size.
- **StatisticsVisitor** — counts files, directories, and maximum depth.
- **Tree Integrity** — prevents duplicate names, cycles, negative file sizes, and multiple parents.

## Design
- `Element` — common interface for file system elements.
- `FileNode` and `DirectoryNode` — represent files and directories.
- `Visitor` — defines overloaded visit methods.
- `TraversingVisitor` — handles recursive traversal.

Traversal is managed by visitors. Each operation starts with a reset to prevent accumulated results.

New visitors can be added without modifying existing elements. Adding a new element type requires updating the Visitor interface and implementations.

## Technologies
- Java 17
- Maven
- JUnit 5

## How to Run

Run tests:

`mvn clean test`

Compile:

`mvn compile`

Run application:

`java -cp target/classes fs.App`

## Sample Output

    Total size: 350100 bytes
    PDF files >= 100000 bytes: [docs/report.pdf]
    Statistics: files=7, directories=8, maxDepth=3

## Testing
The project includes 13 JUnit 5 tests covering double dispatch, traversal, search, statistics, and tree integrity.

**Test Results:** 13 passed, 0 failures, 0 errors.

## Clean Code
The implementation uses meaningful naming, single-responsibility classes, small methods, encapsulation, and polymorphism instead of type-checking chains.

