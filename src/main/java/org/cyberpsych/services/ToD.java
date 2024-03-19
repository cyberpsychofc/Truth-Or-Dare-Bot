package org.cyberpsych.services;
import java.util.Random;

public class ToD {
    String truth[] = {"If you could be invisible for a day, what's the first thing you would do?"};
    Random r = new Random();
    public String getRandomTruth(){
        return truth[r.nextInt(truth.length)];
    }
}
