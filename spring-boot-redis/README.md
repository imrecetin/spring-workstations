# spring-workstations
With big simplification when new data arrives, they are placed in the first empty bucket, but when cache is full, cleaning process is performed according to selected eviction algorithm. Some data are safe, they are used very often or meet other conditions for chosen algorithm. Rest of the data are candidates to remove. In ideal world cache will evict only old data until it found place for new ones.
Cache abstraction
Spring Framework provides an abstraction layer with set of annotations for caching support and can work together with various cache implementation like Redis, EhCache, Hazelcast, Infinispan and many more. Loose coupling is always very welcome :)


Ref : https://medium.com/@MatthewFTech/spring-boot-cache-with-redis-56026f7da83a