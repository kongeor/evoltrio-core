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
public class SimpleDurationFilter extends BaseFilter {

	public static final double durationBonus = 20;
	
	public SimpleDurationFilter(double weigth) {
		super(weigth);
		// TODO Auto-generated constructor stub
	}

	public SimpleDurationFilter() {}

	@Override
	public double evaluate(IChromosome chromo) {

		int zeroCount = 1;
		double evaluation = 0;

		for (int i = 1; i < chromo.size(); i += 2) {
			if (((Integer) (chromo.getGene(i).getAllele())).intValue() == 0)
				zeroCount ++;
				
		}
		evaluation = zeroCount * durationBonus;
		return evaluation * getWeigth();
	}
}
