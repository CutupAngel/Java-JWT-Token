package com.chainhaus.mtg.controller;

import com.chainhaus.mtg.model.BaseResponse;
import com.chainhaus.mtg.util.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

/**
 * Created by Asad Sarwar on 01/07/2020.
 */
@RestController
@RequestMapping("/file")
public class FileController {

    public static final Logger LOGGER = LoggerFactory.getLogger(FileController.class);

    @RequestMapping(value = "/upload", method = POST)
    public ResponseEntity<BaseResponse> upload(@RequestParam("file") MultipartFile fileP, @RequestParam("dealId") String dealId){
        String dirPath = Constants.FILE_PATH.MTG_FILES + dealId;
        String filePath = Constants.FILE_PATH.MTG_FILES + dealId + File.separator + fileP.getOriginalFilename();
        long startTime = System.currentTimeMillis();
        try {

            File dirChecker = new File(dirPath);
            if(!dirChecker.exists()) {
                dirChecker.mkdir();
            }

            File fileChecker = new File(filePath);
            if (fileChecker.exists()) {
                fileChecker.delete();
            }

            fileChecker = new File(filePath);
            fileP.transferTo(fileChecker);

            long endTime = System.currentTimeMillis();

            LOGGER.info("===============================================================================================================");
            LOGGER.info("Upload ms. "+ (endTime - startTime) +" - "+ ((endTime - startTime) / 1000) +" - "+ (((endTime - startTime) / 60) / 60));
            LOGGER.info("===============================================================================================================");

            return ResponseEntity.ok(new BaseResponse("File: " + fileP.getOriginalFilename() + " uploaded."));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new BaseResponse(false, e.getMessage()));
        }
    }

    @GetMapping(value = "/download/{dirName}/{fileName:.+}")
    public void getUserFile(@PathVariable("dirName") String dirName, @PathVariable("fileName") String fileName, HttpServletResponse response) {
        try {
            response.setHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + fileName);
            if(fileName.endsWith(".png")){
                response.setContentType("image/png");

            }else if(fileName.endsWith(".jpg")){
                response.setContentType("image/jpg");

            }else{
                response.setContentType("image/jpeg");
            }

            LOGGER.info("Downloading file: " + fileName);
            LOGGER.info("Downloading path: " + (Constants.FILE_PATH.MTG_FILES + dirName + File.separator + fileName));

            OutputStream outputStream = response.getOutputStream();
            System.out.print(outputStream);

            FileInputStream fis = new FileInputStream(Constants.FILE_PATH.MTG_FILES.concat(dirName).concat(File.separator).concat(fileName));
            int b;
            while ((b = fis.read()) > -1){
                outputStream.write(b);
            }

            fis.close();
            outputStream.close();

        } catch (Exception ex) {
            LOGGER.info("Error writing file to output stream. Filename was '{}'", fileName, ex);
            throw new RuntimeException("IOError writing file to output stream and" + fileName);
        }
    }

    @GetMapping(value = "/open/{fileName:.+}")
    public void openUserFileInBrowser(@PathVariable("fileName") String fileName, HttpServletResponse response) {
        try {
            response.setHeader(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=" + fileName);
            response.setContentType("application/pdf");

            LOGGER.info("Opened file: " + (Constants.FILE_PATH.MTG_FILES + fileName));

            OutputStream outputStream = response.getOutputStream();
            FileInputStream fis = new FileInputStream(Constants.FILE_PATH.MTG_FILES.concat(fileName));
            int b;
            while ((b = fis.read()) > -1){
                outputStream.write(b);
            }
            fis.close();
            outputStream.close();

        } catch (Exception ex) {
            LOGGER.info("Error writing file to output stream. Filename was '{}'", fileName, ex);
            throw new RuntimeException("IOError writing file to output stream");
        }
    }

}
