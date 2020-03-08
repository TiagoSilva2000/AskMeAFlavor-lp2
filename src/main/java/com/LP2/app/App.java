package com.LP2.app;

import com.LP2.app.loader.Loader;
import com.LP2.database.Connect;
import com.LP2.database.items.ItemController;
import com.LP2.server.items.Food;
import com.LP2.server.resources.Image;
import com.LP2.server.users.Manager;
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

  public static void testMenu() {
    for (int i = 0; i < Menu.getMenu().size(); i++)
      System.out.println(Menu.getMenu().get(i).getItsString());
  }


  public static void foodCreation() {
    Image img = new Image("/assets/tmp/", "b", ".jpg");
    Session.storeFood("pickles", 44, "em conserva", false, img);

  }

  public static void drinkCreation() {
    Session.storeDrink("refri", 4, "coca-cola", true, null);
  }

  public static void drinkUpdate() {
    Image img = new Image("/assets/tmp/", "cat", ".jpg");
    Session.updateDrink(31, "água fria", 20,
                      true, "serasa", img);
  }

  public static void foodUpdate() {
    Session.updateFood(31, "água fria", 20,
                      true, "apimentado", null);
  }

  public static void itemDelete() {
    Session.deleteItem(33);
  }


  public static void testOrder() {
    Session.login("ttiago", "12345");
    Session.order(31, 2);
  }

  public static void updateOrder() {
    Session.closeOrder(8);
  }

  public static void testBill() {
    int id = Menu.getMenu().get(0).getID();
    Session.login("ttiago", "12345");
    // Session.order(id, 5);
    // Session.closeOrder(11);
    for (int i = 0; i < Session.getLoggedUser().getOrders().size(); i++)
      Session.closeOrder(Session.getLoggedUser().getOrders().get(i).getID());
    Session.processPayment();
  }


  public static void checkImage() {
    // String [] args = null;
    // ImageTest.main(args);
  }

  public static void main(String[] args) {
    Connect db = null;

    Loader.preLoad();
    db = new Connect();
    Loader.loadControllers(db);
    Menu.load(Constants.getPresent());
    AllOrders.load(Constants.getUnOrder());
    Session.open();

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