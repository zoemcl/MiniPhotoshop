import java.io.*;
public class Comp202Photoshop{
  public static void main(String[] args){
    if(args.length < 4){
      System.err.println("Invalid Input. Must have 4 arguments, 8 if you wish to crop the image.");
      return;
    }
    else{
      try{
        String inFileName = args[0];
        String outFileName = args[1];
        String format = args[2];
        String operation = args[3];
        
        Image image = ImageFileUtilities.read(inFileName);
        
        //Modify the image
        int i = 0;
        while(i == 0){
          if(operation.equals("-fh")){     
            image.flip(true);
            i++;
          }
          else if(operation.equals("-fv")){
            image.flip(false);
            i++;
          }
          else if(operation.equals("-gs")){
            image.toGrey();
            i++;
          }
          else if(operation.equals("-cr")){
            if(args.length < 8 || args.length > 8){
              System.err.println("Invalid input. -cr operation must have 8 arguments.");
              return;
            }
            else{
              int startX = Integer.parseInt(args[4]);
              int startY = Integer.parseInt(args[5]);
              int endX = Integer.parseInt(args[6]);
              int endY = Integer.parseInt(args[7]);
              try{
                image.crop(startX, startY, endX, endY);
                i++;
              }catch(IllegalArgumentException e){
                System.err.println("IllegalArgumentException" + e);
                return;
              }
            }
          }
        }
        //Print the image in specified format
        if(format.equals("pgm")){
            ImageFileUtilities.writePgm(image, outFileName);       
        }
        else if(format.equals("pnm")){
          ImageFileUtilities.writePnm(image, outFileName);
        }
        else{
          throw new IllegalArgumentException("Invalid format");
        }
      }catch(IOException e){
        System.err.println("IOException" + e);
        return;
      } 
    }
  }
}