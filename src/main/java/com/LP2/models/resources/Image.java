package com.LP2.models.resources;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import com.LP2.database.misc.ImageDAO;

import org.apache.commons.io.FilenameUtils;

public class Image {
  private String filePath;
  private String fileType;
  private String fileName;
  private int id;
  byte[] content;
  // private File file;

  public Image(String filePath, String fileName, String fileType) {
    this.filePath = filePath;
    this.fileName = fileName;
    this.fileType = fileType;
    // this.file = this.load();

    File tmpf = this.load();
    this.content = new byte[(int) tmpf.length()];
    try {
      FileInputStream fis = new FileInputStream(tmpf);
      fis.read(this.content);
      fis.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
    this.id = ImageDAO.create(this);
  }

  public Image(final File file) {
    System.out.println("1");
    this.filePath = file.getParent() + "/";
    this.fileName = file.getName();
    this.fileType = FilenameUtils.getExtension(file.getName());
    this.content = new byte[(int) file.length()];
    System.out.println("2");
    try {
      FileInputStream fis = new FileInputStream(file);
      fis.read(this.content);
      System.out.println("3");
      fis.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
    this.id = ImageDAO.create(this);
    System.out.println("4");
  }

  public Image(final int id, final String filePath, final String fileName, final String fileType,
      final byte[] content) {
    this.id = id;
    this.filePath = filePath;
    this.fileName = fileName;
    this.fileType = fileType;
    this.content = content;

    // File dir = new File(System.getProperty("user.dir") + "/public/images");
    // File tmpFile = null;
    // try {
    //   tmpFile = File.createTempFile(fileName, "." + fileType, dir);
    // } catch(final IOException e) {
    //   e.printStackTrace();
    // }

    // try {
    //   OutputStream os = new FileOutputStream(tmpFile);
    //   os.write(content);
    //   System.out.println("Write bytes to file.");
    //   os.close();
    // } catch (Exception e) {
    //   e.printStackTrace();
    // }
  }

  public Image(final int id) {
    Image holder = ImageDAO.read(id);
    this.id = holder.id;
    this.filePath = holder.filePath;
    this.fileName = holder.fileName;
    this.fileType = holder.fileType;
    this.content = holder.content;

    // File dir = new File(System.getProperty("user.dir") + "/public/images");
    // File tmpFile = null;
    // try {
    //   tmpFile = File.createTempFile(holder.fileName, "." + holder.fileType, dir);
    // } catch(final IOException e) {
    //   e.printStackTrace();
    // }

    // try {
    //         OutputStream os = new FileOutputStream(tmpFile);
    //         os.write(content);
    //         System.out.println("Write bytes to file.");
    //         os.close();
    //     } catch (Exception e) {
    //         e.printStackTrace();
    //     }
  }

  public void setFilePath(String filePath) {
    this.filePath = filePath;
  }

  public void setFileName(String fileName) {
    this.fileName = fileName;
  }

  public void setFileType(String fileType) {
    this.fileType = fileType;
  }

  public String getFilePath() {
    return this.filePath;
  }

  public String getFileName() {
    return this.fileName;
  }

  public String getFileType() {
    return this.fileType;
  }

  public int getID() {
    return this.id;
  }

  public byte[] getContent() {
    return this.content;
  }

  public File load() {
    File loadedImage = new File(this.filePath + this.fileName);
    if (!loadedImage.exists()) {
      System.out.println("This image could not be loaded!");
      System.out.println(this.filePath + this.fileName + this.fileType);
      System.exit(234234);
      return null;
    }

    return loadedImage;
  }
}
