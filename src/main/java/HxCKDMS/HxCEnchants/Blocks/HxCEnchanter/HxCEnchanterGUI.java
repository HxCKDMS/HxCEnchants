package HxCKDMS.HxCEnchants.Blocks.HxCEnchanter;

import HxCKDMS.HxCCore.api.Utils.AABBUtils;
import HxCKDMS.HxCEnchants.Configurations.Configurations;
import HxCKDMS.HxCEnchants.HxCEnchants;
import HxCKDMS.HxCEnchants.lib.Reference;
import HxCKDMS.HxCEnchants.network.PacketHxCEnchanterSync;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeHooks;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;

import java.awt.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Objects;

import static HxCKDMS.HxCEnchants.Configurations.Configurations.EnableCoordinatesInGUIs;


@SuppressWarnings("unchecked")
public class HxCEnchanterGUI extends GuiContainer {
    private int px, py, pz, xpneeded, xp, row = 0, shelvesNeeded = 0;
    private HxCEnchanterContainer HxCContainer;
    private List<Enchantment> displayedEnchants = HxCEnchants.KnownRegisteredEnchants;
    private LinkedHashMap<Enchantment, Integer> newEnchLevels = new LinkedHashMap<>(), currentEnchantsOnItem = new LinkedHashMap<>();
    private World world;
    private int xSize = 230, ySize = 220;
    private int xStart = 0, yStart = 0;
    private int xm =  0, xM = 0, ym = 0, yM = 0;

    public HxCEnchanterGUI(HxCEnchanterContainer container, EntityPlayer player) {
        super(container);
        this.px = container.enchanterTile.xCoord;
        this.py = container.enchanterTile.yCoord;
        this.pz = container.enchanterTile.zCoord;
        this.HxCContainer = container;
        this.xp = player.experienceLevel;
        this.world = player.worldObj;
    }

    private static int getShelvesAABB(World world, AxisAlignedBB box) {
        int books = 0;
        for(int x = (int)box.minX; (double)x <= box.maxX; ++x)
            for(int y = (int)box.minY; (double)y <= box.maxY; ++y)
                for(int z = (int)box.minZ; (double)z <= box.maxZ; ++z)
                        books += ForgeHooks.getEnchantPower(world, x, y, z);
        return books;
    }

