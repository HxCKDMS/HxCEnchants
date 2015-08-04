package HxCKDMS.HxCEnchants.XPInfuser;

import HxCKDMS.HxCEnchants.lib.Reference;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class XPInfuserSpecialRenderer extends TileEntitySpecialRenderer {
    public XPInfuserSpecialRenderer(){

    }

    @Override
    public void renderTileEntityAt(TileEntity tileEntity, double x, double y, double z, float f, int number){
        ResourceLocation icon = new ResourceLocation(Reference.MOD_ID + ":textures/blocks/XPInfuser.png");
        Tessellator tessellator = Tessellator.getInstance();
        WorldRenderer renderer = tessellator.getWorldRenderer();
        int MinU = 1,MinV = 1, MaxU = 0, MaxV = 0;
        this.bindTexture(icon);
        GL11.glPushMatrix();
        GL11.glTranslated(x, y, z);
        renderer.startDrawingQuads();

        renderer.setBrightness(255);
        renderer.addVertexWithUV(0, 1, 1, MinU, MinV);
        renderer.addVertexWithUV(1, 1, 1, MinU, MaxV);
        renderer.addVertexWithUV(1, 1, 0, MaxU, MaxV);
        renderer.addVertexWithUV(0, 1, 0, MaxU, MinV);

        renderer.setBrightness(255);
        renderer.addVertexWithUV(1, 0, 1, MinU, MinV);
        renderer.addVertexWithUV(0, 0, 1, MinU, MaxV);
        renderer.addVertexWithUV(0, 0, 0, MaxU, MaxV);
        renderer.addVertexWithUV(1, 0, 0, MaxU, MinV);

        renderer.setBrightness(255);
        renderer.addVertexWithUV(0, 0, 1, MinU, MinV);
        renderer.addVertexWithUV(0, 1, 1, MinU, MaxV);
        renderer.addVertexWithUV(0, 1, 0, MaxU, MaxV);
        renderer.addVertexWithUV(0, 0, 0, MaxU, MinV);

        renderer.setBrightness(255);
        renderer.addVertexWithUV(1, 0, 0, MinU, MinV);
        renderer.addVertexWithUV(1, 1, 0, MinU, MaxV);
        renderer.addVertexWithUV(1, 1, 1, MaxU, MaxV);
        renderer.addVertexWithUV(1, 0, 1, MaxU, MinV);

        renderer.setBrightness(255);
        renderer.addVertexWithUV(0, 0, 0, MinU, MinV);
        renderer.addVertexWithUV(0, 1, 0, MinU, MaxV);
        renderer.addVertexWithUV(1, 1, 0, MaxU, MaxV);
        renderer.addVertexWithUV(1, 0, 0, MaxU, MinV);

        renderer.setBrightness(255);
        renderer.addVertexWithUV(0, 0, 1, MinU, MinV);
        renderer.addVertexWithUV(1, 0, 1, MinU, MaxV);
        renderer.addVertexWithUV(1, 1, 1, MaxU, MaxV);
        renderer.addVertexWithUV(0, 1, 1, MaxU, MinV);

        tessellator.draw();
        GL11.glPopMatrix();
    }
}