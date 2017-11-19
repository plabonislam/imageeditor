/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package comb;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.effect.Glow;
import javafx.scene.effect.Lighting;
import javafx.scene.effect.Reflection;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelReader;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.transform.Rotate;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.imageio.ImageIO;


public class Comb extends Application implements Runnable{
    String ss, ggg;
     Image image;
    InputStream inputStream;
    ImageView imv,im;
    TextField tf,tt;
    int width;

    int height;
    Stage pst;
    PixelReader pxr;
    PixelWriter pxw;
    WritableImage wimg;
    GridPane root;
    Button btn,btnSave,btr,bb;
    ComboBox <String> cb;
    ComboBox <String> cb2;
     GaussianBlur gaussianBlur;
    int c=0;
    double d;
    public void run()
    {
        
    }
    public void start(Stage primaryStage) throws Exception  {
        pst = primaryStage;
        FileChooser fileChooser = new FileChooser();
        configuringFileChooser(fileChooser);
        imv = new ImageView();
        
         GridPane.setConstraints(imv, 8, 6);
        root = new GridPane();
         
        btn = new Button();
        btn.setText("open pic");
       GridPane.setConstraints(btn, 0, 0);
     btr=new Button("rotate");
      GridPane.setConstraints(btr, 1, 0);
     btr.setOnAction(e->
     {
       int g = 10;
            Rotate rotate = new Rotate();
            rotate.setAngle(g);
            imv.getTransforms().addAll(rotate);
            if (g > 100) {
                g = 100;   
     }
     });
        btn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
              
                try {
                    File ff = fileChooser.showOpenDialog(primaryStage);
                    ss = ff.getAbsolutePath();
                    inputStream = new FileInputStream(ss);
                    image = new Image(inputStream, 400, 300, false, false);
                    imv.setImage(image);
                    im=imv;
                    
                    wimg = new WritableImage(width, height);
                    pxr = image.getPixelReader();
                    pxw = wimg.getPixelWriter();
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(Comb.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        });
 width = 400;
        height = 300;
        tf = new TextField();
         GridPane.setConstraints(tf, 4, 0);
        tf.setPromptText("name");
        cb=new ComboBox <>();
         cb.setPromptText("COLOR");
         GridPane.setConstraints(cb, 3, 0);
         
          cb2=new ComboBox <>();
           cb2.setPromptText("Effect");
           GridPane.setConstraints(cb2, 2, 0);
        cb.getItems().addAll(
                
                    "bright",
                "dark",
                "desaturate",
                "saturate",
                "invert",
                "Gray"
                
                );
        cb2.getItems().addAll(
                "lighting",
                "reflect",
                "blur",
                "shadow",
                "glow"
        );
        btnSave =new Button("Save pic");
         GridPane.setConstraints(btnSave, 5, 0);
      btnSave.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                ggg = tf.getText();
                File outputFile = new File("E:/myapp/" + ggg + ".png");
                BufferedImage bImage = SwingFXUtils.fromFXImage(imv.snapshot(null, null), null);
                try {
                    ImageIO.write(bImage, "png", outputFile);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

            }
        });
       
       
        cb.setOnAction(e->
        {
           String st= cb.getValue();
           if(st=="Gray")
           {
              
            for (int y = 0; y < height; y++) {
                for (int x = 0; x < width; x++) {
                    Color clr = pxr.getColor(x, y);
                   pxw.setColor(x, y,clr.grayscale());
                }
            }

            imv.setImage(wimg); 
           }
           else if(st== "Bright")
           {
             for (int y = 0; y < height; y++) {
                for (int x = 0; x < width; x++) {
                    Color clr = pxr.getColor(x, y);
                    pxw.setColor(x, y, clr.brighter());
                }
            }

            imv.setImage(wimg);   
           }
           else if(st== "dark")
           {
             for (int y = 0; y < height; y++) {
                for (int x = 0; x < width; x++) {
                    Color clr = pxr.getColor(x, y);
                    pxw.setColor(x, y, clr.darker());
                }
            }

            imv.setImage(wimg);   
           }
           else if(st== "desaturate")
           {
             for (int y = 0; y < height; y++) {
                for (int x = 0; x < width; x++) {
                    Color clr = pxr.getColor(x, y);
                    pxw.setColor(x, y, clr.desaturate());
                }
            }

            imv.setImage(wimg);   
           }
           else if(st== "saturate")
           {
             for (int y = 0; y < height; y++) {
                for (int x = 0; x < width; x++) {
                    Color clr = pxr.getColor(x, y);
                    pxw.setColor(x, y, clr.saturate());
                }
            }

            imv.setImage(wimg);   
           }
           else if(st== "invert")
           {
             for (int y = 0; y < height; y++) {
                for (int x = 0; x < width; x++) {
                    Color clr = pxr.getColor(x, y);
                    pxw.setColor(x, y, clr.invert());
                }
            }

            imv.setImage(wimg);   
           }
           
        });
       
       cb2.setOnAction(e->{
        String st=cb2.getValue();
        if(st=="lighting")
        {
            
             Lighting lighting = new Lighting(); 
      imv.setEffect(lighting);
            
        }
        else if(st=="blur")
        {
            gaussianBlur = new GaussianBlur();
           
            
          
          
       
           gaussianBlur.setRadius(8.5);
    
     
     imv.setEffect(gaussianBlur);
    
            
      
        }
        else if(st=="reflect")
        {
            Reflection reflection = new Reflection();
            reflection.setBottomOpacity(0.5);
            reflection.setTopOpacity(0.5);
            reflection.setTopOffset(0.3);
            reflection.setFraction(0.7);
            imv.setEffect(reflection);
        }
        else if(st=="glow")
        {
            Glow glow = new Glow(); 
                  glow.setLevel(.9); 
                     imv.setEffect(glow); 
        }
        else if(st=="shadow")
        {
            DropShadow shadow = new DropShadow(); 
            shadow.setColor(Color.BLUE); 
             shadow.setHeight(5); 
             shadow.setWidth(5); 
      shadow.setRadius(5);  
      imv.setEffect(shadow);
        }
        
    });
       root.setVgap(10);
        root.setHgap(10); 
      
 
        root.setPadding(new Insets(30, 10, 20, 40));
        
        if(c==0)
        {
         root.getChildren().addAll(btn,cb,cb2,btnSave,tf,btr,imv);
        }
        
         
        
        
         
        Scene scene = new Scene(root, 1200, 850);
        scene.getStylesheets().addAll("nn.css");
        
        primaryStage.setTitle("Hello World!");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

   
      private void configuringFileChooser(FileChooser fileChooser) {
        
        fileChooser.setTitle("Select Pictures");
        fileChooser.setInitialDirectory(new File("E:/myapp"));
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("All Files", "*.*"), 
                new FileChooser.ExtensionFilter("JPG", "*.jpg"), 
                new FileChooser.ExtensionFilter("PNG", "*.png"));
    }
      void go()
      {
          bb.setOnAction(e->
        {
            String sd= tt.getText();
            
             d=Double.parseDouble(sd);
            
        });
      }
      
    public static void main(String[] args) {
        launch(args);
    }
    
}
