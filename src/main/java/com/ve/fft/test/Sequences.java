package com.ve.fft.test;

import java.util.function.Function;

/**
 * @author: luo
 * @create: 2020-04-12 12:11
 **/
public class Sequences {

    public static Complex[] from(double[][] values) {
        return generate(values.length, integer -> {
            double[] value = values[integer];
            if (value.length == 1) {
                return new Complex(value[0]);
            }
            if (value.length == 2) {
                return new Complex(value[0], value[1]);
            }
            return new Complex();
        });
    }

    public static Complex[] generate(int length, Function<Integer, Complex> function) {
        Complex[] complexes = new Complex[length];
        for (int i = 0; i < complexes.length; i++) {
            complexes[i] = function.apply(i);
        }
        return complexes;
    }

    public static Complex[] expand(Complex[] old, int length, Complex repeat) {
        Complex[] complexes = new Complex[length];
        for (int i = 0; i < length && i < old.length; i++) {
            complexes[i] = old[i];
        }
        if (old.length < length) {
            for (int i = old.length; i < length; i++) {
                complexes[i] = new Complex(repeat);
            }
        }
        return complexes;

    }

    public static Complex[] dk(int n) {//冲激序列
        return generate(n, integer -> new Complex(integer == 0 ? 1 : 0));
    }

    public static Complex[] rk(int n) {//矩形序列
        return generate(n, integer -> new Complex(1));

    }

    public static Complex[] tk(int n) {//梯度序列
        return generate(n, Complex::new);

    }

    public static void print(String msg, Complex[] complexes) {
        System.out.printf("%-10s| ", msg);
        for (Complex complex : complexes) {
            System.out.printf(" [%8s] ", complex);
        }
        System.out.println();
    }

}
