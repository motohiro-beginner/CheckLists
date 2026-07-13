package com.example.demo.Service;

import com.example.demo.Repository.TableRepository;
import org.springframework.stereotype.Service;

@Service
public class TableService {
    private final TableRepository repository;
    public TableService(TableRepository repository){
        this.repository = repository;
    }
}
