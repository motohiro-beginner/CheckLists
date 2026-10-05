//@ts-check
/**checkItemは項目にチェックが入った場合fetch通信でチェックが入った項目の情報を送る関数である。 */
export async function checkItem(/**@type {HTMLInputElement}*/ItemCheckBox){
    const isChecked = ItemCheckBox.checked;
    const cardRow = /**@type {HTMLDivElement} */(ItemCheckBox.parentElement);
    if(!(cardRow instanceof HTMLDivElement)){
        throw new Error(cardRow);
    }
    const itemId = cardRow.dataset.itemId;
    const response = await fetch("/updateCheck",{
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify({
            isChecked: isChecked,
            itemId: itemId
        })
    });
    if(!response.ok){
        const result = await response.json();
        alert(result);
    }
}