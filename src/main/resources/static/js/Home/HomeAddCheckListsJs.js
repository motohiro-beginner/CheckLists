//@ts-check
import { checkItem } from "./HomeIsCheckedJs.js";
/**
 * @typedef {Object} HomeCheckListsViewDTO
 * @property {string} checkListsName,
 * @property {HomeItemsViewDTO[]} items,
 * @property {string} createdAt
 */
/**
 * @typedef {Object} HomeItemsViewDTO
 * @property {string} itemNames,
 * @property {boolean} isChecked
 */
const container = 
/**@type {HTMLDivElement} */
(document.querySelector(".cardContainer"));
if(!(container instanceof HTMLDivElement)){
    throw new Error("containerが見つかりません。");
}
/**JavaScriptで追加するcardContainerの中のタグ構成
 * <div class="card">
 *   <div class="cardUpper">
 *     <div class="checkListName"></div>
 *     <div class="createdAt"></div>
 *   </div>
 *   <div class="cardLower">
 *     <div class="cardRow">
 *       <div class="item"></div>
 *       <div class="itemCheckBox"></div>
 *     </div>
 *   </div>
 * </div>
 * cardは複数連なる場合がある。
 * cardRowも複数連なる場合がある。
 */
//addCheckListsはチェックリストを画面に表示するための関数である。チェックリストの表の定義を行っている。
//addItemsはcheckListに入る項目を定義している。
export function addCheckLists(/**@type {HomeCheckListsViewDTO[]} */checkLists) {
    checkLists.forEach(checkList => {
        //divタグを生成
        const card = document.createElement("div");
        //クラスを付与
        card.classList.add("card");
        //containerの中に追加
        container.appendChild(card);
        //checkListNameを生成してcardの中に追加
        const cardUpper = document.createElement("div");
        cardUpper.classList.add("cardUpper");
        card.appendChild(cardUpper);
        //cardUpperにはチェックリスト名とチェックリストの日付が入る。
        const checkListName = document.createElement("div");
        checkListName.classList.add("checkListName");
        checkListName.textContent = checkList.checkListsName;
        cardUpper.appendChild(checkListName);
        const createdAt = document.createElement("div");
        createdAt.classList.add("createdAt");
        createdAt.textContent = checkList.createdAt;
        cardUpper.appendChild(createdAt);
        //addItemsはcheckListに入る項目を定義している。
        addItems(checkList.items,card);
    });
}
function addItems(/**@type {HomeItemsViewDTO[]}*/items,/**@type {HTMLDivElement}*/card){
     const cardLower = document.createElement("div");
        //cardLowerには複数の項目及びチェックが入っているか否かが入る。
        cardLower.classList.add("cardLower");
        card.appendChild(cardLower);
            items.forEach(items => {
            const cardRow = document.createElement("div");
            cardRow.classList.add("cardRow");
            //cardRowにはチェックリストの項目とそれに対応するチェックボックスが入る。
            const item = document.createElement("div");
            item.classList.add("item");
            item.textContent = items.itemNames;
            //itemFontSizeにはitemの項目の文字の大きさを調整する関数である。
            cardRow.appendChild(item);
            const isChecked = document.createElement("input");
            isChecked.classList.add("itemCheckBox");
            //チェックされたときに実行するためにisCheckedをイベントリスナー登録する。
            isChecked.addEventListener("change",() => {
                checkItem(isChecked);
            })
            isChecked.type = "checkbox";
            isChecked.checked = items.isChecked;
            cardRow.appendChild(isChecked);
            cardLower.appendChild(cardRow);
            itemFontSize(item);
        });
}
//itemFontSizeはitemの項目の文字の大きさを調整する関数である。
function itemFontSize(/**@type {HTMLDivElement}*/item){
    const width = item.clientWidth;
    const length = item.textContent.length;
    let fontSize = width/length*1.8;
    fontSize = Math.max(12,Math.min(fontSize,16));
    item.style.fontSize = `${fontSize}px`;
}