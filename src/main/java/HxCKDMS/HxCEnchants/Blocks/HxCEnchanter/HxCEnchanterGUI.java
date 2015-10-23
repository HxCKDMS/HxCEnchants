package HxCKDMS.HxCEnchants.Blocks.HxCEnchanter;

import HxCKDMS.HxCCore.api.Utils.AABBUtils;
import HxCKDMS.HxCEnchants.Configurations;
import HxCKDMS.HxCEnchants.HxCEnchants;
import HxCKDMS.HxCEnchants.lib.Reference;
import HxCKDMS.HxCEnchants.network.PacketHxCEnchanterSync;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.resources.I18n;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeHooks;
import org.lwjgl.opengl.GL11;

import java.awt.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;


@SuppressWarnings("unchecked")
public class HxCEnchanterGUI extends GuiContainer {
    private int px, py, pz, xpneeded, xp, row = 0, shelvesNeeded = 0;
    private HxCEnchanterContainer HxCContainer;
    private List<Enchantment> enchs = HxCEnchants.KnownRegisteredEnchants;
    private LinkedHashMap<Enchantment, Integer> enchLvls = new LinkedHashMap<>();
    private World world;
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
        HxCEnchants.KnownRegisteredEnchants.forEach(ench -> enchLvls.put(ench, 0));
        super.initGui();
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float opacity, int x, int y) {
        GL11.glColor4f(1F, 1F, 1F, 1F);
        mc.getTextureManager().bindTexture(new ResourceLocation(Reference.MOD_ID + ":gui/HxCEnchanterGUI.png"));
        int xStart = (width - xSize) / 2;
        int yStart = (height - ySize) / 2;
        drawTexturedModalRect(xStart, yStart, 0, 0, xSize, ySize);
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float f) {
        if (HxCContainer.getInventory() != null && !HxCContainer.getInventory().isEmpty() && HxCContainer.getInventory().get(0) != null) {
            enchs = new ArrayList<>();
            final int[] tmp = {0};
            HxCEnchants.KnownRegisteredEnchants.forEach(ench -> {
                if (ench.canApply((ItemStack) HxCContainer.getInventory().get(0))) {
                    enchs.add(ench);
                }
            });
            enchLvls.forEach((x, y) -> {
                if (y > 0) {
                    tmp[0] += y;
                    enchLvls.forEach((a, b) -> {
                        if (x != a && !a.canApplyTogether(x))
                            enchs.remove(a);
                    });
                }
            });
            shelvesNeeded = tmp[0];
            xpneeded = cost((ItemStack) HxCContainer.getInventory().get(0));
        }
        if (row > enchs.size()-5)
            row = enchs.size()-5;
        int yStart = (height - ySize) / 2;
        drawDefaultBackground();
        drawRect(width / 2 - 105, 60, width / 2 - 35, height / 2 + 5, new Color(50, 170, 170, 70).getRGB());
        drawCenteredString(fontRendererObj, I18n.format("HxCEnchanter"), width / 2, 25, Color.WHITE.getRGB());
        super.drawScreen(mouseX, mouseY, f);
        drawString(fontRendererObj, "XP required : " + xpneeded, width / 2 - 45, yStart - 20, Color.white.getRGB());
        drawString(fontRendererObj, "Bookshelves required/present : " + shelvesNeeded + "/" + getShelvesAABB(world, AABBUtils.getAreaBoundingBox(px, py, pz, Configurations.tableRange)), width / 2 - 90, yStart - 10, Color.white.getRGB());
        String str = "";

        try {
            str = enchs.get(row).getTranslatedName(enchLvls.get(enchs.get(row)));
            if (str.length() > 21) str = str.replace("Protection", "Prot.");
        } catch (Exception ignored) {}
        if (!str.isEmpty())
            fontRendererObj.drawString(str, width / 2 - 42, yStart + 10, Color.black.getRGB());

        str = "";
        try {
            str = enchs.get(row + 1).getTranslatedName(enchLvls.get(enchs.get(row + 1)));
            if (str.length() > 21) str = str.replace("Protection", "Prot.");
        } catch (Exception ignored) {}
        if (!str.isEmpty())
            fontRendererObj.drawString(str, width / 2 - 42, yStart + 20, Color.black.getRGB());

        str = "";
        try {
            str = enchs.get(row + 2).getTranslatedName(enchLvls.get(enchs.get(row + 2)));
            if (str.length() > 21) str = str.replace("Protection", "Prot.");
        } catch (Exception ignored) {}
        if (!str.isEmpty())
            fontRendererObj.drawString(str, width / 2 - 42, yStart + 31, Color.black.getRGB());

        str = "";
        try {
            str = enchs.get(row + 3).getTranslatedName(enchLvls.get(enchs.get(row + 3)));
            if (str.length() > 21) str = str.replace("Protection", "Prot.");
        } catch (Exception ignored) {}
        if (!str.isEmpty())
            fontRendererObj.drawString(str, width / 2 - 42, yStart + 42, Color.black.getRGB());

        str = "";
        try {
            str = enchs.get(row + 4).getTranslatedName(enchLvls.get(enchs.get(row + 4)));
            if (str.length() > 21) str = str.replace("Protection", "Prot.");
        } catch (Exception ignored) {}
        if (!str.isEmpty())
            fontRendererObj.drawString(str, width / 2 - 42, yStart + 53, Color.black.getRGB());

        str = "";
        try {
            str = enchs.get(row + 5).getTranslatedName(enchLvls.get(enchs.get(row + 5)));
            if (str.length() > 21) str = str.replace("Protection", "Prot.");
        } catch (Exception ignored) {}
        if (!str.isEmpty())
            fontRendererObj.drawString(str, width / 2 - 42, yStart + 64, Color.black.getRGB());
    }

    @Override
    protected void mouseClicked(int x, int y, int button) {
        if (button == 0) {
            int xStart = (width - xSize) / 2;
            int yStart = (height - ySize) / 2;

            if (xpneeded > 0 && xpneeded <= xp && x >= xStart + 7 && x <= xStart + 32 && y >= yStart + 69 && y <= yStart + 77 && HxCContainer.getInventory().get(0) != null) {
                final String[] tmp = {""};
                enchLvls.forEach((a, b) ->{
                    if (b != 0) tmp[0] = tmp[0] + a.effectId + ":" + b + ":";
                });
                if (!tmp[0].isEmpty()) {
                    HxCEnchants.networkWrapper.sendToServer(new PacketHxCEnchanterSync(px, py, pz, xpneeded, mc.thePlayer.getDisplayName(), tmp[0].substring(0, tmp[0].length()-1)));
                    xp -= xpneeded;
                    xpneeded = 0;
                    HxCEnchants.KnownRegisteredEnchants.forEach(ench -> enchLvls.put(ench, 0));
                }
            }
            if (x >= xStart + 160 && x <= xStart + 170) {
                if (y >= yStart + 9 && y <= yStart + 22 && enchLvls.get(enchs.get(row)) < enchs.get(row).getMaxLevel()) {
                    enchLvls.replace(enchs.get(row), enchLvls.get(enchs.get(row)) + 1);
                } else if (y >= yStart + 23 && y <= yStart + 32 && enchLvls.get(enchs.get(row+1)) < enchs.get(row+1).getMaxLevel()) {
                    enchLvls.replace(enchs.get(row+1), enchLvls.get(enchs.get(row+1)) + 1);
                } else if (y >= yStart + 33 && y <= yStart + 42 && enchLvls.get(enchs.get(row+2)) < enchs.get(row+2).getMaxLevel()) {
                    enchLvls.replace(enchs.get(row+2), enchLvls.get(enchs.get(row+2)) + 1);
                } else if (y >= yStart + 43 && y <= yStart + 52 && enchLvls.get(enchs.get(row+3)) < enchs.get(row+3).getMaxLevel()) {
                    enchLvls.replace(enchs.get(row+3), enchLvls.get(enchs.get(row+3)) + 1);
                } else if (y >= yStart + 53 && y <= yStart + 62 && enchLvls.get(enchs.get(row+4)) < enchs.get(row+4).getMaxLevel()) {
                    enchLvls.replace(enchs.get(row+4), enchLvls.get(enchs.get(row+4)) + 1);
                } else if (y >= yStart + 63 && y <= yStart + 72 && enchLvls.get(enchs.get(row+5)) < enchs.get(row+5).getMaxLevel()) {
                    enchLvls.replace(enchs.get(row+5), enchLvls.get(enchs.get(row+5)) + 1);
                }
            }
            if (x >= xStart + 35 && x <= xStart + 45) {
                if (y >= yStart + 9 && y <= yStart + 22 && enchLvls.get(enchs.get(row)) != 0) {
                    enchLvls.replace(enchs.get(row), enchLvls.get(enchs.get(row)) - 1);
                } else if (y >= yStart + 23 && y <= yStart + 32 && enchLvls.get(enchs.get(row+1)) != 0) {
                    enchLvls.replace(enchs.get(row+1), enchLvls.get(enchs.get(row+1)) - 1);
                } else if (y >= yStart + 33 && y <= yStart + 42 && enchLvls.get(enchs.get(row+2)) != 0) {
                    enchLvls.replace(enchs.get(row+2), enchLvls.get(enchs.get(row+2)) - 1);
                } else if (y >= yStart + 43 && y <= yStart + 52 && enchLvls.get(enchs.get(row+3)) != 0) {
                    enchLvls.replace(enchs.get(row+3), enchLvls.get(enchs.get(row+3)) - 1);
                } else if (y >= yStart + 53 && y <= yStart + 62 && enchLvls.get(enchs.get(row+4)) != 0) {
                    enchLvls.replace(enchs.get(row+4), enchLvls.get(enchs.get(row+4)) - 1);
                } else if (y >= yStart + 63 && y <= yStart + 72 && enchLvls.get(enchs.get(row+5)) != 0) {
                    enchLvls.replace(enchs.get(row+5), enchLvls.get(enchs.get(row+5)) - 1);
                }
            }
            if (x >= xStart + 35 && x <= xStart + 100 && y >= yStart + 74 && y <= yStart + 77 && row < (HxCEnchants.KnownRegisteredEnchants.size() - 5)) {
                row++;
            }
            if (x >= xStart + 103 && x <= xStart + 170 && y >= yStart + 74 && y <= yStart + 77 && row > 0) {
                row--;
            }
        }
        super.mouseClicked(x, y, button);
    }

    private int cost(ItemStack stack) {
        final int[] cost = {0};
        enchLvls.forEach((enchantment, enchantmentLevel) -> {
            if (enchantmentLevel > 0) {
                final int baseCost = enchantment.getMinEnchantability(enchantmentLevel) + enchantment.getMaxEnchantability(enchantmentLevel);
                int enchantability = stack.getItem().getItemEnchantability();

                if (enchantability < 1) enchantability = 1;

                double adjCost = baseCost * enchantmentLevel;

                if (enchantability > 1) {
                    adjCost = baseCost / (enchantability / 2);
                } else {
                    adjCost = baseCost / 2;
                }
                cost[0] += Math.ceil(adjCost);
            }
        });
        return cost[0];
    }
}