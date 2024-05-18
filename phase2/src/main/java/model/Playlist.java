package model;

import java.util.ArrayList;
import java.util.Iterator;

final public class Playlist implements Iterable {
    private long playlistID;
    private String playlistName;
    private String playlistMakerName;
    private ArrayList<Audio> playlistAudios;
    private int IdCounter = 1;

    public Playlist(String playlistName, String playlistMakerName) {
        this.playlistName = playlistName;
        this.playlistAudios = new ArrayList<>();
        this.playlistID = this.IdCounter;
        this.IdCounter++;
        this.playlistMakerName = playlistMakerName;
    }

    public long getPlaylistID() {
        return playlistID;
    }

    public String getPlaylistName() {
        return playlistName;
    }

    public String getPlaylistMakerName() {
        return playlistMakerName;
    }

    public ArrayList<Audio> getPlaylistAudios() {
        return playlistAudios;
    }

    @Override
    public String toString() {
        return "playlist's name: " + this.getPlaylistName() + ", playlist's creator: " + this.getPlaylistMakerName() + ", playlist's ID: " + this.getPlaylistID();
    }


    @Override
    public Iterator iterator() {
        return new Iterator() {
            private int index = 0;

            @Override
            public boolean hasNext() {
                return index < playlistAudios.size();
            }

            @Override
            public Audio next() {
                Audio audio = playlistAudios.get(index);
                index++;
                return audio;
            }
        };
    }
}
