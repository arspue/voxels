/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package voxels.display;

import java.util.LinkedList;
import voxels.voxel.Voxel;

/**
 *
 * @author cibin
 */
public class Graphics {
    
    public static LinkedList<Voxel> drawLine(Voxel a, Voxel b){
        
        LinkedList<Voxel> voxels = new LinkedList<Voxel>();
        
        long x = Math.abs(a.x - b.x);
        long y = Math.abs(a.y - b.y);
        long z = Math.abs(a.z - b.z);
        
        if (x > y && x > z){
            double stY = (double)(a.y-b.y)/(double)(a.x-b.x);
            double stZ = (double)(a.z-b.z)/(double)(a.x-b.x);
            for (int i = 0; i < Math.abs(a.x-b.x); i++){
                Voxel v = new Voxel(
                    (a.x-b.x)+i,
                    Math.round((double)(a.y - b.y) + i * stY),
                    Math.round((double)(a.z - b.z) + i * stZ));
                voxels.add(v);
            }
            
        } else if (y > x && y > z){
            
        } else {
            
        }
        
        return voxels;
        
    }
    
    
    
    
}