    @Override
    public void initGui() {
        HxCEnchants.KnownRegisteredEnchants.forEach(ench -> newEnchLevels.put(ench, 0));
        xStart = (width - xSize) / 2;
        yStart = (height - ySize) / 2;
        xm = xStart + Integer.parseInt(StatCollector.translateToLocal("EnchanterGUI.xMin"));
        xM = xStart + Integer.parseInt(StatCollector.translateToLocal("EnchanterGUI.xMax"));
        ym = yStart + Integer.parseInt(StatCollector.translateToLocal("EnchanterGUI.yMin"));
        yM = yStart + Integer.parseInt(StatCollector.translateToLocal("EnchanterGUI.yMax"));
        displayedEnchants = new ArrayList<>(Enchantment.enchantmentsList.length+1);
        HxCEnchants.KnownRegisteredEnchants.forEach(ench -> displayedEnchants.add(ench));
        super.initGui();
    }
    private boolean itemFound = false;
    @Override
    protected void drawGuiContainerBackgroundLayer(float opacity, int x, int y) {
        GL11.glColor4f(1F, 1F, 1F, 1F);
        //Draws initial texture
        mc.getTextureManager().bindTexture(new ResourceLocation(Reference.MOD_ID + ":gui/HxCEnchanterGUI.png"));
        drawTexturedModalRect(xStart, yStart, 0, 0, xSize, ySize);

        if (inventorySlots.getSlot(0).getHasStack() && !itemFound) {
            itemFound = true;
            displayedEnchants.clear();
            currentEnchantsOnItem.clear();
            newEnchLevels.clear();
            HxCEnchants.KnownRegisteredEnchants.forEach(ench -> displayedEnchants.add(ench));
            HxCEnchants.KnownRegisteredEnchants.forEach(ench -> newEnchLevels.put(ench, 0));
            if (inventorySlots.getSlot(0).getStack().isItemEnchanted()) {
                EnchantmentHelper.getEnchantments(inventorySlots.getSlot(0).getStack()).forEach((id, lvl) -> {
                    newEnchLevels.replace(Enchantment.enchantmentsList[(int) id], (int) lvl);
                    currentEnchantsOnItem.put(Enchantment.enchantmentsList[(int) id], (int) lvl);
                });
                HxCEnchants.KnownRegisteredEnchants.forEach(KRE -> {
                    if (!KRE.canApply(inventorySlots.getSlot(0).getStack()) && !currentEnchantsOnItem.containsKey(KRE)) {
                        displayedEnchants.remove(KRE);
                    } else if (!currentEnchantsOnItem.containsKey(KRE)) {
                        currentEnchantsOnItem.forEach((ENCHANT,LVL) -> {
                            if (LVL > 0 && !KRE.canApplyTogether(ENCHANT)) {
                                displayedEnchants.remove(KRE);
                            }
                        });
                    }
                });
            } else {
                //Lists Valid Enchants for Item
                HxCEnchants.KnownRegisteredEnchants.forEach(KRE -> {
                    if (!KRE.canApply(inventorySlots.getSlot(0).getStack())) {
                        displayedEnchants.remove(KRE);
                        newEnchLevels.remove(KRE);
                    }
                });
            }
        } else if (itemFound && !inventorySlots.getSlot(0).getHasStack()) {
            newEnchLevels.clear();
            currentEnchantsOnItem.clear();
            displayedEnchants.clear();
            itemFound = false;
            HxCEnchants.KnownRegisteredEnchants.forEach(ench -> displayedEnchants.add(ench));
        }

        //Gets if mouse wheel moved
        int d = Mouse.getDWheel();
        if (d < 0)
            d = -1;
        else if (d > 0)
            d = 1;
        if (d != 0)
            scrollEvent(d, x, y);

        //Draws the scrollbar
        float scrollbarlocation = (yStart + 6) + (((float)row / ((float) displayedEnchants.size()-7)) * 156f);
        drawTexturedModalRect(xStart + 210, Math.round(scrollbarlocation), 231, 3, 14, 22);

        //Draws the enchant button
        if (x >= xm && x <= xM && y >= ym && y <= yM)
            drawTexturedModalRect(xStart + 6, yStart + 84, 0, 237, 192, 13);
        else
            drawTexturedModalRect(xStart + 6, yStart + 84, 0, 222, 192, 13);

        //If enabled draws mouse coordinates on screen
        if (EnableCoordinatesInGUIs)
            drawString(fontRendererObj, "X : " + x + "  Y : " + y, 0, 0, Color.white.getRGB());


        if (inventorySlots.getSlot(0) != null && inventorySlots.getSlot(0).getHasStack() && !newEnchLevels.isEmpty()) {
            final int[] shelves = {0};
            //Lists the ready to apply enchant levels
            newEnchLevels.forEach((enchantID, enchantLVL) -> {
                if (enchantLVL > 0) {
                    if ((currentEnchantsOnItem.containsKey(enchantID) && !Objects.equals(currentEnchantsOnItem.get(enchantID), enchantLVL))) {
                        shelves[0] += enchantLVL - currentEnchantsOnItem.get(enchantID);
                    } else if (!currentEnchantsOnItem.containsKey(enchantID)) {
                        shelves[0] += enchantLVL;
                    }
                    newEnchLevels.keySet().forEach(id -> {
                        if (id != enchantID && !id.canApplyTogether(enchantID))
                            displayedEnchants.remove(id);
                    });
                }
            });
            if (shelves[0] < 1)
                shelves[0] = 0;
            shelvesNeeded = shelves[0];
            xpneeded = cost(inventorySlots.getSlot(0).getStack());
        }

        int strX = xStart + 54, stry = 11;
        for (int i = 0; i < 7; i++) {
            String str = "";
            try {
                str = displayedEnchants.get(row + i).getTranslatedName(newEnchLevels.get(displayedEnchants.get(row + i)));
                if (str.length() > 30) str = str.replace("Protection", "Prot.");
            } catch (Exception ignored) {}
            if (!str.isEmpty())
                fontRendererObj.drawString(str, strX, (yStart + 8) + (stry * i), Color.black.getRGB());
        }

        drawString(fontRendererObj, "XP required : " + xpneeded, xStart + 70, yStart - 20, Color.white.getRGB());
        drawString(fontRendererObj, "Bookshelves required/present : " + shelvesNeeded + "/" + getShelvesAABB(world, AABBUtils.getAreaBoundingBox(px, py, pz, Configurations.tableRange)), xStart + 10, yStart - 10, Color.white.getRGB());
        fontRendererObj.drawString("Enchant!", xStart + 101 - fontRendererObj.getStringWidth(StatCollector.translateToLocal("EnchanterGUI.enchantButton")) / 2, yStart + 88, Color.black.getRGB());
    }

