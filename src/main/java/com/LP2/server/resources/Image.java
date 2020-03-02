package com.LP2.server.resources;

import java.io.File;

public class Image {
  private String filePath;
  private String fileType;
  private String fileName;
  private int id;


  public Image(String filePath, String fileName, String fileType) {
    this.filePath = filePath;
    this.fileName = fileName;
    this.fileType = fileType;
    this.id = -1;
  }

  private int setID(final int id) { this.id = id;  return id; }

  public void setFilePath(String filePath) { this.filePath = filePath;}

  public void setFileName(String fileName) { this.fileName = fileName; }

  public void setFileType(String fileType) { this.fileType = fileType; }

  public String getFilePath() { return this.filePath; }

  public String getFileName() { return this.fileName; }

  public String getFileType() { return this.fileType; }

  public int getID() { return this.id; }

  public File load() {
    File loadedImage = new File(this.filePath + this.fileName + this.fileType);
    if (loadedImage == null) {
      System.out.println("This image could not be loaded!");
      return null;
    }

    return loadedImage;
  }
}















