package com.LP2.database.misc;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import java.util.ArrayList;

import com.LP2.app.ErrorCodes;
import com.LP2.database.Connect;
import com.LP2.models.resources.Image;

import org.apache.commons.io.IOUtils;

import javassist.bytecode.ByteArray;

public class ImageDAO {

  static public int create(final Image img) {
    int id = -1;
    File file = img.load();
    if (file == null) {
      System.out.println("Operation to create an image failed!");
      return -1;
    }
    try {
      ResultSet result;
      FileInputStream fis = new FileInputStream(file);
      try {
        final PreparedStatement stm = Connect.getCon()
            .prepareStatement("INSERT INTO Image " +
                              "(filepath, filename, filetype, content) " +
                              "VALUES (?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
        stm.setString(1, img.getFilePath());
        stm.setString(2, img.getFileName());
        stm.setString(3, img.getFileType());
        stm.setBinaryStream(4, fis, (int) file.length());
        stm.executeUpdate();
        result = stm.getGeneratedKeys();
        while(result.next())
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

  static public Image read(final int id) {
    ArrayList<String> fields = new ArrayList<String>();

    try {
      ResultSet result = null;
      int i, maxFields;
      byte[] content = null;
      final PreparedStatement stm = Connect.getCon().prepareStatement(
        "SELECT * FROM Image WHERE id = (?)"
      );
      stm.setInt(1, id);
      result = stm.executeQuery();

      maxFields = result.getMetaData().getColumnCount();
      i = 1;
      while (result.next()) {
        while (i <= (maxFields - 1))
          fields.add(result.getString(i++));
        content = result.getBytes(i);
      }

      stm.close();
      return buildImage(fields, content);
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
        final PreparedStatement stm = Connect.getCon().prepareStatement(
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
      final PreparedStatement stm = Connect.getCon().prepareStatement(
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

  static private Image buildImage(final ArrayList<String> fields, final byte[] content) {
    final int id = Integer.parseInt(fields.get(0));
    final String filePath = fields.get(1);
    final String fileName = fields.get(2);
    final String fileType = fields.get(3);

    return new Image(id, filePath, fileName, fileType, content);
  }

}

  // static public boolean remove(final Image img) {
  //   try {
  //     final PreparedStatement stm = Connect.getCon().prepareStatement(
  //       "DELETE FROM Image " + "WHERE filePath = (?), fileName = (?), fileType = (?)"
  //     );
  //     stm.setString(1, img.getFilePath());
  //     stm.setString(2, img.getFileName());
  //     stm.setString(3, img.getFileType());
  //     stm.executeUpdate();

  //     stm.close();
  //     return true;
  //   } catch(final SQLException e) {
  //     e.printStackTrace();
  //     System.out.println("Error Code: ");
  //     System.out.println(ErrorCodes.DELETERROR());
  //     return false;
  //   }
  // }
  // static public ArrayList<String> read(final int id) {
  //   try {
  //     final ArrayList<String> fields = new ArrayList<String>();
  //     int i, maxFields;
  //     ResultSet result = null;
  //     PreparedStatement stm = Connect.getCon().prepareStatement(
  //       "SELECT * FROM Image " + "WHERE id = (?)"
  //     );
  //     stm.setInt(1, id);
  //     result = stm.executeQuery();
  //     i = 1;
  //     maxFields = result.getMetaData().getColumnCount();
  //     while (i <= maxFields)
  //       fields.add(result.getString(i++));

  //     stm.close();

  //     return fields;
  //   } catch(SQLException e) {
  //     e.printStackTrace();
  //     System.out.println("Error Code: ");
  //     System.out.println(ErrorCodes.READERROR());
  //     return null;
  //   }
  // }

  // static public ArrayList<String> read(String filePath, String fileName, String fileType) {
  //   filePath = filePath.toLowerCase();
  //   fileName = fileName.toLowerCase();
  //   fileType = fileType.toLowerCase();

  //   try {
  //     final ArrayList<String> fields = new ArrayList<String>();
  //     int i, maxFields;
  //     ResultSet result = null;
  //     PreparedStatement stm = Connect.getCon().prepareStatement(
  //       "SELECT * FROM Image " + "WHERE filePath = (?), fileName = (?), fileType = (?)"
  //     );
  //     stm.setString(1, filePath);
  //     stm.setString(2, fileName);
  //     stm.setString(3, fileType);
  //     result = stm.executeQuery();
  //     maxFields = result.getMetaData().getColumnCount();

  //     i = 1;
  //     while (result.next())
  //       while (i <= maxFields)
  //         fields.add(result.getString(i++));


  //     stm.close();
  //     return fields;
  //   } catch(SQLException e) {
  //     e.printStackTrace();
  //     System.out.println("Error Code: ");
  //     System.out.println(ErrorCodes.READERROR());
  //     return null;
  //   }
  // }
