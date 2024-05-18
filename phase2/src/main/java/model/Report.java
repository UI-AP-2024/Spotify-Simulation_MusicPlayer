package model;

final public class Report {
    private Listener reportingListener;
    private Artist reportedArtist;
    private String reportDescription;

    public Report(Listener reportingListener, Artist reportedArtist, String reportDescription) {
        this.reportingListener = reportingListener;
        this.reportedArtist = reportedArtist;
        this.reportDescription = reportDescription;
        Database.getDatabase().getAllReports().add(this);
    }

    public Listener getReportingListener() {
        return reportingListener;
    }

    public Artist getReportedArtist() {
        return reportedArtist;
    }

    public String getReportDescription() {
        return reportDescription;
    }

    public String toString(){
        return "reported Artist: " + this.reportedArtist.getFullName() + ", reporting Listener: " + this.reportingListener.getUserName()
                + ", report Description: " + this.reportDescription + "\n";
    }
}
