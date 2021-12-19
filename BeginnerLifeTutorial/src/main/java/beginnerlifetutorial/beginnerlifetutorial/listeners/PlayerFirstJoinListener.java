package beginnerlifetutorial.beginnerlifetutorial.listeners;

import beginnerlifetutorial.beginnerlifetutorial.BeginnerLifeTutorial;
import beginnerlifetutorial.beginnerlifetutorial.utils.Chat;
import beginnerlifetutorial.beginnerlifetutorial.utils.TutorialConfig;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import static org.bukkit.Sound.BLOCK_SCAFFOLDING_BREAK;

public class PlayerFirstJoinListener implements Listener {
    @EventHandler
    public void onPlayerFirstJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        if (!player.hasPlayedBefore()) {
            player.teleport(TutorialConfig.getFirstSpawnLocation());

            Bukkit.getScheduler().runTaskLater(BeginnerLifeTutorial.getPlugin(), () -> {
                player.sendMessage(Chat.f("&8==============================", false));
                player.sendMessage(Chat.f("&6Lifeサーバーへようこそ！", false));
                player.sendMessage(Chat.f("&8==============================", false));
                player.playSound(player.getLocation(), BLOCK_SCAFFOLDING_BREAK, 1, 1);
            }, 20*4); // 4s遅延

            Bukkit.getScheduler().runTaskLater(BeginnerLifeTutorial.getPlugin(), () -> {
                player.sendMessage(Chat.f("&8==============================", false));
                player.sendMessage(Chat.f("&6Life鯖を遊ぶ上で気を付けてほしいことが一つあります", false));
                player.playSound(player.getLocation(), BLOCK_SCAFFOLDING_BREAK, 1, 1);
            }, 20*8); // 4s遅延

            Bukkit.getScheduler().runTaskLater(BeginnerLifeTutorial.getPlugin(), () -> {
                player.sendMessage(Chat.f("&6それは「他人に迷惑をかけてはいけない」という事です", false));
                player.sendMessage(Chat.f("&8==============================", false));
                player.playSound(player.getLocation(), BLOCK_SCAFFOLDING_BREAK, 1, 1);
            }, 20*10); //2s遅延

            Bukkit.getScheduler().runTaskLater(BeginnerLifeTutorial.getPlugin(), () -> {
                player.sendMessage(Chat.f("&8==============================", false));
                player.sendMessage(Chat.f("&ehttps://www.azisaba.net/server-intro/life/#rule", false));
                player.sendMessage(Chat.f("&6このリンク先にて様々なルールが載っています", false));
                player.playSound(player.getLocation(), BLOCK_SCAFFOLDING_BREAK, 1, 1);
            }, 20*14); // 4s遅延

            Bukkit.getScheduler().runTaskLater(BeginnerLifeTutorial.getPlugin(), () -> {
                player.sendMessage(Chat.f("&6確認できたらn分後に下に出てくる&a&l[確認した！]&r&6のボタンを押してください！", false));
                player.sendMessage(Chat.f("&8==============================", false));
                player.playSound(player.getLocation(), BLOCK_SCAFFOLDING_BREAK, 1, 1);
            }, 20*17); // 3s遅延

            Bukkit.getScheduler().runTaskLater(BeginnerLifeTutorial.getPlugin(), () -> {
                TextComponent button = new TextComponent(Chat.f("&a&l[確認した！]", false));
                button.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(Chat.f("&6ここをクリックしてください！", false)).create()));
                button.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/ltutorial"));
                player.playSound(player.getLocation(), BLOCK_SCAFFOLDING_BREAK, 1, 1);

                player.spigot().sendMessage(button);
            }, 20*137); // 120(仮)s遅延
        }
    }
}
