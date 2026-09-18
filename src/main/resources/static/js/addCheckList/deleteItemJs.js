//@ts-check
import { element,allElement } from "./helperJs.js";
/**deleteItemは項目を削除するためのcloseボタン及び削除を取り消すためのcancelボタンを出現させるための関数である。 */
/**
 * @param { HTMLDivElement } itemColumns
 */
export function deleteItem(itemColumns){
    const cancelBtn = document.createElement("button");
    cancelBtn.classList.add("cancelBtn");
    const cancelIcon = document.createElement("span");
    cancelIcon.classList.add("material-symbols-outlined");
    cancelIcon.textContent = "cancel";
    cancelBtn.appendChild(cancelIcon);
    const addAndDelete = element(itemColumns,".addAndDelete",HTMLDivElement);
    addAndDelete.appendChild(cancelBtn);
    const cardRow = allElement(itemColumns,".cardRow",HTMLDivElement);
    cardRow.forEach(row => {
        const closeBtn = document.createElement("button");
        closeBtn.classList.add("closeBtn");
        const closeIcon = document.createElement("span");
        closeIcon.classList.add("material-symbols-outlined");
        closeIcon.textContent = "close";
        closeBtn.appendChild(closeIcon);
        row.prepend(closeBtn);
        closeBtn.addEventListener("click",()=>{
            deleteRow(row);
        })
    });
}
/**
 * @param {HTMLDivElement} row
 */
function deleteRow(row){
    row.remove();
}