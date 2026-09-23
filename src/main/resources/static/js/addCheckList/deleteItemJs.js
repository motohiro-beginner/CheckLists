//@ts-check
import { addItem } from "./addItemJs.js";
import { element,allElement } from "./helperJs.js";
/**deleteItemは項目を削除するためのcloseボタン及び削除を取り消すためのcancelボタンを出現させるための関数である。 */
/** 
 * 下のコメントは削除画面のタグ構成である。
*   <div class="itemColumns">
 *     <div class="cardRow">
 *        <input type="button" class="closeBtn"><span class="material-symbols-outlined">close</span>
 *        <input class="itemName"type="text"></input>
 *     </div>
 *     <div class="cardRow">
 *        <input type="button" class="closeBtn"><span class="material-symbols-outlined">close</span>
 *        <input class="itemName"type="text"></input>
 *     </div>
 *                //closeBtnを押すとそのcloseBtnを持っているcardRowが丸ごと削除される。
 *     <div class="addAndDelete">
 *        <input type="button" class="cancelBtn"><span class="material-symbols-outlined">cancel</span></input>
 *                //cancelBtnを押すと通常画面に戻る。
 *     </div>
 *   </div>
 */
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
    //削除画面のときにaddBtnやdeleteBtnが押されると都合が悪いためaddBtnとdeleteBtnを削除する。
    const addBtn = element(itemColumns,".addBtn",HTMLButtonElement);
    addBtn.remove();
    const deleteBtn = element(itemColumns,".deleteBtn",HTMLButtonElement);
    deleteBtn.remove();
}
//closeボタンが押されたときに該当する項目を削除する関数
/**
 * @param {HTMLDivElement} row
 */
function deleteRow(row){
    row.remove();
}
//cancelボタンが押されたときに削除ボタンを取り消して通常画面に戻す関数
/**
 * @param {HTMLDivElement} itemColumns 
 */
function deleteCancel(itemColumns){
    const sumCloseBtn = allElement(itemColumns,".closeBtn",HTMLButtonElement);
    sumCloseBtn.forEach(closeBtn=>{
        closeBtn.remove();
    });
    const cancelBtn = element(itemColumns,".cancelBtn",HTMLButtonElement);
    cancelBtn.remove();
    //削除していたaddBtnとdeleteBtnを再び表示する。
    const addAndDelete = element(itemColumns,".addAndDelete",HTMLDivElement);
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