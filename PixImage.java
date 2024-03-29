/* PixImage.java */

/**
 *  The PixImage class represents an image, which is a rectangular grid of
 *  color pixels.  Each pixel has red, green, and blue intensities in the range
 *  0...255.  Descriptions of the methods you must implement appear below.
 *  They include a constructor of the form
 *
 *      public PixImage(int width, int height);
 *
 *  that creates a black (zero intensity) image of the specified width and
 *  height.  Pixels are numbered in the range (0...width - 1, 0...height - 1).
 */

public class PixImage {



  private Pixel[][] pixArray;

  /**
   * PixImage() constructs an empty PixImage with a specified width and height.
   * Every pixel has red, green, and blue intensities of zero (solid black).
   *
   * @param width the width of the image.
   * @param height the height of the image.
   */
  public PixImage(int width, int height) {
    // Your solution here.
    pixArray = new Pixel[width][height];
    for (int i = 0; i < width; i++) {
      for (int j = 0; j< height; j++) {
        pixArray[i][j] = new Pixel();
      }
    }
  }

  /**
   * getWidth() returns the width of the image.
   *
   * @return the width of the image.
   */
  public int getWidth() {
    return pixArray.length;
  }

  /**
   * getHeight() returns the height of the image.
   *
   * @return the height of the image.
   */
  public int getHeight() {
    return pixArray[0].length;
  }

  /**
   * getRed() returns the red intensity of the pixel at coordinate (x, y).
   *
   * @param x the x-coordinate of the pixel.
   * @param y the y-coordinate of the pixel.
   * @return the red intensity of the pixel at coordinate (x, y).
   */
  public Pixel getPixel(int x, int y) {
    return pixArray[x][y];
  }

  public short getRed(int x, int y) {
    return pixArray[x][y].r;
  }

  /**
   * getGreen() returns the green intensity of the pixel at coordinate (x, y).
   *
   * @param x the x-coordinate of the pixel.
   * @param y the y-coordinate of the pixel.
   * @return the green intensity of the pixel at coordinate (x, y).
   */
  public short getGreen(int x, int y) {
    return pixArray[x][y].g;
  }

  /**
   * getBlue() returns the blue intensity of the pixel at coordinate (x, y).
   *
   * @param x the x-coordinate of the pixel.
   * @param y the y-coordinate of the pixel.
   * @return the blue intensity of the pixel at coordinate (x, y).
   */
  public short getBlue(int x, int y) {
    return pixArray[x][y].b;
  }

  /**
   * setPixel() sets the pixel at coordinate (x, y) to specified red, green,
   * and blue intensities.
   *
   * If any of the three color intensities is NOT in the range 0...255, then
   * this method does NOT change any of the pixel intensities.
   *
   * @param x the x-coordinate of the pixel.
   * @param y the y-coordinate of the pixel.
   * @param red the new red intensity for the pixel at coordinate (x, y).
   * @param green the new green intensity for the pixel at coordinate (x, y).
   * @param blue the new blue intensity for the pixel at coordinate (x, y).
   */
  public void setPixel(int x, int y, short red, short green, short blue) {
    if (!((red < 0) || (red > 255) || (green < 0) || (green > 255) || (blue < 0) || (blue > 255))) {
      pixArray[x][y] = new Pixel(red, green, blue);
    }
  }

  /**
   * toString() returns a String representation of this PixImage.
   * @return a String representation of this PixImage.
   */
  public String toString() {
    String returnString = "";
    for (int i = 0; i < pixArray.length; i++) {
      for (int j = 0; j< pixArray[0].length; j++) {
        returnString += pixArray[i][j] + " ";
      }
      returnString += "\n";
    }
    return returnString;
  }

