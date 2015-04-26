package HxCKDMS.HxCEnchants.Handlers;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class HxCEnchantHelper {
    /*******************************/
    /** AdrenalineBoost       [0] **/
    /** AuraDark              [1] **/
    /** AuraDeadly            [2] **/
    /** AuraFiery             [3] **/
    /** AuraThick             [4] **/
    /** AuraToxic             [5] **/
    /** AirStrider            [6] **/
    /** ArmorRegen            [7] **/
    /** ArrowExplosive        [8] **/
    /** ArrowLightning        [9] **/
    /** ArrowSeeking         [10] **/
    /** Bound                [11] **/
    /** BattleHealing        [12] **/
    /** FlameTouch           [13] **/
    /** Fly                  [14] **/
    /** JumpBoost            [15] **/
    /** LeadFooted           [16] **/
    /** LifeSteal            [17] **/
    /** Poison               [18] **/
    /** Repair               [19] **/
    /** Shroud               [20] **/
    /** SoulTear             [21] **/
    /** Swiftness            [22] **/
    /** Stealth              [23] **/
    /** Vampirism            [24] **/
    /** Vitality             [25] **/
    /** WitherProtection     [26] **/
    /** Examine              [27] **/
    /*******************************/

    public static int getEnchantLevel(ItemStack stack, int EnchantNumber){
        int[] HxCEnchants;
        int EnchantLevel;
        try {
            HxCEnchants = stack.getTagCompound().getIntArray("HxCEnchants");
        } catch (Exception ignored) {
            HxCEnchants = new int[]{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
        }
        EnchantLevel = HxCEnchants[EnchantNumber];
        return EnchantLevel;
    }
    public static void setEnchant(ItemStack stack, int EnchantNumber, int Level, int Power){
        NBTTagCompound tag = stack.getTagCompound();
        int[] HxCEnchants;
        try {
            HxCEnchants = tag.getIntArray("HxCEnchants");
        } catch (Exception ignored) {
            HxCEnchants = new int[]{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
        }
        HxCEnchants[EnchantNumber] = Level;
        tag.setIntArray("HxCEnchants", HxCEnchants);
        tag.setInteger("HxCEnchantCharge", (tag.getInteger("HxCEnchantCharge") + Power));
    }
}
