package com.example.erp.url.service;

import com.example.erp.config.cache.CacheService;
import com.example.erp.url.domain.UrlShortener;
import com.example.erp.url.repository.UrlShortenerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class UrlShortenerService {

    @Autowired
    UrlShortenerRepository urlShortenerRepository;

    @Autowired
    CacheService cacheService;


    public UrlShortener shorUrl(String url) throws Exception {
        UrlShortener urlShortener = new UrlShortener();
        urlShortener.setUrl(url);
        urlShortener.setCreatedAt(LocalDateTime.now());
        urlShortener.setHashCode(hash(url + LocalDateTime.now()));
        urlShortenerRepository.save(urlShortener);
        return urlShortener;
    }

    public String url(String code) throws Exception {
        return urlShortenerRepository.findByHashCode(code).getUrl().trim();
    }

    public List<UrlShortener> urls() throws Exception {
        List<UrlShortener> urls = (List<UrlShortener>) cacheService.getListValueFromCache("URLS");
        if (urls != null) {
            System.out.println("FROM CACHE");
            return urls;
        }
        urls = urlShortenerRepository.findAll();
        cacheService.putValueInCache("URLS", urls);
        return urls;
    }

    private String hash(String value) throws Exception {
        return DigestUtils.md5DigestAsHex(value.getBytes(StandardCharsets.UTF_8));
    }

}
