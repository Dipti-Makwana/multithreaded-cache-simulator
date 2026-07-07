public class Main {
    public static void main(String[] args){
        LRUCache cache = new LRUCache(3); // capacity = 3

        cache.put(1);
        cache.put(2);
        cache.put(3);

        System.out.println("Access 1 (expect HIT): " + cache.get(1));

        cache.put(4); // ccache full (1, 2, 3) -> should evict 2 (LRU, since 1 was just touched)
        System.out.println("Access 2 (expect MISS, was evicted): " + cache.get(2));
        System.out.println("Access 3 (expect HIT): " + cache.get(3));
        System.out.println("Access 4 (expect HIT): " + cache.get(4));

    }
}
