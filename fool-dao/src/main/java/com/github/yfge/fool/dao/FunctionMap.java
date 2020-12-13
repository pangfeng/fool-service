package com.github.yfge.fool.dao;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.util.concurrent.ConcurrentHashMap;

public class FunctionMap {

    private static ConcurrentHashMap<String, GetFieldFunction> map = new ConcurrentHashMap<>();

    /**
     *
     */
    //todo 处理基本类型
    static {
        map.put("java.lang.Integer", ResultSet::getInt);
        map.put("java.lang.Double", ResultSet::getDouble);
        map.put("java.lang.Float", ResultSet::getFloat);
        map.put("java.lang.Long", ResultSet::getLong);
        map.put("java.lang.Short", ResultSet::getShort);
        map.put("java.lang.Byte", ResultSet::getByte);
        map.put("java.lang.Boolean", ResultSet::getBoolean);
        map.put("java.lang.Character", ResultSet::getByte);
        map.put("java.lang.String", ResultSet::getString);
        map.put("int", ResultSet::getInt);
        map.put("double", ResultSet::getDouble);
        map.put("long", ResultSet::getLong);
        map.put("short", ResultSet::getShort);
        map.put("byte", ResultSet::getByte);
        map.put("boolean", ResultSet::getBoolean);
        map.put("char", ResultSet::getByte);
        map.put("float", ResultSet::getFloat);
        map.put("java.math.BigDecimal", ResultSet::getBigDecimal);
    }

    public static GetFieldFunction getFieldFunction(Field field) {
        String name = field.getType().getName();
        return map.getOrDefault(name, null);
    }
}
