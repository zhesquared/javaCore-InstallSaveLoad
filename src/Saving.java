import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

public class Saving {

    public static void saveGame(String savePath, GameProgress gameProgress) {

        try (FileOutputStream gameSaver = new FileOutputStream(savePath);
             ObjectOutputStream progress = new ObjectOutputStream(gameSaver)) {
            progress.writeObject(gameProgress);
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
    }
}
