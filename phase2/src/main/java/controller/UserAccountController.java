package controller;

import model.*;

import java.util.ArrayList;

public class UserAccountController {
    //creating this class to override needed methods.
    
    private static UserAccountController userAccountController;

    public UserAccountController() {
    }

    public static UserAccountController getUserAccountController() {
        if (userAccountController == null) {
            userAccountController = new UserAccountController();
        }
        return userAccountController;
    }

    public String showAllArtists(){ //used for both admin and listener
        int index = 1;
        String list = "All existing artists:\n";
        for (UserAccount artists : Database.getDatabase().getAllUsers()) {
            if (artists != null) {
                if (artists instanceof Artist) {
                    list += String.valueOf(index) + ". " + artists.toString() + "\n";
                }
            }
        }
        return list;
    }

    public String showSpecificArtist(String userName){
        for (UserAccount artists : Database.getDatabase().getAllUsers()) {
            if (artists != null) {
                if (artists instanceof Artist) {
                    if (artists.getUserName().equals(userName)){
                        return artists.toString();
                    }
                }
            }
        }
        return "artist not found.";
    }

    public static void likesBubbleSort(ArrayList<Audio> audios){
        int numOfAudios = audios.size();
        for (int i = 0; i < numOfAudios - 1; i++) {
            for (int j = 0; j < numOfAudios - 1 - i; j++) {
                if (audios.get(j).numOfLikes < audios.get(j + 1).numOfLikes){
                    Audio temp = audios.get(j);
                    audios.set(j, audios.get(j + 1));
                    audios.set(j + 1, temp);
                }
            }
        }
    }

    public static void playsBubbleSort(ArrayList<Audio> audios){
        int numOfAudios = audios.size();
        for (int i = 0; i < numOfAudios - 1; i++) {
            for (int j = 0; j < numOfAudios - 1 - i; j++) {
                if (audios.get(j).numOfPlays < audios.get(j + 1).numOfPlays){
                    Audio temp = audios.get(j);
                    audios.set(j, audios.get(j + 1));
                    audios.set(j + 1, temp);
                }
            }
        }
    }

    public static ArrayList<Audio> sortBasedOnLikes() { //for both listener and admin
        ArrayList<Audio> sortedAudiosBasedOnLikes = Database.getDatabase().getAllAudios();
        likesBubbleSort(sortedAudiosBasedOnLikes);
//        String list = "Audios Sorted from Most to the Least Popularity Based On Likes: \n";
//        int index = 1;
//        for (Audio popularAudios : sortedAudiosBasedOnLikes) {
//            if (popularAudios != null) {
//                list += String.valueOf(index) + ". " + popularAudios.toString() + ", Likes: " + String.valueOf(popularAudios.getNumOfLikes()) + "\n";
//                index++;
//            }
//        }
        return sortedAudiosBasedOnLikes;
    }

    public String login(String username, String password) throws Exception {
        for (UserAccount users : Database.getDatabase().getAllUsers()){
            if (users != null) {
               if (users.getUserName().equals(username)){
                   if (users.getAccountPassword().equals(password)) {
                       if (users instanceof Listener) {
                           Listener listener = (Listener) users;
                           ListenerController.getListenerController().setListener(listener);
                           return "listener logged in.";
                       } else if (users instanceof Admin) {
                           AdminController.getAdminController().setAdmin((Admin) users);
                           return "admin logged in.";
                       } else if (users instanceof Singer) {
                           SingerController.getSingerController().setSinger((Singer) users);
                           return "Singer logged in.";
                       } else if (users instanceof PodCaster) {
                           PodCasterController.getPodCasterController().setPodCaster((PodCaster) users);
                           return "PodCaster logged in.";
                       }
                   } else throw new WrongPasswordException();
               }
            }
        }
        throw new UserNotFoundException();
    }

    public String logout() {
        UserAccount loggedInUser = null;
        if (ListenerController.getListenerController().getListener() != null) {
            loggedInUser = ListenerController.getListenerController().getListener();
        } else if (AdminController.getAdminController().getAdmin() != null) {
            loggedInUser = AdminController.getAdminController().getAdmin();
        } else if (SingerController.getSingerController().getSinger() != null) {
            loggedInUser = SingerController.getSingerController().getSinger();
        } else if (PodCasterController.getPodCasterController().getPodCaster() != null) {
            loggedInUser = PodCasterController.getPodCasterController().getPodCaster();
        }

        if (loggedInUser != null) {
            if (loggedInUser instanceof Listener) {
                ListenerController.getListenerController().setListener(null);
                return "Listener logged out.";
            } else if (loggedInUser instanceof Admin) {
                AdminController.getAdminController().setAdmin(null);
                return "Admin logged out.";
            } else if (loggedInUser instanceof Singer) {
                SingerController.getSingerController().setSinger(null);
                return "Singer logged out.";
            } else {
                PodCasterController.getPodCasterController().setPodCaster(null);
                return "PodCaster logged out.";
            }
        }

        return "No user is currently logged in.";
    }

}
