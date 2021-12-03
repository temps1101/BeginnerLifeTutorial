package beginnerlifetutorial.beginnerlifetutorial;

import beginnerlifetutorial.beginnerlifetutorial.commands.AdminCommand;
import beginnerlifetutorial.beginnerlifetutorial.commands.TutorialCommand;
import beginnerlifetutorial.beginnerlifetutorial.listeners.PlayerDoorOpenedListener;
import beginnerlifetutorial.beginnerlifetutorial.listeners.PlayerFirstJoinListener;
import beginnerlifetutorial.beginnerlifetutorial.listeners.TutorialStartListener;
import beginnerlifetutorial.beginnerlifetutorial.utils.PlayerStatus;
import beginnerlifetutorial.beginnerlifetutorial.utils.TutorialConfig;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.Map;


public final class BeginnerLifeTutorial extends JavaPlugin implements Listener {
    private static BeginnerLifeTutorial plugin;
    private static Map<Player, PlayerStatus> playerCache = new HashMap<>();

    @Override
    public void onEnable() {
        plugin = this;

        saveDefaultConfig();
        TutorialConfig.load(getConfig());

        Bukkit.getPluginCommand("ltutorial").setExecutor(new TutorialCommand());
        Bukkit.getPluginCommand("ltutorialAdmin").setExecutor(new AdminCommand(this));

        Bukkit.getPluginManager().registerEvents(new PlayerFirstJoinListener(), this);
        Bukkit.getPluginManager().registerEvents(new PlayerDoorOpenedListener(), this);
        Bukkit.getPluginManager().registerEvents(new TutorialStartListener(), this);
    }

    @Override
    public void onDisable() {
    }

    public static BeginnerLifeTutorial getPlugin() {
        return plugin;
    }

    public static Map<Player, PlayerStatus> getPlayerCache() {
        return playerCache;
    }
}
