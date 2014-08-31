/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package voxels.model.bytearray;

import java.util.Iterator;
import voxels.voxel.*;

/**
 * better for streams?d
 * @author cibin
 */
public class OneDimensionalArraySection implements Iterable<ByteVoxel>{
    
    Cuboid cuboid = null;
    
    byte[] data = null;

    public Cuboid getCuboid() {
        return cuboid;
    }

    public void setCuboid(Cuboid cuboid) {
        this.cuboid = cuboid;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }
    
    public void storeVoxelValue(Voxel voxel, byte value){
        if (cuboid == null){
            System.err.println("no cuboid");
            return;
        }
        
        if (data == null){
            data = new byte[(int) cuboid.getHeight() * (int) cuboid.getWidth() * (int) cuboid.getDepth()];
        }
        
        data[getArrayIndex(voxel)] = value;
    }
    
    public byte getVoxelValue(Voxel voxel){
        //TODO: exceptions?
        if (cuboid == null){
            System.err.println("no cuboid");
            return 0;
        }
        
        if (data == null){
            System.err.println("no data");
            return 0;
        }
        
        return data[getArrayIndex(voxel)];
        
    }
    
    public int getArrayIndex(Voxel voxel){
        if (!cuboid.contains(voxel)){
            System.err.println("out of range");
            return -1;
        }
        
        int x = (int) (voxel.getX() - cuboid.getMinX());
        int y = (int) (voxel.getY() - cuboid.getMinY());
        int z = (int) (voxel.getZ() - cuboid.getMinZ());
        
        int xMltp = 1;
        int yMltp = (int) (cuboid.getWidth());
        int zMltp = (int) (cuboid.getWidth() * cuboid.getHeight());
        
        return x * xMltp + y * yMltp + z * zMltp;
    }

    @Override
    public Iterator<ByteVoxel> iterator() {
        return new OneDimensionalArraySectionIterator(this);
    }
}

class OneDimensionalArraySectionIterator implements Iterator<ByteVoxel>{

    OneDimensionalArraySection section = null;

    int arraySize = -1;
    int pointer = 0;
    
    public OneDimensionalArraySectionIterator(OneDimensionalArraySection section) {
        this.section = section;
    }

    @Override
    public boolean hasNext() {
        Cuboid c = section.getCuboid();
        if (arraySize < 0){
            arraySize = (int) (c.getWidth() * c.getHeight() * c.getDepth());
        }
        
        return (pointer < arraySize);
    }

    @Override
    public ByteVoxel next() {
        
        pointer++;
        
        Cuboid c = section.getCuboid();
//        if (arraySize < 0){ 
//            arraySize = (int) (c.getWidth() * c.getHeight() * c.getDepth());
//        }
        
        int xMltp = 1;
        int yMltp = (int) (c.getWidth());
        int zMltp = (int) (c.getWidth() * c.getHeight());
       
        int xc = ((pointer % zMltp) % yMltp);
        int yc = ((pointer - xc) % zMltp) / yMltp;
        int zc = ((pointer - xc - (yc*yMltp))/zMltp);
       
//        long z = c.getMinZ() + (pointer % zMltp);
//        long y = c.getMinY() + ((pointer / zMltp) % yMltp);
//        long x = c.getMinX() + (((pointer) / zMltp) / yMltp) % xMltp;
        long z = c.getMinZ() + zc;
        long y = c.getMinY() + yc;
        long x = c.getMinX() + xc;
        
        return new ByteVoxel(x, y, z, section.data[pointer-1]);
        
    }

}