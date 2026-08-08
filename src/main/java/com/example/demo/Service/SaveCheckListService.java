package com.example.demo.Service;

import com.example.demo.DTO.OneItemsViewDTO;
import com.example.demo.DTO.SaveCheckListDTO;
import com.example.demo.DTO.SaveItemsDTO;
import com.example.demo.Entity.OneCheckListCheckListsEntity;
import com.example.demo.Entity.OneCheckListItemsEntity;
import com.example.demo.Repository.OneCheckListItemsRepository;
import com.example.demo.Repository.OneCheckListRepository;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class SaveCheckListService {
    private OneCheckListRepository repository;
    private OneCheckListItemsRepository iRepository;
    public SaveCheckListService(OneCheckListRepository repository){
        this.repository = repository;
    }
    /*updateColumnsはユーザーが変更したチェックリスト名、日付、項目名、チェックがついているか否かの情報をデータベースに更新するためのメソッドである。*/
    public void updateColumns(SaveCheckListDTO dto){
        try {
            //チェックリスト名、日付の情報をOneCheckListCheckListsEntityに代入して、repositoryを使ってDBに保存する。
            OneCheckListCheckListsEntity checkList = new OneCheckListCheckListsEntity();
            System.out.println(dto.getCheckListsId());
            System.out.println(dto.getCheckListName());
            System.out.println(dto.getItems());
            System.out.println(dto.getYear());
            System.out.println(dto.getMonth());
            System.out.println(dto.getDay());
            Integer checkListsId = Integer.parseInt(dto.getCheckListsId());
            checkList.setCheckListsId(checkListsId);
            checkList.setCheckListsName(dto.getCheckListName());
            Integer year = Integer.parseInt(dto.getYear());
            Integer month = Integer.parseInt(dto.getMonth());
            Integer day = Integer.parseInt(dto.getDay());
            LocalDate saveDate = LocalDate.of(year, month, day);
            checkList.setCreatedAt(saveDate);
            repository.save(checkList);
            //各項目の、項目名、チェックがついているか否かの情報をOneCheckListItemsEntityに代入して、iRepositoryを使ってDBに保存する。
            for (SaveItemsDTO item : dto.getItems()) {
                OneCheckListItemsEntity oneItem = new OneCheckListItemsEntity();
                Integer itemId = Integer.parseInt(item.getItemId());
                oneItem.setItemId(itemId);
                oneItem.setItemName(item.getItemNames());
                oneItem.setIsChecked(item.getIsChecked());
                iRepository.save(oneItem);
            }
        }catch(DataAccessException e){
            throw new RuntimeException("データの保存に失敗しました。");
        }
    }
}
