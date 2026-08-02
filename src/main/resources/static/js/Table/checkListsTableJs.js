//@ts-check
//下のインポート文はテストのために一時的にコメントアウトしている。
//import { checkItem } from "../Home/HomeIsCheckedJs.js";
//console.log("checkListsTableJs loaded");
/**
 * @typedef {Object} TableViewDTO
 * @property {string} checkListsId,
 * @property {string} checkListsName,
 * @property {TableItemsViewDTO[]} items,
 * @property {string} createdAt
 */
/**
 * @typedef {Object} TableItemsViewDTO
 * @property {string} itemId,
 * @property {string} itemName,
 * @property {boolean} isChecked
 */
//@ts-checkの警告がcontainerでないように@type {HTMLDivElement}を書いている。
const container =
/**@type {HTMLDivElement} */
(document.querySelector(".cardContainer"));
//containerがundefinedでないかどうかを調べる。
if(!(container instanceof HTMLDivElement)){
    throw new Error("containerが見つかりません。");
}
/**
 * ーーーーーーーー　ーーーーーーーー　ーーーーーーーー
 * チェックリスト名　チェックリスト名　チェックリスト名
 * ーーーーーーーー　ーーーーーーーー　ーーーーーーーー
 * 項目　　　　　□　 項目　　　　　□　項目　　　　　 □
 * 項目　　　　　□　 項目　　　　　□　項目　　　　　 □
 * 項目　　　　　□　 項目　　　　　□　項目　　　　　 □
 * ーーーーーーーー　ーーーーーーーー　ーーーーーーーー
 * ーーーーーーーー　ーーーーーーーー　ーーーーーーーー
 * チェックリスト名　チェックリスト名　チェックリスト名
 * ーーーーーーーー　ーーーーーーーー　ーーーーーーーー
 * 項目　　　　　□　 項目　　　　　□　項目　　　　　 □
 * 項目　　　　　□　 項目　　　　　□　項目　　　　　 □
 * 項目　　　　　□　 項目　　　　　□　項目　　　　　 □
 * ーーーーーーーー　ーーーーーーーー　ーーーーーーーー
 * 　　　　　　　　　　　　・
 * 　　　　　　　　　　　　・
 * 　　　　　　　　　　　　・
 * table.htmlの画面はこのようにする。
 * cardタグの構成は下のようにする。
 <div class="card">
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
 */
/**checkListsTableは画面に複数のチェックリストを表示するための構造を作成する変数である。 */
export function checkListsTable(/**@type {TableViewDTO[]} */checkLists){
    //console.log("5");
    //チェックリストの個数分構造を定義する。
    checkLists.forEach(checkList => {
        const card = document.createElement("div");
        card.classList.add("card");
        card.dataset.checkListsId = checkList.checkListsId;
        container.appendChild(card);
        const cardUpper = document.createElement("div");
        cardUpper.classList.add("cardUpper");
        card.appendChild(cardUpper);
        const checkListName = document.createElement("div");
        checkListName.classList.add("checkListName");
        checkListName.textContent = checkList.checkListsName;
        cardUpper.appendChild(checkListName);
        const createdAt = document.createElement("div");
        createdAt.classList.add("createdAt");
        createdAt.textContent = checkList.createdAt;
        cardUpper.appendChild(createdAt);
        checkListItems(checkList.items,card);
        //console.log("6");
    });
}
/**checkListItemsはcheckListsTableのチェックリストに項目に関する要素を追加する関数である。 */
function checkListItems(/**@type {TableItemsViewDTO[]} */items,/**@type {HTMLDivElement} */card){
    //console.log("7");
    const cardLower = document.createElement("div");
    cardLower.classList.add("cardLower");
    card.appendChild(cardLower);
    //チェックリストに項目及びその項目に対するチェックボックスを追加する。
    items.forEach(item => {
        const cardRow = document.createElement("div");
        cardRow.classList.add("cardRow");
        cardRow.dataset.itemId = item.itemId;
        cardLower.appendChild(cardRow);
        const itemName = document.createElement("div");
        itemName.classList.add("itemName");
        itemName.textContent = item.itemName;
        //console.log("8");
        itemFontSize(itemName);
        cardRow.appendChild(itemName);
        const isChecked = document.createElement("input");
        isChecked.type = "checkbox";
        isChecked.checked = item.isChecked;
        isChecked.classList.add("isChecked");
        /*テストのため一時的にコメントアウト
        isChecked.addEventListener("change",() => {
            checkItem(isChecked);
        })

         */
        cardRow.appendChild(isChecked);
        //console.log("11");
    });
}
//itemFontSizeはitemNameの項目の文字の大きさを文字数に応じて調整する関数である。
function itemFontSize(/**@type {HTMLDivElement} */itemName){
    //console.log("9");
    const width = itemName.clientWidth;
    const length = itemName.textContent.length;
    let fontSize = width/length*1.8;
    fontSize = Math.max(12,Math.min(fontSize,16));
    itemName.style.fontSize = `${fontSize}px`;
    //console.log("10");
}
export function responseNotFound(){
    const notFoundText = document.createElement("div");
    notFoundText.classList.add("notFoundText");
    notFoundText.textContent = "チェックリストはまだ作成されていません。";
    container.appendChild(notFoundText);
}
export function searchNotFound(){
    const notFoundText = document.createElement("div");
    notFoundText.classList.add("notFoundText");
    notFoundText.textContent = "検索条件に一致するチェックリストはありませんでした。";
    container.appendChild(notFoundText);
}