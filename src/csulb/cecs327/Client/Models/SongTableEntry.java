package csulb.cecs327.Client.Models;

/**
 * This class is responsible for parsing in the data that is displayed in the table
 */
public class SongTableEntry {
    private String song = "";
    private String artist = "";
    private String album = "";
    private String genre = "";

    public SongTableEntry(String song, String artist, String album, String genre) {
        this.song = song;
        this.artist = artist;
        this.album = album;
        this.genre = genre;
    }
    
    public String getSong() {
        return song;
    }
    
    public String getArtist() {
        return artist;
    }
    
    public String getAlbum() {
        return album;
    }
    
    public String getGenre() {
        return genre;
    }
    
    public void setSong(String song) {
        this.song = song;
    }
    
    public void setArtist(String artist) {
        this.artist = artist;
    }
    
    public void setAlbum(String album) {
        this.album = album;
    }
    
    public void setGenre(String genre) {
        this.genre = genre;
    }
    
    @Override
    public String toString() {
        return "SongTableEntry{" +
                "song='" + song + '\'' +
                ", artist='" + artist + '\'' +
                ", album='" + album + '\'' +
                ", genre='" + genre + '\'' +
                '}';
    }
}
