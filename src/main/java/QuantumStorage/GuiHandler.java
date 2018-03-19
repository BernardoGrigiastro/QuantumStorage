package QuantumStorage;

import QuantumStorage.client.AdvancedGui;
import QuantumStorage.inventory.AdvancedContainer;
import QuantumStorage.multiblock.ContainerMultiBlockCrate;
import QuantumStorage.multiblock.GuiMultiBlockCrate;
import QuantumStorage.multiblock.MultiBlockCrate;
import QuantumStorage.multiblock.TileCrate;
import QuantumStorage.tiles.AdvancedTileEntity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

/**
 * Created by Gigabit101 on 27/01/2017.
 */
public class GuiHandler implements IGuiHandler
{
    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
    {
        if (getMultiBlock(world, x, y, z) != null)
        {
            return new ContainerMultiBlockCrate(player, getMultiBlock(world, x, y, z), world.getTileEntity(new BlockPos(x, y, z)));
        }
        else if (world.getTileEntity(new BlockPos(x, y, z)) != null && world.getTileEntity(new BlockPos(x, y, z)) instanceof AdvancedTileEntity)
        {
            AdvancedTileEntity machine = (AdvancedTileEntity) world.getTileEntity(new BlockPos(x, y, z));
            return new AdvancedContainer(player, machine);
        }
        return null;
    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
    {
        if (getMultiBlock(world, x, y, z) != null)
        {
            return new GuiMultiBlockCrate(player, getMultiBlock(world, x, y, z), new BlockPos(x, y, z));
        }
        else if (world.getTileEntity(new BlockPos(x, y, z)) != null && world.getTileEntity(new BlockPos(x, y, z)) instanceof AdvancedTileEntity)
        {
            AdvancedTileEntity machine = (AdvancedTileEntity) world.getTileEntity(new BlockPos(x, y, z));
            return new AdvancedGui(player, machine);
        }
        return null;
    }

    public MultiBlockCrate getMultiBlock(World world, int x, int y, int z)
    {
        TileEntity tileEntity = world.getTileEntity(new BlockPos(x, y, z));
        if (tileEntity instanceof TileCrate)
        {
            return (MultiBlockCrate) ((TileCrate) tileEntity).getMultiblockController();
        }
        return null;
    }
}
