package com.rains.annotation.usecase;

import java.util.Collections;
import java.util.List;
import java.lang.reflect.Method;
import java.util.ArrayList;

/**
 * Created by Rains on 2016/8/24.
 */
public class UseCaseTracker {

    public static void trackUseCase(List<Integer> useCases , Class<?>cl){
        for(Method m : cl.getDeclaredMethods()){
            UseCase uc = m.getAnnotation(UseCase.class);
            if(uc != null){
                System.out.println("找到用例" + uc.id() + ":" + uc.description());
                useCases.remove(new Integer(uc.id()));
            }
        }

        for(Integer i : useCases){
            System.out.println("缺失用例" + i );
        }
    }

    public static void main(String[] args) {
        List<Integer> useCases = new ArrayList<>();
        Collections.addAll(useCases , 1,2,3,4,5);
        trackUseCase(useCases , PasswordUtil.class);
    }
}
