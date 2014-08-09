/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package voxels.display;

import java.awt.Color;
import java.util.LinkedList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
    import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
    import org.lwjgl.opengl.DisplayMode;
    import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.glu.GLU;
import voxels.voxel.Voxel;

/**
 *
 * @author cibin
 */
public class VoxelDisplay {

    DisplayMode displayMode;
    
    Random random = new Random();
    
    boolean rotate = true;
    int r = 0;
    
    boolean rotate2 = true;
    int r2 = 0;
    
    double d = 0.0;
    
    boolean cmdForward = false;
    boolean cmdBack = false;
    boolean cmdLeft = false;
    boolean cmdRight = false;
    
//    int x = 0;
//    int y = 0;
//    int z = 0;
    
    public void start() {
        
        
        startTimers();

        
            try {
                    Display.setDisplayMode(new DisplayMode(800,600));
                    Display.setTitle("voxels");
//                    Displa
                    Display.create();
            } catch (LWJGLException e) {
                    e.printStackTrace();
                    System.exit(0);
            }

            // init OpenGL here
            initOpenGl();

            while (!Display.isCloseRequested()) {

                    // render OpenGL here
                    renderOpenGl();

                    pollInput();
                    Display.update();
            }

            Display.destroy();
    }

    private Color getRedToGreen(double min, double max, double value){

        double val = value;
        if (value > max) {
            val = max;
        }
        if (value < min) {
            val = min;
        }
        
        double minFz = 0;
        double maxFz = max - min;
        double valFz = val - min;
        double v = valFz / maxFz;
        
        double r = 0;        
        double g = 0;        
        double b = 0;
        
        if (v > .5){
            r = 1 - (v - .5) * 2;
        } else {
            r = 1;
        }
        
        if (v < .5) {
            g = v * 2;
        } else {
            g = 1;
        }
        
        return new Color((float)r, (float)g, (float)b);
    }
    
    private float getRandom(){
        return random.nextFloat() - ((random.nextFloat()*8)%1)/8;
    }
    
    public void drawValueColoredVoxel(Voxel v, int min, int max){
        GL11.glColor3f(
                (float)getRedToGreen(-6, 6, v.z).getRed()/256,
                (float)getRedToGreen(-6, 6, v.z).getGreen()/256,
                0 );
        drawVoxel(v);
        
    }
    
    public void drawRandomColoredVoxel(Voxel v){
        GL11.glColor3f(getRandom(), getRandom(), getRandom());
        drawVoxel(v);
        
    }
    
    public void drawColoredVoxel(Voxel v, Color c){
        GL11.glColor3f(
                (float)c.getRed()/256,
                (float)c.getGreen()/256,
                (float)c.getBlue()/256
            );
        drawVoxel(v);
        
    }
            
