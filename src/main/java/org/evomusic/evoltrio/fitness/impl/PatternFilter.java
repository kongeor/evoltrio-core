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
package org.evomusic.evoltrio.fitness.impl;

import org.evomusic.evoltrio.fitness.BaseFilter;
import org.jgap.IChromosome;

/**
 * 
 * @author Kostas Georgiadis
 * @since 0.0.1
 */
public class PatternFilter extends BaseFilter {

	public static final double baseBonus = 2;
	
	public PatternFilter(double weigth) {
		super(weigth);
	}

	public PatternFilter() {}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * gr.evomusic.evoltrio.fitness.IFitnessFilter#evaluate(org.jgap.IChromosome
	 * , int[], int, int)
	 */
	@Override
	public double evaluate(IChromosome chromo) {
		double evaluation = 0;

		evaluation = evaluatePattern(chromo, 1) + evaluatePattern(chromo, 2) +
				evaluatePattern(chromo, 3) + evaluatePattern(chromo, 4);
		return evaluation * getWeigth();
	}
	
	private double evaluatePattern(IChromosome chromo, int degree) {
		double evaluation = 0;
		int size = chromo.size();
		int pattern = 0 ;
		double value = 0;
		for (int i = 0; i < size; i += 2) {
			for(int j=i; j< ((i+degree*2)<size ? i+degree*2 : size); j+=2) {
				value =  ((Integer) (chromo.getGene(i+1).getAllele())).intValue();
				if(value != 0)
					pattern += value;
			}
			
			if(pattern == 0) {
				evaluation += Math.pow(baseBonus, degree);
				pattern = 0;
			}
			
		}
		
		return evaluation;
	}

}