  /**
   * boxBlur() returns a blurred version of "this" PixImage.
   *
   * If numIterations == 1, each pixel in the output PixImage is assigned
   * a value equal to the average of its neighboring pixels in "this" PixImage,
   * INCLUDING the pixel itself.
   *
   * A pixel not on the image boundary has nine neighbors--the pixel itself and
   * the eight pixels surrounding it.  A pixel on the boundary has six
   * neighbors if it is not a corner pixel; only four neighbors if it is
   * a corner pixel.  The average of the neighbors is the sum of all the
   * neighbor pixel values (including the pixel itself) divided by the number
   * of neighbors, with non-integer quotients rounded toward zero (as Java does
   * naturally when you divide two integers).
   *
   * Each color (red, green, blue) is blurred separately.  The red input should
   * have NO effect on the green or blue outputs, etc.
   *
   * The parameter numIterations specifies a number of repeated iterations of
   * box blurring to perform.  If numIterations is zero or negative, "this"
   * PixImage is returned (not a copy).  If numIterations is positive, the
   * return value is a newly constructed PixImage.
   *
   * IMPORTANT:  DO NOT CHANGE "this" PixImage!!!  All blurring/changes should
   * appear in the new, output PixImage only.
   *
   * @param numIterations the number of iterations of box blurring.
   * @return a blurred version of "this" PixImage.
   */
  public PixImage boxBlur(int numIterations) {
    if (numIterations == 0) {
      return this;
    }
    PixImage blurred = new PixImage(getWidth(), getHeight());
    for (int i = 0; i < getWidth(); i++) {
      for (int j = 0; j < getHeight(); j++) {
        Pixel ij = Pixel.average(neighbors(i, j));
        blurred.setPixel(i, j, ij.r, ij.g, ij.b);      
      }
    }
    return blurred.boxBlur(numIterations-1);
  }
  private Pixel[] neighbors(int i, int j) {
    Pixel[] neighbor;
    if (((i == 0) || (i == getWidth() - 1)) && ((j == 0) || (j == getHeight() - 1))) {
        neighbor = new Pixel[4];
      }
    else if ((i == 0) || (i == getWidth() - 1) || (j == 0) || (j == getHeight() - 1)) {
      neighbor = new Pixel[6];
    }
    else {
      neighbor = new Pixel[9];
    }
    int counter = 0;
    for (int a = max(i-1, 0); a <= min(i+1, getWidth() - 1) ; a++){
      for (int b = max(j-1, 0); b <= min(j+1, getHeight() - 1); b++) {
        neighbor[counter] = pixArray[a][b];
        counter++;
       }
    }
    return neighbor;
  }
  private int max(int a, int b) {
    if (a >= b) {
      return a;
    }
    return b;
  }
  private int min(int a, int b) {
    if (a >= b) {
      return b;
    }
    return a;
  }

  /**
   * mag2gray() maps an energy (squared vector magnitude) in the range
   * 0...24,969,600 to a grayscale intensity in the range 0...255.  The map
   * is logarithmic, but shifted so that values of 5,080 and below map to zero.
   *
   * @param mag the energy (squared vector magnitude) of the pixel whose
   * intensity we want to compute.
   * @return the intensity of the output pixel.
   */
  private static short mag2gray(long mag) {
    short intensity = (short) (30.0 * Math.log(1.0 + (double) mag) - 256.0);

    // Make sure the returned intensity is in the range 0...255, regardless of
    // the input value.
    if (intensity < 0) {
      intensity = 0;
    } else if (intensity > 255) {
      intensity = 255;
    }
    return intensity;
  }

  /**
   * sobelEdges() applies the Sobel operator, identifying edges in "this"
   * image.  The Sobel operator computes a magnitude that represents how
   * strong the edge is.  We compute separate gradients for the red, blue, and
   * green components at each pixel, then sum the squares of the three
   * gradients at each pixel.  We convert the squared magnitude at each pixel
   * into a grayscale pixel intensity in the range 0...255 with the logarithmic
   * mapping encoded in mag2gray().  The output is a grayscale PixImage whose
   * pixel intensities reflect the strength of the edges.
   *
   * See http://en.wikipedia.org/wiki/Sobel_operator#Formulation for details.
   *
   * @return a grayscale PixImage representing the edges of the input image.
   * Whiter pixels represent stronger edges.
   */

  public PixImage sobelEdges() {
    PixImage sobelPixImage = new PixImage(getWidth(), getHeight());
    for (int i = 0; i < getWidth(); i++) {
      for (int j = 0; j < getHeight(); j++) {
        Pixel[][] neighbor = neighbors2d(i, j);
        short[] x = new short[3];
        short[] y = new short[3];
        addPixel(x, neighbor[0][0], (short) 1);
        addPixel(x, neighbor[1][0], (short) 2);
        addPixel(x, neighbor[2][0], (short) 1);
        addPixel(x, neighbor[0][2], (short) -1);
        addPixel(x, neighbor[1][2], (short) -2);
        addPixel(x, neighbor[2][2], (short) -1);
        addPixel(y, neighbor[0][0], (short) 1);
        addPixel(y, neighbor[0][1], (short) 2);
        addPixel(y, neighbor[0][2], (short) 1);
        addPixel(y, neighbor[2][0], (short) -1);
        addPixel(y, neighbor[2][1], (short) -2);
        addPixel(y, neighbor[2][2], (short) -1);
        long square_sum = 0;
        for (short c : x) {
          square_sum += ((long) c) * ((long) c);
        }
        for (short c : y) {
          square_sum += ((long) c) * ((long) c);
        }
        int rgb = mag2gray(square_sum);
        sobelPixImage.pixArray[i][j] = new Pixel(rgb, rgb, rgb);

      }
    }
    return sobelPixImage;
  }
  public Pixel[][] neighbors2d(int i, int j) {
    Pixel[][] neighbor = new Pixel[3][3];
    int c1 = 0;
    int c2 = 0;
    for (int a = i-1; a <= i+1; a++) {
      for (int b = j-1; b <= j+1; b++) {
        neighbor[c1][c2] = pixArray[max(0, min(a, getWidth() - 1))][max(0, min(b, getHeight() - 1))];
        c2++;
      }
      c1++;
      c2 = 0;
    }
    return neighbor;
  }
  private void addPixel(short[] x, Pixel p, short mult){
    x[0] += p.r * mult;
    x[1] += p.g * mult;
    x[2] += p.b * mult;
  }
  


