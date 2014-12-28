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

import org.jgap.InvalidConfigurationException;
import org.jgap.NaturalSelector;
import org.jgap.impl.BestChromosomesSelector;
import org.jgap.impl.ThresholdSelector;
import org.jgap.impl.TournamentSelector;
import org.jgap.impl.WeightedRouletteSelector;

/**
 * 
 * @author Kostas Georgiadis
 * @since 0.3.0
 */
public class NaturalSelectorResolver {

    private Map<String, NaturalSelector> selectors = new HashMap<>();
    
    public NaturalSelectorResolver() throws InvalidConfigurationException {
        selectors.put("best", new BestChromosomesSelector());
        selectors.put("threshold", new ThresholdSelector());
        // conf.addNaturalSelector(new ThresholdSelector(conf, 0.5d),
        selectors.put("tournament", new TournamentSelector());
        // conf.addNaturalSelector(new TournamentSelector(conf, 30, 0.5d),
        selectors.put("weighted", new WeightedRouletteSelector());
    }
    
    public NaturalSelector resolve(String name) {
        NaturalSelector selector = selectors.get(name);
        if (selector == null)
            throw new IllegalArgumentException();
        return selector;
    }
}
