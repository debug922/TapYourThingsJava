package com.sorty.tapyourthingsjava;

import com.orm.SugarRecord;
import com.orm.dsl.Unique;

import java.io.Serializable;

public class Tap extends SugarRecord implements Serializable {

    private int tap;
    @Unique
    private String thing;

    public Tap(){}

    public Tap(int tap, String thing){
        this.tap=tap;
        this.thing=thing;
    }
    public Tap(String thing){
        this(0,thing);
    }


    public int getTap() {
        return tap;
    }

    public void setTap(int tap) {
        this.tap = tap;
    }

    public String getThing() {
        return thing;
    }

    public void setThing(String thing) {
        this.thing = thing;
    }
}
