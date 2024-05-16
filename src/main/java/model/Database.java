package model;

import java.util.*;

public class Database {
    private static Database database;
    private ArrayList<UserAccount> allUsers;
    private ArrayList<Audio> allAudios;
    private ArrayList<Report> allReports;

    private Database() {
        this.allUsers = new ArrayList<>();
        this.allAudios = new ArrayList<>();
        this.allReports = new ArrayList<>();
    }

    public static Database getDatabase() {
        if (database == null) {
            database = new Database();
        }
        return database;
    }


    public ArrayList<UserAccount> getAllUsers() {
        return allUsers;
    }

    public void setAllUsers(ArrayList<UserAccount> allUsers) {
        this.allUsers = allUsers;
    }

    public ArrayList<Audio> getAllAudios() {
        return allAudios;
    }

    public void setAllAudios(ArrayList<Audio> allAudios) {
        this.allAudios = allAudios;
    }

    public ArrayList<Report> getAllReports() {
        return allReports;
    }

    public void setAllReports(ArrayList<Report> allReports) {
        this.allReports = allReports;
    }
}
