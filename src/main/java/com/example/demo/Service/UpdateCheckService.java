package com.example.demo.Service;

import com.example.demo.DTO.CheckDTO;
import com.example.demo.Entity.HomeCheckListsItemsEntity;
import com.example.demo.Repository.HomeItemsRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UpdateCheckService {
    private HomeItemsRepository repository;
    public UpdateCheckService(HomeItemsRepository repository){
        this.repository = repository;
    }
    @Transactional
    public void saveIsChecked(CheckDTO dto) throws IllegalArgumentException{
        HomeCheckListsItemsEntity item = repository.findById(dto.getItemId())
                .orElseThrow(() -> new IllegalArgumentException("指定されたitemIdが見つかりません。"));
        item.setIsChecked(dto.getIsChecked());
        repository.save(item);
    }
}
