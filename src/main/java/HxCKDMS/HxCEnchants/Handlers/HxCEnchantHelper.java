package HxCKDMS.HxCEnchants.Handlers;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class HxCEnchantHelper {
    /*******************************/
    /** AdrenalineBoost       [1] **/
    /** AuraDark              [2] **/
    /** AuraDeadly            [3] **/
    /** AuraFiery             [4] **/
    /** AuraThick             [5] **/
    /** AuraToxic             [6] **/
    /** AirStrider            [7] **/
    /** ArmorRegen            [8] **/
    /** ArrowExplosive        [9] **/
    /** ArrowLightning       [10] **/
    /** ArrowSeeking         [11] **/
    /** Bound                [12] **/
    /** BattleHealing        [13] **/
    /** FlameTouch           [14] **/
    /** Fly                  [15] **/
    /** JumpBoost            [16] **/
    /** LeadFooted           [17] **/
    /** LifeSteal            [18] **/
    /** Poison               [19] **/
    /** Repair               [20] **/
    /** Shroud               [21] **/
    /** SoulTear             [22] **/
    /** Swiftness            [23] **/
    /** Stealth              [24] **/
    /** Vampirism            [25] **/
    /** Vitality             [26] **/
    /** WitherProtection     [27] **/
    /** Examine              [28] **/
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
