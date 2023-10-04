package model;

import java.awt.geom.Path2D;
import java.util.ArrayList;

public class Poligon {


	private ArrayList<Tacka> temena;

	
	public Poligon() {
		this.temena = new ArrayList<Tacka>();
	}

	public Poligon(ArrayList<Tacka> tacke) {
		super();
		this.temena = new ArrayList<Tacka>();
	}

	public ArrayList<Tacka> getTacke() {
		return temena;
	}

	public void setTacke(ArrayList<Tacka> tacke) {
		this.temena = tacke;
	}
	
	
	public void dodajTacku(Tacka point) {
		temena.add(point);
	}
	
	private boolean preseca(Tacka test, Tacka t1, Tacka t2) {
	    double testX = test.getX();
	    double testY = test.getY();
	    double x1 = t1.getX();
	    double y1 = t1.getY();
	    double x2 = t2.getX();
	    double y2 = t2.getY();

	  
	    if ((y1 < testY && y2 >= testY) || (y2 < testY && y1 >= testY)) {    
	        double Xpozicija = x1 + (testY - y1) * (x2 - x1) / (y2 - y1);
	        if (Xpozicija <= testX) {
	            return true;
	        } 
	    } 

	    return false;
	}
	

	public boolean leziNaLiniji(double x1, double y1, double x2, double y2, double testX, double testY) {
	  
	    double m = (y2 - y1) / (x2 - x1);
	    double b = y1 - m * x1;

	    double ocekivanoY = m * testX + b;

	    double epsilon = 1e-6; 
	    return Math.abs(testY - ocekivanoY) < epsilon;
	}

	
	public boolean proveriTacku(Tacka test) {
	    int brojTacaka = temena.size();
	    int brojPreseka = 0;

	    for (int i = 0; i < brojTacaka; i++) {
	        Tacka teme1 = temena.get(i);
	        Tacka teme2 = temena.get((i + 1) % brojTacaka);

	        if(leziNaLiniji(teme1.getX(), teme1.getY(), teme2.getX(), teme2.getY(), test.getX(), test.getY())) {
	        	System.out.println("Tacka lezi na ivici");
	        	return true;
	        }
	        if (preseca(test, teme1, teme2)) {
	        	System.out.println("Presek detektovan");
	        	brojPreseka++;
	        }
	    }
	    System.out.println("Ukupno preseka: " + brojPreseka);
	    return brojPreseka % 2 == 1;
	}

	public boolean daLiJeTeme(Tacka test) {
		for(Tacka teme:temena) {
			if(teme.getX()==test.getX() && teme.getY()==test.getY()) {
				return true;
			} 
		}
		return false;
		
	}
	
	
	//RESENJE KORISCENJEM JAVA AWT
	public boolean proveriTackuAWT(Tacka teme) {
	    Path2D.Double path = new Path2D.Double();

	    path.moveTo(temena.get(0).getX(), temena.get(0).getY());

	    for (int i = 1; i < temena.size(); i++) {
	        path.lineTo(temena.get(i).getX(), temena.get(i).getY());
	    }

	    path.closePath();

	    return path.contains(teme.getX(), teme.getY());
	}
	

}
