package com.example.foodfind;

import android.content.Context;
import android.content.Intent;

import java.util.HashMap;

public class FactoryIntent {
    public static Intent getIntent(Class<?> className, Context context) {
        HashMap<Class<?>, Intent> factoryHash =new HashMap<>();

        if(factoryHash.containsKey(className)==false){
            factoryHash.put(className,createIntent(className,context));
        }
        return (factoryHash.get(className));
    }

    public static Intent createIntent(Class<?> className, Context context){
        Intent intent = new Intent(context, className);

        return (intent);
    }
}
