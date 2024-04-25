package dev.brahms;

import dev.brahms.Serialize.MyUtilies;

public class Main {
    public static void main(String[] args) {


        Show show = new Show("Concert Rasta", "2023-09-04T20:00:00", 50d);

        Artist bob= new Artist("The King of Reggae", "Bob", "Marley", Style.REGGAE);

        show.getArtists().add(bob);

        System.out.println(show);

        MyUtilies.saveShowToFile(show, "dataFile.xml");

        Show restore = MyUtilies.loadShowFromFile("dataFile.xml");

        System.out.println(restore);
    }
}