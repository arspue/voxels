/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package voxels;

import java.util.Random;
import voxels.model.bytearray.OneDimensionalArraySection;
import voxels.voxel.Cuboid;
import voxels.voxel.Voxel;

/**
 *
 * @author cibin
 */
public class MockData {
    
    static Random random = new Random();

    static OneDimensionalArraySection section = null;
    
    public static OneDimensionalArraySection getSection(){
        if (section == null) {
            section = new OneDimensionalArraySection();
        
            Cuboid c = new Cuboid(new Voxel(-32, -32, -32), new Voxel(32, 32, 32));
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
