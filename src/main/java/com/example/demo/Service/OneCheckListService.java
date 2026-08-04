package com.example.demo.Service;

import com.example.demo.DTO.OneCheckListDTO;
import com.example.demo.DTO.OneCheckListViewDTO;
import com.example.demo.DTO.OneItemsViewDTO;
import com.example.demo.Repository.OneCheckListRepository;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class OneCheckListService {
    private OneCheckListRepository repository;
    public OneCheckListService(OneCheckListRepository repository){
        this.repository =repository;
    }
    /*findOneCheckListは該当するcheckListIdに一致するチェックリストの情報を渡すメソッドである。
    * 渡すチェックリストは１つである。
    * 例
    * List<OneCheckListDTO>
    * checkListsName "買い物" itemName "リンゴ" isChecked true createdAt 2026/1/1
    * checkListsName "買い物" itemName "みかん" isChecked true createdAt 2026/1/1
    * checkListsName "買い物" itemName "イチゴ" isChecked false createdAt 2026/1/1
    *                                ↓
    * OneCheckListViewDTO
    * checkListsName "買い物" items itemName "リンゴ" createdAt "2026/1/1"
    *                              isChecked true
    *                              itemName "みかん"
    *                              isChecked true
    *                              itemName "イチゴ"
    *                              isChecked false
    * */
    public OneCheckListViewDTO findOneCheckList(Integer checkListId){
        List<OneCheckListDTO> dto = repository.findOneCheckList(checkListId);
        //dto.get(0)はよく使うので、変数に代入している。
        OneCheckListDTO one = dto.get(0);
        List<OneItemsViewDTO> items = new ArrayList<>();
        //コードを見やすくするために変数を使用している。
        String itemId;
        String itemName;
        boolean isChecked;
        String createdAt = one.getCreatedAt().format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
        for(OneCheckListDTO column:dto){
            itemId = column.getItemId().toString();
            itemName = column.getItemName();
            isChecked = column.getIsChecked();
            items.add(new OneItemsViewDTO(itemId,itemName,isChecked));
        }
        OneCheckListViewDTO viewDto = new OneCheckListViewDTO(one.getCheckListId().toString(),one.getCheckListName(),items,createdAt);
        return viewDto;
    }
}
