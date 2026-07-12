//@ts-check
//テストするために偽のFetch通信の関数を用意する。
export async function newRegistrationFakeFetch(userName,password){
    //MSWが使うルール一覧
    if(userName === "abcdefg"&&password === "qwerty12"){
        return {
            ok: true,
            status: 200,
            json: async () => [{}]
        };
        //なるべく元のfetch通信に寄せるためにオブジェクト形式で返す。
        //okはresponse.ok,statusはresponse.status,である。
        // jsonはresponse.jsonが実行されたときのために用意した関数である。
    }else{
        return {
            ok: false,
            status: 400,
            json:async () => [
                "ユーザー名は既に他の人に使われています。"
            ]
            //badRequest用
        }
    }
}