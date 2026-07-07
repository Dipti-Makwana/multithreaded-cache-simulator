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

        System.out.println("\n--- Testing Cache.java (Direct-Mapped) ---");
        Cache directMapped = new Cache(4, 1); // 4 sets, 1 way = Direct-Mapped

        System.out.println("Access 10 (expect MISS): " + directMapped.access(10));
        System.out.println("Access 10 again (expect HIT): " + directMapped.access(10));

        System.out.println("\n--- Testing Cache.java (Set-Associativ) ---");
        Cache setAssociative = new Cache(2, 2); // 2 sets, 2 ways each - Set-Associative

        System.out.println("Access 5 (expect MISS): " + setAssociative.access(5));
        System.out.println("Access 7 (expect MISS): " + setAssociative.access(7));
        System.out.println("Access 5 (expect HIT): " + setAssociative.access(5));




    }

    

}
