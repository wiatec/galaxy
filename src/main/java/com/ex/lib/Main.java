package com.ex.lib;

import com.ex.lib.common.CommandLineUtils;
import com.ex.lib.common.GzipUtils;
import com.ex.lib.common.RegularUtils;
import com.ex.lib.common.TimeUtils;

import java.util.Date;

/**
 * @author patrick
 */
public class Main {

    public static void main(String[] args){
        System.out.println(TimeUtils.getDifferentDays(new Date(1582301201232L), new Date()));
        System.out.println(new Date(1582301201232L));
    }
}
