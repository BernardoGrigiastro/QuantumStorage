package net.gigabit101.quantumstorage.guis;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.gigabit101.quantumstorage.client.GuiBuilderQuantumStorage;
import net.gigabit101.quantumstorage.containers.ContainerChestIron;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.text.ITextComponent;

public class GuiChestIron extends ContainerScreen<ContainerChestIron>
{
    GuiBuilderQuantumStorage builder = new GuiBuilderQuantumStorage();

    public GuiChestIron(ContainerChestIron container, PlayerInventory playerinv, ITextComponent title)
    {
        super(container, playerinv, title);
        this.xSize = 190;
        this.ySize = 220;
    }

    //Render
    @Override
    protected void func_230450_a_(MatrixStack matrixStack, float p_230450_2_, int p_230450_3_, int p_230450_4_)
    {
        builder.drawDefaultBackground(this, matrixStack, guiLeft, guiTop, xSize, ySize, 256, 256);
        builder.drawPlayerSlots(this, matrixStack, guiLeft + xSize / 2, guiTop + 131, true, 256, 256);

        for (int l = 0; l < 4; ++l)
        {
            for (int j1 = 0; j1 < 9; ++j1)
            {
                builder.drawSlot(this, matrixStack, guiLeft + 14 + j1 * 18 - 1, guiTop + 18 + l * 18 - 1, 256, 256);
            }
        }
    }

    @Override
    protected void func_230451_b_(MatrixStack matrixStack, int mouseX, int mouseY)
    {
        this.field_230712_o_.func_238422_b_(matrixStack, this.field_230704_d_, 45.0F, 6.0F, 4210752);
        this.field_230712_o_.func_238422_b_(matrixStack, this.playerInventory.getDisplayName(), 14.0F, (float) (this.ySize - 100), 4210752);
    }

    @Override
    public void func_230430_a_(MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks)
    {
        this.func_230446_a_(matrixStack);
        super.func_230430_a_(matrixStack, mouseX, mouseY, partialTicks);
        this.func_230459_a_(matrixStack, mouseX, mouseY);
    }
}
