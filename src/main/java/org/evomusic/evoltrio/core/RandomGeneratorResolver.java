package org.evomusic.evoltrio.core;

import java.util.HashMap;
import java.util.Map;

import org.jgap.RandomGenerator;
import org.jgap.impl.CauchyRandomGenerator;
import org.jgap.impl.GaussianRandomGenerator;
import org.jgap.impl.StockRandomGenerator;

public class RandomGeneratorResolver {

    Map<String, RandomGenerator> generators = new HashMap<>();
    
    public RandomGeneratorResolver() {
        generators.put("stock", new StockRandomGenerator());
        generators.put("cauchy", new CauchyRandomGenerator());
        generators.put("gaussian", new GaussianRandomGenerator());
    }
    
    public RandomGenerator resolve(String name) {
        RandomGenerator random = generators.get(name);
        if (random == null)
            throw new IllegalArgumentException();
        return random;
    }
}
