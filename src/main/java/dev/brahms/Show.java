package dev.brahms;

import dev.brahms.Exception.InvalidDateException;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.TreeMap;

/**
 *  Represents a SHOW, which is a type of
 *  paid or free event involving several artists.
 */
public class Show  extends Evenement implements Searchable{

    /**
     * This is the price about the Show
     */
    private double price;

    /**
     *  List of artists
     */
    private ArrayList<Artist> artists;

    /**
     * Create a Show
     * @param texte title of the Show
     * @param dateEvent date and time of the Show
     * @param price price of the Show
     * @param artists list of artists invited to the Show
     */
    public Show(String texte, LocalDateTime dateEvent, double price, ArrayList<Artist> artists) {
        super(texte, dateEvent);
        this.price = price;
        this.artists = artists;
    }

    /**
     * Create a Show without artists by default
     * @param texte title of the Show
     * @param dateEvent date and time of the Show
     * @param price price of the Show
     */
    public Show(String texte, String dateEvent, double price) {
        super(texte, LocalDateTime.parse(dateEvent));

        if(LocalDateTime.parse(dateEvent).getDayOfWeek().equals(DayOfWeek.SUNDAY)) {
            throw new InvalidDateException("The show can be created on Sunday");
        }

        this.price = price;
        this.artists = new ArrayList<>();
    }

    /**
     * Create a Show free by default
     * @param texte title of the Show
     * @param dateEvent date and time of the Show
     */
    public Show(String texte, String dateEvent) {
        this(texte, dateEvent, 0d);
    }

    /**
     * Returns the price of the Show
     * @return price of the Show
     */
    public double getPrice() {
        return price;
    }

    /**
     * Updates the price of the Show
     * @param price new price of the Show
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * Returns the list of artists of the Show
     * @return list of artists of the Show
     */
    public ArrayList<Artist> getArtists() {
        return artists;
    }

    /**
     * Update the list of artists of the Show
     * @param artists new list artists of the Show
     */
    public void setArtists(ArrayList<Artist> artists) {
        this.artists = artists;
    }

    /**
     * Returns a literal representation of the Show
     * @return representation of the Show
     */
    @Override
    public String toString() {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy' à 'HH:mm");
        TreeMap<Style, ArrayList<Artist>> map = new TreeMap<>();

        Iterator<Artist> itera = artists.iterator();

        while(itera.hasNext()) {
            Artist artist = itera.next();
            Style style = artist.getStyle();

            if(!map.containsKey(style)) {
                map.put(style, new ArrayList<Artist>());
            }

            map.get(style).add(artist);
        }

        StringBuilder strBuild = new StringBuilder();

        Iterator<Style> iteraMap = map.keySet().iterator();

        while (iteraMap.hasNext()) {
            Style style = iteraMap.next();
            int nbr = map.get(style).size();

            strBuild.append("\t- ");
            strBuild.append(style);
            strBuild.append(" ( ")
                    .append(nbr)
                    .append(nbr > 1 ? " artistes" : " artiste")
                    .append(" ) \r\n )");
        }

        return getTexte() + " - "
                + getDateEvent().format(formatter) + " - "
                + (price == 0d ? "Gratuit": String.format("%.2f", price) + " €") + "\r\n"
                + strBuild.toString();
    }

    /**
     * Returns the list of artists of a given style
     *
     * @param s the musical style of sought-after artists
     * @return the list of artists of a given style
     */
    @Override
    public List findBy(String s) {

        List<Artist> listArtists = new ArrayList<>();
        Iterator<Artist> iterator = artists.iterator();

        while (iterator.hasNext()) {
            Artist artist = iterator.next();

            if( artist.getStyle()
                    .toString()
                    .equalsIgnoreCase(s)) {
                listArtists.add(artist);
            }
        }
        return listArtists;
    }

}
