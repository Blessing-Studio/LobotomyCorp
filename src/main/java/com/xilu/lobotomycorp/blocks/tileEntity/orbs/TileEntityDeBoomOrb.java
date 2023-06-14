package com.xilu.lobotomycorp.blocks.tileEntity.orbs;

import com.xilu.lobotomycorp.LobotomyCorp;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.event.world.ExplosionEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

//"Tonation Orb" de-detonation orb
public class TileEntityDeBoomOrb extends TileEntityOrbBase implements ITickable {

	@SubscribeEvent
	public void onExplode(ExplosionEvent.Start event) {
		Vec3d pos = event.getExplosion().getPosition();
		//LobotomyCorp.Log(String.format("onExplode:(%s,%s,%s)", pos.x, pos.y, pos.z));
		//can use some optimization here. each orb will make it worse.

		if (!event.isCanceled() && aabb.contains(pos))
		{
			event.setCanceled(true);
			PlaySoundHere();
			LobotomyCorp.Log("Stopped an explosion");
		}
	}

	static
	{
		register("lobotomycorp:deboom_orb_basic", TileEntityDeBoomOrb.class);
	}
}
