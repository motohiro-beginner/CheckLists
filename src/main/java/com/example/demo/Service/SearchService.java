package com.example.demo.Service;

import com.example.demo.DTO.TableDTO;
import com.example.demo.DTO.TableItemsViewDTO;
import com.example.demo.DTO.TableSearchDTO;
import com.example.demo.DTO.TableViewDTO;
import com.example.demo.Repository.TableRepository;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
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
        //%という文字列がキーワードに含まれていた場合、%の前に\を挿入する。
        StringBuilder sb = new StringBuilder();
        sb.append(searchDto.getSearchName());
        for(int i=0;i<sb.length();i++){
            if('%' == sb.charAt(i)){
                sb.insert(i,"\\");
            }
        }
        //部分一致検索なのでワイルドカードの%を両端につける。
        String keyword = "%" + searchDto.getSearchName() + "%";
        Integer year = Integer.parseInt(searchDto.getYearSearch());
        Integer month = Integer.parseInt(searchDto.getMonthSearch());
        Integer day = Integer.parseInt(searchDto.getDaySearch());
        List<TableDTO> dto = repository.findSearchedCheckLists(userName,keyword,year,month,day);
        //これ以降はTableServiceのList<TableDTO>からList<TableViewDTO>に変換する部分と同じである。
        List<TableViewDTO> viewDto = new ArrayList<>();
        //checkListsId及びitemIdはJavaScriptに情報を送る関係でString型にする。
        String checkListsId = null;
        String checkListsName = null;
        List<TableItemsViewDTO> items = new ArrayList<>();
        String createdAt = null;
        String prev = null;
        for (TableDTO column : dto) {
            if (prev == null) {
                //最初の繰り返しのときにこの部分を実行する。
                checkListsId = column.getCheckListsId().toString();
                checkListsName = column.getCheckListsName();
                createdAt = column.getCreatedAt().format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
                items.add(new TableItemsViewDTO(column.getItemId().toString(),column.getItemNames(), column.getIsChecked()));
            } else if (!(prev.equals(column.getCheckListsName()))) {
                //checkListsNameの名前が変わったタイミングでTableViewDTOを作り、viewDtoに代入する。
                viewDto.add(new TableViewDTO(checkListsId,checkListsName, items, createdAt));
                checkListsId = column.getCheckListsId().toString();
                checkListsName = column.getCheckListsName();
                items = new ArrayList<>();
                items.add(new TableItemsViewDTO(column.getItemId().toString(),column.getItemNames(), column.getIsChecked()));
                createdAt = column.getCreatedAt().format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
            } else {
                //itemsには一つのチェックリストの項目が入る。一つのチェックリストに１つ以上の項目が入る。
                items.add(new TableItemsViewDTO(column.getItemId().toString(),column.getItemNames(), column.getIsChecked()));
            }
            prev = column.getCheckListsName();
        }
        viewDto.add(new TableViewDTO(checkListsId,checkListsName, items, createdAt));
        return viewDto;
    }
    public boolean existsBySearchedCheckLists(String userName,TableSearchDTO searchDTO){
        StringBuilder sb = new StringBuilder();
        sb.append(searchDTO.getSearchName());
        for(int i=0;i<sb.length();i++){
            if('%' == sb.charAt(i)){
                sb.insert(i,"\\");
            }
        }
        String keyword = "%" + searchDTO.getSearchName() + "%";
        Integer year = Integer.parseInt(searchDTO.getYearSearch());
        Integer month = Integer.parseInt(searchDTO.getMonthSearch());
        Integer day = Integer.parseInt(searchDTO.getDaySearch());
        return repository.existsBySearchedCheckLists(userName,keyword,year,month,day) > 0;
    }
}
