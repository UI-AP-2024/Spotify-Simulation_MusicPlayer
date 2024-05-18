package model;

import java.util.ArrayList;

final public class Album {
    private final long albumID;
    private final String albumTitle;
    private String singerName;
    private final ArrayList<Music> albumTracks;
    private static int IdCounter = 1;

    public Album(String albumTitle, String singerName) {
        this.albumTitle = albumTitle;
        this.singerName = singerName;
        this.albumTracks = new ArrayList<>();
        this.albumID = Long.parseLong(String.format("%02d", this.IdCounter));
        this.IdCounter++;
    }

    public long getAlbumID() {
        return albumID;
    }

    public String getAlbumTitle() {
        return albumTitle;
    }

    public String getSingerName() {
        return singerName;
    }

    public ArrayList<Music> getAlbumTracks() {
        return albumTracks;
    }

    @Override
    public String toString() {
        return "Album title: " + this.albumTitle + ", album's singer name: " + this.singerName + ", album's ID: " + String.valueOf(this.albumID);
    }
}
