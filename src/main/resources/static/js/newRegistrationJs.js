//@ts-check
import { worker } from "../test/newRegistrationBrowser.js";
const userNameText =
/**@type {HTMLInputElement} */
(document.querySelector("#userNameText"));
const passwordText =
/**@type {HTMLInputElement} */
(document.querySelector("#passwordText"));
const btn =
/**@type {HTMLButtonElement} */
(document.querySelector("#btn"));
const loginBtn =
/**@type {HTMLButtonElement} */
(document.querySelector("#loginBtn"));
//@type {HTMLInputElement}は値がHTMLInputElementであると宣言している。javaでいうキャストに近い。
if(!(userNameText instanceof HTMLInputElement)){
    throw new TypeError("userNameTextが見つかりません。");
}
if(!(passwordText instanceof HTMLInputElement)){
    throw new TypeError("passwordTextが見つかりません。");
}
if(!(btn instanceof HTMLButtonElement)){
    throw new TypeError("btnが見つかりません。");
}
if(!(loginBtn instanceof HTMLButtonElement)){
    throw new TypeError("loginBtnが見つかりません。");
}
//それぞれの変数がnull出ないことをたしかめている。
function checkInput() {
    try{
        if(userNameText.value.trim() !== "" && passwordText.value.trim() !== ""){
            btn.disabled = false;
        }else{
            btn.disabled = true;
        }
    }catch(error){
        alert("通信エラーが発生しました。");
        console.error(error);
    }
}
/*checkInputはユーザーネーム欄とパスワード欄が入力されていたら、ボタンをおせるようにする。関数である。*/
userNameText.addEventListener("input",checkInput);
passwordText.addEventListener("input",checkInput);
async function registration() {
    try{
        const userName = userNameText.value;
        const password = passwordText.value;
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
        //fetch通信では新規登録成功するか否かを確かめる。
        if(response.ok){
            window.location.href = "/home"
        }else if(response.status === 409){
            const registrationResult = await response.json();
            alert(registrationResult.join("\n"));
        }else{
            const registrationResult = await response.json();
            alert(registrationResult.join("\n"));
        }
    }catch(error){
        alert("通信エラーが発生しました。");
        console.error(error);
    }
}
/*registration関数は入力されたユーザーIDとパスワードの情報を送り、登録成功したなら、
* home画面に遷移、失敗したら、ダイアログボックスで失敗したことを伝える。*/
btn.addEventListener("click",registration);
//fetch通信で入力されたユーザー名とパスワードが登録可能か否かの結果が返ってくる.
//結果がtrueであればhome画面に遷移する。
function loginTransition(){
    window.location.href = "/login"
}
loginBtn.addEventListener("click",loginTransition);
//ログインボタンが押されたら、ログイン画面に遷移する。