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
if(!(container instanceof HTMLDivElement)){
    throw new Error("cardContainerが見つかりません。");
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
 *     <input class="checkListName" value="チェックリスト名"></input>
 *     <input class="createdAt" value="このチェックリストを使用する日付"></input>
 *   </div>
 *   <div class="cardLower">
 *     <div class="cardRow">
 *       <input class="item" value="項目名"></input>
 *     </div>
 *                     ・
 * 　　　　　　　　　　　・
 * 　　　　　　　　　　　・
 *   </div>
 * </div>
 * </div>
 */
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
    cardUpper.appendChild(checkListName);
    const createdAt = document.createElement("input");
    createdAt.type = "text";
    createdAt.value = checkList.createdAt;
    createdAt.classList.add("createdAt");
    cardUpper.appendChild(createdAt);
}
function checkListItems(/**@type {OneItemsViewDTO[]} */items,/**@type {HTMLDivElement} */card){
    const cardLower = document.createElement("div");
    cardLower.classList.add("cardLower");
    card.appendChild(cardLower);
    items.forEach(item => {
        const cardRow = document.createElement("div");
        cardRow.classList.add("cardRow");
        cardRow.dataset.itemId = item.itemId;
    })
}