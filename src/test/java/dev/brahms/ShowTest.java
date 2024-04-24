package dev.brahms;


import dev.brahms.Exception.InvalidDateException;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

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

    @Test
    void testInterfaceSearchable() {

        Show show = new Show("Concert Rasta", "2023-09-04T20:00", 50d);
        assertTrue(show instanceof Searchable);

    }

    @Test
    void testInvalidDateException() {

        String sunday = "2024-04-21T12:30";

        assertThrows(InvalidDateException.class, () -> new Show("Concert Rasta", sunday));
    }

    @Test
    void testFindBy() {

        Show show = new Show("Concert Rasta","2023-09-04T20:00",50d);

        Artist bob = new Artist("The King of Raggae", "Bob", "Marley", Style.REGGAE);
        Artist elvis = new Artist("The King of Rock", "Elvis", "Presley", Style.ROCK);
        Artist denis = new Artist("The prince of Raggae", "Dennis", "Brown", Style.REGGAE);

        show.getArtists().add(bob);
        show.getArtists().add(elvis);
        show.getArtists().add(denis);

        List listArtist = show.findBy("Reggae");

        assertNotNull(listArtist);
        assertEquals(2, listArtist.size());
        assertTrue(listArtist.contains(bob));
        assertTrue(listArtist.contains(denis));
        assertFalse(listArtist.contains(elvis));

    }

    @Test
    void testToString() {

        Show show = new Show("Fête de la musique", "2023-06-21T18:30");

        Artist bob = new Artist("The King of Raggae", "Bob", "Marley", Style.REGGAE);
        Artist denis = new Artist("The prince of Raggae", "Dennis", "Brown", Style.REGGAE);
        Artist peter = new Artist("The love of Raggae", "Peter", "Tosh", Style.REGGAE);
        Artist jimmy = new Artist("The son of Raggae", "Jimmy", "Cliff", Style.REGGAE);
        Artist tupac = new Artist("The creator of hip hop", "Tupac", "Shakur", Style.HIP_HOP);
        Artist jay = new Artist("the prince of hip hop", "Jay", "Z", Style.HIP_HOP);
        Artist elvis = new Artist("The King of Rock", "Elvis", "Presley", Style.ROCK);

        show.getArtists().add(bob);
        show.getArtists().add(denis);
        show.getArtists().add(peter);
        show.getArtists().add(jimmy);
        show.getArtists().add(tupac);
        show.getArtists().add(jay);
        show.getArtists().add(elvis);

        String result = """
                Fête de la musique - 21/06/2023 à 18:30 - Gratuit
                \t- ROCK (1 artiste)
                \t- HIP_HOP (2 artistes)
                \t- REGGAE (4 artistes)
                """;

        /*
          Remplace tous les séparateurs de ligne dans la chaîne générée par la méthode toString()
          avec le caractère de saut de ligne universel "\n".
         */
        String stringPhrase = show.toString().replace(System.lineSeparator(), "\n");

        assertEquals(result, stringPhrase);

    }


}