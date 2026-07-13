package com.example.demo.Service;

import com.example.demo.DTO.TableDTO;
import com.example.demo.DTO.TableViewDTO;
import com.example.demo.Repository.TableRepository;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class TableService {
    private final TableRepository repository;
    public TableService(TableRepository repository){
        this.repository = repository;
    }
    public List<TableViewDTO> findTableAllCheckLists(String userName){
        List<TableDTO> dto = repository.findTableAllCheckLists(userName);
        List<TableViewDTO> viewDto = new ArrayList<>();
        String checkListsName;
        List<String> itemName = new ArrayList<>();
        List<Boolean> isChecked = new ArrayList<>();
        String createdAt;
        String prev = null;
        for(TableDTO column:dto){
            if(prev == null){
                checkListsName = column.getCheckListsName();
                createdAt = column.getCreatedAt().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            }else if(!(prev.equals(column.getCheckListsName()))){
                //checkListsNameの名前が変わったタイミングでTableViewDTOを作り、viewDtoに代入する。
            }else{
                itemName.add(column.getItemNames());
                isChecked.add(column.getIsChecked());
            }
        }
    }
}
