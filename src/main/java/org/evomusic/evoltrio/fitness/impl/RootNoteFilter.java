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
public class RootNoteFilter extends BaseFilter {

	public final static int rootBonus = 20;
	
	public RootNoteFilter(double weigth) {
		super(weigth);
	}

	public RootNoteFilter() {}

	@Override
	public double evaluate(IChromosome chromo) {	
		int keyNote = MusicConfiguration.getInstance().getKeyNote();
		int currentNote = keyNote;
		// offset is needed for the cross-scale check
		int evaluation = 0;

		for (int i = 0; i < chromo.size(); i += 2) {
			currentNote += ((Integer) (chromo.getGene(i).getAllele()))
					.intValue();
		
//			if ( keyNote == currentNote )
//				evaluation += rootBonus;
//			else if ((keyNote%12) == (currentNote%12))
//				evaluation += rootBonus/2;
			if(isInMelodicApproach(currentNote))
				evaluation += rootBonus;

		}

		return evaluation * getWeigth();
	}
	
	private boolean isInMelodicApproach(int currentNote) {
		int[] intervals = MusicConfiguration.getInstance().getScaleIntervals();
		
		int note = currentNote%12;
		
		if(intervals.length > 4)
			if( note == intervals[0] || 
					note == intervals[4]  )
				return true;
		
		return false;
		
	}

}
