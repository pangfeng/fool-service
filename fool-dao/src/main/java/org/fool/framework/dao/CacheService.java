package org.fool.framework.dao;


import org.springframework.util.ObjectUtils;

import java.lang.reflect.InvocationTargetException;
import java.util.concurrent.ConcurrentHashMap;

public class CacheService {


    private static ConcurrentHashMap<String, Object> cacheMapper = new ConcurrentHashMap<>();

    public static <T> T get(Class<T> clazz, Object key) {
        if (ObjectUtils.isEmpty(key)) {
            return null;
        }
        String cacheKey = clazz.getName().concat(key.toString());
        if (cacheMapper.containsKey(cacheKey)) {
            return (T) cacheMapper.get(cacheKey);
        }
        return null;
    }

    /**
     * @param clazz
     * @param key
     * @param <T>
     * @return
     */
    public static <T> T getOrInit(Class<T> clazz, Object key) {
        if (ObjectUtils.isEmpty(key)) {
            return null;
        }
        String cacheKey = clazz.getName().concat(key.toString());
        if (!cacheMapper.containsKey(cacheKey)) {
            T object = null;
            try {
                object = clazz.getDeclaredConstructor().newInstance();
            } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
                return null;
            }
            T last = (T) cacheMapper.putIfAbsent(cacheKey, object);
            if (last != null) {
                cacheMapper.put(cacheKey, last);
                return last;
            } else {
                return object;
            }
        }
        return null;
    }
}
