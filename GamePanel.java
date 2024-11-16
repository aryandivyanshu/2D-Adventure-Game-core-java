package main;
import javax.swing.JPanel;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {

    //SCREEN SETTINGS

    final int originalTileSize = 16; //16 x 16 in-game tiles
    //But modern monitors have higher resolution, 16 x 16 tiled character or environment would look small
    //We will upscale
    final int scale = 3; //make this dynamic, user input based
    final int tileSize = originalTileSize  * scale;

    final int maxScreenColumn = 16; //no. of  tiles per column
    final int maxScreenRow = 12; //no. of  tiles per row
    //this data will be useful to decide screen size

    final int screenWidth = tileSize * maxScreenColumn;
    final int screenHeight = tileSize * maxScreenRow;
    //The screen size is same as the number of tiles that shall fit in our number of Columns and Rows
    //Why dynamic screen size? Any static number might not fit the tiles exactly without leaving gaps.

    //A thread is something you can start and stop
    //A thread can keep your program running until you stop it
    //A thread does not do something special in itself
    //when you wanna do something again and again, eg. Drawing screen 60 times frames per second
    Thread gameThread;


    //CONSTRUCTOR OF THE GamePanel CLASS
    public GamePanel(){
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
    }

    public void startGameThread (){
        gameThread = new Thread(this); //this means this class, here : GamePanel class.
        gameThread.start();
    }

    //automatically implemented from the abstract/interface class "Runnable" when added Thread
    @Override
    public void run() {
        //starting the thread causes the object's run method to be called in that separately executing thread.
        //with this run method, will create game loop.
        while(gameThread != null) { //as long as gameThread exists, it will repeat everything inside this bracket
            System.out.println("Game loop test.");
            //WE DO 2 THINGS INSIDE THE RUN METHOD, THAT IS NEEDED TO BE DONE REPEATEDLY
            //1. UPDATE - BACKEND - WE UPDATE THE INFORMATION CONTINUOUSLY.
            update();
            //2. DRAW - FRONTEND - WE DRAW THE GAME SCREEN BASED ON THE UPDATED PLAYER INFORMATION.
            repaint(); //bit confusing but to call paintComponent() we use repaint()

            //TO DO THIS UPDATE AND DRAW, WE CREATE 2 METHODS : update and paintComponent
        }
    }

    //back-end of the game.
    public void update(){}

    //front-end of the game.
    public void paintComponent(Graphics g){ // actually, is a built-in method in JPanel
        //Graphics is a class that can do graphics stuff, again we see another class' object as an argument
        //Main class ke JFrame ke method me JPanel ka object insert kiya tha, yaha JPanel me Graphics ka kiya
        //on the go the Graphics class object was made , Hence, "(Graphics g)" as parameter instead of just the object name "(g)". Both are same tho.
        //JPanel doesn't care about what Graphic's g object will bring, it just provides Panel for Graphics g.
        //Again, Independent of each other. BEAUTY OF OBJECT ORIENTED PROGRAMMING.

        super.paintComponent(g); //GamePanel is subclass of JPanel, super means JPanel

        Graphics2D g2 = (Graphics2D)g ; //upcasting
        g2.setColor(Color.white);
        g2.fillRect(100,100,tileSize,tileSize);

        g2.dispose();

    }
}