  /**
   * TEST CODE
   */


  /**
   * doTest() checks whether the condition is true and prints the given error
   * message if it is not.
   *
   * @param b the condition to check.
   * @param msg the error message to print if the condition is false.
   */
  private static void doTest(boolean b, String msg) {
    if (b) {
      System.out.println("Good.");
    } else {
      System.err.println(msg);
    }
  }

  /**
   * array2PixImage() converts a 2D array of grayscale intensities to
   * a grayscale PixImage.
   *
   * @param pixels a 2D array of grayscale intensities in the range 0...255.
   * @return a new PixImage whose red, green, and blue values are equal to
   * the input grayscale intensities.
   */
  private static PixImage array2PixImage(int[][] pixels) {
    int width = pixels.length;
    int height = pixels[0].length;
    PixImage image = new PixImage(width, height);

    for (int x = 0; x < width; x++) {
      for (int y = 0; y < height; y++) {
        image.setPixel(x, y, (short) pixels[x][y], (short) pixels[x][y],
                       (short) pixels[x][y]);
      }
    }

    return image;
  }

  /**
   * equals() checks whether two images are the same, i.e. have the same
   * dimensions and pixels.
   *
   * @param image a PixImage to compare with "this" PixImage.
   * @return true if the specified PixImage is identical to "this" PixImage.
   */
  public boolean equals(PixImage image) {
    int width = getWidth();
    int height = getHeight();

    if (image == null ||
        width != image.getWidth() || height != image.getHeight()) {
      return false;
    }

    for (int x = 0; x < width; x++) {
      for (int y = 0; y < height; y++) {
        if (! (getRed(x, y) == image.getRed(x, y) &&
               getGreen(x, y) == image.getGreen(x, y) &&
               getBlue(x, y) == image.getBlue(x, y))) {
          return false;
        }
      }
    }
    return true;
  }

  /**
   * main() runs a series of tests to ensure that the convolutions (box blur
   * and Sobel) are correct.
   */
  public static void main(String[] args) {
    PixImage image1 = array2PixImage(new int[][] { { 0, 10, 240 },
                                                   { 30, 120, 250 },
                                                   { 80, 250, 255 } });
    System.out.println("Testing getWidth/getHeight on a 3x3 image.  " +
                       "Input image:");
    System.out.print(image1);
    doTest(image1.getWidth() == 3 && image1.getHeight() == 3,
           "Incorrect image width and height.");

    System.out.println("Testing blurring on a 3x3 image.");
    doTest(image1.boxBlur(1).equals(
           array2PixImage(new int[][] { { 40, 108, 155 },
                                        { 81, 137, 187 },
                                        { 120, 164, 218 } })),
           "Incorrect box blur (1 rep):\n" + image1.boxBlur(1));
    doTest(image1.boxBlur(2).equals(
           array2PixImage(new int[][] { { 91, 118, 146 },
                                        { 108, 134, 161 },
                                        { 125, 151, 176 } })),
           "Incorrect box blur (2 rep):\n" + image1.boxBlur(2));
    doTest(image1.boxBlur(2).equals(image1.boxBlur(1).boxBlur(1)),
           "Incorrect box blur (1 rep + 1 rep):\n" +
           image1.boxBlur(2) + image1.boxBlur(1).boxBlur(1));

    System.out.println("Testing edge detection on a 3x3 image.");
    doTest(image1.sobelEdges().equals(
           array2PixImage(new int[][] { { 104, 189, 180 },
                                        { 160, 193, 157 },
                                        { 166, 178, 96 } })),
           "Incorrect Sobel:\n" + image1.sobelEdges());


    PixImage image2 = array2PixImage(new int[][] { { 0, 100, 100 },
                                                   { 0, 0, 100 } });
    System.out.println("Testing getWidth/getHeight on a 2x3 image.  " +
                       "Input image:");
    System.out.print(image2);
    doTest(image2.getWidth() == 2 && image2.getHeight() == 3,
           "Incorrect image width and height.");

    System.out.println("Testing blurring on a 2x3 image.");
    doTest(image2.boxBlur(1).equals(
           array2PixImage(new int[][] { { 25, 50, 75 },
                                        { 25, 50, 75 } })),
           "Incorrect box blur (1 rep):\n" + image2.boxBlur(1));

    System.out.println("Testing edge detection on a 2x3 image.");
    doTest(image2.sobelEdges().equals(
           array2PixImage(new int[][] { { 122, 143, 74 },
                                        { 74, 143, 122 } })),
           "Incorrect Sobel:\n" + image2.sobelEdges());
  }
}
