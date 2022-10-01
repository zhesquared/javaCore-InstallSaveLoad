public class Main {
    static final String INSTALL_PATH = "D:\\Games";
    static final String SAVING_PATH = INSTALL_PATH + "\\savegames\\";

    public static void main(String[] args) {
        String[] rootDirectories = {"src", "res", "savegames", "temp"};
        String[] srcDirectories = {"main", "test"};
        String[] resDirectories = {"drawables", "vectors", "icons"};
        String[] srcFiles = {"Main.java", "Utils.java"};

        String srcPath = INSTALL_PATH + "\\src";
        String resPath = INSTALL_PATH + "\\res";

        Installation installer = new Installation();

        installer.directoryMaker(INSTALL_PATH, rootDirectories);
        installer.directoryMaker(srcPath, srcDirectories);
        installer.directoryMaker(resPath, resDirectories);

        installer.fileMaker(srcPath, srcFiles);

        installer.logWriter(INSTALL_PATH);

        GameProgress progress1 = new GameProgress(87, 14, 80, 125.2);
        GameProgress progress2 = new GameProgress(15, 156, 99, 85.0);
        GameProgress progress3 = new GameProgress(75, 22, 10, 1256.7);

        Saving.saveGame(SAVING_PATH, progress1);
        Saving.saveGame(SAVING_PATH, progress2);
        Saving.saveGame(SAVING_PATH, progress3);

        Saving.zipFiles(SAVING_PATH);

        Saving.removeSaves(SAVING_PATH);

        Loading.openZip(SAVING_PATH);
    }
}