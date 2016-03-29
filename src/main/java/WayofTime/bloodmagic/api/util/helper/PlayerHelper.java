package WayofTime.bloodmagic.api.util.helper;

import WayofTime.bloodmagic.api.Constants;

import com.google.common.base.Strings;
import com.google.common.collect.Lists;

import WayofTime.bloodmagic.ConfigHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.server.MinecraftServer;
import net.minecraftforge.common.UsernameCache;
import net.minecraftforge.common.util.FakePlayer;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.relauncher.Side;

import java.util.ArrayList;
import java.util.UUID;

public class PlayerHelper
{
    /**
     * A list of all known fake players that do not extend FakePlayer.
     * 
     * Will be added to as needed.
     */
    private static final ArrayList<String> knownFakePlayers = Lists.newArrayList();

    public static String getUsernameFromPlayer(EntityPlayer player)
    {
        return player.worldObj.isRemote ? "" : UsernameCache.getLastKnownUsername(getUUIDFromPlayer(player));
    }

    public static EntityPlayer getPlayerFromUsername(String username)
    {
        if (FMLCommonHandler.instance().getEffectiveSide() == Side.CLIENT)
            return null;

        return FMLCommonHandler.instance().getMinecraftServerInstance().getPlayerList().getPlayerByUsername(username);
    }

    public static EntityPlayer getPlayerFromUUID(String uuid)
    {
        return getPlayerFromUsername(getUsernameFromUUID(uuid));
    }

    public static EntityPlayer getPlayerFromUUID(UUID uuid)
    {
        return getPlayerFromUsername(getUsernameFromUUID(uuid));
    }

    public static UUID getUUIDFromPlayer(EntityPlayer player)
    {
        return player.getGameProfile().getId();
    }

    public static String getUsernameFromUUID(String uuid)
    {
        return UsernameCache.getLastKnownUsername(UUID.fromString(uuid));
    }

    public static String getUsernameFromUUID(UUID uuid)
    {
        return UsernameCache.getLastKnownUsername(uuid);
    }

    public static String getUsernameFromStack(ItemStack stack)
    {
        stack = NBTHelper.checkNBT(stack);

        return stack.getTagCompound().getString(Constants.NBT.OWNER_NAME);
    }

    public static boolean isFakePlayer(EntityPlayer player)
    {
        return player != null && (player instanceof FakePlayer || knownFakePlayers.contains(player.getClass().getCanonicalName()));
    }

    public static void causeNauseaToPlayer(ItemStack stack)
    {
        if (FMLCommonHandler.instance().getEffectiveSide() == Side.CLIENT)
            return;

        stack = NBTHelper.checkNBT(stack);

        if (!Strings.isNullOrEmpty(stack.getTagCompound().getString(Constants.NBT.OWNER_UUID)))
            causeNauseaToPlayer(stack.getTagCompound().getString(Constants.NBT.OWNER_UUID));
    }

    public static void causeNauseaToPlayer(String ownerName)
    {
        EntityPlayer player = getPlayerFromUsername(ownerName);

        if (player == null)
            return;

<<<<<<< HEAD
        player.addPotionEffect(new PotionEffect(MobEffects.confusion, 80));
=======
        PotionEffect effect = ConfigHandler.antiHitler ? new PotionEffect(Potion.weakness.getId(), 99, 127) : new PotionEffect(Potion.confusion.getId(), 99);
        player.addPotionEffect(effect);
>>>>>>> refs/remotes/WayofTime/1.8
    }
}
