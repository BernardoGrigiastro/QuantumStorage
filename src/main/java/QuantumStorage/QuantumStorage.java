package QuantumStorage;

import QuantumStorage.client.RenderChest;
import QuantumStorage.compat.CompatHandler;
import QuantumStorage.config.ConfigQuantumStorage;
import QuantumStorage.init.ModBlocks;
import QuantumStorage.init.ModItems;
import QuantumStorage.init.ModRecipes;
import QuantumStorage.init.ModelHandler;
import QuantumStorage.proxy.CommonProxy;
import QuantumStorage.tiles.TileChestIron;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.relauncher.Side;

import java.io.File;

/**
 * Created by Gigabit101 on 27/01/2017.
 */
@Mod(name = QuantumStorage.MOD_NAME, modid = QuantumStorage.MOD_ID, version = QuantumStorage.MOD_VERSION, dependencies = QuantumStorage.MOD_DEPENDENCUIES)
public class QuantumStorage
{
    public static final String MOD_ID = "quantumstorage";
    public static final String MOD_NAME  = "QuantumStorage";
    public static final String MOD_VERSION = "@VERSION@";
    public static final String MOD_DEPENDENCUIES ="required-after:reborncore";

    public static ConfigQuantumStorage config;

    @Mod.Instance
    public static QuantumStorage INSTANCE;

    @SidedProxy(clientSide = "QuantumStorage.proxy.CommonProxy", serverSide = "QuantumStorage.proxy.ClientProxy")
    public static CommonProxy proxy;

    @Mod.EventHandler
    public void preinit(FMLPreInitializationEvent event)
    {
        INSTANCE = this;

        String path = event.getSuggestedConfigurationFile().getAbsolutePath().replace(QuantumStorage.MOD_ID, "QuantumStorage");
        config = ConfigQuantumStorage.init(new File(path));

        ModItems.init();
        ModBlocks.init();

        ModRecipes.init();
        CompatHandler.init();
        if(event.getSide() == Side.CLIENT)
        {
            ModelHandler.init();
            ClientRegistry.bindTileEntitySpecialRenderer(TileChestIron.class, new RenderChest());
//            ClientRegistry.bindTileEntitySpecialRenderer(TileQuantumStorageUnit.class, new RenderDsu());
//            ClientRegistry.bindTileEntitySpecialRenderer(TileQuantumBarrel.class, new RenderBarrel());
        }
        NetworkRegistry.INSTANCE.registerGuiHandler(INSTANCE, new GuiHandler());
    }
}
