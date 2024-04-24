package dev.brahms;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ShowTest {

    @Test
    void testShowConstructor() {

        Show show = new Show("Concert Rasta", "2023-09-04T20:00", 50d);

        assertTrue(show instanceof Searchable);
        assertTrue(show instanceof Evenement);
        assertEquals("Concert Rasta", show.getTexte());
        assertEquals("2023-09-04T20:00", show.getDateEvent().toString());
        assertEquals(50d, show.getPrice());

    }


}