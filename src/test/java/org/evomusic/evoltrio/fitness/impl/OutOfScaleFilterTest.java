package org.evomusic.evoltrio.fitness.impl;

import static org.junit.Assert.assertEquals;

import org.evomusic.evoltrio.fitness.impl.ScaleFilter;
import org.evomusic.evoltrio.midi.MusicConfiguration;
import org.jgap.Chromosome;
import org.jgap.Configuration;
import org.jgap.Gene;
import org.jgap.IChromosome;
import org.jgap.InvalidConfigurationException;
import org.jgap.impl.IntegerGene;
import org.junit.Before;
import org.junit.Test;

public class OutOfScaleFilterTest {
	
	private Configuration conf;
	
	@Before
	public void initialize() {
		conf = new Configuration();
	}

	@Test
	public void testEvaluate() throws InvalidConfigurationException {
		//change to the key of D
		MusicConfiguration.getInstance().setRootNote("D");
		
		
		ScaleFilter filter = new ScaleFilter();
		Gene[] genes = new Gene[14];
		
		int[] notes = { 0, 2, 2, 0, -2, -2, 0 }; //all the notes are in scale
		int correctNotes = 7;
		int[] durations = { 0, 0, 0, 0, 0, 0, 0 }; //duration are irrelevant
		
		for (int i = 0; i < genes.length/2; i++) {
			genes[i*2] = new IntegerGene(conf);
			genes[i*2].setAllele(new Integer(notes[i]));
			
			genes[(i*2)+1] = new IntegerGene(conf);
			genes[(i*2)+1].setAllele(new Integer(durations[i]));
		}
		
		IChromosome chromosome = new Chromosome(conf, genes);
		assertEquals("Result", correctNotes*ScaleFilter.inScaleBonus, filter.evaluate(chromosome), 0);
		
	}

}
