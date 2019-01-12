import arc.*;
import java.awt.*;
import java.awt.image.*;
public class rpg{
  public static void main (String[]args){
    String strName;
    Console con = new Console(900,900);
    con.clear();
    int intCount=0;
    int intCount1;
    int intPotion=0;
    int intElixer=0;
    char chrMove='s';
    char chrUse = ' ';

    char chrMenu=' ';
    String strPlayer[][];
    String strSplit[];
    String strMap[][];
    String strItems[][];
    String strMapName="map1.csv";
    Boolean blnMap=true;
    Boolean blnBoss=false;
    int intMouseX;
    int intMouseY;
    
    
    
    //LOAD IMAGES
    BufferedImage down;
    down = con.loadImage("down.png");
    BufferedImage down1;
    down1 = con.loadImage("down1.png");
    BufferedImage down2;
    down2 = con.loadImage("down2.png");
    
    BufferedImage up;
    up = con.loadImage("up.png");
    BufferedImage up1;
    up1 = con.loadImage("up1.png");
    BufferedImage up2;
    up2 = con.loadImage("up2.png");
    
    BufferedImage right;
    right = con.loadImage("right.png");
    BufferedImage right1;
    right1 = con.loadImage("right1.png");
    BufferedImage right2;
    right2 = con.loadImage("right2.png");
    
    BufferedImage left;
    left = con.loadImage("left.png");
    BufferedImage left1;
    left1 = con.loadImage("left1.png");
    BufferedImage left2;
    left2 = con.loadImage("left2.png");
    
    
    
    BufferedImage gameover;
    gameover = con.loadImage("gameover.jpg");
    BufferedImage help;
    help = con.loadImage("help.jpg");
    BufferedImage title;
    title = con.loadImage("title.jpg");
    BufferedImage textbar;
    textbar = con.loadImage("textbar.png");
    BufferedImage outro;
    outro = con.loadImage("outro.jpg");
    
    BufferedImage tip;
    tip = con.loadImage("tip.jpg");
    
    
    //LOADING THE MAP CSV INTO ARRAY
    strMap = methodRPG.loadMap(strMapName);
    strPlayer = methodRPG.loadMap("playermove.csv");
    strItems = methodRPG.loadMap("items.csv");
    
    con.drawImage(title,0,0);
    con.repaint();
    chrMenu = con.getChar();
    con.drawImage(tip,0,0);
    con.repaint();
    chrMenu = con.getChar();
    
    methodRPG.drawMap(con,strMap);//draw map
    methodRPG.drawMap(con,strItems);//draw map
    
    //set array coordinates to x y coordinates
    int intX=0;
    int intY=0;
    
    for (intCount=0;intCount<20;intCount++){
      for (intCount1 = 0 ; intCount1<20;intCount1++){
        if (strPlayer [intCount1][intCount].equals("p")){
          intX = intCount1*45;
          intY = intCount*45;
        }
      }
    }
    
    con.drawImage(down,intX,intY);
    int intOldY;
    int intOldX;
    int intMoveCount;
    int intRand;
    int intPlayerHP=60;
    int intMaxHP = intPlayerHP;
    int intAttack = 10;
    int intDefense =10;
    boolean blnInBattle=false;
    
    //INTRO DRAWING
    Font MoveName =con.loadFont("pkmnfl.ttf", 40);
    con.setDrawFont(MoveName);
    con.drawImage(textbar,0,720);
    con.drawString("Where am I?", 60,800);
    con.repaint();
    chrMenu=con.getChar();
    con.drawImage(textbar,0,720);
    con.drawString("Where's Ash?", 60,800);
    con.repaint();
    chrMenu=con.getChar();
    con.drawImage(textbar,0,720);
    con.drawString("Hold on - what's my name again?", 60,800);
    con.repaint();
    for (intCount=0;intCount<32;intCount++){
    con.println();
    }
    con.print("     ");
    strName = con.readLine();
    con.clear();
    con.drawImage(textbar,0,720);
    con.drawString("Well looks like I better be going.", 60,800);
    con.repaint();
    chrMenu=con.getChar();
    
    
    methodRPG.drawMap(con,strMap);//draw map
    methodRPG.drawMap(con,strItems);//draw map
    con.drawImage(down,intX,intY);//DRAW PLAYER
    con.repaint();
    //DRAWING STATS WINDOW
    Console stats = new Console(301,901);
    BufferedImage hud;
    hud = stats.loadImage("hud.jpg");
    methodRPG.drawStats(stats,strName,intPlayerHP,intMaxHP, intAttack, intDefense, intPotion, intElixer);
    
    while (intPlayerHP>0) {
      if (blnInBattle ==false && blnMap==true){
        //con.drawImage(up,intX,intY);
        chrMove = con.currentChar();
        con.sleep(1);
        if (chrMove == 'w'||chrMove == 'W'){
          intOldY = intY;
          movement.up(con, intOldY, intX, strMap);
          con.drawImage(up,intX,intY);
          con.repaint();
          //MOVEMENT
          if (strMap[((intY-45)/45)][intX/45].equals("grass") && !strItems[((intY-45)/45)][intX/45].equals("pokeball") || strMap[((intY-45)/45)][intX/45].equals("water") || strMap[((intY-45)/45)][intX/45].equals("door") || strMap[((intY-45)/45)][intX/45].equals("edge") || strMap[((intY-45)/45)][intX/45].equals("ground") ){
            for (intMoveCount = 0;intMoveCount<3;intMoveCount++) {
              intY = intY-3; 
              movement.ad(con, intX, intOldY,strMap);
              con.drawImage(up1,intX,intY);
              con.sleep(25);
              intY = intY-3;
              movement.ad(con, intX, intOldY,strMap);
              con.drawImage(up,intX,intY);
              con.sleep(25);
              intY = intY-3;
              movement.ad(con, intX, intOldY,strMap);
              con.drawImage(up2,intX,intY);
              con.sleep(25);
              intY = intY-3;
              movement.ad(con, intX, intOldY,strMap);
              con.drawImage(up,intX,intY);
              con.repaint();
              con.sleep(25);
              intY = intY-3;
            }
          }
          
          if (strMap[intY/45][intX/45].equals("grass")){
            intRand=(int) (Math.random()*8+1);
            if (intRand == 2){
              blnInBattle = true;
            }
            
          }
          else if (strMap[intY/45][intX/45].equals("water")){
            intPlayerHP = 0; 
          }
          else if (strMap[intY/45][(intX)/45].equals("door")){
            blnMap = false;
          }
          else if (strMap[(intY-45)/45][(intX)/45].equals("boss")){
            blnBoss = true;
            blnInBattle = true;
          }
//FINDING AN ITEM
          while (strItems[(intY-45)/45][intX/45].equals("pokeball")){
            MoveName =con.loadFont("pkmnfl.ttf", 40);
            con.setDrawFont(MoveName);
            con.drawImage(textbar,0,720);
            
            intRand=(int) (Math.random()*4+1);
            if (intRand ==1){
              intElixer = intElixer+1;
              
              System.out.println("picked up elixer");
              con.drawString(strName+" found an ELIXER", 60,800);
              con.repaint();
            }
            else{
              System.out.println("picked up potion");
              con.drawString(strName+" found a POTION", 60,800);
              con.repaint();
              intPotion = intPotion+1;
            }
            
            //DRAWING STATS AFTER ITEM
            con.sleep(1000);
            methodRPG.drawMap(con,strMap);//draw map
            strItems[(intY-45)/45][(intX)/45] = "blank";
            methodRPG.drawMap(con, strItems);
            movement.drawDirection(con,chrMove,intX,intY);
            con.repaint();
            methodRPG.drawStats(stats,strName,intPlayerHP,intMaxHP, intAttack, intDefense, intPotion, intElixer);
          }
        }
        
        
        //MOVE DOWN
        else if (chrMove == 's' || chrMove == 'S'){
          intOldY = intY;
          movement.up(con, intOldY, intX, strMap);
          con.drawImage(down,intX,intY);
          if (strMap[((intY+45)/45)][intX/45].equals("grass") && !strItems[((intY+45)/45)][intX/45].equals("pokeball") || strMap[((intY+45)/45)][intX/45].equals("water")|| strMap[((intY+45)/45)][intX/45].equals("edge")|| strMap[((intY+45)/45)][intX/45].equals("ground") ){
            
            for (intMoveCount = 0;intMoveCount<3;intMoveCount++) {
              intY = intY+3;
              movement.ad(con, intX, intOldY,strMap);
              con.drawImage(down1,intX,intY);
              con.sleep(25);
              intY = intY+3;
              movement.ad(con, intX, intOldY,strMap);
              con.drawImage(down,intX,intY);
              con.sleep(25);
              intY = intY+3;
              movement.ad(con, intX, intOldY,strMap);
              con.drawImage(down2,intX,intY);
              con.sleep(25);
              intY = intY+3;
              movement.ad(con, intX, intOldY,strMap);
              con.drawImage(down,intX,intY);
              con.sleep(25);
              intY = intY+3;
            }
            con.repaint();
          }
          while (strItems[(intY+45)/45][intX/45].equals("pokeball")){
            MoveName =con.loadFont("pkmnfl.ttf", 40);
            con.setDrawFont(MoveName);
            con.drawImage(textbar,0,720);
            
            intRand=(int) (Math.random()*4+1);
            if (intRand ==1){
              intElixer = intElixer+1;
              
              System.out.println("picked up elixer");
              con.drawString(strName+" found an ELIXER", 60,800);
              con.repaint();
            }
            else{
              System.out.println("picked up potion");
              con.drawString(strName+" found a POTION", 60,800);
              con.repaint();
              intPotion = intPotion+1;
            }
            
            
            con.sleep(1000);
            methodRPG.drawMap(con,strMap);//draw map
            strItems[(intY+45)/45][(intX)/45] = "blank";
            methodRPG.drawMap(con, strItems);
            movement.drawDirection(con,chrMove,intX,intY);
            con.repaint();
            methodRPG.drawStats(stats,strName,intPlayerHP,intMaxHP, intAttack, intDefense, intPotion, intElixer);
          }
          if (strMap[intY/45][intX/45].equals("grass")){
            intRand=(int) (Math.random()*8+1);
            if (intRand == 2){
              blnInBattle = true;
            }
          }
          else if (strMap[intY/45][intX/45].equals("water")){
            intPlayerHP = 0; 
          }
          else{
            
          }
        }
        //MOVE LEFT
        else if (chrMove == 'a' || chrMove == 'A'){
          intOldX= intX;
          movement.ad(con, intOldX, intY,strMap);
          con.drawImage(left,intX,intY);
          if (strMap[intY/45][(intX-45)/45].equals("grass")  && !strItems[intY/45][(intX-45)/45].equals("pokeball") || strMap[intY/45][(intX-45)/45].equals("water") || strMap[intY/45][(intX-45)/45].equals("edge") && !strMap[intY/45][(intX-45)/45].equals("pokeball")  || strMap[intY/45][(intX-45)/45].equals("ground") ){
            
            for (intMoveCount = 0;intMoveCount<3;intMoveCount++) {
              intX = intX-3;
              movement.ad(con, intOldX, intY,strMap);
              con.drawImage(left1,intX,intY);
              con.sleep(25);
              intX = intX-3;
              movement.ad(con, intOldX, intY,strMap);
              con.drawImage(left,intX,intY);
              con.sleep(25);
              intX = intX-3;
              movement.ad(con, intOldX, intY,strMap);
              con.drawImage(left2,intX,intY);
              con.sleep(25);
              intX = intX-3;
              movement.ad(con, intOldX, intY,strMap);
              con.repaint();
              con.drawImage(left,intX,intY);
              con.repaint();
              con.sleep(25);
              intX = intX-3;
            }
          }
          
          if (strMap[intY/45][intX/45].equals("grass")){
            intRand=(int) (Math.random()*8+1);
            if (intRand == 2){
              blnInBattle = true;
            }
          }
          else if (strMap[intY/45][intX/45].equals("water")){
            intPlayerHP = 0; 
          }
          else{
          }
          while (strItems[(intY)/45][(intX-45)/45].equals("pokeball")){
            MoveName =con.loadFont("pkmnfl.ttf", 40);
            con.setDrawFont(MoveName);
            con.drawImage(textbar,0,720);
            
            intRand=(int) (Math.random()*4+1);
            if (intRand ==1){
              intElixer = intElixer+1;
              
              System.out.println("picked up elixer");
              con.drawString(strName+" found an ELIXER", 60,800);
              con.repaint();
            }
            else{
              System.out.println("picked up potion");
              con.drawString(strName+" found a POTION", 60,800);
              con.repaint();
              intPotion = intPotion+1;
            }
            
            
            con.sleep(1000);
            methodRPG.drawMap(con,strMap);//draw map
            strItems[(intY)/45][(intX-45)/45] = "blank";
            methodRPG.drawMap(con, strItems);
            movement.drawDirection(con,chrMove,intX,intY);
            con.repaint();
            methodRPG.drawStats(stats,strName,intPlayerHP,intMaxHP, intAttack, intDefense, intPotion, intElixer);
          }
        }
        //MOVE RIGHT
        else  if (chrMove == 'd'|| chrMove == 'D'){
          intOldX= intX;
          movement.ad(con, intOldX, intY,strMap);
          con.drawImage(left,intX,intY);
          if (strMap[intY/45][(intX+45)/45].equals("grass") && !strItems[intY/45][(intX+45)/45].equals("pokeball") || strMap[intY/45][(intX+45)/45].equals("water") || strMap[intY/45][(intX+45)/45].equals("edge") && !strMap[intY/45][(intX+45)/45].equals("pokeball") || strMap[intY/45][(intX+45)/45].equals("ground")){
            
            for (intMoveCount = 0;intMoveCount<3;intMoveCount++) {
              intX = intX+3;
              movement.ad(con, intOldX, intY,strMap);
              con.drawImage(right1,intX,intY);
              con.sleep(25);
              intX = intX+3;
              movement.ad(con, intOldX, intY,strMap);;
              con.drawImage(right,intX,intY);
              con.sleep(25);
              intX = intX+3;
              movement.ad(con, intOldX, intY,strMap);
              con.drawImage(right2,intX,intY);
              con.sleep(25);
              intX = intX+3;
              movement.ad(con, intOldX, intY,strMap);
              con.drawImage(right,intX,intY);
              con.repaint();
              con.sleep(25);
              intX = intX+3;
            }
            
          }
          
          if (strMap[intY/45][intX/45].equals("grass")){
            intRand=(int) (Math.random()*8+1);
            if (intRand == 2){
              blnInBattle = true;
            }
          }
          else if (strMap[intY/45][intX/45].equals("water")){
            intPlayerHP = 0; 
          }
          while (strItems[(intY)/45][(intX+45)/45].equals("pokeball")){
            MoveName =con.loadFont("pkmnfl.ttf", 40);
            con.setDrawFont(MoveName);
            con.drawImage(textbar,0,720);
            
            intRand=(int) (Math.random()*4+1);
            if (intRand ==1){
              intElixer = intElixer+1;
              
              System.out.println("picked up elixer");
              con.drawString(strName+" found an ELIXER", 60,800);
              con.repaint();
            }
            else{
              System.out.println("picked up potion");
              con.drawString(strName+" found a POTION", 60,800);
              con.repaint();
              intPotion = intPotion+1;
            }
            
            
            con.sleep(1000);
            methodRPG.drawMap(con,strMap);//draw map
            strItems[(intY)/45][(intX+45)/45] = "blank";
            methodRPG.drawMap(con, strItems);
            movement.drawDirection(con,chrMove,intX,intY);
            con.repaint();
            methodRPG.drawStats(stats,strName,intPlayerHP,intMaxHP, intAttack, intDefense, intPotion, intElixer);
          }
        }
        else if (chrMove =='1'){
          
          intPotion = intPotion-1;
          intPlayerHP = intPlayerHP+20;
          if (intPlayerHP>intMaxHP){
            intPlayerHP = intMaxHP;
          }
          MoveName =con.loadFont("pkmnfl.ttf", 40);
          con.setDrawFont(MoveName);
          con.drawImage(textbar,0,720);
          con.drawString(strName+" used a POTION", 60,800);           
          con.repaint();
          con.sleep(1000);
          methodRPG.drawStats(stats,strName,intPlayerHP,intMaxHP, intAttack, intDefense, intPotion, intElixer);
          methodRPG.drawMap(con,strMap);//draw map
          methodRPG.drawMap(con,strItems);//draw map
          con.drawImage(down,intX,intY);
          con.repaint();
        }
        if (chrMove == 'h' || chrMove=='H'){
          con.drawImage(help,0,0);
          con.repaint();
          chrMenu = con.getChar();
          methodRPG.drawMap(con,strMap);//draw map
          methodRPG.drawMap(con,strItems);//draw map
        }
      }
      //CHANGING THE MAP
      if (blnMap == false){
        if (strMap[intY/45][(intX)/45].equals("door")){
          strMapName = "map2.csv";
          strItems = methodRPG.loadMap("map2.csv");
          intY = 17*45;
          intX = 10*45;
        }
        else if (strMap[intY/45][(intX+45)/45].equals("entrance")){
          strMapName = "map1.csv";
        }
        strMap = methodRPG.loadMap(strMapName);
        methodRPG.drawMap(con,strMap);
        //con.drawImage(up,intY,intX);
        
        blnMap=true;
      }
      //OPEN BATTLE METHOD
      else if (blnInBattle == true){
        System.out.println("attack"+intAttack);
        intPlayerHP = battle.Battle(con,intPlayerHP,strName,intAttack, intDefense, intMaxHP, intPotion, intElixer, blnBoss);
        methodRPG.drawMap(con,strMap);
        methodRPG.drawMap(con,strItems);//draw map
        movement.drawDirection(con,chrMove,intX,intY);
        blnInBattle = false;
        intAttack = battle.statAttack(intAttack);
        intDefense = battle.statDefense(intDefense);
        if (intPlayerHP<0){
          intPlayerHP = 0;
        }
        intMaxHP = battle.statMaxHP(intMaxHP);
        methodRPG.drawStats(stats,strName,intPlayerHP,intMaxHP, intAttack, intDefense, intPotion, intElixer);
      }
      
    }
    //LOSING SCREEN
    con.setDrawColor(Color.WHITE);
    con.drawImage(outro,0,0);
    MoveName =con.loadFont("pkmnfl.ttf", 40);
    con.setDrawFont(MoveName);
    con.drawString("ASH: Sorry "+strName+", you are not yet ready for me.", 60,800);
    con.repaint();
    con.sleep(3000);
    con.drawImage(gameover,0,0);
    con.repaint();
    con.sleep(2000);
    con.closeConsole();
  }
  
}

