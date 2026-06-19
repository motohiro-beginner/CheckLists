//@ts-check
const userNameText =
/** @type {HTMLInputElement} */
(document.querySelector("#userNameText"));
const passwordText =
/** @type {HTMLInputElement} */
(document.querySelector("#passwordText"));
const btn =
/** @type {HTMLInputElement} */
(document.getElementById("btn"));
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
    if(userName.trim()===""&&password.trim()===""){
        btn.disabled = true;
    }else{
        btn.disabled = false;
    }
}
btn.addEventListener("input",checkInput);
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
login();