/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package voxels.display;

    import org.lwjgl.LWJGLException;
    import org.lwjgl.opengl.DisplayMode;
    import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;

/**
 *
 * @author cibin
 */
public class VoxelDisplay {

    public void start() {
            try {
                    Display.setDisplayMode(new DisplayMode(800,600));
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

                    Display.update();
            }

            Display.destroy();
    }

    private void initOpenGl() {
        GL11.glMatrixMode(GL11.GL_PROJECTION);
	GL11.glLoadIdentity();
	GL11.glOrtho(0, 800, 0, 600, 1, -1);
	GL11.glMatrixMode(GL11.GL_MODELVIEW);
    }

    private void renderOpenGl() {
        // Clear the screen and depth buffer
        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);

        // set the color of the quad (R,G,B,A)
        GL11.glColor3f(0.5f, 0.5f, 1.0f);

        // draw quad
        GL11.glBegin(GL11.GL_3D);
        GL11.glVertex3f(100, 100, -100);
        GL11.glVertex3f(100 + 200, 100, -100);
        GL11.glVertex3f(100 + 200, 100 + 200, -100);
        GL11.glVertex3f(100, 100 + 200, -100);
        GL11.glVertex3f(100, 100, -100 - 200);
        GL11.glVertex3f(100 + 200, 100, -100 - 200);
        GL11.glVertex3f(100 + 200, 100 + 200, -100 - 200);
        GL11.glVertex3f(100, 100 + 200, -100 - 200);
        GL11.glEnd();
    }

}
