package org.evomusic.evoltrio.core.util;

import static org.junit.Assert.*;

import org.junit.Test;

public class InspectableTest {

    @Test
    public void test() {
        assertEquals("{bar: a, baz: 1}", new Foo("a", 1).inspect());
    }
    
    private static class Foo implements Inspectable {

        private String bar;
        public String getBar() { return bar; }
        public void setBar(String bar) { this.bar = bar; }

        private int baz;
        public int getBaz() { return baz; }
        public void setBaz(int baz) { this.baz = baz; }

        public Foo(String bar, int baz) {
            this.bar = bar;
            this.baz = baz;
        }
    }

}
