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

import org.evomusic.evoltrio.core.util.Inspectable;
import org.evomusic.evoltrio.fitness.SoloFitnessEvol;

/**
 * 
 * @author Kostas Georgiadis
 * @since 0.0.1
 */
public class EvolConfiguration implements Inspectable {

	@Pref(name="Random Generator", help="The random generator to use", type=PrefType.COMBO)
	@ComboPref(allowedValues={"stock", "cauchy", "gaussian", "weighted"}, resolver=RandomGeneratorResolver.class)
	private String randomGen = "stock";
	public String getRandomGen() { return randomGen; }
    public void setRandomGen(String randomGen) { this.randomGen = randomGen; }

    @Pref(name="Natural Selector", help="The natural selector to use", type=PrefType.COMBO)
	@ComboPref(allowedValues={"best", "threshold", "tournament"}, resolver=NaturalSelectorResolver.class)
	private String naturalSel = "best";
	public String getNaturalSel() { return naturalSel; }
    public void setNaturalSel(String naturalSel) { this.naturalSel = naturalSel; }

	// TODO change this with allow doublette

    @Pref(name="Execture Natural Selectors Before Genetic Operators", type=PrefType.BOOLEAN)
	private boolean executeNaturalBefore = false;
	public boolean getExecuteNaturalBefore() { return executeNaturalBefore; }
    public void setExecuteNaturalBefore(boolean executeNaturalBefore) { this.executeNaturalBefore = executeNaturalBefore; }

    @Pref(name="Minimum Population Size Percent", help="Minimum size guaranteed for population", type=PrefType.PERCENTAGE)
	private int minPopSizePercent = 0;
	public int getMinPopSizePercent() { return minPopSizePercent; }
    public void setMinPopSizePercent(int minPopSizePercent) { this.minPopSizePercent = minPopSizePercent; }

    @Pref(name="Select From Previous Generation", help="The percent of the chromsomes to select from the previous geneartion",
	        type=PrefType.PERCENTAGE)
	private double selectFromPrevGen = 1.0d;
	public double getSelectFromPrevGen() { return selectFromPrevGen; }
    public void setSelectFromPrevGen(double selectFromPrevGen) { this.selectFromPrevGen = selectFromPrevGen; }

    @Pref(name="Keep Population Size Constant", type=PrefType.BOOLEAN)
	private boolean keepPopSizeConstant = false;
	public boolean getKeepPopSizeConstant() { return keepPopSizeConstant; }
    public void setKeepPopSizeConstant(boolean keepPopSizeConstant) { this.keepPopSizeConstant = keepPopSizeConstant; }

    @Pref(name="Crossover Rate", type=PrefType.PERCENTAGE)
	private Double crossoverRate = 0.65d;
	public Double getCrossoverRate() { return crossoverRate; }
    public void setCrossoverRate(Double crossoverRate) { this.crossoverRate = crossoverRate; }

    @Pref(name="Mutation Rate", type=PrefType.PERCENTAGE)
	private Integer mutationRate = 42;
	public Integer getMutationRate() { return mutationRate; }
    public void setMutationRate(Integer mutationRate) { this.mutationRate = mutationRate; }

    @Pref(name="Iterations", help="The number of evolution cycles", type=PrefType.NUMERIC)
    private int iterations = 100;
	public int getIterations() { return iterations; }
    public void setIterations(int iterations) { this.iterations = iterations; }

    @Pref(name="Population Size", type=PrefType.NUMERIC)
	private int population = 30;
	public int getPopulationSize() { return population; }
	public void setPopulationSize(int population) { this.population = population; }


    private SoloFitnessEvol soloFitnessEvol;

	public EvolConfiguration() {
		soloFitnessEvol = new SoloFitnessEvol();
	}
	
	public SoloFitnessEvol getSoloFitnessEvol() {
		return soloFitnessEvol;
	}

	@Override
	public String toString() {
	    return inspect();
	}

}
