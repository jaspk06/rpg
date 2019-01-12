import arc.*;
import java.awt.*;
import java.awt.image.*;

public class movement{
  public static BufferedImage tile;
  public static String strTile;
  public static BufferedImage up;
  public static BufferedImage down;
  public static BufferedImage left;
  public static BufferedImage right;
  public static BufferedImage oak;
  public static void up (Console con, int intOldY, int intX, String[][] strMap){//UNDER DEVELOPMENT
    strTile = methodRPG.readTile(strMap,intOldY/45, intX/45);
    tile = con.loadImage(strTile+".png");
    con.drawImage(tile,intX,intOldY);
    strTile = methodRPG.readTile(strMap,(intOldY+45)/45, (intX)/45);
    tile = con.loadImage(strTile+".png");
    con.drawImage(tile,intX,intOldY+45);
    strTile = methodRPG.readTile(strMap,(intOldY-45)/45, (intX)/45);
    tile = con.loadImage(strTile+".png");
    con.drawImage(tile,intX,intOldY-45);
    con.repaint();
  }
  //DRAWING 9 TILES AROUND THE PLAYER WHEN MOVING
  public static void ad (Console con, int intOldX, int intY, String[][] strMap){

   
    strTile = methodRPG.readTile(strMap,intY/45, intOldX/45);
    tile = con.loadImage(strTile+".png");
    con.drawImage(tile,intOldX,intY);
    strTile = methodRPG.readTile(strMap,(intY)/45, (intOldX+45)/45);
    tile = con.loadImage(strTile+".png");
    con.drawImage(tile,intOldX+45,intY);
    strTile = methodRPG.readTile(strMap,(intY)/45, (intOldX-45)/45);
    tile = con.loadImage(strTile+".png");
    con.drawImage(tile,intOldX-45,intY);
    strTile = methodRPG.readTile(strMap,(intY+45)/45, (intOldX+45)/45);
    tile = con.loadImage(strTile+".png");
    con.drawImage(tile,intOldX+45,intY+45);
    strTile = methodRPG.readTile(strMap,(intY-45)/45, (intOldX-45)/45);
    tile = con.loadImage(strTile+".png");
    con.drawImage(tile,intOldX-45,intY-45);
    strTile = methodRPG.readTile(strMap,(intY-45)/45, (intOldX+45)/45);
    tile = con.loadImage(strTile+".png");
    con.drawImage(tile,intOldX+45,intY-45);
    strTile = methodRPG.readTile(strMap,(intY+45)/45, (intOldX-45)/45);
    tile = con.loadImage(strTile+".png");
    con.drawImage(tile,intOldX-45,intY+45);
    strTile = methodRPG.readTile(strMap,(intY+45)/45, (intOldX)/45);
    tile = con.loadImage(strTile+".png");
    con.drawImage(tile,intOldX,intY+45);
    strTile = methodRPG.readTile(strMap,(intY-45)/45, (intOldX)/45);
    tile = con.loadImage(strTile+".png");
    con.drawImage(tile,intOldX,intY-45);
    con.repaint();
  }
  public static void drawDirection (Console con, char chrMove, int intX, int intY){//DRAWING SPAWN DIRECTION
    up = con.loadImage("up.png");
    down = con.loadImage("down.png");
    left = con.loadImage("left.png");
    right = con.loadImage("right.png");
    if (chrMove=='w'){
      con.drawImage(up,intX,intY);
    }
    else if (chrMove=='s'){
      con.drawImage(down,intX,intY);
    }
    else if (chrMove=='a'){
      con.drawImage(left,intX,intY);
    }
    else if (chrMove=='d'){
      con.drawImage(right,intX,intY);
    }
    else{
      con.drawImage(down,intX,intY);
    }
    con.repaint();
  }
}