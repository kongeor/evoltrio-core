package org.evomusic.evoltrio.core;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;

import org.evomusic.evoltrio.midi.MusicConfiguration;
import org.jgap.Configuration;
import org.jgap.Gene;
import org.jgap.InvalidConfigurationException;
import org.junit.Before;
import org.junit.Test;

public class SampleChromosomeBuilderTest {

    private Configuration config;
    private MusicConfiguration musicConfig;
    
    private SampleChromosomeBuilder builder;
    
    @Before
    public void setUp() {
        config = new Configuration();
        musicConfig = MusicConfiguration.getInstance();
        
        musicConfig.setMaxIntervalJump(4);
        musicConfig.setMaxDurationJump(3);
        musicConfig.setPhraseNotes(1);
        
        builder = new SampleChromosomeBuilder(config, musicConfig);
    }

    @Test
    public void testSampleChromosome() throws InvalidConfigurationException {
        Gene[] genes = builder.createSampleChromosome().getGenes();
        assertEquals(Arrays.toString(genes), 
                "[IntegerGene(-4,4)=null, IntegerGene(-3,3)=null]");
    }

}
