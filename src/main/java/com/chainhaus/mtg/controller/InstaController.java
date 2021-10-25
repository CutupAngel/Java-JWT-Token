package com.chainhaus.mtg.controller;

import com.chainhaus.mtg.entity.Minted;
import com.chainhaus.mtg.exception.exception.IPFSDuplicateException;
import com.chainhaus.mtg.repository.MintedRepository;
import com.chainhaus.mtg.service.InfuraService;
import com.chainhaus.mtg.service.InstaService;
import com.chainhaus.mtg.service.IpfsService;
import com.chainhaus.mtg.util.AuthUtil;
import com.chainhaus.mtg.util.Constants;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;
//import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Asad Sarwar on 9/9/2021.
 */
@Controller
@RequestMapping("/insta")
public class InstaController {

    Logger logger = LoggerFactory.getLogger("InstaController");

    @Autowired
    InstaService instaService;

    @Autowired
    IpfsService ipfsService;

    @Autowired
    MintedRepository mintedRepository;

    @Value(Constants.IPFS_API_URL)
    private String ipfsApiUrl;

//    @GetMapping("/valid")
//    public String successAuth(@RequestParam String code, Model model) {
//        try {
//            logger.info("Insta user authenticated: ");
//            logger.info(code);
//            logger.info("Getting user token");
//            JSONObject token = instaService.getUserToken(code);
//            logger.info("token: " + token);
//            logger.info("access_token: " + token.getString("access_token"));
//            logger.info("user_id " + token.getLong("user_id"));
//
//            model.addAttribute("access_token", token.getString("access_token"));
//            model.addAttribute("user_id", token.getLong("user_id"));
//
////            model.addAttribute("access_token", "IGQVJXMkdTTWt3TmN5UzlVbW9sZA0lkcWlDZA2pUOElIYzFCWDl1ZAnYzcWN6T0JTcjh2M3pHWTJQREtLdjNTZAUpzMkRjMHIyY0Y0cVVIYkRxMUNyaUNnNVZAmaEZAKcWE3N3ZAZAQkxCaDR0ekxYTXNvazM2TjZAzMzVZAMkdMNmpz");
////            model.addAttribute("user_id", "17841449262687969");
//
//            return "instaList";
//        }catch (Exception e){
//            return "dashboard";
//        }
//    }


    @GetMapping("/getUserToken/{code}")
    public ResponseEntity<String> getUserToken(@PathVariable String code) {
        try {
            logger.info("Insta user authenticated: ");
            logger.info(code);
            logger.info("Getting user token");
            String token = instaService.getUserToken(code);

            return ResponseEntity.ok(token);
        }catch (Exception e){
            return ResponseEntity.status(401).body("Not Authenticated");
        }
    }


    @GetMapping("/view")
    public String successAuth(@RequestParam String mediaId, @RequestParam String accessToken, @RequestParam String userId,Model model) {
        try {
            model.addAttribute("access_token", accessToken);
            model.addAttribute("user_id", userId);
            model.addAttribute("media_id", mediaId);

            return "instaView";
        }catch (Exception e){
            return "dashboard";
        }
    }

    @GetMapping("/invalid")
    public String failureAuth(){
        return "dashboard";
    }

    @GetMapping("/latestMinted")
    public ResponseEntity<List<Minted>> latestMinted(){
        return ResponseEntity.ok(mintedRepository.findAllByCreatedByOrderByCreatedDate(AuthUtil.getLoggedInUserId()));
    }

    @PostMapping("/mint")
    public ResponseEntity<String> mintImage(
                        @RequestParam String imageUrl,
                        @RequestParam String description,
                        @RequestParam String caption,
                        @RequestParam String address
    ){
        try{
            File file = ipfsService.getFile(imageUrl);
            String cid = ipfsService.addFile(file);
//
            String metadata = ipfsService.createMetadata(AuthUtil.getLoggedInUserId() + "", "instagram", imageUrl, "https://ipfs.io/ipfs/" + cid, new Date().toLocaleString(), description, "1");
            File metadataFile = ipfsService.createMetadataFile(metadata);
            String metaCid = ipfsService.addFile(metadataFile);
            logger.info("META CID: " + metaCid);

            Long mintedId = ipfsService.saveMinted(imageUrl, cid, "https://ipfs.io/ipfs/" + cid, address, metadata, metaCid, caption, description);



            return ResponseEntity.ok(mintedId+"");

        }catch (IOException e){
            return ResponseEntity.status(403).body(e.getMessage());
        }catch (IPFSDuplicateException e){
            return ResponseEntity.status(403).body("File hash already exist!");
        }
    }


    @Autowired
    InfuraService infuraService;

    @GetMapping("/printBalance")
    public ResponseEntity<String> printBalance(){
        infuraService.printBalance();
        return ResponseEntity.ok("Check console");
    }


//    @GetMapping("/file")
//    public ResponseEntity<String> writeFile(@RequestParam int url){
//        String[] urls = new String[]{
//                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTDSQmNgmTYTxMH-4d_3w-qVK2SeUYhYfqfLg&usqp=CAU",
//                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRLMl4_ScwHun3sWMAmlDsshDOzMCpJIWXsXQ&usqp=CAU",
//                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTDfjM-pNLe7wU-1Rv5OvyoHxKfo2XA9gt8Lw&usqp=CAU",
//                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcT1E5SKljnQvLKVwFk1dcOTKNBVGvbyDNl_qA&usqp=CAU",
//                "",
//                "",
//                "",
//                "",
//                ""
//        };
//
//        String cid = ipfsService.addFile(ipfsService.getFile(urls[url]));
//        return ResponseEntity.ok(cid);
//    }



}
