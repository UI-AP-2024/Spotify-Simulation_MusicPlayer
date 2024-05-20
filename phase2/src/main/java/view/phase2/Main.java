package view.phase2;

import controller.AdminController;
import controller.SingerController;
import controller.UserAccountController;
import javafx.animation.FadeTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.Admin;
import model.Genre;
import model.Listener;
import model.Singer;

import java.nio.file.Paths;
import java.time.LocalDate;

public class Main extends Application {
    public static void main(String[] args) throws Exception {
        //System.out.println("Hi there :)");
        AdminController adminController = new AdminController();
        adminController.setAdmin(Admin.getAdmin("admin01", "adminPass", "Admin", "admin01@gmail.com","09121112233",LocalDate.of(2005,7,10)));
        //
        Singer theNeighborhood = new Singer("TheNBHD", "nbhd123", "The Neighborhood", "thenbhd@gmail.com", "09123456789", LocalDate.of(2000, 9, 12), "American alternative rock band");
        SingerController s1 = new SingerController();
        s1.setSinger(theNeighborhood);
        s1.createNewAlbum("Wiped Out!");
        s1.publishNewSong("The Beach", Genre.POP, "I'm sick, and I'm tired too\n" + "I can admit, I am not fireproof\n" + "I feel it " +
                "burning me\n" + "I feel it burning you\n" + "I hope I don't murder me\n" + "I hope I don't burden you\n" + "If I do, If I do", "https://ts15.tarafdari.com/contents/user847852/content-sound/the_beach_-_the_neighbourhood.mp3",  "src/main/resources/view/phase2/Images/nbhd1.png", 1);
        s1.publishNewSong("Sweater Weather", Genre.POP, "And all I am is a man\n" + "I want the world in my hands\n" + "I hate the beach\n" +
                "But I stand in California with my toes in the sand\n" + "Use the sleeves of my sweater\n" + "Let's have an adventure\n" + "Head in the clouds but my gravity centered", "https://dl.musicdel.ir/Music/1400/09/the_neighbourhood_sweater_weather%20128.mp3", "src/main/resources/view/phase2/Images/nbhd2.png", 1);
        //
        Singer katatonia = new Singer("katatonia", "kataPass", "Katatonia", "katatoniaaa@gmail.com", "09001234567", LocalDate.of(2005,7,10), "Swedish heavy metal band");
        SingerController s2 = new SingerController();
        s2.setSinger(katatonia);
        s2.createNewAlbum("Discouraged Ones");
        s2.publishNewSong("Gone", Genre.ROCK, "A dead start in my head and the day's been laid\n" + "The things I really want, always torn from my heart\n" + "You never saw the way how I wanted you to stay\n" +
                "And now you're gone, I'm on my own\n" + "When I was thinking this was something permanent\n" + "You were already thinking of going away", "https://ts14.tarafdari.com/contents/user744012/content-sound/katatonia_-_gone_live.mp3", "src/main/resources/view/phase2/Images/katatonia.png", 2);
        s2.publishNewSong("DeadHouse", Genre.INTERVIEW, "Somehow I never leave\n" + "This dead house\n" + "Somehow I don't mind being gone\n" +
                "And if you think you've seen me\n" + "I have to prove you\n" + "That you're wrong", "https://dl.psnmusic.com/Top%20Single/Katatonia%20-%20Deadhouse%20%28128%29.mp3", "src/main/resources/view/phase2/Images/katatonia.png", 2);
        s2.createNewAlbum("The Great distance");
        s2.publishNewSong("Day", Genre.COUNTRY, "Grey park look the same\n" + "And the days are pale\n" +
                "I never thought it would rain this way\n" + "I should be knowing that, it used to be me", "https://ts15.tarafdari.com/contents/user797152/content-sound/112_-_day_live_-_katatonia_320.mp3", "src/main/resources/view/phase2/Images/katatonia2.png", 3);
        //
        Singer anathema = new Singer("ana121thema", "pass121", "Anathema", "testMail@gmail.com", "09987654321", LocalDate.of(1998,10,5), "Rock band from liverpool");
        SingerController s3 = new SingerController();
        s3.setSinger(anathema);
        s3.createNewAlbum("Judgement");
        s3.publishNewSong("Deep", Genre.JAZZ, "A fettered heart, waking\n" +
                "Tainted youth, fading\n" +
                "Leave it all behind\n" +
                "Delirious again\n" +
                "Mesmerize my senses\n" +
                "Souls entwine one more time", "https://ts2.tarafdari.com/contents/user6984/content-sound/anathema_-_deep_mp3.pm_.mp3", "src/main/resources/view/phase2/Images/anathema.png", 4);
        s3.publishNewSong("One Last Goodbye", Genre.HIPHOP, "Somehow I knew you would leave me this way\n" +
                "And somehow I knew you could never, never stay\n" +
                "And in the early morning light after a silent peaceful night\n" + "You took my heart away and I grieve", "https://ts1.tarafdari.com/contents/user85869/content-sound/04_-_one_last_goodbye.mp3", "src/main/resources/view/phase2/Images/anathema.png", 4);
        //
        Singer opeth = new Singer("opethUser", "pass123", "Opeth", "email@gmail.com", "09178965434", LocalDate.of(2001, 4, 17), "Worldwide Tour soon");
        SingerController s4 = new SingerController();
        s4.setSinger(opeth);
        s4.createNewAlbum("Still Life");
        s4.publishNewSong("Hope Leaves", Genre.SOCIETY, "In the corner beside my window\n" +
                "Hangs a lonely photograph\n" + "There is no reason\n" + "I'd never notice a memory that could hold me back\n" +
                "There is a wound that's always bleeding\n" + "There is a road I'm always walking\n" + "And I know you'll never return to this place", "https://ts5.tarafdari.com/contents/user664487/content-sound/opeth_-_hope_leaves.mp3", "src/main/resources/view/phase2/Images/opeth.png", 5);
        s4.publishNewSong("Harvest", Genre.TRUE_CRIME, "Into the orchard, I walk peering way past the gate\n" +
                "Wilted scenes for us who couldn't wait\n" + "Drained by the coldest caress\n" + "Stalking shadows ahead\n" +
                "Halo of death\n" + "All I see is departure\n" + "Mourner's lament, but it's me who's the martyr", "https://ts2.tarafdari.com/contents/user6984/content-sound/opeth_-_harvest.mp3", "src/main/resources/view/phase2/Images/opeth.png", 5);
        //
        Singer theWeekend = new Singer("oscar", "passOscar", "The Weekend", "oscar@gmail.com", "09087654759", LocalDate.of(2012,3,10), "Singer And SongWriter");
        SingerController s5 = new SingerController();
        s5.setSinger(theWeekend);
        s5.createNewAlbum("Star");
        s5.publishNewSong("Save Your Tears", Genre.COUNTRY, "Take me back 'cause I wanna stay\n" +
                "Save your tears for another\n" + "Save your tears for another day\n" +
                "Save your tears for another day", "https://ts5.tarafdari.com/contents/user628719/content-sound/the_weeknd_-_save_your_tears.mp3", "src/main/resources/view/phase2/Images/syt.png", 6);
        //
        Singer arctic = new Singer("arctic", "aPass", "Arctic Monkeys", "arcMonkeys@gmail.com", "09012398736", LocalDate.of(2000,3,4), "Also known as: Death Ramps");
        SingerController s6 = new SingerController();
        s6.setSinger(arctic);
        s6.createNewAlbum("AM");
        s6.publishNewSong("505", Genre.JAZZ, "But I crumble completely when you cry\n" +
                "It seems like once again you've had to greet me with goodbye\n" +
                "I'm always just about to go and spoil a surprise\n" + "Take my hands off of your eyes too soon", "https://ts2.tarafdari.com/contents/user601585/content-sound/arctic_monkeys_-_505.mp3", "src/main/resources/view/phase2/Images/am.png", 7);
        s6.publishNewSong("Arabella", Genre.ROCK, "Arabella's got a '70s head\n" +
                "\n" +
                "But she's a modern lover\n" +
                "\n" +
                "It's an exploration, she's made of outer space", "https://ts9.tarafdari.com/contents/user739677/content-sound/120313.mp3", "src/main/resources/view/phase2/Images/am.png", 7);
//        view.phase2.View.getView().showMenu();
        launch();
    }

    @Override
    public void start(Stage stage) throws Exception {
        HomePageNotLoggedInController.ctrlStage = stage;
        LoginPageController.ctrlStage = stage;
        SignUpPageController.ctrlStage = stage;
        GenrePickingController.ctrlStage = stage;
        ArtistProfilePageController.ctrlStage = stage;
        AllArtistsPagesController.ctrlStage = stage;
        HomePageLoggedInController.ctrlStage = stage;
        SearchController.ctrlStage = stage;
        AllAudiosPageController.ctrlStage = stage;
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("homePageNotLoggedIn.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 700, 450);
        FXMLLoader fxmlLoader1 = new FXMLLoader(Main.class.getResource("start.fxml"));
        Scene scene1 = new Scene(fxmlLoader1.load(), 700, 450);
        stage.setScene(scene1);
        stage.setTitle("Spotify");
        ScaleTransition startTransition = new ScaleTransition(Duration.seconds(0.9), scene1.getRoot());
        startTransition.setToX(1.6);
        startTransition.setToY(1.6);

        startTransition.setOnFinished(actionEvent -> {
            stage.setScene(scene);
        });
        startTransition.play();
        stage.show();
    }
}
