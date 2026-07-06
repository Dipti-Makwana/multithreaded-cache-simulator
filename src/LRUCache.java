import java.util.HashMap;

class LRUCache {
    private int capacity;
    private HashMap<Integer, Node> map;
    private Node head;
    private Node tail;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.map = new HashMap<>();

        head = new Node(-1);
        tail = new Node(-1);

        head.next = tail;
        tail.prev = head;

    }

    private void remove(Node node) {
        Node prevNode = node.prev;
        Node nextNode = node.next;

        prevNode.next = nextNode;
        nextNode.prev = prevNode;

    }

    private void insertAtFront(Node node) {
        Node firstRealNode = head.next;

        head.next = node;
        node.prev = head;

        node.next = firstRealNode;
        firstRealNode.prev = node; 
    }
}