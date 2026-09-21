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
    itemName.addEventListener("input",() =>{
        itemFontSize(itemName);
    })
    cardRow.appendChild(itemName);
}
/**
 * itemFontSizeはitemName欄に文字を入力された後文字の大きさを調整する関数である。
 */
/**
 * @param {HTMLDivElement} itemName
 */
function itemFontSize(itemName){
    const width = itemName.clientWidth;
    const length = itemName.textContent.length;
    let fontSize = width/length*1.8;
    fontSize = Math.max(12,Math.min(fontSize,16));
    itemName.style.fontSize = `${fontSize}px`;
}