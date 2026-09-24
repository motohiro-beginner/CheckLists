//@ts-check
import { element,allElement } from "./helperJs.js";
import { inputCheck } from "./inputCheckJs.js";
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
    /** テストのため一時的にコメントアウトしている。
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
    */
   //テストのために一時的にokFakeFetchに差し替えている。
    const response = await okFakeFetch();
    const result = await response.json();
    if(result.ok){
        console.log("通信成功");
        //後でinitialization()を作る予定
        //initialization()
    }else{
        alert(result.join("\n"));
    }
}