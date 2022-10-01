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
        File savePath = new File(zipPath);

        try (ZipOutputStream archiver = new ZipOutputStream(
                new FileOutputStream(zipPath + DATE_FORMAT.format(new Date()) + "_archive.zip"))) {
            if (savePath.isDirectory()) {
                for (File items : savePath.listFiles((path, name) -> name.toLowerCase().endsWith(".dat"))) { //фильтр по файлам сохранений
                    String name = items.toString();
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

    public static void removeSaves(String savePath) {
        File removePath = new File(savePath);

        if (removePath.isDirectory()) {
            for (File items : removePath.listFiles((path, name) -> name.toLowerCase().endsWith(".dat"))) {
                items.delete();
            }
        }
    }
}
