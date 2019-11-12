package birdo.enemies;

import java.awt.Color;

import birdo.utilities.enemy;
import birdo.utilities.feather;

public class miniBoss2 extends enemy {

	public miniBoss2(int x, int y) {
		super(x, y);
		health = 20;
		score = 1500;
		w = 30;
		h = 30;
	}

	public void move() {
		if (x < 700 && health >= 10)
			customMove("upDown");
		if (health < 10)
			customMove("moveCenter");
		super.move();
	}

	public void shootFeather() {
		if (health >= 10) {
			if (shootcount <= 0) {
				customShot("homing");
				shootcount = 200;
			}
			if (shootcount % 20 == 0 && (shootcount / 20) < 80) {
				if (shootcount / 20 < 5)
					customShot("normal");
				else
					customShot("homing");
			}
		}

		if (health < 10 ) {
			if (shootcount <= 0) {
				customShot("spinShot");
				shootcount = 10;
			}
		}
		shootcount--;
	}

	public void poop() {
		return;
	}

}
