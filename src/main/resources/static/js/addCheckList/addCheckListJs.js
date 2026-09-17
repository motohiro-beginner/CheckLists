//@ts-check
import { element } from "./helperJs.js";
/**createAddCheckListはnewCheckListContainerをもとにitemColumnタグを追加して
 * チェックリスト新規作成画面を作る関数である。
 * nameColumnタグとcreatedAtColumnタグはhtmlで定義されている。
 * newCheckListContainerの中身
 * <div class="newCheckListContainer">
 *   <div class="nameColumn">
 *     <div class="checkListLabel"></div>
 *     <input class="checkListName"></input>
 *   </div>
 *   <div class="createdAtColumn">
 *     <div class="createdAtLabel"></div>
 *     <input class=""createdAt" type="text"></input>
 *   </div>
 *   <div class="itemColumns">
 *     <div class="addAndDelete">
 *       <button class="addBtn"><span class="material-symbols-outlined">add</span></button>
 *       <button class="deleteBtn"><span class="material-symbols-outlined">delete</span></button>
 *     </div>
 *   </div>
 * </div>
*/
function createCheckList(){
    const itemColumns = element(document,".itemColumns",HTMLDivElement);
    const addAndDelete = document.createElement("div");
    addAndDelete.classList.add("addAndDelete");
    itemColumns.appendChild(addAndDelete);
    const addBtn = document.createElement("button");
    addBtn.classList.add("addBtn");
    addBtn.addEventListener("click",() => {
        //後でaddItemを作成する予定
        //addItem(itemColumns);
    });
    addAndDelete.appendChild(addBtn);
    const addIcon = document.createElement("span");
    addIcon.classList.add("material-symbols-outlined");
    addBtn.appendChild(addIcon);
    const deleteBtn = document.createElement("button");
    deleteBtn.classList.add("deleteBtn");
    deleteBtn.addEventListener("click",() => {
        //後でdeleteItemを作成する予定
        //deleteItem();
    })
    addAndDelete.appendChild(deleteBtn);
    const deleteIcon = document.createElement("span");
    deleteIcon.classList.add("material-symbols-outlined");
    deleteBtn.appendChild(deleteIcon);
}
createCheckList();