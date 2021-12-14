package beginnerlifetutorial.beginnerlifetutorial.utils;

import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;


public class TutorialConfig {
    // 基本設定
    private static Location firstSpawnLocation;
    private static Location tspLocation;

    // チュートリアルへの扉の座標系
    private static Location resourceDoorLocation;
    private static Location shopMoneyDoorLocation;
    private static Location dungeonDoorLocation;
    private static Location raidDoorLocation;

    // 資源チュートリアルの設定
    private static Location resourceLocation;
    private static Location shopMoneyLocation;
    private static Location dungeonLocation;
    private static Location raidLocation;

    public static void load(FileConfiguration configuration) {
        for (Object value : configuration.getValues(true).values()) {
            if (value == null) {
                return;
            }
        }

        // 基本設定
        firstSpawnLocation = configuration.getLocation("firstSpawnLocation");
        tspLocation = configuration.getLocation("tspLocation");

        // チュートリアルへの扉の座標系
        resourceDoorLocation = configuration.getLocation("resourceDoorLocation");
        shopMoneyDoorLocation = configuration.getLocation("shopMoneyDoorLocation");
        dungeonDoorLocation = configuration.getLocation("dungeonDoorLocation");
        raidDoorLocation = configuration.getLocation("raidDoorLocation");

        // 資源チュートリアルの設定
        resourceLocation = configuration.getLocation("resourceLocation");
        shopMoneyLocation = configuration.getLocation("shopMoneyLocation");
        dungeonLocation = configuration.getLocation("dungeonLocation");
        raidLocation = configuration.getLocation("raidLocation");
    }

    // 基本設定
    public static Location getFirstSpawnLocation() {
        return firstSpawnLocation;
    }

    public static Location getTspLocation() {
        return tspLocation;
    }

    // チュートリアルへの扉の座標系
    public static Location getResourceDoorLocation() {
        return resourceDoorLocation;
    }

    public static Location getShopMoneyDoorLocation() {
        return shopMoneyDoorLocation;
    }

    public static Location getDungeonDoorLocation() {
        return dungeonDoorLocation;
    }

    public static Location getRaidDoorLocation() {
        return raidDoorLocation;
    }

    // 資源チュートリアルの設定
    public static Location getResourceLocation() {
        return resourceLocation;
    }

    public static Location getShopMoneyLocation() {
        return shopMoneyLocation;
    }

    public static Location getDungeonLocation() {
        return dungeonLocation;
    }

    public static Location getRaidLocation() {
        return raidLocation;
    }
}
