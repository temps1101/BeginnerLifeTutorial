package beginnerlifetutorial.beginnerlifetutorial;

import beginnerlifetutorial.beginnerlifetutorial.utils.ItemUtil;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import static org.bukkit.Material.*;

public class Inventories {
    private static Inventory shopMenu;
    private static Inventory smgMenu;

    public static void initialize() {
        // shopMenuを初期化
        ItemStack separatorBlue = ItemUtil.createItem(BLUE_STAINED_GLASS_PANE, " ");
        ItemStack separatorRed = ItemUtil.createItem(RED_STAINED_GLASS_PANE, " ");
        shopMenu = Bukkit.createInventory(null, 27, "Menu");
        shopMenu.setContents(new ItemStack[]{
                separatorBlue, separatorRed, separatorBlue, separatorRed, ItemUtil.createItem(BEACON, "&a貴重品"), separatorRed, separatorBlue, separatorRed, separatorBlue,
                ItemUtil.createItem(STICK, "&a雑貨&ゴミ"), ItemUtil.createItem(EMERALD, "&a鉱石"), ItemUtil.createItem(WHEAT, "&e農作物+α"), ItemUtil.createItem(STONE, "&6建材"), ItemUtil.createItem(GLASS, "&6色付きブロック系の建材"), ItemUtil.createItem(OAK_LOG, "&b木材"), ItemUtil.createItem(ZOMBIE_SPAWN_EGG, "&6スポーンエッグ"), ItemUtil.createItem(HONEY_BLOCK, "&6装飾"), ItemUtil.createItem(ENCHANTING_TABLE, "&6エンチャント"),
                separatorBlue, separatorRed, separatorBlue, separatorRed, separatorBlue, separatorRed, separatorBlue, separatorRed, separatorBlue
        });

        // smgMenuを初期化
        smgMenu = Bukkit.createInventory(null, 9, "§8SELLMMITEM MENU");
        ItemStack shopOpen = ItemUtil.createItem(GREEN_STAINED_GLASS_PANE, "&aSHOPを開く");
        ItemStack shopAttention = ItemUtil.createItem(YELLOW_STAINED_GLASS_PANE, "&eSHOP注意点", "&dSHOPのインベントリに", "&d指定アイテム以外を入れてしまうと", "&d一円にもならずアイテムが消えます", "&d消えたアイテムに関しては&c補填対象外&dです");
        ItemStack shopAvailable = ItemUtil.createItem(BLACK_STAINED_GLASS_PANE, "&8売却可能アイテム一覧をみる", "&d売却可能アイテムは", "&3一万円&f");

        smgMenu.setItem(0, shopOpen);
        smgMenu.setItem(5, shopAttention);
        smgMenu.setItem(3, shopAvailable);
    }

    public static Inventory getShopInventory() {
        return shopMenu;
    }

    public static Inventory getSmgMenu() {
        return smgMenu;
    }
}
