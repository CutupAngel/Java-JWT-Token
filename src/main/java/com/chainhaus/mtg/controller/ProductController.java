package com.chainhaus.mtg.controller;

//import com.chainhaus.mtg.config.AppConfig;
import com.chainhaus.mtg.entity.AskHistory;
import com.chainhaus.mtg.entity.BidAskHistory;
import com.chainhaus.mtg.entity.Minted;
import com.chainhaus.mtg.model.Product;
import com.chainhaus.mtg.repository.AskHistoryRepository;
import com.chainhaus.mtg.repository.BidAskHistoryRepository;
import com.chainhaus.mtg.repository.MintedRepository;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/product")
public class ProductController {

    private final MintedRepository mintedRepository;
    private final BidAskHistoryRepository bidAskHistoryRepository;
    private final AskHistoryRepository askHistoryRepository;

    public ProductController(MintedRepository mintedRepository, BidAskHistoryRepository bidAskHistoryRepository, AskHistoryRepository askHistoryRepository) {
        this.mintedRepository = mintedRepository;
        this.bidAskHistoryRepository = bidAskHistoryRepository;
        this.askHistoryRepository = askHistoryRepository;
    }

    @GetMapping("")
    public List<Product> getProduct() {
        return getAllProducts();
    }

    @GetMapping("/{pid}/{uid}")
    public Product getCatalog(@PathVariable("pid") Integer pid, @PathVariable("uid") Integer uid) {
        List<Product> products = getAllProducts();
        List<Product> list = products.stream().filter(p -> p.getProductId().equals(pid) && p.getOwnerId().equals(uid)).collect(Collectors.toList());
        if (list.size() > 0) {
            return list.get(0);
        }
        return null;
    }

    @GetMapping("/{uid}")
    public List<Product> getByUserId(@PathVariable("uid") Integer uid) {
        List<Product> products = getAllProducts();
        return products.stream().filter(p -> p.getOwnerId().equals(uid)).collect(Collectors.toList());
    }

    @GetMapping("getById/{pid}")
    public Product getProduct(@PathVariable("pid") Integer pid) {
        List<Product> products = getAllProducts();
        List<Product> list = products.stream().filter(p -> p.getProductId().equals(pid.longValue())).collect(Collectors.toList());
        if (list.size() > 0) {
            return list.get(0);
        }
        return null;
    }

    @PostMapping("/addBid")
    public BidAskHistory saveBid(@RequestBody BidAskHistory buyerView) {
        buyerView.setCreatedAt(LocalDateTime.now());
        return bidAskHistoryRepository.save(buyerView);
    }

    @GetMapping("/maxBid/{pid}")
    public BidAskHistory maxBid(@PathVariable Integer pid) {
        List<BidAskHistory> list = bidAskHistoryRepository.getMaxBidHistory(pid);
        if (list.size() > 0) {
            return list.get(0);
        }
        return null;
    }

    @PostMapping("/addAsk")
    public AskHistory saveAsk(@RequestBody AskHistory askHistory) {
        askHistory.setCreatedAt(LocalDateTime.now());
        askHistory = askHistoryRepository.save(askHistory);
        BidAskHistory history = new BidAskHistory();
        history.setAskPrice(askHistory.getAskPrice());
        history.setCreatedAt(LocalDateTime.now());
        history.setPid(askHistory.getPid());
        history = bidAskHistoryRepository.save(history);
        return askHistory;
    }

    @GetMapping("/latestAsk")
    public AskHistory maxAsk() {
        return askHistoryRepository.getMaxAskHistory();
    }

    @GetMapping("/getBidAskHistory/{pid}")
    public List<BidAskHistory> maxAsk(@PathVariable("pid") Integer pid) {
        return bidAskHistoryRepository.findAllByPid(pid);
    }

    private List<Product> getAllProducts(){
        List<Product> products = new LinkedList<>();
        for(Minted minted : mintedRepository.findAll()){
            products.add(new Product(minted.getId(), minted.getImageUrl(),  minted.getCreatedBy(), minted.getAskingProce()));
        }
        return products;
    }
}
