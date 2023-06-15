package com.xilu.lobotomycorp.blocks.blockBasic;

import java.util.Random;

import com.xilu.lobotomycorp.blocks.BlockBase;
import com.xilu.lobotomycorp.blocks.tileEntity.orbs.TileEntityNullifyOrb;
import com.xilu.lobotomycorp.blocks.tileEntity.orbs.TileEntityNullifyOrbMor;
import com.xilu.lobotomycorp.init.ModCreativeTab;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockNullifyOrb extends BlockBase implements ITileEntityProvider {

	public boolean isMoroonProof = false;
	public BlockNullifyOrb(String name, Material material) {
		super(name, material);
		this.hasTileEntity = true;
		setSoundType(SoundType.METAL);
		setHardness(5.0F);
		setResistance(15.0F);
		setHarvestLevel("pickaxe", 3);
		setLightOpacity(1);
		setCreativeTab(ModCreativeTab.LC_EGO_WEAPON);
	}

	public BlockNullifyOrb setAdvanced(boolean val)
	{
		isMoroonProof = val;
		return this;
	}


	//optional
	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune)
	{
		return super.getItemDropped(state, rand, fortune);
	}
	
	@Override
	public int quantityDropped(Random rand) {
		return super.quantityDropped(rand);
	}

	/**
	 * Returns a new instance of a block's tile entity class. Called on placing the block.
	 */
	public TileEntity createNewTileEntity(World worldIn, int meta)
	{
		if (isMoroonProof)
		{
			return new TileEntityNullifyOrbMor();
		}else {
			return new TileEntityNullifyOrb();
		}
	}


}
