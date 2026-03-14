package fi.dy.masa.malilib.gui;

import net.minecraft.src.GuiButton;
import net.minecraft.src.Minecraft;
import org.lwjgl.opengl.GL11;

public class MenuIconButton extends GuiButton {

    public MenuIconButton(int id, int x, int y) {
        super(id, x, y, 20, 20, null);
    }

    @Override
    public void drawButton(Minecraft par1Minecraft, int par2, int par3) {
        if (this.drawButton) {
            par1Minecraft.getTextureManager().bindTexture(ManyLibIcons.MenuIcon.getTexture());
            GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
            this.field_82253_i = par2 >= this.xPosition && par3 >= this.yPosition
                    && par2 < this.xPosition + this.width && par3 < this.yPosition + this.height;
            ManyLibIcons.MenuIcon.renderAt(this.xPosition, this.yPosition, 0.0F,
                    this.drawButton, this.drawButton && this.field_82253_i);
            this.mouseDragged(par1Minecraft, par2, par3);
        }
    }
}