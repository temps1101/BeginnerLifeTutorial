package beginnerlifetutorial.beginnerlifetutorial.listeners;

import beginnerlifetutorial.beginnerlifetutorial.utils.Chat;
import beginnerlifetutorial.beginnerlifetutorial.utils.TutorialConfig;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerFirstJoinListener implements Listener {
    @EventHandler
    public void onPlayerFirstJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        if (!player.hasPlayedBefore()) {
            player.teleport(TutorialConfig.getFirstSpawnLocation());

            Chat.fancySend(player, true, 1, Chat.f("&6Lifeサーバーへようこそ！", false));
            Chat.fancySend(player, true, 2, Chat.f("&6Life鯖を遊ぶ上で気を付けてほしいことが一つあります", false));
            Chat.fancySend(player, true, 1, Chat.f("&6それは「他人に迷惑をかけてはいけない」という事です", false));
            Chat.fancySend(player, true, 2, Chat.f("&ehttps://www.azisaba.net/server-intro/life/#rule", false));
            Chat.fancySend(player, true, 1, Chat.f("&6このリンク先にて様々なルールが載っています", false));
            Chat.fancySend(player, true, 2, Chat.f("&6確認できたら下を押してください！", false));

            TextComponent button = new TextComponent(Chat.f("&a&l[確認した！]", false));
            button.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(Chat.f("&6ここをクリックしてください！", false)).create()));
            button.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/ltutorial"));

            player.spigot().sendMessage(button);
        }
    }
}
