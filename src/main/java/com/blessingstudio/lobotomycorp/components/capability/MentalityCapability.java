package com.blessingstudio.lobotomycorp.components.capability;

import com.blessingstudio.lobotomycorp.LobotomyCorp;
import com.blessingstudio.lobotomycorp.classes.interfaces.IMentality;
import com.blessingstudio.lobotomycorp.components.handler.CapabilityHandler;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class MentalityCapability {
    public static class Storage implements Capability.IStorage<IMentality> {
        @Nullable
        @Override
        public NBTBase writeNBT(Capability<IMentality> capability, IMentality instance, EnumFacing side) {
            NBTTagCompound compound = new NBTTagCompound();
            compound.setDouble("mentality", instance.getMentalityValue());
            return compound;
        }

        @Override
        public void readNBT(Capability<IMentality> capability, IMentality instance, EnumFacing side, NBTBase nbt) {
            NBTTagCompound compound =(NBTTagCompound) nbt;
            double mentalityValue = 0d;
            if(compound.hasKey("mentality")) {
                mentalityValue = compound.getDouble("mentality");
            }

            instance.setMentalityValue(mentalityValue);
        }
    }

    public static class Implementation implements IMentality {
        private static double mentalityValue = 20d;

        @Override
        public double getMentalityValue() {
            return this.mentalityValue;
        }

        @Override
        public void setMentalityValue(double mentValue) {
            LobotomyCorp.Log("设置是的旧精神值和新精神值" + mentalityValue + " - " + mentValue);
            this.mentalityValue = mentValue;
        }
    }

    public static class ProvidePlayer implements ICapabilitySerializable<NBTTagCompound>, ICapabilityProvider {
        //创建一个已经默认实现了的consciousness实例
        private IMentality mentality = new Implementation();
        //获取该Capability的Storage结构
        private Capability.IStorage<IMentality> storage = CapabilityHandler.mentalityCapability.getStorage();

        //判断目前Forge在该游戏对象上检测到的Capability是否为该Capability
        @Override
        public boolean hasCapability(@Nonnull Capability<?> capability, @Nullable EnumFacing facing) {
            return CapabilityHandler.mentalityCapability.equals(capability);
        }

        //获取该游戏对象的Capability
        @Nullable
        @Override
        public <T> T getCapability(@Nonnull Capability<T> capability, @Nullable EnumFacing facing) {
            if(CapabilityHandler.mentalityCapability.equals(capability)){
                @SuppressWarnings("unchecked")
                T result = (T) mentality;
                return result;
            }

            return null;
        }

        @Override
        public NBTTagCompound serializeNBT() {
            NBTTagCompound compound = new NBTTagCompound();
            compound.setTag("mentality", storage.writeNBT(CapabilityHandler.mentalityCapability, mentality, null));
            //将处理好的NBT标签传回上层函数做进一步处理
            return compound;
        }
        
        @Override
        public void deserializeNBT(NBTTagCompound nbt) {
            //取出NBT中标签为"consciousness"的对应数据
            NBTTagCompound compound = nbt.getCompoundTag("mentality");
            //从compound中读取出Capability数据并将其放入consciousness变量中
            storage.readNBT(CapabilityHandler.mentalityCapability, mentality, null, compound);
        }
    }
}