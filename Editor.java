package editor;

import javafx.application.Application;
import javafx.stage.Stage;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.VPos;
import javafx.geometry.Orientation;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.control.ScrollBar;
import javafx.stage.Stage;

import javafx.scene.input.MouseEvent;
import javafx.util.Duration;

import java.awt.Toolkit;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Orientation;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.Scene;
import javafx.scene.control.ScrollBar;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import javax.swing.KeyStroke;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import javafx.stage.FileChooser;

import java.util.LinkedList;
import java.util.ArrayList;

public class Editor extends Application {
    public File file;
    private int windowWidth;
    private int windowHeight;

    private double sbWidth;

    ArrayList<LinkedList<String>> fullDoc = new ArrayList<LinkedList<String>>();

    public Text currentLine = new Text(250, 250, "");

    private TextBuffer buffer;
    private final int MARGIN = 10;

    public void handleDownArrow() {

    }

    private int cursorX;
    private int cursorY;

    private class TextBuffer {
        private String enteredText;
        private int currentPos; //Cursor should be to the right of selected letter

        public void addChar(char x) {
            //adds the given character to the string in currentPos

        }

        public void deleteChar() {
            //deletes the character at the currentPos
        }

        /*public char get(int i) {
            //returns the i-th character of the enteredText
            
        }*/

        public int currentPos() {
            //returns the currentPos;
            return currentPos;
        }
    }


    /*public void save() {
        if (file != null) {
            try (BufferedWriter buffer = new BufferedWriter(new FileWriter(file))) {
                for (String s : textArea.getText().split("\n")) {
                    buffer.write(s);
                    buffer.newLine();
                }
                System.out.println("File saved");
            } catch (IOException e) {
                System.out.println("Unable to save");
            }
        } else {
            FileChooser newFile = new FileChooser();
            newFile.setTitle("Save file as...");
            this.file = newFile.showSaveDialog(null);
            newFile = null;

            if (file != null) {
                try (BufferedWriter buffer = new BufferedWriter(new FileWriter(file))) {
                    for (String s : textArea.getText().split("\n")) {
                        buffer.write(s);
                        buffer.newLine();
                    }
                    System.out.println("File saved");
                } catch (IOException e) {
                    System.out.println("Unable to save");
                }
            }
        }
            
    }*/

    public void undo() {

    }

    public void redo() {

    }

    private class KeyEventHandler implements EventHandler<KeyEvent> {
    	LinkedList<String> typed = new LinkedList<String>();
        LinkedList<String> reference = new LinkedList<String>();
        //ArrayList<LinkedList<String>> fullDoc = new ArrayList<LinkedList<String>>();

        int textCenterX;
        int textCenterY;

        String tempString;

        public double lineWidth;

        public Text displayText = new Text(250, 250, "");
        //public Text tempText = new Text(250, 250, "");
        public int fontSize = 20;

        private String fontName = "Verdana";

        //int windowWidth;
        //int windowHeight;
        public Text currentLine = new Text(250, 250, "");

        String currentLineString;


        public KeyEventHandler(final Group root, int windowWidth, int windowHeight) {
            windowWidth = windowWidth;
            windowHeight = windowHeight;
            textCenterX = windowWidth / 2;
            textCenterY = windowHeight / 2;

            displayText = new Text(textCenterX, textCenterY, "");
            currentLine = new Text(textCenterX, textCenterY, "");
          
            displayText.setTextOrigin(VPos.TOP);
            displayText.setFont(Font.font (fontName, fontSize));
            currentLine.setFont(Font.font (fontName, fontSize));

            root.getChildren().add(displayText);

            fullDoc.add(typed);
        }

        //Backspace: if backspace button is pressed, last element of linked list is removed, displayText reassigned to new list


