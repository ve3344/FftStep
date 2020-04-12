package com.ve.fft.test;

public class Complex {
    private double real, imaginary;

    public Complex() {
        this.real = 0.0;
        this.imaginary = 0.0;
    }

    public Complex(double real, double imaginary) {
        this.real = real;
        this.imaginary = imaginary;
    }

    public Complex(double real) {
        this.real = real;
    }

    public Complex(Complex complex) {
        if (complex!=null) {
            this.real = complex.real;
            this.imaginary = complex.imaginary;
        }
    }

    public double getReal() {
        return real;
    }

    public double getImaginary() {
        return imaginary;
    }

    public String toString() {
        String replace = getString().replace(EMMIT_ZERO, "");
        if (replace.equals("-0")){
            return "0";
        }
        return replace;

    }


    public static void setDotLength(int len){
        REAL_FORMAT="%."+len+"f";
        IMG_FORMAT=REAL_FORMAT+"i";
        StringBuilder sb=new StringBuilder(".");
        for (int i = 0; i < len; i++) {
            sb.append("0");
        }
        EMMIT_ZERO=sb.toString();
    }

    private static String EMMIT_ZERO=".0";
    private static String REAL_FORMAT="%.1f";
    private static String IMG_FORMAT="%.1fi";

    private String getString() {
        if (checkEqual(imaginary,0)) {
            return String.format(REAL_FORMAT, real);
        }
        if (checkEqual(real,0)) {
            return String.format(IMG_FORMAT, imaginary);
        }
        if (imaginary < 0) {
            return String.format(REAL_FORMAT+IMG_FORMAT, real, imaginary);
        }

        return String.format(REAL_FORMAT+"+"+IMG_FORMAT, real, imaginary);
    }
    private static boolean checkEqual(double a,double b){
        final double dis=1e-8;
        return Math.abs(a-b)<dis;
    }


    public Complex add(Complex o) {
        if (o == null) {
            return this;
        }
        return new Complex(this.real + o.real, this.imaginary + o.imaginary);
    }


    public Complex minus(Complex o) {
        if (o == null) {
            return this;
        }
        return new Complex(this.real - o.real, this.imaginary - o.imaginary);
    }

    public Complex multiply(Complex o) {
        if (o == null) {
            return new Complex();
        }
        double real = this.real * o.real - this.imaginary * o.imaginary;   //新建一个实部
        double img = this.real * o.imaginary + this.imaginary * o.real;   //新建一个虚部
        return new Complex(real, img);
    }

    public Complex divide(Complex o) {
        if (o == null || (o.real == 0.0 && imaginary == 0.0)) {
            throw new IllegalArgumentException("不能除以0");
        }

        double real = (this.real * o.real + this.imaginary * o.imaginary) / (o.real * o.real + o.imaginary * o.imaginary);
        double img = (this.imaginary * o.real - this.real * o.imaginary) / (o.real * o.real + o.imaginary * o.imaginary);
        return new Complex(real, img);
    }


}
