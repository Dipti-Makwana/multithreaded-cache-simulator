# Multi-Threaded Cache Memory Simulator & Profiler

A Java-based simulator that models CPU cache behavior — supporting Direct-Mapped and Set-Associative configurations, with O(1) LRU eviction and multi-threaded access simulation.

## Features

- Configurable cache architecture: Direct-Mapped or Set-Associative
- LRU eviction implemented with O(1) time complexity using a Doubly Linked List + HashMap
- Multi-threaded simulation — multiple threads access a shared cache concurrently, synchronized to prevent race conditions
- Built-in profiler tracking cache hits, misses, hit ratio, and execution time
- Fully configurable via command-line flags

## How to Run

Clone the repository:
```bash
git clone https://github.com/Dipti-Makwana/multithreaded-cache-simulator.git
cd multithreaded-cache-simulator/src
```

Compile and run:
```bash
javac Main.java
java Main
```

Run with custom configuration:
```bash
java Main --threads=8 --accesses=500 --sets=4 --ways=2 --range=500
```

**Available flags:**
| Flag | Description | Default |
|---|---|---|
| `--threads` | Number of concurrent threads (simulated cores) | 4 |
| `--accesses` | Number of memory accesses per thread | 1000 |
| `--sets` | Number of cache sets | 16 |
| `--ways` | Number of ways per set (1 = Direct-Mapped) | 4 |
| `--range` | Range of possible memory addresses | 500 |

## Sample Output

```
--- Multi-Threaded Simulation ---
=== Cache Profiler Report ===
Total Accesses: 4000
Hits: 525
Misses: 3475
Hit Ratio: 13.13%
Total Time: 7.07 ms
```

Running with fewer sets (higher contention) shows a clear drop in hit ratio:

```
java Main --threads=8 --accesses=500 --sets=4 --ways=2

Total Accesses: 4000
Hits: 69
Misses: 3931
Hit Ratio: 1.73%
```

This demonstrates the real-world tradeoff between cache size and hit rate — fewer available cache lines relative to the address space leads to significantly more evictions and misses.

## Architecture

- **Node.java** — represents a single cached entry with prev/next pointers for the linked list
- **LRUCache.java** — core eviction engine using a Doubly Linked List + HashMap, giving O(1) get/put/evict operations. Thread-safe via `synchronized` methods
- **Cache.java** — routes addresses to the correct set (`address % numberOfSets`), supporting both Direct-Mapped (ways=1) and Set-Associative (ways>1) configurations
- **Worker.java** — implements `Runnable`, simulating a CPU core generating random memory accesses
- **Profiler.java** — thread-safe metrics tracker using `AtomicInteger`, records hits/misses and execution time
- **ArgsParser.java** — parses command-line flags into configuration values
- **Main.java** — wires everything together and runs the simulation

## Concurrency Design

Multiple worker threads share a single `Cache` instance. To prevent race conditions where two threads could both register a miss for the same address, the check-then-act sequence (`get()` followed by `put()`) is synchronized on the specific target set — not the entire cache — preserving parallelism across unrelated sets while keeping each set's data consistent.