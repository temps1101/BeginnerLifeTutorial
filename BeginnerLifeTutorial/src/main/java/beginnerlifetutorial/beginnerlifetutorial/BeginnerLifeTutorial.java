package beginnerlifetutorial.beginnerlifetutorial;

import beginnerlifetutorial.beginnerlifetutorial.commands.AdminCommand;
import beginnerlifetutorial.beginnerlifetutorial.commands.TutorialCommand;
import beginnerlifetutorial.beginnerlifetutorial.listeners.PlayerDoorOpenedListener;
import beginnerlifetutorial.beginnerlifetutorial.listeners.PlayerFirstJoinListener;
import beginnerlifetutorial.beginnerlifetutorial.utils.TutorialConfig;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;


public final class BeginnerLifeTutorial extends JavaPlugin implements Listener {
    private static BeginnerLifeTutorial plugin;

    @Override
    public void onEnable() {
        saveDefaultConfig();

        plugin = this;
        TutorialConfig.load(getConfig());

        Bukkit.getPluginCommand("ltutorial").setExecutor(new TutorialCommand());
        Bukkit.getPluginCommand("ltutorialAdmin").setExecutor(new AdminCommand(this));

        Bukkit.getPluginManager().registerEvents(new PlayerFirstJoinListener(), this);
        Bukkit.getPluginManager().registerEvents(new PlayerDoorOpenedListener(), this);
    }

    @Override
    public void onDisable() {
    }

    public static BeginnerLifeTutorial getPlugin() {
        return plugin;
    }
}
