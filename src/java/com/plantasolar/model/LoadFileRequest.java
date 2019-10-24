/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.plantasolar.model;

import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Braiam
 */
@XmlRootElement
public class LoadFileRequest {
    
    public LoadFileRequest(){
        
    }
    
    public LoadFileRequest(String fileName, String fileBase64){
        this.fileName = fileName;
        
        this.fileBase64 = fileBase64;
    }
    
    private String fileName;
    
    private String fileExtension;
    
    private String fileBase64;

    public String getFileName() {
        return fileName;
    }

    public String getFileBase64() {
        return fileBase64;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public void setFileBase64(String fileBase64) {
        this.fileBase64 = fileBase64;
    }

    public String getFileExtension() {
        return fileExtension;
    }

    public void setFileExtension(String fileExtension) {
        this.fileExtension = fileExtension;
    }
    
    
    @Override
    public String toString(){
        return "LoadFileRequest{"+"fileName="+fileName+", fileExtension="+fileExtension+", fileBase64"+fileBase64+"}";
    }
    
}
