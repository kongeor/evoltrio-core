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

import org.evomusic.evoltrio.fitness.SoloFitnessEvol;
import org.jgap.Configuration;
import org.jgap.DefaultFitnessEvaluator;
import org.jgap.InvalidConfigurationException;
import org.jgap.event.EventManager;
import org.jgap.impl.ChromosomePool;
import org.jgap.impl.CrossoverOperator;
import org.jgap.impl.GABreeder;
import org.jgap.impl.MutationOperator;

/**
 * 
 * @author Kostas Georgiadis
 * @since 0.3.0
 */
public class EvolutionConfigBuilder {
    
    private EvolConfiguration evolutionConfig;
    
    public EvolutionConfigBuilder(EvolConfiguration evolutionConfig) {
        this.evolutionConfig = evolutionConfig;
    }

    public Configuration buildConfig() throws InvalidConfigurationException {
        Configuration conf = new Configuration();

        // set the ones according to DefaultConfiguration in jgap
        conf.setBreeder(new GABreeder());
        conf.setEventManager(new EventManager());
        conf.setFitnessEvaluator(new DefaultFitnessEvaluator());
        conf.setChromosomePool(new ChromosomePool());
        conf.setRandomGenerator(new RandomGeneratorResolver()
                .resolve(evolutionConfig.getRandomGen()));
        conf.addNaturalSelector(new NaturalSelectorResolver()
                .resolve(evolutionConfig.getNaturalSel()), evolutionConfig
                .getExecuteNaturalBefore());

        // set the rest
        conf.setMinimumPopSizePercent(evolutionConfig.getMinPopSizePercent());
        conf.setSelectFromPrevGen(evolutionConfig.getSelectFromPrevGen());
        conf.setKeepPopulationSizeConstant(evolutionConfig
                .getKeepPopSizeConstant());
        conf.setPopulationSize(evolutionConfig.getPopulationSize());

        Double crossoverRate = evolutionConfig.getCrossoverRate();
        if (crossoverRate != null)
            conf.addGeneticOperator(new CrossoverOperator(conf, crossoverRate));

        Integer mutationRate = evolutionConfig.getMutationRate();
        if (mutationRate != null)
            conf.addGeneticOperator(new MutationOperator(conf, mutationRate));

        conf.setFitnessFunction(new SoloFitnessEvol());

        return conf;
    }
}
