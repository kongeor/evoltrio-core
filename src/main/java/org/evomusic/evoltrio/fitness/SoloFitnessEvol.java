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

import org.jgap.FitnessFunction;
import org.jgap.IChromosome;

/**
 * 
 * @author Kostas Georgiadis
 * @since 0.0.1
 */
public class SoloFitnessEvol extends FitnessFunction {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1179210604817322613L;

	private IFitnessFilter[] filters;

	public SoloFitnessEvol() {
		filters = new IFitnessFilter[FiltersFactory.Filter.values().length];
		
		for (int i = 0; i < filters.length; i++) {
			filters[i] = FiltersFactory.getFilter(FiltersFactory.Filter.values()[i]);
			
		}
		
		
		
		enableAllFilters(); // TODO check
	}
	
	public IFitnessFilter[] getFilters() {
		return filters;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.jgap.FitnessFunction#evaluate(org.jgap.IChromosome)
	 */
	@Override
	protected double evaluate(IChromosome chromo) {

		double evaluation = 0;

		for (IFitnessFilter filter : filters)
			try {
				if (filter.isEnabled())
					evaluation += filter.evaluate(chromo);
			} catch (Exception e) {
				e.printStackTrace();
			}

		return evaluation;
	}

	public void setEnabled(String name, boolean enabled) {
		for (IFitnessFilter filter : filters)
			if (FiltersFactory.getName(filter).name().equals(name))
				filter.setEnabled(enabled);
	}
	
	public boolean isEnabled(String name) {
		for (IFitnessFilter filter : filters)
			if (FiltersFactory.getName(filter).name().equals(name))
				if(filter.isEnabled())
					return true;
		
		return false;
	}
	
	
	public void setWeight(String name, double weigth) {
		for (IFitnessFilter filter : filters)
			if (FiltersFactory.getName(filter).name().equals(name))
				filter.setWeigth(weigth);
	}
	
	public double getWeight(String name) {
		
		for (IFitnessFilter filter : filters)
			if (FiltersFactory.getName(filter).name().equals(name))
				return filter.getWeigth();
		
		return -1;
	}

	public String[] getActiveFiltersNameArray() {
		String[] names = new String[filters.length];

		int index = 0;

		for (IFitnessFilter filter : filters) {
			if (filter.isEnabled())
				names[index++] = FiltersFactory.getName(filter).name();
		}

		return names;

	}
	
	public IFitnessFilter getFilter(String name) {
		for (IFitnessFilter filter : filters)
			if (FiltersFactory.getName(filter).name().equals(name))
				return filter;
		
		return null;
	}

	public void enableAllFilters() {
		for (IFitnessFilter filter : filters)
			filter.setEnabled(true);
	}

	public void disableAllFilters() {
		for (IFitnessFilter filter : filters)
			filter.setEnabled(false);
	}

	public String toString() {
		String str = "---------------------------------------------------\n"
				+ " Enabled Fitness Filters                                   \n"
				+ "---------------------------------------------------\n\n";
		for (IFitnessFilter filter : filters) {
			if(filter.isEnabled()) {
				str += FiltersFactory.getName(filter) + " --  Weigth: "
					+ filter.getWeigth() + "\n";
			}
		}

		str += "\n";

		return str;
	}

}
