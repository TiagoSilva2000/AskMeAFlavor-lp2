package com.LP2.database.misc;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.LP2.app.ErrorCodes;
import com.LP2.database.Connect;
import com.LP2.server.resources.Image;

public class ImageController {
  static protected Connect connection;

  public ImageController(final Connect conn) {
    connection = conn;
  }

  static public void setConnection(final Connect conn) {
    connection = conn;
  }

  static public int create(final Image img) {
    File file = img.load();
    int id = -1;

    if (file == null) {
      System.out.println("Operation to create an image failed!");
      return -1;
    }
    try {
      ResultSet result;
      FileInputStream fis = new FileInputStream(file);
      try {
        final PreparedStatement stm = connection.getCon()
            .prepareStatement("INSERT INTO Image" + "VALUES (?, ?, ?, ?)");
        stm.setBinaryStream(1, fis, (int) file.length());
        stm.setString(2, img.getFilePath());
        stm.setString(3, img.getFileName());
        stm.setString(4, img.getFileType());
        result = stm.getGeneratedKeys();
        result.next();
        id = result.getInt(1);

        stm.close();
        return id;
      } catch (SQLException e) {
        e.printStackTrace();
      }
    } catch (FileNotFoundException e) {
      e.printStackTrace();
      System.exit(ErrorCodes.CREATIONERROR());
    }

    return 1;
  }

  static public ArrayList<String> get(final int id) {
    try {
      final ArrayList<String> fields = new ArrayList<String>();
      int i, maxFields;
      ResultSet result = null;
      PreparedStatement stm = connection.getCon().prepareStatement(
        "SELECT * FROM Image " + "WHERE id = (?)"
      );
      stm.setInt(1, id);
      result = stm.executeQuery();
      i = 1;
      maxFields = result.getMetaData().getColumnCount();
      while (i <= maxFields)
        fields.add(result.getString(i++));

      stm.close();

      return fields;
    } catch(SQLException e) {
      e.printStackTrace();
      System.out.println("Error Code: ");
      System.out.println(ErrorCodes.READERROR());
      return null;
    }
  }

  static public ArrayList<String> get(String filePath, String fileName, String fileType) {
    filePath = filePath.toLowerCase();
    fileName = fileName.toLowerCase();
    fileType = fileType.toLowerCase();

    try {
      final ArrayList<String> fields = new ArrayList<String>();
      int i, maxFields;
      ResultSet result = null;
      PreparedStatement stm = connection.getCon().prepareStatement(
        "SELECT * FROM Image " + "WHERE filePath = (?), fileName = (?), fileType = (?)"
      );
      stm.setString(1, filePath);
      stm.setString(2, fileName);
      stm.setString(3, fileType);
      result = stm.executeQuery();
      maxFields = result.getMetaData().getColumnCount();

      i = 1;
      while (i <= maxFields)
        fields.add(result.getString(i++));


      stm.close();
      return fields;
    } catch(SQLException e) {
      e.printStackTrace();
      System.out.println("Error Code: ");
      System.out.println(ErrorCodes.READERROR());
      return null;
    }
  }

  static public ArrayList<ArrayList<String>> get() {
    ArrayList<ArrayList<String>> images = new ArrayList<ArrayList<String>>();

    try {
      ResultSet result = null;
      int i, j, maxFields;
      final PreparedStatement stm = connection.getCon().prepareStatement(
        "SELECT * FROM Image"
      );
      result = stm.executeQuery();

      i = 0;
      maxFields = result.getMetaData().getColumnCount();

      while (result.next()) {
        images.add(new ArrayList<String>());
        j = 1;
        while (j <= maxFields)
          images.get(i).add(result.getString(j++));
        i += 1;
      }

      stm.close();
      return images;
    } catch(SQLException e) {
      e.printStackTrace();
      System.out.println("Error Code: ");
      System.out.println(ErrorCodes.READERROR());

      return null;
    }
  }

  static public boolean update(final Image img) {
    File file = img.load();

    if (file == null) {
      System.out.println("Operation to create an image failed!");
      return false;
    }

    try {
      FileInputStream fis = new FileInputStream(file);
      try {
        final PreparedStatement stm = connection.getCon().prepareStatement(
          "UPDATE Image " +
          "SET filePath = (?), fileName = (?), fileType = (?), content = (?)" +
          "WHERE id = (?)"
          );
          stm.setString(1, img.getFilePath());
          stm.setString(2, img.getFileName());
          stm.setString(3, img.getFileType());
          stm.setBinaryStream(4, fis, (int)file.length());
          stm.setInt(5, img.getID());
          stm.executeUpdate();

          return true;
      } catch(final Exception e) {
        e.printStackTrace();
        System.out.println("Error code: ");
        System.out.println(ErrorCodes.UPDATEERROR());
        return false;
      }
    } catch(FileNotFoundException e) {
      e.printStackTrace();
      System.exit(ErrorCodes.UPDATEERROR());

      return false;
    }
  }

  static public boolean remove(final int id) {
    try {
      final PreparedStatement stm = connection.getCon().prepareStatement(
        "DELETE FROM Image " + "WHERE id = (?)"
      );
      stm.setInt(1, id);
      stm.executeUpdate();

      stm.close();
      return true;
    } catch(final SQLException e) {
      e.printStackTrace();
      System.out.println("Error Code: ");
      System.out.println(ErrorCodes.DELETERROR());
      return false;
    }
  }

  static public boolean remove(final Image img) {
    try {
      final PreparedStatement stm = connection.getCon().prepareStatement(
        "DELETE FROM Image " + "WHERE filePath = (?), fileName = (?), fileType = (?)"
      );
      stm.setString(1, img.getFilePath());
      stm.setString(2, img.getFileName());
      stm.setString(3, img.getFileType());
      stm.executeUpdate();

      stm.close();
      return true;
    } catch(final SQLException e) {
      e.printStackTrace();
      System.out.println("Error Code: ");
      System.out.println(ErrorCodes.DELETERROR());
      return false;
    }
  }

}
