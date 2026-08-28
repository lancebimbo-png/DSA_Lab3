public class LabTestRunner {

    private static int passed = 0;
    private static int failed = 0;

    public static void main(String[] args) {
        System.out.println("Running LabTestRunner...");

        testEmptyList();
        testSingleNode();
        testHeadDeletion();
        testTailDeletion();
        testMissingValue();
        testDuplicates();

        System.out.println();
        System.out.println("Results: " + passed + " passed, " + failed + " failed");
        if (failed > 0) {
            throw new AssertionError(failed + " test(s) failed");
        }
    }

    private static void testEmptyList() {
        DoublyIntLinkedList list = new DoublyIntLinkedList();
        check("empty list size is 0", list.size() == 0);
        check("empty list isEmpty true", list.isEmpty());
        check("removeFirstOccurrence on empty returns false", !list.removeFirstOccurrence(5));
        check("empty list invariants hold", list.validateInvariants());
        check("empty list toArrayForward is empty", java.util.Arrays.equals(list.toArrayForward(), new int[0]));
        check("empty list toArrayBackward is empty", java.util.Arrays.equals(list.toArrayBackward(), new int[0]));
    }

    private static void testSingleNode() {
    DoublyIntLinkedList list = new DoublyIntLinkedList();
    list.addFirst(10);
    check("single node size is 1", list.size() == 1);
    check("single node invariants hold", list.validateInvariants());

    boolean removed = list.removeFirstOccurrence(10);
    check("removing only node returns true", removed);
    check("removing only node leaves size 0", list.size() == 0);
    check("removing only node leaves isEmpty true", list.isEmpty());
    check("removing only node leaves toArrayForward empty",
            java.util.Arrays.equals(list.toArrayForward(), new int[0]));
}

    private static void testHeadDeletion() {
        DoublyIntLinkedList list = new DoublyIntLinkedList();
        list.addLast(1);
        list.addLast(2);
        list.addLast(3);
        boolean removed = list.removeFirstOccurrence(1);
        check("head deletion returns true", removed);
        check("head deletion updates size", list.size() == 2);
        check("head deletion forward order correct",
                java.util.Arrays.equals(list.toArrayForward(), new int[] { 2, 3 }));
        check("head deletion invariants hold", list.validateInvariants());
    }

    private static void testTailDeletion() {
        DoublyIntLinkedList list = new DoublyIntLinkedList();
        list.addLast(1);
        list.addLast(2);
        list.addLast(3);
        boolean removed = list.removeFirstOccurrence(3);
        check("tail deletion returns true", removed);
        check("tail deletion updates size", list.size() == 2);
        check("tail deletion forward order correct",
                java.util.Arrays.equals(list.toArrayForward(), new int[] { 1, 2 }));
        check("tail deletion invariants hold", list.validateInvariants());
    }

    private static void testMissingValue() {
        DoublyIntLinkedList list = new DoublyIntLinkedList();
        list.addLast(1);
        list.addLast(2);
        boolean removed = list.removeFirstOccurrence(99);
        check("missing value returns false", !removed);
        check("missing value leaves size unchanged", list.size() == 2);
        check("missing value leaves list unchanged",
                java.util.Arrays.equals(list.toArrayForward(), new int[] { 1, 2 }));
                check("missing value leaves invariants holding", list.validateInvariants());
    }

    private static void testDuplicates() {
        DoublyIntLinkedList list = new DoublyIntLinkedList();
        list.addLast(5);
        list.addLast(5);
        list.addLast(5);
        boolean removed = list.removeFirstOccurrence(5);
        check("duplicate removal returns true", removed);
        check("duplicate removal only removes one", list.size() == 2);
        check("duplicate removal removes first occurrence, others remain",
                java.util.Arrays.equals(list.toArrayForward(), new int[] { 5, 5 }));
        check("duplicate removal invariants hold", list.validateInvariants());
    }

    private static void check(String description, boolean condition) {
        if (condition) {
            passed++;
            System.out.println("  [PASS] " + description);
        } else {
            failed++;
            System.out.println("  [FAIL] " + description);
        }
    }
}