package dev.brahms;

import dev.brahms.Exception.InvalidDateException;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.TreeMap;

public class Show  extends Evenement implements Searchable{

    private double price;
    private ArrayList<Artist> artists;

    public Show(String texte, LocalDateTime dateEvent, double price, ArrayList<Artist> artists) {
        super(texte, dateEvent);
        this.price = price;
        this.artists = artists;
    }

    public Show(String texte, String dateEvent, double price) {
        super(texte, LocalDateTime.parse(dateEvent));

        if(LocalDateTime.parse(dateEvent).getDayOfWeek().equals(DayOfWeek.SUNDAY)) {
            throw new InvalidDateException("The show can be created on Sunday");
        }

        this.price = price;
        this.artists = new ArrayList<>();
    }

    public Show(String texte, String dateEvent) {
        this(texte, dateEvent, 0d);
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public ArrayList<Artist> getArtists() {
        return artists;
    }

    public void setArtists(ArrayList<Artist> artists) {
        this.artists = artists;
    }

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
