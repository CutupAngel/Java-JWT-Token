package com.chainhaus.mtg.service;

import org.springframework.stereotype.Service;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.methods.response.EthGetBalance;
import org.web3j.protocol.http.HttpService;

import java.util.concurrent.ExecutionException;

/**
 * Created by Asad Sarwar on 9/30/2021.
 */
@Service
public class Erc721ContractService {

    public String getBalance(String address){
        Web3j web3j = Web3j.build(new HttpService("https://rinkeby.infura.io/v3/949203af2faa455981c61482b03a6932"));

        EthGetBalance balance = null;
        try {
            balance = web3j.ethGetBalance(address, DefaultBlockParameter.valueOf("latest"))
                    .sendAsync()
                    .get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return balance.getBalance() + "";
    }
}
