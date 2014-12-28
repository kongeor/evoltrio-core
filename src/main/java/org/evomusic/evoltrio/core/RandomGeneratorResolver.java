/**
 * This file is part of evoltrio-core.
 *
 * evoltrio-core is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * evoltrio-core is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with evoltrio-core.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.evomusic.evoltrio.core;

import java.util.HashMap;
import java.util.Map;

import org.jgap.RandomGenerator;
import org.jgap.impl.CauchyRandomGenerator;
import org.jgap.impl.GaussianRandomGenerator;
import org.jgap.impl.StockRandomGenerator;

/**
 * 
 * @author Kostas Georgiadis
 * @since 0.3.0
 */
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
