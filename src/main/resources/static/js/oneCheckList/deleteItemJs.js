//@ts-check
import { addItem } from "./addItemJs.js";
import { element,allElement } from "./helperJs.js";
/**deleteItemはクリックされた項目を削除できるようにするメソッドである。
 * 具体的には項目名の左に削除ボタンを追加する。
 */
export function deleteItem(/**@type { HTMLDivElement } */cardLower){
    const cardRow = allElement(cardLower,".cardRow",HTMLDivElement);
    cardRow.forEach(row => {
        const closeBtn = document.createElement("button");
        closeBtn.classList.add("closeBtn");
        const closeIcon = document.createElement("span");
        closeIcon.classList.add("material-symbols-outlined");
        closeIcon.textContent = "close";
        closeBtn.appendChild(closeIcon);
        row.prepend(closeBtn);
        closeBtn.addEventListener("click",() => {
            deleteRow(/**@type { HTMLDivElement }*/(row));
        });
    });
    const newCardRow = allElement(cardLower,".newCardRow",HTMLDivElement);
    newCardRow.forEach(row => {
        const closeBtn = document.createElement("button");
        closeBtn.classList.add("closeBtn");
        const closeIcon = document.createElement("span");
        closeIcon.classList.add("material-symbols-outlined");
        closeIcon.textContent = "close";
        closeBtn.appendChild(closeIcon);
        row.prepend(closeBtn);
        closeBtn.addEventListener("click",() => {
            deleteRow(/**@type { HTMLDivElement }*/(row));
        });
    })
    const lastRow = element(cardLower,".lastRow",HTMLDivElement);
    const cancelBtn = document.createElement("button");
    cancelBtn.classList.add("cancelBtn");
    const cancelIcon = document.createElement("span");
    cancelIcon.classList.add("material-symbols-outlined");
    cancelIcon.textContent = "cancel";
    cancelBtn.appendChild(cancelIcon);
    lastRow.appendChild(cancelBtn);
    cancelBtn.addEventListener("click",() => {
        cancelDelete(cardLower);
    });
    //削除処理中に追加ボタンと削除ボタンを押されたら問題が発生するので削除する。
    const addItemBtn = element(cardLower,".addItemBtn",HTMLButtonElement);
    addItemBtn.remove();
    const deleteItemBtn = element(cardLower,".deleteItemBtn",HTMLButtonElement);
    deleteItemBtn.remove();
}
/**deleteRowはクリックされたcloseアイコンに対応する項目を削除するメソッドである。
 */
function deleteRow(/**@type { HTMLDivElement } */cardRow){
    cardRow.remove();
}
/**cancelDeleteはcancelボタンが押された時にcloseボタンを削除して通常の状態に戻すためのメソッドである。 */
function cancelDelete(/**@type { HTMLDivElement } */cardLower){
    const cardRow = allElement(cardLower,".cardRow",HTMLDivElement);
    cardRow.forEach(row => {
        const closeBtn = element(row,".closeBtn",HTMLButtonElement);
        closeBtn.remove();
    });
    const newCardRow = allElement(cardLower,".newCardRow",HTMLDivElement);
    newCardRow.forEach(row => {
        const closeBtn = element(row,".closeBtn",HTMLButtonElement);
        closeBtn.remove();
    });
    const cancelBtn = element(cardLower,".cancelBtn",HTMLButtonElement);
    cancelBtn.remove();
    //削除していた追加ボタンと削除ボタンを再び表示する。
    const lastRow = element(cardLower,".lastRow",HTMLDivElement);
    const addItemBtn = document.createElement("button");
    addItemBtn.classList.add("addItemBtn");
    const addIcon = document.createElement("span");
    addIcon.classList.add("material-symbols-outlined");
    addIcon.textContent = "add";
    addItemBtn.appendChild(addIcon);
    addItemBtn.addEventListener("click",() => {
        addItem(cardLower);
    });
    lastRow.appendChild(addItemBtn);
    const deleteItemBtn = document.createElement("button");
    deleteItemBtn.classList.add("deleteItemBtn");
    const deleteIcon = document.createElement("span");
    deleteIcon.classList.add("material-symbols-outlined");
    deleteIcon.textContent = "remove";
    deleteItemBtn.appendChild(deleteIcon);
    deleteItemBtn.addEventListener("click",() => {
        deleteItem(cardLower);
    });
    lastRow.appendChild(deleteItemBtn);
}