package beginnerlifetutorial.beginnerlifetutorial.listeners;

import beginnerlifetutorial.beginnerlifetutorial.BeginnerLifeTutorial;
import beginnerlifetutorial.beginnerlifetutorial.TutorialType;
import beginnerlifetutorial.beginnerlifetutorial.utils.Chat;
import beginnerlifetutorial.beginnerlifetutorial.utils.ItemUtil;
import beginnerlifetutorial.beginnerlifetutorial.utils.PlayerStatus;
import beginnerlifetutorial.beginnerlifetutorial.utils.TutorialConfig;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import static beginnerlifetutorial.beginnerlifetutorial.BeginnerLifeTutorial.getPlugin;
import static beginnerlifetutorial.beginnerlifetutorial.TutorialType.*;
import static org.bukkit.Sound.ENTITY_PLAYER_LEVELUP;

public class PlayerDoorOpenedListener implements Listener {
    private Material DOOR = Material.DARK_OAK_DOOR;
    @EventHandler
    public void onDoorOpened(PlayerInteractEvent event) {
        if (event.getAction() == Action.RIGHT_CLICK_BLOCK) {
            Block clickedBlock = event.getClickedBlock();
            if (clickedBlock.getType() == DOOR) {
                Player player = event.getPlayer();

                if (isDoorSameCoordinate(clickedBlock.getLocation(), TutorialConfig.getResourceDoorLocation()) ) {
                    startProcess(player, event, RESOURCE);

                    // チュートリアル開始
                    Chat.fancySend(player, true, 4, Chat.f("&6ここは資源ワールドです。", false));
                    Chat.fancySend(player, true, 4, Chat.f("&6まずは今入手した桑で目の前の小麦を収穫してみましょう。", false));

                    BeginnerLifeTutorial.getPlayerCache().get(player).setBeforeTutorialInventory(player.getInventory());
                    player.getInventory().clear();
                    player.getInventory().setItemInMainHand(ItemUtil.createItem(Material.DIAMOND_HOE, "&6チュートリアル用クワ", 1, "&fチュートリアル用に作られた桑", "&f持っていたアイテムはチュートリアル終了後に返却されます。"));

                    Chat.fancySend(player, true, 4, Chat.f("&6桑で小麦を右クリックすると小麦を回収できます。", false));
                } else if (isDoorSameCoordinate(clickedBlock.getLocation(), TutorialConfig.getShopMoneyDoorLocation()) ) {
                    startProcess(player, event, SHOPMONEY);

                } else if (isDoorSameCoordinate(clickedBlock.getLocation(), TutorialConfig.getDungeonDoorLocation()) ) {
                    startProcess(player, event, DUNGEON);
                } else if (isDoorSameCoordinate(clickedBlock.getLocation(), TutorialConfig.getRaidDoorLocation()) ) {
                    startProcess(player, event, RAID);
                }
            }
        }
    }

    private boolean isDoorSameCoordinate(Location location1, Location location2) {
        return location1.getWorld().getName().equals(location2.getWorld().getName()) &&
               location1.getBlockX() == location2.getBlockX() &&
               location1.getBlockZ() == location2.getBlockZ() &&
               Math.abs(location1.getY() - location2.getY()) <= 1;
    }

    private void startProcess(Player player, PlayerInteractEvent event, TutorialType tutorialType) {
        // プレイヤーを資源チュートリアル中に変更する
        if (!BeginnerLifeTutorial.getPlayerCache().containsKey(player)) {
            BeginnerLifeTutorial.getPlayerCache().put(player, new PlayerStatus());
        }
        BeginnerLifeTutorial.getPlayerCache().get(player).setTutorialType(RESOURCE);

        // ドアを閉める
        long ticks = 20 * (long) 3;
        Bukkit.getScheduler().runTaskLaterAsynchronously(getPlugin(), () -> event.setCancelled(true), ticks);

        String japaneseTitle;
        String englishTitle;
        // テレポート
        switch (tutorialType) {
            case RESOURCE:
                player.teleport(TutorialConfig.getResourceLocation());
                japaneseTitle = "&6資源チュートリアル";
                englishTitle = "&6RESOURCE TUTORIAL";
                break;

            case SHOPMONEY:
                player.teleport(TutorialConfig.getShopMoneyLocation());
                japaneseTitle = "&6Shop、Moneyチュートリアル";
                englishTitle = "&6Shop＆Money TUTORIAL";
                break;

            case DUNGEON:
                player.teleport(TutorialConfig.getDungeonLocation());
                japaneseTitle = "&6ダンジョンチュートリアル";
                englishTitle = "&6DUNGEON TUTORIAL";
                break;

            case RAID:
                player.teleport(TutorialConfig.getRaidLocation());
                japaneseTitle = "&6レイドチュートリアル";
                englishTitle = "&6RAID TUTORIAL";
                break;

            default:
                japaneseTitle = "";
                englishTitle = "";
        }
        player.playSound(player.getLocation(), ENTITY_PLAYER_LEVELUP, 1, 1);
        player.sendTitle(Chat.f(japaneseTitle, false), Chat.f(englishTitle, false), 10, 70, 20);
    }
}
