package beginnerlifetutorial.beginnerlifetutorial.utils;

import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.*;

/**
 * <p>アイテムをわかりやすく簡単に作成するツール。</p>
 * <p>例：</p>
 * <ul>
 *     <li>オンタイムチケットを作りたい時
 *         <ul>
 *             <li>
 *                 <span>
 *                     <p>ItemStack ontimeTicket = ItemCreator</p>
 *                     <p>.start()</p>
 *                     <p>.material(Material.PAPER)</p>
 *                     <p>.count(1)</p>
 *                     <p>.name("&aオンタイムチケット")</p>
 *                     <p>.lore("ログインしていると一定時間ごとにもらえる。")</p>
 *                     <p>.lore("換金、特殊アイテムとの交換等に使える。")</p>
 *                     <p>.unbreakable(true)</p>
 *                     <p>.flags(ItemFlag.HIDE_ENCHANTS)</p>
 *                     <p>.create();</p>
 *                 </span>
 *             </li>
 *         </ul>
 *     </li>
 *     <li>LGW投票チケットを作りたい時
 *         <ul>
 *             <li>
 *                 <span>
 *                     <p>ItemStack voteTicket = ItemCreator</p>
 *                     <p>.start()</p>
 *                     <p>.material(Material.PAPER)</p>
 *                     <p>.count(1)</p>
 *                     <p>.name("&a投票チケット")</p>
 *                     <p>.lore("&5ガチャ券との交換やイベンんとでの使用などさまざまな用途がある。")</p>
 *                     <p>.create();</p>
 *                 </span>
 *             </li>
 *         </ul>
 *     </li>
 * </ul>
 * @author temps1101
 */
public class ItemCreator {
    private Material material;
    private int count;
    private String name;
    private boolean unbreakable = false;
    private List<String> lore = new ArrayList<>();
    private Map<Enchantment, Integer> enchantments = new HashMap<>();
    private Map<Attribute, AttributeModifier> attributeModifiers = new HashMap<>();
    private List<ItemFlag> flags = new ArrayList<>();

    public static ItemCreator start() {
        return new ItemCreator();
    }

    public ItemCreator material(Material material) {
        this.material = material;
        return this;
    }

    public ItemCreator count(int count) {
        this.count = count;
        return this;
    }

    public ItemCreator name(String name) {
        this.name = Chat.f(name, false);
        return this;
    }

    public ItemCreator lore(String lore) {
        this.lore.add(Chat.f(lore, false));
        return this;
    }

    public ItemCreator enchantment(Enchantment enchantment, int level) {
        this.enchantments.put(enchantment, level);
        return this;
    }

    public ItemCreator attributeModifiers(Attribute attribute, AttributeModifier attributeModifier) {
        this.attributeModifiers.put(attribute, attributeModifier);
        return this;
    }

    public ItemCreator unbreakable(boolean unbreakable) {
        this.unbreakable = unbreakable;
        return this;
    }

    public ItemCreator flags(ItemFlag itemFlag) {
        this.flags.add(itemFlag);
        return this;
    }

    public ItemStack create() {
        if (material == null) {
            throw new IllegalArgumentException("[material]項目を設定してください！");
        }
        if (count == 0) {
            material = Material.AIR;
        }
        ItemStack itemStack = new ItemStack(material, count);

        ItemMeta itemMeta = itemStack.getItemMeta();
        if (name != null) {
            itemMeta.setDisplayName(name);
        }
        itemMeta.setUnbreakable(unbreakable);
        itemMeta.setLore(lore);
        enchantments.forEach((enchantment, level) -> itemMeta.addEnchant(enchantment, level, true));
        attributeModifiers.forEach(itemMeta::addAttributeModifier);
        flags.forEach(itemMeta::addItemFlags);

        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }
}