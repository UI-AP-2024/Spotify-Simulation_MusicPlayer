package controller;

import model.*;

import java.time.LocalDate;

public class PodCasterController extends ArtistController {
    private PodCaster podCaster;
    private static PodCasterController podCasterController;

    public PodCasterController(){};

    public static PodCasterController getPodCasterController() {
        if (podCasterController == null) {
            podCasterController = new PodCasterController();
        }
        return podCasterController;
    }

    public PodCaster getPodCaster() {
        return podCaster;
    }

    public void setPodCaster(PodCaster podCaster) {
        this.podCaster = podCaster;
    }

    public String publishNewPodcast(String title, Genre podcastGenre, String podcastCaption, String podcastLink, String cover){
        Podcast newPodcast = new Podcast(title,podcastGenre,podcastCaption,podcastLink,cover,this.podCaster.getFullName());
        this.podCaster.getArtistPodcasts().add(newPodcast);
        Database.getDatabase().getAllAudios().add(newPodcast);
        return "Podcast Published Successfully.";
    }

    @Override
    public String viewStatistics(){
        String statistics = "PodCaster's Statistics of Podcasts: \n";
        int index = 1;
        for (Podcast podcastsArchive : this.podCaster.getArtistPodcasts()){
            if (podcastsArchive != null) {
                statistics += String.valueOf(index) + ". " + podcastsArchive.getAudioTitle() + ": " + String.valueOf(podcastsArchive.getNumOfPlays()) + "\n";
                index++;
            }
        }
        return statistics;
    }

    @Override
    public double calculateEarnings(){
        double totalIncome = 0;
        for (Podcast podcasts: this.podCaster.getArtistPodcasts()) {
            if (podcasts != null) {
                totalIncome += podcasts.numOfPlays * 0.5;
            }
        }
        return totalIncome;
    }

    public String podCasterSignUp(String userName, String accountPassword, String fullName, String email, String phoneNumber, LocalDate birthDate, String bio) throws Exception {
        for (UserAccount users : Database.getDatabase().getAllUsers()) {
            if (users != null) {
                if (users.getUserName().equals(userName)) {
                    return "this username is already taken.";
                }
            }
        }
                PodCaster newPodCaster = new PodCaster(userName, accountPassword, fullName, email, phoneNumber, birthDate, bio);
                setPodCaster(newPodCaster);
                Database.getDatabase().getAllUsers().add(newPodCaster);
        return "sign up as a podCaster completed. please login to continue. ";
    }
}
