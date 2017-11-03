package game;

import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Vector3f;

import Entities.Camera;
import Entities.Entity;
import Models.RawModel;
import Models.TexturedModel;
import RenderEngine.DisplayManager;
import RenderEngine.Loader;
import RenderEngine.MasterRenderer;
import Shaders.StaticShader;
import Textures.ModelTexture;

public class MainGameLoop {

	public static Loader loader1 = null;
	public static StaticShader shader1 = null;
	
	public static void main(String[] args) {
		
		DisplayManager.createDisplay();
		
		Loader loader = new Loader();
		loader1 = loader;
		StaticShader shader = new StaticShader();
		shader1 = shader;
		MasterRenderer renderer = new MasterRenderer(shader);
		
		float[] vertices = {
				
				-0.5f, 0.5f, 0,
				-0.5f, -0.5f, 0,
				0.5f, -0.5f, 0,
				0.5f, 0.5f, 0,

		};
		
		int[] indices = {
				
				0, 1, 2,
				2, 3, 0
				
		};
		
		float[] uv = {
				
				0,0,
				0,1,
				1,1,
				1,0
				
		};
		
		RawModel model = loader.loadToVAO(vertices, indices, uv);
		ModelTexture texture = new ModelTexture(loader.loadTexture("dirt"));
		TexturedModel texModel = new TexturedModel(model, texture);
		Entity entity = new Entity(texModel, new Vector3f(0, 0, -1), 0, 0, 0, 1);
		
		Camera camera = new Camera(new Vector3f(0, 0, 0), 0, 0, 0);
		
		while (!Display.isCloseRequested()) {
			
			camera.move();
			
			renderer.prepare();
			
			entity.increasePosition(0f, 0, 0);
			entity.increaseScale(0f);
			entity.increaseRotation(0, 0, 0f);
			
			shader.start();
			shader.loadViewMatrix(camera);
			renderer.render(entity, shader);
			shader.stop();
			
			DisplayManager.updateDisplay();
			
		}
		
		DisplayManager.closeDisplay();

	}

}
