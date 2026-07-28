package com.example.demo.Service;

import com.example.demo.DTO.TableDTO;
import com.example.demo.DTO.TableSearchDTO;
import com.example.demo.DTO.TableViewDTO;
import com.example.demo.Repository.TableRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SearchService {
    private TableRepository repository;
    public SearchService(TableRepository repository){
        this.repository = repository;
    }
    /*findSearchedCheckListsは、dbから受け取った列が格納されている
     * TableDTOから、チェックリストのデータが格納されているTableViewDTO
     * に、変換するメソッドである。
     * なお、TableSearchDTOにはユーザーが入力したチェックリスト名,年,月,日などの情報が入っている。
     * チェックリスト名は部分一致で検索することも可能
     * それらの値はnullの可能性がある。
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
    public List<TableViewDTO> findSearchedCheckLists(String userName,TableSearchDTO searchDto){
        String keyword = "%" + searchDto.getSearchName() + "%";
        Integer year = Integer.parseInt(searchDto.getYearSearch());
        Integer month = Integer.parseInt(searchDto.getMonthSearch());
        Integer day = Integer.parseInt(searchDto.getDaySearch());
        List<TableDTO> dto = repository.findSearchedCheckLists(userName,keyword,)
    }
}
