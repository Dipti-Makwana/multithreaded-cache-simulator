class Cache {
    private LRUCache[] sets;
    private int numberOfSets;

    // ways = how many slots per set
    // if ways = 1 -> Direct-Mapped
    //if ways > 1 -> set-Associative
    public Cache(int numberOfSets, int ways) {
        this.numberOfSets = numberOfSets;
        this.sets = new LRUCache[numberOfSets];

        for (int i = 0; i < numberOfSets; i++) {
            sets[i] = new LRUCache(ways);
        }
    }

    // return true if HIT, false if MISS (and inserts on miss)
    public boolean access(int address) {
        int setIndex = address % numberOfSets;
        LRUCache targetSet = sets[setIndex];

        boolean isHit = targetSet.get(address);

        if (!isHit) {
            targetSet.put(address);
        }

        return isHit;
    }


        
    
    
}
