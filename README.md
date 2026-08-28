# Lab 3: Doubly Linked List

## Overview

A Java implementation of a doubly linked list of integers (`DoublyIntLinkedList`) supporting
head/tail insertion, value-based removal, bidirectional traversal, and structural invariant
validation (correct `prev`/`next` pointer consistency after every mutation).

## Directory Structure

03-linked-lists/
├── src/
│ └── DoublyIntLinkedList.java # Core implementation
├── test/
│ ├── LabTestRunner.java # Assertion-based test suite
│ └── ComplexityMeasurement.java # Empirical timing across input sizes
├── AGENT_LOG.md # Plan, review, build, and audit checkpoints
├── REFLECTION.md # Learning reflection + measured complexity
└── run-tests.bat # Compile + run script
`out/` (compiled `.class` files) is generated locally and excluded from this repo.

## Implementation

**`DoublyIntLinkedList.java`**

| Method | Description |
|---|---|
| `addFirst(int value)` | Insert a node at the head |
| `addLast(int value)` | Insert a node at the tail |
| `removeFirstOccurrence(int value)` | Remove the first matching node; returns `true`/`false` |
| `size()` | Return the current element count |
| `isEmpty()` | Return whether the list has zero elements |
| `toArrayForward()` | Return values head → tail as `int[]` |
| `toArrayBackward()` | Return values tail → head as `int[]` |
| `validateInvariants()` | Verify `head.prev == null`, `tail.next == null`, and `node.next.prev == node` for every node, plus size consistency |

## Testing

**`LabTestRunner.java`** — 28 assertions across all 6 required cases:
- Empty list
- Single node (including removing the only node)
- Head deletion
- Tail deletion
- Missing value (no-op removal)
- Duplicate values (only first occurrence removed)

**`ComplexityMeasurement.java`** — measures `addLast`, worst-case `removeFirstOccurrence`,
and `validateInvariants` at n = 1,000 / 10,000 / 100,000 to compare against predicted Big-O.

## Building & Running

From the `03-linked-lists` directory:

```powershell
.\run-tests.bat
```

This compiles `src/` and `test/` into `out/` and runs `LabTestRunner` with `-ea`
(assertions enabled). Expected result: **28 passed, 0 failed**.

To run the complexity measurement separately:

```powershell
javac -d out src\DoublyIntLinkedList.java test\ComplexityMeasurement.java
java -cp out ComplexityMeasurement
```

## Complexity

| Operation | Best/Avg | Worst | Notes |
|---|---|---|---|
| `addFirst` / `addLast` | O(1) | O(1) | Head/tail references maintained directly |
| `removeFirstOccurrence` | O(1) (head match) | O(n) | Search dominates when not at head |
| `toArrayForward` / `toArrayBackward` | O(n) | O(n) | Full traversal, returns a snapshot array |
| `validateInvariants` | O(n) | O(n) | Every link is inspected |

**Auxiliary space:** O(1) for `addFirst`/`addLast`/`removeFirstOccurrence`/`validateInvariants`;
O(n) for `toArrayForward`/`toArrayBackward` (the returned array).

Measured timings and predicted-vs-actual analysis are in [`REFLECTION.md`](./REFLECTION.md).

## Agent Workflow

This lab followed a checkpointed Plan → Test → Build → Audit workflow using OpenCode agents.
Full plan, plan-review feedback, build-diff review, and audit ([`@test-reviewer`](.),
[`@complexity-auditor`](.)) notes are recorded in [`AGENT_LOG.md`](./AGENT_LOG.md).

## Submission Checklist

- [x] No TODOs remain in `src/` or `test/`
- [x] Terminal output pasted, run with `-ea`
- [x] `AGENT_LOG.md` complete
- [x] `REFLECTION.md` complete
- [x] `out/`, credentials, session links excluded
