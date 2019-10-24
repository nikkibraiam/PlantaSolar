/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.plantasolar.ws;

import com.plantasolar.model.LoadFileRequest;
import com.plantasolar.model.LoadFileResponse;
import com.plantasolar.utils.Utils;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.core.MediaType;

@Path("/files")
public class FilesResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of LoadFile
     */
    public FilesResource() {
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON + ";charset=utf-8")
    @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
    @Path("/loadFile")
    public LoadFileResponse loadFile(LoadFileRequest loadFileRequest){
        LoadFileResponse loadFileResponse = new LoadFileResponse();
        try{        
            Pattern p = Pattern.compile("[0-9]+");
            Matcher m = p.matcher(loadFileRequest.getFileName());
            StringBuffer sBuffer = new StringBuffer();
            while (m.find()) {
                sBuffer.append(m.group());
            }
            String directoryName = sBuffer.toString();
            
            if(Files.notExists(Paths.get(Utils.getConfig("RutaArchivosPlanos") + directoryName))){
                Files.createDirectory(Paths.get(Utils.getConfig("RutaArchivosPlanos") + directoryName));
            }else{
                loadFileResponse.setMessage("NO fue posible crear el directorio");
            }
            File file = new File(
                    Utils.getConfig("RutaArchivosPlanos") + 
                            directoryName + "\\" +
                            loadFileRequest.getFileName() + 
                            loadFileRequest.getFileExtension());
            if(file.createNewFile()){
                try (FileOutputStream fileOutputStream = new FileOutputStream(file)) {
                    fileOutputStream.write(Base64.getDecoder().decode(loadFileRequest.getFileBase64()));
                    fileOutputStream.close();
                }                
            }else{
                loadFileResponse.setMessage("No fue posible crear el file");    
            }         
            loadFileResponse.setMessage(loadFileResponse.getMessage() + " Carga Exitosa Prueba");
        }
        catch(FileNotFoundException th)
        {
            loadFileResponse.setMessage(th.getMessage());
        }
        catch(IOException io){
            loadFileResponse.setMessage(io.getMessage());
        }
        catch(Throwable th){
            loadFileResponse.setMessage(th.getMessage());
        }
        return loadFileResponse;
        
    }
}
