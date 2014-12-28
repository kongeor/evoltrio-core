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
public class DiversityFilter extends BaseFilter {

	public DiversityFilter(double weigth) {
		super(weigth);
	}

	public DiversityFilter() {}

	public static final int bonusFactor = 3;
	
	@Override
	public double evaluate(IChromosome chromo) {		
		int evaluation = 0;
		
		int pitch = 0;
		int dur = 0;
		
		int pitchDiff = 0;
		int durDiff = 0;
		
		for (int i = 0; i < chromo.size(); i += 2) {
		    pitch = ((Integer) (chromo.getGene(i).getAllele())).intValue();
		    dur = ((Integer) (chromo.getGene(i+1).getAllele())).intValue();
			
		    evaluation += Math.abs(pitch);
			evaluation += Math.abs(dur);
			
			evaluation += Math.abs(pitch - pitchDiff);
			evaluation += Math.abs(dur - durDiff);
			
			pitchDiff = pitch;
			durDiff = dur;
		}
		
		for (int i = 0; i < chromo.size(); i += 2) {
            evaluation += Math.abs(((Integer) (chromo.getGene(i).getAllele()))
                    .intValue());
            evaluation += Math.abs(((Integer) (chromo.getGene(i+1).getAllele()))
                    .intValue());
        }

		return evaluation * getWeigth();
	}

}
