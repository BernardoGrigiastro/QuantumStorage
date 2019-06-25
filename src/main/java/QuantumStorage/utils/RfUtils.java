//package QuantumStorage.utils;
//
//import net.minecraft.item.ItemStack;
//import net.minecraft.tileentity.TileEntity;
//import net.minecraft.util.Direction;
//import net.minecraft.util.math.BlockPos;
//import net.minecraft.world.World;
//import net.minecraftforge.common.capabilities.Capability;
//import net.minecraftforge.energy.CapabilityEnergy;
//import net.minecraftforge.energy.IEnergyStorage;
//
//import javax.annotation.Nullable;
//import java.text.DecimalFormat;
//import java.text.DecimalFormatSymbols;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Locale;
//
///**
// * Created by Gigabit101 on 01/07/2017.
// */
//public class RfUtils
//{
//    public static int transferPower(@Nullable IEnergyStorage source, @Nullable IEnergyStorage destination, int maxAmount, boolean simulate)
//    {
//        if (source == null || destination == null)
//            return 0;
//
//        int amount = source.extractEnergy(destination.receiveEnergy(maxAmount, true), true);
//
//        return destination.receiveEnergy(source.extractEnergy(amount, simulate), simulate);
//    }
//
////    public static <T> List<T> getConnectedCapabilities(Capability<T> capability, World world, BlockPos pos)
////    {
////        final List<T> capabilities = new ArrayList<>();
////
////        for (final Direction side : Direction.values())
////        {
////            final TileEntity tile = world.getTileEntity(pos.offset(side));
////            if (tile != null && tile.hasCapability(capability, side.getOpposite()))
////            {
////                capabilities.add(tile.getCapability(capability, side.getOpposite()));
////            }
////        }
////        return capabilities;
////    }
////
////    public static int distributePowerToFaces(IEnergyStorage source, World world, BlockPos pos, int amountPerFace, boolean simulated)
////    {
////        int consumedPower = 0;
////
////        for (Direction dir : Direction.values())
////        {
////            TileEntity tile = world.getTileEntity(pos.offset(dir));
////            if (tile != null && tile.hasCapability(CapabilityEnergy.ENERGY, dir.getOpposite()))
////            {
////                consumedPower += transferPower(source, tile.getCapability(CapabilityEnergy.ENERGY, dir.getOpposite()), amountPerFace, simulated);
////            }
////        }
////        return consumedPower;
////    }
////
////    public static int consumePowerFromFaces(IEnergyStorage source, World world, BlockPos pos, int amountPerFace, boolean simulated)
////    {
////        int receivedPower = 0;
////
////        for (Direction dir : Direction.values())
////        {
////            TileEntity tile = world.getTileEntity(pos.offset(dir));
////            if (tile != null && tile.hasCapability(CapabilityEnergy.ENERGY, dir.getOpposite()))
////            {
////                receivedPower += transferPower(tile.getCapability(CapabilityEnergy.ENERGY, dir.getOpposite()), source, amountPerFace, simulated);
////            }
////        }
////        return receivedPower;
////    }
////
////    //Item
////    public static boolean isPoweredItem(ItemStack stack)
////    {
////        return stack.hasCapability(CapabilityEnergy.ENERGY, null);
////    }
//
//    public static double getDurabilityForDisplay(ItemStack stack)
//    {
//        IEnergyStorage storage = (IEnergyStorage) stack.getCapability(CapabilityEnergy.ENERGY, null);
//        double max = storage.getMaxEnergyStored();
//        double diff = max - storage.getEnergyStored();
//        return diff / max;
//    }
//
//    //TODO move to RC
//    public static final DecimalFormat QUANTITY_FORMATTER = new DecimalFormat("####0.#", DecimalFormatSymbols.getInstance(Locale.US));
//
//    //TODO move to RC
//    public static String formatQuantity(int qty)
//    {
//        if (qty >= 1000000)
//        {
//            return QUANTITY_FORMATTER.format((float) qty / 1000000F) + "M";
//        } else if (qty >= 1000)
//        {
//            return QUANTITY_FORMATTER.format((float) qty / 1000F) + "K";
//        }
//        return String.valueOf(qty);
//    }
//
//    public static String addPowerTooltip(ItemStack stack)
//    {
//        IEnergyStorage storage = (IEnergyStorage) stack.getCapability(CapabilityEnergy.ENERGY, null);
//
//        return formatQuantity(storage.getEnergyStored()) + " / " + formatQuantity(storage.getMaxEnergyStored());
//    }
//
//    public static boolean isItemFull(ItemStack stack)
//    {
//        if (isPoweredItem(stack))
//        {
//            IEnergyStorage storage = (IEnergyStorage) stack.getCapability(CapabilityEnergy.ENERGY, null);
//            if (storage.getEnergyStored() != storage.getMaxEnergyStored())
//            {
//                return false;
//            } else return true;
//        }
//        return false;
//    }
//
//    //Untested
//    public static void chargeItem(ItemStack stack, IEnergyStorage storage)
//    {
//        if (isPoweredItem(stack) && !isItemFull(stack))
//        {
//            IEnergyStorage storageItem = (IEnergyStorage) stack.getCapability(CapabilityEnergy.ENERGY, null);
//
//            int amount2send = storageItem.receiveEnergy(1000, true);
//            if (storage.getEnergyStored() >= amount2send)
//            {
//                storageItem.receiveEnergy(amount2send, false);
//                storage.extractEnergy(amount2send, false);
//            }
//        }
//    }
//
//    public static int dischargeItem(ItemStack stack, int amount, boolean sim)
//    {
//        if (isPoweredItem(stack))
//        {
//            final IEnergyStorage storage = (IEnergyStorage) stack.getCapability(CapabilityEnergy.ENERGY, null);
//            return storage.extractEnergy(amount, sim);
//        }
//        return 0;
//    }
//}
