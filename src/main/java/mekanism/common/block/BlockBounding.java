package mekanism.common.block;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import mekanism.common.tile.TileEntityAdvancedBoundingBlock;
import mekanism.common.tile.TileEntityBoundingBlock;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

import java.util.Random;

public class BlockBounding extends Block
{
	public BlockBounding()
	{
		super(Material.iron);
		setHardness(3.5F);
		setResistance(8F);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister register) 
	{
		blockIcon = register.registerIcon(BlockBasic.ICON_BASE);
	}

	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer entityplayer, int facing, float playerX, float playerY, float playerZ)
	{
		try {
			TileEntityBoundingBlock tileEntity = (TileEntityBoundingBlock)world.getTileEntity(x, y, z);
			return world.getBlock(tileEntity.mainX, tileEntity.mainY, tileEntity.mainZ).onBlockActivated(world, tileEntity.mainX, tileEntity.mainY, tileEntity.mainZ, entityplayer, facing, playerX, playerY, playerZ);
		} catch(Exception e) {
			return false;
		}
	}

	@Override
    public ItemStack getPickBlock(MovingObjectPosition target, World world, int x, int y, int z, EntityPlayer player)
	{
		try {
			TileEntityBoundingBlock tileEntity = (TileEntityBoundingBlock)world.getTileEntity(x, y, z);
			return world.getBlock(tileEntity.mainX, tileEntity.mainY, tileEntity.mainZ).getPickBlock(target, world, tileEntity.mainX, tileEntity.mainY, tileEntity.mainZ, player);
		} catch(Exception e) {
			return null;
		}
	}

	@Override
	public boolean removedByPlayer(World world, EntityPlayer player, int x, int y, int z, boolean willHarvest)
	{
		try {
			TileEntityBoundingBlock tileEntity = (TileEntityBoundingBlock)world.getTileEntity(x, y, z);
			return world.getBlock(tileEntity.mainX, tileEntity.mainY, tileEntity.mainZ).removedByPlayer(world, player, tileEntity.mainX, tileEntity.mainY, tileEntity.mainZ, willHarvest);
		} catch(Exception e) {
			return false;
		}
	}

	@Override
	public void onNeighborBlockChange(World world, int x, int y, int z, Block block)
	{
		try {
			TileEntityBoundingBlock tileEntity = (TileEntityBoundingBlock)world.getTileEntity(x, y, z);
			tileEntity.onNeighborChange(block);
			world.getBlock(tileEntity.mainX, tileEntity.mainY, tileEntity.mainZ).onNeighborBlockChange(world, tileEntity.mainX, tileEntity.mainY, tileEntity.mainZ, this);
		} catch(Exception e) {}
	}

	@Override
	public int quantityDropped(Random random)
	{
		return 0;
	}

	@Override
	public Item getItemDropped(int i, Random random, int j)
	{
		return null;
	}

	@Override
	public int getRenderType()
	{
		return -1;
	}

	@Override
	public boolean isOpaqueCube()
	{
		return false;
	}

	@Override
	public boolean renderAsNormalBlock()
	{
		return false;
	}

	@Override
	public boolean hasTileEntity(int metadata)
	{
		return true;
	}

	@Override
	public TileEntity createTileEntity(World world, int metadata)
	{
		if(metadata == 0)
		{
			return new TileEntityBoundingBlock();
		}
		else if(metadata == 1)
		{
			return new TileEntityAdvancedBoundingBlock();
		}

		return null;
	}
}
