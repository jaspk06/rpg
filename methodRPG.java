import arc.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.awt.image.*;
import javax.imageio.*;
public class methodRPG{
  public static int file (String strFileName){
    Console con = new Console();
    int intLine=0;
    String strLine;
    TextInputFile file = new TextInputFile ( strFileName);
    while (file.eof()==false){
      intLine = intLine+1;
      strLine = file.readLine();
      con.println(strLine);
    }
    file.close();
    return intLine;
  }
  public static void writeLine (String strFileName, String strWriteThis){
    TextOutputFile fileout = new TextOutputFile (strFileName,true);
    fileout.println(strWriteThis);
    fileout.close();
  }
  public static String[][] loadMap(String strFileName){
    String strMap[][];
    int intRow;
    int intColumn;
    String strLine;
    String strSplit[];
    strMap = new String [20][20];
    TextInputFile map = new TextInputFile (strFileName);
    for(intRow =0;intRow <20;intRow++){
      strLine = map.readLine();
      strSplit = strLine.split(",");
      for(intColumn =0;intColumn <20;intColumn++){
        strMap[intRow][intColumn] = strSplit[intColumn]; 
      }
    }
    map.close();
    return strMap;
  }
  public static void drawMap (Console con, String[][] strMap){
    int intCount;
    int intCount1;
    BufferedImage treeb;
    treeb = con.loadImage("treeb.png");
    BufferedImage treet;
    treet = con.loadImage("treet.png");
    BufferedImage down;
    down = con.loadImage("down.png");
    BufferedImage down1;
    down1 = con.loadImage("down1.png");
    BufferedImage down2;
    down2 = con.loadImage("down2.png");
    BufferedImage grass;
    grass = con.loadImage("grass.png");
    BufferedImage water;
    water= con.loadImage("water.png");
    BufferedImage housedraw;
    housedraw = con.loadImage("housedraw.png");
    BufferedImage edge;
    edge = con.loadImage("edge.png");
    BufferedImage end1;
    end1 = con.loadImage("end1.png");
    BufferedImage end2;
    end2 = con.loadImage("end2.png");
    BufferedImage pokeball;
    pokeball = con.loadImage("pokeball.png");
    BufferedImage ground;
    ground = con.loadImage ("ground.png");
    BufferedImage pillar1;
    pillar1 = con.loadImage("pillar1.png");
    BufferedImage pillar2;
    pillar2 = con.loadImage("pillar2.png");
    BufferedImage pillar3;
    pillar3 = con.loadImage("pillar3.png");
    BufferedImage pillar4;
    pillar4 = con.loadImage("pillar4.png");
    BufferedImage pillar5;
    pillar5 = con.loadImage("pillar5.png");
    BufferedImage pillar6;
    pillar6 = con.loadImage("pillar6.png");
    BufferedImage pillar7;
    pillar7 = con.loadImage("pillar7.png");
    BufferedImage pillar8;
    pillar8 = con.loadImage("pillar8.png");
    BufferedImage pillar10;
    pillar10 = con.loadImage("pillar10.png");
    BufferedImage pillar11;
    pillar11 = con.loadImage("pillar11.png");
    BufferedImage pillar12;
    pillar12 = con.loadImage("pillar12.png");
    
    BufferedImage black;
    black = con.loadImage("black.png");
    BufferedImage boss;
    boss = con.loadImage("boss.png");
    
    for (intCount=0;intCount<20;intCount++){
      for (intCount1 = 0 ; intCount1<20;intCount1++){
        if (strMap [intCount][intCount1].equals("treet")){
          con.drawImage(treet,45*intCount1,45*intCount);
        }
        else if (strMap [intCount][intCount1].equals("treeb")){
          con.drawImage(treeb,45*intCount1,45*intCount);
        } 
        else if (strMap [intCount][intCount1].equals("grass")){
          con.drawImage(grass,45*intCount1,45*intCount);
        } 
        else if (strMap [intCount][intCount1].equals("water")){
          con.drawImage(water,45*intCount1,45*intCount);
        }
        else if (strMap [intCount][intCount1].equals("housedraw")){
          con.drawImage(housedraw,45*intCount1,45*intCount);
        }
        else if (strMap [intCount][intCount1].equals("edge")){
          con.drawImage(edge,45*intCount1,45*intCount);
        }
        else if (strMap [intCount][intCount1].equals("end1")){
          con.drawImage(end1,45*intCount1,45*intCount);
        }
        else if (strMap [intCount][intCount1].equals("end2")){
          con.drawImage(end2,45*intCount1,45*intCount);
        }
        else if (strMap [intCount][intCount1].equals("pokeball")){
          con.drawImage(pokeball,45*intCount1,45*intCount);
        }
        else if (strMap [intCount][intCount1].equals("ground")){
          con.drawImage(ground,45*intCount1,45*intCount);
        }
        else if (strMap [intCount][intCount1].equals("pillar1")){
          con.drawImage(pillar1,45*intCount1,45*intCount);
        }
        else if (strMap [intCount][intCount1].equals("pillar2")){
          con.drawImage(pillar2,45*intCount1,45*intCount);
        }
        else if (strMap [intCount][intCount1].equals("pillar3")){
          con.drawImage(pillar3,45*intCount1,45*intCount);
        }
        else if (strMap [intCount][intCount1].equals("pillar4")){
          con.drawImage(pillar4,45*intCount1,45*intCount);
        }
        else if (strMap [intCount][intCount1].equals("pillar5")){
          con.drawImage(pillar5,45*intCount1,45*intCount);
        }
        else if (strMap [intCount][intCount1].equals("pillar6")){
          con.drawImage(pillar6,45*intCount1,45*intCount);
        }
        else if (strMap [intCount][intCount1].equals("pillar7")){
          con.drawImage(pillar7,45*intCount1,45*intCount);
        }
        else if (strMap [intCount][intCount1].equals("pillar8")){
          con.drawImage(pillar8,45*intCount1,45*intCount);
        }
        else if (strMap [intCount][intCount1].equals("pillar10")){
          con.drawImage(pillar10,45*intCount1,45*intCount);
        }
        else if (strMap [intCount][intCount1].equals("pillar11")){
          con.drawImage(pillar11,45*intCount1,45*intCount);
        }
        else if (strMap [intCount][intCount1].equals("pillar12")){
          con.drawImage(pillar12,45*intCount1,45*intCount);
        }
        else if (strMap [intCount][intCount1].equals("ground")){
          con.drawImage(ground,45*intCount1,45*intCount);
        }
        else if (strMap [intCount][intCount1].equals("black")){
          con.drawImage(black,45*intCount1,45*intCount);
        }
        else if (strMap [intCount][intCount1].equals("boss")){
          con.drawImage(boss,45*intCount1,45*intCount);
        }
        
      }
    }
    con.println("");
    con.repaint();
  }
  

