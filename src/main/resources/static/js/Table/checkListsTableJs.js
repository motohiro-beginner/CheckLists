//@ts-check
/**
 * @typedef {Object} TableViewDTO
 * @property {string} checkListsName,
 * @property {TableItemsViewDTO[]} items,
 * @property {string} createdAt
 */
/**
 * @typedef {Object} TableItemsViewDTO
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
 *ーーーーーーーー　ーーーーーーーー　ーーーーーーーー
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
function checkListsTable(/**@type {TableViewDTO[]} */checkLists){
    checkLists.forEach(checkList => {
        const card = document.createElement("div");
        card.classList.add("card");
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

    });
}
function checkListItems(/**@type {TableItemsViewDTO[]} */items,/**@type {HTMLDivElement} */card){
    items.forEach(item => {
        const
    })
}