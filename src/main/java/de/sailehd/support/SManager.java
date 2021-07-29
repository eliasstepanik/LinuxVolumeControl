package de.sailehd.support;

public class SManager {
    public static double similarity(String source, String target){
        String longer = source, shorter = target;
        if (source.length() < target.length()) {
            longer = target; shorter = source;
        }
        int longerLength = longer.length();
        if (longerLength == 0) { return 1.0;}

        return (longerLength - editDistance(longer, shorter)) / (double) longerLength;
    }

    private static int editDistance(String source, String target) {
        source = source.toLowerCase();
        target = target.toLowerCase();

        int[] costs = new int[target.length() + 1];
        for (int i = 0; i <= source.length(); i++) {
            int lastValue = i;
            for (int j = 0; j <= target.length(); j++) {
                if (i == 0)
                    costs[j] = j;
                else {
                    if (j > 0) {
                        int newValue = costs[j - 1];
                        if (source.charAt(i - 1) != target.charAt(j - 1))
                            newValue = Math.min(Math.min(newValue, lastValue),
                                    costs[j]) + 1;
                        costs[j - 1] = lastValue;
                        lastValue = newValue;
                    }
                }
            }
            if (i > 0)
                costs[target.length()] = lastValue;
        }
        return costs[target.length()];
    }

}


