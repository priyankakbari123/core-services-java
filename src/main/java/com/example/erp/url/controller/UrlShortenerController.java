package com.example.erp.url.controller;

import com.example.erp.url.domain.UrlShortener;
import com.example.erp.url.service.UrlShortenerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UrlShortenerController {
    @Autowired
    UrlShortenerService urlShortenerService;

    @PostMapping(value = "/short/url",produces = "application/json")
    public UrlShortener shortUrl(@RequestBody String url)throws Exception{
        try{
            return urlShortenerService.shorUrl(url);
        }catch (Exception e){
            throw e;
        }
    };

    @GetMapping(value ="/fetch/url/code/{code}",produces = "application/json")
    public String getUrl(@PathVariable("code")String code)throws Exception{
        try{
            return urlShortenerService.url(code);
        }catch (Exception e){
            throw e;
        }
    }

    @GetMapping(value ="/fetch/url/all",produces = "application/json")
    public List<UrlShortener> getUrls()throws Exception{
        try{
            return urlShortenerService.urls();
        }catch (Exception e){
            throw e;
        }
    }
}
