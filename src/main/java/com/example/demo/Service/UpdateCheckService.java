package com.example.demo.Service;

import com.example.demo.Repository.HomeItemsRepository;
import org.springframework.stereotype.Service;

@Service
public class UpdateCheckService {
    private HomeItemsRepository repository;
    public UpdateCheckService(HomeItemsRepository repository){
        this.repository = repository;
    }
}
