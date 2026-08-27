//@ts-check
//addItemは+ボタンを押されたときに新しい項目を追加するメソッドである。
export async function addItem(/**@type {HTMLDivElement} */cardLower){
    /*チェックリストの最後の項目は　+ -　(項目を追加する記号と項目を削除する記号)となっている。
    その項目の一つ前に新しい項目を追加する。*/
    const newCardRow = document.createElement("div");
    newCardRow.classList.add("newCardRow");
    const lastRow = cardLower.lastElementChild;
    cardLower.insertBefore(newCardRow,lastRow);
    const itemName = document.createElement("input");
    itemName.type = "text";
    itemName.classList.add("itemNames");
    newCardRow.appendChild(itemName);
    const isChecked = document.createElement("input");
    isChecked.type = "checkbox";
    isChecked.checked = false;
    isChecked.classList.add("itemCheckBox");
    newCardRow.appendChild(isChecked);
}