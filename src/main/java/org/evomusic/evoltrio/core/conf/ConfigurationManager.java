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
package org.evomusic.evoltrio.core.conf;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.evomusic.evoltrio.core.EvolConfiguration;
import org.evomusic.evoltrio.fitness.FiltersFactory.Filter;
import org.evomusic.evoltrio.fitness.SoloFitnessEvol;
import org.evomusic.evoltrio.midi.MusicConfiguration;

/**
 * 
 * @author Kostas Georgiadis
 * @since 0.0.1
 */
public class ConfigurationManager {
    public static final String PRESET_NAME = "name";
    public static final String PRESET_REVISION = "revision";
	
	public static final String BEGGINING_DURATION = "begDur";
	public static final String PHRASE_NOTES = "phraseNotes";
	public static final String INTERVAL_JUMP = "intJump";
	public static final String DURATION_JUMP = "durJump";
	public static final String ROOTNOTE = "root";
	public static final String OCTAVE = "octave";
	public static final String TEMPO = "tempo";
	public static final String INSTRUMENT = "instrument";
	
	public static final String ACTIVE_DURATION_LIST = "activeDurationList";
	
	public static final String SCALE_NAME = "scaleName";
	public static final String SCALE_INTERVALS = "scaleIntervals";
	
	public static final String RANDOM_GENERATOR = "randomGen";
	public static final String NATURAL_SELECTOR = "naturalSel";
	public static final String EXECUTE_GO_BEFORE_NS = "execBefore";
	public static final String MIN_POP_SIZE_PERCENT = "minPop";
	public static final String SELECT_FROM_PREVIOUS_GEN = "previousGen";
	public static final String KEEP_POP_SIZE_CONSTANT = "popConstant";
	public static final String CROSSOVER = "crossover";
	public static final String MUTATION = "mutation";
	public static final String POP_SIZE = "population";
	public static final String ITERATIONS = "iterations";
	
	public static final String SIMPLE_PITCH = "simplepitch";
	public static final String SIMPLE_PITCH_WEIGHT = "pitchWeight";
	public static final String SIMPLE_DURATION = "simpleduration";
	public static final String SIMPLE_DURATION_WEIGHT = "durationWeight";
	public static final String SCALE = "scale";
	public static final String SCALE_WEIGHT = "scaleWeight";
	public static final String TIME = "time";
	public static final String TIME_WEIGHT = "timeWeight";
	public static final String DIVERSITY= "diversity";
	public static final String DIVERSITY_WEIGHT = "diversityWeight";
	public static final String ROOT = "rootNote";
	public static final String ROOT_WEIGHT = "rootNoteWeight";
	public static final String PATTERN = "pattern";
	public static final String PATTERN_WEIGHT = "patternWeight";
	public static final String DIRECTION = "direction";
	public static final String DIRECTION_WEIGHT = "directionWeight";

	public static String CONF_PATH = "";

	public static File[] availableConfigurations() {
		List<File> confs = new ArrayList<File>();

		File path = new File(CONF_PATH);

		for (File dirItem : path.listFiles())
			if (dirItem.isFile())
				confs.add(dirItem);

		// TODO HOW GOD DAMMIT!
		File[] fileConfs = new File[confs.size()];

		for (int i = 0; i < confs.size(); i++)
			fileConfs[i] = confs.get(i);

		return fileConfs;
	}
	
	public static boolean deleteConfiguration(String name) {
		for(File file : availableConfigurations())
			if (file.getName().equals(name))
				return file.delete();
		
		return false;
	}

	public static String[] availableConfsString() {
	    String[] confs = { "east", "atonic", "doom_bass", "fun_with_cowboys", 
	            "electronic", "chick", "smooth", "psychotic", "retro", "blue",
	            "barok", "my_baby"};
	    

	    return confs;
	}
	
	public static int getSelectedConfIndex(String name) {
		String[] confs = availableConfsString();
		
		for (int i = 0; i < confs.length; i++) {
			if(confs[i].equals(name))
				return i;
		}
		return 0; //default
	}

