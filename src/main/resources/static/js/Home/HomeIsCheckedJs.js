//@ts-check
/**checkItemは項目にチェックが入った場合fetch通信でチェックが入った項目の情報を送る関数である。 */
export async function checkItem(/**@type {HTMLInputElement}*/ItemCheckBox){
    const isChecked = ItemCheckBox.checked;
    const cardRow = itemCheckBox.parentElement;
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
        alert("ユーザーが項目につけたチェックの情報の保存に失敗しました。");
    }
}