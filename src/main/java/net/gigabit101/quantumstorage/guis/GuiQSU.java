package net.gigabit101.quantumstorage.guis;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.gigabit101.quantumstorage.client.GuiBuilderQuantumStorage;
import net.gigabit101.quantumstorage.containers.ContainerQSU;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.items.IItemHandler;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

public class GuiQSU extends ContainerScreen<ContainerQSU>
{
    GuiBuilderQuantumStorage builder = new GuiBuilderQuantumStorage();

    int STORAGE = 1;
    int OUTPUT = 2;

    public GuiQSU(ContainerQSU container, PlayerInventory playerinv, ITextComponent title)
    {
        super(container, playerinv, title);
        this.xSize = 190;
        this.ySize = 220;
    }

    //Render
    @Override
    protected void func_230450_a_(MatrixStack matrixStack, float p_230450_2_, int mouseX, int mouseY)
    {
        builder.drawDefaultBackground(this, matrixStack, guiLeft, guiTop, xSize, ySize, 256, 256);
        builder.drawPlayerSlots(this, matrixStack, guiLeft + xSize / 2, guiTop + 131, true, 256, 256);

        builder.drawSlot(this, matrixStack, guiLeft + xSize / 2 - 9, guiTop + 30, 256, 256);
        builder.drawSlot(this, matrixStack, guiLeft + xSize / 2 - 9, guiTop + 80, 256, 256);
    }

    //RenderTop
    @Override
    protected void func_230451_b_(MatrixStack matrixStack, int mouseX, int mouseY)
    {
        this.field_230712_o_.func_238422_b_(matrixStack, this.field_230704_d_, 40.0F, 6.0F, 4210752);
        this.field_230712_o_.func_238422_b_(matrixStack, this.playerInventory.getDisplayName(), 14.0F, (float) (this.ySize - 100), 4210752);

        builder.drawBigBlueBar( this, matrixStack, 36, 56, this.getInv().getStackInSlot(STORAGE).getCount() + this.getInv().getStackInSlot(OUTPUT).getCount(), Integer.MAX_VALUE, mouseX - guiLeft, mouseY - guiTop, "Stored", getInv().getStackInSlot(OUTPUT).getDisplayName().getString(),
                formatQuantity(this.getInv().getStackInSlot(STORAGE).getCount() + this.getInv().getStackInSlot(OUTPUT).getCount()), 256, 256);
    }

    @Override
    public void func_230430_a_(MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks)
    {
        this.func_230446_a_(matrixStack);
        super.func_230430_a_(matrixStack, mouseX, mouseY, partialTicks);
        this.func_230459_a_(matrixStack, mouseX, mouseY);
    }

    public static final DecimalFormat QUANTITY_FORMATTER = new DecimalFormat("####0.#", DecimalFormatSymbols.getInstance(Locale.US));

    public static String formatQuantity(int qty)
    {
        if (qty >= 1000000)
        {
            return QUANTITY_FORMATTER.format((float) qty / 1000000F) + "M";
        }
        else if (qty >= 1000)
        {
            return QUANTITY_FORMATTER.format((float) qty / 1000F) + "K";
        }
        return String.valueOf(qty);
    }

    public IItemHandler getInv()
    {
        return container.getInv();
    }
}
