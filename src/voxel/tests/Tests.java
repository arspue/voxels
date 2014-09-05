/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//TODO cbn: do some real test like junit or something
package voxel.tests;

import java.util.Random;
import voxels.model.bytearray.OneDimensionalArraySection;
import voxels.voxel.Cuboid;
import voxels.voxel.Voxel;

/**
 * 
 * @author cibin
 */
public class Tests {
    public static boolean runTests() {
    if (!sectionIndex()){
        return false;
    }
    return true;
    }

    private static boolean sectionIndex() {
        
        Cuboid c = new Cuboid(new Voxel(0, 0, 0), new Voxel(7, 7, 7));
        
        int size =(int) (c.getWidth()*c.getHeight()*c.getDepth());
                
        
        int index = new Random().nextInt(size);
        
        OneDimensionalArraySection section = new OneDimensionalArraySection();
        section.setCuboid(c);
        byte[] data = new byte[size];
        section.setData(data);
        
        int index2 = section.getArrayIndex(section.getVoxel(index));
        
        boolean success = (index == index2);
        System.out.println("index test " + (success?"ok.":"failed!"));
        return success;
    }
    
}
