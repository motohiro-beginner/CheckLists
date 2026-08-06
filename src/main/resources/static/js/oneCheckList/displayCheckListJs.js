//@ts-check
import { saveCheckList } from "./saveCheckListJs";
import { element } from "./helperJs";
/**
 * @typedef {Object} OneCheckListViewDTO
 * @property {string} checkListsId,
 * @property {string} checkListsName,
 * @property {OneItemsViewDTO[]} items,
 * @property {string} createdAt
 */
/**
 * @typedef {Object} OneItemsViewDTO
 * @property {string} itemId,
 * @property {string} itemNames,
 * @property {boolean} isChecked
 */
const container = element(document,".cardContainer",HTMLDivElement);
const keepBtn = element(document,"#keepBtn",HTMLDivElement);
const card = document.createElement("div");
keepBtn.addEventListener("click",() => {
        //後でchangeCheckListName関数を作る予定
        saveCheckList(card);
    });
/**
 * oneCheckListの画面
 * 　　　　　　　　　ーーーーーーーーー
 * 　　　　　　　　　|チェックリスト名|
 *                 ーーーーーーーーーー
 * 　　　　　　　　　|項目　　　　　□|
 * 　　　　　　　　　|項目　　　　　□|
 * 　　　　　　　　　|項目　　　　　□|
 * 　　　　　　　　　ーーーーーーーーー
 * 
 * cardContainerタグの中は下のような構成にする。
 * <div class="cardContainer">
 * <div class="card">
 *   <div class="cardUpper">
 *     <input class="checkListName" value="チェックリスト名"></input>//チェックリスト名の初期値はcheckListの中のcheckListsNameによって決まる。
 *     <div class="createdAt">
 *        <input class="year"></input>
 *        <span class="dataLabel">年</span>
 *        <input class="month"></input>
 *        <span class="dateLabel">月</span>
 *        <input class="day"></input>
 *        <span class="dateLabel">日</span>
 *     </div>//inputタグのyear,month,dayにはそれぞれ初期値が入っている。それらの初期値はcheckListの中のcreatedAtによって決まる。
 *   </div>
 *   <div class="cardLower">
 *     <div class="cardRow">
 *       <input type="text" class="item" value="項目名"></input>//項目名の初期値はcheckListの中のitemNamesによって決まる。
 *       <input type="checkbox" class="isChecked"></input> //チェックが入っているか否かはcheckListの中のisCheckedによって決まる。
 *     </div>
 *                     ・
 * 　　　　　　　　　　　・
 * 　　　　　　　　　　　・
 *   </div>
 * </div>
 * </div>
 * チェックリスト名及び日付及び項目名のところはテキストボックスとなっており文字を入力して変更することができる。
 * チェックボックスをクリックしてチェックがついている状態にすることができる。
 */
/** displayCheckListは画面上に一つのチェックリストを表示するための関数であり、チェックリストの構成を作成する。*/
export async function displayCheckList(/**@type {OneCheckListViewDTO} */checkList){
    card.classList.add("card");
    card.dataset.checkListsId = checkList.checkListsId;
    container.appendChild(card);
    const cardUpper = document.createElement("div");
    cardUpper.classList.add("cardUpper");
    card.appendChild(cardUpper);
    const checkListName = document.createElement("input");
    checkListName.type = "text";
    checkListName.value = checkList.checkListsName;
    checkListName.classList.add("checkListName");
    cardUpper.appendChild(checkListName);
    //年月日の情報を分けて表示する。  チェックリスト内では□年□月□日といった風に表示する。
    const createdAt = document.createElement("div");
    createdAt.classList.add("createdAt");
    const ymd = checkList.createdAt.split("/",3);

    const year = document.createElement("input");
    year.type = "text";
    year.value = ymd[0];
    year.classList.add("year");
    createdAt.appendChild(year);

    const yearLabel = document.createElement("span");
    yearLabel.textContent = "年";
    yearLabel.classList.add("dateLabel");
    createdAt.appendChild(yearLabel);

    const month = document.createElement("input");
    month.type = "text";
    month.value = ymd[1];
    month.classList.add("month");
    createdAt.appendChild(month);

    const monthLabel = document.createElement("span");
    monthLabel.textContent = "月";
    monthLabel.classList.add("dateLabel");
    createdAt.appendChild(monthLabel);

    const day = document.createElement("input");
    day.type = "text";
    day.value = ymd[2];
    day.classList.add("day");
    createdAt.appendChild(day);

    const dayLabel = document.createElement("input");
    dayLabel.textContent = "日";
    dayLabel.classList.add("dateLabel");
    createdAt.appendChild(dayLabel);
    checkListItems(checkList.items,card);
}
/**checkListItemsは項目名を変更できるテキストボックスおよびチェックボックスを作成する変数である。 */
function checkListItems(/**@type {OneItemsViewDTO[]} */items,/**@type {HTMLDivElement} */card){
    const cardLower = document.createElement("div");
    cardLower.classList.add("cardLower");
    card.appendChild(cardLower);
    items.forEach(item => {
        const cardRow = document.createElement("div");
        cardRow.classList.add("cardRow");
        cardRow.dataset.itemId = item.itemId;
        cardLower.appendChild(cardRow);
        const itemName = document.createElement("input");
        itemName.type = "text";
        itemName.value = item.itemNames;
        itemName.classList.add("itemNames");
        cardRow.appendChild(itemName);
        const isChecked = document.createElement("input");
        isChecked.type = "checkbox";
        isChecked.checked = item.isChecked;
        isChecked.classList.add("isChecked");
        cardRow.appendChild(isChecked);
    });
    const cardRow = document.createElement("div");
    cardRow.classList.add("cardRow");
    cardLower.appendChild(cardRow);
    const addItemBtn = document.createElement("button");
    const addIcon = document.createElement("span");
    addIcon.classList.add("material-symbols-outlined");
    addIcon.textContent = "add";
    addItemBtn.appendChild(addIcon);
    addItemBtn.addEventListener("click",() => {
        addItem(cardLower);
    });
    cardRow.appendChild(addItemBtn);
    const deleteItemBtn = document.createElement("button");
    const deleteIcon = document.createElement("span");
    deleteIcon.textContent = "remove";
    deleteItemBtn.appendChild(deleteIcon);
    deleteItemBtn.addEventListener("click",() => {
        deleteItemBtn(cardLower);
    });
    cardRow.appendChild(deleteItemBtn);
    //保存ボタンを押したときに変更した内容を更新するように仕様を変更する。
}