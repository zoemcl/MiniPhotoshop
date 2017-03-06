public class Pixel{
  private int red;
  private int green;
  private int blue;
  
  //Question 1.1
  public Pixel(int redElem, int greenElem, int blueElem){
    if( redElem < 0 || redElem > 255 || greenElem < 0 || greenElem > 255 || blueElem < 0 || blueElem > 255){
      throw new IllegalArgumentException("Values should range from [0-255] inclusive");
    }
    else{
      this.red = redElem;
      this.green = greenElem;
      this.blue = blueElem;
    }
  }
  
  //Question 1.2
  public Pixel(int intensity){
    if( intensity < 0 || intensity > 255){
      throw new IllegalArgumentException("Values should range from [0-255] inclusive");
    }
    else{
      this.red = intensity;
      this.green = intensity;
      this.blue = intensity;
    }
  }
  
  public int getRed(){
    return this.red;
  }
  
  public int getGreen(){
    return this.green;
  }
   
  public int getBlue(){
    return this.blue;
  }
  
  public int grey(){
    return ((this.red + this.green + this.blue)/3);
  }
}
  
  
