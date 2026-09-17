//@ts-check
/**addItemはitemColumnsに項目名を入力する欄を追加する関数である。 */
/**
 * @param { HTMLDivElement } itemColumns
 */
export function addItem(itemColumns){
    const cardRow = document.createElement("div");
    cardRow.classList.add("cardRow");
    itemColumns.prepend(cardRow);
    const itemName = document.createElement("input");
    itemName.type = "text";
    itemName.classList.add("itemName");
    cardRow.appendChild(itemName);
}