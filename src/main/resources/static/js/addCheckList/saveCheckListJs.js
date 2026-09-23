//@ts-check
import { element,allElement } from "./helperJs.js";
import { inputCheck } from "./inputCheckJs.js";
const saveBtn = element(document,".saveBtn",HTMLButtonElement);
saveBtn.addEventListener("click",() => {

});
/**saveCheckListは書き込まれたチェックリストの情報を保存するためのメソッドである。 */
async function saveCheckList(){
    const checkListName = element(document,".checkListName",HTMLInputElement).value;
    const year = element(document,".year",HTMLInputElement).value;
    const month = element(document,".month",HTMLInputElement).value;
    const day = element(document,".day",HTMLInputElement).value;
    const items = allElement(document,".itemName",HTMLInputElement);
    let itemName =
    /**@type {string[]}*/
    ([]);
    items.forEach(item => {
        itemName.push(/**@type {string}*/item.value);
    });
    const checkResult = inputCheck(checkListName,year,month,day,itemName);
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
            itemName
        })
    });
    */
    const result = await response.json();
    if(result.ok){
        //後でinitialization()を作る予定
        //initialization()
    }else{
        alert(result.join("\n"));
    }
}