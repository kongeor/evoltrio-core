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
import org.jgap.IChromosome;

/**
 * 
 * @author Kostas Georgiadis
 * @since 0.0.1
 */
public class ScaleFilter extends BaseFilter {
	
	public static final int inScaleBonus = 20;
	
	public ScaleFilter(double weigth) {
		super(weigth);
	}

	public ScaleFilter() {}

	@Override
	public double evaluate(IChromosome chromo) {
		
		int currentNote = MusicConfiguration.getInstance().getKeyNote();
		int[] scale = MusicConfiguration.getInstance().getScaleIntervals();
		// offset is needed for the cross-scale check
		int offset = MusicConfiguration.getInstance().getKeyNote() % 12;
		int evaluation = 0;

		for (int i = 0; i < chromo.size(); i += 2) {
			currentNote += ((Integer) (chromo.getGene(i).getAllele()))
					.intValue();
			
			for(int note: scale)
				if ( note == ((currentNote%12) - offset))
					evaluation += inScaleBonus;
					

		}

		return evaluation * getWeigth();
	}

}