	public static void parse(String filename, EvolConfiguration evolConf) {
		
		SoloFitnessEvol fitness = evolConf.getSoloFitnessEvol();

		Properties config = new Properties();
		
		InputStream ins;
		try {
			//ins = new FileInputStream(new File(CONF_PATH + filename));
			ins = ConfigurationManager.class.getResourceAsStream(CONF_PATH + filename);
			config.load(ins);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		//parse preset stuff
		MusicConfiguration.getInstance().setPresetName(
		        config.getProperty(PRESET_NAME));
		MusicConfiguration.getInstance().setRevisionVersion(
		        Float.parseFloat(config.getProperty(PRESET_REVISION)));
		

		// parse music parameters
		MusicConfiguration.getInstance().setBeginningDuration(
				Integer.parseInt(config.getProperty(BEGGINING_DURATION)));
		MusicConfiguration.getInstance().setPhraseNotes(
				Integer.parseInt(config.getProperty(PHRASE_NOTES)));
		MusicConfiguration.getInstance().setMaxIntervalJump(
				Integer.parseInt(config.getProperty(INTERVAL_JUMP)));
		MusicConfiguration.getInstance().setMaxDurationJump(
				Integer.parseInt(config.getProperty(DURATION_JUMP)));
		MusicConfiguration.getInstance().setRootNote(
				config.getProperty(ROOTNOTE));
		MusicConfiguration.getInstance().setOctave(
				Integer.parseInt(config.getProperty(OCTAVE)));
		MusicConfiguration.getInstance().setTempo(config.getProperty(TEMPO));
		MusicConfiguration.getInstance().setSoloOrgan(
				Integer.parseInt(config.getProperty(INSTRUMENT)));
		
		// parse duration array
		MusicConfiguration.getInstance().setActiveDurationList(config.getProperty(ACTIVE_DURATION_LIST));
		
		MusicConfiguration.getInstance().setScaleName(config.getProperty(SCALE_NAME));
		MusicConfiguration.getInstance().setIntervalsString(config.getProperty(SCALE_INTERVALS));

		// parse evolutionary parameters
		evolConf.setRandomGen(config.getProperty(RANDOM_GENERATOR));
		evolConf.setNaturalSel(config.getProperty(NATURAL_SELECTOR));
		evolConf.setExecuteNaturalBefore(Boolean.parseBoolean(config
				.getProperty(EXECUTE_GO_BEFORE_NS)));
		evolConf.setMinPopSizePercent(Integer.parseInt(config.getProperty(MIN_POP_SIZE_PERCENT)));
		evolConf.setSelectFromPrevGen(Double.parseDouble(config
				.getProperty(SELECT_FROM_PREVIOUS_GEN)));
		evolConf.setKeepPopSizeConstant(Boolean.parseBoolean(config
				.getProperty(KEEP_POP_SIZE_CONSTANT)));
		evolConf.setCrossoverRate(Double.parseDouble(config
				.getProperty(CROSSOVER)));
		evolConf.setMutationRate(Integer.parseInt(config
				.getProperty(MUTATION)));
		evolConf.setPopulationSize(Integer.parseInt(config.getProperty(POP_SIZE)));
		
		evolConf.setIterations(Integer.parseInt(config.getProperty(ITERATIONS)));


		// parse Solo Fitness Parameters
		fitness.setEnabled(Filter.SIMPLEPITCH.name(),
				Boolean.parseBoolean(config.getProperty(SIMPLE_PITCH)));
		fitness.setWeight(Filter.SIMPLEPITCH.name(),
				Double.parseDouble(config.getProperty(SIMPLE_PITCH_WEIGHT)));

		fitness.setEnabled(Filter.SIMPLEDURATION.name(),
				Boolean.parseBoolean(config.getProperty(SIMPLE_DURATION)));
		fitness.setWeight(Filter.SIMPLEDURATION.name(),
				Double.parseDouble(config.getProperty(SIMPLE_DURATION_WEIGHT)));

		fitness.setEnabled(Filter.SCALE.name(),
				Boolean.parseBoolean(config.getProperty(SCALE)));
		fitness.setWeight(Filter.SCALE.name(),
				Double.parseDouble(config.getProperty(SCALE_WEIGHT)));

		fitness.setEnabled(Filter.TIME.name(),
				Boolean.parseBoolean(config.getProperty(TIME)));
		fitness.setWeight(Filter.TIME.name(),
				Double.parseDouble(config.getProperty(TIME_WEIGHT)));

		fitness.setEnabled(Filter.DIVERSITY.name(),
				Boolean.parseBoolean(config.getProperty(DIVERSITY)));
		fitness.setWeight(Filter.DIVERSITY.name(),
				Double.parseDouble(config.getProperty(DIVERSITY_WEIGHT)));

		fitness.setEnabled(Filter.ROOTNOTE.name(),
				Boolean.parseBoolean(config.getProperty(ROOT)));
		fitness.setWeight(Filter.ROOTNOTE.name(),
				Double.parseDouble(config.getProperty(ROOT_WEIGHT)));

		fitness.setEnabled(Filter.PATTERN.name(),
				Boolean.parseBoolean(config.getProperty(PATTERN)));
		fitness.setWeight(Filter.PATTERN.name(),
				Double.parseDouble(config.getProperty(PATTERN_WEIGHT)));

		fitness.setEnabled(Filter.DIRECTION.name(),
				Boolean.parseBoolean(config.getProperty(DIRECTION)));
		fitness.setWeight(Filter.DIRECTION.name(),
				Double.parseDouble(config.getProperty(DIRECTION_WEIGHT)));

		// add all filters
		if (Boolean.parseBoolean(config.getProperty("filtAll"))) {
			fitness.enableAllFilters();
		}

//		System.out.println(MusicConfiguration.getInstance());
//		System.out.println(evolConf);
//		System.out.println(fitness);
	}
	
	public static void save(String filename, EvolConfiguration evolConf) {
		Properties config = new Properties();
		
		config.put(BEGGINING_DURATION, "" + MusicConfiguration.getInstance().getBeginningDuration());
		config.put(PHRASE_NOTES, "" + MusicConfiguration.getInstance().getPhraseNotes());
		config.put(INTERVAL_JUMP, "" + MusicConfiguration.getInstance().getMaxIntervalJump());
		config.put(DURATION_JUMP, "" + MusicConfiguration.getInstance().getMaxDurationJump());
		config.put(ROOTNOTE, "" + MusicConfiguration.getInstance().getRootNote());
		config.put(OCTAVE, "" + MusicConfiguration.getInstance().getOctave());
		config.put(TEMPO, MusicConfiguration.getInstance().getTempo());
		config.put(INSTRUMENT,"" + MusicConfiguration.getInstance().getSoloOrgan());
		
		config.put(ACTIVE_DURATION_LIST, MusicConfiguration.getInstance().getActiveDurationString());
		
		config.put(SCALE_NAME, MusicConfiguration.getInstance().getScaleName());
		config.put(SCALE_INTERVALS, MusicConfiguration.getInstance().getIntervalsString());
		
		config.put(RANDOM_GENERATOR, evolConf.getRandomGen());
		config.put(NATURAL_SELECTOR, evolConf.getNaturalSel());
		config.put(EXECUTE_GO_BEFORE_NS,"" + evolConf.getExecuteNaturalBefore());
		config.put(MIN_POP_SIZE_PERCENT,"" + evolConf.getMinPopSizePercent());
		config.put(SELECT_FROM_PREVIOUS_GEN,"" + evolConf.getSelectFromPrevGen());
		config.put(KEEP_POP_SIZE_CONSTANT,"" + evolConf.getKeepPopSizeConstant());
		config.put(CROSSOVER, "" +evolConf.getCrossoverRate());
		config.put(MUTATION, "" + evolConf.getMutationRate());
		config.put(POP_SIZE, "" + evolConf.getPopulationSize());
		config.put(ITERATIONS, "" + evolConf.getIterations());
		
		config.put(SIMPLE_PITCH,  "" + evolConf.getSoloFitnessEvol().getFilter(SIMPLE_PITCH.toUpperCase()).isEnabled());
		config.put(SIMPLE_PITCH_WEIGHT,  "" + evolConf.getSoloFitnessEvol().getFilter(SIMPLE_PITCH.toUpperCase()).getWeigth());
		config.put(SIMPLE_DURATION,  "" + evolConf.getSoloFitnessEvol().getFilter(SIMPLE_DURATION.toUpperCase()).isEnabled());
		config.put(SIMPLE_DURATION_WEIGHT,  "" + evolConf.getSoloFitnessEvol().getFilter(SIMPLE_DURATION.toUpperCase()).getWeigth());
		config.put(SCALE,  "" + evolConf.getSoloFitnessEvol().getFilter(SCALE.toUpperCase()).isEnabled());
		config.put(SCALE_WEIGHT,  "" + evolConf.getSoloFitnessEvol().getFilter(SCALE.toUpperCase()).getWeigth());
		config.put(TIME,  "" + evolConf.getSoloFitnessEvol().getFilter(TIME.toUpperCase()).isEnabled());
		config.put(TIME_WEIGHT,  "" + evolConf.getSoloFitnessEvol().getFilter(TIME.toUpperCase()).getWeigth());
		config.put(DIVERSITY,  "" + evolConf.getSoloFitnessEvol().getFilter(DIVERSITY.toUpperCase()).isEnabled());
		config.put(DIVERSITY_WEIGHT,  "" + evolConf.getSoloFitnessEvol().getFilter(DIVERSITY.toUpperCase()).getWeigth());
		config.put(ROOT,  "" + evolConf.getSoloFitnessEvol().getFilter(ROOT.toUpperCase()).isEnabled());
		config.put(ROOT_WEIGHT,  "" + evolConf.getSoloFitnessEvol().getFilter(ROOT.toUpperCase()).getWeigth());
		config.put(PATTERN,  "" + evolConf.getSoloFitnessEvol().getFilter(PATTERN.toUpperCase()).isEnabled());
		config.put(PATTERN_WEIGHT,  "" + evolConf.getSoloFitnessEvol().getFilter(PATTERN.toUpperCase()).getWeigth());
		config.put(DIRECTION,  "" + evolConf.getSoloFitnessEvol().getFilter(DIRECTION.toUpperCase()).isEnabled());
		config.put(DIRECTION_WEIGHT,  "" + evolConf.getSoloFitnessEvol().getFilter(DIRECTION.toUpperCase()).getWeigth());
		
		try {
			config.store(new FileOutputStream(new File(filename)),"Generated By evoltrio-core");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}
	
}
