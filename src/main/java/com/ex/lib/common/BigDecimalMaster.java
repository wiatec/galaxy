package com.ex.lib.common;

import java.math.BigDecimal;

/**
 * @author patrick
 */
public class BigDecimalMaster {

    public static final BigDecimal ZERO = new BigDecimal("0.00");


    public static BigDecimal add(double v1, double v2){
        BigDecimal b1 = new BigDecimal(v1);
        BigDecimal b2 = new BigDecimal(v2);
        return b1.add(b2);
    }

    public static BigDecimal subtract(double v1, double v2){
        BigDecimal b1 = new BigDecimal(v1);
        BigDecimal b2 = new BigDecimal(v2);
        return b1.subtract(b2);
    }

    public static BigDecimal multiply(double v1, double v2){
        BigDecimal b1 = new BigDecimal(v1);
        BigDecimal b2 = new BigDecimal(v2);
        return b1.multiply(b2);
    }

    public static BigDecimal divide(double v1, double v2){
        BigDecimal b1 = new BigDecimal(v1);
        BigDecimal b2 = new BigDecimal(v2);
        //四舍五入， 2位小数
        return b1.divide(b2, 2, BigDecimal.ROUND_HALF_UP);
    }

    public static boolean compare(BigDecimal big, BigDecimal small){
        int a = big.compareTo(small);
        return a > 0;
    }



}
