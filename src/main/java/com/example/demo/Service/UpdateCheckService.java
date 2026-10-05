package com.example.demo.Service;

import com.example.demo.DTO.CheckDTO;
import com.example.demo.Entity.HomeCheckListsItemsEntity;
import com.example.demo.Repository.HomeItemsRepository;
import jakarta.transaction.Transactional;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UpdateCheckService {
    private final HomeItemsRepository repository;
    public UpdateCheckService(HomeItemsRepository repository){
        this.repository = repository;
    }
    /*saveIsCheckedはユーザーがつけた項目に対するチェックを更新するためのメソッドである。
    * ユーザーがチェックボックスに印をつけたら、true、ついていなければfalseに更新する。*/
    @Transactional
    public void saveIsChecked(CheckDTO dto) throws IllegalArgumentException{
        try{
            HomeCheckListsItemsEntity item = repository.findById(dto.getItemId())
                    .orElseThrow(() -> new IllegalArgumentException("指定されたitemIdが見つかりません。"));
            item.setIsChecked(dto.getIsChecked());
            repository.save(item);
        }catch(DataAccessException e){
            throw new RuntimeException("更新処理に失敗しました。");
        }
    }
}
