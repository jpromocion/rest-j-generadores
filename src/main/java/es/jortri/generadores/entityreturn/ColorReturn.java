package es.jortri.generadores.entityreturn;

public class ColorReturn {
	private int red;
	private int green;
	private int blue;
	private int alpha;
	private String hex;
	
	public ColorReturn() {
		super();
	}
	
	public ColorReturn(int red, int green, int blue, String hex) {
		super();
		this.red = red;
		this.green = green;
		this.blue = blue;
		this.hex = hex;
	}
	
	public ColorReturn(int red, int green, int blue, int alpha, String hex) {
		super();
		this.red = red;
		this.green = green;
		this.blue = blue;
		this.alpha = alpha;
		this.hex = hex;
	}
	
	public int getRed() {
		return red;
	}
	
	public void setRed(int red) {
		this.red = red;
	}
	
	public int getGreen() {
		return green;
	}
	
	public void setGreen(int green) {
		this.green = green;
	}
	
	public int getBlue() {
		return blue;
	}
	
	public void setBlue(int blue) {
		this.blue = blue;
	}
	
	public int getAlpha() {
		return alpha;
	}
	
	public void setAlpha(int alpha) {
        this.alpha = alpha;
    }
	
	public String getHex() {
		return hex;
	}
	
	public void setHex(String hex) {
		this.hex = hex;
	}
	
	@Override
	public String toString() {
		return "ColorReturn [red=" + red + ", green=" + green + ", blue=" + blue + ", alpha=" + alpha + ", hex=" + hex + "]";
	}
	
}
