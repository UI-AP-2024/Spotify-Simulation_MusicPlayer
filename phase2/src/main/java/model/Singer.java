package model;

import java.time.LocalDate;
import java.util.ArrayList;

final public class Singer extends Artist{
    private ArrayList<Album> singerAlbums;

    public Singer(String userName, String accountPassword, String fullName, String email, String phoneNumber, LocalDate birthDate, String artistBio) throws Exception {
        super(userName, accountPassword, fullName, email, phoneNumber, birthDate, artistBio);
        this.singerAlbums = new ArrayList<>();
    }

    public ArrayList<Album> getSingerAlbums() {
        return singerAlbums;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
