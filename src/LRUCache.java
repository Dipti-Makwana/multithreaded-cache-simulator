import java.util.HashMap;

class LRUCache {
    private int capacity;
    private HashMap<Integer, Node> map;
    private Node head; // dummy head (Most Rrecently Used side)
    private Node tail; // demmy tail (Least Recently Used side)

    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.map = new HashMap<>();


    // dummy nodes with fake address -1 (never real data)
        head = new Node(-1);
        tail = new Node(-1);

        head.next = tail;
        tail.prev = head;

    }
    
    // removes a node from its current position in the list
    private void remove(Node node) {
        Node prevNode = node.prev;
        Node nextNode = node.next;

        prevNode.next = nextNode;
        nextNode.prev = prevNode;

    }

    // inserts a node right after head (marks it as Most Recently Used)
    private void insertAtFront(Node node) {
        Node firstRealNode = head.next;

        head.next = node;
        node.prev = head;

        node.next = firstRealNode;
        firstRealNode.prev = node; 
    }

    // returns true if address is a HIT, false if it's a MISS
    public boolean get(int address) {
        if (map.containsKey(address)) {
            // HIT - address alreaady in cache
            Node node = map.get(address);

            // move it ti front since it's now the most recently used
            remove(node);
            insertAtFront(node);

            return true;  // hit
        }
        return false; // miss
        }
    }


