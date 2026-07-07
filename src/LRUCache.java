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

        //inserts a new address into the cache, evicting LRU item if full
        public void put(int address) {
            // if it already exists, just refresh its position and exit
            if (map.containsKey(address)) {
                Node node = map.get(address);
                remove(node);
                insertAtFront(node);
                return;
            }

            // if cache is full, evit the Least Recently Used item (right before tail)
            if (map.size() == capacity) {
                Node lruNode = tail.prev;
                remove(lruNode);
                map.remove(lruNode.address);
            }

            // insert the new address at the front
            Node newNode = new Node(address);
            insertAtFront(newNode);
            map.put(address, newNode);

        }
    }


