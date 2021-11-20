package beginnerlifetutorial.beginnerlifetutorial.utils;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.List;

public class TutorialConfig {
    private static String tutorialWorldName;
    private static double[] firstSpawnLocation = new double[3];
    private static double[] tspLocation = new double[3];

    // チュートリアルへの扉の座標系
    private static double[] resourceDoorLocation = new double[3];
    private static double[] shopMoneyDoorLocation = new double[3];
    private static double[] dungeonDoorLocation = new double[3];
    private static double[] raidDoorLocation = new double[3];

    public static void load(FileConfiguration configuration) {
        String tutorialWorldName_temp = configuration.getString("tutorialWorldName");
        List<?> firstSpawnLocation_temp = configuration.getList("firstSpawnLocation");
        List<?> tspLocation_temp = configuration.getList("tspLocation");

        // チュートリアルへの扉の座標系
        List<?> resourceDoorLocation_temp = configuration.getList("resourceDoor");
        List<?> shopMoneyDoorLocation_temp = configuration.getList("shopMoneyDoor");
        List<?> dungeonDoorLocation_temp = configuration.getList("dungeonDoor");
        List<?> raidDoorLocation_temp = configuration.getList("raidDoor");

        if (tutorialWorldName_temp != null && firstSpawnLocation_temp != null && tspLocation_temp != null) {
            tutorialWorldName = tutorialWorldName_temp;

            for (int i=0; i<3; i++) {
                firstSpawnLocation[i] = (double) firstSpawnLocation_temp.get(i);
                tspLocation[i] = (double) tspLocation_temp.get(i);

                // チュートリアルへの扉の座標系
                resourceDoorLocation[i] = (double) resourceDoorLocation_temp.get(i);
                shopMoneyDoorLocation[i] = (double) shopMoneyDoorLocation_temp.get(i);
                dungeonDoorLocation[i] = (double) dungeonDoorLocation_temp.get(i);
                raidDoorLocation[i] = (double) raidDoorLocation_temp.get(i);
            }
        }
    }

    public static String getTutorialWorldName() {
        return tutorialWorldName;
    }

    public static Location getFirstSpawnLocation(String worldName) {
        return toLocation(firstSpawnLocation, worldName);
    }

    public static Location getTspLocation(String worldName) {
        return toLocation(tspLocation, worldName);
    }

    public static Location getResourceDoorLocation(String worldName) {
        return toLocation(resourceDoorLocation, worldName);
    }

    public static Location getShopMoneyDoorLocation(String worldName) {
        return toLocation(shopMoneyDoorLocation, worldName);
    }

    public static Location getDungeonDoorLocation(String worldName) {
        return toLocation(dungeonDoorLocation, worldName);
    }

    public static Location getRaidDoorLocation(String worldName) {
        return toLocation(raidDoorLocation, worldName);
    }

    private static Location toLocation(double[] configLocation, String worldName) {
        return new Location(Bukkit.getWorld(worldName), configLocation[0], configLocation[1], configLocation[2]);
    }

}
