package com.hwk.seckill.common.util;

import java.security.MessageDigest;


public class Md5 {


    /**
     * md5 加密
     * @param str
     * @return
     */
    public static String encode(String str){

        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            md5.update((str).getBytes("UTF-8"));
            byte[] b = md5.digest();

            int i;
            StringBuffer buf = new StringBuffer();
            for (int offset = 0; offset < b.length; offset++){
                i = b[offset];
                if(i < 0){
                    i += 256;
                }
                if( i < 16 ){
                    buf.append("0");
                }
                buf.append(Integer.toHexString(i));
            }
            str = buf.toString();
            System.out.println("result = " + str);
        } catch (Exception e) {
            System.out.println("error:" + e);
        }
        return str;
    }
}
