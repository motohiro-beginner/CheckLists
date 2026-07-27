package com.example.demo.Service;

import com.example.demo.Repository.TableRepository;
import org.springframework.stereotype.Service;

@Service
public class SearchService {
    private TableRepository repository;
    public SearchService(TableRepository repository){
        this.repository = repository;
    }
}
