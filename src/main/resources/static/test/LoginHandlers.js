//@ts-check
export async function LoginFakeFetch(userName,password){
    //テストのために偽のFetch通信の関数を用意する。
    if(userName === "abcdefg"&&password === "qwerty12"){
        return {
            ok: true,
            status: 200,
            json: async () => [{}]
        };
    }else{
        return {
            ok: false,
            status: 400,
            json: async () => [
                "ユーザー名またはパスワードが違います。"
            ]
        }
    }
}