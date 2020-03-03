package com.LP2.server.resources;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import com.LP2.database.misc.ImageController;

import org.apache.commons.io.IOUtils;

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
    try {
      this.content = IOUtils.toByteArray(new FileInputStream(this.load()));
    } catch(IOException e) {
      e.printStackTrace();
    }
    this.id = ImageController.create(this);
  }

  public Image(String filePath, String fileName, String fileType, int id, byte[] content) {
    this.filePath = filePath;
    this.fileName = fileName;
    this.fileType = fileType;
    this.id = id;
    this.content = content;
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















