package com.blessingstudio.lobotomycorp.service;

public class MathService {
    public static float getActualRedDamage(float dr, float amount) {
        return dr * amount;
    }

    public static float getActualBlackDamage(float dr, float amount) {
        return dr * amount;
    }

    public static float getActualWhiteDamage(float dr, float amount) {
        return dr * amount;
    }

    public static float getActualBlueDamage(float dr, float amount, float maxHealth) {
        double actualAmount = dr * amount;
        return (float) (maxHealth * (actualAmount / 100F));
    }
}