        @Override
        public void handle(KeyEvent keyEvent) {
            //If enter or shift is pressed, go to new line (cursor goes to the next line)
            //Backspace --> cursor goes back
            //Arrow keys move cursor accordingly


            /*
            //Shortcut + s saves file (call save())
            KeyStroke save = KeyStroke.getKeyStroke(KeyEvent.VK_S, Toolkit.getDefaultToolkit().getMenuShortcutKeyMask());
            */



            /*Shortcut + z undoes most recent up to 100 (call undo())
            KeyStroke undo = KeyStroke.getKeyStroke(KeyEvent.VK_Z, Toolkit.getDefaultToolkit().getMenuShortcutKeyMask());*/

            /*Shortcut + y redoes most recent (call redo())
            KeyStroke redo = KeyStroke.getKeyStroke(KeyEvent.VK_Y, Toolkit.getDefaultToolkit().getMenuShortcutKeyMask());*/


        	String fullText = "";

            String currentLineString = "";
            if (keyEvent.getEventType() == KeyEvent.KEY_TYPED) {
                //double textHeight = displayText.getLayoutBounds().getHeight();
                double textWidth = displayText.getLayoutBounds().getWidth();
                String characterTyped = keyEvent.getCharacter();
                Text characterTypedText = new Text(characterTyped);

                double charWidth = characterTypedText.getLayoutBounds().getWidth();
                double charHeight = characterTypedText.getLayoutBounds().getHeight();


                double lineWidth = currentLine.getLayoutBounds().getWidth();

                if (characterTyped.length() > 0 && characterTyped.charAt(0) != 8) {
                    if ((lineWidth + charWidth) < (windowWidth - sbWidth)) {
                        typed.add(characterTyped); //If width doesn't exceed window width, add into array
                        
                        for (String s : typed) {
                            currentLineString += s;
                        }

                        currentLine.setText(currentLineString);
                        //System.out.println(textWidth);
                        //System.out.println(lineWidth);
                        
                    } else {                         
                        typed = new LinkedList<String>();
                        fullDoc.add(typed);

                        typed.add(characterTyped);
                        currentLineString = "";

                        for (String s : typed) {
                            currentLineString += s;
                        }

                        currentLine.setText(currentLineString);
                    
                        //make fullText go through each linkedlist in the array and at the of each arraylist entry add \n
                    }
                   
                    for (LinkedList<String> list : fullDoc) {
                        for (int i = 0; i < list.size(); i++) {
                            fullText += list.get(i);
                        }
                        fullText += "\n";
                    }
                    
                    displayText.setText(fullText);
                    keyEvent.consume();
            }
                leftText();
            } else if (keyEvent.getEventType() == KeyEvent.KEY_PRESSED) {
                KeyCode code = keyEvent.getCode();

                int sizeDoc = fullDoc.size();
                int lastNum = sizeDoc - 1;
                LinkedList<String> lastList = fullDoc.get(lastNum);

                if (code == KeyCode.UP) {
                    fontSize += 4;
                    displayText.setFont(Font.font(fontName, fontSize));
                    //centerText();
                    leftText();
                } else if (code == KeyCode.DOWN) {
                    fontSize = Math.max(0, fontSize - 4);
                    displayText.setFont(Font.font(fontName, fontSize));
                    //centerText();
                    leftText();
                } else if (code == KeyCode.BACK_SPACE) {
                
                    if (lastList.isEmpty()) {
                        lastNum -= 1;
                        lastList = fullDoc.get(lastNum);
                        lastList.removeLast();
                    } else {
                        lastList.removeLast();
                    }

                    for (LinkedList<String> list : fullDoc) {
                        for (int i = 0; i < list.size(); i++) {
                            fullText += list.get(i);
                        }
                        fullText += "\n";
                    }
                    
                    displayText.setText(fullText);
                    keyEvent.consume();
                }
            }
        }

