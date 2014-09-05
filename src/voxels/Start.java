/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package voxels;

import voxel.tests.Tests;
import voxels.display.VoxelDisplay;

/**
 *
 * @author cibin
 */
public class Start {
    public static void main(String[] args) {
        if (!Tests.runTests()){
            return;
        }
        //temp entry point?
        VoxelDisplay vd = new VoxelDisplay();
                vd.start();
    }
}
