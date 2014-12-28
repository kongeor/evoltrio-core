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
package org.evomusic.evoltrio.core;

import org.evomusic.evoltrio.midi.MusicConfiguration;
import org.jgap.Chromosome;
import org.jgap.Configuration;
import org.jgap.Gene;
import org.jgap.IChromosome;
import org.jgap.InvalidConfigurationException;
import org.jgap.impl.IntegerGene;

/**
 * The base interface for every Fitness Filter.
 * 
 * @author Kostas Georgiadis
 * @since 0.3.0
 */
public class SampleChromosomeBuilder {

    private Configuration config;
    private MusicConfiguration musicConfig;

    public SampleChromosomeBuilder(Configuration config,
            MusicConfiguration musicConfig) {
        this.config = config;
        this.musicConfig = musicConfig;
    }

    public IChromosome createSampleChromosome() throws InvalidConfigurationException {
        Gene[] sampleGenes = new Gene[musicConfig.getPhraseNotes() * 2];
        
        int intervalJump = musicConfig.getMaxIntervalJump();
        int durationJump = musicConfig.getMaxDurationJump();
        
        for (int i = 0; i < musicConfig.getPhraseNotes() * 2; i += 2) {
            sampleGenes[i] = new IntegerGene(config, -1 * intervalJump, intervalJump);
            sampleGenes[i + 1] = new IntegerGene(config, -1 * durationJump, durationJump);
        }

        return new Chromosome(config, sampleGenes);
    }
    
}
