/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package voxels.voxel;

/**
 * 
 * @author cibin
 */
public class Voxel {

    public long x = 0L;
    public long y = 0L;
    public long z = 0L;

    public Voxel(long x, long y, long z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public long getX() {
        return x;
    }

    public void setX(long x) {
        this.x = x;
    }

    public long getY() {
        return y;
    }

    public void setY(long y) {
        this.y = y;
    }

    public long getZ() {
        return z;
    }

    public void setZ(long z) {
        this.z = z;
    }
    
    @Override
    protected Object clone() throws CloneNotSupportedException {
        return new Voxel(x, y, z);
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Voxel){
            Voxel v = (Voxel) o;
            return (x == v.x && y == v.y && z == v.z);
        }
        return false;
    }

    @Override
    public String toString() {
        return "x: " + x + ", y: " + y + ", z: " + z; 
    }
    
    
    
}
