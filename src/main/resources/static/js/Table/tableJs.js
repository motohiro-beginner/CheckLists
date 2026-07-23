//@ts-check
import { checkListsTable } from "./checkListsTableJs.js";
import { tableFakeFetch } from "../../test/tableFakeFetchJs.js";
/**
 * @typedef {Object} TableViewDTO
 * @property {string} checkListsName,
 * @property {TableItemsViewDTO[]} items,
 * @property {string} createdAt
 */
/**
 * @typedef {Object} TableItemsViewDTO
 * @property {string} itemName,
 * @property {boolean} isChecked
 */
async function table(){
    /*テストのため一時的にコメントアウトしている。
    const response = await fetch("/table",{
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body:JSON.stringify({})
    });
    */
   console.log("1");
    const response =  await tableFakeFetch();
    console.log("2");
    if(response.status === 400){
        //セッション情報が何等かの理由でなかった時に400となる。その時にログイン画面に遷移する。
        const checkLists = await response.json();
        alert(checkLists.join("\n"));
        window.location.href ="/Login";
    }else if(response.status === 404){
        const checkLists = await response.json();
        //responseNotFoundという関数を作成する予定
        alert(checkLists.join("\n"));
        table();
    }else{
        console.log("3");
        /**@type {TableViewDTO[]} */
        const checkLists = await response.json(); 
        checkListsTable(checkLists);
        console.log("4");
    }
}
table();