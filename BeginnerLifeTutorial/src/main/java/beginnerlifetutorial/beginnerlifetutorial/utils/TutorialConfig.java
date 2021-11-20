package beginnerlifetutorial.beginnerlifetutorial.utils;

import org.bukkit.configuration.file.FileConfiguration;

import java.util.List;

public class TutorialConfig {
    private static String tutorialWorldName;
    private static double[] firstSpawnLocation = new double[3];
    private static double[] tspLocation = new double[3];

    public static void load(FileConfiguration configuration) {
        String tutorialWorldName_temp = configuration.getString("tutorialWorldName");
        List<?> firstSpawnLocation_temp = configuration.getList("firstSpawnLocation");
        List<?> tspLocation_temp = configuration.getList("tspLocation");

        if (tutorialWorldName_temp != null && firstSpawnLocation_temp != null && tspLocation_temp != null) {
            tutorialWorldName = tutorialWorldName_temp;

            for (int i=0; i<3; i++) {
                firstSpawnLocation[i] = (double) firstSpawnLocation_temp.get(i);
                tspLocation[i] = (double) tspLocation_temp.get(i);
            }
        }
    }

    public static String getTutorialWorldName() {
        return tutorialWorldName;
    }

    public static double[] getFirstSpawnLocation() {
        return firstSpawnLocation;
    }

    public static double[] getTspLocation() {
        return tspLocation;
    }
}
