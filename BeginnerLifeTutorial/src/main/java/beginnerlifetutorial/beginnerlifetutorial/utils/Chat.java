package beginnerlifetutorial.beginnerlifetutorial.utils;

import java.text.MessageFormat;
import org.bukkit.ChatColor;

public class Chat {
    private static final char COLOR_KEY = '&';
    public static String f(String text, boolean prefix, Object... args) {
        String out = prefix ? "&l&7[&9Life&3Tutorial&7]&r: " + text : text;
        return MessageFormat.format(ChatColor.translateAlternateColorCodes(COLOR_KEY, out), args);
    }
}
