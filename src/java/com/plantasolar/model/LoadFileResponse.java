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
public class LoadFileResponse {
    
    public LoadFileResponse(){
        
    }
    
    public LoadFileResponse(String message){
        this.message = message;
    }
    
    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    
    @Override
    public String toString(){
        return "LoadFileResponse{"+"message="+message+"}";
    }
}
