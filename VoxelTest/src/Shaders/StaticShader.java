package Shaders;

import org.lwjgl.util.vector.Matrix4f;

import Entities.Camera;
import ToolBox.Maths;

public class StaticShader extends ShaderProgram {
	
	private static final String vertexFile = "/Shaders/vertexShader.txt";
	private static final String fragmentFile = "/Shaders/fragmentShader.txt";
	
	int location_transformationMatrix;
	int location_projectionMatrix;
	int location_viewMatrix;

	public StaticShader() {
		super(vertexFile, fragmentFile);
	}

	@Override
	protected void bindAttributes() {
		
		super.bindAttribute("position", 0);
		super.bindAttribute("textureCoords", 1);
	}

	@Override
	protected void getAllUniformLocations() {
		
		location_transformationMatrix = super.getUniformLocation("transformationMatrix");
		location_projectionMatrix = super.getUniformLocation("projectionMatrix");
		location_viewMatrix = super.getUniformLocation("viewMatrix");
		
	}
	
	public void loadTransformationMatrix(Matrix4f matrix) {
		super.loadMatrix(location_transformationMatrix, matrix);
	}
	
	public void loadProjectionMatrix(Matrix4f matrix) {
		super.loadMatrix(location_projectionMatrix, matrix);
	}
	
	public void loadViewMatrix(Camera camera) {
		super.loadMatrix(location_viewMatrix, Maths.createViewMatrix(camera));
	}
	
}
