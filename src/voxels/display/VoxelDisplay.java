/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package voxels.display;

import java.util.LinkedList;
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
    
    boolean rotate = true;
    int r = 0;
    
    boolean cmdForward = false;
    boolean cmdBack = false;
    
    public void start() {
        
        new RotationTimer().start();
        
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

    private void drawCube(Voxel v) {

        GL11.glColor3f(0.0f, 1.0f, 0.0f);          // Set The Color To Green
        GL11.glVertex3f(v.x + 1.0f, v.y + 1.0f, v.z);          // Top Right Of The Quad (Top)
        GL11.glVertex3f(v.x,        v.y + 1.0f, v.z);          // Top Left Of The Quad (Top)
        GL11.glVertex3f(v.x,        v.y + 1.0f, v.z + 1.0f);          // Bottom Left Of The Quad (Top)
        GL11.glVertex3f(v.x + 1.0f, v.y + 1.0f, v.z + 1.0f);          // Bottom Right Of The Quad (Top)

        GL11.glColor3f(1.0f, 0.5f, 0.0f);          // Set The Color To Orange
        GL11.glVertex3f(v.x + 1.0f, v.y, v.z + 1.0f);          // Top Right Of The Quad (Bottom)
        GL11.glVertex3f(v.x,        v.y, v.z + 1.0f);          // Top Left Of The Quad (Bottom)
        GL11.glVertex3f(v.x,        v.y, v.z);          // Bottom Left Of The Quad (Bottom)
        GL11.glVertex3f(v.x + 1.0f, v.y, v.z);          // Bottom Right Of The Quad (Bottom)

        GL11.glColor3f(1.0f, 0.0f, 0.0f);          // Set The Color To Red
        GL11.glVertex3f(v.x + 1.0f, v.y + 1.0f, v.z + 1.0f);          // Top Right Of The Quad (Front)
        GL11.glVertex3f(v.x,        v.y + 1.0f, v.z + 1.0f);          // Top Left Of The Quad (Front)
        GL11.glVertex3f(v.x,        v.y,        v.z + 1.0f);          // Bottom Left Of The Quad (Front)
        GL11.glVertex3f(v.x + 1.0f, v.y,        v.z + 1.0f);          // Bottom Right Of The Quad (Front)

        GL11.glColor3f(1.0f, 1.0f, 0.0f);          // Set The Color To Yellow
        GL11.glVertex3f(v.x + 1.0f, v.y,        v.z);          // Bottom Left Of The Quad (Back)
        GL11.glVertex3f(v.x,        v.y,        v.z);          // Bottom Right Of The Quad (Back)
        GL11.glVertex3f(v.x,        v.y + 1.0f, v.z);          // Top Right Of The Quad (Back)
        GL11.glVertex3f(v.x + 1.0f, v.y + 1.0f, v.z);          // Top Left Of The Quad (Back)

        GL11.glColor3f(0.0f, 0.0f, 1.0f);          // Set The Color To Blue
        GL11.glVertex3f(v.x, v.y + 1.0f, v.z + 1.0f);          // Top Right Of The Quad (Left)
        GL11.glVertex3f(v.x, v.y + 1.0f, v.z);          // Top Left Of The Quad (Left)
        GL11.glVertex3f(v.x, v.y,        v.z);          // Bottom Left Of The Quad (Left)
        GL11.glVertex3f(v.x, v.y,        v.z + 1.0f);          // Bottom Right Of The Quad (Left)

        GL11.glColor3f(1.0f, 0.0f, 1.0f);             // Set The Color To Violet
        GL11.glVertex3f(v.x + 1.0f, v.y + 1.0f, v.z);         // Top Right Of The Quad (Right)
        GL11.glVertex3f(v.x + 1.0f, v.y + 1.0f, v.z + 1.0f);         // Top Left Of The Quad (Right)
        GL11.glVertex3f(v.x + 1.0f, v.y,        v.z + 1.0f);         // Bottom Left Of The Quad (Right)
        GL11.glVertex3f(v.x + 1.0f, v.y,        v.z);         // Bottom Right Of The Quad (Right)
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
        Display.sync(240);
        //poll for keypresses first, default key is 'forward'
        //if(Keyboard.isKeyDown(Keyboard.KEY_NUMPAD8));

        GL11.glScalef(0.02f, 0.02f, 0.02f);

        GL11.glClear(GL11.GL_DEPTH_BUFFER_BIT|GL11.GL_COLOR_BUFFER_BIT);
        GL11.glEnable(GL11.GL_CULL_FACE|GL11.GL_DEPTH_TEST);
//        GL11.glColor3f(1, 0, 0);
        GL11.glRotatef(r, 1, 1, 1);
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
        
        drawLine(new Voxel(10, 0, 0), new Voxel(-10, 0, 0));
        drawLine(new Voxel(0, 10, 0), new Voxel(0, -10, 0));
        drawLine(new Voxel(0, 0, 10), new Voxel(0, 0, -10));
        
        
        drawLine(new Voxel(-5+2, -5+2, -5), new Voxel(5+2, 5+2, 5));
        drawLine(new Voxel(-5-2, -5-2, -5), new Voxel(5-2, 5-2, 5));
        
        GL11.glEnd();
        GL11.glLoadIdentity();

    }
    
    public void drawLine(Voxel a, Voxel b){
        LinkedList<Voxel> vl = Graphics.drawLine(a, b);
//        vl = Graphics.drawLine(new Voxel(0, 0, 0), new Voxel(5, 5, 5));
//        
        for (Voxel voxel : vl) {
            drawCube(voxel);
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
		    System.out.println("A Key Pressed");
		}
		if (Keyboard.getEventKey() == Keyboard.KEY_S) {
                    cmdBack = true;
		    System.out.println("S Key Pressed");
                    
		}
		if (Keyboard.getEventKey() == Keyboard.KEY_D) {
		    System.out.println("D Key Pressed");
		}
	    } else {
	        if (Keyboard.getEventKey() == Keyboard.KEY_W) {
                    cmdForward = false;
		    System.out.println("W Key Released");
	        }
	        if (Keyboard.getEventKey() == Keyboard.KEY_A) {
		    System.out.println("A Key Released");
	        }
	    	if (Keyboard.getEventKey() == Keyboard.KEY_S) {
                    cmdBack = false;
		    System.out.println("S Key Released");
		}
		if (Keyboard.getEventKey() == Keyboard.KEY_D) {
		    System.out.println("D Key Released");
		}
	    }
	}
    }
    
}
