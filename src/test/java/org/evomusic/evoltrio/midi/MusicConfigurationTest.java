package org.evomusic.evoltrio.midi;

import static org.junit.Assert.assertEquals;

import java.util.List;
import java.util.StringTokenizer;

import org.evomusic.evoltrio.midi.Duration;
import org.evomusic.evoltrio.midi.MusicConfiguration;
import org.junit.Before;
import org.junit.Test;

public class MusicConfigurationTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testSetActiveDurationList() {
		String test = "h,q,e,s";
		
		MusicConfiguration.getInstance().setActiveDurationList(test);
		
		List<Duration> dur = MusicConfiguration.getInstance().getActiveDurationList();
		
		// Why ?
		assertEquals("Testing testing", new StringTokenizer(test, ",").countTokens(), dur.size());
		
//		for(Duration d: dur) {
//			System.out.println(d.toString());
//			assertEquals(d, Duration.fromString(d.getCharacter()));
//		}
	}

}
