const btn = document.getElementById("btn");
async function registration() {
    const userName = document.getElementById("userNameText").value;
    const password = document.getElementById("passwordText").value;
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
    if(registrationResult){
        window.location.href = "/home"
    }else{
        alert()
    }
}
btn.addEventListener("click",registration);
//fetch通信で入力されたユーザー名とパスワードが登録可能か否かの結果が返ってくる.
//結果がtrueであれば
//可能であればhome画面に遷移する。