## Plan (student-authored, before any Build edits)

### Expected behavior

- addFirst(value): Create a new node. If the list is empty, the new node becomes both
  head and tail. Otherwise, link the new node before the current head (new.next = head,
  head.prev = new), then update head to point to the new node. Increment size.

- addLast(value): Create a new node. If the list is empty, the new node becomes both
  head and tail. Otherwise, link the new node after the current tail (new.prev = tail,
  tail.next = new), then update tail to point to the new node. Increment size.

- removeFirstOccurrence(value): Walk from head to tail looking for the first node whose
  value matches. If not found, return false. If found:
    - if it's the only node, set head = tail = null
    - else if it's the head, move head to head.next and set new head.prev = null
    - else if it's the tail, move tail to tail.prev and set new tail.next = null
    - else, connect its prev and next directly to each other (node.prev.next = node.next,
      node.next.prev = node.prev)
  Decrement size, return true.

- toArrayForward(): Allocate an int[] of size `size`. Walk from head to tail, filling the
  array in order. Return it.

- toArrayBackward(): Allocate an int[] of size `size`. Walk from tail to head, filling the
  array in order. Return it.

- validateInvariants(): Walk from head to tail. Check: head.prev == null (if head exists),
  tail.next == null (if tail exists), and for every node, node.next.prev == node when
  node.next is not null. Also count nodes while walking and confirm the count equals `size`.
  Return true only if all checks pass.

### Pseudocode

addFirst(value):
  newNode = Node(value)
  if head == null:
      head = tail = newNode
  else:
      newNode.next = head
      head.prev = newNode
      head = newNode
  size++

addLast(value):
  newNode = Node(value)
  if tail == null:
      head = tail = newNode
  else:
      newNode.prev = tail
      tail.next = newNode
      tail = newNode
  size++

removeFirstOccurrence(value):
  current = head
  while current != null:
      if current.value == value:
          if current.prev != null: current.prev.next = current.next
          else: head = current.next
          if current.next != null: current.next.prev = current.prev
          else: tail = current.prev
          size--
          return true
      current = current.next
  return false

toArrayForward():
  result = new int[size]
  i = 0
  current = head
  while current != null:
      result[i] = current.value
      i++
      current = current.next
  return result

toArrayBackward():
  result = new int[size]
  i = 0
  current = tail
  while current != null:
      result[i] = current.value
      i++
      current = current.prev
  return result

validateInvariants():
  if head == null and tail == null: return size == 0
  if head.prev != null: return false
  if tail.next != null: return false
  count = 0
  current = head
  while current != null:
      count++
      if current.next != null and current.next.prev != current: return false
      current = current.next
  return count == size

### Edge cases

- Empty list: head/tail both null, size 0
- Single node: head == tail, both prev/next null
- Delete the head node (list has 2+ nodes)
- Delete the tail node (list has 2+ nodes)
- Delete a middle node
- Delete a value that doesn't exist (return false, list unchanged)
- Delete when duplicates exist (only first occurrence from head removed)
- Delete the only node (list becomes empty again)

### Predicted complexity

| Operation | Best/avg | Worst |
|---|---|---|
| addFirst/addLast | O(1) | O(1) |
| removeFirstOccurrence | O(1) best (head match) | O(n) (search + tail/missing) |
| traverse (toArray*) | O(n) | O(n) |
| validateInvariants | O(n) | O(n) |

Auxiliary space: addFirst/addLast/removeFirstOccurrence are O(1) extra space.
toArrayForward/Backward are O(n) extra space (the returned array). validateInvariants is O(1) extra space.

## Test-first checkpoint

- Student-authored assertion added: `check("empty list invariants hold", list.validateInvariants());`
  (also added full coverage for head deletion, tail deletion, missing value, and duplicates)
- Observed failure before implementation (stub methods):


## Build diffs reviewed

- Reviewed OpenCode's implementation of addFirst, addLast, removeFirstOccurrence,
  toArrayForward, toArrayBackward, and validateInvariants against my pseudocode above.
- Confirmed pointer rewiring order matches the plan: for removeFirstOccurrence, prev/next
  links are reassigned before size is decremented, and head/tail are updated only when the
  removed node was actually the head/tail, preventing loss of the remaining chain.
- No deviations from the plan were needed.

## Verification

- Command run: `javac -d out src/DoublyIntLinkedList.java test/LabTestRunner.java` then
  `java -ea -cp out LabTestRunner`
- Result:


## Audit checkpoint

### @test-reviewer feedback
Identified 3 coverage gaps:
1. Single node — didn't test removing the only node (size→0, isEmpty, empty toArray).
   **Accepted** — added assertions for this case.
2. Missing value — didn't verify invariants still hold after a failed removal.
   **Accepted** — added invariants check.
3. Empty list — didn't test toArrayForward/toArrayBackward return empty arrays.
   **Accepted** — added both assertions.
Reported as adequately covered: head deletion, tail deletion, duplicate values.

### @complexity-auditor feedback
Confirmed all method complexities match my AGENT_LOG.md predictions exactly:
size()/isEmpty()/addFirst()/addLast() O(1); removeFirstOccurrence() O(1) best/O(n) worst;
toArrayForward()/toArrayBackward()/validateInvariants() O(n).
No flags raised — no discrepancies found.
**Accepted** in full, no changes needed to the implementation.

### @test-reviewer feedback
(pending — to be run in OpenCode)

### @complexity-auditor feedback
(pending — to be run in OpenCode)

## Independently evaluated agent limitation or error

While setting up the project, OpenCode's Build agent was initially assigned the model
"x-preview-f-free," which is not a supported model and returned an error instead of
generating any output. I had to independently diagnose this (not the agent — it simply
failed silently) and manually switch to a different available model (Nemotron 3 Ultra Free)
before the Build step could succeed. This shows a limitation: the agent could not recover
from or explain its own configuration error, and required human intervention to identify
that the failure was a model-availability issue rather than a problem with my prompt or plan.