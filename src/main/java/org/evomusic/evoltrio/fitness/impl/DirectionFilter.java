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
public class DirectionFilter extends BaseFilter {

	DirectionFilter(double weigth) {
		super(weigth);
	}

	public DirectionFilter() {}

	private static final double directionBonus = 1.2;

	@Override
	public double evaluate(IChromosome chromo) {
		double evaluation = 0;
		int direction = 0;

		int currentDirection = 0;
		int aggregatedDirection = 0;

		double directionFactor = directionBonus;

		for (int i = 0; i < chromo.size(); i += 2) {
			currentDirection = ((Integer) (chromo.getGene(i).getAllele()))
					.intValue();

			if ((currentDirection > 0 && direction > 0)
					|| (currentDirection < 0 && direction < 0))
				directionFactor *= directionBonus;
			// direction changed or stayed the same
			else if ((direction == 0 && (direction > 0 || direction < 0))) {
				// nothing
			} else {
				evaluation += directionFactor;
				directionFactor = directionBonus;
			}

			direction = currentDirection;
			aggregatedDirection += (direction * directionBonus);
		}

		return evaluation * getWeigth();
	}

}
