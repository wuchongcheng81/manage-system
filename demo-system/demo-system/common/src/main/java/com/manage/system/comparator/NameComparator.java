package com.manage.system.comparator;

import java.util.Comparator;
import java.util.Hashtable;

/**
 * @author wcc
 * @date 2019/11/25 21:37
 */
public class NameComparator implements Comparator {

    public int compare(Object a, Object b) {
        Hashtable hashA = (Hashtable)a;
        Hashtable hashB = (Hashtable)b;
        if (((Boolean)hashA.get("is_dir")) && !((Boolean)hashB.get("is_dir"))) {
            return -1;
        } else if (!((Boolean)hashA.get("is_dir")) && ((Boolean)hashB.get("is_dir"))) {
            return 1;
        } else {
            return ((String)hashA.get("filename")).compareTo((String)hashB.get("filename"));
        }
    }
}
