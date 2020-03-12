package com.LP2.app;

import com.LP2.app.loader.Loader;
import com.LP2.database.Connect;
import com.LP2.server.utils.AllOrders;
import com.LP2.server.utils.Constants;
import com.LP2.server.utils.Menu;
import com.LP2.view.pages.Login;

// import javax.imageio.ImageIO;
// import javax.swing.JComponent;
// import javax.swing.JFrame;
// import java.awt.EventQueue;
// import java.io.File;
// import java.io.IOException;
// import java.awt.Graphics;
// import java.awt.Image;


public class App {

  public static void main(String[] args) {

    Loader.preLoad();

    Connect.connect();
    Menu.load(Constants.getPresent());
    AllOrders.load(Constants.getUnOrder());
    Session.open();

    // db.addColumnToTable("ALTER TABLE Person ADD COLUMN phone varchar(20) NOT NULL DEFAULT '(21)22222-2222'");

    Login.main(args);

    Session.close();
    Loader.unloadStreams();
  }
}

// class ImageTest {
//   public static void main(String[] args){
//     EventQueue.invokeLater(new Runnable() {
//           public void run(){
//               ImageFrame frame = new ImageFrame();
//               frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//               frame.setVisible(true);
//           }
//       }
//       );
//   }
// }

// class ImageFrame extends JFrame{
//   public ImageFrame(){
//       setTitle("ImageTest");
//       setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);

//       ImageComponent component = new ImageComponent();
//       add(component);
//       getContentPane().validate();
//       getContentPane().repaint();
//   }

//   public static final int DEFAULT_WIDTH = 300;
//   public static final int DEFAULT_HEIGHT = 200;
// }

// class ImageComponent extends JComponent{
//   private static final long serialVersionUID = 1L;
//   private Image image;
//   public ImageComponent(){
//       try{
//           File image2 = new File("b.jpg");
//           image = ImageIO.read(image2);
//       } catch (IOException e){
//           e.printStackTrace();
//       }
//   }
//   public void paintComponent (Graphics g){
//       if(image == null) return;
//       int imageWidth = image.getWidth(this);
//       int imageHeight = image.getHeight(this);

//       g.drawImage(image, 50, 50, this);

//       for (int i = 0; i*imageWidth <= getWidth(); i++)
//           for(int j = 0; j*imageHeight <= getHeight();j++)
//               if(i+j>0) g.copyArea(0, 0, imageWidth, imageHeight, i*imageWidth, j*imageHeight);
//   }
// }