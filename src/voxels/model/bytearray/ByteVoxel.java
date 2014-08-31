/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package voxels.model.bytearray;

import voxels.voxel.Voxel;
/**
 *
 * @author cibin
 */
public class ByteVoxel extends Voxel{

    byte value = 0;
    
    public ByteVoxel(long x, long y, long z) {
        super(x, y, z);
    }
    
    public ByteVoxel(long x, long y, long z, byte value) {
        super(x, y, z);
        this.value = value;
    }

    public byte getValue() {
        return value;
    }

    public void setValue(byte value) {
        this.value = value;
    }
    
}
