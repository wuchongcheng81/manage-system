/**
 * Amendment History
 * <p>
 * Programmer        Time            Amendment summary
 * ---------------------------------------------------
 * Junbo Xie         4/24/2018        Initial version
 */
package com.manage.system.util;

import org.apache.commons.lang3.StringUtils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.UUID;

public class ObjectUtil extends org.apache.commons.lang3.ObjectUtils {

    public static void annotationToObject(Object annotation, Object object) {
        if (annotation != null) {
            Class<?> annotationClass = annotation.getClass();
            if (object == null) {
                return;
            }
            Class<?> objectClass = object.getClass();
            Method[] arr$ = objectClass.getMethods();
            int len$ = arr$.length;

            for (int i$ = 0; i$ < len$; ++i$) {
                Method m = arr$[i$];
                if (StringUtils.startsWith(m.getName(), "set")) {
                    try {
                        String s = StringUtils.uncapitalize(StringUtils
                                .substring(m.getName(), 3));
                        Object obj = annotationClass.getMethod(s).invoke(
                                annotation);
                        if (obj != null && !"".equals(obj.toString())) {
                            object = objectClass.newInstance();
                            m.invoke(object, obj);
                        }
                    } catch (Exception var10) {
                        ;
                        var10.printStackTrace();
                    }
                }
            }
        }

    }

    public static byte[] serialize(Object object) {
        ObjectOutputStream oos = null;
        ByteArrayOutputStream baos = null;

        try {
            if (object != null) {
                baos = new ByteArrayOutputStream();
                oos = new ObjectOutputStream(baos);
                oos.writeObject(object);
                return baos.toByteArray();
            }
        } catch (Exception var4) {
            var4.printStackTrace();
        }

        return null;
    }

    public static Object unserialize(byte[] bytes) {
        ByteArrayInputStream bais = null;

        try {
            if (bytes != null && bytes.length > 0) {
                bais = new ByteArrayInputStream(bytes);
                ObjectInputStream ois = new ObjectInputStream(bais);
                return ois.readObject();
            }
        } catch (Exception var3) {
            var3.printStackTrace();
        }

        return null;
    }

    public static String getUUID() {
        return UUID.randomUUID().toString().replace("-", "").toUpperCase();
    }

    public boolean checkObjectFieldIsNull(Object obj)
            throws IllegalAccessException {
        boolean flag = false;
        Field[] arr$ = obj.getClass().getDeclaredFields();
        int len$ = arr$.length;

        for (int i$ = 0; i$ < len$; ++i$) {
            Field f = arr$[i$];
            f.setAccessible(true);
            if (f.get(obj) == null) {
                flag = true;
                return flag;
            }
        }

        return flag;
    }
}
