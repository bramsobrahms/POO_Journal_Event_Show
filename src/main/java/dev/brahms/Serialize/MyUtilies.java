package dev.brahms.Serialize;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.security.AnyTypePermission;
import dev.brahms.Artist;
import dev.brahms.Show;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class MyUtilies {

    public static Show loadShowFromFile(String filename) {

        File file = new File(filename);

        XStream xstream = new XStream();

        configureXS(xstream);

        return (Show) xstream.fromXML(file);

    }

    private static void configureXS(XStream xstream) {

        xstream.addPermission(AnyTypePermission.ANY);
        xstream.alias("Show", Show.class);
        xstream.alias("artist", Artist.class);
        xstream.useAttributeFor(Show.class, "texte");
        xstream.aliasAttribute("title", "texte");
        xstream.useAttributeFor(Show.class, "dateEvent");
        xstream.aliasAttribute("date", "dateEvent");
        xstream.useAttributeFor(Show.class, "price");
        xstream.addImplicitCollection(Show.class, "artists");

    }

    public static void saveShowToFile(Show show, String filename) {

        File file = new File(filename);
        FileWriter fw = null;

        XStream xstream = new XStream();

        configureXS(xstream);

        try{
            fw = new FileWriter(file);

            xstream.toXML(show, fw);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
