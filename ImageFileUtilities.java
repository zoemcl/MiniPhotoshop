import java.util.Scanner;
import java.io.*;
public class ImageFileUtilities{
  public static Image read(String filename) throws IOException{
    
    Scanner sc = new Scanner(new File(filename));
    //Store Format
    String metadata = sc.nextLine();
    //Store comments
    String s = "#";
    while(sc.hasNext(s)){
      String comments = sc.nextLine();
    }
    //store width, height, maxRange 
    int width = sc.nextInt();
    int height = sc.nextInt();
    int maxRange = sc.nextInt();
    //declare an array of type Pixel to read in pixel vales. Also declare an array of ints for groups of red, green and blue.
    Pixel[][] data = new Pixel[height][width];
    int[] rgb = new int[3];
    
    //Reads P2 file format
    if(metadata.equals("P2")){
      while(sc.hasNextInt()){
        for(int i=0; i<data.length; i++){
          for(int j=0; j<data[0].length; j++){
            Pixel grey = new Pixel(sc.nextInt());
            data[i][j] = grey;
          }
        }
      }
      sc.close();
      Image image = new Image(metadata, maxRange, data);
      return image;
    }
    
    //Reads P3 file format
    else if(metadata.equals("P3")){
      while(sc.hasNextInt()){
        for(int i=0; i<data.length; i++){
          for(int j=0; j<data[0].length; j++){
            for(int k=0; k<3; k++){
              rgb[k] = sc.nextInt();
            }
            Pixel colour = new Pixel(rgb[0], rgb[1], rgb[2]);
            data[i][j]= colour;
          }
        }
      }
      sc.close();
      Image image = new Image(metadata, maxRange, data);
      return image; 
    }
    //Exception
    else{
      sc.close();
      throw new IOException("Must be .pnm or .pgm format");
    } 
  }
  
  //Write Images in P3 format
  public static void writePnm(Image image, String filename) throws IOException{
    FileWriter fw = new FileWriter(filename); 
    BufferedWriter bw = new BufferedWriter(fw); 
 
    int height = image.getHeight();
    int width = image.getWidth();
    int maxRange = image.getMaxRange();
    
    bw.write("P3 \n");
    bw.write(width + " " + height + "\n");
    bw.write(maxRange + "\n");
    for(int i=0; i<height; i++){
      for(int j=0; j<width; j++){
        bw.write(image.getPixel(i,j).getRed() + " " + image.getPixel(i,j).getGreen() + " " +image.getPixel(i,j).getBlue()+ " "); 
      }
      bw.write("\n");
    }
    bw.close();
    fw.close();
  }
  //Write images in P2 format
  public static void writePgm(Image image, String filename) throws IOException{
    
    FileWriter fw = new FileWriter(filename); 
    BufferedWriter bw = new BufferedWriter(fw); 
 
    int height = image.getHeight();
    int width = image.getWidth();
    int maxRange = image.getMaxRange();
    
    bw.write("P2 \n");
    bw.write(width + " " + height + "\n");
    bw.write(maxRange + "\n");
    for(int i=0; i<height; i++){
      for(int j=0; j<width; j++){
        int intensity = image.getPixel(i,j).grey();
        bw.write(intensity + " ");
      }
      bw.write("\n");
    }
    bw.close();
    fw.close();
  }
}