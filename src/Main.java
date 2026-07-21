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
        Profiler profiler1 = new Profiler();
        profiler1.start();
        Cache directMapped = new Cache(4, 1, profiler1); // 4 sets, 1 way = Direct-Mapped

        System.out.println("Access 10 (expect MISS): " + directMapped.access(10));
        System.out.println("Access 10 again (expect HIT): " + directMapped.access(10));
        profiler1.stop();
        profiler1.printReport();


        System.out.println("\n--- Testing Cache.java (Set-Associative) ---");
        Profiler profiler2 = new Profiler();
        profiler2.start();
        Cache setAssociative = new Cache(2, 2, profiler2); // 2 sets, 2 ways each - Set-Associative

        System.out.println("Access 5 (expect MISS): " + setAssociative.access(5));
        System.out.println("Access 7 (expect MISS): " + setAssociative.access(7));
        System.out.println("Access 5 (expect HIT): " + setAssociative.access(5));
        profiler2.stop();
        profiler2.printReport();


        System.out.println("\n--- Multi-Threaded Simulation ---");

        Profiler multiThreadProfiler = new Profiler();
        ArgsParser argsParser = new ArgsParser(args);

        int numberOfThreads = argsParser.getInt("threads", 4);
        int accessesPerThread = argsParser.getInt("accesses", 1000);
        int addressRange = argsParser.getInt("range", 500);
        int numberOfSets = argsParser.getInt("sets", 16);
        int ways = argsParser.getInt("ways", 4);
        
        Cache sharedCache = new Cache(numberOfSets, ways, multiThreadProfiler);

        Thread[] threads = new Thread[numberOfThreads];

        multiThreadProfiler.start();

        // create and start all threads
        for (int i = 0; i < numberOfThreads; i++) {
            Worker worker = new Worker(sharedCache, accessesPerThread, addressRange);
            threads[i] = new Thread(worker);
            threads[i].start();
        }

        //wait for all threads to finish before printing report
        try{
        for (int i = 0; i< numberOfThreads; i++) {
            threads[i].join();
        } 
    } catch (InterruptedException e) {
        e.printStackTrace();
    }

        multiThreadProfiler.stop();
        multiThreadProfiler.printReport();



        



    }

    

}
