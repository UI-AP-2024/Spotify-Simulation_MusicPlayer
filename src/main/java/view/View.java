package view;

import model.*;
import controller.*;

import java.time.LocalDate;
import java.util.Scanner;

public class View {
    private static View view;

    private View() {
    }

    public static View getView() {
        if (view == null) {
            view = new View();
        }
        return view;
    }

    Scanner sc = new Scanner(System.in);

    public void showMenu() throws Exception {
        System.out.println("Hi! please login or signup to continue");
        String answer = sc.nextLine();
        String[] answers = answer.split("-");
        if (answers[0].equals("Signup")) {
            if (answers[1].equals("L")) {
                System.out.println(ListenerController.getListenerController().listenerSignUp(answers[2], answers[3], answers[4], answers[5], answers[6],
                        LocalDate.of(Integer.parseInt(answers[7]), Integer.parseInt(answers[8]), Integer.parseInt(answers[9]))));
                String answer1 = sc.nextLine();
                String[] answers1 = answer1.split(",");
                for (int i = 0; i < 4; i++) {
                    ListenerController.getListenerController().addToFavGenres(Genre.valueOf(answers1[i]));
                }
                System.out.println("now login.");
            } else if (answers[1].equals("S")) {
                System.out.println(SingerController.getSingerController().singerSignUp(answers[2], answers[3], answers[4], answers[5], answers[6],
                        LocalDate.of(Integer.parseInt(answers[7]), Integer.parseInt(answers[8]), Integer.parseInt(answers[9])), answers[10]));
            } else if (answers[1].equals("P")) {
                System.out.println(PodCasterController.getPodCasterController().podCasterSignUp(answers[2], answers[3], answers[4], answers[5], answers[6],
                        LocalDate.of(Integer.parseInt(answers[7]), Integer.parseInt(answers[8]), Integer.parseInt(answers[9])), answers[10]));
            }
            showMenu();
        } else if (answers[0].equals("Login")) {
            System.out.println(UserAccountController.getUserAccountController().login(answers[1],answers[2]));
            if(UserAccountController.getUserAccountController().login(answers[1],answers[2]).equals("listener logged in.")){
                Listener();
            }
            else if(UserAccountController.getUserAccountController().login(answers[1],answers[2]).equals("admin logged in.")){
                Admin();
            }
            else if(UserAccountController.getUserAccountController().login(answers[1],answers[2]).equals("Singer logged in.")){
                Singer();
            }
            else if(UserAccountController.getUserAccountController().login(answers[1],answers[2]).equals("PodCaster logged in.")){
                PodCaster();
            }
            showMenu();
        } else { System.out.println("invalid command. try again. ");
            showMenu(); }
    }

    public void Listener() throws Exception {
        String[] answers;
        String answer = sc.nextLine();
        answers = answer.split("-");
        if (answers[0].equals("Artists")){
            System.out.println(ListenerController.getListenerController().showAllArtists());
            Listener();
        } else if (answers[0].equals("Artist")) {
            System.out.println(ListenerController.getListenerController().showSpecificArtist(answers[1]));
            Listener();
        } else if(answers[0].equals("Follow")) {
            System.out.println(ListenerController.getListenerController().followArtist(answers[1]));
            Listener();
        } else if (answers[0].equals("Search")) {
            System.out.println(ListenerController.getListenerController().search(answers[1]));
            Listener();
        } else if (answers[0].equals("Sort")) {
            switch (answers[1]){
                case "L" :
                    System.out.println(ListenerController.getListenerController().sortBasedOnLikesListener());
                    Listener();
                case "P" :
                    System.out.println(ListenerController.getListenerController().sortBasedOnPlays());
                    Listener();
            }
        } else if (answers[0].equals("Filter")) {
            switch (answers[1]){
                case "A" :
                    System.out.println(ListenerController.getListenerController().filterArtist(answers[2]));
                    Listener();
                case "G" :
                    System.out.println(ListenerController.getListenerController().filterByGenre(Genre.valueOf(answers[2])));
                    Listener();
                case "D" :
                    System.out.println(ListenerController.getListenerController().filterByReleaseDate(LocalDate.of(Integer.parseInt(answers[2]), Integer.parseInt(answers[3]), Integer.parseInt(answers[4]))));
                    Listener();
            }
        } else if (answers[0].equals("Add")) {
            System.out.println(ListenerController.getListenerController().addAudioToPlaylist(answers[1],answers[2]));
            Listener();
        } else if (answers[0].equals("ShowPlaylists")) {
            System.out.println(ListenerController.getListenerController().showPlaylists());
            Listener();
        } else if (answers[0].equals("SelectPlaylist")) {
            System.out.println(ListenerController.getListenerController().showSpecificPlaylist(answers[1]));
            Listener();
        } else if (answers[0].equals("Play")) {
            ListenerController.getListenerController().playAudio(answers[1]);
            Listener();
        } else if (answers[0].equals("Like")) {
            ListenerController.getListenerController().likeAudio(answers[1]);
            Listener();
        } else if (answers[0].equals("Lyric")) {
            System.out.println(ListenerController.getListenerController().showLyrics(answers[1]));
            Listener();
        } else if (answers[0].equals("NewPlaylist")) {
            System.out.println(ListenerController.getListenerController().createPlaylist(answers[1]));
            Listener();
        } else if (answers[0].equals("Followings")) {
            System.out.println(ListenerController.getListenerController().showListenerFollowings());
            Listener();
        } else if (answers[0].equals("Report")) {
            System.out.println(ListenerController.getListenerController().submitReport(answers[1], answers[2]));
            Listener();
        } else if (answers[0].equals("IncreaseCredit")){
            ListenerController.getListenerController().increaseCredit(Double.parseDouble(answers[1]));
            Listener();
        } else if (answers[0].equals("GetPremium")) {
            System.out.println(ListenerController.getListenerController().getPremium(PremiumSubscription.valueOf(answers[1])));
            Listener();
        }  else if (answers[0].equals("GetSuggestions")) {
            System.out.println(ListenerController.getListenerController().suggestAudios());
            Listener();
        } else if (answers[0].equals("Logout")) {
            System.out.println(ListenerController.getListenerController().logout());
            showMenu();
        } else if (answers[0].equals("AccountInfo")) {
            System.out.println(ListenerController.getListenerController().showListenerInfo());
            Listener();
        } else if (answers[0].equals("RemainedDays")) {
            System.out.println(ListenerController.getListenerController().premiumRemainedDays());
            Listener();
        } else {
            System.out.println("invalid command. try again :)");
            Listener();
        }
    }

