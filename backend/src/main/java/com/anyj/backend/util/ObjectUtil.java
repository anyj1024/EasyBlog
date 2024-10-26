package com.anyj.backend.util;

import java.lang.reflect.Field;

public class ObjectUtil {

    public static String toString(Object obj) {
        if (obj == null) {
            return "null";
        }

        StringBuilder sb = new StringBuilder();
        Class<?> objClass = obj.getClass();

        sb.append(objClass.getSimpleName()).append(" {");

        Field[] fields = objClass.getDeclaredFields();
        for (int i = 0; i < fields.length; i++) {
            Field field = fields[i];
            field.setAccessible(true); // 确保可以访问私有字段
            try {
                sb.append(field.getName()).append("=");
                Object value = field.get(obj);
                sb.append(value != null ? value.toString() : "null");
            } catch (IllegalAccessException e) {
                sb.append("N/A");
            }
            if (i < fields.length - 1) {
                sb.append(", ");
            }
        }
        sb.append("}");
        return sb.toString();
    }
}
