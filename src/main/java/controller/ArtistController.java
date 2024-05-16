package controller;

import model.*;

public abstract class ArtistController extends UserAccountController {
    private Artist artist;

    public ArtistController() {
    }

    public Artist getArtist() {
        return artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }

    public String showArtistFollowers() {
        String list = "Followers of Artist with following Info: \n" + artist.toString() + " : \n";
        int index = 1;
        for (UserAccount followers : this.artist.getArtistFollowers()) {
            if (followers != null) {
                list += String.valueOf(index) + ". " + followers.getUserName() + "\n";
                index++;
            }
        }
        return list;
    }

    public String showArtistInfo(){
        return this.artist.toString() + "\n" + "Password: " + this.artist.getAccountPassword() + ", BirthDate: " + this.artist.getBirthDate() + "\n" +
                "E-mail: " + this.artist.getEmail() + ", PhoneNumber: " + this.artist.getPhoneNumber();
    }

    abstract public String viewStatistics();
    abstract public double calculateEarnings();
}