package com.jack.util;

import org.apache.shiro.crypto.hash.SimpleHash;

/**
 * Created by Jackaroo Zhang on 2018/10/22.
 */
public class MD5Util {

    public static String encrypt(String src) {
        return new SimpleHash("MD5", src).toString();
    }

}
