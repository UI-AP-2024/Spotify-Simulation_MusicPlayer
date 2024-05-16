package model;

import java.time.LocalDate;

final public class FreeListener extends Listener {
    private final int songsInPlaylistLimitation = 10;
    private final int playlistLimitation = 3;

    public FreeListener(String userName, String accountPassword, String fullName, String email, String phoneNumber, LocalDate birthDate) throws Exception {
        super(userName, accountPassword, fullName, email, phoneNumber, birthDate);
    }

    public int getSongsInPlaylistLimitation() {
        return songsInPlaylistLimitation;
    }

    public int getPlaylistLimitation() {
        return playlistLimitation;
    }

    @Override
    public String toString() {
        return super.toString() + "subscription type: Free";
    }
}