  public static String readTile(String[][] strMap,int intX, int intY){
    String strTile;
    strTile = strMap[intX][intY];
    return strTile;
  }
  public static int remap (int intHigh1, int intHigh2, int intValue){
    double dblConverted;
    double dblRatio;
    dblRatio = (double) intHigh1/intHigh2;
    dblConverted = (double)intValue*dblRatio;
    return (int) dblConverted;
  }
  public static void drawStats (Console stats,String strName, int intPlayerHP, int intMaxHP, int intAttack, int intDefense, int intPotion, int intElixer){
    BufferedImage hud;
    hud = stats.loadImage("hud.jpg");
    Font MoveName =stats.loadFont("pkmnfl.ttf", 40);
    stats.setDrawFont(MoveName);
    stats.drawImage(hud,0,0);
    stats.setDrawColor(Color.BLACK);
    stats.drawString(strName,25,365);
    stats.drawString(Integer.toString(intPlayerHP)+"/"+Integer.toString(intMaxHP),160,493);
    stats.drawString(Integer.toString(intAttack),160,548);
    stats.drawString(Integer.toString(intDefense),160,602);
    stats.drawString("x"+Integer.toString(intPotion),75,680);
    stats.drawString("x"+Integer.toString(intElixer),203,680);
    stats.repaint();
  }
}