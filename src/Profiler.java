import java.util.concurrent.atomic.AtomicInteger;

class Profiler {
    private AtomicInteger hits = new AtomicInteger(0);
    private AtomicInteger misses = new AtomicInteger(0);
    private long startTime;
    private long endTime;

    public void start() {
        startTime = System.nanoTime();
    }

    public void stop() {
        endTime = System.nanoTime();
    }

    public void recordAccess(boolean isHit) {
        if (isHit) {
            hits.incrementAndGet();
        } else {
            misses.incrementAndGet();
        }
    }

    public void printRepost() {
        int totalAccesses = hits.get() + misses.get();
        double hitRatio = (totalAccesses == 0) ? 0 : (hits.get() * 100.0) / totalAccesses;
        double durationMs = (endTime - startTime) / 1_000_000.0;

        System.out.println("\n=== Cache Profiler Repost ===");
        System.out.println("Total Accesses: " + totalAccesses);
        System.out.println("Hits: " + hits.get());
        System.out.println("Misses: " + misses.get());
        System.out.printf("Hit Ratio: %.2f%%\n", hitRatio);
        System.out.printf("Total Time: %.2f ms\n", durationMs);
        





    }
}