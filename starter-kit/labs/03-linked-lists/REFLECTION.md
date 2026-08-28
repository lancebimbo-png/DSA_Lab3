# Reflection — Lab 3: Doubly Linked List

## What I learned
Implementing this list made the pointer bookkeeping in a doubly linked list much more
concrete than just reading about it. The trickiest part was realizing that deleting a
node isn't one uniform operation — the head, tail, middle, and only-node cases all need
different handling, because head/tail aren't just regular nodes, they're also the entry
points the rest of the list depends on. I also better understood why validateInvariants
matters: it's easy to write an add/remove method that looks right but silently breaks
the prev/next symmetry, and only a full pass that checks node.next.prev == node in both
directions would catch that.

## Where the agent helped
Once I had written out full pseudocode myself, the Build agent translated it into working
Java quickly and correctly on the first pass — no bugs in the actual pointer logic. It also
caught real gaps in my test coverage during the audit step (missing assertions for removing
the only node, and for checking invariants after a failed removal) that I hadn't thought to
test myself.

## Where the agent was wrong or limited (specific example)
Early in setup, OpenCode's Build agent was configured to a model ("x-preview-f-free") that
wasn't actually supported, and it failed with an error instead of generating any code. The
agent gave no useful explanation or recovery step — I had to independently recognize this
was a model-availability problem (not an issue with my prompt) and manually switch to a
different model before Build could proceed. This showed a real limitation: the agent
couldn't diagnose or work around its own configuration failure.

## Complexity: predicted vs measured

| Operation | Predicted | Measured (n=1,000 / 10,000 / 100,000) | Matches? |
|---|---|---|---|
| addLast | O(1) per op | 0.32ms / 0.17ms / 1.44ms | Roughly consistent — the n=1,000 result is skewed high by JVM warm-up cost on the first loop run, not by addLast itself. |
| removeFirstOccurrence (worst case) | O(n) | 0.01ms / 0.07ms / 0.77ms | Matches — time grows ~10x for each ~10x increase in n, consistent with linear scaling. |
| validateInvariants | O(n) | 0.02ms / 0.11ms / 1.18ms | Matches — same ~10x-per-10x linear growth pattern. |

Counter definition: elapsed wall-clock time measured with System.nanoTime() around each
operation, converted to milliseconds. This measures total operation time including JVM
overhead, not pure algorithmic step count.

Measured timings for removeFirstOccurrence (worst case) and validateInvariants both grew
roughly 10x when n grew 10x, matching the predicted O(n) behavior. The addLast timing at
n=1,000 was skewed higher than expected, likely due to JVM warm-up/JIT overhead affecting
the first loop run rather than addLast itself being non-constant time.

## Submission checklist
- [x] No TODOs remain in src/ or test/
- [x] Terminal output pasted, run with -ea
- [x] AGENT_LOG.md complete
- [x] REFLECTION.md complete
- [ ] out/, credentials, session links excluded