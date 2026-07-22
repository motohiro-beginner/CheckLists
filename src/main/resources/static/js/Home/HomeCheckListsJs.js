//@ts-check
import { addCheckLists } from "./HomeAddCheckListsJs.js";
/**
 * @typedef {Object} HomeCheckListsViewDTO
 * @property {string} checkListsName,
 * @property {HomeItemsViewDTO[]} items,
 * @property {string} createdAt
 */
/**
 * @typedef {Object} HomeItemsViewDTO
 * @property {string} itemNames,
 * @property {boolean} isChecked
 */
const container = 
/**@type {HTMLDivElement} */
(document.querySelector(".cardContainer"));
if(!(container instanceof HTMLDivElement)){
    throw new Error("containerが見つかりません。");
}
async function HomeCheckLists(){
    const response = await fetch("/home", {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify({})
    });
    let checkLists = null;
    if(response.status === 400){
        /**@type {string[]}*/
        checkLists = await response.json();
        alert(checkLists.join("\n"));
        window.location.href ="/Login"
        //セッション情報がなかった場合,badRequestが送られてくる。
        // もしセッション情報がなかった場合、Login画面に遷移する。
    }else if(response.status === 404){
        responseNotFound();
    }else{
        /**@type {HomeCheckListsViewDTO[]} */
        checkLists = await response.json();
        addCheckLists(checkLists);
    }
    /**checkListsオブジェクトに含まれるデータの例
     * const checkLists = [
     * {
     *  checkListsName: "買い物",
     *  items: [
     *   {
     *    itemNames: "リンゴ",
     *    isChecked: true
     *   },
     *   {
     *    itemNames: "みかん",
     *    isChecked: true
     *   },
     *   {
     *    itemNames: "イチゴ",
     *    isChecked: false
     *   }
     *   ],
     *  createdAt: "2025/6/20"
     * }
     * {
     *  checkListsName: "勉強",
     *  items: [
     *   {
     *    itemNames: "国語",
     *    isChecked: true
     *   },
     *   {
     *    itemNames: "数学",
     *    isChecked: true
     *   },
     *   {
     *    itemNames: "英語",
     *    isChecked: false
     *   }
     *   ],
     *  createdAt: "2025/6/20"
     * }
     * ]
     */
}
//responseNotFoundはチェックリストがなかったときに画面に
// "今日のチェックリストは作成されていません。"
//と表示するための関数である。
function responseNotFound(){
    const notFoundText = document.createElement("div");
    notFoundText.classList.add("notFoundText");
    notFoundText.textContent = "今日のチェックリストは作成されていません。"
    container.appendChild(notFoundText);
}
/*
function cardContainerTest(){
    const checkLists = [
     {
       checkListsName: "買い物",
       items: [
        {
         itemNames: "リンゴ",
         isChecked: true
        },
        {
         itemNames: "みかん",
         isChecked: true
        },
        {
         itemNames: "イチゴ",
         isChecked: false
        }
        ],
       createdAt: "2025/6/20"
      },
      {
      checkListsName: "勉強",
       items: [
        {
         itemNames: "abcdefghijklmnopqrstuvwxyz,abcdefghijklmnopqrstuvwxyz,abcdefghijklmnopqrstuvwxyzaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa",
         isChecked: true
        },
        {
         itemNames: "数学",
         isChecked: true
        },
        {
        itemNames: "英語",
         isChecked: false
        }
        ],
       createdAt: "2025/6/20"
     }
     ]
     //上に書いたcheckListsオブジェクトはテスト用に書いたデータである、後で消す。
    addCheckLists(checkLists);
}
cardContainerTest();
*/
//cardContainerTestはテスト用に用意した関数,後で消す。