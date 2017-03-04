/*
 * String_Generator - Text analysis for realistic random strings generation
 * 
 * RandomGenerator.java
 * Copyright (C) 2017 Jarretier Adrien
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package model;

import java.util.Random;
import java.util.stream.LongStream;
import umontreal.ssj.rng.MRG32k3a;
import umontreal.ssj.rng.MT19937;

/**
 *
 * @author Jarretier Adrien "jarretier.adrien@gmail.com"
 */
public class RandomGenerator {

    private MT19937 gen;

    /* Private constructor     */
    private RandomGenerator() {
        Random rand = new Random();
        LongStream seed = LongStream.concat(rand.longs(3, 1, 4294967087L), rand.longs(3, 1, 4294944443L));

        MRG32k3a.setPackageSeed(seed.toArray());

        MRG32k3a mrg = new MRG32k3a();
        gen = new MT19937(mrg);
    }

    private static class RandomGeneratorHolder {

        private static final RandomGenerator INSTANCE = new RandomGenerator();
    }

    /* Access point to the unique instance of the singleton */
    public static RandomGenerator getGenerator() {
        return RandomGeneratorHolder.INSTANCE;
    }

    /**
     * Generates a uniformly distributed random int
     *
     * @param min inclusive
     * @param max inclusive
     * @return a random int uniformly ditributed between min and max
     */
    public int rollI(int min, int max) {
        return gen.nextInt(min, max);
    }
}
