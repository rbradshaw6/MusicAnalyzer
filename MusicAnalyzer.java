import java.io.*;
import java.util.ArrayList;
import java.awt.*;
import java.util.Scanner;

//Try
//Vegetable Valley Piano.mp3
//Anthem of Denial v2.mp3
//Mimas Full.mp3

public class MusicAnalyzer
{
  static int[] value(String in){
    int[] out = new int[in.length()];
    for(int i = 0; i < out.length; i++)
      out[i] = (int)in.charAt(i);
    return out;
  }
  
  static int sum(int[] v){
    int k = 0;
    for(int i = 0; i < v.length; i++)
      k+=v[i];
    return k;
  }
  
  static int avg(int[] v){
    if(v.length > 0)
      return sum(v)/v.length;
    else
      return 0;
  }
  
  static int fix(int k){
    return Math.min(Math.max(k, 0), 255);
  }
  
  static float fix(float k){
    return Math.min(Math.max(k, 0), 1);
  }
  
  static ArrayList<String> shorten(ArrayList<String> in, int factor){
    ArrayList<String> out = new ArrayList<String>();
    for(int i = 0; i < in.size(); i++){
      if(i%factor == 0)
        out.add(in.get(i));
    }
    return out;
  }
  
  static Color pix(int[][] picture, int x, int w){
    int[] values = picture[x%w];
    int r = 0, g = 0, b = 0;
    float h = 0, s = 0, v = 0;
    int k = 25;
    
    if(picture[x%w].length != 0)
      h = (float)(values.length*0.01);
    if(picture[(x+1)%w].length != 0)
      s = (float)(picture[(x+1)%w][0]*0.01);
    if(picture[(x+2)%w].length != 0)
      v = (float)(picture[(x+2)%w][0]*0.01);
    
    h = fix(h);
    s = fix(s);
    v = fix(v);
    Color hsv = Color.getHSBColor(h, s, v);
    
    r = hsv.getRed();
    g = hsv.getGreen();
    b = hsv.getBlue();
    
    return new Color(r, g, b, 15000/w);
  }
  
  static int height(int[] values, int hi){
    return hi - Math.min(values.length, hi);
  }
  
  static int[][] bitStream(String fileName, int factor){
    ArrayList<String> k = new ArrayList<String>();
    String line = null;
    
    try{
      FileReader fileReader = new FileReader(fileName);
      
      BufferedReader bufferedReader = new BufferedReader(fileReader);
      
      while((line = bufferedReader.readLine()) != null)
        k.add(line);
      
      bufferedReader.close();
    }
    catch(FileNotFoundException ex){
      System.out.println("Unable to open '" + fileName + "'");
    }
    catch(IOException ex){
      System.out.println("Error opening '" + fileName + "'");
    }
    
    ArrayList<String> l = shorten(k, factor);
    int[][] p = new int[k.size()][640];
    for(int i = 0; i < l.size(); i++){
      p[i] = value(l.get(i));
    }
    
    return p;
  }
  
  static void draw(int[][] picture, Graphics g, int h, int mw)
  {
    int y;
    for(int x = 0; x < picture.length; x++){
      g.setColor(pix(picture, x, mw));
      y = height(picture[x], h);
      g.fillOval(x%mw, y%h, 20, y%h);
    }
  }
  
  public static void main(String[]args){
    Scanner key = new Scanner(System.in);
    System.out.println("File name? ");
    String fileName = key.nextLine();
    //System.out.println("Shortening Factor? ");
    int shorteningFactor = 1;
    int hi = 640;
    
    int[][] picture = bitStream(fileName, shorteningFactor);
    int maxWi = 4*(int)Math.sqrt(picture.length);
    Frame f = new Frame("");
    Window w = new Window(f);
    w.setSize(new Dimension(Math.min(picture.length/shorteningFactor, maxWi), hi));
    w.setVisible(true);
    
    Graphics g = w.getGraphics();
    g.setColor(Color.black);
    g.fillRect(0, 0, Math.min(picture.length/shorteningFactor, maxWi), hi);
    
    draw(picture, g, hi, maxWi);
  }
  
  static void print(int[] n){
    for(int i = 0; i < n.length; i++)
      System.out.println(n[i]);
  }
}