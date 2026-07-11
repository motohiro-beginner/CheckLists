//@ts-check
import { http, HttpResponse } from "msw";
console.log("handlerが読み込まれました");
//http HTTP通信を書くためのオブジェクトをインポートして使えるようにする。
export const newRegistrationHandlers = [
    //MSWが使うルール一覧
    http.post("/newRegistration",async({request}) =>{
        console.log("MSWがPOSTを受け取りました");
        //http.postはpost送信がきたら、実行する関数
        //request 送られてきたオブジェクト SpringBootでいうとdtoに近いもの
        const body = await request.json();
        if(body.userName === "abcdefg"&&body.password === "qwerty12"){
            return HttpResponse.json({})
        }else{
            return HttpResponse.json(
                {
                message: "ユーザー名は既に他の人に使われています。"
            },
            {
                status: 400
            }
            //HttpResponseでテスト用の偽の戻り値を返す。
        )
        }
    })
]