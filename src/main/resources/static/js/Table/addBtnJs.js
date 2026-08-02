//@ts-check
const addBtn =
/**@type { HTMLButtonElement } */
(document.querySelector("#addBtn"));
addBtn.addEventListener("click",addCheckListScreen);
//addCheckListScreenはaddBtnのidを持つボタンが押されたときに/addCheckList画面に遷移する画面である。
function addCheckListScreen(){
    window.location.href = "/addCheckList";
}