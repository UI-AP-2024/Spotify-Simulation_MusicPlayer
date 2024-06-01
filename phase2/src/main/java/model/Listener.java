package model;

import java.time.LocalDate;
import java.util.*;

public class Listener extends UserAccount {
    private double listenerCredit = 50;
    private ArrayList<Playlist> listenerPlaylists;
    private Map<Audio, Long> numOfListenedAudios;
    private LocalDate subscriptionExpiryDate;
    private static ArrayList<Genre> favGenres;
    private ArrayList<Artist> followings;
    private Set<Genre> genresSuggestion;
    private Set<Audio> likedAudios;

    public Listener(String userName, String accountPassword, String fullName, String email, String phoneNumber, LocalDate birthDate) throws Exception {
        super(userName, accountPassword, fullName, email, phoneNumber, birthDate);
        favGenres = new ArrayList<>();
        this.followings = new ArrayList<>();
        this.listenerPlaylists = new ArrayList<>();
        this.numOfListenedAudios = new HashMap<>();
        this.genresSuggestion = new HashSet<>();
        this.likedAudios = new HashSet<>();
    }

    public double getListenerCredit() {
        return listenerCredit;
    }

    public ArrayList<Playlist> getListenerPlaylists() {
        return listenerPlaylists;
    }

    public Map<Audio, Long> getNumOfListenedAudios() {
        return numOfListenedAudios;
    }

    public LocalDate getSubscriptionExpiryDate() {
        return subscriptionExpiryDate;
    }

    public ArrayList<Genre> getFavGenres() {
        return favGenres;
    }

    public void setListenerCredit(double listenerCredit) {
        this.listenerCredit = listenerCredit;
    }

    public void setSubscriptionExpiryDate(LocalDate subscriptionExpiryDate) {
        this.subscriptionExpiryDate = subscriptionExpiryDate;
    }

    public ArrayList<Artist> getFollowings() {
        return followings;
    }

    public Set<Genre> getGenresSuggestion() {
        return genresSuggestion;
    }

    public Set<Audio> getLikedAudios() {
        return likedAudios;
    }

    @Override
    public String toString() {
        return super.toString() + ", listener's credit: " + String.valueOf(this.getListenerCredit());
    }
}
