import javafx.animation.AnimationTimer;
import javafx.event.*;
import javafx.scene.Scene;
import javafx.scene.text.Text;
import javafx.scene.text.Font;
import javafx.scene.paint.Color;
import javafx.geometry.Insets;
import javafx.scene.shape.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.GridPane;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import javafx.scene.Group;
import javafx.application.Application;
import javafx.scene.shape.Box;
import javafx.scene.paint.PhongMaterial;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import javafx.scene.layout.StackPane;
import javafx.geometry.Pos;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;

/**
 * Write a description of JavaFX class Inconvenience here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class TypingKing extends Application
{
    //Word Section:
    //The Word Being Typed
    private Text theWord;
    private String theWordStr;
    //The Word to Type
    private String word = "";
    private Text wordText;

    //File IO Section:
    private BufferedReader reader;
    private ArrayList<String> wordList;

    //Utility Section:
    private Random rd;

    //Timer Section:
    private int timerInt = 0;
    private Arc timerArc;
    private AnimationTimer timer;

    //Game Checking Section:
    private boolean wordDone = false;

    //Score and Game Vars Section:
    private int score = 0;
    private Text theScore;
    
    //Javafx Utility Section:
    private StackPane root;
    private Scene scene1;
    
    //Mode Section:
    private boolean mouse = false;
    private boolean key = false;

    //Handler Section:
    private EventHandler handler = new EventHandler<KeyEvent>()
        {
            public void handle(KeyEvent e)
            {
                parseKeyboard(e);
            }
        };
    private EventHandler handler2 = new EventHandler<ActionEvent>()
        {
            public void handle(ActionEvent e2)
            {
                parseButton(e2);
            }
        };

    //Main Menu Section:
    private CheckBox hardMode = new CheckBox("Shortened Timer Mode");
    private boolean easyMode = true;
    private Rectangle rect = new Rectangle(400,350);
    private Text menuText = new Text("Welcom To Typing King \n        select a mode:");
    private RadioButton mouseEnable;
    private RadioButton keyboardEnable;
    private ToggleGroup inputGroup = new ToggleGroup();
    private Button startButton = new Button("Start Game");
    
    //End Screen Section:
    //player must quit and restart application
    //because im lazy
    private Button restart = new Button("Quit");

    //Visal Section:
    //pantone 18-1447, rust red
    final private Color primary = Color.web("0xC3634F");
    //pantone dark burgundy, red, dark burgundy
    final private Color secondary = Color.web("0x770f05");
    //pantone 321C, teal blue very ugly!!!
    final private Color highlight = secondary;
    private Background background;

    //Button Section:
    private Button a = new Button("A");
    private Button b = new Button("B");
    private Button c = new Button("C");
    private Button d = new Button("D");
    private Button e = new Button("E");
    private Button f = new Button("F");
    private Button g = new Button("G");
    private Button h = new Button("H");
    private Button i = new Button("I");
    private Button j = new Button("J");
    private Button k = new Button("K");
    private Button l = new Button("L");
    private Button m = new Button("M");
    private Button n = new Button("N");
    private Button o = new Button("O");
    private Button p = new Button("P");
    private Button q = new Button("Q");
    private Button r = new Button("R");
    private Button s = new Button("S");
    private Button t = new Button("T");
    private Button u = new Button("U");
    private Button v = new Button("V");
    private Button w = new Button("W");
    private Button x = new Button("X");
    private Button y = new Button("Y");
    private Button z = new Button("Z");
    /**
     * The start method is the main entry point for every JavaFX application. 
     * It is called after the init() method has returned and after 
     * the system is ready for the application to begin running.
     *
     * @param  stage the primary stage for this application.
     */
    public void start(Stage stage) 
    {
        //Instantiate the game texts:
        wordText = new Text("");
        theWord = new Text("#");
        theWord.setFont(new Font(35));
        wordText.setFont(new Font(20));
        theWordStr = "#";
        theScore = new Text("0");
        theScore.setFill(highlight);
        theScore.setFont(new Font(20));
        
        //Instatiate the utility:
        rd = new Random();
        
        //Instantiate the timer arc:
        timerArc = new Arc();
        timerArc.setRadiusX(20);
        timerArc.setRadiusY(20);
        timerArc.setFill(highlight);
        timerArc.setStartAngle(0);
        timerArc.setType(ArcType.ROUND);
        
        //Instantiate the animation timer:
        timer = new myTimer();
        
        //Instantiate the background:
        background = new Background(
            new BackgroundFill(primary, CornerRadii.EMPTY, Insets.EMPTY));
            
        //Instantiate the main menu buttons:
        mouseEnable = new RadioButton("Use On-Screen Keyboard (No Backspace)");
        keyboardEnable = new RadioButton("Use Physical Keyboard (Easier)");
        mouseEnable.setStyle("-fx-text-fill: #770f05");
        mouseEnable.setToggleGroup(inputGroup);
        keyboardEnable.setToggleGroup(inputGroup);
        
        //Style the button text with the SECONDARY colour:
        keyboardEnable.setStyle("-fx-text-fill: #770f05");
        hardMode.setStyle("-fx-text-fill: #770f05");
        startButton.setStyle("-fx-text-fill: #770f05");
        
        //Style the keyboard buttons (sorry):
        a.setStyle("-fx-text-fill: #770f05");
        b.setStyle("-fx-text-fill: #770f05");
        c.setStyle("-fx-text-fill: #770f05");
        d.setStyle("-fx-text-fill: #770f05");
        e.setStyle("-fx-text-fill: #770f05");
        f.setStyle("-fx-text-fill: #770f05");
        g.setStyle("-fx-text-fill: #770f05");
        h.setStyle("-fx-text-fill: #770f05");
        i.setStyle("-fx-text-fill: #770f05");
        j.setStyle("-fx-text-fill: #770f05");
        k.setStyle("-fx-text-fill: #770f05");
        l.setStyle("-fx-text-fill: #770f05");
        m.setStyle("-fx-text-fill: #770f05");
        n.setStyle("-fx-text-fill: #770f05");
        o.setStyle("-fx-text-fill: #770f05");
        p.setStyle("-fx-text-fill: #770f05");
        q.setStyle("-fx-text-fill: #770f05");
        r.setStyle("-fx-text-fill: #770f05");
        s.setStyle("-fx-text-fill: #770f05");
        t.setStyle("-fx-text-fill: #770f05");
        u.setStyle("-fx-text-fill: #770f05");
        v.setStyle("-fx-text-fill: #770f05");
        w.setStyle("-fx-text-fill: #770f05");
        x.setStyle("-fx-text-fill: #770f05");
        y.setStyle("-fx-text-fill: #770f05");
        z.setStyle("-fx-text-fill: #770f05");
        restart.setStyle("-fx-text-fill: #770f05");

        //Set up the main menu text:
        menuText.setFont(new Font(35));
        menuText.setFill(secondary);
        
        //Set up the wordlist from the file:
        try
        {
            setupArray("englishwordlist_randomized.txt");
        }
        catch(Exception E)
        {
            System.out.println("File not found, place the "
            + "englishwordlist_randomized.txt in the DIR");
        }

        // Create a new grid pane to house the keyboard buttons:
        GridPane pane1 = new GridPane();
        pane1.setPadding(new Insets(20, 20, 20, 20));
        pane1.setPrefSize(400, 350);
        pane1.setVgap(10);
        pane1.setHgap(10);
        
        //Set up the stackpane with the game first:
        root = new StackPane();
        root.getChildren().add(pane1);
        root.getChildren().add(wordText);
        root.getChildren().add(theWord);
        root.getChildren().add(timerArc);
        root.getChildren().add(theScore);
        
        //Set up the alignments of the game text:
        root.setAlignment(theWord, Pos.TOP_CENTER);
        root.setAlignment(wordText, Pos.CENTER);
        root.setAlignment(timerArc, Pos.TOP_LEFT);
        root.setAlignment(theScore, Pos.TOP_RIGHT);

        //the main menu:
        rect.setFill(primary);
        root.getChildren().add(rect);
        root.getChildren().add(menuText);
        root.getChildren().addAll(mouseEnable, keyboardEnable);
        root.getChildren().add(startButton);
        root.getChildren().add(hardMode);
        
        //set alignment for main menu:
        root.setAlignment(menuText, Pos.TOP_CENTER);
        root.setMargin(menuText, new Insets(50,0,0,0));
        root.setAlignment(mouseEnable, Pos.CENTER);
        root.setAlignment(keyboardEnable, Pos.CENTER);
        root.setMargin(mouseEnable, new Insets(100,0,0,0));
        root.setMargin(keyboardEnable, new Insets(150,0,0,0));
        root.setAlignment(startButton, Pos.BOTTOM_CENTER);
        root.setAlignment(hardMode, Pos.CENTER);
        root.setMargin(hardMode, new Insets(200,0,0,0));

        //Enable the buttons:
        mouseEnable.setOnAction(this::enableMouse);
        keyboardEnable.setOnAction(this::enableKeyboard);
        startButton.setOnAction(this::startGame);
        hardMode.setOnAction(this::enableHard);
        restart.setOnAction(this::restartGame);

        //The Keyboard:
        //first row
        pane1.add(q, 1, 20);
        pane1.add(w, 2, 20);
        pane1.add(e, 3, 20);
        pane1.add(r, 4, 20);
        pane1.add(t, 5, 20);
        pane1.add(y, 6, 20);
        pane1.add(u, 7, 20);
        pane1.add(i, 8, 20);
        pane1.add(o, 9, 20);
        pane1.add(p, 10, 20);
        //second row
        pane1.add(a, 1, 21);
        pane1.add(s, 2, 21);
        pane1.add(d, 3, 21);
        pane1.add(f, 4, 21);
        pane1.add(g, 5, 21);
        pane1.add(h, 6, 21);
        pane1.add(j, 7, 21);
        pane1.add(k, 8, 21);
        pane1.add(l, 9, 21);
        //third row
        pane1.add(z, 2, 22);
        pane1.add(x, 3, 22);
        pane1.add(c, 4, 22);
        pane1.add(v, 5, 22);
        pane1.add(b, 6, 22);
        pane1.add(n, 7, 22);
        pane1.add(m, 8, 22);

        //All the handlers for mouse and keyboard:
        root.addEventHandler(KeyEvent.KEY_PRESSED, handler);
        root.addEventHandler(ActionEvent.ACTION, handler2);
        
        //Create the scene and set the background:
        scene1 = new Scene(root, 400, 350);
        root.setBackground(background);
        stage.setTitle("Typing King");
        stage.setScene(scene1);
        
        // Show the Stage (window)
        stage.show();
    }
    
    private void restartGame(ActionEvent event)
    {
        System.exit(0);
    }

    //Parses a key event into the appropriate letter to be added:
    private void parseKeyboard(KeyEvent event)
    {
        if(key)
        {
            KeyCode keyC = event.getCode();
            switch(keyC)
            {
                case BACK_SPACE: 
                word = word.substring(0, word.length() - 1);
                break;
            }
            String keyString = event.getText();
            word += keyString;
        }
    }

    //Big if statement because this applies to
    //all buttons (like start button and radiobuttons)
    private void parseButton(ActionEvent e)
    {
        if(mouse && !(e.getTarget() instanceof RadioButton) 
        && !(e.getTarget() instanceof CheckBox) 
        && !(((Button)e.getTarget()).getText().toString().equals("Start Game")))
        {
            //System.out.println(e.getTarget());
            word += ((Button)e.getTarget()).getText().toString().toLowerCase();
        }
    }

    
    //enables hard mode (2/3 time)
    private void enableHard(ActionEvent event)
    {
        easyMode = false;
    }

    //enables the on screen keyboard and mouse
    private void enableMouse(ActionEvent event)
    {
        mouse = true;
        key = false;
    }

    //enables the physical keyboard 
    private void enableKeyboard(ActionEvent event)
    {
        mouse = false;
        key = true;
    }

    //starts the game timer
    private void startGame(ActionEvent event)
    {
        timer.start();
        
        //remove the main menu features
        root.getChildren().remove(rect);
        root.getChildren().remove(startButton);
        root.getChildren().remove(mouseEnable);
        root.getChildren().remove(keyboardEnable);
        root.getChildren().remove(menuText);
        root.getChildren().remove(hardMode);
    }

    //should be obvious:
    private void handleKeyA(ActionEvent event)
    {
        word += "a";
    }

    private void handleKeyB(ActionEvent event)
    {
        word += "b";
    }

    private void handleKeyC(ActionEvent event)
    {
        word += "c";
    }

    private void handleKeyD(ActionEvent event)
    {
        word += "d";
    }

    private void handleKeyE(ActionEvent event)
    {
        word += "e";
    }

    private void handleKeyF(ActionEvent event)
    {
        word += "f";
    }

    private void handleKeyG(ActionEvent event)
    {
        word += "g";
    }

    private void handleKeyH(ActionEvent event)
    {
        word += "h";
    }

    private void handleKeyI(ActionEvent event)
    {
        word += "i";
    }

    private void handleKeyJ(ActionEvent event)
    {
        word += "j";
    }

    private void handleKeyK(ActionEvent event)
    {
        word += "k";
    }

    private void handleKeyL(ActionEvent event)
    {
        word += "l";
    }

    private void handleKeyM(ActionEvent event)
    {
        word += "m";
    }

    private void handleKeyN(ActionEvent event)
    {
        word += "n";
    }

    private void handleKeyO(ActionEvent event)
    {
        word += "o";
    }

    private void handleKeyP(ActionEvent event)
    {
        word += "p";
    }

    private void handleKeyQ(ActionEvent event)
    {
        word += "q";
    }

    private void handleKeyR(ActionEvent event)
    {
        word += "r";
    }

    private void handleKeyS(ActionEvent event)
    {
        word += "s";
    }

    private void handleKeyT(ActionEvent event)
    {
        word += "t";
    }

    private void handleKeyU(ActionEvent event)
    {
        word += "u";
    }

    private void handleKeyV(ActionEvent event)
    {
        word += "v";
    }

    private void handleKeyW(ActionEvent event)
    {
        word += "w";
    }

    private void handleKeyX(ActionEvent event)
    {
        word += "x";
    }

    private void handleKeyY(ActionEvent event)
    {
        word += "y";
    }

    private void handleKeyZ(ActionEvent event)
    {
        word += "z";
    }

    public static void main(String[] args)
    {
        launch(args);
    }

    //Sets up the englishwordlist_randomized.txt file
    //into a big array to be picked from
    
    //reads file, puts it into array, done
    private String[] setupArray(String fileName) throws IOException
    {
        reader = new BufferedReader(new FileReader(fileName));
        String line;
        int linecount = 0;
        wordList = new ArrayList<String>();
        //converts arraylist to array
        while((line = reader.readLine()) != null)
        {
            wordList.add(line);
        }
        String[] arr = new String[wordList.size()];
        for(int i = 0; i < wordList.size(); i++)
        {
            arr[i] = wordList.get(i);
        }
        reader.close();
        return arr;
    }

    private class myTimer extends AnimationTimer
    {
        @Override
        public void handle(long now)
        {
            //for fun
            doStuff();
        }

        public void doStuff()
        {
            //Happens every frame
            //like draw in processing
            
            //set the colours of the text
            theWord.setFill(secondary);
            wordText.setFill(secondary);
            
            //changes time based on mode
            if(easyMode)
            {
                timerArc.setLength(timerInt/3);
                if(timerInt == 1080)
                {
                    timerInt = 0;
                    endGame();
                }
            }
            else
            {
                timerArc.setLength(timerInt/2);
                if(timerInt == 720)
                {
                    timerInt = 0;
                    endGame();
                }
            }
            
            //sets the word to be guessed with a random word
            wordText.setText(word);
            //# is a special character in this game
            //shouldnt be typed because its not a letter
            if(theWordStr.equals("#"))
            {
                //means word is guessed and new one needs
                //to be queued
                int rdIndex = rd.nextInt(wordList.size());
                theWordStr = wordList.get(rdIndex);
                theWord.setText(theWordStr);
            }
            //fixes a bug, idk
            if(theWordStr.equals("start game"))
            {
                theWordStr = "";
                wordText.setText("");
            }
            //checks for end of game
            if(word.equals(theWordStr))
            {
                wordDone = true;
            }
            //repeat by resetting timer and setting wordStr to #
            if(wordDone)
            {
                //set both to be # again
                //increment score
                score++;
                theScore.setText(Integer.toString(score));
                theWordStr = "#";
                word = "";
                timerInt = 0;
                wordDone = false;
            }
            //draw the arc based on the timer number.

            //based on 60 fps
            timerInt += 1;
        }

        //freezes input and displayes game over + score
        public void endGame()
        {
            theWord.setFont(new Font(35));
            theWord.setText("Game Over, Out of Time");
            root.getChildren().remove(timerArc);
            theScore.setText("Score: " + score);
            root.setAlignment(theScore, Pos.CENTER);
            root.setMargin(theScore, new Insets(0,0,100,0));
            root.getChildren().add(restart);
            root.setAlignment(restart, Pos.CENTER);
            root.setMargin(restart, new Insets(20,0,0,0));
            timer.stop();
        }
    }
}
