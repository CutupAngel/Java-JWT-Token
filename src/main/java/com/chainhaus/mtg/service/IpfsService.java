package com.chainhaus.mtg.service;

import com.chainhaus.mtg.entity.Minted;
import com.chainhaus.mtg.exception.exception.IPFSDuplicateException;
import com.chainhaus.mtg.exception.exception.IPFSException;
import com.chainhaus.mtg.repository.MintedRepository;
import com.chainhaus.mtg.util.AuthUtil;
import com.chainhaus.mtg.util.Util;
import io.ipfs.api.IPFS;
import io.ipfs.api.MerkleNode;
//import io.ipfs.api.Multipart;
import io.ipfs.api.NamedStreamable;
import io.ipfs.multihash.Multihash;
//import okhttp3.OkHttpClient;
//import okhttp3.Request;
//import okhttp3.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import com.chainhaus.mtg.util.Constants;
import org.springframework.web.client.RestTemplate;

import javax.imageio.ImageIO;
//import javax.persistence.Column;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URL;
//import java.nio.file.Path;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * Created by Jamali on 9/9/2021.
 */
@Service
public class IpfsService {

    Logger logger = LoggerFactory.getLogger("IpfsService");
    private String filesPath = null;
    IPFS ipfs = null;

    @Autowired
    MintedRepository mintedRepository;

    public IpfsService(@Autowired Environment environment){
        try {
            filesPath = environment.getProperty(Constants.MTG_FILE_DIR_PATH, String.class);
            ipfs = new IPFS(environment.getProperty(Constants.IPFS_API_URL, String.class));
        }catch (Exception e){
            logger.error("Could not create IPFS instance", e);
//            throw new IPFSException(e.getMessage());
        }
    }

    public String addFile(File file) throws IPFSDuplicateException{
        try {
            List<Multihash> hashes = ipfs.refs.local();
            NamedStreamable.FileWrapper fileWrapper = new NamedStreamable.FileWrapper(file);
            MerkleNode result = ipfs.add(fileWrapper).get(0);
            String newHash = result.hash.toBase58();
            if(cidExits(hashes, newHash)){
                logger.error("HASH: " + newHash);
                throw new IPFSDuplicateException("Hash already exits!");
            }
            return newHash;
        }catch (IPFSDuplicateException e){
            throw e;
        }
        catch (Exception e){
            logger.error("Could not write file to IPFS instance");
            throw new IPFSException(e.getMessage());
        }
    }

    public Long saveMinted(String imageUrl, String ipfsHash, String ipfsUrl, String address, String metaData, String metaDataHash, String caption, String description){

        Minted minted = new Minted();
        minted.setImageUrl(imageUrl);
        minted.setIpfsHash(ipfsHash);
        minted.setIpfsUrl(ipfsUrl);

        minted.setAddress(address);
        minted.setMetaData(metaData);
        minted.setMetaDataIpfsHash(metaDataHash);
        minted.setCaption(caption);
        minted.setDescription(description);

        minted.setCreatedBy(AuthUtil.getLoggedInUserId());
        minted.setCreatedDate(Util.getCurrentTimeStamp());

        mintedRepository.save(minted);

        return minted.getId();

    }

    //@todo move to fileservice
    public File getFile(String url) throws IOException{
        File file = null;
        try {

            RestTemplate restTemplate = new RestTemplate();
            byte[] imageBytes = restTemplate.getForObject(url, byte[].class);
            String filePath = filesPath + File.separator + Util.getFileName(url);
            Files.write(Paths.get(filePath), imageBytes);

            file = new File(filePath);
            if(!file.exists()){
                throw new IOException("Unable to write file.");
            }


//            BufferedImage bufferedImage = ImageIO.read(new URL(url));
//            String filePath = filesPath + File.separator + Util.getFileName(url);
//            file = new File(filePath);
//            if(!file.exists()){
//                file.createNewFile();
//            }
//            boolean written = ImageIO.write(bufferedImage, Util.getImageFormat(filePath), file );
//            logger.info("Image IO result " + written);
//            if(!written){
//                throw new IOException("Unable to write file.");
//            }


//            OkHttpClient client = new OkHttpClient();
//            Request request = new Request.Builder().url(url).build();
//            Response response = client.newCall(request).execute();
//            if (!response.isSuccessful()) {
//            }
//            FileOutputStream fos = null;
////            file = new File(File.separator + "FILES" + File.separator + "testFile.jpg");
//            file = new File(filesPath + File.separator + Util.getFileName(url));
//            file.createNewFile();
//            fos = new FileOutputStream(file);
//            fos.write(response.body().bytes());
//            fos.close();

        } catch (IOException e) {
            logger.error("",e);
            throw new IOException("Failed to download image file.", e);
        } catch (Exception e){
            logger.error("",e);
            throw new IOException(e.getMessage(), e);
        }

        return file;
    }

    public String createMetadata(String userid, String source, String postId, String mediaURL, String mintTimeStamp, String description, String totalSupply){
        try {
            StringBuilder metaData = new StringBuilder();
            metaData.append("{");
            metaData.append("\"userid\": \"".concat(userid).concat("\","));
            metaData.append("\"source\": \"".concat(source).concat("\","));
            metaData.append("\"postId\": \"".concat(postId).concat("\","));
            metaData.append("\"mediaURL\": \"".concat(mediaURL).concat("\","));
            metaData.append("\"mintTimeStamp\": \"".concat(mintTimeStamp).concat("\","));
            metaData.append("\"description\": \"".concat(description).concat("\","));
            metaData.append("\"totalSupply\": \"".concat(totalSupply).concat("\","));
            metaData.append("\"secretHash\": \"".concat("0x3d2916a46115d5bec8f61254368111ce12879181").concat("\""));
            metaData.append("}");

            return metaData.toString();
        } catch (Exception e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return null;
    }

    public File createMetadataFile(String metaData){
        try {
            Random random = new Random();
            String fileName = (new Date().getTime()) + "_" + (random.nextInt(999999 - 100000) + 100000) + ".txt";
            FileWriter myWriter = new FileWriter(filesPath + File.separator + fileName);
            myWriter.write(metaData);
            myWriter.close();

            return new File(filesPath + File.separator + fileName);
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return null;
    }

    private boolean cidExits(List<Multihash> storedFiles, String newHash){
        try{
            for(Multihash multihash : storedFiles){
                if(multihash.toBase58().equals(newHash)){
                    return true;
                }
            }
        }catch (Exception e){
            logger.error(e.getMessage(), e);
            return false;
        }
        return false;
    }

}