    private void scrollEvent(int d, int x, int y) {
        if (displayedEnchants != null && !displayedEnchants.isEmpty()) {
            // Decides wether to scroll Enchant level or Enchant list
            if (!(x >= xStart + 50 && x <= xStart + 195 && y >= yStart + 7 && y <= yStart + 87)) {
                row -= d;
                if (row > displayedEnchants.size() - 7)
                    row = displayedEnchants.size() - 7;
                else if (row < 0)
                    row = 0;
            } else if (newEnchLevels != null && !newEnchLevels.isEmpty() && x >= xStart + 50 && x <= xStart + 195 && inventorySlots != null && inventorySlots.getSlot(0) != null && inventorySlots.getSlot(0).getHasStack() && ((inventorySlots.getSlot(0).getStack().isItemEnchantable()) || inventorySlots.getSlot(0).getStack().isItemEnchanted())) {
                int yi = yStart + 8;
                for (int i = 0; i < 7; i++)
                    if (y > yi + (11 * i) && y < yi + (11 * (i + 1))) {
                        //can only null if Enchant is null, newEnchLevels is somehow null, and displayedEnchants is also somehow null
                        if (displayedEnchants.get(row + i) != null && newEnchLevels.get(displayedEnchants.get(row + i)) != null) {
                            newEnchLevels.replace(displayedEnchants.get(row + i), newEnchLevels.get(displayedEnchants.get(row + i)) + d);

                            //Functioning Don't Change, makes sure doesn't exceed limits
                            if (newEnchLevels.get(displayedEnchants.get(row + i)) <= 0)
                                newEnchLevels.replace(displayedEnchants.get(row + i), 0);
                            else if (newEnchLevels.get(displayedEnchants.get(row + i)) >= displayedEnchants.get(row + i).getMaxLevel())
                                newEnchLevels.replace(displayedEnchants.get(row + i), displayedEnchants.get(row + i).getMaxLevel());
                        }
                    }
            }
        }
    }

    @Override
    protected void mouseClicked(int x, int y, int button) {
        if (button == 0) {
            if (inventorySlots.getSlot(0) != null && inventorySlots.getSlot(0).getHasStack()) {
                if (xpneeded > 0 && xpneeded <= xp && x >= xm && x <= xM && y >= ym && y <= yM) {
                    final String[] tmp = {""};
                    newEnchLevels.forEach((a, b) -> {
                        if (b != 0) tmp[0] = tmp[0] + a.effectId + ":" + b + ":";
                    });
                    if (!tmp[0].isEmpty()) {
                        HxCEnchants.networkWrapper.sendToServer(new PacketHxCEnchanterSync(px, py, pz, xpneeded, mc.thePlayer.getDisplayName(), tmp[0].substring(0, tmp[0].length() - 1)));
                        xp -= xpneeded;
                        xpneeded = 0;
                        HxCEnchants.KnownRegisteredEnchants.forEach(ench -> newEnchLevels.put(ench, 0));
                    }
                }
            }
        }
        super.mouseClicked(x, y, button);
    }

    private int cost(ItemStack stack) {
        final int[] cost = {0};
        newEnchLevels.forEach((enchantment, enchantmentLevel) -> {
            if (!(currentEnchantsOnItem.keySet().contains(enchantment) && Objects.equals(currentEnchantsOnItem.get(enchantment), enchantmentLevel))) {
                if (enchantmentLevel > 0) {
                    final int baseCost = enchantment.getMaxEnchantability(enchantmentLevel) + enchantment.getMinEnchantability(enchantmentLevel);
                    int enchantability = stack.getItem().getItemEnchantability();

                    if (enchantability < 1) enchantability = 1;

                    double adjCost;
                    if (currentEnchantsOnItem.containsKey(enchantment))
                        adjCost = (baseCost * enchantmentLevel) - (baseCost * currentEnchantsOnItem.get(enchantment));
                    else
                        adjCost = baseCost * enchantmentLevel;

                    if (enchantability > 1)
                        adjCost = baseCost / (enchantability / 2);
                    else
                        adjCost = baseCost / 2;

                    cost[0] += Math.ceil(adjCost);
                }
            }
        });
        return cost[0];
    }
}