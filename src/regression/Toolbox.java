package regression;

public class Toolbox {
	public Double epsilon = 10e-14;
	
	public String numberToString(Double d) {
		if (d == Math.round(d)) {
			return String.format("%d", Math.round(d));
		} else {
			return d.toString();
		}
	}
	
	public String complexToString(Complex c) {
		if (Math.abs(c.y()) < epsilon) { // Real
			return numberToString(c.x());
		} else {
			return c.toString();
		}
	}
}
