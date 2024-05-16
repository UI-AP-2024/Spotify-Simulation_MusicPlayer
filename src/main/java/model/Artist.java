package model;

import java.time.LocalDate;
import java.util.ArrayList;

public class Artist extends UserAccount{
    private double artistIncome;
    private ArrayList<UserAccount> artistFollowers;
    private String artistBio;

    public Artist(String userName, String accountPassword, String fullName, String email, String phoneNumber, LocalDate birthDate, String artistBio) throws Exception {
        super(userName, accountPassword, fullName, email, phoneNumber, birthDate);
        this.artistBio = artistBio;
        this.artistFollowers = new ArrayList<>();
    }

    public double getArtistIncome() {
        return artistIncome;
    }

    public ArrayList<UserAccount> getArtistFollowers() {
        return artistFollowers;
    }

    public String getArtistBio() {
        return artistBio;
    }

    @Override
    public String toString() {
        return "Artist's " + super.toString() + ", Bio: " + this.getArtistBio();
    }
}
