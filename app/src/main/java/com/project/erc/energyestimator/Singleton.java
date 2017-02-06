package com.project.erc.energyestimator;

/**
 * Created by spoor on 2/3/2017.
 */

public class Singleton {
    private static Singleton instance = null;
    public Singleton(){

    }

    public static Singleton getInstance(){
        if(instance == null){
            instance = new Singleton();

        }
        return instance;
    }

}
