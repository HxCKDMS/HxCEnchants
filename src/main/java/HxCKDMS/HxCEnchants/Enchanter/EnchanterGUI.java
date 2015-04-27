package HxCKDMS.HxCEnchants.Enchanter;

import HxCKDMS.HxCEnchants.HxCEnchants;
import HxCKDMS.HxCEnchants.lib.Reference;
import HxCKDMS.HxCEnchants.network.PacketHandler;
import cpw.mods.fml.client.config.GuiSlider;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.inventory.Container;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

import java.nio.ByteBuffer;

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

        addButton(new GuiButton(2, 0, 0, 20, 20, String.valueOf(Selection[0])), GUITCX - 30, GUITCY);
        addButton(new GuiButton(3, 0, 0, 20, 20, String.valueOf(Selection[1])), GUITCX - 30, GUITCY + 25);
        addButton(new GuiButton(4, 0, 0, 20, 20, String.valueOf(Selection[2])), GUITCX - 30, GUITCY + 50);
        addButton(new GuiButton(5, 0, 0, 20, 20, String.valueOf(Selection[3])), GUITCX - 55, GUITCY);
        addButton(new GuiButton(6, 0, 0, 20, 20, String.valueOf(Selection[4])), GUITCX - 55, GUITCY + 25);
        addButton(new GuiButton(7, 0, 0, 20, 20, String.valueOf(Selection[5])), GUITCX - 55, GUITCY + 50);
        addButton(new GuiButton(8, 0, 0, 20, 20, String.valueOf(Selection[7])), GUITCX - 80, GUITCY);
        addButton(new GuiButton(9, 0, 0, 20, 20, String.valueOf(Selection[8])), GUITCX - 80, GUITCY + 25);
        addButton(new GuiButton(10, 0, 0, 20, 20,  String.valueOf(Selection[9])), GUITCX - 80, GUITCY + 50);
        addButton(new GuiButton(11, 0, 0, 20, 20,  String.valueOf(Selection[10])), GUITCX - 105, GUITCY);
        addButton(new GuiButton(12, 0, 0, 20, 20,  String.valueOf(Selection[12])), GUITCX - 105, GUITCY + 25);
        addButton(new GuiButton(24, 0, 0, 20, 20,  String.valueOf(Selection[13])), GUITCX - 105, GUITCY + 50);

        addButton(new GuiButton(13, 0, 0, 20, 20,  String.valueOf(Selection[14])), GUITCX + 170, GUITCY);
        addButton(new GuiButton(14, 0, 0, 20, 20,  String.valueOf(Selection[15])), GUITCX + 170, GUITCY + 25);
        addButton(new GuiButton(15, 0, 0, 20, 20,  String.valueOf(Selection[17])), GUITCX + 170, GUITCY + 50);
        addButton(new GuiButton(16, 0, 0, 20, 20,  String.valueOf(Selection[18])), GUITCX + 195, GUITCY);
        addButton(new GuiButton(17, 0, 0, 20, 20,  String.valueOf(Selection[19])), GUITCX + 195, GUITCY + 25);
        addButton(new GuiButton(18, 0, 0, 20, 20,  String.valueOf(Selection[21])), GUITCX + 195, GUITCY + 50);
        addButton(new GuiButton(19, 0, 0, 20, 20,  String.valueOf(Selection[22])), GUITCX + 220, GUITCY);
        addButton(new GuiButton(20, 0, 0, 20, 20,  String.valueOf(Selection[23])), GUITCX + 220, GUITCY + 25);
        addButton(new GuiButton(21, 0, 0, 20, 20,  String.valueOf(Selection[24])), GUITCX + 220, GUITCY + 50);
        addButton(new GuiButton(22, 0, 0, 20, 20,  String.valueOf(Selection[25])), GUITCX + 245, GUITCY);
        addButton(new GuiButton(23, 0, 0, 20, 20,  String.valueOf(Selection[26])), GUITCX + 245, GUITCY + 25);
        addButton(new GuiButton(25, 0, 0, 20, 20,  String.valueOf(Selection[27])), GUITCX + 245, GUITCY + 50);

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
    protected void actionPerformed(GuiButton button) {
        if (button.id == 1) {
            ByteBuffer byteBuffer = ByteBuffer.allocate(HxCEnchs.length * 4);
            for (Object b : this.buttonList) {
                GuiButton btn = (GuiButton)b;
                if (btn.id == 0) {
                    tile.xpti = ((GuiSlider)btn).getValueInt();
                    tile.enchs = null;
                }
            }

            HxCEnchants.Network.sendToServer(new PacketHandler(tile));
            mc.thePlayer.closeScreen();
        } else if (button.id > 1 && button.id < 8) {
            Selection[button.id - 2] = Selection[(button.id - 2)] + 1;
            updateScreen();
        } else if (button.id > 7 && button.id < 12) {
            Selection[button.id - 3] = Selection[(button.id - 3)] + 1;
            updateScreen();
        } else if (button.id > 11 && button.id < 15) {
            Selection[button.id - 4] = Selection[(button.id - 4)] + 1;
            updateScreen();
        } else if (button.id > 14 && button.id < 18) {
            Selection[button.id - 5] = Selection[(button.id - 5)] + 1;
            updateScreen();
        } else if (button.id > 17 && button.id < 26) {
            Selection[button.id - 6] = Selection[(button.id - 6)] + 1;
            updateScreen();
        }
    }

    public void addButton(GuiButton btn, int xpos, int ypos) {
        btn.xPosition = xpos;
        btn.yPosition = ypos;
        buttonList.add(btn);
    }
}