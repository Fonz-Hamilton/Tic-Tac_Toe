package tictactoe;

/**
 * Description: A tic-tac-toe game using JavaFx components
 * @author Fonz
 * Date: 7/29/2020
 */

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.GridPane;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.paint.Color;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class TicTacToe extends Application {
    private Label p1Label;
    private Label p2Label;
    private Label p1WinLabel;
    private Label p2WinLabel;
    
    private boolean turnX = true; // boolean for which turn; alternates
    private int p1Win = 0;        // total wins player 1
    private int p2Win = 0;        // total wins player 2
    
    private Canvas canvas;
    private GraphicsContext gc;
    private Color bgColor = Color.rgb(210, 240, 240);   // red, green, blue
    private Rectangle r1 = new Rectangle();
    private Rectangle r2 = new Rectangle();
    private Rectangle r3 = new Rectangle();
    private Rectangle r4 = new Rectangle();
    private Rectangle r5 = new Rectangle();
    private Rectangle r6 = new Rectangle();
    private Rectangle r7 = new Rectangle();
    private Rectangle r8 = new Rectangle();
    private Rectangle r9 = new Rectangle();
    
    // variable to keep track of which quadrant is X or O; 1 or 2 respectively
    private int q1 = 0, q2 = 0, q3 = 0, q4 = 0, q5 = 0, q6 = 0, q7 = 0, q8 = 0, q9 = 0;
    
    @Override
    public void start(Stage applicationStage) {
        Pane pane = new Pane();
        Scene scene = new Scene(pane);
        canvas = new Canvas(500, 400); // canvas for drawing (width, height)
        GridPane gridPane = new GridPane(); // for button and label layout
        
        // get the canvas graphics context to draw
        gc = canvas.getGraphicsContext2D();
        
        // background stuff
        gc.setFill(bgColor);         // sets the fill color of rectangle
        gc.fillRect(0, 0, 500, 400);  // x pos, y, width, height
        
        // line stuff for 3x3 grid
        Color line = Color.rgb(0, 0, 0);
        gc.setFill(line);
        gc.setLineWidth(8.0);
        gc.strokeLine(200.0, 100.0, 200.0, 300.0); // v1
        gc.strokeLine(300.0, 100.0, 300.0, 300.0); // v2
        gc.strokeLine(123.0, 160.0, 375.0, 160.0); // h1
        gc.strokeLine(123.0, 241.0, 375.0, 241.0); // h2

        // label and textfield shit
        p1Label = new Label("Player 1 wins:");
        p2Label = new Label("Player 2 wins:");
        p1WinLabel = new Label("0");
        p2WinLabel = new Label("0");
        
        // gridPane shenanigans
        gridPane.setHgap(5);
        gridPane.add(p1Label, 0, 0);
        gridPane.add(p1WinLabel, 1, 0);
        gridPane.add(p2Label, 62, 0);
        gridPane.add(p2WinLabel, 63, 0);
        
        // quadrant 1 hitbox
        r1.setFill(Color.rgb(123,5,23));
        r1.setOpacity(.0);
        r1.setX(120.0);
        r1.setY(96.5);
        r1.setWidth(75.0);
        r1.setHeight(59.0);
        r1.setVisible(false);
        
        // quadrant 2 hitbox
        r2.setFill(Color.rgb(123, 5, 23));
        r2.setOpacity(.0);
        r2.setX(205.0);
        r2.setY(96.5);
        r2.setWidth(90.0);
        r2.setHeight(59.0);
        r2.setVisible(false);
        
        // quadrant 3 hitbox
        r3.setFill(Color.rgb(123, 5, 23));
        r3.setOpacity(.0);
        r3.setX(305.0);
        r3.setY(96.5);
        r3.setWidth(79.0);
        r3.setHeight(59.0);
        r3.setVisible(false);
        
        // quadrant 4 hitbox
        r4.setFill(Color.rgb(123, 5, 23));
        r4.setOpacity(.0);
        r4.setX(120.0);
        r4.setY(164.5);
        r4.setWidth(75.0);
        r4.setHeight(72.0);
        r4.setVisible(false);
        
        // quadrant 5 hitbox
        r5.setFill(Color.rgb(123, 5, 23));
        r5.setOpacity(.0);
        r5.setX(205.0);
        r5.setY(164.5);
        r5.setWidth(90.0);
        r5.setHeight(72.0);
        r5.setVisible(false);
        
        // quadrant 6 hitbox
        r6.setFill(Color.rgb(123, 5, 23));
        r6.setOpacity(.0);
        r6.setX(305.0);
        r6.setY(164.5);
        r6.setWidth(79.0);
        r6.setHeight(72.0);
        r6.setVisible(false);
        
        // quadrant 7 hitbox
        r7.setFill(Color.rgb(123, 5, 23));
        r7.setOpacity(.0);
        r7.setX(120.0);
        r7.setY(245.5);
        r7.setWidth(75.0);
        r7.setHeight(58.5);
        r7.setVisible(false);
        
        // quadrant 8 hitbox
        r8.setFill(Color.rgb(123, 5, 23));
        r8.setOpacity(.0);
        r8.setX(205.0);
        r8.setY(245.5);
        r8.setWidth(90.0);
        r8.setHeight(58.5);
        r8.setVisible(false);
        
        // quadrant 9 hitbox
        r9.setFill(Color.rgb(123, 5, 23));
        r9.setOpacity(.0);
        r9.setX(305.0);
        r9.setY(245.5);
        r9.setWidth(79.0);
        r9.setHeight(58.5);
        r9.setVisible(false);
        
        // Start game button visual
        // Start game rectangle
        Rectangle startGameRect = new Rectangle();
        startGameRect.setFill(Color.rgb(247, 158, 62));
        startGameRect.setX(25.0);
        startGameRect.setY(350.0);
        startGameRect.setWidth(60.0);
        startGameRect.setHeight(25.0);
        startGameRect.setVisible(true);
        // start game line border
        Line startGameLine1 = new Line();           // top Line
        startGameLine1.setFill(Color.rgb(0, 0, 0));
        startGameLine1.setStartX(25.0);
        startGameLine1.setStartY(350.0);
        startGameLine1.setEndX(85.0);
        startGameLine1.setEndY(350.0);
        startGameLine1.setVisible(true);
        Line startGameLine2 = new Line();           // left Line
        startGameLine2.setFill(Color.rgb(0, 0, 0));
        startGameLine2.setStartX(25.0);
        startGameLine2.setStartY(350.0);
        startGameLine2.setEndX(25.0);
        startGameLine2.setEndY(375.0);
        startGameLine2.setVisible(true);
        Line startGameLine3 = new Line();           // bottom Line
        startGameLine3.setFill(Color.rgb(0, 0, 0));
        startGameLine3.setStartX(25.0);
        startGameLine3.setStartY(375.0);
        startGameLine3.setEndX(85.0);
        startGameLine3.setEndY(375.0);
        startGameLine3.setVisible(true);
        Line startGameLine4 = new Line();           // right Line
        startGameLine4.setFill(Color.rgb(0, 0, 0));
        startGameLine4.setStartX(85.0);
        startGameLine4.setStartY(350.0);
        startGameLine4.setEndX(85.0);
        startGameLine4.setEndY(375.0);
        startGameLine4.setVisible(true);
        // start game text
        Text startGameText = new Text(27, 366, "Game Start");   // text
        startGameText.setFont(Font.font(java.awt.Font.SERIF, 12));
        // start game hitbox
        Rectangle startGameHbox = new Rectangle();
        startGameHbox.setFill(Color.rgb(123, 5, 23));
        startGameHbox.setOpacity(.0);
        startGameHbox.setX(25.0);
        startGameHbox.setY(350.5);
        startGameHbox.setWidth(60.0);
        startGameHbox.setHeight(25.5);
        startGameHbox.setVisible(true);
        
        // Event handlers for hitbox
        // start hitbox event
        EventHandler<MouseEvent> start = new EventHandler<MouseEvent>() {
            @Override
            public void handle (MouseEvent e) {
                setHboxVisible();
                startGameRect.setVisible(false);
                startGameLine1.setVisible(false);
                startGameLine2.setVisible(false);
                startGameLine3.setVisible(false);
                startGameLine4.setVisible(false);
                startGameText.setVisible(false);
                startGameHbox.setVisible(false);
            }
        };
        // quadrant 1 rectangle hitbox event
        EventHandler<MouseEvent> rec1 = new EventHandler<MouseEvent>() {
            @Override
            public void handle (MouseEvent e) {
                r1.setVisible(false);
                if (turnX) {
                    gc.setLineWidth(7.0);
                    gc.strokeLine(130.0, 100.0, 185.0, 145.0);
                    gc.strokeLine(130.0, 145.0, 185.0, 100.0);
                    q1 = 1;
                    turnX = false;
                    checkWin();
                }
                else {
                    gc.setLineWidth(7.0);
                    gc.strokeOval(128.0, 91.0, 60.0, 60.0);
                    q1 = 2;
                    turnX = true;
                    checkWin();
                }
            }
        };
        // quadrant 2 rectangle hitbox event
        EventHandler<MouseEvent> rec2 = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                r2.setVisible(false);
                if (turnX) {
                    gc.setLineWidth(7.0);
                    gc.strokeLine(222.0, 100.0, 277.0, 145.0);
                    gc.strokeLine(222.0, 145.0, 277.0, 100.0);
                    q2 = 1;
                    turnX = false;
                    checkWin();
                }
                else {
                    gc.setLineWidth(7.0);
                    gc.strokeOval(220.0, 91.0, 60.0, 60.0);
                    q2 = 2;
                    turnX = true;
                    checkWin();
                }
            }
        };
        // quadrant 3 rectangle hitbox event
        EventHandler<MouseEvent> rec3 = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                r3.setVisible(false);
                if (turnX) {
                    gc.setLineWidth(7.0);
                    gc.strokeLine(315.0, 100.0, 370.0, 145.0);
                    gc.strokeLine(315.0, 145.0, 370.0, 100.0);
                    q3 = 1;
                    turnX = false;
                    checkWin();
                }
                else {
                    gc.setLineWidth(7.0);
                    gc.strokeOval(313.0, 91.0, 60.0, 60.0);
                    q3 = 2;
                    turnX = true;
                    checkWin();
                }
            }
        };
        // quadrant 4 rectangle hitbox event
        EventHandler<MouseEvent> rec4 = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                r4.setVisible(false);
                if (turnX) {
                    gc.setLineWidth(7.0);
                    gc.strokeLine(130.0, 181.0, 185.0, 226.0);
                    gc.strokeLine(130.0, 226.0, 185.0, 181.0);
                    q4 = 1;
                    turnX = false;
                    checkWin();
                }
                else {
                    gc.setLineWidth(7.0);
                    gc.strokeOval(128.0, 172.0, 60.0, 60.0);
                    q4 = 2;
                    turnX = true;
                    checkWin();
                }
            }
        };
        // quadrant 5 rectangle hitbox event
        EventHandler<MouseEvent> rec5 = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                r5.setVisible(false);
                if (turnX) {
                    gc.setLineWidth(7.0);
                    gc.strokeLine(222.0, 181.0, 277.0, 226.0);
                    gc.strokeLine(222.0, 226.0, 277.0, 181.0);
                    q5 = 1;
                    turnX = false;
                    checkWin();
                }
                else {
                    gc.setLineWidth(7.0);
                    gc.strokeOval(220.0, 172.0, 60.0, 60.0);
                    q5 = 2;
                    turnX = true;
                    checkWin();
                }
            }
        };
        // quadrant 6 rectangle hitbox event
        EventHandler<MouseEvent> rec6 = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                r6.setVisible(false);
                if (turnX) {
                    gc.setLineWidth(7.0);
                    gc.strokeLine(315.0, 181.0, 370.0, 226.0);
                    gc.strokeLine(315.0, 226.0, 370.0, 181.0);
                    q6 = 1;
                    turnX = false;
                    checkWin();
                }
                else {
                    gc.setLineWidth(7.0);
                    gc.strokeOval(313.0, 172.0, 60.0, 60.0);
                    q6 = 2;
                    turnX = true;
                    checkWin();
                }
            }
        };
        // quadrant 7 rectangle hitbox event
        EventHandler<MouseEvent> rec7 = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                r7.setVisible(false);
                if (turnX) {
                    gc.setLineWidth(7.0);
                    gc.strokeLine(130.0, 259.0, 185.0, 304.0);
                    gc.strokeLine(130.0, 304.0, 185.0, 259.0);
                    q7 = 1;
                    turnX = false;
                    checkWin();
                }
                else {
                    gc.setLineWidth(7.0);
                    gc.strokeOval(128.0, 250.0, 60.0, 60.0);
                    q7 = 2;
                    turnX = true;
                    checkWin();
                }
            }
        };
        // quadrant 8 rectangle hitbox event
        EventHandler<MouseEvent> rec8 = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                r8.setVisible(false);
                if (turnX) {
                    gc.setLineWidth(7.0);
                    gc.strokeLine(222.0, 259.0, 277.0, 304.0);
                    gc.strokeLine(222.0, 304.0, 277.0, 259.0);
                    q8 = 1;
                    turnX = false;
                    checkWin();
                }
                else {
                    gc.setLineWidth(7.0);
                    gc.strokeOval(220.0, 250.0, 60.0, 60.0);
                    q8 = 2;
                    turnX = true;
                    checkWin();
                }
            }
        };
        // quadrant 9 rectangle hitbox event
        EventHandler<MouseEvent> rec9 = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                r9.setVisible(false);
                if (turnX) {
                    gc.setLineWidth(7.0);
                    gc.strokeLine(315, 259.0, 370.0, 304.0);
                    gc.strokeLine(315.0, 305.0, 370.0, 259.0);
                    q9 = 1;
                    turnX = false;
                    checkWin();
                }
                else {
                    gc.setLineWidth(7.0);
                    gc.strokeOval(313.0, 250.0, 60.0, 60.0);
                    q9 = 2;
                    turnX = true;
                    checkWin();
                }
            }
        };
        
        // events
        startGameHbox.addEventFilter(MouseEvent.MOUSE_CLICKED, start);
        r1.addEventFilter(MouseEvent.MOUSE_CLICKED, rec1);
        r2.addEventFilter(MouseEvent.MOUSE_CLICKED, rec2);
        r3.addEventFilter(MouseEvent.MOUSE_CLICKED, rec3);
        r4.addEventFilter(MouseEvent.MOUSE_CLICKED, rec4);
        r5.addEventFilter(MouseEvent.MOUSE_CLICKED, rec5);
        r6.addEventFilter(MouseEvent.MOUSE_CLICKED, rec6);
        r7.addEventFilter(MouseEvent.MOUSE_CLICKED, rec7);
        r8.addEventFilter(MouseEvent.MOUSE_CLICKED, rec8);
        r9.addEventFilter(MouseEvent.MOUSE_CLICKED, rec9);

        // window properties
        pane.getChildren().add(canvas);
        pane.getChildren().add(startGameRect);
        pane.getChildren().add(startGameLine1);
        pane.getChildren().add(startGameLine2);
        pane.getChildren().add(startGameLine3);
        pane.getChildren().add(startGameLine4);
        pane.getChildren().add(startGameText);
        pane.getChildren().add(startGameHbox);
        pane.getChildren().add(r1);
        pane.getChildren().add(r2);
        pane.getChildren().add(r3);
        pane.getChildren().add(r4);
        pane.getChildren().add(r5);
        pane.getChildren().add(r6);
        pane.getChildren().add(r7);
        pane.getChildren().add(r8);
        pane.getChildren().add(r9);
        pane.getChildren().add(gridPane);
        applicationStage.show();
        applicationStage.setScene(scene);
        applicationStage.setTitle("Tic-tac-toe");
        applicationStage.setResizable(false);
        applicationStage.sizeToScene();
        applicationStage.setFullScreen(false);
        
    }
    // method to handle win events
    public void checkWin() {
        
       if ((q1 == 1 && q2 == 1 && q3 == 1) || (q4 == 1 && q5 == 1 && q6 == 1) 
               || (q7 == 1 && q8 == 1 && q9 == 1) || (q1 == 1 && q4 == 1 
               && q7 == 1) || (q2 == 1 && q5 == 1 && q8 == 1) || (q3 == 1 
               && q6 == 1 && q9 == 1) || (q1 == 1 && q5 == 1 && q9 == 1) 
               || (q3 == 1 && q5 == 1 && q7 == 1)) {
           Alert alert = new Alert(AlertType.INFORMATION);
           alert.setTitle("Winner!");
           alert.setHeaderText("Player 1 is the winner!!");
           alert.setContentText("Press 'OK' for next game!");
           alert.showAndWait();
           p1Win++;
           p1WinLabel.setText(Integer.toString(p1Win));
           grid();
           setHboxVisible();
           turnX = true;
           reset();
       }
       else if ((q1 == 2 && q2 == 2 && q3 == 2) || (q4 == 2 && q5 == 2 && q6 == 2) 
               || (q7 == 2 && q8 == 2 && q9 == 2) || (q1 == 2 && q4 == 2 
               && q7 == 2) || (q2 == 2 && q5 == 2 && q8 == 2) || (q3 == 2 
               && q6 == 2 && q9 == 2) || (q1 == 2 && q5 == 2 && q9 == 2) 
               || (q3 == 2 && q5 == 2 && q7 == 2)) {
           Alert alert = new Alert(AlertType.INFORMATION);
           alert.setTitle("Winner!");
           alert.setHeaderText("Player 2 is the winner!!");
           alert.setContentText("Press 'OK' for next game!");
           alert.showAndWait();
           p2Win++;
           p2WinLabel.setText(Integer.toString(p2Win));
           grid();
           setHboxVisible();
           turnX = true;
           reset();
       }
       else {
           if (q1 > 0 && q2 > 0 && q3 > 0 && q4 > 0 && q5 > 0 && q6 > 0 && q7 > 0 && q8 > 0 && q9 > 0) {
               Alert alert = new Alert(AlertType.INFORMATION);
               alert.setTitle("No Winner!");
               alert.setHeaderText("Cats game!! No winner!");
               alert.setContentText("Press 'OK' for the next game!");
               alert.showAndWait();
               grid();
               setHboxVisible();
               turnX = true;
               reset();
       
           }
       }
    }

    public void reset() {               // resets quadrant values
        q1 = 0;
        q2 = 0;
        q3 = 0;
        q4 = 0;
        q5 = 0;
        q6 = 0;
        q7 = 0;
        q8 = 0;
        q9 = 0;
    }
    public void setHboxVisible() {      // makes hit box visible again
        r1.setVisible(true);
        r2.setVisible(true);
        r3.setVisible(true);
        r4.setVisible(true);
        r5.setVisible(true);
        r6.setVisible(true);
        r7.setVisible(true);
        r8.setVisible(true);
        r9.setVisible(true);
    }
    public void grid() {                // draws the 3x3 grid
        gc.setFill(bgColor);
        gc.fillRect(0, 0, 500.0, 400.0);
        Color line = Color.rgb(0, 0, 0);
        gc.setFill(line);
        gc.setLineWidth(8.0);
        gc.strokeLine(200.0, 100.0, 200.0, 300.0); // v1
        gc.strokeLine(300.0, 100.0, 300.0, 300.0); // v2
        gc.strokeLine(123.0, 160.0, 375.0, 160.0); // h1
        gc.strokeLine(123.0, 241.0, 375.0, 241.0); // h2
    }

    public static void main(String[] args) {
        launch(args);

    } // end of main
}