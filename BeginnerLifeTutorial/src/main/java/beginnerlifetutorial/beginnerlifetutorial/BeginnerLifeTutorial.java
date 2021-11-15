package beginnerlifetutorial.beginnerlifetutorial;

import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;


public final class BeginnerLifeTutorial extends JavaPlugin implements Listener {
    @Override
    public void onEnable() {
        saveDefaultConfig();
    }

    @Override
    public void onDisable() {
    }
}
