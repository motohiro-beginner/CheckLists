//@ts-check
import { addItem } from "./addItemJs.js";
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
    cancelBtn.addEventListener("click",()=>{
        deleteCancel(itemColumns);
    });
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
    const addBtn = element(itemColumns,".addBtn",HTMLButtonElement);
    addBtn.remove();
    const deleteBtn = element(itemColumns,".deleteBtn",HTMLButtonElement);
    deleteBtn.remove();
}
/**
 * @param {HTMLDivElement} row
 */
function deleteRow(row){
    row.remove();
}
/**
 * @param {HTMLDivElement} itemColumns 
 */
function deleteCancel(itemColumns){
    const sumCloseBtn = allElement(itemColumns,".closeBtn",HTMLDivElement);
    sumCloseBtn.forEach(closeBtn=>{
        closeBtn.remove();
    });
    const cancelBtn = element(itemColumns,".cancelBtn",HTMLButtonElement);
    cancelBtn.remove();
    const addAndDelete = element(itemColumns,".addAndDelete",HTMLDivElement);
    addAndDelete.classList.add("addAndDelete");
    itemColumns.appendChild(addAndDelete);
    const addBtn = document.createElement("button");
    addBtn.classList.add("addBtn");
    addBtn.addEventListener("click",() => {
        addItem(itemColumns);
    });
    addAndDelete.appendChild(addBtn);
    const addIcon = document.createElement("span");
    addIcon.textContent = "add";
    addIcon.classList.add("material-symbols-outlined");
    addBtn.appendChild(addIcon);
    const deleteBtn = document.createElement("button");
    deleteBtn.classList.add("deleteBtn");
    deleteBtn.addEventListener("click",() => {
        deleteItem(itemColumns);
    })
    addAndDelete.appendChild(deleteBtn);
    const deleteIcon = document.createElement("span");
    deleteIcon.textContent = "remove";
    deleteIcon.classList.add("material-symbols-outlined");
    deleteBtn.appendChild(deleteIcon);
}