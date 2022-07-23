package com.github.barteksc.pdfviewer.util;

public class MathUtils {
    private static final double BIG_ENOUGH_CEIL = 16384.999999999996d;
    private static final double BIG_ENOUGH_FLOOR = 16384.0d;
    private static final int BIG_ENOUGH_INT = 16384;

    private MathUtils() {
    }

    public static int limit(int number, int between, int and) {
        if (number <= between) {
            return between;
        }
        if (number >= and) {
            return and;
        }
        return number;
    }

    public static float limit(float number, float between, float and) {
        if (number <= between) {
            return between;
        }
        if (number >= and) {
            return and;
        }
        return number;
    }

    public static float max(float number, float max) {
        if (number > max) {
            return max;
        }
        return number;
    }

    public static float min(float number, float min) {
        if (number < min) {
            return min;
        }
        return number;
    }

    public static int max(int number, int max) {
        if (number > max) {
            return max;
        }
        return number;
    }

    public static int min(int number, int min) {
        if (number < min) {
            return min;
        }
        return number;
    }

    public static int floor(float value) {
        double d = (double) value;
        Double.isNaN(d);
        return ((int) (d + BIG_ENOUGH_FLOOR)) - 16384;
    }

    public static int ceil(float value) {
        double d = (double) value;
        Double.isNaN(d);
        return ((int) (d + BIG_ENOUGH_CEIL)) - 16384;
    }
}
