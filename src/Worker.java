import java.util.Random;

 class Worker implements Runnable {
    private Cache cache;
    private int numberOfAccesses;
    private int addressRange;

    public Worker(Cache cache, int numberOfAccesses, int addressRange) {
        this.cache = cache;
        this.numberOfAccesses = numberOfAccesses;
        this.addressRange = addressRange;
        
    }

    @Override 
    public void run() {
        Random random = new Random();

        for (int i = 0; i< numberOfAccesses; i++) {
            int address = random.nextInt(addressRange);
            cache.access(address);
        }
    }

 }
