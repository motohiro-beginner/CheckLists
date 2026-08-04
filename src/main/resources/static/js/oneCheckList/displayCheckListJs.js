//@ts-check
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
const container =
/**@type {HTMLDivElement} */
(document.querySelector(".cardContainer"));
const keepBtn =
/**@type {HTMLButtonElement} */
(document.querySelector("#keepBtn"));
if(!(container instanceof HTMLDivElement)){
    throw new Error("cardContainerが見つかりません。");
}
if(!(keepBtn instanceof HTMLButtonElement)){
    throw new Error("keepBtnが見つかりません。");
}
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
 *     <input class="createdAt" value="このチェックリストを使用する日付"></input>//日付の初期値はcheckListの中のcreatedAtによって決まる。
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
    const card = document.createElement("div");
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
    keepBtn.addEventListener("input",() => {
        //後でchangeCheckListName関数を作る予定
        changeCheckListName(checkListName);
    });
    cardUpper.appendChild(checkListName);
    const createdAt = document.createElement("input");
    createdAt.type = "text";
    createdAt.value = checkList.createdAt;
    createdAt.classList.add("createdAt");
    keepBtn.addEventListener("input",() => {
        //後でchangeCreatedAt関数を作る予定
        changeCreatedAt(createdAt);
    });
    cardUpper.appendChild(createdAt);
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
        keepBtn.addEventListener("input",() => {
            //後でchangeItemName関数を作る予定
            changeItemName(itemName);
        });
        itemName.classList.add("itemNames");
        cardRow.appendChild(itemName);
        const isChecked = document.createElement("input");
        isChecked.type = "checkbox";
        isChecked.checked = item.isChecked;
        keepBtn.addEventListener("change",() => {
            //後でchangeChecked関数を作る予定
            changeChecked(isChecked);
        });
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