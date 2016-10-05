package HxCKDMS.HxCEnchants.api;

/**
 * Created by kyahn on 10/05/2016.
 */
public class EnchantingUtils {
    public static long xpAtLevel(int level) {
        if (level <= 15) return (level * level) + (6 * level);
        else if (level <= 30) return Math.round(2.5 * (level * level) - (40.5 * level) + 360);
        else return Math.round(4.5 * (level * level) - (162.5 * level) + 2220);
    }

    public static long xpFromLevel(int level) {
        if (level <= 15) return (level * 2) + 7;
        else if (level <= 30) return (level * 5) - 38;
        else return (level * 9) - 158;
    }
}
