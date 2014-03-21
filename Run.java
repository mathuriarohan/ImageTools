public class Run {
	public int length;
	public Pixel value;
	public Run() {
		this(1, new Pixel());
	}
	public Run(int length, Pixel value) {
		this.length = length;
		this.value = value;
	}
}