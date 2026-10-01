//@ts-check
import { element,allElement } from "./helperJs.js";
import { inputCheck } from "./inputCheckJs.js";
import { initialization } from "./InitializationCheckListJs.js";
import { okFakeFetch } from "../test/addCheckListFakeFetchJs.js";
const saveBtn = element(document,".saveBtn",HTMLButtonElement);
saveBtn.addEventListener("click",() => {
    saveCheckList();
});
/**saveCheckListは書き込まれたチェックリストの情報を保存するためのメソッドである。 */
async function saveCheckList(){
    const checkListName = element(document,".checkListName",HTMLInputElement).value;
    const year = element(document,".year",HTMLInputElement).value;
    const month = element(document,".month",HTMLInputElement).value;
    const day = element(document,".day",HTMLInputElement).value;
    const itemNames = allElement(document,".itemName",HTMLInputElement);
    let items =
    /**@type {string[]}*/
    ([]);
    itemNames.forEach(item => {
        items.push(/**@type {string}*/item.value);
    });
    const checkResult = inputCheck(checkListName,year,month,day,items);
    if(checkResult.problem){
        alert(checkResult.caution.join("\n"));
        return;
    }
    const response = await fetch("/addCheckList", {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify({
            checkListName,
            year,
            month,
            day,
            items
        })
    });
    const result = await response.json();
    if(result.ok){
        //テスト用
        console.log("通信成功");
        console.log(checkListName);
        console.log(year);
        console.log(month);
        console.log(day);
        for(const item of items){
            console.log(item);
        }
        initialization()
    }else{
        alert(result.join("\n"));
    }
}