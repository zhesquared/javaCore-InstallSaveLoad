import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class Loading {

    public static void openZip(String loadingPath) {
        File unzipPath = new File(loadingPath);

        if (unzipPath.isDirectory()) {
            for (File items : unzipPath.listFiles((path, name) -> name.toLowerCase().endsWith(".zip"))) {
                try (ZipInputStream unZip = new ZipInputStream(
                        new FileInputStream(items.toString()))) {
                    ZipEntry entry;
                    String fileName;

                    while ((entry = unZip.getNextEntry()) != null) {
                        fileName = entry.getName();
                        FileOutputStream extract = new FileOutputStream(fileName);
                        for (int c = unZip.read(); c != -1; c = unZip.read()) {
                            extract.write(c);
                        }
                        extract.flush();
                        unZip.closeEntry();
                        extract.close();
                    }
                } catch (Exception exception) {
                    System.out.println(exception.getMessage());
                }
            }
        }
    }
}