    private void drawVoxel(Voxel v) {
        
//        GL11.glColor3f(getRandom(), getRandom(), getRandom());
        
//        GL11.glColor3f(0.0f, 1.0f, 0.0f);          // Set The Color To Green
        GL11.glVertex3f(v.x + 1.0f, v.y + 1.0f, v.z);          // Top Right Of The Quad (Top)
        GL11.glVertex3f(v.x,        v.y + 1.0f, v.z);          // Top Left Of The Quad (Top)
        GL11.glVertex3f(v.x,        v.y + 1.0f, v.z + 1.0f);          // Bottom Left Of The Quad (Top)
        GL11.glVertex3f(v.x + 1.0f, v.y + 1.0f, v.z + 1.0f);          // Bottom Right Of The Quad (Top)

//        GL11.glColor3f(1.0f, 0.5f, 0.0f);          // Set The Color To Orange
        GL11.glVertex3f(v.x + 1.0f, v.y, v.z + 1.0f);          // Top Right Of The Quad (Bottom)
        GL11.glVertex3f(v.x,        v.y, v.z + 1.0f);          // Top Left Of The Quad (Bottom)
        GL11.glVertex3f(v.x,        v.y, v.z);          // Bottom Left Of The Quad (Bottom)
        GL11.glVertex3f(v.x + 1.0f, v.y, v.z);          // Bottom Right Of The Quad (Bottom)

//        GL11.glColor3f(1.0f, 0.0f, 0.0f);          // Set The Color To Red
        GL11.glVertex3f(v.x + 1.0f, v.y + 1.0f, v.z + 1.0f);          // Top Right Of The Quad (Front)
        GL11.glVertex3f(v.x,        v.y + 1.0f, v.z + 1.0f);          // Top Left Of The Quad (Front)
        GL11.glVertex3f(v.x,        v.y,        v.z + 1.0f);          // Bottom Left Of The Quad (Front)
        GL11.glVertex3f(v.x + 1.0f, v.y,        v.z + 1.0f);          // Bottom Right Of The Quad (Front)

//        GL11.glColor3f(1.0f, 1.0f, 0.0f);          // Set The Color To Yellow
        GL11.glVertex3f(v.x + 1.0f, v.y,        v.z);          // Bottom Left Of The Quad (Back)
        GL11.glVertex3f(v.x,        v.y,        v.z);          // Bottom Right Of The Quad (Back)
        GL11.glVertex3f(v.x,        v.y + 1.0f, v.z);          // Top Right Of The Quad (Back)
        GL11.glVertex3f(v.x + 1.0f, v.y + 1.0f, v.z);          // Top Left Of The Quad (Back)

//        GL11.glColor3f(0.0f, 0.0f, 1.0f);          // Set The Color To Blue
        GL11.glVertex3f(v.x, v.y + 1.0f, v.z + 1.0f);          // Top Right Of The Quad (Left)
        GL11.glVertex3f(v.x, v.y + 1.0f, v.z);          // Top Left Of The Quad (Left)
        GL11.glVertex3f(v.x, v.y,        v.z);          // Bottom Left Of The Quad (Left)
        GL11.glVertex3f(v.x, v.y,        v.z + 1.0f);          // Bottom Right Of The Quad (Left)

//        GL11.glColor3f(1.0f, 0.0f, 1.0f);             // Set The Color To Violet
        GL11.glVertex3f(v.x + 1.0f, v.y + 1.0f, v.z);         // Top Right Of The Quad (Right)
        GL11.glVertex3f(v.x + 1.0f, v.y + 1.0f, v.z + 1.0f);         // Top Left Of The Quad (Right)
        GL11.glVertex3f(v.x + 1.0f, v.y,        v.z + 1.0f);         // Bottom Left Of The Quad (Right)
        GL11.glVertex3f(v.x + 1.0f, v.y,        v.z);         // Bottom Right Of The Quad (Right)
    }

    private void startTimers() {
        new RotationTimer().start();
        new RotationTimerLR().start();
        new DegreeTimer().start();
    }
    
    private class RotationTimer extends Thread implements Runnable{

