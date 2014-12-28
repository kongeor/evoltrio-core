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
package org.evomusic.evoltrio.midi;

import java.util.HashMap;
import java.util.Map;


/**
 * A Scale representation enum constant that can hold 
 * the intervals of well known musical scales.
 * 
 * @author Kostas Georgiadis
 * @since 0.1.0
 *
 */
public enum Scale {
	
	MAJOR(new int[]{0,2,4,5,7,9,11}),
	MINOR(new int[]{0,2,3,5,7,8,10}),
	MINOR_PENTATONIC(new int[]{0,3,5,7,10}),
	MINOR_BLUES(new int[]{0,3,5,6,7,10}),
	MINOR_HARMONIC(new int[]{0,2,3,5,7,8,11});
	
	private static final Map<String, Scale> stringToEnum  =
			new HashMap<String, Scale>();
	
	static {
		for (Scale s: Scale.values())
			stringToEnum.put(s.name(), s);
	}
	
	public static Scale fromString(String name) {
		return stringToEnum.get(name);
	}
	
	public static String[] stringValues() {
		String[] values = new String[Scale.values().length];
		
		for (int i = 0; i < values.length; i++) {
			values[i] = Scale.values()[i].name();
		}
		
		return values;
	}
	
	private int[] intervals;
	
	private Scale(int[] intervals) {
		this.intervals = intervals;
	}
	
	public int[] getIntervals() {
		return intervals;
	}

}
