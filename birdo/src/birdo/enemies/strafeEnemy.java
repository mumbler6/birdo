package birdo.enemies;

import java.awt.Color;

import birdo.utilities.egg;
import birdo.utilities.enemy;

public class strafeEnemy extends enemy {

	public strafeEnemy(int x, int y) {
		super(x, y);
		dx = -6;
		c = new Color(249, 163, 27);
	}

	public void shoot() {
		return;
	}

	public void poop() {
		if (isDead)
			return;
		if (poopCount == 0) {
			eggs.add(new egg(this.x, this.y));
			poopCount = 20;
		}
		poopCount--;
	}

}
