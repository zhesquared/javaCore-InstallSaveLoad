import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Installation {
    static StringBuilder tempRecords = new StringBuilder();

    public void directoryMaker(String directoryPath, String[] directories) {
        for (String directory : directories) {
            File installDirectory = new File(directoryPath, directory);

            if (installDirectory.mkdir()) {
                tempRecords.append(directoryPath)
                        .append(" ")
                        .append(directory)
                        .append(" is created")
                        .append("\n");
            } else {
                tempRecords.append(directoryPath)
                        .append(" ")
                        .append(directory)
                        .append(" is not created")
                        .append("\n");
            }
        }
    }

    public void fileMaker(String directoryPath, String[] files) {
        for (String file : files) {
            File installFile = new File(directoryPath, file);

            try {
                if (installFile.createNewFile()) {
                    tempRecords.append(file)
                            .append(" is created in ")
                            .append(directoryPath)
                            .append("\n");
                } else {
                    tempRecords.append(file)
                            .append(" is not created in ")
                            .append(directoryPath)
                            .append("\n");
                }
            } catch (IOException exception) {
                System.out.println(exception.getMessage());
            }
        }
    }

    public void logWriter(String directoryPath) {
        String log = tempRecords.toString();
        String path = directoryPath + "\\temp.txt";

        try (FileWriter writer = new FileWriter(path)) {
            writer.write(log);
            writer.flush();
        } catch (IOException exception) {
            System.out.println(exception.getMessage());
        }
    }
}
