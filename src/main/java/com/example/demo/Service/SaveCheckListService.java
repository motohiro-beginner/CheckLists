package com.example.demo.Service;

import com.example.demo.DTO.SaveCheckListDTO;
import com.example.demo.Entity.OneCheckListCheckListsEntity;
import com.example.demo.Repository.OneCheckListRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class SaveCheckListService {
    private OneCheckListRepository repository;
    public SaveCheckListService(OneCheckListRepository repository){
        this.repository = repository;
    }
    /*updateColumnsはユーザーが変更したチェックリスト名、日付、項目名、チェックがついているか否かの情報をデータベースに更新するためのメソッドである。*/
    public void updateColumns(SaveCheckListDTO dto){
        //チェックリスト名、日付の情報をOneCheckListCheckListsEntityに代入して、repositoryを使ってDBの保存する。
        OneCheckListCheckListsEntity checkList = new OneCheckListCheckListsEntity();
        Integer checkListsId = Integer.parseInt(dto.getCheckListsId());
        checkList.setCheckListsId(checkListsId);
        checkList.setCheckListsName(dto.getCheckListName());
        Integer year = Integer.parseInt(dto.getYear());
        Integer month = Integer.parseInt(dto.getMonth());
        Integer day = Integer.parseInt(dto.getDay());
        LocalDate saveDate = LocalDate.of(year,month,day);
        checkList.setCreatedAt(saveDate);
    }
}
