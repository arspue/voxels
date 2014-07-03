/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package voxels.voxel;

/**
 *
 * @author cibin
 */
public class Dimension {
    
    long width = 0;
    long height = 0;
    long depth = 0;

    public Dimension(long width, long height, long depth) {
        this.width = width;
        this.height = height;
        this.depth = depth;
    }
    
    public Dimension getSize(){
        try {
            return (Dimension) this.clone();
        } catch (CloneNotSupportedException ex) {
            return null;
        }
    }
    
    public void setSize(Dimension d){
        setSize(d.width, d.height, d.depth);
    }
    
    public void setSize(long width, long height, long depth){
        this.width = width;
        this.height = height;
        this.depth = depth;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        super.clone();
        return new Dimension(width, height, depth);
    }
    
}
