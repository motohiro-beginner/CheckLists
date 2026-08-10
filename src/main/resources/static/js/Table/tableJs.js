//@ts-check
import { checkListsTable } from "./checkListsTableJs.js";
import { responseNotFound } from "./checkListsTableJs.js";
import {tableFakeFetch, tableFakeFetchNoContent} from "../../test/tableFakeFetchJs.js";
/**
 * @typedef {Object} TableViewDTO
 * @property {string} checkListsId,
 * @property {string} checkListsName,
 * @property {TableItemsViewDTO[]} items,
 * @property {string} createdAt
 */
/**
 * @typedef {Object} TableItemsViewDTO
 * @property {string} itemId,
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
   //console.log("1");
    const response =  await tableFakeFetchNoContent();
   //console.log("2");
    if(response.status === 400){
        //セッション情報が何等かの理由でなかった時に400となる。その時にログイン画面に遷移する。
        const caution = await response.json();
        alert(caution.join("\n"));
        window.location.href ="/Login";
    }else if(response.status === 204){
        responseNotFound();
    }else if(response.ok){
        //console.log("3");
        /**@type {TableViewDTO[]} */
        const checkLists = await response.json();
        /*console.log(checkLists[0].checkListsName);
        console.log(checkLists[0].items[0].itemName);
        console.log(checkLists[0].items[0].isChecked);*/
        checkListsTable(checkLists);
        //console.log("4");
    }else{
        throw new Error("Fetch通信の応答にてエラー発生");
    }
}
table();