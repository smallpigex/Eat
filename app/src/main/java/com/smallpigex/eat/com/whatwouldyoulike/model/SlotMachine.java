package com.smallpigex.eat.com.whatwouldyoulike.model;

import java.util.Random;
/**
 * Created by smallpigex on 2015/9/6.
 */
public class SlotMachine {
    private int min = 0;

    public int getRandomNumber(int size) {
        Random rand = new Random();
        int randomNum = rand.nextInt(size);
        return randomNum;
    }
}
