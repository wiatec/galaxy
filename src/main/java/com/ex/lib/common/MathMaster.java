package com.ex.lib.common;

import java.math.BigDecimal;

/**
 * @author patrick
 */
public class MathMaster {

    /**
     * 计算2个int数相除的结果小数点保留2位并四舍五入
     */
    public static Double divide(int a, int b){
        return divide(new BigDecimal(a), new BigDecimal(b));
    }

    /**
     * 计算2个double数相除的结果小数点保留2位并四舍五入
     */
    public static Double divide(double a, double b){
        return divide(new BigDecimal(a), new BigDecimal(b));
    }

    /**
     * 计算2个float数相除的结果小数点保留2位并四舍五入
     */
    public static Double divide(float a, float b){
        return divide(new BigDecimal(a), new BigDecimal(b));
    }

    /**
     * 计算2个BigDecimal数相除的结果小数点保留2位并四舍五入
     */
    public static Double divide(BigDecimal a, BigDecimal b){
        BigDecimal c = a.divide(b, 4, BigDecimal.ROUND_HALF_UP);
        double result = c.doubleValue();
        if(result >= 1 || result<= -1){
            return result;
        }else{
            return result * 100;
        }
    }

    /**
     * 将double数值四舍五入保留2位小数
     */
    public static double halfUp(double value){
        try {
            BigDecimal bigDecimal = new BigDecimal(value);
            bigDecimal = bigDecimal.setScale(2, BigDecimal.ROUND_HALF_UP);
            return bigDecimal.doubleValue();
        }catch (Exception e){
            return 0.0;
        }
    }
}
