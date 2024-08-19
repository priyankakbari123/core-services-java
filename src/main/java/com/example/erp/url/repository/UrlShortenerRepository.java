package com.example.erp.url.repository;

import com.example.erp.url.domain.UrlShortener;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface UrlShortenerRepository extends MongoRepository<UrlShortener,String> {
    @Query("{ 'hashCode' : ?0 }")
    UrlShortener findByHashCode(String hashCode);
}
