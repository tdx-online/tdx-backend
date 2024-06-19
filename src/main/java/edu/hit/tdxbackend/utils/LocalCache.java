package edu.hit.tdxbackend.utils;

import java.util.Map;

public class LocalCache {
    private volatile static LocalCache instance = null;
    private static final Map<String, Object> cache = new java.util.concurrent.ConcurrentHashMap<>();

    private LocalCache() {
    }

    public LocalCache getInstance() {
        if (instance == null) {
            synchronized (LocalCache.class) {
                if (instance == null) {
                    instance = new LocalCache();
                }
            }
        }
        return instance;
    }

    public static void put(String key, Object value) {
        cache.put(key, value);
    }

    public static Object get(String key) {
        return cache.get(key);
    }

    public static void remove(String key) {
        cache.remove(key);
    }
}
