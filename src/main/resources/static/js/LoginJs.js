//@ts-check
const userNameText =
/** @type {HTMLInputElement} */
(document.querySelector("#userNameText"));
const passwordText =
/** @type {HTMLInputElement} */
(document.querySelector("#passwordText"));
const btn =
/** @type {HTMLButtonElement} */
(document.querySelector("#btn"));
//@type {HTMLInputElement}は値がHTMLInputElementであると宣言している。javaでいうキャストに近い。
if(!(userNameText instanceof HTMLInputElement)){
    throw new Error("userNameTextが見つかりません。");
}
if(!(passwordText instanceof HTMLInputElement)){
    throw new Error("userNameTextが見つかりません。");
}
if(!(btn instanceof HTMLButtonElement)){
    throw new Error("userNameTextが見つかりません。");
}
//userNameText,passwordText,btnそれぞれnullでないことを確かめている。
function checkInput() {
    const userName = userNameText.value;
    const password = passwordText.value;
    if(userName.trim() !== "" && password.trim() !== ""){
        btn.disabled = false;
    }else{
        btn.disabled = true;
    }
}
//ユーザー名欄とパスワード欄がそれぞれ入力されていたらボタンを押せるようにする。
userNameText.addEventListener("input",checkInput);
passwordText.addEventListener("input",checkInput);
async function login() {
    const userName = userNameText.value;
    const password = passwordText.value;
    const LoginResult = await fetch("/login", {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify({
            userName,
            password
        })
    });//userNameとpasswordをPost送信で送ってログインできるか否かを確かめる。
    const result = await LoginResult.json();
    if(LoginResult.ok){
        window.location.href = "/home"
    }else{
        alert(result.join("\n"));
    }
}
btn.addEventListener("click",login);