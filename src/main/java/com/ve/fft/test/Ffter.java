package com.ve.fft.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: luo
 * @create: 2020-04-12 10:48
 **/
public class Ffter {
    private List[] complexesList;//log2(8)

    public Ffter() {

    }

    private void initRecorder(int len) {
        complexesList = new List[len];
        for (int i = 0; i < complexesList.length; i++) {
            complexesList[i] = new ArrayList<>();
        }
    }

    private void recordStep(Complex[] complexes) {
        complexesList[complexes.length - 1].addAll(Arrays.asList(complexes));
    }

    public Complex[] fft(Complex[] x) {
        initRecorder(x.length);
        return fftIpml(x);
    }

    private Complex[] fftIpml(Complex[] x) {
        int length = x.length;

        if (length == 1) {
            recordStep(x);
            return x;
        }

        Complex[] evenDftResult = fftIpml(getEvenComplexes(x));//偶数

        Complex[] oddDftResult = fftIpml(getOddComplexes(x));//奇数


        // 偶数+奇数
        Complex[] result = new Complex[length];
        for (int k = 0; k < length / 2; k++) {
            Complex W = complexW(length, k);    //获取旋转因子
            Complex Wx = W.multiply(oddDftResult[k]);//旋转因子乘以 奇数的值


            result[k] = evenDftResult[k].add(Wx);//偶数+奇数
            result[k + length / 2] = evenDftResult[k].minus(Wx);//偶数-奇数
        }
        recordStep(result);
        return result;
    }


    // 使用欧拉公式展开
    private Complex complexW(int length, int k) {
        double p = -2 * k * Math.PI / length;
        return new Complex(Math.cos(p), Math.sin(p));
    }

    private Complex[] getOddComplexes(Complex[] x) {
        int length = x.length;
        Complex[] odd = new Complex[length / 2];        //可以复用偶数的那个数组
        for (int k = 0; k < length / 2; k++) {
            odd[k] = x[2 * k + 1];
        }
        return odd;
    }

    private Complex[] getEvenComplexes(Complex[] x) {
        int length = x.length;
        Complex[] even = new Complex[length / 2];
        for (int k = 0; k < length / 2; k++) {
            even[k] = x[2 * k];
        }
        return even;
    }


    public void printStepsHor() {
        for (Map.Entry<Integer, List<Complex>> e : getSteps().entrySet()) {
            List<Complex> value = e.getValue();
            Sequences.print(e.getKey() + " 点", value.toArray(new Complex[0]));
        }
    }

    private Map<Integer, List<Complex>> getSteps() {
        int num = 0;

        Map<Integer, List<Complex>> complexesListNotEmpty = new HashMap<>();

        for (List<Complex> complexList : complexesList) {
            num++;
            if (!complexList.isEmpty()) {
                complexesListNotEmpty.put(num, complexList);
            }
        }
        return complexesListNotEmpty;
    }

    public void printStepsVec() {
        Map<Integer, List<Complex>> steps = getSteps();
        int size = complexesList[0].size();

        System.out.printf("      ", 0);
        int numIndex = 1;
        Collection<List<Complex>> values = steps.values();
        for (int i1 = 0; i1 < values.size(); i1++) {
            String text = numIndex + "dots";
            if (i1 == 0) {
                text = "x[x]";
            }
            if (i1 == values.size()-1 ) {
                text = "X[m]";
            }
            System.out.printf("  %"+COLUMN_WIDTH+"s  ", text);
            numIndex <<= 1;

        }
        System.out.println();


        for (int i = 0; i < size; i++) {//行
            System.out.printf("x[%2d] : ",Utils.reverse(i,size));

            for (List<Complex> complexList : complexesList) {
                if (!complexList.isEmpty()) {
                    Complex complex = complexList.get(i);
                    System.out.printf(" [%"+COLUMN_WIDTH+"s] ", complex);
                }
            }

            System.out.println();
        }


    }

    public static int COLUMN_WIDTH=8;


}
