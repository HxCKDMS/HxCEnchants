package HxCKDMS.HxCEnchants.Blocks.XPInfuser;

import HxCKDMS.HxCEnchants.Configurations;
import HxCKDMS.HxCEnchants.HxCEnchants;
import HxCKDMS.HxCEnchants.lib.Reference;
import HxCKDMS.HxCEnchants.network.PacketInfuserSync;
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
    private float xp_percentage = 1;
    private boolean dragging = false;
    public XPInfuserGUI (XPInfuserContainer container, EntityPlayer player) {
        super(container);
        this.x = container.infuserTile.xCoord;
        this.y = container.infuserTile.yCoord;
        this.z = container.infuserTile.zCoord;
        xp = player.experienceLevel;
    }

    @Override
    public void initGui() {
        // need this to calculate center
        ScaledResolution sres = new ScaledResolution(this.mc, this.mc.displayWidth, this.mc.displayHeight);
        int button_width = 160;

        int GUITCX = (sres.getScaledWidth() - button_width) / 2;
        int GUITCY = (sres.getScaledHeight() - 160) / 2;

        addButton(new GuiButton(0, 0, 0, button_width, 20, "Infuse!"), GUITCX, GUITCY + 55);
        super.initGui();
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float opacity, int x, int y) {
        GL11.glColor4f(1F, 1F, 1F, 1F);

        mc.getTextureManager().bindTexture(new ResourceLocation(Reference.MOD_ID + ":gui/XPInfuserGUI" + Configurations.guiVersion + ".png"));
        int xStart = (width - xSize) / 2;
        int yStart = (height - ySize) / 2;
        drawTexturedModalRect(xStart, yStart, 0, 0, xSize, ySize);
        drawTexturedModalRect(xStart + 7, yStart + 7, 7, 168, (int) (168 * (1 - xp_percentage)), 19);
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float f) {
        int yStart = (height - ySize) / 2;
        drawDefaultBackground();
        drawRect(width / 2 - 105, 60, width / 2 - 35, height / 2 + 5, new Color(50, 170, 170, 70).getRGB());
        drawCenteredString(fontRendererObj, I18n.format("XP Infuser"), width / 2, 25, Color.WHITE.getRGB());
        super.drawScreen(mouseX, mouseY, f);
        for (Object button : buttonList) {
            if (button instanceof GuiButton) {
                GuiButton btn = (GuiButton) button;
                if (btn.isMouseOver()) {
                    String text = "Infuse XP!";
                    List temp = Collections.singletonList(text);
                    drawHoveringText(temp, mouseX, mouseY, fontRendererObj);
                }
            }
        }
        drawString(fontRendererObj, "XP to infuse : " + xpti, width / 2 - 35, yStart - 10, Color.white.getRGB());
    }

    @Override
    protected void actionPerformed(GuiButton button) {
        if (button.id == 0 && xpti != 0 && xpti <= xp) {
            HxCEnchants.networkWrapper.sendToServer(new PacketInfuserSync(x, y, z, xpti, mc.thePlayer.getDisplayName()));
            xp -= xpti; xpti = 0; xp_percentage = 1;
        }
    }

    public void addButton(GuiButton btn, int xpos, int ypos) {
        btn.xPosition = xpos;
        btn.yPosition = ypos;
        buttonList.add(btn);
    }

    @Override
    protected void mouseClicked(int x, int y, int button) {
        if(button == 0){
            int xStart = (width - xSize) / 2;
            int yStart = (height - ySize) / 2;

            if(x >= xStart + 7 && x <= xStart + 168){
                float percent = 1 - ((float)(x - xStart - 7)) / 168;

                if(y >= yStart + 7 && y <= yStart + 27) {
                    xp_percentage = percent;
                    xpti = xp - Math.round(xp * xp_percentage);
                }
            }
        }
        super.mouseClicked(x, y, button);
    }

    @Override
    protected void mouseClickMove(int x, int y, int lastButtonClicked, long timeSinceClicked) {
        int xStart = (width - xSize) / 2;
        int yStart = (height - ySize) / 2;

        if(lastButtonClicked == 0){
            if(!dragging) {
                if (y >= yStart + 7 && y <= yStart + 25)
                    if (x >= xStart + 7 && x <= xStart + 168)
                        dragging = true;
            }
            else {
                float percent = 1 - ((float)(x - xStart - 7)) / 168;

                if(percent > 1) percent = 1;
                if(percent < 0) percent = 0;

                xp_percentage = percent;
                xpti = xp - Math.round(xp * xp_percentage);
            }
        }
        super.mouseClickMove(x, y, lastButtonClicked, timeSinceClicked);
    }

    @Override
    protected void mouseReleased(int mouseX, int mouseY, int state) {
        if(state == 0)
            dragging = false;

        super.mouseReleased(mouseX, mouseY, state);
    }

    /*@Override
    protected void mouseMovedOrUp(int x, int y, int which) {
        if(which == 0)
            dragging = false;

        super.mouseMovedOrUp(x, y, which);
    }*/

}