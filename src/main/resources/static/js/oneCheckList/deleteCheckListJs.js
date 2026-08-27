//deleteCheckListは削除ボタンを押されたときにチェックリストの情報を削除するために用意した関数である。
export async function deleteCheckList(/**@type {HTMLDivElement} */card){
    const checkListsId = card.dataset.checkListsId;
    const response = await fetch("/deleteCheckList",{
        mothod: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify({
            checkListsId
        })
    });
}