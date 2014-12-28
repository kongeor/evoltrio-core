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
package org.evomusic.evoltrio.fitness;

import org.evomusic.evoltrio.fitness.impl.DirectionFilter;
import org.evomusic.evoltrio.fitness.impl.DiversityFilter;
import org.evomusic.evoltrio.fitness.impl.PatternFilter;
import org.evomusic.evoltrio.fitness.impl.RootNoteFilter;
import org.evomusic.evoltrio.fitness.impl.ScaleFilter;
import org.evomusic.evoltrio.fitness.impl.SimpleDurationFilter;
import org.evomusic.evoltrio.fitness.impl.SimplePitchFilter;
import org.evomusic.evoltrio.fitness.impl.TimeFilter;

/**
 * 
 * @author Kostas Georgiadis
 * @since 0.0.1
 */
public class FiltersFactory {

	public static enum Filter {
		SIMPLEPITCH, SIMPLEDURATION, SCALE, TIME, DIVERSITY, ROOTNOTE, DIRECTION, PATTERN
	}

	public static IFitnessFilter getFilter(Filter filter) {
		switch (filter) {
		case SIMPLEPITCH:
			return new SimplePitchFilter();
		case SIMPLEDURATION:
			return new SimpleDurationFilter();
		case SCALE:
			return new ScaleFilter();
		case TIME:
			return new TimeFilter();
		case DIVERSITY:
			return new DiversityFilter();
		case ROOTNOTE:
			return new RootNoteFilter();
		case DIRECTION:
			return new DirectionFilter();
		case PATTERN:
			return new PatternFilter();

		default:
			return null; // should never happen
		}
	}
	
	public static IFitnessFilter getFilter(String filter) {
		
		if(filter.equals(Filter.SIMPLEPITCH.name()))
			return new SimplePitchFilter();
		if(filter.equals(Filter.SIMPLEDURATION.name()))
			return new SimpleDurationFilter();
		if(filter.equals(Filter.SCALE.name()))
			return new ScaleFilter();
		if(filter.equals(Filter.TIME.name()))
			return new TimeFilter();
		if(filter.equals(Filter.DIVERSITY.name()))
			return new DiversityFilter();
		if(filter.equals(Filter.ROOTNOTE.name()))
			return new RootNoteFilter();
		if(filter.equals(Filter.DIRECTION.name()))
			return new DirectionFilter();
		if(filter.equals(Filter.PATTERN.name()))
			return new PatternFilter();

			return null; // should never happen
		
	}
	
	public static Filter getName(IFitnessFilter filter) {
		if(filter instanceof SimplePitchFilter)
			return Filter.SIMPLEPITCH;
		if(filter instanceof SimpleDurationFilter)
			return Filter.SIMPLEDURATION;
		if(filter instanceof ScaleFilter)
			return Filter.SCALE;
		if(filter instanceof DirectionFilter)
			return Filter.DIRECTION;
		if(filter instanceof DiversityFilter)
			return Filter.DIVERSITY;
		if(filter instanceof PatternFilter)
			return Filter.PATTERN;
		if(filter instanceof RootNoteFilter)
			return Filter.ROOTNOTE;
		if(filter instanceof TimeFilter)
			return Filter.TIME;

			return null; // should never happen
	}

	// TODO arrayList alternative implementation -- toArray cast ??
	public static String[] getFilterNamesArray() {
		Filter[] filter = Filter.values();
		String[] names = new String[filter.length];

		for (int i = 0; i < filter.length; i++) {
			names[i] = "" + filter[i];
		}

		return names;
	}
	
	public static void main(String[] args) {
		System.out.println(new DirectionFilter().getClass());
	}

}
