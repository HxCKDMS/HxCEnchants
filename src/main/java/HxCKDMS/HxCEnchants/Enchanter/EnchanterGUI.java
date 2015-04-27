package HxCKDMS.HxCEnchants.Enchanter;

import HxCKDMS.HxCEnchants.HxCEnchants;
import HxCKDMS.HxCEnchants.lib.Reference;
import HxCKDMS.HxCEnchants.network.PacketHandler;
import cpw.mods.fml.client.config.GuiSlider;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.resources.I18n;
import net.minecraft.inventory.Container;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

import java.awt.*;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.util.Arrays;
import java.util.List;

@SuppressWarnings("unchecked")
public class EnchanterGUI extends GuiContainer {
    public EnchanterGUI (Container container) {
        super(container);
    }
    private EnchanterTile tile = new EnchanterTile();
    int[] HxCEnchs = new int[]{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
    int[] Selection = new int[]{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};

    @Override
    public void initGui() {
        // need this to calculate center
        ScaledResolution sres = new ScaledResolution(this.mc, this.mc.displayWidth, this.mc.displayHeight);
        int button_width = 160;
        int xp = mc.thePlayer.experienceLevel;
        int GUITCX = (sres.getScaledWidth() - button_width) / 2;
        int GUITCY = (sres.getScaledHeight() - 160) / 2;

        addButton(new GuiSlider(0, 0, 0, button_width, 20, "XP To Infuse: ", "", 0, xp, tile.xpti, false, true), GUITCX, GUITCY-25);
        addButton(new GuiButton(1, 0, 0, button_width, 20,"Infuse!"), GUITCX, GUITCY+55);

        addButton(new GuiButton(2, 0, 0, 20, 20, null), GUITCX - 30, GUITCY);
        addButton(new GuiButton(3, 0, 0, 20, 20, null), GUITCX - 30, GUITCY + 25);
        addButton(new GuiButton(4, 0, 0, 20, 20, null), GUITCX - 30, GUITCY + 50);
        addButton(new GuiButton(5, 0, 0, 20, 20, null), GUITCX - 55, GUITCY);
        addButton(new GuiButton(6, 0, 0, 20, 20, null), GUITCX - 55, GUITCY + 25);
        addButton(new GuiButton(7, 0, 0, 20, 20, null), GUITCX - 55, GUITCY + 50);
        addButton(new GuiButton(8, 0, 0, 20, 20, null), GUITCX - 80, GUITCY);
        addButton(new GuiButton(9, 0, 0, 20, 20, null), GUITCX - 80, GUITCY + 25);
        addButton(new GuiButton(10, 0, 0, 20, 20,  null), GUITCX - 80, GUITCY + 50);
        addButton(new GuiButton(11, 0, 0, 20, 20,  null), GUITCX - 105, GUITCY);
        addButton(new GuiButton(12, 0, 0, 20, 20,  null), GUITCX - 105, GUITCY + 25);
        addButton(new GuiButton(24, 0, 0, 20, 20,  null), GUITCX - 105, GUITCY + 50);

        addButton(new GuiButton(13, 0, 0, 20, 20,  null), GUITCX + 170, GUITCY);
        addButton(new GuiButton(14, 0, 0, 20, 20,  null), GUITCX + 170, GUITCY + 25);
        addButton(new GuiButton(15, 0, 0, 20, 20,  null), GUITCX + 170, GUITCY + 50);
        addButton(new GuiButton(16, 0, 0, 20, 20,  null), GUITCX + 195, GUITCY);
        addButton(new GuiButton(17, 0, 0, 20, 20,  null), GUITCX + 195, GUITCY + 25);
        addButton(new GuiButton(18, 0, 0, 20, 20,  null), GUITCX + 195, GUITCY + 50);
        addButton(new GuiButton(19, 0, 0, 20, 20,  null), GUITCX + 220, GUITCY);
        addButton(new GuiButton(20, 0, 0, 20, 20,  null), GUITCX + 220, GUITCY + 25);
        addButton(new GuiButton(21, 0, 0, 20, 20,  null), GUITCX + 220, GUITCY + 50);
        addButton(new GuiButton(22, 0, 0, 20, 20,  null), GUITCX + 245, GUITCY);
        addButton(new GuiButton(23, 0, 0, 20, 20,  null), GUITCX + 245, GUITCY + 25);
        addButton(new GuiButton(25, 0, 0, 20, 20,  null), GUITCX + 245, GUITCY + 50);

        super.initGui();
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float opacity, int x, int y) {
        GL11.glColor4f(1F, 1F, 1F, 1F);

        mc.getTextureManager().bindTexture(new ResourceLocation(Reference.MOD_ID + ":gui/EnchanterGUI.png"));

        int xStart = (width - xSize) / 2;
        int yStart = (height - ySize) / 2;
        drawTexturedModalRect(xStart, yStart, 0, 0, xSize, ySize);
    }
    @Override
    public void drawScreen(int mouseX, int mouseY, float f) {
        // Draw your stuff like Textures and Strings
        drawDefaultBackground();
        drawRect(width / 2 - 105, 60, width / 2 - 35, height / 2 + 5, new Color(50, 170, 170, 70).getRGB());
        drawCenteredString(fontRendererObj, I18n.format("menu.title"), width / 2, 25, Color.WHITE.getRGB());

        // Call this methods super, as it draws buttons and stuff
        super.drawScreen(mouseX, mouseY, f);
        // Add the hovering for all GuiButtons in the buttonList
        for (Object button : buttonList) {
            if (button instanceof GuiButton) {
                GuiButton btn = (GuiButton) button;
                String number = "0";
                if (btn.id >= 2 && btn.id <= 7) {
                    number = String.valueOf(Selection[btn.id - 2]);
                } else if (btn.id >= 8 && btn.id <= 11) {
                    number = String.valueOf(Selection[btn.id - 1]);
                } else if (btn.id >= 12 && btn.id <= 14) {
                    number = String.valueOf(Selection[btn.id]);
                } else if (btn.id >= 15 && btn.id <= 17) {
                    number = String.valueOf(Selection[btn.id + 1]);
                } else if (btn.id >= 18 && btn.id <= 25) {
                    number = String.valueOf(Selection[btn.id + 2]);
                }
                if (btn.id > 1) fontRendererObj.drawStringWithShadow(number, btn.xPosition+6, btn.yPosition+5, 0x00FFFF);
                String text;
                if (btn.func_146115_a()) {
                    switch (btn.id) {
                        case 0 : text = "XP levels to infuse into item.";
                            break;
                        case 1 : text = "Infuse the current enchants into item?";
                            break;
                        case 2 : text = "AdrenalineBoost";
                            break;
                        case 3 : text = "AuraDark";
                            break;
                        case 4 : text = "AuraDeadly";
                            break;
                        case 5 : text = "AuraFiery";
                            break;
                        case 6 : text = "AuraThick";
                            break;
                        case 7 : text = "AuraToxic";
                            break;
                        case 8 : text = "ArmorRegen";
                            break;
                        case 9 : text = "ArrowExplosive";
                            break;
                        case 10 : text = "ArrowLightning";
                            break;
                        case 11 : text = "ArrowSeeking";
                            break;
                        case 12 : text = "BattleHealing";
                            break;
                        case 13 : text = "FlameTouch";
                            break;
                        case 14 : text = "Fly";
                            break;
                        case 15 : text = "JumpBoost";
                            break;
                        case 16 : text = "LifeSteal";
                            break;
                        case 17 : text = "Poison";
                            break;
                        case 18 : text = "Repair";
                            break;
                        case 19 : text = "SoulTear";
                            break;
                        case 20 : text = "Swiftness";
                            break;
                        case 21 : text = "Stealth";
                            break;
                        case 22 : text = "Vampirism";
                            break;
                        case 23 : text = "Vitality";
                            break;
                        case 24 : text = "WitherProtection";
                            break;
                        case 25 : text = "Examine";
                            break;
                        default : text = "null";
                            break;
                    }
                    List temp = Arrays.asList(text);
                    drawHoveringText(temp, mouseX, mouseY, fontRendererObj);
                }
            }
        }
    }
    @Override
    protected void actionPerformed(GuiButton button) {
        if (button.id == 1) {
            ByteBuffer byteBuffer = ByteBuffer.allocate(Selection.length * 4);
            IntBuffer intBuffer = byteBuffer.asIntBuffer();
            intBuffer.put(Selection);
            byte[] enchArray = byteBuffer.array();
            for (Object b : this.buttonList) {
                GuiButton btn = (GuiButton)b;
                if (btn.id == 0) {
                    tile.xpti = ((GuiSlider)btn).getValueInt();
                    tile.enchs = enchArray;
                }
            }

            HxCEnchants.Network.sendToServer(new PacketHandler(tile));
            mc.thePlayer.closeScreen();
        }
        if (button.id >= 2 && button.id <= 7) {
            Selection[button.id - 2] = Selection[button.id - 2] + 1;
        } else if (button.id >= 8 && button.id <= 11) {
            Selection[button.id - 1] = Selection[button.id - 1] + 1;
        } else if (button.id >= 12 && button.id <= 14) {
            Selection[button.id] = Selection[(button.id)] + 1;
        } else if (button.id >= 15 && button.id <= 17) {
            Selection[button.id + 1] = Selection[button.id + 1] + 1;
        } else if (button.id >= 18 && button.id <= 25) {
            Selection[button.id + 2] = Selection[button.id + 2] + 1;
        }
    }

    public void addButton(GuiButton btn, int xpos, int ypos) {
        btn.xPosition = xpos;
        btn.yPosition = ypos;
        buttonList.add(btn);
    }
}