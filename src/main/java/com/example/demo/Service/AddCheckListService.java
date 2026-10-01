package com.example.demo.Service;

import com.example.demo.DTO.AddCheckListDTO;
import com.example.demo.DTO.AddItemsDTO;
import com.example.demo.Entity.AddCheckListEntity;
import com.example.demo.Entity.AddItemsEntity;
import com.example.demo.Entity.AddUserEntity;
import com.example.demo.Repository.AddCheckListRepository;
import com.example.demo.Repository.AddItemsRepository;
import com.example.demo.Repository.AddUserRepository;
import jakarta.transaction.Transactional;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.DateTimeException;

@Service
public class AddCheckListService {
    private final AddUserRepository userRepository;
    private final AddCheckListRepository checkListRepository;
    private final AddItemsRepository itemRepository;
    public AddCheckListService(AddUserRepository userRepository,AddCheckListRepository checkListRepository,AddItemsRepository itemRepository){
        this.userRepository = userRepository;
        this.checkListRepository = checkListRepository;
        this.itemRepository = itemRepository;
    }
    //addCheckListは追加するチェックリストの情報を受け取りDBに格納するメソッドである。
    @Transactional
    public void addCheckList(String userName,AddCheckListDTO dto){
        try {
            AddUserEntity user = userRepository.findByUserName(userName);
            AddCheckListEntity checkList = new AddCheckListEntity();
            checkList.setCheckListName(dto.getCheckListName());
            Integer year = Integer.parseInt(dto.getYear());
            Integer month = Integer.parseInt(dto.getMonth());
            Integer day = Integer.parseInt(dto.getDay());
            LocalDate createdAt = LocalDate.of(year,month,day);
            LocalDate nowDate = LocalDate.now();
            if(createdAt.isBefore(nowDate)){
                throw new IllegalArgumentException("チェックリストに入力する日付に過去の日付を入力しないでください。");
            }
            checkList.setCreatedAt(createdAt);
            checkList.setUserEntity(user);
            checkListRepository.save(checkList);
            for (AddItemsDTO items : dto.getItems()) {
                AddItemsEntity item = new AddItemsEntity();
                item.setItemName(items.getItemName());
                //最初は項目にチェックがされていない状態にするのでfalseにしている。
                item.setIsChecked(false);
                item.setCheckList(checkList);
                itemRepository.save(item);
            }
        }catch(DataAccessException e){
            throw new RuntimeException("チェックリスト新規作成処理に失敗しました。");
        }catch(IllegalArgumentException e){
            throw new RuntimeException(e.getMessage());
        }catch(DateTimeException e){
            throw new RuntimeException("実在しない日付が入力されています。");
        }
    }
}
