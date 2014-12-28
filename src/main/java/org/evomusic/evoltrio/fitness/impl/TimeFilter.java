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
import org.evomusic.evoltrio.midi.MusicConfiguration;
import org.evomusic.evoltrio.midi.MusicFactory;
import org.jgap.IChromosome;

/**
 * 
 * @author Kostas Georgiadis
 * @since 0.0.1
 */
public class TimeFilter extends BaseFilter {

	public static int timeBonus = 10;
	
	public TimeFilter(double weigth) {
		super(weigth);
		// TODO Auto-generated constructor stub
	}

	public TimeFilter() {}

	@Override
	public double evaluate(IChromosome chromo) {

		int evaluation = 0;
		int currentDuration = MusicConfiguration.getInstance()
				.getBeginningDuration();
		double totalDuration = MusicFactory
				.convertToDoubleDuration(currentDuration);

		for (int i = 1; i < chromo.size(); i += 2) {
			if (totalDuration % 4 == 0)
				evaluation += 50;

			currentDuration = MusicFactory.calcDurationAtIndex(chromo, i,
					currentDuration);
			totalDuration += MusicFactory
					.convertToDoubleDuration(currentDuration);
		}

		return evaluation * getWeigth();
	}

}
