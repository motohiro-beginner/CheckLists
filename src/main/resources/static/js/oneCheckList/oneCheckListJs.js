//@ts-check
console.log("oneCheckListJs.js開始");
import {element} from "./helperJs.js";
import { displayCheckList } from "./displayCheckListJs.js";
import { oneCheckListFakeFetch } from "../../test/oneCheckListFakeFetchJs.js";
/**
 * @typedef {Object} OneCheckListViewDTO
 * @property {string} checkListsId,
 * @property {string} checkListsName,
 * @property {OneItemsViewDTO[]} items,
 * @property {string} createdAt
 */
/**
 * @typedef {Object} OneItemsViewDTO
 * @property {string} itemId,
 * @property {string} itemNames,
 * @property {boolean} isChecked
 */
const deleteBtn = element(document,"#deleteBtn",HTMLButtonElement);
const backBtn = element(document,"#backBtn",HTMLButtonElement);
async function oneCheckList(){

    /*テストのため一時的にコメントアウトしている。
    const id = window.location.pathname.split("/").pop();
    const response = await fetch(`showOneCheckList/${id}`, {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify({})
    });
    */
   const response = await oneCheckListFakeFetch();
    if(response.ok){
        /**@type {OneCheckListViewDTO} */
        const checkList = await response.json();
        console.log(checkList);
        displayCheckList(checkList);
    }else if(response.status == 400){
        /**@type {string} */
        const message = await response.json();
        alert(message);
    }else{
        throw new Error("Fetch通信の応答にてエラー発生");
    }
}
oneCheckList();