    public void Admin() throws Exception {
        String[] answers;
        String answer = sc.nextLine();
        answers = answer.split("-");
        if (answers[0].equals("Statistics")) {
            System.out.println(AdminController.getAdminController().statistics());
            Admin();
        } else if (answers[0].equals("Audios")) {
            System.out.println(AdminController.getAdminController().showAllAudios());
            Admin();
        } else if(answers[0].equals("Audio")) {
            System.out.println(AdminController.getAdminController().showSpecificAudio(answers[1]));
            Admin();
        } else if (answers[0].equals("Artists")) {
            System.out.println(AdminController.getAdminController().showAllArtists());
            Admin();
        } else if (answers[0].equals("Reports")) {
            System.out.println(AdminController.getAdminController().showReports());
            Admin();
        } else if (answers[0].equals("Logout")) {
            System.out.println(AdminController.getAdminController().logout());
            showMenu();
        } else if (answers[0].equals("AccountInfo")) {
            System.out.println(AdminController.getAdminController().showAdminInfo());
            Admin();
        } else {
            System.out.println("invalid command. try again");
            Admin();
        }
    }

    public void Singer() throws Exception {
        String[] answers;
        String answer = sc.nextLine();
        answers = answer.split("-");
        if (answers[0].equals("Followers")) {
            System.out.println(SingerController.getSingerController().showArtistFollowers());
            Singer();
        } else if(answers[0].equals("ViewStatistics")) {
            System.out.println(SingerController.getSingerController().viewStatistics());
            Singer();
        } else if (answers[0].equals("CalculateEarnings")){
            System.out.println(SingerController.getSingerController().calculateEarnings());
            Singer();
        } else if (answers[0].equals("NewAlbum")) {
            SingerController.getSingerController().createNewAlbum(answers[1]);
            Singer();
        } else if (answers[0].equals("Publish") && answers[1].equals("M")) {
            System.out.println(SingerController.getSingerController().publishNewSong(answers[2], Genre.valueOf(answers[3]), answers[4], answers[5], answers[6], Long.parseLong(answers[7])));
            Singer();
        } else if (answers[0].equals("Logout")) {
            System.out.println(SingerController.getSingerController().logout());
            showMenu();
        } else if (answers[0].equals("AccountInfo")) {
            System.out.println(SingerController.getSingerController().showArtistInfo());
            Singer();
        } else {
            System.out.println("invalid command. try again");
            Singer();
        }
    }

    public void PodCaster() throws Exception {
        String[] answers;
        String answer = sc.nextLine();
        answers = answer.split("-");
        if (answers[0].equals("Followers")) {
            System.out.println(PodCasterController.getPodCasterController().showArtistFollowers());
            PodCaster();
        } else if(answers[0].equals("ViewStatistics")) {
            System.out.println(PodCasterController.getPodCasterController().viewStatistics());
            PodCaster();
        } else if (answers[0].equals("CalculateEarnings")){
            System.out.println(PodCasterController.getPodCasterController().calculateEarnings());
            PodCaster();
        } else if (answers[0].equals("Publish") && answers[1].equals("P")) {
            System.out.println(PodCasterController.getPodCasterController().publishNewPodcast(answers[2], Genre.valueOf(answers[3]), answers[4], answers[5], answers[6]));
            PodCaster();
        } else if (answers[0].equals("Logout")) {
            System.out.println(PodCasterController.getPodCasterController().logout());
            showMenu();
        } else if (answers[0].equals("AccountInfo")) {
            System.out.println(PodCasterController.getPodCasterController().showArtistInfo());
            PodCaster();
        }  else {
            System.out.println("invalid command. try again");
            PodCaster();
        }
    }
}
