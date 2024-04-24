package dev.brahms;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ShowTest {

    @Test
    void testShowConstructor() {

        Show show = new Show("Concert Rasta", "2023-09-04T20:00", 50d);

        assertTrue(show instanceof Evenement);
        assertEquals("Concert Rasta", show.getTexte());
        assertEquals("2023-09-04T20:00", show.getDateEvent().toString());
        assertEquals(50d, show.getPrice());

    }

    @Test
    void testShowConstructorWithoutPrice() {

        Show show = new Show("Fête de la musique", "2023-06-21T18:30");

        assertEquals(0d, show.getPrice());

    }


}