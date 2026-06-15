const userNameText = document.getElementById("userNameText");
const passwordText = document.getElementById("passwordText");
const btn = document.getElementById("btn");
async function checkInput() {
    if(userNameText.value.trim() !== "" && passwordText.value.trim() !== ""){
        btn.disabled = false;
    }else{
        btn.disabled = true;
    }
}
/*checkInputはユーザーネーム欄とパスワード欄が入力されていたら、ボタンをおせるようにする。関数である。*/
userNameText.addEventListener("input",checkInput);
passwordText.addEventListener("input",checkInput);
//ユーザーネーム欄とパスワード欄が入力されるたびにcheckInputを呼び出す。
async function registration() {
    const userName = userNameText.value;
    const password = userNameText.value;
    const response = await fetch("/newRegistration", {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify({
            userName,
            password
        })
    });
    const registrationResult = await response.json();
    if(response.ok){
        window.location.href = "/home"
    }else{
        alert(result.join("\n"));
    }
}
/*registration関数は入力されたユーザーIDとパスワードの情報を送り、登録成功したなら、
* home画面に遷移、失敗したら、ダイアログボックスで失敗したことを伝える。*/
btn.addEventListener("click",registration);
//fetch通信で入力されたユーザー名とパスワードが登録可能か否かの結果が返ってくる.
//結果がtrueであれば
//可能であればhome画面に遷移する。