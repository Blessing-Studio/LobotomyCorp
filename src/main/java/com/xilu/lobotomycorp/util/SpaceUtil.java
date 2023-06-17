package com.xilu.lobotomycorp.util;

import net.minecraft.util.math.BlockPos;

public class SpaceUtil {
    public static BlockPos GetPositionFromX(BlockPos start,BlockPos end,double X)
    {
        double deltaX = end.getX() - start.getX();
        double deltaY = end.getY() - start.getY();
        double deltaZ = end.getZ() - start.getZ();

        double tangentY = deltaY / deltaX;
        double tangentZ = deltaZ / deltaX;

        double finalY = tangentY*(X-start.getX())+start.getY();
        double finalZ = tangentZ*(X-start.getX())+start.getZ();
        return new BlockPos(X,finalY,finalZ);
    }

    public static double getDistance(BlockPos start,BlockPos end){
        double distance = Math.sqrt(
                (end.getX()-start.getX()) *(end.getX()-start.getX()) +
                (end.getY()-start.getY()) * (end.getY()-start.getY()) +
                (end.getZ()-start.getZ()) * (end.getZ()-start.getZ()));
        return distance;
    }
}
