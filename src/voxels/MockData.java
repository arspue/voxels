/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package voxels;

import java.util.ArrayList;
import java.util.Random;
import voxels.model.bytearray.ByteVoxel;
import voxels.model.bytearray.OneDimensionalArraySection;
import voxels.voxel.Cuboid;
import voxels.voxel.Voxel;

/**
 *
 * @author cibin
 */
public class MockData {
    
    public static Random random = new Random();

    public static OneDimensionalArraySection section = MockData.getSection();
    
    public static ArrayList<Voxel> activeVoxels = null;

    public static ArrayList<Voxel> getActiveVoxels() {
        
        if (activeVoxels == null) {
            
            activeVoxels = new ArrayList<Voxel>();
            
            for (ByteVoxel v : section) {
                if (v.getValue() > 0) {
                    activeVoxels.add(new Voxel(v.x, v.y, v.z));
                }
            }
        }
        
        return activeVoxels;
    }
    
    
    
    public static OneDimensionalArraySection getSection(){
        if (section == null) {
            section = new OneDimensionalArraySection();
        
            Cuboid c = new Cuboid(new Voxel(-32, -32, -32), new Voxel(33, 33, 33));
            section.setCuboid(c);

            for (int x = -32; x < 32; x++) {
                for (int y = -32; y < 32; y++) {
                    for (int z = -32; z < 32; z++) {
                        if (random.nextDouble() < 0.01){
                            section.storeVoxelValue(new Voxel(x, y, z), (byte) 1);
                        } else {
                            section.storeVoxelValue(new Voxel(x, y, z), (byte) 0);
                        }
                    }
                }
            }
        }
        return section;
    }
}
