package HxCKDMS.HxCEnchants.Enchanter;

import HxCKDMS.HxCEnchants.lib.Reference;
import cpw.mods.fml.client.config.GuiSlider;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.inventory.Container;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;
@SuppressWarnings("unchecked")
public class EnchanterGUI extends GuiContainer {
    public EnchanterGUI (Container container) {
        super(container);
    }
    private EnchanterTile tile = new EnchanterTile();

    // gui elements
    private enum ELEMENTS {UsedXP,DONEBTN}

    @Override
    public void initGui() {
        // need this to calculate center
        ScaledResolution sres = new ScaledResolution(this.mc, this.mc.displayWidth, this.mc.displayHeight);

        int GUITCX = sres.getScaledWidth();
        int GUITCY = sres.getScaledHeight();

        addButton(new GuiSlider(ELEMENTS.UsedXP.ordinal(), 0, 0, width/4, 20, "XP To Infuse: ", "", 0.0, 512.0, tile.xpti, false, true), GUITCX, GUITCY);
        addButton(new GuiButton(ELEMENTS.DONEBTN.ordinal(), 0, 0,width/4, 20,"lel"), GUITCX, GUITCY+20);

        super.initGui();
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float opacity, int x, int y) {
        GL11.glColor4f(1F, 1F, 1F, 1F);

        mc.getTextureManager().bindTexture(new ResourceLocation(Reference.MOD_ID + ":textures/gui/Enchanter.png"));

        int xStart = (width - xSize) / 2;
        int yStart = (height - ySize) / 2;
        drawTexturedModalRect(xStart, yStart, 0, 0, xSize, ySize);
    }

    public void addButton(GuiButton btn, int xpos, int ypos) {
        btn.xPosition = xpos;
        btn.yPosition = ypos;
        buttonList.add(btn);
    }
}