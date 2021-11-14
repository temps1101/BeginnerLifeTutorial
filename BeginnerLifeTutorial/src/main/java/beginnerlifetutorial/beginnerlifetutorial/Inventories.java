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
    private static Inventory diamondAmountMenu;

    public static void initialize() {
        // shopMenuを初期化
        ItemStack separatorBlue = ItemUtil.createItem(BLUE_STAINED_GLASS_PANE, " ", 1);
        ItemStack separatorRed = ItemUtil.createItem(RED_STAINED_GLASS_PANE, " ", 1);
        shopMenu = Bukkit.createInventory(null, 27, "Menu");
        shopMenu.setContents(new ItemStack[]{
                separatorBlue, separatorRed, separatorBlue, separatorRed, ItemUtil.createItem(BEACON, "&a貴重品", 1), separatorRed, separatorBlue, separatorRed, separatorBlue,
                ItemUtil.createItem(STICK, "&a雑貨&ゴミ", 1), ItemUtil.createItem(EMERALD, "&a鉱石", 1), ItemUtil.createItem(WHEAT, "&e農作物+α", 1), ItemUtil.createItem(STONE, "&6建材", 1), ItemUtil.createItem(GLASS, "&6色付きブロック系の建材", 1), ItemUtil.createItem(OAK_LOG, "&b木材", 1), ItemUtil.createItem(ZOMBIE_SPAWN_EGG, "&6スポーンエッグ", 1), ItemUtil.createItem(HONEY_BLOCK, "&6装飾", 1), ItemUtil.createItem(ENCHANTING_TABLE, "&6エンチャント", 1),
                separatorBlue, separatorRed, separatorBlue, separatorRed, separatorBlue, separatorRed, separatorBlue, separatorRed, separatorBlue
        });

        // smgMenuを初期化
        smgMenu = Bukkit.createInventory(null, 9, "§8SELLMMITEM MENU");
        ItemStack shopOpen = ItemUtil.createItem(GREEN_STAINED_GLASS_PANE, "&aSHOPを開く", 1);
        ItemStack shopAttention = ItemUtil.createItem(YELLOW_STAINED_GLASS_PANE, "&eSHOP注意点", 1, "&dSHOPのインベントリに", "&d指定アイテム以外を入れてしまうと", "&d一円にもならずアイテムが消えます", "&d消えたアイテムに関しては&c補填対象外&dです");
        ItemStack shopAvailable = ItemUtil.createItem(BLACK_STAINED_GLASS_PANE, "&8売却可能アイテム一覧をみる", 1, "&d売却可能アイテムは", "&3一万円&f");

        smgMenu.setItem(0, shopOpen);
        smgMenu.setItem(5, shopAttention);
        smgMenu.setItem(3, shopAvailable);

        // diamondAmountMenuを初期化
        diamondAmountMenu = Bukkit.createInventory(null, 54, "§c量を指定してください");
        for (int i=0 ; i<7 ; i++) {
            int index = 19 + i; // 説明変数ってやつ
            int amount = (int) Math.pow(2, i);
            String price = "&f購入: &c¥" + (int)(10000 * Math.pow(2, (i - 1))) + ".0";

            diamondAmountMenu.setItem(index, ItemUtil.createItem(DIAMOND, null, amount, price));
        }
    }

    public static Inventory getShopInventory() {
        return shopMenu;
    }

    public static Inventory getSmgMenu() {
        return smgMenu;
    }

    public static Inventory getDiamondAmountMenu() {
        return diamondAmountMenu;
    }
}
