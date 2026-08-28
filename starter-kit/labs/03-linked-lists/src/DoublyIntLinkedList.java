public class DoublyIntLinkedList {

    private static class Node {
        int value;
        Node prev;
        Node next;

        Node(int value) {
            this.value = value;
        }
    }

    private Node head;
    private Node tail;
    private int size;

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void addFirst(int value) {
        Node node = new Node(value);
        if (head == null) {
            head = node;
            tail = node;
        } else {
            node.next = head;
            head.prev = node;
            head = node;
        }
        size++;
    }

    public void addLast(int value) {
        Node node = new Node(value);
        if (tail == null) {
            head = node;
            tail = node;
        } else {
            node.prev = tail;
            tail.next = node;
            tail = node;
        }
        size++;
    }

    public boolean removeFirstOccurrence(int value) {
        Node current = head;
        while (current != null) {
            if (current.value == value) {
                if (current.prev != null) {
                    current.prev.next = current.next;
                } else {
                    head = current.next;
                }
                if (current.next != null) {
                    current.next.prev = current.prev;
                } else {
                    tail = current.prev;
                }
                size--;
                return true;
            }
            current = current.next;
        }
        return false;
    }

    public int[] toArrayForward() {
        int[] result = new int[size];
        int i = 0;
        Node current = head;
        while (current != null) {
            result[i] = current.value;
            i++;
            current = current.next;
        }
        return result;
    }

    public int[] toArrayBackward() {
        int[] result = new int[size];
        int i = 0;
        Node current = tail;
        while (current != null) {
            result[i] = current.value;
            i++;
            current = current.prev;
        }
        return result;
    }

    public boolean validateInvariants() {
        if (head == null && tail == null) {
            return size == 0;
        }
        if (head == null || tail == null) {
            return false;
        }
        if (head.prev != null || tail.next != null) {
            return false;
        }
        int count = 0;
        Node current = head;
        while (current != null) {
            count++;
            if (current.next != null && current.next.prev != current) {
                return false;
            }
            current = current.next;
        }
        return count == size;
    }
}
