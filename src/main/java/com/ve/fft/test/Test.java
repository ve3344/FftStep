package com.ve.fft.test;

/**
 * @author: luo
 * @create: 2020-04-12 12:21
 **/
public class Test {
    public static void main(String[] args) {
        Ffter ffter = new Ffter();
        Complex.setDotLength(4);//设置小数点位数
        Ffter.COLUMN_WIDTH=16;//设置输出宽度16字符

        Complex[] cos2PikD8 = Sequences.generate(8,
                k -> new Complex(Math.cos((2*Math.PI*k)/8))
        );
        //cos (2pi*k/8) 序列

        Complex[] result = ffter.fft(cos2PikD8);

        //结果序列

        ffter.printStepsVec();//输出流程

    }
}
