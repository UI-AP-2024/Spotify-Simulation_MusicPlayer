package model;

final public class Music extends Audio {
    private final String lyrics;

    public Music(String audioTitle, Genre audioGenre, String lyrics, String audioLink, String cover, String artistName) {
        super(audioTitle, audioGenre, audioLink, cover, artistName);
        this.lyrics = lyrics;
    }

    public String getLyrics() {
        return lyrics;
    }

    @Override
    public String toString() {
        return super.toString() + ", music's lyrics: " + this.getLyrics();
    }
}
