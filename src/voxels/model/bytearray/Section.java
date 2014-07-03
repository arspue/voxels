/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package voxels.model.bytearray;

import voxels.voxel.Cuboid;

/**
 *
 * @author cibin
 */
public class Section {
    
    Cuboid cuboid = null;
    
    byte[][][] data = null;

    public Section(Cuboid cuboid, byte[][][] data) {
        this.cuboid = cuboid;
        this.data = data;
    }

    public Cuboid getCuboid() {
        return cuboid;
    }

    public void setCuboid(Cuboid cuboid) {
        this.cuboid = cuboid;
    }

    public byte[][][] getData() {
        return data;
    }

    public void setData(byte[][][] data) {
        this.data = data;
    }

    
    
}


