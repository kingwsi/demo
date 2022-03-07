package com.demo.lru;

import org.junit.jupiter.api.Test;

/**
 * description:  <br>
 * date: 2021/12/30 11:37 <br>
 * author: ws <br>
 */
public class LRUTest {

    @Test
    public void testCache(){
        LRUCache<String, String> lruCache = new LRUCache<>(10);
    }
}
