import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Saving {
    public static final DateFormat DATE_FORMAT = new SimpleDateFormat("dd_MM_yy_HH_mm_ss");
    static int countOfSavings = 1;

    public static void saveGame(String savePath, GameProgress gameProgress) {

        try (FileOutputStream gameSaver = new FileOutputStream(
                savePath + "save_" + " " + countOfSavings + DATE_FORMAT.format(new Date()) + ".dat");
             ObjectOutputStream progress = new ObjectOutputStream(gameSaver)) {
            progress.writeObject(gameProgress);
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
        countOfSavings++;
    }
}
