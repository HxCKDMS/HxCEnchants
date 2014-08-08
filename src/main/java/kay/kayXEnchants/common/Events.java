package kay.kayXEnchants.common;

import java.lang.reflect.Method;
import java.util.*;
import java.lang.*;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.*;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;

public class Events implements EventListener {
    public boolean morphExists = false;
    public Method getMorphEntity;
    public Method getEntityAbilities;
    public Events() {
        try {
            getMorphEntity = Class.forName("morph.api.Api").getDeclaredMethod("getMorphEntity", String.class, boolean.class);
            getEntityAbilities = Class.forName("morph.common.ability.AbilityHandler").getDeclaredMethod("getEntityAbilities", Class.class);
            morphExists = true;
        } catch (Exception e) {
            System.out.println("Morph doesn't exist");
        }
    }
    

    @Mod.EventHandler
    @SideOnly(Side.SERVER)
    public void Tick(EnumSet<TickEvent.Type> type, Object... tickData) {
        if (type.contains(TickEvent.Type.PLAYER)) {
            EntityPlayer player = (EntityPlayer) tickData[0];
            boolean Fly = false;
            if (Config.enchFlyEnable){
            	Fly = player.capabilities.isCreativeMode;
                // Check for morph
                if (morphExists) {
                    try {
                        Object e = getMorphEntity.invoke(null, player.getUniqueID(), player.worldObj.isRemote);
                        if (e != null) {
                            // Player is morphed
                            ArrayList abilities = (ArrayList) getEntityAbilities.invoke(null, e.getClass());
                            for (Object ability : abilities) {
                                if (ability.getClass().getName().equals("morph.common.ability.AbilityFly")) {
                                    Fly = true;
                                    break;
                                }
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        System.out.println("Morph's flight is not compatible with the flight enchantment");
                        morphExists = false;
                        getMorphEntity = null;
                        getEntityAbilities = null;
                    }
                }
            }
            /**    Fly    **/
            if (!Fly)
            {
            ItemStack boots = player.inventory.armorItemInSlot(3);
                if (kayXEnchants.containsEnchant(boots, Config.enchFlyID))
                {
                    Fly = true;
                    if (player.worldObj.isRemote && player.capabilities.isFlying) player.worldObj.spawnParticle("smoke", player.posX + Math.random() - 0.5d, player.posY - 1.62d, player.posZ + Math.random() - 0.5d, 0.0d, 0.0d, 0.0d);
                }
            }
            player.capabilities.allowFlying = Fly;
            if (!Fly) player.capabilities.isFlying = false;
       }
    }
    public EnumSet<TickEvent.Type> ticks() {
        return EnumSet.of(TickEvent.Type.PLAYER);
    }
    @SubscribeEvent
    @SideOnly(Side.SERVER)
    public void onPlayerTick(TickEvent.PlayerTickEvent event) {
        if (event.type.equals(DamageSource.fall) && (event.player != null)) {
            EntityPlayer player = event.player;
            if (!player.capabilities.isCreativeMode) {
                ItemStack boots = player.inventory.armorInventory[0];
                if (kayXEnchants.containsEnchant(boots, Config.enchFlyID)) {
                        if (!(boots.isItemDamaged()) && event.player.isSneaking())
                                event.setCanceled(true);
                            if (!event.player.isSneaking())
                                boots.damageItem((int) Math.sqrt(event.hashCode()), player);
                                event.setCanceled(true);
                    if (boots.stackSize <= 0) player.inventory.armorInventory[0] = null;
                    event.setCanceled(true);
                }
            }
        }
    }
    @SideOnly(Side.CLIENT)
    public void onItemTooltip(ItemTooltipEvent event) {
    	if (Config.Tooltip){
       if (event.itemStack.getItem() instanceof ItemArmor && kayXEnchants.containsEnchant(event.itemStack, Config.enchFlyID)) {
           event.toolTip.add(COLOR + "9Double jump to Fly");
           event.toolTip.add(COLOR + "3Boots absorb all fall damage");
       }
       if (event.itemStack.getItem() instanceof ItemArmor && kayXEnchants.containsEnchant(event.itemStack, Config.enchSwiftnessID)) {
           event.toolTip.add(COLOR + "9Makes you run just a little bit faster =D");
           event.toolTip.add(COLOR + "3Each level makes your speed go up 0.1");
       }
       if (event.itemStack.getItem() instanceof ItemArmor && kayXEnchants.containsEnchant(event.itemStack, Config.enchVitalityID)) {
           event.toolTip.add(COLOR + "9Gives you more Max HP");
           event.toolTip.add(COLOR + "3Each Level increases max Health by 5");
           event.toolTip.add(COLOR + "4Not Stackable with TConstruct Heart Canisters");
       }
       if (event.itemStack.getItem() instanceof ItemArmor && kayXEnchants.containsEnchant(event.itemStack, Config.enchBattleHealingID)) {
           event.toolTip.add(COLOR + "2Gives you regen at the cost of Durability");
           event.toolTip.add(COLOR + "aEach level increases Regen Level and Decreases time");
       }
       if (event.itemStack.getItem() instanceof ItemArmor && kayXEnchants.containsEnchant(event.itemStack, Config.enchWitherProtectionID)) {
           event.toolTip.add(COLOR + "1Gives you protection from Wither Effect");
           event.toolTip.add(COLOR + "9But at the cost of 1 Durability per Damage");
       }
       if (event.itemStack.getItem() instanceof ItemArmor && kayXEnchants.containsEnchant(event.itemStack, Config.enchAdrenalineBoostID)) {
           event.toolTip.add(COLOR + "9Gives you a Boost of Speed, Jump Height, And Strength When Attacked");
           event.toolTip.add(COLOR + "9But at the cost of Lots of Durability");
       }}
   }
    public static final String COLOR = String.copyValueOf(Character.toChars(0xA7));
}
