//@ts-check
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
}
/**deleteRowはクリックされたcloseアイコンに対応する項目を削除するメソッドである。
 */
function deleteRow(/**@type { HTMLDivElement } */cardRow){
    cardRow.remove();
}
/**cancelDeleteはcancelBtnが押されたときにcloseボタンとcancelボタンを削除するメソッドである。 */
function cancelDelete(/**@type { HTMLDivElement } */cardLower){
    const cardRow = allElement(cardLower,".cardRow",HTMLDivElement);
    cardRow.forEach(row => {
        const closeBtn = element(row,".closeBtn",HTMLButtonElement);
        closeBtn.remove();
    });
    const cancelBtn = element(cardLower,".cancelBtn",HTMLButtonElement);
    cancelBtn.remove();
}