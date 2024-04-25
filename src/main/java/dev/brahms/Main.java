package dev.brahms;

import dev.brahms.Serialize.MyUtilities;

public class Main {
    public static void main(String[] args) {


        Show show = new Show("Concert Rasta", "2023-09-04T20:00:00", 50d);

        Artist bob= new Artist("The King of Reggae", "Bob", "Marley", Style.REGGAE);

        show.getArtists().add(bob);

        System.out.println(show);

        // Saves the details of the concert to an XML file.
        MyUtilities.saveShowToFile(show, "dataFile.xml");

        // Restores the details of the concert from the XML file.
        Show restore = MyUtilities.loadShowFromFile("dataFile.xml");

        System.out.println(restore);
    }
}