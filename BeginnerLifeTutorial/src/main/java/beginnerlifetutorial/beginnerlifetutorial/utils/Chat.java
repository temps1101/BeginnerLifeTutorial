// inspired by https://github.com/AzisabaNetwork/LeonGunWar
// TODO config.ymlからプレフィックスとカラーコードキーの設定を取る

package beginnerlifetutorial.beginnerlifetutorial.utils;

import org.bukkit.ChatColor;
import org.bukkit.Sound;
import java.text.MessageFormat;

import static org.bukkit.Sound.BLOCK_SCAFFOLDING_BREAK;

public class Chat {
    private static final char COLOR_KEY = '&';
    private static final Sound FANCY_SEND_SOUND = BLOCK_SCAFFOLDING_BREAK;

    /**
     * カラーコード付き文字列を変換、プリフィックスを追加、フォーマットの三つの機能を備えたメソッド。カラーコードの記号はデフォルトで&
     * @param text カラーコード変換をさせたい文字列
     * @param prefix 先頭に装飾された[LifeTutorial]というプレフィックスをのせるか否か
     * @param args "{0} {1}"みたいなのに参照させたい変数たち
     * @return コンパイル(バイナリー化とかじゃないからね)された文字列
     */
    public static String f(String text, boolean prefix, Object... args) {
        String out = prefix ? "&l&7[&9Life&3Tutorial&7]&r: " + text : text;
        return MessageFormat.format(ChatColor.translateAlternateColorCodes(COLOR_KEY, out), args);
    }
}
