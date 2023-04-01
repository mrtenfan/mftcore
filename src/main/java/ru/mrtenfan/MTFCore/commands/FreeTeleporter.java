package ru.mrtenfan.MTFCore.commands;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.world.Teleporter;
import net.minecraft.world.WorldServer;

/**
 * @author AlexSocol, edited by mr_ten_fan
 */
public class FreeTeleporter extends Teleporter {

	WorldServer world;

	public FreeTeleporter(WorldServer worldIn) {
		super(worldIn);
		world = worldIn;
	}

	@Override
	public void placeInPortal(Entity entity, double x, double y, double z, float rotationYaw) {
		int ix = (int)entity.posX, iy = 60, iz = (int)entity.posZ;
		while(!(world.getBlock(ix, iy - 1, iz).isNormalCube() && world.getBlock(ix, iy, iz).isAir(world, ix, iy, iz) && world.getBlock(ix, iy, iz).isAir(world, ix, iy + 1, iz))) {
			iy += 1;
			System.out.println(iy);
			if(iy > 100) {
				iy = (int)entity.posY;
				world.setBlock(ix, iy - 1, iz, Blocks.cobblestone);
				world.setBlock(ix, iy, iz, Blocks.air);
				world.setBlock(ix, iy + 1, iz, Blocks.air);
				continue;
			}
		}
		entity.setPosition(ix + 0.5D, iy, iz + 0.5D);
	}

	@Override
	public boolean placeInExistingPortal(Entity entity, double x, double y, double z, float rotationYaw) {
		if (entity instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer) entity;
			if (player.capabilities.allowFlying) player.capabilities.isFlying = true;
		}
		return true;
	}
}