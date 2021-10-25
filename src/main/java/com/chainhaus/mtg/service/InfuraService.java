package com.chainhaus.mtg.service;

import org.springframework.stereotype.Service;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.methods.response.EthGetBalance;
import org.web3j.protocol.http.HttpService;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * Created by Jamali on 9/30/2021.
 */
@Service
public class InfuraService {

    final Web3j web3j = Web3j.build(new HttpService("https://rinkeby.infura.io/v3/949203af2faa455981c61482b03a6932"));
    final String ethAddress = "0x3d2916a46115d5bec8f61254368111ce12879181";

    public void printBalance(){
        try {
            EthGetBalance balance =
                web3j
                    .ethGetBalance(ethAddress, DefaultBlockParameter.valueOf("latest"))
                    .sendAsync()
                    .get(10, TimeUnit.SECONDS);

            System.out.println(balance.getBalance());


        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
    }
}
