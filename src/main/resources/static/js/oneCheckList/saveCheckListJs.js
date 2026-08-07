//@ts-check
import { inputCheck } from "./inputCheckJs";
import { element } from "./helperJs";
/**
 * @typedef { Object } items
 * @property {string} itemId,
 * @property {string} itemNames,
 * @property {boolean} isChecked
 */
/**changeCheckListNameはチェックリスト名が変更されたとき、変更された名前をバックエンド側に送るための関数である。 */
export async function saveCheckList(/**@type {HTMLDivElement} */card){
    const checkListsId = card.dataset.checkListsId;
    const checkListName = element(card,".checkListName",HTMLButtonElement).value;
    const year = element(card,".year",HTMLInputElement).value;
    const month = element(card,".month",HTMLInputElement).value;
    const day = element(card,".day",HTMLInputElement).value;
    const cardRow = card.querySelectorAll(".cardRow");
    let items = 
    /**@type {items[]} */
    ([]);
    cardRow.forEach(row => {
        //警告をなくすためにチェックをしている。
        if(!(row instanceof HTMLDivElement)){
            throw new Error("rowの型が一致しません。");
        }
        const id = /**@type {string}*/(row.dataset.itemId);
        const name = element(row,".itemNames",HTMLInputElement).value;
        const  isChecked = element(row,".isChecked",HTMLInputElement).checked;
        items.push({
            itemId: id,
            itemNames: name,
            isChecked: isChecked
        });
    });
    const input = inputCheck(checkListName,year,month,day,items);
    if(input.problem){
        alert(input.caution.join("/n"));
        return;
    }
    const response = await fetch("/saveCheckList",{
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify({
            checkListsId,
            checkListName,
            items,
            year,
            month,
            day
        })
    });
}