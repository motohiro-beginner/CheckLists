//@ts-check
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
    throw new Error("containerが見つかりません");
}
async function HomeCheckLists(){
    const response = await fetch("/home", {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify({})
    });
    /**@type {HomeCheckListsViewDTO[]} */
    const checkLists = await response.json();
    /**checkListsオブジェクトに含まれるデータの例
     * checkLists = [
     * {
     *  checkListsName: "買い物",
     *  items: [
     *   {
     *    itemNames: "リンゴ",
     *    isChecked: true
     *   },
     *   {
     *    itemNames: "みかん",
     *    isChecked: true
     *   },
     *   {
     *    itemNames: "イチゴ",
     *    isChecked: false
     *   }
     *   ],
     *  createdAt: "2025/6/20"
     * }
     * {
     *  checkListsName: "勉強",
     *  items: [
     *   {
     *    itemNames: "国語",
     *    isChecked: true
     *   },
     *   {
     *    itemNames: "数学",
     *    isChecked: true
     *   },
     *   {
     *    itemNames: "英語",
     *    isChecked: false
     *   }
     *   ],
     *  createdAt: "2025/6/20"
     * }
     * ]
     */
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
        const cardLower = document.createElement("div");
        //cardLowerには複数の項目及びチェックが入っているか否かが入る。
        cardLower.classList.add("cardLower");
        const cardRow = document.createElement("div");
        cardRow.classList.add("cardRow");
        //cardRowにはチェックリストの項目とそれに対応するチェックボックスが入る。
        checkList.items.forEach(items => {
            const item = document.createElement("div");
            item.classList.add("item");
            item.textContent = items.itemNames;
            cardRow.appendChild(item);
            const isChecked = document.createElement("input");
            isChecked.classList.add("itemCheckBox");
            isChecked.type = "checkbox";
            isChecked.checked = items.isChecked;
        });
    });
}