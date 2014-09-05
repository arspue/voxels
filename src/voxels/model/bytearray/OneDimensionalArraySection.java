/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package voxels.model.bytearray;

import java.util.Iterator;
import voxels.model.Section;
import voxels.voxel.*;

/**
 * better for streams?d
 * @author cibin
 */
public class OneDimensionalArraySection extends Section implements Iterable<ByteVoxel>{
    
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
            System.err.println("cuboid.contains(voxel) = false -> getArrayIndex(Voxel voxel) return -1 //TODO exceptions?");
            return -1;
        }
        
        int x = (int) (voxel.getX() - cuboid.getMinX());
        int y = (int) (voxel.getY() - cuboid.getMinY());
        int z = (int) (voxel.getZ() - cuboid.getMinZ());
        
        int xMltp = 1;
        int yMltp = (int) ( cuboid.getWidth() );
        int zMltp = (int) ( cuboid.getWidth() * cuboid.getHeight() );
        
        int index = x * xMltp + y * yMltp + z * zMltp;
        
//        //debug stuff
//        int numOfVoxels = (int) (cuboid.getWidth() * cuboid.getHeight() * cuboid.getDepth());
//        if (index > numOfVoxels - 1){
//            System.out.println("getArrayIndex(): something wrong, index > number of voxels - 1");
//            System.out.println("    voxel: " + voxel + ", index: " + index + ", maxInxex: " + (numOfVoxels-1));
//            return numOfVoxels - 1;
//        }
        return index;
    }

    public Voxel getVoxel(int index){
                
        Cuboid c = getCuboid();
        
        int xMltp = 1;
        int yMltp = (int) (c.getWidth());
        int zMltp = (int) (c.getWidth() * c.getHeight());
       
//        int xc = ((index % zMltp) % yMltp);
//        int yc = ((index - xc) % zMltp) / yMltp;
//        int zc = ((index - xc - (yc*yMltp))/zMltp);
//       
////        long z = c.getMinZ() + (pointer % zMltp);
////        long y = c.getMinY() + ((pointer / zMltp) % yMltp);
////        long x = c.getMinX() + (((pointer) / zMltp) / yMltp) % xMltp;
//        
        
        int xInC = ( (index % zMltp) % yMltp );
        int yInC =   (index % zMltp) / yMltp;
        int zInC =    index / zMltp;
        
        long x = c.getMinX() + xInC;
        long y = c.getMinY() + yInC;
        long z = c.getMinZ() + zInC;
        
        return new Voxel(x, y, z);
    }
    
    @Override
    public Iterator<ByteVoxel> iterator() {
        return new OneDimensionalArraySectionIterator(this);
    }
}

class OneDimensionalArraySectionIterator extends Section implements Iterator<ByteVoxel>{

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
        
        return (pointer < arraySize-1);
    }

    @Override
    public ByteVoxel next() {
        
//        
//        Cuboid c = section.getCuboid();
////        if (arraySize < 0){ 
////            arraySize = (int) (c.getWidth() * c.getHeight() * c.getDepth());
////        }
//        System.err.println("pointer: " + pointer);
//        
//        int xMltp = 1;
//        int yMltp = (int) (c.getWidth());
//        int zMltp = (int) (c.getWidth() * c.getHeight());
//       
//        int xc = ((pointer % zMltp) % yMltp);
//        int yc = ((pointer - xc) % zMltp) / yMltp;
//        int zc = ((pointer - xc - (yc*yMltp))/zMltp);
//       
////        long z = c.getMinZ() + (pointer % zMltp);
////        long y = c.getMinY() + ((pointer / zMltp) % yMltp);
////        long x = c.getMinX() + (((pointer) / zMltp) / yMltp) % xMltp;
//        long z = c.getMinZ() + zc;
//        long y = c.getMinY() + yc;
//        long x = c.getMinX() + xc;
//        
        
        Voxel voxel = section.getVoxel(pointer);
        
        return new ByteVoxel(voxel, section.data[pointer++]);
        
//        return new ByteVoxel(x, y, z, section.getVoxelValue(new Voxel(x, y, z)));
        
    }

}