import arc.*;
import java.awt.*;
import java.awt.image.*;
public class battle{
  public static int intPP=30;
  public static int intPP1=15;
  public static int intAttack;
  public static int Battle (Console con, int intHP, String strplayName,int intAttack, int intDefense, int intMaxHP, int intPotions, int intElixer, Boolean blnBoss){
    
    //VARIABLES 
    int intEnemyHP = 40;//starting enemy HP
    int intEnemyOldHP=40;//Max enemy HP
    
    int intRand;
    int intHealthXE;//healthbar x axis enemy
    int intHealthXP;//healthbar x axis player
    int intArrowX=35;//x axis of arrow for menu
    int intArrowY=460;//y axis of arrow for menu
    
    int intMaxPP=30;
    int intMaxPP1=15;
    int intTemp;
    int intLevel=5;
    int intEAttack=5;
    int intMultiplier=1;//attack multiplier
    
    int intCount;
    
    double dblEAttack;
    
    String strName="0";//ENEMY NAME
    String strEAttack="";//ENEMY ATTACK NAME
    String strAttack;//PLAYER ATTACK 1
    String strAttack1;//PLAYER ATTACK 2
    String strAttackCondition="SCRATCH";// ATTACK NAME
    String strType = "";// POKEMON TYPE
    String strFileName;
    char chrAttack=10;
    
    Font MoveName = con.loadFont("pkmnfl.ttf", 40);
    
    Boolean blnAttackUsed=false;
    Boolean blnMove1=true;//START AT MOVE 1
    Boolean blnPP=true;//ENOUGH PP
    Boolean blnPotion = false;//USED POTION
    Boolean blnElixer = false;//USED ELEXIR
    
    strAttack = ("SCRATCH");
    strAttack1 = ("EMBER");
    
    //IMPORT IMAGES
    BufferedImage battle;
    battle = con.loadImage("battle.png");
    BufferedImage moves;
    moves= con.loadImage("moves.png");
    BufferedImage wild;
    wild = con.loadImage("grass.png");
    BufferedImage starter;
    starter = con.loadImage("charmander.png");
    BufferedImage healthbar;
    healthbar = con.loadImage("healthbar.png");
    BufferedImage arrow;
    arrow = con.loadImage("arrow.png");
    BufferedImage textbox;
    textbox = con.loadImage("textbox.png");
    BufferedImage hpdraw;
    hpdraw = con.loadImage("hpdraw.png");
    BufferedImage healthbarp;
    healthbarp = con.loadImage("healthbarp.png");
    BufferedImage healthbare;
    healthbare = con.loadImage("healthbare.png");
    BufferedImage lightning;
    
    BufferedImage cut;
    BufferedImage ember;
    
    BufferedImage battleanimate;
    battleanimate = con.loadImage("battleanimate.png");
    BufferedImage battleanimatep;
    battleanimatep = con.loadImage("battleanimatep.png");
    BufferedImage textbar;
    textbar = con.loadImage("textbar.png");
    BufferedImage outro;
    outro = con.loadImage("outro.jpg");
    
    //GENERATE RANDOM NUMBER FOR RANDOM WILD POKEMON
    // ENEMY ATTACK STAT IS BASE ATTACK*ENEMYLEVEL/PLAYER DEFENSE
    intRand=(int) (Math.random()*2+1);
    if(blnBoss ==true){
      intLevel = 40;
      intEnemyHP = 120;
      intEnemyOldHP = intEnemyHP;
      wild = con.loadImage("pikachu.png");
      strName = "ASH'S PIKACHU";
      strType = "ELECTRIC";
      strEAttack = "THUNDERBOLT";
      intEAttack=150;
      dblEAttack = (double) intEAttack/intDefense;
      intEAttack = (int) dblEAttack;
    }
    else if (intRand == 1){
      intLevel=(int) (Math.random()*16+3);
      intEAttack=8*intLevel;
      intEnemyHP = intLevel*5;
      intEnemyOldHP=intEnemyHP;
      wild = con.loadImage("rattata.png");
      strName = "RATTATA";
      strType = "normal";
      strEAttack = "SCRATCH";
      dblEAttack = (double) intEAttack/intDefense;
      intEAttack = (int) dblEAttack;
    }
    else if (intRand == 2){
      intLevel=(int) (Math.random()*16+3);
      intEAttack=8*intLevel;
      intEnemyHP = intLevel*5;
      intEnemyOldHP=intEnemyHP;
      wild = con.loadImage("caterpie.png");
      strName = "CATERPIE";
      strType = "grass";
      strEAttack = "SWIPE";
      intEAttack=50;
      dblEAttack = (double) intEAttack/intDefense;
      intEAttack = (int) dblEAttack;
    }
    
    if (intEAttack == 0){//ENEMY WONT DO 0 DAMAGE
      intEAttack = 1;
    }
    
    intEnemyOldHP = intEnemyHP;//SETTING MAX HP
    
    //DRAWING BATTLE SCENE
    drawbattle(con,strName, strplayName, strAttack, strAttack1, wild);
    con.drawString( Integer.toString(intPP),758,495);
    con.drawString( Integer.toString(intMaxPP),830,495);
    //DRAWING HEALTHBARS
    con.setDrawColor(Color.GREEN);
    intHealthXE = methodRPG.remap(181, intEnemyOldHP, intEnemyHP);   
    con.fillRect(195,123,intHealthXE,11);
    intHealthXP = methodRPG.remap(181, intMaxHP, intHP);
    if (intHealthXP<=30){
      con.setDrawColor(Color.RED);
    }
    else if (intHealthXP<=90){
      con.setDrawColor(Color.YELLOW);
    }
    else{
      con.setDrawColor(Color.GREEN);
    }
    con.fillRect(654,343,intHealthXP,11);
    
    con.drawImage(arrow,intArrowX,intArrowY);
    con.repaint();
  
    con.setDrawColor(Color.BLACK);
    Font BattleName = con.loadFont("pkmnfl.ttf", 28);
    con.setDrawFont(BattleName);
    con.drawString (intHP+" / " +intMaxHP,720,390);
    con.drawString("Lv"+intLevel,310,90);
    con.repaint();
    
    while (intHP>0 && intEnemyHP>0){//ONLY RUNS WHEN PLAYER OR ENEMY IS ALIVE
      if (blnMove1 == true){//MOVE 1 IS SET
        drawFightMenu(con, strAttack,strAttack1, intPP, intMaxPP);
      }
      else{//MOVE 2 IS SET
        drawFightMenu(con, strAttack,strAttack1, intPP1, intMaxPP1);
      }
      con.drawImage(arrow,intArrowX,intArrowY);
      con.repaint();
      blnAttackUsed=false;//DIDN'T USE MOVE YET
      con.setDrawColor(Color.BLACK);
      chrAttack=con.getChar();
      
      if (chrAttack == '1'){//SELECTED 1ST MOVE
        intArrowX = 35;
        intArrowY = 460;
        intMultiplier=1;
        drawFightMenu(con, strAttack, strAttack1, intPP, intMaxPP);
        strAttackCondition = "SCRATCH";
        con.drawImage(arrow,intArrowX,intArrowY);
        con.repaint();
        blnAttackUsed=false;
        blnMove1 = true;
        blnPotion =false;
        blnElixer = false;
        if (intPP>0){
          blnPP=true;
        }
        else{
          blnPP=false;
        }
      }
      else if (chrAttack=='2'){//SELECTED 2ND MOVE
        intArrowX = 280;
        intArrowY = 460;
        intMultiplier = 2;
        drawFightMenu(con, strAttack, strAttack1,intPP1, intMaxPP1);
        strAttackCondition = "EMBER";
        con.drawImage(arrow,intArrowX,intArrowY);
        con.repaint();
        blnAttackUsed=false;
        blnMove1 = false;
        blnPotion = false;
        blnElixer = false;
        if (intPP1>0){
          blnPP=true;
        }
        else{
          blnPP=false;
        }
      }
      
      else if (chrAttack=='3'){//SELECTED POTION
        intArrowX = 35;
        intArrowY = 522;
        drawFightMenuNoPP(con, strAttack, strAttack1);
        con.drawImage(arrow,intArrowX,intArrowY);
        con.repaint();
        blnAttackUsed=false;
        blnMove1 = false;
        blnPotion = true;
        blnElixer = false;
        if (intPotions>0){
          blnPP=true;
        }
        else{
          blnPP=false;
        }
      }
      else if (chrAttack=='4'){//SELECTED ELEXIR
        intArrowX = 280;
        intArrowY = 522;
        drawFightMenuNoPP(con, strAttack, strAttack1);
        con.drawImage(arrow,intArrowX,intArrowY);
        con.repaint();
        blnAttackUsed=false;
        blnMove1 = false;
        blnPotion = false;
        blnElixer = true;
        if (intElixer>0){
          blnPP=true;
        }
        else{
          blnPP=false;
        }
      }
      //IF AN ATTACK MOVE IS USED
      else if (chrAttack == ' ' && blnPP==true && blnPotion == false && blnElixer == false){
        con.drawImage(textbox,0,0);
        con.setDrawColor(Color.WHITE);
        MoveName = con.loadFont("pkmnfl.ttf", 40);
        con.setDrawFont(MoveName);
        con.drawString(strplayName+" used "+strAttackCondition+"!",35,490);
        con.repaint();
        con.sleep(600);
        //FIGHTING ANIMATIONS
        if (strAttackCondition.equals("SCRATCH")){
          for (intCount=1; intCount<6; intCount++) {//LOAD AND DRAW THE 5 IMAGES
            strFileName = "cut"+intCount+".png";
            cut = con.loadImage(strFileName);
            con.drawImage(cut,610,130);
            con.repaint();
            con.sleep(100);
            con.drawImage(battleanimate,0,0);
            con.drawImage(wild,583,130);
          }
          intPP = intPP-1;
        }
        
        else if (strAttackCondition.equals("EMBER")){
          for (intCount=1; intCount<6; intCount++) {//LOAD AND DRAW 5 IMAGES
            strFileName = "ember"+intCount+".png";
            ember = con.loadImage(strFileName);
            con.drawImage(ember,610,130);
            con.repaint();
            con.sleep(100);
            con.drawImage(battleanimate,0,0);
            con.drawImage(wild,583,130);
          }
          if (strType.equals("grass")&&strAttackCondition.equals("EMBER")){//EMBER IS SUPEREFFECTIVE VS GRASS
            intMultiplier=3;
          }
          intPP1 = intPP1-1;
        }
        blnAttackUsed = true;
        intEnemyHP = intEnemyHP-(intAttack*intMultiplier);
        if (intEnemyHP<0){//SET HP TO 0 WHEN LESS THAN 0
          intEnemyHP=0;
        }
        
        
        //REDRAWING THE HEALTHBAR ENEMY
        intTemp = intHealthXE;
        intHealthXE = methodRPG.remap(181, intEnemyOldHP, intEnemyHP);   
        if (intHealthXE<=30){
          con.setDrawColor(Color.RED);
        }
        else if (intHealthXE<=90){
          con.setDrawColor(Color.YELLOW);
        }
        else{
          con.setDrawColor(Color.GREEN);
        }
        for (intCount=intTemp; intCount>intHealthXE;intCount--){
          if (intCount<=30){
            con.setDrawColor(Color.RED);
          }
          else if (intCount<=90){
            con.setDrawColor(Color.YELLOW);
          }
          else{
            con.setDrawColor(Color.GREEN);
          }
          con.drawImage(healthbare,0,0);
          con.fillRect(195,123,intCount,11);
          con.repaint();
          con.sleep(15);
        }
        
        if (strType.equals("grass")&& strAttackCondition.equals("EMBER")){
          con.setDrawColor(Color.WHITE);
          con.drawImage(textbox,0,0);
          con.drawString("It's super effective!",35,490);
          con.repaint();
          con.sleep(1000);
        }
        
        
        //REDRAW BATTLE SCENE
        con.drawImage(moves,0,422);
        con.setDrawColor(Color.BLACK);
        con.drawString(strAttack,60, 490);
        con.drawString(strAttack1,310, 490);
        con.drawString("RUN",60, 550);
        con.drawString( Integer.toString(intPP),758,495);
        con.drawString( Integer.toString(intMaxPP),830,495);
        con.drawImage(arrow,intArrowX,intArrowY);
        intHealthXP = methodRPG.remap(181, intMaxHP, intHP);
        if (intHealthXP<=30){
          con.setDrawColor(Color.RED);
        }
        else if (intHealthXP<=90){
          con.setDrawColor(Color.YELLOW);
        }
        else{
          con.setDrawColor(Color.GREEN);
        }
        
        con.fillRect(654,343,intHealthXP,11);
      }
      
      else if (chrAttack ==' ' && blnPP == true && blnPotion == true && intHP!=intMaxHP){//USE POTION, CANNOT USE POTION WHEN MAX HP
        intHP = intHP+20;
        con.setDrawFont(MoveName);
        con.setDrawColor(Color.WHITE);
        con.drawImage(textbox,0,0);
        con.drawString(strplayName+" used a POTION.",35,490);
        con.repaint();
        con.sleep(400);
        if (intHP>intMaxHP){
          intHP = intMaxHP;
        }
        intTemp = intHealthXP;
        intHealthXP = methodRPG.remap(181, intMaxHP, intHP);
        //ANIMATE HEALTH BAR
        for (intCount =intTemp; intCount<intHealthXP;intCount++){
          if (intCount<=30){
            con.setDrawColor(Color.RED);
          }
          else if (intCount<=90){
            con.setDrawColor(Color.YELLOW);
          }
          else{
            con.setDrawColor(Color.GREEN);
          }
          con.drawImage(healthbarp,0,0);
          con.fillRect(654,343,intCount,11);
          con.repaint();
          con.sleep(15);
        }       
        if (intHealthXE<=30){
          con.setDrawColor(Color.RED);
        }
        else if (intHealthXE<=90){
          con.setDrawColor(Color.YELLOW);
        }
        else{
          con.setDrawColor(Color.GREEN);
        }
        con.setDrawColor(Color.BLACK);
        con.drawImage(hpdraw,0,0);
        BattleName = con.loadFont("pkmnfl.ttf", 28);
        con.setDrawFont(BattleName);
        con.drawString (intHP+" / " +intMaxHP,720,390);
        blnAttackUsed=true;
      }
      else if (chrAttack ==' ' && blnPP == true && blnElixer == true){//USE ELEXIR
        con.setDrawFont(MoveName);
        con.setDrawColor(Color.WHITE);
        con.drawImage(textbox,0,0);
        con.drawString(strplayName+" used an ELIXER.",35,490);
        con.repaint();
        con.sleep(1200);
        intPP1 = intMaxPP1;
        intPP = intMaxPP;
        blnAttackUsed=true;
      }
      else if (chrAttack ==' ' && blnPP ==false){//NOT ENOUGH PP WHEN SELECT MOVES
        MoveName = con.loadFont("pkmnfl.ttf", 40);
        con.setDrawFont(MoveName);
        con.setDrawColor(Color.WHITE);
        con.drawImage(textbox,0,0);
        con.drawString("Not enough PP.",35,490);
        con.repaint();
        con.sleep(1200);
        con.setDrawColor(Color.BLACK);
        
      }
      //ENEMY ATTACK
      if ( blnAttackUsed == true && intEnemyHP>0){
        //REDRAW HEALTH BAR PLAYER
        //Enemy Attack
        con.setDrawColor(Color.WHITE);
        if (strEAttack.equals("THUNDERBOLT")){//ANIMATE ATTACK
          MoveName = con.loadFont("pkmnfl.ttf", 40);
          con.setDrawFont(MoveName);
          for (intCount=1; intCount<6; intCount++) {
            
            strFileName = "lightning"+intCount+".png";
            lightning = con.loadImage(strFileName);
            con.drawImage(lightning,280,220);
            con.repaint();
            con.sleep(100);
            con.drawImage(battleanimatep,0,0);
            con.drawImage(starter,155,211);
            con.drawImage(textbox,0,0);
            con.drawString("Foe "+strName+" used "+strEAttack+"!",35,490);
            con.repaint();
          }
        }
        else{
          MoveName = con.loadFont("pkmnfl.ttf", 40);
          con.setDrawFont(MoveName);
          for (intCount=1; intCount<6; intCount++) {//ANIMATE ATTACK
            strFileName = "cut"+intCount+".png";
            cut = con.loadImage(strFileName);
            con.drawImage(cut,280,250);
            con.repaint();
            con.sleep(100);
            con.drawImage(battleanimatep,0,0);
            con.drawImage(starter,155,211);
            con.drawImage(textbox,0,0);
            
            con.drawString("Foe "+strName+" used "+strEAttack,35,490);
            con.repaint();
          }
        }
        con.sleep(1000);
        intHP = intHP-intEAttack;
        intTemp = intHealthXP;
        intHealthXP = methodRPG.remap(181, intMaxHP, intHP);
        //ANIMATE HEALTHBAR
        for (intCount =intTemp; intCount>intHealthXP;intCount--){
          if (intCount<=30){
            con.setDrawColor(Color.RED);
          }
          else if (intCount<=90){
            con.setDrawColor(Color.YELLOW);
          }
          else{
            con.setDrawColor(Color.GREEN);
          }
          con.drawImage(healthbarp,0,0);
          con.fillRect(654,343,intCount,11);
          con.repaint();
          con.sleep(30);
        }
        
        if (intHealthXE<=30){
          con.setDrawColor(Color.RED);
        }
        else if (intHealthXE<=90){
          con.setDrawColor(Color.YELLOW);
        }
        else{
          con.setDrawColor(Color.GREEN);
        }
        con.fillRect(195,123,intHealthXE,11);
        con.drawImage(hpdraw,0,0);
        con.setDrawColor(Color.BLACK);
        BattleName = con.loadFont("pkmnfl.ttf", 28);
        con.setDrawFont(BattleName);
        con.drawString (intHP+" / " +intMaxHP,720,390);
        con.repaint();
      }
      
    }
    if (intEnemyHP<=0 && blnBoss ==true){//BOSS BATTLE ENDING IF WON
      con.setDrawColor(Color.WHITE);
      con.drawImage(textbox,0,0);
      MoveName = con.loadFont("pkmnfl.ttf", 40);
      con.setDrawFont(MoveName);
      con.drawString("Foe "+strName+" fainted!",35,490);
      con.repaint();
      con.sleep(1200);
      con.drawImage(outro,0,0);
      con.drawString("ASH: Come back, "+strplayName+". You are worthy.", 60,800);
      con.repaint();
      con.sleep(2000);
      chrAttack = con.getChar();
      con.closeConsole();
    }
    if (intEnemyHP<=0 && blnBoss == false){//KILLING ENEMY DRAWING
      con.setDrawColor(Color.WHITE);
      con.drawImage(textbox,0,0);
      MoveName = con.loadFont("pkmnfl.ttf", 40);
      con.setDrawFont(MoveName);
      con.drawString("Foe "+strName+" fainted!",35,490);
      intAttack = intAttack+2;
      con.repaint();
      con.sleep(1000);
    }
    
    return intHP;
  }
  public static int statAttack(int intAttack){
    intAttack = intAttack+2;
    return intAttack;
  }
  public static int statDefense (int intDefense){
    intDefense = intDefense+2;
    return intDefense;
  }
  public static int statMaxHP (int intMaxHP){
    intMaxHP = intMaxHP+3;
    return intMaxHP;
  }
  public static void drawbattle(Console con, String strName, String strplayName, String strAttack,String strAttack1, BufferedImage wild){
    BufferedImage battle;
    battle = con.loadImage("battle.png");
    BufferedImage moves;
    moves= con.loadImage("moves.png");
    BufferedImage starter;
    starter = con.loadImage("charmander.png");
    BufferedImage healthbar;
    healthbar = con.loadImage("healthbar.png");
    
    con.drawImage(battle,0,0);
    con.drawImage(moves,0,422);
    con.drawImage(wild,583,130);
    con.drawImage(starter,155,211);
    
    con.setDrawColor(Color.BLACK);
    Font BattleName = con.loadFont("pkmnfl.ttf", 28);
    con.setDrawFont(BattleName);
    con.drawString(strName,72,90);
    con.drawString(strplayName,532,310);
    Font MoveName = con.loadFont("pkmnfl.ttf", 40);
    con.setDrawFont(MoveName);
    con.drawString(strAttack,60, 490);
    con.drawString(strAttack1,310, 490);
    con.drawString("RUN",60, 550);
    con.repaint();
  }
  public static void drawFightMenu(Console con, String strAttack,String strAttack1, int intPP1, int intMaxPP1){
    BufferedImage moves;
    moves= con.loadImage("moves.png");
    con.drawImage(moves,0,422);
    Font MoveName = con.loadFont("pkmnfl.ttf", 40);
    con.setDrawFont(MoveName);
    con.drawString(strAttack,60, 490);
    con.drawString(strAttack1,310, 490);
    con.drawString("POTION",60, 550);
    con.drawString("ELIXER",310, 550);
    con.drawString( Integer.toString(intPP1),758,495);
    con.drawString( Integer.toString(intMaxPP1),830,495);
    con.repaint();
  }
  public static void drawFightMenuNoPP(Console con, String strAttack,String strAttack1){
    BufferedImage moves;
    moves= con.loadImage("moves.png");
    con.drawImage(moves,0,422);
    Font MoveName = con.loadFont("pkmnfl.ttf", 40);
    con.setDrawFont(MoveName);
    con.drawString(strAttack,60, 490);
    con.drawString(strAttack1,310, 490);
    con.drawString("POTION",60, 550);
    con.drawString("ELIXER",310, 550);
    con.repaint();
  }
  
}