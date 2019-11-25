package com.manage.system.comparator;

import java.util.Comparator;
import java.util.Hashtable;

/**
 * @author wcc
 * @date 2019/11/25 21:36
 */
public class SizeComparator implements Comparator {
    public int compare(Object a, Object b) {
        Hashtable hashA = (Hashtable)a;
        Hashtable hashB = (Hashtable)b;
        if (((Boolean)hashA.get("is_dir")) && !((Boolean)hashB.get("is_dir"))) {
            return -1;
        } else if (!((Boolean)hashA.get("is_dir")) && ((Boolean)hashB.get("is_dir"))) {
            return 1;
        } else {
            if (((Long)hashA.get("filesize")) > ((Long)hashB.get("filesize"))) {
                return 1;
            } else if (((Long)hashA.get("filesize")) < ((Long)hashB.get("filesize"))) {
                return -1;
            } else {
                return 0;
            }
        }
    }
}
