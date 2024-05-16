package controller;

import model.*;

import java.time.LocalDate;

public class SingerController extends ArtistController {
    private Singer singer;
    private static SingerController singerController;

    public SingerController() {
    }

    public static SingerController getSingerController() {
        if (singerController == null) {
            singerController = new SingerController();
        }
        return singerController;
    }

    public Singer getSinger() {
        return singer;
    }

    public void setSinger(Singer singer) {
        this.singer = singer;
    }

    public void createNewAlbum(String albumName) {
        Album newAlbum = new Album(albumName, this.singer.getFullName());
        this.singer.getSingerAlbums().add(newAlbum);
    }

    public String publishNewSong(String audioTitle, Genre audioGenre, String lyrics, String audioLink, String cover, long albumID) {
        Music newMusic = new Music(audioTitle, audioGenre, lyrics, audioLink, cover, this.singer.getFullName());
        for (Album singerAlbums : this.singer.getSingerAlbums()) {
            if (singerAlbums != null) {
                if (singerAlbums.getAlbumID() == albumID) {
                    singerAlbums.getAlbumTracks().add(newMusic);
                    Database.getDatabase().getAllAudios().add(newMusic);
                    return "Song Published Successfully.";
                }
            }
        }
        return "Publishing Song Wasn't Successful.";
    }

    @Override
    public String viewStatistics() {
        String statistics = "Singer's Statistics of Musics: \n";
        int index = 1, secondIndex = 1;
        for (Album albumsArchive : this.singer.getSingerAlbums())
            if (albumsArchive != null) {
                statistics += String.valueOf(index) + ". " + String.valueOf(albumsArchive.getAlbumTitle()) + ": \n";
                for (Music albumsMusics : albumsArchive.getAlbumTracks()) {
                    if (albumsMusics != null) {
                        statistics += "  " + String.valueOf(secondIndex) + ". " + albumsMusics.getAudioTitle() + ": likes: " + String.valueOf(albumsMusics.getNumOfLikes()) + "\n";
                        secondIndex++;
                    }
                }
                index++;
            }
        return statistics;
    }

    @Override
    public double calculateEarnings() {
        double totalIncome = 0;
        for (Album albumsArchive : this.singer.getSingerAlbums())
            if (albumsArchive != null) {
                for (Music albumsMusics : albumsArchive.getAlbumTracks()) {
                    if (albumsMusics != null) {
                        totalIncome += albumsMusics.numOfPlays * 0.4;
                    }
                }
            }
        return totalIncome;
    }

    public String singerSignUp(String userName, String accountPassword, String fullName, String email, String phoneNumber, LocalDate birthDate, String bio) throws Exception {
        for (UserAccount users : Database.getDatabase().getAllUsers()) {
            if (users != null) {
                if (users.getUserName().equals(userName)) {
                    return "this username is already taken.";
                }
            }
        }
                Singer newSinger = new Singer(userName, accountPassword, fullName, email, phoneNumber, birthDate, bio);
                setSinger(newSinger);
                Database.getDatabase().getAllUsers().add(newSinger);
        return "sign up as a Singer completed. please login to continue. ";
    }
}
