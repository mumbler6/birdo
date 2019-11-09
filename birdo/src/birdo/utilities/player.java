package birdo.utilities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;

public class player extends object {
	public int shootcount;
	// shoot cooldown
	public int poopcount;
	// poop cooldown
	public int health;
	// health
	public int damage;
	// strength of attacks
	public int moveSpeed;
	// move speed when holding a key
	public boolean isDead;
	// checks if the character is dead
	public boolean invulnerable;
	// checks if invulnerable
	public int invulnerablecooldown;
	// invelnerability cooldown
	public int shotmultiplier;
	// for spin shot
	public String poweruptype; // string keeps track of what powerup the player is holding
	public int ammo;
	public int maxammo;
	public int[] stats = { health, damage, moveSpeed };
	public ArrayList<feather> feathers;
	public ArrayList<egg> eggs;
	public player p;

	public player(int x, int y, Color c) {
		super(x, y, 20, 20, c);
		health = 10;
		damage = 1;
		moveSpeed = 4;
		shootcount = 0;
		poopcount = 0;
		ammo = 0;
		maxammo = 3;
		isDead = false;
		feathers = new ArrayList<feather>();
		eggs = new ArrayList<egg>();
		invulnerable = false;
		invulnerablecooldown = 0;
		shotmultiplier = 0;
		poweruptype = "none"; // default powerup is always none
	}

	public void draw(Graphics g) {
		for (feather a : feathers)
			a.draw(g);
		// draws feathers
		for (egg e : eggs)
			e.draw(g);
		// draw egg
		super.draw(g);
		// draws self

	}

	public void move() {
		super.move();
		for (feather f : feathers)
			f.move();
		for (egg e : eggs)
			e.move();
		if (x < 800)
			shootFeather();
	}

	public void shootFeather() { // shoots automatically with cooldown
		if (shootcount == 0) {
			customShot("normal");
			// adds a feather if alive
			shootcount = 15;
		}
		shootcount--;
	}

