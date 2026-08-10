//@ts-check
const checkListsTableBtn =
/**@type {HTMLButtonElement} */
(document.querySelector("#checkListsTableBtn"));
const logOutBtn =
/**@type {HTMLButtonElement} */
(document.querySelector("#logOutBtn"));
if(!(checkListsTableBtn instanceof HTMLButtonElement)){
    throw new Error("checkListsTableBtnが見つかりません");
}
if(!(logOutBtn instanceof HTMLButtonElement)){
    throw new Error("logOutBtnが見つかりません");
}
//作成したチェックリスト一覧を見れる画面に移動
async function tableTransition(){
    await fetch("tableTransition");
}
checkListsTableBtn.addEventListener("click",tableTransition);

async function logOut() {
    await fetch("/logOut", {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify({})
    });
}
//fetch通信をしてセッション情報を破棄して、ログイン画面に移行
logOutBtn.addEventListener("click",logOut);
