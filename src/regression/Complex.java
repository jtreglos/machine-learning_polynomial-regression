package regression;

public class Complex extends Toolbox {
	private Double x;
	private Double y;
	private Double r;
	private Double phi;
	
	public Complex(Number x, Number y) {
		this.x = x.doubleValue();
		this.y = y.doubleValue();
		calcPolar();
	}
	
	public Complex(Complex c) {
		this(c.x, c.y);
	}
	
	public static Complex polar(Number r, Number phi) {
		Double dr = r.doubleValue();
		Double dphi = phi.doubleValue();
		
		return new Complex(dr * Math.cos(dphi), dr * Math.sin(dphi));
	}
	
	public void calcXY() {
		x = r * Math.cos(phi);
		y = r * Math.sin(phi);
	}
	
	public void calcPolar() {
		r = Math.sqrt(x * x + y * y);
		phi = Math.atan2(y, x);
	}
	
	public Double x() {
		return x;
	}
	
	public void x(Number x) {
		this.x = x.doubleValue();
		calcPolar();
	}
	
	public Double y() {
		return y;
	}
	
	public void y(Number y) {
		this.y = y.doubleValue();
		calcPolar();
	}
	
	public Double r() {
		return r;
	}
	
	public void r(Number r) {
		this.r = r.doubleValue();
		calcXY();
	}
	
	public Double phi() {
		return phi;
	}
	
	public void phi(Number phi) {
		this.phi = phi.doubleValue();
		calcXY();
	}
	
	public Complex add(Complex c) {
		return new Complex(x + c.x, y + c.y);
	}
	
	public Complex add(Number n) {
		Double operand = n.doubleValue();
		
		return new Complex(x + operand, y);
	}
	
	public Complex sub(Complex c) {
		return new Complex(x - c.x, y - c.y);
	}
	
	public Complex sub(Number n) {
		Double operand = n.doubleValue();
		
		return new Complex(x - operand, y);
	}
	
	public Complex inc(Complex c) {
		x += c.x;
		y += c.y;
		
		calcPolar();
		
		return this;
	}
	
	public Complex inc(Number n) {
		Double operand = n.doubleValue();
		
		x += operand;
		
		calcPolar();
		
		return this;
	}
	
	public Complex dec(Complex c) {
		x -= c.x;
		y -= c.y;
		
		calcPolar();
		
		return this;
	}
	
	public Complex dec(Number n) {
		Double operand = n.doubleValue();
		
		x -= operand;
		
		calcPolar();
		
		return this;
	}
	
	public Complex mult(Complex c) {
		return Complex.polar(r * c.r, phi + c.phi);
	}
	
	public Complex mult(Number n) {
		Double multiplier = n.doubleValue();
		
		return Complex.polar(r * multiplier, phi);
	}
	
	public Complex pow(Number n) {
		Double power = n.doubleValue();
		
		return Complex.polar(Math.pow(r, power), phi * power);
	}
	
	public Complex div(Complex c) {
		if (!c.isNull()) {
			return Complex.polar(r / c.r, phi - c.phi);
		} else {
			return null;
		}
	}
	
	public Complex div(Number n) {
		Double divider = n.doubleValue();
		
		if (divider != 0.0) {
			return Complex.polar(r / divider, phi);
		} else {
			return null;
		}
	}
	
	public boolean isReal() {
		return Math.abs(y) < epsilon;
	}
	
	public boolean isNull() {
		return Math.abs(x) < epsilon && isReal();
	}
	
	@Override
	public String toString() {
		if (isReal()) {
			return numberToString(x);
		} else {
			return "(" + numberToString(x) + ", " + numberToString(y) + ")";
		}
	}
	
	public String toPolarForm() {
		return "r: " + String.format("%.4f", r) + " / 𝜑: " + String.format("%.4f",  phi);
	}
}
