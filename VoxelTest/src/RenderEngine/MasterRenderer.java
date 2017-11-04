package RenderEngine;

import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.vector.Matrix4f;

import Entities.Entity;
import Shaders.StaticShader;

public class MasterRenderer {
	
	public MasterRenderer (StaticShader shader) {
		
		createProjectionMatrix();
		shader.start();
		shader.loadProjectionMatrix(projectionMatrix);
		shader.stop();
		
	}
	
	Matrix4f projectionMatrix;
	
	private static final float FOV = 90f;
	private static final float NEAR_PLANE = 0.01f;
	private static final float FAR_PLANE = 10000f;
	
	public void prepare() {
		
		
		GL11.glEnable(GL11.GL_DEPTH_TEST);
		GL11.glClearColor(0.4f, 0.7f, 1.0f, 1);
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
		
	}
	
	public void render(Entity entity, StaticShader shader) {
		
		EntityRenderer.render(entity, shader);
		
	}
	
	public void createProjectionMatrix() {
		
		projectionMatrix = new Matrix4f();
		
		float aspect = (float) Display.getWidth() / (float) Display.getHeight();
		float yScale = (float) (1f / Math.tan(Math.toRadians(FOV / 2f)));
		float xScale = yScale / aspect;
		float zp = FAR_PLANE + NEAR_PLANE;
		float zm = FAR_PLANE - NEAR_PLANE;
		
		projectionMatrix.m00 = xScale;
		projectionMatrix.m11 = yScale;
		projectionMatrix.m22 = -zp / zm;
		projectionMatrix.m23 = -1;
		projectionMatrix.m32 = -(2 * FAR_PLANE * NEAR_PLANE) / zm;
		projectionMatrix.m33 = 0;
		
	}

}