	public void customShot(String type) {
		if (isDead)
			return;
		if (type == "normal") {
			feathers.add(new feather(this.x, this.y, true));
		}
		if (type == "tripleShot") {
			feather f = new feather(this.x, this.y, true);
			feather f1 = new feather(this.x, this.y, true);
			feather f2 = new feather(this.x, this.y, true);

			f.dx = 5;

			f1.dx = (int) (5 * Math.cos(15 * Math.PI / 180));
			f1.dy = (int) (5 * Math.sin(15 * Math.PI / 180));

			f2.dx = (int) (5 * Math.cos(345 * Math.PI / 180));
			f2.dy = (int) (5 * Math.sin(345 * Math.PI / 180));

			feathers.add(f);
			feathers.add(f1);
			feathers.add(f2);
		}
		if (type == "bloomShot") {
			feather f = new feather(this.x + this.w / 2 - 6, this.y + this.h / 2 - 4, false);
			feather f1 = new feather(this.x + this.w / 2 - 6, this.y + this.h / 2 - 4, false);
			feather f2 = new feather(this.x + this.w / 2 - 6, this.y + this.h / 2 - 4, false);
			feather f3 = new feather(this.x + this.w / 2 - 6, this.y + this.h / 2 - 4, false);
			feather f4 = new feather(this.x + this.w / 2 - 6, this.y + this.h / 2 - 4, false);
			feather f5 = new feather(this.x + this.w / 2 - 6, this.y + this.h / 2 - 4, false);
			feather f6 = new feather(this.x + this.w / 2 - 6, this.y + this.h / 2 - 4, false);
			feather f7 = new feather(this.x + this.w / 2 - 6, this.y + this.h / 2 - 4, false);

			f.dx = -5;
			f.dy = 0;
			f3.dx = 5;
			f3.dy = 0;

			f1.dx = -1 * (int) (-5 * Math.cos(Math.PI / 4));
			f1.dy = -1 * (int) (-5 * Math.sin(Math.PI / 4));

			f2.dx = -1 * (int) (-5 * Math.cos(7 * Math.PI / 4));
			f2.dy = -1 * (int) (-5 * Math.sin(7 * Math.PI / 4));

			f4.dx = (int) (-5 * Math.cos(Math.PI / 4));
			f4.dy = (int) (-5 * Math.sin(Math.PI / 4));

			f5.dx = (int) (-5 * Math.cos(7 * Math.PI / 4));
			f5.dy = (int) (-5 * Math.sin(7 * Math.PI / 4));

			f6.dx = 0;
			f6.dy = -5;
			f7.dx = 0;
			f7.dy = 5;

			feathers.add(f);
			feathers.add(f1);
			feathers.add(f2);
			feathers.add(f3);
			feathers.add(f4);
			feathers.add(f5);
			feathers.add(f6);
			feathers.add(f7);
		}
		if (type == "spinShot") {
			feather f = new feather(this.x + this.w / 2 - 6, this.y + this.h / 2 - 4, false);
			feather f1 = new feather(this.x + this.w / 2 - 6, this.y + this.h / 2 - 4, false);
			feather f2 = new feather(this.x + this.w / 2 - 6, this.y + this.h / 2 - 4, false);
			feather f3 = new feather(this.x + this.w / 2 - 6, this.y + this.h / 2 - 4, false);

			f.dx = -1 * (int) (5 * Math.cos(shotmultiplier * Math.PI / 12));
			f.dy = -1 * (int) (5 * Math.sin(shotmultiplier * Math.PI / 12));

			f1.dx = -1 * (int) (5 * Math.cos((shotmultiplier * Math.PI / 12) + Math.PI / 2));
			f1.dy = -1 * (int) (5 * Math.sin((shotmultiplier * Math.PI / 12) + Math.PI / 2));

			f2.dx = -1 * (int) (5 * Math.cos((shotmultiplier * Math.PI / 12) + Math.PI));
			f2.dy = -1 * (int) (5 * Math.sin((shotmultiplier * Math.PI / 12) + Math.PI));

			f3.dx = -1 * (int) (5 * Math.cos((shotmultiplier * Math.PI / 12) + 3 * Math.PI / 2));
			f3.dy = -1 * (int) (5 * Math.sin((shotmultiplier * Math.PI / 12) + 3 * Math.PI / 2));

			shotmultiplier++;

			feathers.add(f);
			feathers.add(f1);
			feathers.add(f2);
			feathers.add(f3);
		}
		if (type == "buckShot") {
			feather f = new feather(this.x, this.y, true);
			feather f1 = new feather(this.x, this.y, true);
			feather f2 = new feather(this.x, this.y, true);
			feather f3 = new feather(this.x, this.y, true);
			feather f4 = new feather(this.x, this.y, true);
			feather f5 = new feather(this.x, this.y, true);

			f1.dx = (int) (5 * Math.cos(Math.PI / 8));
			f1.dy = (int) (5 * Math.sin(Math.PI / 8));
			f2.dx = (int) (5 * Math.cos(2 * Math.PI / 8));
			f2.dy = (int) (5 * Math.sin(2 * Math.PI / 8));
			f3.dx = (int) (5 * Math.cos(3 * Math.PI / 8));
			f3.dy = (int) (5 * Math.sin(3 * Math.PI / 8));

			f4.dx = (int) (5 * Math.cos(-1 * Math.PI / 8));
			f4.dy = (int) (5 * Math.sin(-1 * Math.PI / 8));
			f5.dx = (int) (5 * Math.cos(-2 * Math.PI / 8));
			f5.dy = (int) (5 * Math.sin(-2 * Math.PI / 8));
			f.dx = (int) (5 * Math.cos(-3 * Math.PI / 8));
			f.dy = (int) (5 * Math.sin(-3 * Math.PI / 8));

			feathers.add(f1);
			feathers.add(f2);
			feathers.add(f3);
			feathers.add(f4);
			feathers.add(f5);
			feathers.add(f);
		}
	}

	public void poop() { // poops
		if (ammo > 0) {
			if (!isDead) {
				eggs.add(new egg(this.x, this.y));
				ammo--;
			}
		}
	}

	public void usePowerup() { // uses the powerup based on string type, add powerups as you feel
		if (poweruptype == "none")
			return;
		if (poweruptype == "eggs")
			poop();
		if (poweruptype == "buckShot")
			customShot("buckShot");
		if (poweruptype == "tripleShot")
			customShot("tripleShot");

		ammo--;
		if (ammo == 0)
			poweruptype = "none";

	}

	public boolean checkisDead() {
		if (health <= 0) {
			isDead = true;
			dx = 0;
			dy = 7;
			return true;
		}
		return false;
	}

}
