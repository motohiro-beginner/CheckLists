package com.example.demo.Service;

import com.example.demo.DTO.*;
import com.example.demo.Entity.OneCheckListCheckListsEntity;
import com.example.demo.Entity.OneCheckListItemsEntity;
import com.example.demo.Repository.OneCheckListItemsRepository;
import com.example.demo.Repository.OneCheckListRepository;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class SaveCheckListService {
    private OneCheckListRepository repository;
    private OneCheckListItemsRepository iRepository;
    public SaveCheckListService(OneCheckListRepository repository,OneCheckListItemsRepository iRepository){
        this.repository = repository;
        this.iRepository = iRepository;
    }
    /*updateColumnsはユーザーが変更したチェックリスト名、日付、項目名、チェックがついているか否かの情報をデータベースに更新するためのメソッドである。*/
    public void updateColumns(SaveCheckListDTO dto){
        try {
            //チェックリスト本体を保存する。
            OneCheckListCheckListsEntity checkList = new OneCheckListCheckListsEntity();
            /*テスト用コード
            System.out.println(dto.getCheckListsId());
            System.out.println(dto.getCheckListName());
            System.out.println(dto.getItems());
            System.out.println(dto.getYear());
            System.out.println(dto.getMonth());
            System.out.println(dto.getDay());*/
            Integer checkListsId = Integer.parseInt(dto.getCheckListsId());
            checkList.setCheckListsId(checkListsId);
            checkList.setCheckListsName(dto.getCheckListName());
            //DTOではStringで受け取った日付をLocalDateに変換する。
            Integer year = Integer.parseInt(dto.getYear());
            Integer month = Integer.parseInt(dto.getMonth());
            Integer day = Integer.parseInt(dto.getDay());
            //LocalDate.ofで実在する日付かどうかをチェックすることができる。
            LocalDate saveDate = LocalDate.of(year, month, day);
            LocalDate nowDate = LocalDate.now();
            if(saveDate.isBefore(nowDate)){
                throw new IllegalArgumentException("チェックリストに入力する日付に過去の日付を入力しないでください。");
            }
            checkList.setCreatedAt(saveDate);
            repository.save(checkList);
            //チェックリストに含まれる各項目を保存する。
            for (SaveItemsDTO item : dto.getItems()) {
                OneCheckListItemsEntity oneItem = new OneCheckListItemsEntity();
                Integer itemId = Integer.parseInt(item.getItemId());
                oneItem.setItemId(itemId);
                oneItem.setItemName(item.getItemNames());
                oneItem.setIsChecked(item.getIsChecked());
                iRepository.save(oneItem);
            }
            for(NewItemsDTO item:dto.getNewItems()){
                OneCheckListItemsEntity newItem = new OneCheckListItemsEntity();
                newItem.setItemName(item.getItemName());
                newItem.setIsChecked(item.getIsChecked());
                iRepository.save(newItem);
            }
            //データベースに保存されているとある項目の情報が更新された項目の中に無かった場合、その項目を削除する。
            //具体的にはデータベースから取得したitemIdとdtoの中のitemIdを突き合わせる。
            List<Integer> oldItems = iRepository.findItemIdByCheckListsId(Integer.parseInt(dto.getCheckListsId()));
            List<Integer> someItemId = new ArrayList<>();
            for(SaveItemsDTO saveDTO:dto.getItems()){
                someItemId.add(Integer.parseInt(saveDTO.getItemId()));
            }
            for(Integer oldItem:oldItems){
                if(!someItemId.contains(oldItem)){
                    iRepository.deleteItemsByItemId(oldItem);
                }
            }
        }catch(DataAccessException e){
            //Serviceクラスの例外をControllerクラスに伝播する。
            throw new RuntimeException("データの保存に失敗しました。");
        }catch(IllegalArgumentException e){
            throw new RuntimeException(e.getMessage());
        }catch(DateTimeException e){
            throw new RuntimeException("実在しない日付が入力されています。");
        }
    }
}
