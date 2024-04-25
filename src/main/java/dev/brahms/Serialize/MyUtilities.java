package dev.brahms.Serialize;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.security.AnyTypePermission;
import dev.brahms.Artist;
import dev.brahms.Show;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Utility class for loading and saving Show Object
 * to/from XML files using XStream Core
 */
public class MyUtilies {

    /**
     * Loads a Show object from XML file.
     *
     * @param filename the name of the XML file to load the Show object from
     * @return The Show object loaded from the XML file.
     */
    public static Show loadShowFromFile(String filename) {

        File file = new File(filename);

        XStream xstream = new XStream();

        configureXS(xstream);

        return (Show) xstream.fromXML(file);

    }

    /**
     * Configures the XStream instance with aliases and permissions for Show and Artist classes.
     *
     * @param xstream The XStream instance to be configured.
     */
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

    /**
     * Saves a Show object to an XML file.
     *
     * @param show The Show object to be saved.
     * @param filename The name of the XML file to save the Show object to.
     */
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
