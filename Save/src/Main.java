import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.Arrays;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Main {

    private final static String mainPath = "C:\\Users\\USER\\IdeaProjects\\JC-Input-Output-Save\\Save\\Games\\savegames\\";

    public static void main(String[] args) {
        GameProgress game1 = new GameProgress(100, 1, 1, 1.3);
        GameProgress game2 = new GameProgress(85, 2, 6, 68.4);
        GameProgress game3 = new GameProgress(200, 4, 25, 245.4);

        String pathSaveGame1 = "game1.dat";
        String pathSaveGame2 = "game2.dat";
        String pathSaveGame3 = "game3.dat";

        saveGame(mainPath + pathSaveGame1, game1);
        saveGame(mainPath + pathSaveGame2, game2);
        saveGame(mainPath + pathSaveGame3, game3);

        zipFile(new String[] {pathSaveGame1, pathSaveGame2, pathSaveGame3}, mainPath + "saves.zip");
        deleteSave(new String[] {pathSaveGame1, pathSaveGame2, pathSaveGame3});
    }

    private static void saveGame(String pathToSave, GameProgress game) {
        try (FileOutputStream fos = new FileOutputStream(pathToSave);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(game);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    private static void zipFile(String[] files, String archiveName) {
        try (ZipOutputStream zout = new ZipOutputStream(new FileOutputStream(archiveName))) {
            for (String file : files) {
                FileInputStream fis = new FileInputStream(mainPath + file);

                ZipEntry entry = new ZipEntry(file);
                zout.putNextEntry(entry);

                byte[] buffer = new byte[fis.available()];

                zout.write(buffer);
                zout.closeEntry();
            }

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
    private static void deleteSave(String[] pathToFile) {
        for (String delFile : pathToFile) {
            File file = new File(mainPath + delFile);
            if (file.delete()) {
                System.out.println("delete temporary");
            }
        }
    }


}