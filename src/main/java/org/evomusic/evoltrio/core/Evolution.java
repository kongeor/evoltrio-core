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
import org.jgap.Genotype;
import org.jgap.IChromosome;
import org.jgap.InvalidConfigurationException;
import org.jgap.impl.IntegerGene;

/**
 * 
 * @author Kostas Georgiadis
 * @since 0.0.1
 */
public class Evolution {
	
	// version
	public static final String VERSION_MAJOR = "0";
	public static final String VERSION_MINOR = "2";
	public static final String VERSION_PATCHSET = "1";
	
	protected Genotype population;

    protected IChromosome[] phrases;
    
    private EvolConfiguration evolConf;
    private Configuration conf;
    
    private String preset = "";

    public Evolution() {
        evolConf = new EvolConfiguration();
    }

    public void setup() throws InvalidConfigurationException {
    	conf = new EvolutionConfigBuilder(evolConf).buildConfig();
    	SampleChromosomeBuilder sampleBuilder = 
    	        new SampleChromosomeBuilder(conf, MusicConfiguration.getInstance());

    	conf.setSampleChromosome(sampleBuilder.createSampleChromosome());
    	population = Genotype.randomInitialGenotype(conf);
    }

    public void evolve() throws Exception {

        StringBuffer progress = new StringBuffer("|");
        int maxBars = 50;
        int bar;

        int iterations = 100; //TODO set it up

        for (int i = 0; i < phrases.length; i++) {
            population = Genotype.randomInitialGenotype(conf);
            for (int j = 0; j <= iterations; j++) {
                // System.out.println("Phrase: " + (i+1) + ", iteration: " + j);

                progress = new StringBuffer("Fitness : "
                        + population.getFittestChromosome().getFitnessValue()
                        + "\tProgress: "
                        + (int) (((double) (i * iterations + j)
                                / (phrases.length * iterations) * 100)) + "% ");
                progress.append("|");
                bar = (maxBars * (i * iterations + j))
                        / (phrases.length * iterations);

                for (int k = 0; k < bar; k++)
                    progress.append("=");

                for (int k = 0; k < maxBars - bar; k++)
                    progress.append(" ");

                progress.append("| Phrase : " + (i + 1) + "/" + phrases.length);

                progress.append("\r");
                System.out.print(progress);
                population.evolve();
                // System.out.println(bestPhrase);
            }
            phrases[i] = population.getFittestChromosome();

        }
        System.out.println();
    }

    public IChromosome evolveOnce() {

        population.evolve();
        return population.getFittestChromosome();
    }

    public IChromosome getFittestChromosome() {
        return population.getFittestChromosome();
    }

    public Genotype getPopulation() {
        return population;
    }
    
    public Configuration getConf() {
		return conf;
	}

	public EvolConfiguration getEvolConf() {
		return evolConf;
	}

	public void setEvolConf(EvolConfiguration evolConf) {
		this.evolConf = evolConf;
	}
	
	public String getPreset() {
        return preset;
    }

    public void setPreset(String preset) {
        this.preset = preset;
    }

    public String getVersion() {
		return VERSION_MAJOR + "." + VERSION_MINOR + "." + VERSION_PATCHSET;		
	}

	public static void main(String args[]) throws Exception {
    	//new EvolTrioUI();
    }
}
