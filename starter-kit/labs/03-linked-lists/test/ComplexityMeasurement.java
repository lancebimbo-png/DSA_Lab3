public class ComplexityMeasurement {
    public static void main(String[] args) {
        int[] sizes = {1000, 10000, 100000};

        System.out.println("addLast timing (build a list of n elements):");
        for (int n : sizes) {
            DoublyIntLinkedList list = new DoublyIntLinkedList();
            long start = System.nanoTime();
            for (int i = 0; i < n; i++) {
                list.addLast(i);
            }
            long end = System.nanoTime();
            System.out.println("  n=" + n + ": " + (end - start) / 1_000_000.0 + " ms");
        }

        System.out.println("removeFirstOccurrence (worst case, value not present):");
        for (int n : sizes) {
            DoublyIntLinkedList list = new DoublyIntLinkedList();
            for (int i = 0; i < n; i++) {
                list.addLast(i);
            }
            long start = System.nanoTime();
            list.removeFirstOccurrence(-1); // not present, forces full traversal
            long end = System.nanoTime();
            System.out.println("  n=" + n + ": " + (end - start) / 1_000_000.0 + " ms");
        }

        System.out.println("validateInvariants timing:");
        for (int n : sizes) {
            DoublyIntLinkedList list = new DoublyIntLinkedList();
            for (int i = 0; i < n; i++) {
                list.addLast(i);
            }
            long start = System.nanoTime();
            list.validateInvariants();
            long end = System.nanoTime();
            System.out.println("  n=" + n + ": " + (end - start) / 1_000_000.0 + " ms");
        }
    }
}