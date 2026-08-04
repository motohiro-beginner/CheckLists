//@ts-check
/**changeCheckListNameはチェックリスト名が変更されたとき、変更された名前をバックエンド側に送るための関数である。 */
export async function changeCheckListName(/**@type {HTMLInputElement} */checkListNameText){
    const userName = checkListNameText.value;
    const response = await fetch("/changeCheckListName",{
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify({
            
        })
    });
}