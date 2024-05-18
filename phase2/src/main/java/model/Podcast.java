package model;

final public class Podcast extends Audio {
    private final String podcastCaption;

    public Podcast(String audioTitle, Genre audioGenre, String podcastCaption, String audioLink, String cover, String artistName) {
        super(audioTitle, audioGenre, audioLink, cover, artistName);
        this.podcastCaption = podcastCaption;
    }

    public String getPodcastCaption() {
        return podcastCaption;
    }

    @Override
    public String toString() {
        return super.toString() + ", podcast's caption: " + this.getPodcastCaption();
    }
}
