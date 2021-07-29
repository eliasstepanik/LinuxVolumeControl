package de.sailehd.support;

import org.apache.commons.lang3.RandomStringUtils;

public class Random {
    public static int Range(int min, int max){
        return (int) ((Math.random() * (max - min)) + min);
    }

    public static float Range(float min, float max){
        return (float) ((Math.random() * (max - min)) + min);
    }

    public static double Range(double min, double max){
        return (double) ((Math.random() * (max - min)) + min);
    }

    public static long Range(long min, long max){
        return (long) ((Math.random() * (max - min)) + min);
    }

    public static short Range(short min, short max){
        return (short) ((Math.random() * (max - min)) + min);
    }

    public static String String(int length, boolean useLetters, boolean useNumbers) {
        String generatedString = RandomStringUtils.random(length, useLetters, useNumbers);

        return generatedString;
    }


}
