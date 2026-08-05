//@ts-check
/**changeCheckListNameはチェックリスト名が変更されたとき、変更された名前をバックエンド側に送るための関数である。 */
export async function saveCheckList(/**@type {HTMLDivElement} */card){
    const checkListsId = card.dataset.checkListsId;
    const checkListName = card.querySelector(".checkListName");
    const createdAt = card.querySelector(".createdAt");
    const cardRow = card.querySelectorAll(".cardRow");
    let items = 
    /**@type {Object[]} */
    ([]);
    cardRow.forEach(row => {
        //警告をなくすためにチェックをしている。
        if(!(row instanceof HTMLDivElement)){
            throw new Error("rowの型が一致しません。");
        }
        const id = row.dataset.itemId;
        const name = row.querySelector(".itemNames");
        const  checkbox = row.querySelector("isChecked");
        //警告をなくすためにチェックをしている。
        if(!(checkbox instanceof HTMLInputElement)){
            throw new Error("checkboxがnullです。");
        }
        const isChecked = checkbox.checked;
        items.push({
            itemId: id,
            itemNames: name,
            isChecked: isChecked
        });
    });
    const response = await fetch("/saveCheckList",{
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify({
            checkListsId,
            checkListName,
            items,
            createdAt
        })
    });
}