        private void centerText() {
      
            double textHeight = displayText.getLayoutBounds().getHeight();
            double textWidth = displayText.getLayoutBounds().getWidth();

          
            double textTop = textCenterY - textHeight / 2;
            double textLeft = textCenterX - textWidth / 2;

            // Re-position the text.
            displayText.setX(textLeft);
            displayText.setY(textTop);

            // Make sure the text appears in front of any other objects you might add.
            displayText.toFront();
        }

        private void nextLine(double charHeight) {
            double textHeight = displayText.getLayoutBounds().getHeight();
            double textWidth = displayText.getLayoutBounds().getWidth();

            double textLeft = 0.0;
            double textTop = textHeight + charHeight;

            displayText.setX(textLeft);
            displayText.setY(textTop);

            displayText.toFront();
        }


        private void leftText() {
        	double textHeight = displayText.getLayoutBounds().getHeight();
        	double textWidth = displayText.getLayoutBounds().getWidth();

        	double textTop = 0.0;
        	double textLeft = 0.0;

        	displayText.setX(textLeft);
        	displayText.setY(textTop);

        	displayText.toFront();
        }
    }

    private class MouseClickEventHandler implements EventHandler<MouseEvent> {
        Text positionText;

        MouseClickEventHandler(Group root) {
            positionText = new Text("");

            positionText.setTextOrigin(VPos.BOTTOM);

            root.getChildren().add(positionText);
        }

        @Override
        public void handle(MouseEvent mouseEvent) {
            double mousePressedX = mouseEvent.getX();
            double mousePressedY = mouseEvent.getY();

            positionText.setText("(" + mousePressedX + ", " + mousePressedY + ")");
            positionText.setX(mousePressedX);
            positionText.setY(mousePressedY);
        }
    }

    private int getDimensionInsideMargin(int outsideDimension) {
        return outsideDimension - 2 * MARGIN;
    }

    /*public void rewrap(int newWidth) {

        String fullText = "";
        String lineString = "";
        
        double lineWidth = currentLine.getLayoutBounds().getWidth();

        LinkedList<String> newTyped = new LinkedList<String>();
        int difference = windowWidth - newWidth;

        ArrayList<LinkedList<String>> temp = fullDoc;
        fullDoc = new ArrayList<LinkedList<String>>();
        fullDoc.add(newTyped);

            for (LinkedList<String> list : temp) {
                for (int i = 0; i < list.size(); i++) {
                    Text characterTypedText = new Text(list.get(i));
                    double charWidth = characterTypedText.getLayoutBounds().getWidth();

                    if ((lineWidth + charWidth) < (newWidth - sbWidth)) {
                        newTyped.add(list.get(i));

                    for (String s : newTyped) {
                        lineString += s;
                    }
                    currentLine.setText(lineString);
                } else {
                    newTyped = new LinkedList<String>();
                    fullDoc.add(newTyped);
                    newTyped.add(list.get(i));

                    lineString = "";

                    for (String s : newTyped) {
                        lineString += s;
                    }

                    currentLine.setText(lineString);
                }
                }
                
            }

        for (LinkedList<String> list : fullDoc) {
            for (int i = 0; i < list.size(); i++) {
                fullText += list.get(i);
            }
                fullText += "\n";
            }
                    
        displayText.setText(fullText);

    }*/

