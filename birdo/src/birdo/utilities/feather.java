package birdo.utilities;

import java.awt.Color;
import java.util.ArrayList;

public class feather extends object {
	
	public boolean forward;
	public player p;
	public ArrayList<enemy> enemies = new ArrayList<enemy>();
	public ArrayList<Integer> hasHit = new ArrayList<Integer>();
	
	// stats
	public int attack;
	public int pierce;
	
	// status effects
	public String effect;
	public int effectDuration;
	
	// homing
	public boolean isHoming;
	public boolean isStrong;
	public boolean isStunShot;
	public int homingSpeed;
	public double prevTheta;
	public boolean track = true;
	public boolean init = true;
	public int homingDuration;

	public feather(double x, double y, int attack, int pierce, boolean forward) {
		super(x, y, 8, 8, Color.BLACK);
		this.forward = forward;
		
		this.attack = attack;
		this.pierce = pierce;
		
		effect = "none";
		effectDuration = -1;
		
		isHoming = false;
		isStrong = false;
		isStunShot = false;

		if (forward) {
			dx = 5;
			dy = 0;
		}
		if (!forward) {
			dx = -5;
			dy = 0;
		}
	}
	
	public void move() {
		if (isHoming) {
			if (!isStunShot)
				c = Color.GREEN;
			if (isStunShot)
				c = Color.BLUE;
			home();
		}
		super.move();
	}
	
	public void home() {
		if (x < 0 || x > 800 || y < 0 || y > 550) 
			return;
		
		if (!forward) {
			double deltaX = p.x - x;
			double deltaY = p.y - y;
			double hypotenuse = Math.sqrt(deltaX * deltaX + deltaY * deltaY);
			double theta = Math.atan(deltaY / deltaX);
			// during first move, set prevTheta to theta so it starts out as tracking
			if (init) {
				prevTheta = theta;
				init = false;
			}
			// if close enough, start tracking
			if (hypotenuse < 300)
				track = true;
			// if ready to fuck off, stop tracking
			if (homingDuration <= 0) 
				track = false;
			// if change in angle is greater than threshold, then stop tracking
			if (Math.abs(prevTheta - theta) > 5 * Math.PI / 4 && forward == false)
				track = false;
			// update dx and dy to follow player if track is true
			if (track) {
				dx = (homingSpeed * deltaX / hypotenuse);
				dy = (homingSpeed * deltaY / hypotenuse);
			}
			// update prevThetas
			prevTheta = theta;
		}
		if (forward && enemies.size() > 0) {
			if (nearestEnemy() == null) return;
			
			double deltaX = nearestEnemy().x - x;
			double deltaY = nearestEnemy().y - y;
			double hypotenuse = Math.sqrt(deltaX * deltaX + deltaY * deltaY);
			double theta = Math.atan(deltaY / deltaX);
			// during first move, set prevTheta to theta so it starts out as tracking
			if (init) {
				prevTheta = theta;
				init = false;
			}
			// if close enough, start tracking
			if (hypotenuse < 300 && !isStrong)
				track = true;
			// if ready to fuck off, stop tracking
			if (homingDuration <= 0) 
				track = false;
			// if change in angle is greater than threshold, then stop tracking
			if (Math.abs(prevTheta - theta) > 5 * Math.PI / 4 && !isStrong)
				track = false;
			if (Math.abs(prevTheta - theta) > 3 * Math.PI / 2 && isStrong)
				track = false;
			// update dx and dy to follow player if track is true
			if (track) {
				dx = (homingSpeed * deltaX / hypotenuse);
				dy = (homingSpeed * deltaY / hypotenuse);
			}
			prevTheta = theta;
		}
		
		homingDuration--;
	}
	
	public enemy nearestEnemy() { // function to find the nearest enemy for tracking bullets
		
		if (enemies.size() != 0) {
			enemy nearestEnemy = enemies.get(0);
			double nearestDeltaX = nearestEnemy.x - x;
			double nearestDeltaY = nearestEnemy.y - y;
			double nearestHypotenuse = Math.sqrt(nearestDeltaX * nearestDeltaX + nearestDeltaY * nearestDeltaY);
			
			for (enemy a : enemies) {
				
				if (a.isDead)
					continue;
				
				if (a.x < 0 || a.x > 800)
				
				if (hasHit.contains(a.hash))
					continue;
				
				double deltaX = a.x - x;
				double deltaY = a.y - y;
				double hypotenuse = Math.sqrt(deltaX * deltaX + deltaY * deltaY);
				if (nearestHypotenuse > hypotenuse) {
					nearestHypotenuse = hypotenuse;
					nearestDeltaX = deltaX;
					nearestDeltaY = deltaY;
					nearestEnemy = a;
				}
			}
			
			if (nearestEnemy.isDead || hasHit.contains(nearestEnemy.hash) || 
				nearestEnemy.x < 0 || nearestEnemy.x > 800)
				return null;
			else
				return nearestEnemy;
		}
		return null;
	}

}
