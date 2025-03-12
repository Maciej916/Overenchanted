package com.maciej916.overenchanted.util;

public enum EnchantmentRarity {
    CUSTOM(15),
    COMMON(10),
    UNCOMMON(5),
    RARE(2),
    VERY_RARE(1);

    private int weight;

    EnchantmentRarity(int i) {
        this.weight = i;
    }

    public EnchantmentRarity setWeight(int i) {
        this.weight = i;
        return this;
    }

    public int weight() {
        return this.weight;
    }
}
