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

import org.jgap.IChromosome;

/**
 * The base interface for every Fitness Filter.
 * 
 * @author Kostas Georgiadis
 * @since 0.0.1
 */
public interface IFitnessFilter {

	public double getWeigth();
	public void setWeigth(double weigth);
	
	public boolean isEnabled();
	public void setEnabled(boolean enabled);

	/**
	 * Evaluate a music chromosome.
	 * 
	 * @param chromo The chromosome to evaluate
	 * @return The fitness value for the implemented fitness filter.
	 */
	public double evaluate(IChromosome chromo);

}
