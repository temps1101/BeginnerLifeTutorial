package beginnerlifetutorial.beginnerlifetutorial;

import beginnerlifetutorial.beginnerlifetutorial.commands.AdminCommand;
import beginnerlifetutorial.beginnerlifetutorial.commands.NavigatorCommand;
import beginnerlifetutorial.beginnerlifetutorial.commands.TutorialCommand;
import beginnerlifetutorial.beginnerlifetutorial.listeners.*;
import beginnerlifetutorial.beginnerlifetutorial.utils.PlayerStatus;
import beginnerlifetutorial.beginnerlifetutorial.utils.TutorialConfig;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.Map;


public final class BeginnerLifeTutorial extends JavaPlugin implements Listener {
    private static BeginnerLifeTutorial plugin;
    private static Economy economy;
    private static Map<Player, PlayerStatus> playerCache = new HashMap<>();

    @Override
    public void onEnable() {
        plugin = this;
        setupEconomy();

        saveDefaultConfig();
        TutorialConfig.load(getConfig());

        Bukkit.getPluginCommand("ltutorial").setExecutor(new TutorialCommand());
        Bukkit.getPluginCommand("ltutorialAdmin").setExecutor(new AdminCommand(this));
        Bukkit.getPluginCommand("ltutorialNavigator").setExecutor(new NavigatorCommand());
        Bukkit.getPluginCommand("tradeOntimeDummy").setExecutor(new CommandExecutor() {
            @Override
            public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
                sender.sendMessage("dami-tukaunaaho");
                return true;
            }
        });

        Bukkit.getPluginManager().registerEvents(new PlayerFirstJoinListener(), this);
        Bukkit.getPluginManager().registerEvents(new PlayerDoorOpenedListener(), this);
        Bukkit.getPluginManager().registerEvents(new ResourceTutorialWorldListener(), this);
        Bukkit.getPluginManager().registerEvents(new TutorialProcessor(), this);
        Bukkit.getPluginManager().registerEvents(new OverrideCommands(), this);
    }

    @Override
    public void onDisable() {
    }

    private void setupEconomy() {
        RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
        economy = rsp.getProvider();
    }

    public static Economy getEconomy() {
        return economy;
    }

    public static BeginnerLifeTutorial getPlugin() {
        return plugin;
    }

    public static Map<Player, PlayerStatus> getPlayerCache() {
        return playerCache;
    }
}
