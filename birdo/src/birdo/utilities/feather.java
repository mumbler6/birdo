package birdo.utilities;

import java.awt.Color;
import java.util.ArrayList;

public class feather extends object {
	
	public boolean forward;
	public int speed;
	public player p;
	public ArrayList<enemy> enemies = new ArrayList<enemy>();

	public feather(double x, double y, boolean forward) {
		super(x, y, 8, 8, Color.BLACK);
		this.forward = forward;

		if (forward) {
			dx = 5;
			dy = 0;
		}
		if (!forward) {
			dx = -5;
			dy = 0;
		}
	}

}
