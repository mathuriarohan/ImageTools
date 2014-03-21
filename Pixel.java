public class Pixel {
	public short r;
	public short g;
	public short b;
	public Pixel(int r, int g, int b){
		this.r = (short) r;
		this.g = (short) g;
		this.b = (short) b;
	}
	public Pixel(short r, short g, short b) {
		this.r = r;
		this.g = g;
		this.b = b;
	}
	public Pixel() {
		this((short) 0,(short) 0,(short) 0);
	}
	public static Pixel average(Pixel[] neighbors) {
		short red_sum = 0;
		short green_sum = 0;
		short blue_sum = 0;
		for (Pixel element : neighbors) {
			red_sum += element.r;
			green_sum += element.g;
			blue_sum += element.b;
		}
		short l = (short) neighbors.length;
		Pixel returnPixel =  new Pixel(red_sum/l ,green_sum/l, blue_sum/l);
		return returnPixel;
	}
	public String toString() {
		return "(" + r + ", " + g + ", " + b + ")";
	}
	public boolean equals(Object obj) {
		if (!(obj instanceof Pixel)) {
			return false;
		}
		Pixel object = (Pixel) obj;
		if ((object.r == this.r) && (object.g == this.g) && (object.b == this.b)){
			return true;
		}
		return false;
	}
}