        @Override
        public void run() {
            while (rotate){
                if (cmdForward) r++;
                if (cmdBack) r--;
                try {
                    Thread.sleep(10);
                } catch (InterruptedException ex) {
                    Logger.getLogger(VoxelDisplay.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }   
    }
    private class DegreeTimer extends Thread implements Runnable{

        @Override
        public void run() {
            while (true){
                d++;
                try {
                    Thread.sleep(10);
                } catch (InterruptedException ex) {
                    Logger.getLogger(VoxelDisplay.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }   
    }

        private class RotationTimerLR extends Thread implements Runnable{

        @Override
        public void run() {
            while (rotate2){
                if (cmdLeft) r2++;
                if (cmdRight) r2--;
                try {
                    Thread.sleep(10);
                } catch (InterruptedException ex) {
                    Logger.getLogger(VoxelDisplay.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }   
    }

    
    
    private void initOpenGl() {
//    GL11.glMatrixMode(GL11.GL_PROJECTION);
//    GL11.glLoadIdentity();
//    GL11.glOrtho(-5, 5, -5, 5, -1, 5);
//    GL11.glMatrixMode(GL11.GL_MODELVIEW);
    
        GL11.glEnable(GL11.GL_TEXTURE_2D); // Enable Texture Mapping
        GL11.glShadeModel(GL11.GL_SMOOTH); // Enable Smooth Shading
        GL11.glClearColor(0.0f, 0.0f, 0.0f, 0.0f); // Black Background
        GL11.glClearDepth(1.0); // Depth Buffer Setup
        GL11.glEnable(GL11.GL_DEPTH_TEST); // Enables Depth Testing
        GL11.glDepthFunc(GL11.GL_LEQUAL); // The Type Of Depth Testing To Do

        GL11.glMatrixMode(GL11.GL_PROJECTION); // Select The Projection Matrix
    }

    private void renderOpenGl() {
        Display.sync(30);
        //poll for keypresses first, default key is 'forward'
        //if(Keyboard.isKeyDown(Keyboard.KEY_NUMPAD8));

        GL11.glScalef(0.02f, 0.02f, 0.02f);

        GL11.glClear(GL11.GL_DEPTH_BUFFER_BIT|GL11.GL_COLOR_BUFFER_BIT);
        GL11.glEnable(GL11.GL_CULL_FACE|GL11.GL_DEPTH_TEST);
//        GL11.glColor3f(1, 0, 0);
        GL11.glRotatef(r2, 1, (float)Math.sin(Math.toRadians(r)), 1);
        GL11.glBegin(GL11.GL_QUADS);
     
//        drawCube(new Voxel(1, 2, 0));
//        drawCube(new Voxel(2, 1, 0));
//        drawCube(new Voxel(1, 0, 1));
//        drawCube(new Voxel(1, 1, 1));
//        drawCube(new Voxel(2, 4, 1));
//        drawCube(new Voxel(3, 1, 1));
//        drawCube(new Voxel(1, 1, 2));
//        drawCube(new Voxel(2, 4, 3));
//        drawCube(new Voxel(3, 1, 5));
        
//        drawLine(new Voxel(-10, 0, 0), new Voxel(10, 0, 0));
//        drawLine(new Voxel(0, -10, 0), new Voxel(0, 10, 0));
//        drawLine(new Voxel(0, 0, -10), new Voxel(0, 0, 10));

        
        for (int x = -32; x <= 32; x++){
            for(int y = -32; y <= 32; y++ ){
                int z =(int) Math.round(Math.sin(d*0.05+(((double)(x*y))/1024)*Math.PI/2)*8);
//                System.out.println(Math.sin((((double)(x*y))/256)*Math.PI)*8);
                drawValueColoredVoxel(new Voxel(x, y, z), -10, 10);
            }
        }
        
//        drawColoredLine(new Voxel(10, 20, 30), new Voxel(20, 10, 0), Color.MAGENTA);
        
        drawColoredLine(new Voxel(100, 0, 0), new Voxel(-100, 0, 0), Color.RED);
        drawColoredLine(new Voxel(0, 100, 0), new Voxel(0, -100, 0), Color.GREEN);
        drawColoredLine(new Voxel(0, 0, 100), new Voxel(0, 0, -100), Color.BLUE);
        
        
//        drawLine(new Voxel(-5+2, -5+2, -5), new Voxel(5+2, 5+2, 5));
//        drawLine(new Voxel(-5-2, -5-2, -5), new Voxel(5-2, 5-2, 5));
        
        GL11.glEnd();
        GL11.glLoadIdentity();
      
    }
    
    public void drawLine(Voxel a, Voxel b){
        LinkedList<Voxel> vl = Graphics.drawLine(a, b);
        for (Voxel voxel : vl) {
            drawColoredVoxel(voxel, Color.MAGENTA);
        }
    }
    
    public void drawColoredLine(Voxel a, Voxel b, Color c){
        LinkedList<Voxel> vl = Graphics.drawLine(a, b);
        for (Voxel voxel : vl) {
            drawColoredVoxel(voxel, c);
        }
    }

    public void pollInput() {
		
        if (Mouse.isButtonDown(0)) {
	    int x = Mouse.getX();
	    int y = Mouse.getY();
			
	    System.out.println("MOUSE DOWN @ X: " + x + " Y: " + y);
	}
		
	if (Keyboard.isKeyDown(Keyboard.KEY_SPACE)) {
	    System.out.println("SPACE KEY IS DOWN");
	}
		
	while (Keyboard.next()) {
	    if (Keyboard.getEventKeyState()) {
	        if (Keyboard.getEventKey() == Keyboard.KEY_W) {
                    cmdForward = true;
		    System.out.println("W Key Pressed");
		}
	        if (Keyboard.getEventKey() == Keyboard.KEY_A) {
                    cmdLeft = true;
		    System.out.println("A Key Pressed");
		}
		if (Keyboard.getEventKey() == Keyboard.KEY_S) {
                    cmdBack = true;
		    System.out.println("S Key Pressed");
                    
		}
		if (Keyboard.getEventKey() == Keyboard.KEY_D) {
                    cmdRight = true;
		    System.out.println("D Key Pressed");
		}
	    } else {
	        if (Keyboard.getEventKey() == Keyboard.KEY_W) {
                    cmdForward = false;
		    System.out.println("W Key Released");
	        }
	        if (Keyboard.getEventKey() == Keyboard.KEY_A) {
                    cmdLeft = false;
		    System.out.println("A Key Released");
	        }
	    	if (Keyboard.getEventKey() == Keyboard.KEY_S) {
                    cmdBack = false;
		    System.out.println("S Key Released");
		}
		if (Keyboard.getEventKey() == Keyboard.KEY_D) {
                    cmdRight = false;
		    System.out.println("D Key Released");
		}
	    }
	}
    }
    
}
