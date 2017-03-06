public class Image{
  //store format
  private String metadata;
  //store upper bound of pixel values, assume maxRange >= all values in pixel array
  private int maxRange;
  //stores values of various pixels. forst array store forst row of image pixel values. so array.length = height of array, array[i].length is width
  private Pixel[][] data;
  
  //1.constructor
  public Image(String md, int mr, Pixel[][] d){
    if(mr < 0){
      throw new IllegalArgumentException("Maximum Range cannot be negative");
    }
    else{
      this.metadata = md;
      this.maxRange = mr;
      this.data = new Pixel[d.length][d[0].length];
      for(int i=0; i<this.data.length; i++){
        for(int j=0; j<this.data[0].length; j++){
          this.data[i][j] = d[i][j];
        }
      }
    }
  }
  
  //get methods 2.
  public String getMetadata(){
    return this.metadata;
  }
  //3.
  public int getMaxRange(){
    return this.maxRange;
  }
  //4.
  public int getWidth(){
    return this.data[0].length;
  }
  //5.
  public int getHeight(){
    return this.data.length;
  }
  //6. 
  public Pixel getPixel(int i, int j){
      return this.data[i][j];
  }
  
  
  public Pixel[][] getData(){
    return this.data;
  }
  //7. Flip  
  public void flip(boolean horizontal){
    Pixel[][] temp = new Pixel[this.data.length][this.data[0].length];
    //flip horizontal
    if(horizontal == true){
      for(int i=0; i<temp.length; i++){
        for(int j=0; j<temp[0].length; j++){
          temp[i][j] = this.data[i][this.data[0].length - 1 - j];
        }
      }
      for(int i=0; i<temp.length; i++){
        for(int j=0; j<temp[0].length; j++){
          this.data[i][j] = temp[i][j];
        }
      }   
    }
    //flip vertical
    else{
      for(int i=0; i<temp.length; i++){
        for(int j=0; j<temp[0].length; j++){
          temp[i][j] = this.data[this.data.length - 1 - i][j];

        }
      }
      for(int i=0; i<temp.length; i++){
        for(int j=0; j<temp[0].length; j++){
          this.data[i][j] = temp[i][j];
        }
      }
    }
  }
  //8. toGrey()
  public void toGrey(){
    for(int i=0; i<this.data.length; i++){
      for(int j=0; j<this.data[0].length; j++){
        int average = this.data[i][j].grey();
        Pixel temp = new Pixel(average);
        this.data[i][j] = temp;
      }
    }
  }
  //9. crop()
  public void crop(int startX, int startY, int endX, int endY){
    if(startX < 0 || startY < 0 || endX > this.data[0].length || endY > this.data.length){
      throw new IllegalArgumentException("Arguments are not within range.");
    }
    else{
      Pixel[][] temp = new Pixel[endY-startY][endX-startX];
      for( int i=0; i<(endY-startY); i++){
        for( int j=0; j<(endX-startX); j++){
          temp[i][j] = this.data[i + startY][j + startX];
        }
      }
    this.data = temp; 
    } 
  }
}

