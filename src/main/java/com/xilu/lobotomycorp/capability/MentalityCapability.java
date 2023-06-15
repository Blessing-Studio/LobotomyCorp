package com.xilu.lobotomycorp.capability;

import com.xilu.lobotomycorp.handler.CapabilityHandler;
import com.xilu.lobotomycorp.interfaces.IMentality;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class MentalityCapability {
    //Storage类
    public static class Storage implements Capability.IStorage<IMentality>{
        @Nullable
        @Override
        public NBTBase writeNBT(Capability<IMentality> capability, IMentality instance, EnumFacing side) {
            //创建一个新的NBT标签
            NBTTagCompound compound = new NBTTagCompound();
            //将清醒度值存入NBT的“consciousness”标签中
            compound.setDouble("consciousness", instance.getMentalityValue());
            //将该NBT标签返回上级代码
            return compound;
        }

        @Override
        public void readNBT(Capability<IMentality> capability, IMentality instance, EnumFacing side, NBTBase nbt) {
            //将传入的NBTBase强制转换为NBTTagCompound类型方便处理
            NBTTagCompound compound =(NBTTagCompound) nbt;
            //设置存储读取数据变量的初值防止报错
            double conV = 20D;
            //判断是否有所需要的标签
            if(compound.hasKey("consciousness")) {
                //读取标签
                conV = compound.getDouble("consciousness");
            }
            //调用数据接口类中的方法将读取的数据设置进自定义的数据结构中
            instance.setMentalityValue(conV);
        }
    }

    public static class Implementation implements IMentality{
        private double conV = 20D;//默认初始理智值，也是最大理智值

        @Override
        public double getMentalityValue() {
            return this.conV;
        }

        @Override
        public void setMentalityValue(double conV) {
            this.conV = conV;
        }
    }

    public static class ProvidePlayer implements ICapabilitySerializable<NBTTagCompound>, ICapabilityProvider {
        //创建一个已经默认实现了的consciousness实例
        private IMentality consciousness = new Implementation();
        //获取该Capability的Storage结构
        private Capability.IStorage<IMentality> storage = CapabilityHandler.capConsciousness.getStorage();

        //判断目前Forge在该游戏对象上检测到的Capability是否为该Capability
        @Override
        public boolean hasCapability(@Nonnull Capability<?> capability, @Nullable EnumFacing facing) {
            return CapabilityHandler.capConsciousness.equals(capability);
        }

        //获取该游戏对象的Capability
        @Nullable
        @Override
        public <T> T getCapability(@Nonnull Capability<T> capability, @Nullable EnumFacing facing) {
            //判断获取到的Capability是否为该Capability
            if(CapabilityHandler.capConsciousness.equals(capability)){
                @SuppressWarnings("unchecked")
                //将consciousness变量强制转为一个通用泛型并返回（玄学代码，如果理解不了可以暂时留着，但要求会用）
                T result = (T) consciousness;
                return result;
            }
            return null;
        }

        //将Capability中的IConsiousness数据结构序列化为一个NBT
        @Override
        public NBTTagCompound serializeNBT() {
            //新建一个NBTTagCompound标签
            NBTTagCompound compound = new NBTTagCompound();
            //从该Capability的Storage中取出consciousness数据结构并将其写入compound的“consciousness”标签中
            compound.setTag("consciousness", storage.writeNBT(CapabilityHandler.capConsciousness, consciousness, null));
            //将处理好的NBT标签传回上层函数做进一步处理
            return compound;
        }

        //从NBT中读取IConsciousness类型的数据并将其放入consciousness变量中
        @Override
        public void deserializeNBT(NBTTagCompound nbt) {
            //取出NBT中标签为"consciousness"的对应数据
            NBTTagCompound compound = nbt.getCompoundTag("consciousness");
            //从compound中读取出Capability数据并将其放入consciousness变量中
            storage.readNBT(CapabilityHandler.capConsciousness, consciousness, null, compound);
        }
    }
}
