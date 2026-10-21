//@ts-check
import { inputCheck } from "./inputCheckJs.js";
import { element,allElement } from "./helperJs.js";
/**
 * @typedef { Object } items
 * @property { string } itemId,
 * @property { string } itemNames,
 * @property { boolean } isChecked
 */
/**
 * @typedef { Object } newItems
 * @property { string } itemNames,
 * @property { boolean } isChecked
 */
/**changeCheckListNameはチェックリスト名が変更されたとき、変更された名前をバックエンド側に送るための関数である。 */
export async function saveCheckList(/**@type {HTMLDivElement} */card){
    const checkListsId = card.dataset.checkListsId;
    const checkListName = element(card,".checkListName",HTMLInputElement).value;
    const year = element(card,".year",HTMLInputElement).value;
    const month = element(card,".month",HTMLInputElement).value;
    const day = element(card,".day",HTMLInputElement).value;
    const cardRow = allElement(card,".cardRow",HTMLDivElement);
    let items = 
    /**@type {items[]} */
    ([]);
    cardRow.forEach(row => {
        const id = /**@type {string}*/(row.dataset.itemId);
        const name = element(row,".itemNames",HTMLInputElement).value;
        const isChecked = element(row,".itemCheckBox",HTMLInputElement).checked;
        items.push({
            itemId: id,
            itemNames: name,
            isChecked: isChecked
        });
    });
    const newCardRow = allElement(card,".newCardRow",HTMLDivElement);
    let newItems =
    /**@type {newItems[]} */
    ([]);
    newCardRow.forEach(row => {
        const name = element(row,".itemNames",HTMLInputElement).value;
        const isChecked = element(row,".itemCheckBox",HTMLInputElement).checked;
        newItems.push({
            itemNames: name,
            isChecked: isChecked
        });
    });
    const input = inputCheck(checkListName,year,month,day,items,newItems);
    if(input.problem){
        alert(input.caution.join("/n"));
        return;
    }
    /**
    console.log(checkListsId);
    console.log(checkListName);
    console.log(year);
    console.log(month);
    console.log(day);
        */
    items.forEach(item => {
        console.log(item.itemId);
        console.log(item.itemNames);
        console.log(item.isChecked);
    })
    newItems.forEach(item => {
        console.log(item.itemNames);
        console.log(item.isChecked);
    })
    const response = await fetch("/saveCheckList",{
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify({
            checkListsId,
            checkListName,
            items,
            newItems,
            year,
            month,
            day
        })
    });
}