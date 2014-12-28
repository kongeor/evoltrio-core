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
 * 
 * @author Kostas Georgiadis
 * @since 0.1.0
 */
public abstract class BaseFilter implements IFitnessFilter {

	private double weigth;
	private boolean enabled;
	
	public BaseFilter() {
		this(1.0);
	}
	
	public BaseFilter(double weigth) {
		this.weigth = weigth;
	}
	
	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public double getWeigth() {
		return weigth;
	}

	public void setWeigth(double weigth) {
		this.weigth = weigth;
	}
	
	@Override
	public abstract double evaluate(IChromosome chromo);

}
