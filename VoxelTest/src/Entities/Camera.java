package Entities;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.util.vector.Vector3f;

public class Camera {
	
	Vector3f position;
	float rotX;
	float rotY;
	float rotZ;
	
	float speed = 0.01f;
	float turnSpeed = 0.2f;
	float moveAt = 0;
	
	public Camera(Vector3f position, float rotX, float rotY, float rotZ) {
		this.position = position;
		this.rotX = rotX;
		this.rotY = rotY;
		this.rotZ = rotZ;
		
	}
	
	public void move() {
		
		
		if (Keyboard.isKeyDown(Keyboard.KEY_W)) {
			moveAt = -speed;
		} else if (Keyboard.isKeyDown(Keyboard.KEY_S)) {
			moveAt = speed;
		} else {
			moveAt = 0;
		}
		
		rotY += -Mouse.getDY() * turnSpeed;
		rotX += Mouse.getDX() * turnSpeed;
		
		float dx = (float) - (moveAt * Math.sin(Math.toRadians(rotY)));
		float dy = (float) (moveAt * Math.sin(Math.toRadians(rotX)));
		float dz = (float) (moveAt * Math.cos(Math.toRadians(rotZ)));
		
		position.x += dx;
		position.y += dy;
		position.z += dz;
		
	}
	
	public Vector3f getPosition() {
		return position;
	}
	public float getRotX() {
		return rotX;
	}
	public float getRotY() {
		return rotY;
	}
	public float getRotZ() {
		return rotZ;
	}
	
}
