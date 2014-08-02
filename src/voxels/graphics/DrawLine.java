/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package voxels.graphics;

import voxels.voxel.Voxel;

/**
 *
 * @author cibin
 */
public class DrawLine {
    
    Voxel a = new Voxel(0, 0, 0);
    Voxel b = new Voxel(0, 0, 0);
    
    double xMultiplier = 0.0;
    double yMultiplier = 0.0;
    double zMultiplier = 0.0;
    
    public Voxel next(){
        return null;
    }
    
    public boolean hasNext(){
        return false;
    }
    
}
