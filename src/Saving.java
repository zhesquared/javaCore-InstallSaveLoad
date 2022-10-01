import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Saving {
    public static final DateFormat DATE_FORMAT = new SimpleDateFormat("yy_MM_dd_HH_mm_ss");
    static int countOfSavings = 1;

    public static void saveGame(String savePath, GameProgress gameProgress) {

        try (FileOutputStream gameSaver = new FileOutputStream(
                savePath + "save_" + countOfSavings + "_" + DATE_FORMAT.format(new Date()) + ".dat");
             ObjectOutputStream progress = new ObjectOutputStream(gameSaver)) {
            progress.writeObject(gameProgress);
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
        countOfSavings++;
    }

    public static void zipFiles(String zipPath) {
        File file = new File(zipPath);

        try (ZipOutputStream archiver = new ZipOutputStream(
                new FileOutputStream(zipPath + DATE_FORMAT.format(new Date()) + ".zip"))) {
            if (file.isDirectory()) {
                for (File item : file.listFiles((path, name) -> name.toLowerCase().endsWith(".dat"))) {
                    //фильтр по файлам сохранений
                    String name = item.toString();
                    FileInputStream savesArchive = new FileInputStream(name);
                    ZipEntry entry = new ZipEntry(name);
                    archiver.putNextEntry(entry);
                    byte[] buffer = new byte[savesArchive.available()];
                    savesArchive.read(buffer);
                    archiver.write(buffer);
                    archiver.closeEntry();
                    savesArchive.close();
                }
            }

        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
    }
}
