/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package voxels.display;

import java.util.LinkedList;
import voxels.Param;
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
            double yMltp = Math.abs((double)(a.y-b.y)/(double)(a.x-b.x));
            double zMltp = Math.abs((double)(a.z-b.z)/(double)(a.x-b.x));
            for (int i = 0; i < Math.abs(a.x-b.x); i++){
                Voxel v = new Voxel(
                    a.x + i *(a.x < b.x?1:-1),
                    Math.round((double) a.y + i * yMltp *(a.y < b.y?1:-1)),
                    Math.round((double) a.z + i * zMltp *(a.z < b.z?1:-1)) );
                voxels.add(v);
            }
        } else if (y > x && y > z){
            double xMltp = Math.abs((double)(a.x-b.x)/(double)(a.y-b.y));
            double zMltp = Math.abs((double)(a.z-b.z)/(double)(a.y-b.y));
            for (int i = 0; i < Math.abs(a.y-b.y); i++){
                Voxel v = new Voxel(
                    Math.round((double) a.x + i * xMltp *(a.x < b.x?1:-1)),
                    a.y + i *(a.y < b.y?1:-1),
                    Math.round((double) a.z + i * zMltp *(a.z < b.z?1:-1)) );
                voxels.add(v);
            }
        } else {
            double xMltp = Math.abs((double)(a.x-b.x)/(double)(a.z-b.z));
            double yMltp = Math.abs((double)(a.y-b.y)/(double)(a.z-b.z));
            for (int i = 0; i < Math.abs(a.z-b.z); i++){
                Voxel v = new Voxel(
                    Math.round((double) a.x + i * xMltp *(a.x < b.x?1:-1)),
                    Math.round((double) a.y + i * yMltp *(a.y < b.y?1:-1)),
                    a.z + i *(a.z < b.z?1:-1) );
                voxels.add(v);
            }
        }
        
        if (Param.debug){
            System.out.println("Draw line: " + a.x + " " + a.y + " " + a.z + " - " + b.x + " " + b.y + " " + b.z);
            for (Voxel voxel : voxels) {
                System.out.println(voxel.x + " " + voxel.y + " " + voxel.z);
            }
        }
        
        
        return voxels;
        
    }
    
}
