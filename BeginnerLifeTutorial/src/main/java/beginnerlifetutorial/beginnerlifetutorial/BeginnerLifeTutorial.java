package beginnerlifetutorial.beginnerlifetutorial;

import beginnerlifetutorial.beginnerlifetutorial.commands.TutorialAdminCommand;
import beginnerlifetutorial.beginnerlifetutorial.commands.TutorialCommand;
import beginnerlifetutorial.beginnerlifetutorial.commands.TutorialStepCommand;
import beginnerlifetutorial.beginnerlifetutorial.listeners.ResourceListener;
import beginnerlifetutorial.beginnerlifetutorial.listeners.ShopMoneyListener;
import beginnerlifetutorial.beginnerlifetutorial.listeners.TutorialHomeListener;
import beginnerlifetutorial.beginnerlifetutorial.listeners.TutorialProcessor;
import beginnerlifetutorial.beginnerlifetutorial.utils.PlayerStatus;
import java.util.HashMap;
import java.util.Map;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;


public final class BeginnerLifeTutorial extends JavaPlugin {
    private static Map<Player, PlayerStatus> tutorialPlayerCache = new HashMap<>();
    private static BeginnerLifeTutorial plugin;
    @Override
    public void onEnable() {
        plugin = this;
        saveDefaultConfig();

        Bukkit.getPluginManager().registerEvents(new TutorialProcessor(getConfig()), this);
        Bukkit.getPluginManager().registerEvents(new TutorialHomeListener(getConfig()), this);
        Bukkit.getPluginManager().registerEvents(new ResourceListener(), this);
        Bukkit.getPluginManager().registerEvents(new ShopMoneyListener(), this);


        Bukkit.getPluginCommand("tutorialAdmin").setExecutor(new TutorialAdminCommand());
        Bukkit.getPluginCommand("tutorial").setExecutor(new TutorialCommand(getConfig()));
        Bukkit.getPluginCommand("stepTutorial").setExecutor(new TutorialStepCommand());
    }

    @Override
    public void onDisable() {

    }

    public static Map<Player, PlayerStatus> getTutorialPlayerCache() {
        return tutorialPlayerCache;
    }

    public static PlayerStatus getPlayerStatus(Player player) {
        return tutorialPlayerCache.get(player);
    }

    public static void setPlayerStatus(Player player, PlayerStatus playerStatus) {
        tutorialPlayerCache.put(player, playerStatus);
    }

    public static void changePlayerStatus(Player player, PlayerStatus playerStatus) {
        tutorialPlayerCache.replace(player, playerStatus);
    }

    public static void removePlayerStatus(Player player) {
        tutorialPlayerCache.remove(player);
    }

    public static BeginnerLifeTutorial getPlugin() {
        return plugin;
    }
}
