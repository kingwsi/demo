package com.demo.lru;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * description:  使用链表实现<br>
 * date: 2021/12/30 11:31 <br>
 * author: ws <br>
 */
public class LRUCache<K, V> extends LinkedHashMap<K, V> {
    private final int MAX_CACHE_SIZE;

    public LRUCache(int size) {
        super((int) Math.ceil(size / 0.75) + 1, 0.75f, true);
        MAX_CACHE_SIZE = size;
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
        return size() > MAX_CACHE_SIZE;
    }
}
