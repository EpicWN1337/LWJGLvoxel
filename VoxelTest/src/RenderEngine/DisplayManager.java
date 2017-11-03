package RenderEngine;

import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.PixelFormat;

import game.MainGameLoop;

import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.ContextAttribs;
import org.lwjgl.opengl.Display;

public class DisplayManager {
	
	private static final int WIDTH = 1280;
	private static final int HEIGHT = 720;
	private static final int FPS_CAP = 120;
	
	public static void createDisplay() {
		
		ContextAttribs attribs = new ContextAttribs(3,2)
				.withForwardCompatible(true)
				.withProfileCore(true);
		
		try {
			Display.setDisplayMode(new DisplayMode(WIDTH, HEIGHT));
			Display.create(new PixelFormat(), attribs);
			Display.setTitle("Voxel Game");
			Display.setFullscreen(true);
			GL11.glViewport(0, 0, Display.getWidth(), Display.getHeight());
		} catch (LWJGLException e)	{
			e.printStackTrace();
		}
		Mouse.setGrabbed(true);
		
	}
	
	public static void updateDisplay() {
		
		Display.sync(FPS_CAP);
		Display.update();
		
		while(Keyboard.next()) {
			if(Keyboard.getEventKeyState()) {
				
				if (Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)){
					closeDisplay();
				}
				
				if(Keyboard.isKeyDown(Keyboard.KEY_E) && Mouse.isGrabbed()) {
					Mouse.setGrabbed(false);
				} else if(Keyboard.isKeyDown(Keyboard.KEY_E) && !Mouse.isGrabbed()) {
					Mouse.setGrabbed(true);
				}
			}
		}
		
	}
	
	public static void closeDisplay() {
		
		MainGameLoop.loader1.cleanUp();
		MainGameLoop.shader1.cleanUp();
		
		Display.destroy();
		System.exit(0);
		
	}

}
