package regression;

import java.util.*;
// import processing.core.*;

public class RegressionRun {
	public static void main(String[] args) {
		// PApplet.main(new String[] { "--present", "regression.RegressionPApplet" });
		
		Complex c1 = new Complex(4, -3);
		System.out.println("c1: " + c1);
		System.out.println("c1 (polar): " + c1.toPolarForm());
		
		Complex c2 = Complex.polar(3, Math.PI/4);
		System.out.println("c2: " + c2);
		System.out.println("c2 (polar): " + c2.toPolarForm());
		
		System.out.println("c1 * c2: " + c1.mult(c2));
		System.out.println("c1 * c2 (polar): " + c1.mult(c2).toPolarForm());
		
		System.out.println("c1^2: " + c1.pow(2));
		System.out.println("c1^2 (polar): " + c1.pow(2).toPolarForm());
		
		System.out.println("c2^3: " + c2.pow(3));
		System.out.println("c2^3 (polar): " + c2.pow(3).toPolarForm());
		
		Polynomial p = new Polynomial(Arrays.asList(6, 11, 11, -14, 4, -14));
		System.out.println(p + " - degree: " + p.getDegree());
		System.out.println("root radius: " + p.rootRadius());
		System.out.println("f(1) = " + p.f(1.0));
		Complex root = p.findCloseRoot(new Complex(1, 0));
		System.out.println("root: " + root);
		System.out.println("f(root): " + p.f(root));
		
		System.out.println("f((4, -3)) = " + p.f(c1));
		System.out.println("f((4, -3)) (polar) = " + p.f(c1).toPolarForm());
		
		System.out.println("Random polynomial of degree 3: " + new Polynomial(3));
		System.out.println("Random polynomial of degree 5: " + new Polynomial(5));
	}
}
