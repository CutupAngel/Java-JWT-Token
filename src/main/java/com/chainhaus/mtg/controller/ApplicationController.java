package com.chainhaus.mtg.controller;

import com.chainhaus.mtg.entity.Minted;
import com.chainhaus.mtg.repository.MintedRepository;
import com.chainhaus.mtg.util.AuthUtil;
import com.chainhaus.mtg.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

/**
 * Created by Jamali on 9/9/2021.
 */
@Controller
public class ApplicationController {

    @Autowired
    private MintedRepository mintedRepository;

    private String succesRedirectUrl;
    public ApplicationController(@Autowired Environment environment){
        succesRedirectUrl = environment.getProperty(Constants.MTG_INSTA_SUCCESS_REDIRECT_URL, String.class);
    }

    @RequestMapping("/login")
    public String login(Model model) {
        if(AuthUtil.getLoggedInUser() != null){
            return "dashboard";

        }else{
            return "login";
        }
    }

    @RequestMapping("/")
    public String home(Model model) {
        model.addAttribute("success_url", succesRedirectUrl);
        return "dashboard";
    }

    @RequestMapping("/dashboard")
    public String dashboard(Model model) {
        model.addAttribute("success_url", succesRedirectUrl);
        return "dashboard";
    }

    @RequestMapping("/view/mintedList")
    public String mintedList() {
        return "mintedList";
    }

    @RequestMapping("/view/minted/{mintedId}")
    public String mintedView(@PathVariable Long mintedId, Model model) {
//        Optional<Minted> mintedOpt = mintedRepository.findById(mintedId);
//        if(mintedOpt.isPresent()){
//            Minted minted = mintedOpt.get();
//            model.addAttribute("ipfs_hash", minted.getIpfsHash());
//            model.addAttribute("timestamp", minted.getCreatedDate());
//            model.addAttribute("ipfs_url", minted.getIpfsUrl());
//        }
        model.addAttribute("minted_id", mintedId);
        return "mintedView";
    }

    @RequestMapping("/minted/{mintedId}")
    public String minted(@PathVariable Long mintedId, Model model) {

        Optional<Minted> mintedOpt = mintedRepository.findById(mintedId);
        if(mintedOpt.isPresent()){
            Minted minted = mintedOpt.get();
            model.addAttribute("image_url", minted.getImageUrl());
            model.addAttribute("ipfs_hash", minted.getIpfsHash());
            model.addAttribute("timestamp", minted.getCreatedDate());
            model.addAttribute("ipfs_url", minted.getIpfsUrl());
        }

        return "minted";
    }
}
