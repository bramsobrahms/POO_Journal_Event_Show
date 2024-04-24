package dev.brahms;

public class Artist {

    private String pseudo;
    private String firstname;
    private String lastname;
    private Style style;

    public Artist(String pseudo, String firstname, String lastname, Style style) {
        super();
        this.pseudo = pseudo;
        this.firstname = firstname;
        this.lastname = lastname;
        this.style = style;
    }

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public Style getStyle() {
        return style;
    }

    public void setStyle(Style style) {
        this.style = style;
    }

    @Override
    public String toString() {
        return "Artist{" +
                "pseudo='" + pseudo + '\'' +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", style=" + style +
                '}';
    }

}
