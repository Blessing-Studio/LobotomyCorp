package com.xilu.lobotomycorp.events;

import com.xilu.lobotomycorp.handler.CapabilityHandler;
import com.xilu.lobotomycorp.interfaces.IMentality;
import com.xilu.lobotomycorp.util.SpaceUtil;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.ParticleManager;
import net.minecraft.client.renderer.EntityRenderer;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.entity.Entity;
import net.minecraft.entity.passive.EntityPig;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

import java.util.ArrayList;
import java.util.List;

@Mod.EventBusSubscriber
public class SPChangeEvent{
    static List<Entity> foreached = new ArrayList<Entity>();
    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {

        if (event.phase == TickEvent.Phase.START) {
            for (Entity e:event.player.world.loadedEntityList) {
                if(e instanceof EntityPig && !foreached.contains(e)){
                    try {

                        BlockPos playerPos =  event.player.getPosition();
                        Vec3d playerLookDirection =  event.player.getLookVec();
                        Vec3d entityPosition = e.getPositionVector();
                        Vec3d entityToPlayer = event.player.getPositionVector().subtract(entityPosition);
                        double angle = playerLookDirection.dotProduct(entityToPlayer.normalize());
                        float fovs = Minecraft.getMinecraft().gameSettings.fovSetting;
                        double cosine = Math.cos(Math.toRadians(100));

                        boolean inSight = angle <= cosine;
                        if (angle * cosine<0){
                            inSight = false;
                        }
                        if(inSight){
                            double distan = SpaceUtil.getDistance(event.player.getPosition(),e.getPosition());
                            boolean tellif = false;
                            if(fovs>=90&&fovs<=110&&distan<=45){
                                tellif = true;
                            }
                            else if(fovs>=30&&fovs<=50&&distan<=35)
                            {
                                tellif = true;
                            }
                            else if(distan<=40){
                                tellif = true;
                            }
                            if(tellif){
                                tellif=event.player.world.getLight(event.player.getPosition())>=8;
                            }
                            if(tellif){
                                try{
                                    Vec3d direction = new Vec3d(((int)e.posX - playerPos.getX()),
                                            ((int)e.posY - playerPos.getY()), ((int)e.posZ - playerPos.getZ())).normalize();
                                    Vec3d start =event.player.getPositionEyes(1.0F);
                                    Vec3d end = start.add(direction.scale(distan));
                                    RayTraceResult result = event.player.world.rayTraceBlocks(start, end);
                                    if(result!=null&& result.typeOfHit == RayTraceResult.Type.BLOCK){
                                        Block b = event.player.world.getBlockState(result.getBlockPos()).getBlock();
                                        if(b== Blocks.AIR){
                                            IMentality mentality = event.player.getCapability(CapabilityHandler.capMentality, null);
                                            float lastvalue= mentality.getMentalityValue();
                                            mentality.setMentalityValue(lastvalue-0.5f);
                                        }
                                    }
                                    else{
                                        IMentality mentality = event.player.getCapability(CapabilityHandler.capMentality, null);
                                        float lastvalue= mentality.getMentalityValue();
                                        mentality.setMentalityValue(lastvalue-0.5f);
                                    }
                                }
                                catch (Exception ex){

                                }
                            }
                        }
                        foreached.add(e);
                    }
                    catch (Exception ex){
                        //String msg = ex.getMessage()+" " +ex.getStackTrace();
                        //event.player.sendMessage(new TextComponentString(msg));
                    }
                }
            }
        }
    }
    //}
    public static void register(){
        MinecraftForge.EVENT_BUS.register(new SPChangeEvent());
    }
}