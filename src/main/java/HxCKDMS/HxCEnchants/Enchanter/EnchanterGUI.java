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

    // padding (or margin w/e) and internal placement offsets
    public int paddingX = 0;
    public int paddingY = 0;
    private int offsetXcounter = 0;
    private int offsetYcounter = 0;

    // gui elements
    private enum ELEMENTS {
        UsedXP,
        DONEBTN, CANCELBTN
    }

    @Override
    public void initGui() {
        // need this to calculate center
        ScaledResolution sres = new ScaledResolution(this.mc, this.mc.displayWidth, this.mc.displayHeight);
        int[] centerCoords = new int[]{(sres.getScaledHeight()/2),(sres.getScaledWidth()/2)};
        int width = centerCoords[1] - 40;
        addButton(new GuiSlider(ELEMENTS.UsedXP.ordinal(), 20, 20, width, 20, "XP To Infuse: ", "", 0.0, 512.0, tile.xpti, false, true));
        addButton(new GuiButton(1,20,400,width,10,"lel"));

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

    public void addButton(GuiButton btn) {
        btn.xPosition = offsetXcounter;
        btn.yPosition = offsetYcounter;
        buttonList.add(btn);

        this.offsetYcounter += btn.height + this.paddingY;
    }

    public void addButtonsH(GuiButton... btns) {
        for (GuiButton btn : btns) {
            btn.xPosition = offsetXcounter;
            btn.yPosition = offsetYcounter;
            this.buttonList.add(btn);
            this.offsetXcounter += btn.width + this.paddingX;
        }
    }
}