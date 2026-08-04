//@ts-check
/**
 * @typedef {Object} OneCheckListViewDTO
 * @property {string} checkListsId,
 * @property {string} checkListsName,
 * @property {OneItemsViewDTO[]} items,
 * @property {string} createdAt
 */
/**
 * @typedef {Object} OneItemsViewDTO
 * @property {string} itemId,
 * @property {string} itemNames,
 * @property {boolean} isChecked
 */
const deleteBtn =
/**@type {HTMLButtonElement} */
(document.querySelector("#deleteBtn"));
const backBtn =
/**@type {HTMLButtonElement} */
(document.querySelector("#backBtn"));
async function oneCheckList(){
    const id = window.location.pathname.split("/").pop();
    const response = await fetch(`showOneCheckList/${id}`, {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify({})
    });
    if(response.ok){
        /**@type {OneCheckListViewDTO} */
        const checkList = await response.json();
    }else if(response.status == 400){
        /**@type {string} */
        const message = await response.json();
        alert(message);
    }else{
        throw new Error("Fetch通信の応答にてエラー発生");
    }
}