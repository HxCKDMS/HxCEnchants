package HxCKDMS.HxCEnchants.XPInfuser;

import HxCKDMS.HxCEnchants.HxCEnchants;
import HxCKDMS.HxCEnchants.lib.Reference;
import HxCKDMS.HxCEnchants.network.PacketEnchanterSync;
import cpw.mods.fml.client.config.GuiSlider;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

import java.awt.*;
import java.util.Collections;
import java.util.List;

@SuppressWarnings("unchecked")
public class XPInfuserGUI extends GuiContainer {
    private int x, y, z, xpti, xp;
    private EntityPlayer player;
    public XPInfuserGUI (XPInfuserContainer container, EntityPlayer player) {
        super(container);
        this.x = container.infuserTile.xCoord;
        this.y = container.infuserTile.yCoord;
        this.z = container.infuserTile.zCoord;
        this.player = player;
    }

    @Override
    public void initGui() {
        // need this to calculate center
        ScaledResolution sres = new ScaledResolution(this.mc, this.mc.displayWidth, this.mc.displayHeight);
        int button_width = 160;

        xp = player.experienceLevel;

        int GUITCX = (sres.getScaledWidth() - button_width) / 2;
        int GUITCY = (sres.getScaledHeight() - 160) / 2;

        addButton(new GuiSlider(0, 0, 0, button_width, 20, "XP To Infuse: ", "", 0, xp, xpti, false, true), GUITCX, GUITCY-25);
        addButton(new GuiButton(1, 0, 0, button_width, 20,"Infuse!"), GUITCX, GUITCY+55);

        super.initGui();
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float opacity, int x, int y) {
        GL11.glColor4f(1F, 1F, 1F, 1F);

        mc.getTextureManager().bindTexture(new ResourceLocation(Reference.MOD_ID + ":gui/XPInfuserGUI.png"));

        int xStart = (width - xSize) / 2;
        int yStart = (height - ySize) / 2;
        drawTexturedModalRect(xStart, yStart, 0, 0, xSize, ySize);
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float f) {
        drawDefaultBackground();
        drawRect(width / 2 - 105, 60, width / 2 - 35, height / 2 + 5, new Color(50, 170, 170, 70).getRGB());
        drawCenteredString(fontRendererObj, I18n.format("XP Infuser"), width / 2, 25, Color.WHITE.getRGB());
        super.drawScreen(mouseX, mouseY, f);
        for (Object button : buttonList) {
            if (button instanceof GuiButton) {
                GuiButton btn = (GuiButton) button;
                String text;
                if (btn.func_146115_a()) {
                    switch (btn.id) {
                        case 0 : text = "XP levels to infuse into item.";
                            break;
                        case 1 : text = "Infuse the current enchants into item?";
                            break;
                        default : text = "null";
                            break;
                    }
                    List temp = Collections.singletonList(text);
                    drawHoveringText(temp, mouseX, mouseY, fontRendererObj);
                }
            }
        }
    }

    @Override
    protected void actionPerformed(GuiButton button) {
        if (button.id == 1) {
            for (Object b : this.buttonList) {
                GuiButton btn = (GuiButton)b;
                if (btn.id == 0) {
                    xpti = ((GuiSlider) btn).getValueInt();
                    ((GuiSlider)btn).setValue(0);
                    ((GuiSlider)btn).sliderValue = 0;
                    xp -= xpti;
                    ((GuiSlider)btn).maxValue = xp;
                }
            }
            HxCEnchants.packetPipeline.sendToServer(new PacketEnchanterSync(x, y, z, xpti, mc.thePlayer.getDisplayName()));
            xpti = 0;
        }
    }

    public void addButton(GuiButton btn, int xpos, int ypos) {
        btn.xPosition = xpos;
        btn.yPosition = ypos;
        buttonList.add(btn);
    }
}