    @Override
    public void start(Stage primaryStage) {
        // Create a Node that will be the parent of all things displayed on the screen.
        Group root = new Group();
        Group textRoot = new Group(); 

        // The Scene represents the window: its height and width will be the height and width
        // of the window displayed.
        windowWidth = 500;
        windowHeight = 500;
        Scene scene = new Scene(root, windowWidth, windowHeight, Color.WHITE);
        
        ScrollBar sb = new ScrollBar();
        sb.setOrientation(Orientation.VERTICAL);
        sb.setPrefHeight(windowHeight);
        sbWidth = sb.getLayoutBounds().getWidth();
        sb.setLayoutX(windowWidth - sbWidth);

        sb.setMin(0);
        sb.setMax(windowHeight);

        root.getChildren().add(sb);
        root.getChildren().add(textRoot);

        //What happens when you change the width of the file
        scene.widthProperty().addListener(new ChangeListener<Number>() {
            @Override public void changed (
                ObservableValue<? extends Number> observableValue,
                Number oldScreenWidth,
                Number newScreenWidth) {

            int newWidth = getDimensionInsideMargin(newScreenWidth.intValue());
            sb.setLayoutX(newWidth);


            
            }
        });

        //What happens when you change the height of the file
        scene.heightProperty().addListener(new ChangeListener<Number> () {
            @Override public void changed (
                ObservableValue<? extends Number> observableValue,
                Number oldScreenHeight,
                Number newScreenHeight) {
                int newHeight = getDimensionInsideMargin(newScreenHeight.intValue());
                sb.setPrefHeight(newHeight);
                textRoot.setLayoutY(-newHeight);
            }     
        });


        sb.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed (
                ObservableValue<? extends Number> observableValue,
                Number oldValue, Number newValue) {
                textRoot.setLayoutY(-(newValue.doubleValue()));

            }
                
        });

        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                if (keyEvent.isShortcutDown()) {
                    if (keyEvent.getCode() == KeyCode.S) {
                        //Save File
                    } else if (keyEvent.getCode() == KeyCode.Z) {
                        //Undo up to 100
                    } else if (keyEvent.getCode() == KeyCode.Y) {
                        //Redo
                    }
                }
            }
        });

        scene.setOnMouseClicked(new MouseClickEventHandler(root));
        // To get information about what keys the user is pressing, create an EventHandler.
        // EventHandler subclasses must override the "handle" function, which will be called
        // by javafx.
        EventHandler<KeyEvent> keyEventHandler =
                new KeyEventHandler(textRoot, windowWidth, windowHeight);
        // Register the event handler to be called for all KEY_PRESSED and KEY_TYPED events.
        scene.setOnKeyTyped(keyEventHandler);
        scene.setOnKeyPressed(keyEventHandler);

        primaryStage.setTitle("Full Text Display");
        
        // This is boilerplate, necessary to setup the window where things are displayed.
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public static void main(String[] args) {
       //"The following below is for when you open with new files, test with blank editor first"

        /*String outputFilename = args[1];

        if (outputFilename == "debug") {
            //Print any output you like to facilitate debugging
        } else if (outputFilename == "") {
            //Don't print anything
        } 

        try {
            File inputFile = new File(inputFilename);

            if (!inputFile.exists()) {
                System.out.println("Unable to open because file with name " + inputFilename + 
                    " does not exist");
                return;
            }
            FileReader reader = new FileReader(inputFile);
            BufferedReader bufferedReader = new BufferedReader(reader);

            FileWriter writer = new FileWriter(outputFilename); 

            int intReader = -1;

            while ((intReader = bufferedReader.read()) != -1) {
                char charRead = (char) intReader;
                writer.write(charRead);
            }

            System.out.println("Successfully copied file " + inputFilename + " to "
                + outputFilename);

            bufferedReader.close();
            writer.close();
        } catch (FileNotFoundException fileNotFoundException) {
            System.out.println("File not found! Exception was: " + fileNotFoundException);
        } catch (IOException ioException) {
            System.out.println("Error when copy; exception was: " + ioException);
        } */

        if (args.length < 1) {
            System.out.println("No file input.");
            System.exit(1);
        }
        String input = args[0];
        try {
            File inputFile = new File(input);

            if (!inputFile.exists()) {
                FileWriter f = new FileWriter(new File(input));
                launch(args);
            }

            FileReader reader = new FileReader(inputFile);
            String readerString = reader.toString();
            launch(readerString);
        
        } catch (FileNotFoundException fileNotFoundException) {
            System.out.println("File not found! Exception was: " + fileNotFoundException);
        } catch (IOException ioException) {
            System.out.println("Error when copy; exception was: " + ioException);
        } 

        //launch(args);
    }


}