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
    /*findTableAllCheckListsはdbから受け取った列が格納されている
    * TableDTOからチェックリストのデータが格納されているTableViewDTO
    * に変換するメソッドである。
    * 例
    * List<TableDTO>
    * checkListsName "買い物" itemName "リンゴ" isChecked true createdAt 2026/1/1
    * checkListsName "買い物" itemName "みかん" isChecked true createdAt 2026/1/1
    * checkListsName "買い物" itemName "イチゴ" isChecked false createdAt 2026/1/1
    * checkListsName "勉強" itemName "数学" isChecked true createdAt 2026/1/2
     * checkListsName "勉強" itemName "国語" isChecked false createdAt 2026/1/2
     * checkListsName "勉強" itemName "英語" isChecked false createdAt 2026/1/2
     *                                ↓
     * List<TableViewDTO>
     * checkListsName "買い物" items itemName "リンゴ" createdAt "2026/1/1"
     *                              isChecked true
     *                              itemName "みかん"
     *                              isChecked true
     *                              itemName "イチゴ"
     *                              isChecked false
     *
     * checkListsName "勉強" items   itemName "数学"   createdAt "2026/1/2"
     *                              isChecked true
     *                              itemName "国語"
     *                              isChecked false
     *                              itemName "英語"
     *                              isChecked false
    * */
    public List<TableViewDTO> findTableAllCheckLists(String userName) {

        List<TableDTO> dto = repository.findTableAllCheckLists(userName);
        List<TableViewDTO> viewDto = new ArrayList<>();
        Integer checkListsId = null;
        String checkListsName = null;
        List<TableItemsViewDTO> items = new ArrayList<>();
        String createdAt = null;
        String prev = null;
        for (TableDTO column : dto) {
            if (prev == null) {
                //最初の繰り返しのときにこの部分を実行する。
                checkListsId = column.getCheckListsId();
                checkListsName = column.getCheckListsName();
                createdAt = column.getCreatedAt().format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
                items.add(new TableItemsViewDTO(column.getItemId(),column.getItemNames(), column.getIsChecked()));
            } else if (!(prev.equals(column.getCheckListsName()))) {
                //checkListsNameの名前が変わったタイミングでTableViewDTOを作り、viewDtoに代入する。
                viewDto.add(new TableViewDTO(checkListsId,checkListsName, items, createdAt));
                checkListsId = column.getCheckListsId();
                checkListsName = column.getCheckListsName();
                items = new ArrayList<>();
                items.add(new TableItemsViewDTO(column.getItemId(),column.getItemNames(), column.getIsChecked()));
                createdAt = column.getCreatedAt().format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
            } else {
                //itemsには一つのチェックリストの項目が入る。一つのチェックリストに１つ以上の項目が入る。
                items.add(new TableItemsViewDTO(column.getItemId(),column.getItemNames(), column.getIsChecked()));
            }
                prev = column.getCheckListsName();
        }
        viewDto.add(new TableViewDTO(checkListsId,checkListsName, items, createdAt));
        return viewDto;
    }
    /*existsByUserCheckListsは該当するユーザーのチェックリストがあるかないかを返すメソッドである。*/
    public boolean existsByUserCheckLists(String userName){
        return repository.existsByUserCheckLists(userName) > 0;
    }
}
