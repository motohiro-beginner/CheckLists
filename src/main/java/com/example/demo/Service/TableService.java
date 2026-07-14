package com.example.demo.Service;

import com.example.demo.DTO.TableDTO;
import com.example.demo.DTO.TableItemsViewDTO;
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
        String checkListsName = null;
        List<TableItemsViewDTO> items = new ArrayList<>();
        List<String> itemName = new ArrayList<>();
        List<Boolean> isChecked = new ArrayList<>();
        String createdAt = null;
        String prev = null;
        for(TableDTO column:dto){
            if(prev == null){
                //最初の繰り返しのときにこの部分を実行する。
                checkListsName = column.getCheckListsName();
                createdAt = column.getCreatedAt().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            }else if(!(prev.equals(column.getCheckListsName()))){
                //checkListsNameの名前が変わったタイミングでTableViewDTOを作り、viewDtoに代入する。
                viewDto.add(new TableViewDTO(checkListsName,items,createdAt));
                checkListsName = column.getCheckListsName();
                items = new ArrayList<>();
                itemName = new ArrayList<>();
                isChecked = new ArrayList<>();
                items.add(new TableItemsViewDTO(column.getItemNames(),column.getIsChecked()));
                createdAt = column.getCreatedAt().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            }else{
                //itemsには一つのチェックリストの項目が入る。一つのチェックリストに１つ以上の項目が入る。
                items.add(new TableItemsViewDTO(column.getItemNames(),column.getIsChecked()));
            }
            prev = column.getCheckListsName();
        }
        viewDto.add(new TableViewDTO(checkListsName,items,createdAt));
    }
}
