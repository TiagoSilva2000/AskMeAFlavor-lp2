package com.LP2.server.resources;

import java.io.File;

import com.LP2.database.misc.ImageController;

public class Image {
  private String filePath;
  private String fileType;
  private String fileName;
  private int id;


  public Image(String filePath, String fileName, String fileType) {
    this.filePath = filePath;
    this.fileName = fileName;
    this.fileType = fileType;
    this.id = ImageController.create(this);
  }

  public void setFilePath(String filePath) { this.filePath = filePath;}

  public void setFileName(String fileName) { this.fileName = fileName; }

  public void setFileType(String fileType) { this.fileType = fileType; }

  public String getFilePath() { return this.filePath; }

  public String getFileName() { return this.fileName; }

  public String getFileType() { return this.fileType; }

  public int getID() { return this.id; }

  public File load() {
    File loadedImage = new File(this.filePath + this.fileName + this.fileType);
    if (!loadedImage.exists()) {
      System.out.println("This image could not be loaded!");
      System.out.println(this.filePath + this.fileName + this.fileType);
      System.exit(234234);
      return null;
    }

    return loadedImage;
  }
}















