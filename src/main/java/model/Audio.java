package model;

import java.time.LocalDate;

abstract public class Audio implements Comparable<Audio>{
    private final String audioID;
    private final String audioTitle;
    private final String artistName;
    public long numOfPlays;
    public long numOfLikes;
    private final LocalDate releaseDate;
    private final Genre audioGenre;
    private final String audioLink;
    private final String cover;
    private static int IdCounter = 1;


    public Audio(String audioTitle, Genre audioGenre, String audioLink, String cover, String artistName) {
        this.audioTitle = audioTitle;
        this.audioGenre = audioGenre;
        this.audioLink = audioLink;
        this.artistName = artistName;
        this.cover = cover;
        this.releaseDate = LocalDate.now();
        this.audioID = String.valueOf(this.audioTitle.length()) + String.format("%03d", this.IdCounter);
        this.IdCounter++;
        this.numOfLikes = 0;
        this.numOfPlays = 0;
    }

    public String getAudioID() {
        return audioID;
    }

    public String getAudioTitle() {
        return audioTitle;
    }

    public String getArtistName() {
        return artistName;
    }

    public long getNumOfPlays() {
        return numOfPlays;
    }

    public long getNumOfLikes() {
        return numOfLikes;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public Genre getAudioGenre() {
        return audioGenre;
    }

    public String getAudioLink() {
        return audioLink;
    }

    public String getCover() {
        return cover;
    }

    @Override
    public String toString() {
        return "Title: " + this.getAudioTitle() + ", ID: " + this.getAudioID() + ", Artist's Name: " + this.getArtistName();
    }

    @Override
    public int compareTo(Audio o) {
        if (this.getAudioTitle().toLowerCase().compareTo(o.getAudioTitle().toLowerCase()) == 0) {
            if (this.getNumOfLikes() > o.getNumOfLikes()) return 1;
            else if (this.getNumOfLikes() < o.getNumOfLikes()) return -1;
            else {
                if (this instanceof Music && o instanceof Podcast) return 1;
                else if (this instanceof Podcast && o instanceof Music) return -1;
                else{
                    if (this.getNumOfPlays() > o.getNumOfPlays()) return 1;
                    else if (this.getNumOfPlays() < o.getNumOfPlays()) return -1;
                    else return 0;
                }
            }
        } else return this.getAudioTitle().toLowerCase().compareTo(o.getAudioTitle().toLowerCase());
    }
}
