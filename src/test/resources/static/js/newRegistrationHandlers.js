import { http, HttpResponse } from "msv";
//http HTTP通信を書くためのオブジェクトをインポートして使えるようにする。
export const handlers = [
    //MSWが使うルール一覧
    http.post("/newRegistration",async({request}) =>{
        //http.postはpost送信がきたら、実行する関数
        //request 送られてきたrequest SpringBootでいうとdtoに近いもの
        const body = await request.json();
        if(body.userName === "abcdefg"){
            
        }
    })
]