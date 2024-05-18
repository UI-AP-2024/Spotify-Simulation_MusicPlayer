package model;

import java.time.LocalDate;
import java.util.ArrayList;

final public class PodCaster extends Artist {
    private ArrayList<Podcast> artistPodcasts;

    public PodCaster(String userName, String accountPassword, String fullName, String email, String phoneNumber, LocalDate birthDate, String artistBio) throws Exception {
        super(userName, accountPassword, fullName, email, phoneNumber, birthDate, artistBio);
        this.artistPodcasts = new ArrayList<>();
    }

    public ArrayList<Podcast> getArtistPodcasts() {
        return artistPodcasts;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
