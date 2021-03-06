package smalldeadguy.elementalcreepers;

import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;

public class EntityWaterCreeper extends EntityElementalCreeper {
	public EntityWaterCreeper(World world) {
		super(world);
//		texture = "/mob/watercreeper.png";
	}

	@Override
	public void creeperEffect() {
		double radius = getPowered() ? (int) (ElementalCreepers.waterCreeperRadius * 1.5) : ElementalCreepers.waterCreeperRadius;
		for(int x = (int) -radius - 1; x <= radius; x++)
			for(int y = (int) -radius - 1; y <= radius; y++)
				for(int z = (int) -radius - 1; z <= radius; z++)
					if(Blocks.water.canPlaceBlockAt(worldObj, (int) posX + x, (int) posY + y, (int) posZ + z) && Math.sqrt(Math.pow(x, 2) + Math.pow(y,  2) + Math.pow(z, 2)) <= radius)
						worldObj.setBlock((int) posX + x, (int) posY + y, (int) posZ + z, Blocks.flowing_water, 4, 3);
		worldObj.playSoundEffect(posX, posY, posZ, "random.explode", 4F, (1.0F + (worldObj.rand.nextFloat() - worldObj.rand.nextFloat()) * 0.2F) * 0.7F);
		spawnExplosionParticle();
	}
}