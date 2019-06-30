package com.ic.unicamp.br.mc322.pacman.game.utilities;

import java.util.AbstractMap;

public class Tuple<K, V> { // Simple Tuple class that Java seems to lack

    private AbstractMap.SimpleEntry<K, V> values;

    public Tuple(K a, V b) {
        values = new AbstractMap.SimpleEntry<>(a, b);
    }

    public K getA() {
        return this.values.getKey();
    }

    public V getB() {
        return this.values.getValue();
    }
}
