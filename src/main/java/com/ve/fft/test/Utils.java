package com.ve.fft.test;

/**
 * @author: luo
 * @create: 2020-04-12 13:26
 **/
public class Utils {
    public static   int LENGTH = 3;

    public static int reverse(int num,int len) {
        LENGTH= (int) (Math.log10(len)/Math.log10(2));//相当于底为2
        int res = 0;
        for (int i = 0; i < LENGTH; ++i) {
            int r = LENGTH - 1 - i;
            int mask = 1 << r;
            int bit = (num & mask) != 0 ? 1 : 0;
            res |= bit << i;

        }
        return res;
    }
}
