package regression;

import java.util.*;

public class Polynomial extends Toolbox {
	private int degree;
	private List<Complex> coeffs;
	
	public Polynomial() {
		degree = 0;
		coeffs = new ArrayList<Complex>();
	}
	
	public Polynomial(List<?> coeffs) {
		this.coeffs = new ArrayList<Complex>();
		
		if (coeffs.isEmpty()) {
			degree = 0;			
		} else {
			degree = coeffs.size() - 1;
			for(Object o : coeffs) {
				if (o instanceof Complex) {
					this.coeffs.add((Complex) o);
				} else {
					this.coeffs.add(new Complex((Number) o, 0));
				}
			}
		}
	}
	
	public Polynomial(int degree) {
		this.degree = degree;
		coeffs = new ArrayList<Complex>();
		for(int i=0; i<=degree; i++) {
			coeffs.add(new Complex(Math.round(40.0 * (Math.random() - 0.5)), 0));
		}
	}
	
	public int getDegree() {
		return degree;
	}
	
	public Complex coeff(int i) {
		return coeffs.get(i);
	}
	
	public Double complexity() {
		Double complexity = 0.0;
		
		if (!coeffs.isEmpty()) {
			for(int i=0; i<=degree; i++) {
				complexity += coeff(i).pow(i+1).r();
			}
		}
		
		return complexity;
	}
	
	public Complex f(Number x) {
		Complex result = new Complex(0, 0);
		
		if (!coeffs.isEmpty()) {
			for(int i=0; i<=degree; i++) {
				Complex wi = coeff(i);
				if (!wi.isNull()) {
					result.inc(wi.mult(Math.pow(x.doubleValue(), i)));
				}
			}
		}
		
		return result;
	}
	
	public Complex f(Complex c) {
		Complex result = new Complex(0, 0);
		
		if (!coeffs.isEmpty()) {
			for(int i=0; i<=degree; i++) {
				Complex wi = coeff(i);
				if (!wi.isNull()) {
					result.inc(c.pow(i).mult(wi));
				}
			}
		}
		
		return result;
	}
	
	public Polynomial dx() {
		if (!coeffs.isEmpty()) {
			List<Complex> new_coeffs = new ArrayList<Complex>();
			for (int i=1; i<=degree; i++) {
				new_coeffs.add(coeff(i).mult(i));
			}
			
			return new Polynomial(new_coeffs);
		} else {
			return new Polynomial();
		}
	}
	
	public Double rootRadius() {
		Double weights_sum = 0.0;
		
		if (!coeffs.isEmpty()) {
			for (int i=0; i<=degree; i++) {
				weights_sum += Math.abs(coeff(i).r());
			}
		}
		return Math.max(1.0, weights_sum);
	}
	
	public Complex findCloseRoot(Complex x0) {
		Polynomial derivative = dx();
		Double tolerance = 10e-7;
		int maxIterations = 20;
		boolean solutionFound = false;
		Complex y, yprime, x1 = new Complex(0, 0);
		
		for (int i=0; i<maxIterations; i++) {
			y = f(x0);
			yprime = derivative.f(x0);
			
			if (yprime.r() < epsilon) {
				break;
			}
			
			x1 = x0.sub(y.div(yprime));
			
			if (x1.sub(x0).r() / x1.r() < tolerance) {
				solutionFound = true;
				break;
			}
			
			x0 = x1;
		}
		
		if (solutionFound) {
			return x1;
		} else {
			return null;
		}
	}
	
	private String coeffAndPowerToString(Complex w, int p) {
		String ret = "";
		
		if (w.isReal()) {
			Double n = w.x();
			
			if (Math.abs(n) != 1 || p == 0) {
				ret += numberToString(Math.abs(n));			
			}
		} else {
			ret += complexToString(w);
		}
		
		if (p >= 1) {
			ret += "x";
			if (p > 1) {
				ret += "^" + p;
			}
		}
		
		return ret;
	}
	
	private String termToString(int i) {
		String ret = "";
		Complex wi;
		
		if (!coeffs.isEmpty() && i <= degree) {
			wi = coeff(i);
			
			if (!wi.isNull()) {
				if (i == degree) {
					if (wi.isReal()) {
						Double n = wi.x();
						if (n < 0) {
							ret += "-";
						}
					}
				} else {
					if (wi.isReal() && wi.x() < 0) {
						ret += " - ";
					} else {
						ret += " + ";
					}
				}
				
				ret += coeffAndPowerToString(wi, i);
			}
		}
		
		return ret;
	}
	
	@Override
	public String toString() {
		String ret = "";
		
		if (!coeffs.isEmpty()) {
			for (int i=degree; i>=0; i--) {
				ret += termToString(i);
			}
		}
		
		 return ret;
	}
}
