package WayofTime.bloodmagic.api.teleport;

import java.io.Serializable;

import lombok.Getter;
import lombok.ToString;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.BlockPos;
import WayofTime.bloodmagic.api.Constants;

@ToString
public class PortalLocation implements Serializable
{
    @Getter
    private int x;
    @Getter
    private int y;
    @Getter
    private int z;
    @Getter
    private int dimension;

    public PortalLocation(int x, int y, int z, int dimension)
    {
        this.x = x;
        this.y = y;
        this.z = z;
        this.dimension = dimension;
    }

    public PortalLocation(BlockPos blockPos, int dimension)
    {
        this(blockPos.getX(), blockPos.getY(), blockPos.getZ(), dimension);
    }

    public static PortalLocation readFromNBT(NBTTagCompound tag)
    {
        if (tag.hasKey(Constants.NBT.PORTAL_LOCATION))
        {
            NBTTagCompound locationTag = tag.getCompoundTag(Constants.NBT.PORTAL_LOCATION);
            return new PortalLocation(locationTag.getInteger(Constants.NBT.X_COORD), locationTag.getInteger(Constants.NBT.Y_COORD), locationTag.getInteger(Constants.NBT.Z_COORD), locationTag.getInteger(Constants.NBT.DIMENSION_ID));
        }
        return null;
    }

    public NBTTagCompound writeToNBT(NBTTagCompound tag)
    {
        NBTTagCompound locationTag = new NBTTagCompound();

        locationTag.setInteger(Constants.NBT.X_COORD, x);
        locationTag.setInteger(Constants.NBT.Y_COORD, y);
        locationTag.setInteger(Constants.NBT.Z_COORD, z);
        locationTag.setInteger(Constants.NBT.DIMENSION_ID, dimension);
        tag.setTag(Constants.NBT.PORTAL_LOCATION, locationTag);

        return tag;
    }

    public BlockPos getBlockPos()
    {
        return new BlockPos(x, y, z);
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        PortalLocation that = (PortalLocation) o;

        if (x != that.x)
            return false;
        if (y != that.y)
            return false;
        return z == that.z;

    }

    @Override
    public int hashCode()
    {
        int result = x;
        result = 31 * result + y;
        result = 31 * result + z;
        return result;
    }
}
