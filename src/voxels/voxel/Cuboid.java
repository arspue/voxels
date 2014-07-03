/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package voxels.voxel;

/**
 * @author cibin
 */
public class Cuboid{

    Voxel a = null;
    Voxel b = null;
    
    public Cuboid(Voxel a, Voxel b) {
        this.a = a;
        this.b = b;
    }

    public Cuboid(Voxel a, Dimension dimension) {
        long bx = a.x + dimension.width;
        long by = a.y + dimension.height;
        long bz = a.z + dimension.depth;
        
        this.a = a;
        this.b = new Voxel(bx, by, bz);
    }
    
    public Cuboid(long ax, long ay, long az, long bx, long by, long bz) {
        this.a = new Voxel(ax, ay, az);
        this.b = new Voxel(bx, by, bz);
    }
    
    public boolean contains(Voxel v){
        long minX = !(a.x > b.x)? a.x : b.x;
        long minY = !(a.y > b.y)? a.y : b.y;
        long minZ = !(a.z > b.z)? a.z : b.z; 
        long maxX = !(b.x < a.x)? b.x : a.x;
        long maxY = !(b.y < a.y)? b.y : a.y;
        long maxZ = !(b.z < a.z)? b.z : a.z;
        
        return (v.x >= minX && v.x <= maxX
             && v.y >= minY && v.y <= maxY
             && v.z >= minZ && v.z <= maxZ);
    }
    
    public Dimension getSize(){
        return new Dimension(b.x - a.x, b.y - a.y, b.z - a.z);
    }
    
}
