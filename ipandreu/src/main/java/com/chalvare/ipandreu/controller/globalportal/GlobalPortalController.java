package com.chalvare.ipandreu.controller.globalportal;

import com.chalvare.ipandreu.dto.AuctionDTO;
import com.chalvare.ipandreu.service.global.GlobalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/global")
public class GlobalPortalController {

    private final GlobalService globalService;

    @Autowired
    public GlobalPortalController(GlobalService globalService) {
        this.globalService = globalService;
    }

    /**
     * GetAuctionsCustomer: get the auctions per the customer
     */
    @GetMapping("/auctions/{customer}")
    public Flux<AuctionDTO> getAuctionsDto(@PathVariable("customer")String customer){
         return globalService.getAuctionsFromCustomer(customer);
    }


}
