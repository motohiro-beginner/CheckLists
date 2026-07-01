//@ts-check
function tableTransition(){
    window.location.href = "/checkListsTable"
}
//作成したチェックリスト一覧を見れる画面に移動
checkListsTableBtn.addEventListener("click",tableTransition);

async function logOut() {
    await fetch("/logOut", {
        method: "Post",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify({})
    });
    window.location.href = "/Login";
}
//fetch通信をしてセッション情報を破棄して、ログイン画面に移行
logOutBtn.addEventListener("click",logOut);
