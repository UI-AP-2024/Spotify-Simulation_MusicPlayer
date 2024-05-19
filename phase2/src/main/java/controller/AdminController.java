package controller;

import model.Admin;
import model.Audio;
import model.Database;
import model.Report;

import java.util.ArrayList;

public class AdminController extends UserAccountController {
    private Admin admin;
    private static AdminController adminController;

    public AdminController() {
    }

    public static AdminController getAdminController() {
        if (adminController == null) {
            adminController = new AdminController();
        }
        return adminController;
    }

    public Admin getAdmin() {
        return admin;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }

    public ArrayList statistics() {
        return sortBasedOnLikes();
    }

    public String showAdminInfo() {
        return this.admin.toString() + "\n" + "Password: " + this.admin.getAccountPassword() + ", BirthDate: " + this.admin.getBirthDate() + "\n" +
                "E-mail: " + this.admin.getEmail() + ", PhoneNumber: " + this.admin.getPhoneNumber();
    }

    public String showReports() {
        String reportsList = "ALl Reports Info: \n";
        int i = 1;
        for (Report allReports : Database.getDatabase().getAllReports()) {
            if (allReports != null) {
                reportsList += String.valueOf(i) + ". " + allReports.toString() + "\n";
                i++;
            }
        }
        return reportsList;
    }

    public String showAllAudios() {
        int index = 1;
        String list = "All existing audios:\n";
        for (Audio audios : Database.getDatabase().getAllAudios()) {
            if (audios != null) {
                list += String.valueOf(index) + ". " + audios.toString() + "\n";
            }
        }
        return list;
    }

    public String showSpecificAudio(String audioID) {
        for (Audio audios : Database.getDatabase().getAllAudios()) {
            if (audios != null) {
                if (audios.getAudioID().equals(audioID)) {
                    return audios.toString();
                }
            }
        }
        return "audio not found.";
    }
}
