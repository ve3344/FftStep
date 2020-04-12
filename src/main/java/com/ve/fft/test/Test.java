package com.ve.fft.test;

/**
 * @author: luo
 * @create: 2020-04-12 12:21
 **/
public class Test {
    public static void main(String[] args) {
        Ffter ffter = new Ffter();
        Complex.setDotLength(4);
        Ffter.COLUMN_WIDTH=16;

        Complex[] cos2pik = Sequences.generate(8,
                k -> new Complex(Math.cos((2*Math.PI*k)/8))
        );

        Complex[] res = ffter.fft(cos2pik);
        ffter.printStepsVec();

    }
}
