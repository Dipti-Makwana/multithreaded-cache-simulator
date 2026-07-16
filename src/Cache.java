class Cache {
    private LRUCache[] sets;
    private int numberOfSets;
    private Profiler profiler;

    // ways = how many slots per set
    // if ways = 1 -> Direct-Mapped
    //if ways > 1 -> set-Associative
    public Cache(int numberOfSets, int ways, Profiler profiler) {
        this.numberOfSets = numberOfSets;
        this.sets = new LRUCache[numberOfSets];
        this.profiler = profiler;

        for (int i = 0; i < numberOfSets; i++) {
            sets[i] = new LRUCache(ways);
        }
    }

    // return true if HIT, false if MISS (and inserts on miss)
    public boolean access(int address) {
        int setIndex = address % numberOfSets;
        LRUCache targetSet = sets[setIndex];

        boolean isHit;

        synchronized (targetSet) {
            isHit = targetSet.get(address);
            if (!isHit) {
                targetSet.put(address);
            }
        }

        profiler.recordAccess(isHit);

        return isHit;
    }


        
    
    
}
