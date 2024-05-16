package controller;

import model.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ListenerController extends UserAccountController {
    private Listener listener;
    private static ListenerController listenerController;
    private int playlistCounter = 0;
    private int audioInPlaylistCounter = 0;

    public ListenerController() {
    }

    public static ListenerController getListenerController() {
        if (listenerController == null) {
            listenerController = new ListenerController();
        }
        return listenerController;
    }

    public Listener getListener() {
        return listener;
    }

    public void setListener(Listener listener) {
        this.listener = listener;
    }

    public String showListenerFollowings() {
        String followings = "followings: \n";
        for (Artist followingArtists : this.listener.getFollowings()) {
            if (followingArtists != null) {
                followings += followingArtists.getUserName() + "\n";
            }
        }
        return followings;
    }

    public String submitReport(String artistUserName, String reportDescription) {
        Artist reportedArtist;
        for (UserAccount reported : Database.getDatabase().getAllUsers())
            if (reported != null) {
                if (reported instanceof Artist) {
                    reportedArtist = (Artist) reported;
                    if (reported.getUserName().equals(artistUserName)) {
                        Report newReport = new Report(this.listener, reportedArtist, reportDescription);
                    }
                }
            }
        return "report submitted. Thanks for letting us know!";
    }

    public String showListenerInfo() {
        return this.listener.toString() + "\n" + "Password: " + this.listener.getAccountPassword() + ", BirthDate: " + this.listener.getBirthDate() + "\n" +
                "E-mail: " + this.listener.getEmail() + ", PhoneNumber: " + this.listener.getPhoneNumber();
    }

    public void playAudio(String ID) {
        Audio playedAudio = null;
        for (Audio allAudios : Database.getDatabase().getAllAudios()) {
            if (allAudios != null) {
                if (allAudios.getAudioID().equals(ID)) {
                    playedAudio = allAudios;
                    playedAudio.numOfPlays++;
                    listener.getNumOfListenedAudios().put(playedAudio, playedAudio.getNumOfPlays());
                    listener.getAudiosSuggestion().add(playedAudio);
                    listener.getGenresSuggestion().add(playedAudio.getAudioGenre());
                }
            }
        }
    }

    public void likeAudio(String ID) {
        Audio likedAudio = null;
        for (Audio allAudios : Database.getDatabase().getAllAudios()) {
            if (allAudios != null) {
                if (allAudios.getAudioID().equals(ID)) {
                    likedAudio = allAudios;
                    likedAudio.numOfLikes++;
                    listener.getAudiosSuggestion().add(likedAudio);
                    listener.getGenresSuggestion().add(likedAudio.getAudioGenre());
                }
            }
        }

    }

    public String followArtist(String artistUserName) {
        for (UserAccount artists : Database.getDatabase().getAllUsers()) {
            if (artists != null) {
                if (artists instanceof Artist) {
                    Artist foundArtist = (Artist) artists;
                    if (artists.getUserName().equals(artistUserName)) {
                        this.listener.getFollowings().add(foundArtist);
                        foundArtist.getArtistFollowers().add(this.listener);
                        return "you're now following " + foundArtist.getFullName() + " successfully.";
                    }
                }
            }
        }
        return "";
    }

    public String showLyrics(String ID) {
        for (Audio allAudios : Database.getDatabase().getAllAudios()) {
            if (allAudios != null) {
                if (allAudios instanceof Music) {
                    Music music = (Music) allAudios;
                    if (allAudios.getAudioID().equals(ID)) {
                        return music.getLyrics();
                    }
                } else if (allAudios instanceof Podcast) {
                    Podcast podcast = (Podcast) allAudios;
                    if (allAudios.getAudioID().equals(ID)) {
                        return podcast.getPodcastCaption();
                    }
                }
            }
        }
        return "Audio not found.";
    }

    public void increaseCredit(double addedAmount) {
        this.listener.setListenerCredit(this.listener.getListenerCredit() + addedAmount);
    }

    public String getPremium(PremiumSubscription premiumSubscription) throws Exception {
        if (this.listener.getListenerCredit() >= premiumSubscription.getSubscriptionCost()) {
            int index = -1;
            if (this.listener instanceof PremiumListener) {
                PremiumListener premiumListener = (PremiumListener) this.listener;
                switch (premiumSubscription.getSubscriptionCost()) {
                    case 5: {
                        premiumListener.setSubscriptionRemainedDays(premiumListener.getSubscriptionRemainedDays() + 30);
                        premiumListener.setSubscriptionExpiryDate(premiumListener.getSubscriptionExpiryDate().plusDays(30));
                        break;
                    }
                    case 9: {
                        premiumListener.setSubscriptionRemainedDays(premiumListener.getSubscriptionRemainedDays() + 60);
                        premiumListener.setSubscriptionExpiryDate(premiumListener.getSubscriptionExpiryDate().plusDays(60));
                        break;
                    }
                    case 14: {
                        premiumListener.setSubscriptionRemainedDays(premiumListener.getSubscriptionRemainedDays() + 180);
                        premiumListener.setSubscriptionExpiryDate(premiumListener.getSubscriptionExpiryDate().plusDays(180));
                        break;
                    }
                }
                this.listener.setListenerCredit(this.listener.getListenerCredit() - premiumSubscription.getSubscriptionCost());
                return "your Premium Subscription remained days got updated!";
            } else {
                index = Database.getDatabase().getAllUsers().indexOf(this.listener);
                PremiumListener newPremium = null;
                switch (premiumSubscription.getSubscriptionCost()) {
                    case 5: {
                        newPremium = new PremiumListener(this.listener.getUserName(), this.listener.getAccountPassword(), this.listener.getFullName(),
                                this.listener.getEmail(), this.listener.getPhoneNumber(), this.listener.getBirthDate(), 30);
                        break;
                    }
                    case 9: {
                        newPremium = new PremiumListener(this.listener.getUserName(), this.listener.getAccountPassword(), this.listener.getFullName(),
                                this.listener.getEmail(), this.listener.getPhoneNumber(), this.listener.getBirthDate(), 60);
                        break;
                    }
                    case 14: {
                        newPremium = new PremiumListener(this.listener.getUserName(), this.listener.getAccountPassword(), this.listener.getFullName(),
                                this.listener.getEmail(), this.listener.getPhoneNumber(), this.listener.getBirthDate(), 180);
                        break;
                    }
                }
                Database.getDatabase().getAllUsers().remove(index);
                Database.getDatabase().getAllUsers().add(index, newPremium);
                this.listener = newPremium;
                this.listener.setListenerCredit(this.listener.getListenerCredit() - premiumSubscription.getSubscriptionCost());
                return "your Free account is now premium!";
            }
        }
        throw new NotEnoughCreditException();
    }

    public String createPlaylist(String name) {
        if (this.listener instanceof PremiumListener) {
            Playlist newPlaylist = new Playlist(name, this.listener.getFullName());
            this.listener.getListenerPlaylists().add(newPlaylist);
            return "playlist created successfully.";
        } else {
            if (this.playlistCounter <= 3) {
                Playlist newPlaylist = new Playlist(name, this.listener.getFullName());
                this.listener.getListenerPlaylists().add(newPlaylist);
                this.playlistCounter++;
                return "playlist created successfully.";
            }
            return "you've reached the limit for creating playlist. if you want to create more, get premium subscription.";
        }
    }

    ;

    public String addAudioToPlaylist(String playlistName, String audioID) {
        if (this.listener instanceof PremiumListener) {
            Audio foundAudio = null;
            for (Audio allAudios : Database.getDatabase().getAllAudios()) {
                if (allAudios != null) {
                    if (allAudios.getAudioID().equals(audioID)) {
                        foundAudio = allAudios;
                        break;
                    }
                } else return "Audio not found.";
            }
            for (Playlist listenerPlaylist : this.listener.getListenerPlaylists()) {
                if (listenerPlaylist != null) {
                    if (listenerPlaylist.getPlaylistName().equals(playlistName) && foundAudio!= null) {
                        listenerPlaylist.getPlaylistAudios().add(foundAudio);
                        listener.getAudiosSuggestion().add(foundAudio);
                        listener.getGenresSuggestion().add(foundAudio.getAudioGenre());
                        return "audio added to wanted playlist successfully.";
                    }
                } else return "playlist not found.";
            }
        } else {
            if (this.audioInPlaylistCounter <= 10) {
                Audio foundAudio = null;
                for (Audio allAudios : Database.getDatabase().getAllAudios()) {
                    if (allAudios != null) {
                        if (allAudios.getAudioID().equals(audioID)) {
                            foundAudio = allAudios;
                            break;
                        }
                    } else return "audio not found.";
                }
                for (Playlist listenerPlaylist : this.listener.getListenerPlaylists()) {
                    if (listenerPlaylist != null) {
                        if (listenerPlaylist.getPlaylistName().equals(playlistName) && foundAudio!= null) {
                            listenerPlaylist.getPlaylistAudios().add(foundAudio);
                            listener.getAudiosSuggestion().add(foundAudio);
                            listener.getGenresSuggestion().add(foundAudio.getAudioGenre());
                            this.audioInPlaylistCounter++;
                            return "audio added to wanted playlist successfully.";
                        }
                    } else return "playlist not found.";
                }
            }
            return "you've reached the limit for adding songs to this playlist. if you want to add more, get premium subscription.";
        }
        return "";
    }

    public String showPlaylists() {
        String list = "your Playlists:\n";
        int index = 1;
        for (Playlist playlists : this.listener.getListenerPlaylists()) {
            list += String.valueOf(index) + ". " + playlists.toString() + "\n   audios: ";
            for (Audio audios : playlists.getPlaylistAudios()) {
                list += audios.getAudioTitle() + " ";
            }
            index++;
        }
        return list;
    }

    public String showSpecificPlaylist(String playlistName) {
        String playlistInfo = "";
        for (Playlist playlists : this.listener.getListenerPlaylists()) {
            if (playlists != null) {
                if (playlists.getPlaylistName().equals(playlistName)) {
                    playlistInfo += playlists.toString() + "\n   audios: ";
                    for (Audio audios : playlists.getPlaylistAudios()) {
                        playlistInfo += audios.getAudioTitle() + " ";
                    }
                    return playlistInfo;
                }
            }
        }
        return "playlist not found.";
    }

    public String sortBasedOnPlays() {
        ArrayList<Audio> sortedAudiosBasedOnPlays = Database.getDatabase().getAllAudios();
        playsBubbleSort(sortedAudiosBasedOnPlays);
        String list = "Audios Sorted from Most to Least Popularity Based On Plays: \n";
        int index = 1;
        for (Audio popularAudios : sortedAudiosBasedOnPlays) {
            if (popularAudios != null) {
                list += String.valueOf(index) + ". " + popularAudios.toString() + ", plays: " + String.valueOf(popularAudios.getNumOfPlays()) + "\n";
                index++;
            }
        }
        return list;
    }

    public String sortBasedOnLikesListener() {
        return sortBasedOnLikes();
    }

    public String search(String name) {
        String results = "your search's results: \n";
        int index = 1;
        for (Audio allAudios : Database.getDatabase().getAllAudios()) {
            if (allAudios != null) {
                if (allAudios.getAudioTitle().equals(name)) {
                    results += String.valueOf(index) + ". Audio: " + name + " with ID: " + allAudios.getAudioID() + "\n";
                    index++;
                }
            }
        }
        for (UserAccount allArtists : Database.getDatabase().getAllUsers()) {
            if (allArtists != null) {
                if (allArtists instanceof Artist) {
                    if (allArtists.getFullName().equals(name)) {
                        results += String.valueOf(index) + ". Artist" + name + " with userName: " + allArtists.getUserName() + "\n";
                        index++;
                    }
                }
            }
        }
        return results;
    }

    public String filterArtist(String artistName) {
        String results = " Audios by " + artistName + " : \n";
        int index = 1;
        for (Audio allAudios : Database.getDatabase().getAllAudios()) {
            if (allAudios != null) {
                if (allAudios.getArtistName().equals(artistName)) {
                    results += String.valueOf(index) + ". Audio: " + artistName + " with ID: " + allAudios.getAudioID() + "\n";
                    index++;
                }
            }
        }
        return results;
    }

    public String filterByReleaseDate(LocalDate date) {
        String results = " Audios released in " + date + " : \n";
        int index = 1;
        for (Audio allAudios : Database.getDatabase().getAllAudios()) {
            if (allAudios != null) {
                if (allAudios.getReleaseDate().equals(date)) {
                    results += String.valueOf(index) + ". Audio: " + allAudios.getAudioTitle() + " with ID: " + allAudios.getAudioID() + "\n";
                    index++;
                }
            }
        }
        return results;
    }

    public String filterByGenre(Genre genre) {
        String results = " Audios with this genre : \n";
        int index = 1;
        for (Audio allAudios : Database.getDatabase().getAllAudios()) {
            if (allAudios != null) {
                if (allAudios.getAudioGenre().equals(genre)) {
                    results += String.valueOf(index) + ". Audio: " + allAudios.getAudioTitle() + " with ID: " + allAudios.getAudioID() + "\n";
                    index++;
                }
            }
        }
        return results;
    }

    public void addToFavGenres(Genre fav) {
        this.listener.getFavGenres().add(fav);
        this.listener.getGenresSuggestion().add(fav);
    }

    public String suggestAudios() {
        String suggestions = "10 suggested Audios based on your recent activity:\n";
        int index = 1;
        for (int i = 0; i <= 10; i++) {
            Random random = new Random();
            if (this.listener.getGenresSuggestion().size() == 0) return "Spotify needs more Info to suggest songs to you. please Be more Active. :)";
            int randomIndex = random.nextInt(0, this.listener.getGenresSuggestion().size());
            List<Genre> genresList = new ArrayList<>(this.listener.getGenresSuggestion());
            Genre genre = genresList.get(randomIndex);
            for (Audio allAudios : Database.getDatabase().getAllAudios()) {
                if (allAudios != null) {
                    if (allAudios.getAudioGenre().equals(genre)) {
                        suggestions += String.valueOf(index) + ". " + allAudios.getAudioTitle() + " by " + allAudios.getArtistName() + "\n";
                        index++;
                        break;
                    }
                }
            }
        }
        return suggestions;
    }

    public String listenerSignUp(String userName, String accountPassword, String fullName, String email, String phoneNumber, LocalDate birthDate) throws Exception {
        for (UserAccount users : Database.getDatabase().getAllUsers()) {
            if (users != null) {
                if (users.getUserName().equals(userName)) {
                    return "this username is already taken.";
                }
            }
        }
        Listener newListener = new Listener(userName, accountPassword, fullName, email, phoneNumber, birthDate);
        setListener(newListener);
        Database.getDatabase().getAllUsers().add(newListener);
        return "sign up as a listener completed. please choose four of your favorite genres of music. ";
    }

    public String premiumRemainedDays() {
        if (this.listener instanceof PremiumListener) {
            return "Your Account's Subscription remained days are: " + String.valueOf(((PremiumListener) this.listener).getSubscriptionRemainedDays() - 1) + " days.";
        } else return "Your Account is not premium yet.";
    }
}
