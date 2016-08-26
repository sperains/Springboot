package com.rains.annotation;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;

/**
 * Created by Administrator on 2016-08-22.
 */
@MyAnnotation(color = "blue")
public class MyAnnotationTest {

    public static void main(String[] args) throws ClassNotFoundException {

        System.out.println(args.length);

        for(String className : args){
            Class<?> cl = Class.forName(className);
            System.out.println(cl);
        }


    }
}
