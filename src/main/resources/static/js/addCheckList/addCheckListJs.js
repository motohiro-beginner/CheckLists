//@ts-check
import { addItem,itemFontSize } from "./addItemJs.js";
import { deleteItem } from "./deleteItemJs.js";
import { element } from "./helperJs.js";
/**createAddCheckListはitemColumnタグをもとに複数のタグを追加して
 * チェックリスト新規作成画面を作る関数である。
 * iteColumns以外の部分ははhtmlで定義されている。
 * 下のコメントはcreateAddCheckListで作られるタグの構成である。
 *   <div class="itemColumns">
 *     <div class="cardRow">
 *        <input class="itemName"type="text"></input>
 *     </div>
 *                //cardRowはユーザーによって追加,削除される。
 *     <div class="addAndDelete">
 *       <button class="addBtn"><span class="material-symbols-outlined">add</span></button>
 *       <button class="deleteBtn"><span class="material-symbols-outlined">delete</span></button>
 *                //addBtnとdeleteBtnのボタンをそれぞれ押すと追加,削除が行われる。
 *     </div>
 *   </div>
*/
function createCheckList(){
    const itemColumns = element(document,".itemColumns",HTMLDivElement);
    //最初の画面では一つだけ項目の入力欄を表示する。
    const cardRow = document.createElement("div");
    cardRow.classList.add("cardRow");
    const itemName = document.createElement("input");
    itemName.classList.add("itemName");
    itemName.type = "text";
    itemName.addEventListener("input",() =>{
        itemFontSize(itemName);
    });
    cardRow.appendChild(itemName);
    itemColumns.appendChild(cardRow);
    const addAndDelete = document.createElement("div");
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
createCheckList();
element(document,"#backBtn",HTMLButtonElement).addEventListener("click",()=>{
    tableTransition();
});
async function tableTransition(){
    await fetch("/tableTransition",{
        method: "GET"
    });
}