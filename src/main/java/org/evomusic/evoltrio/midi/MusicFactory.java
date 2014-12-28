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

import org.jgap.IChromosome;

/**
 * 
 * @author Kostas Georgiadis
 * @since 0.0.1
 */
public class MusicFactory {
	
	
	public static String chromosomeToJFuguePattern(IChromosome chromo) {
		
		int currentPitch = MusicConfiguration.getInstance().getKeyNote();
		int currentDuration = MusicConfiguration.getInstance().getBeginningDuration();

		String pattern = "";
		
		for (int i = 0; i < chromo.size(); i += 2) {

			// set the pitch
			currentPitch = currentPitch
					+ ((Integer) (chromo.getGene(i).getAllele())).intValue();
			// ensure that the pitch is not above 127 or less than 0
			if (currentPitch < 0) {
				// move one octave up
				currentPitch += MusicConfiguration.SCALE_NOTES_COUNT;
			} else if (currentPitch > 127) {
				// move one octave down
				currentPitch -= MusicConfiguration.SCALE_NOTES_COUNT;
			}
			
			currentDuration = calcDurationAtIndex(chromo, i+1, currentDuration);

			pattern += MusicFactory.convertToJFugueNote(currentPitch);
			pattern += MusicFactory.convertToJFugueDuration(currentDuration);
			pattern += " ";

		}
		
		return pattern;
	}

	/**
	 * Calculates the current running duration according to where are we and
	 * what is the change.
	 * 
	 * @param currentDur
	 *            the current duration
	 * @param runningDur
	 *            the int representation of the duration change.
	 * @return the current duration.
	 */
	public static int calcDurationAtIndex(IChromosome chromo, int index,
			int currentDur) {
		int runningDur = ((Integer) (chromo.getGene(index).getAllele()))
				.intValue();
		int dur = currentDur + runningDur;

		//the total available durations
		int durationSum = MusicConfiguration.getInstance().getActiveDurationList().size();
		
		if (dur >= durationSum)
			dur = dur - durationSum;
		else if (dur < 0)
			dur = durationSum + dur;

		return dur;
	}
	
	static public double convertToDoubleDuration(int value) {
		return ((Duration)MusicConfiguration.getInstance().getActiveDurationList().toArray()[value]).getValue();
	}

	public static String convertToJFugueNote(int value) {
		
		int octave = value / MusicConfiguration.SCALE_NOTES_COUNT;
		//if(value / MusicConfiguration.SCALE_NOTE_COUNT)
		return (value % MusicConfiguration.SCALE_NOTES_COUNT)>MusicConfiguration.NOTES.length ?
				(getJFugueNote(value)) : (getJFugueNote(value) + octave);
	}
	/**
	 * Does not return an octave
	 * @param value
	 * @return
	 */
    public static String getJFugueNote(int value){
		String jnote = "";
		int note = value % MusicConfiguration.SCALE_NOTES_COUNT;

		switch(note){
			case 0: jnote = "C"; break;
			case 1: jnote = "C#"; break;
			case 2: jnote = "D"; break;
			case 3: jnote = "D#"; break;
			case 4: jnote = "E"; break;
			case 5: jnote = "F"; break;
			case 6: jnote = "F#"; break;
			case 7: jnote = "G"; break;
			case 8: jnote = "G#"; break;
			case 9: jnote = "A"; break;
			case 10: jnote = "A#"; break;
			case 11: jnote = "B"; break;
			default: jnote =  "R";
		}

		return jnote;

	}

	public static String convertToJFugueDuration(int value) {
		return ((Duration)MusicConfiguration.getInstance().getActiveDurationList().toArray()[value]).getCharacter();
		
	}

}
