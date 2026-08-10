//@ts-check
import { checkListsTable } from "./checkListsTableJs.js";
import { searchNotFound } from "./checkListsTableJs.js";
import { searchResultFetch, searchResultFetchNoContent } from "../../test/tableFakeFetchJs.js";
const searchNameInput =
/**@type { HTMLInputElement } */
(document.querySelector("#searchName"));
const yearSearchInput =
/**@type { HTMLInputElement } */
(document.querySelector("#yearSearch"));
const monthSearchInput =
/**@type { HTMLInputElement } */
(document.querySelector("#monthSearch"));
const daySearchInput =
/**@type { HTMLInputElement } */
(document.querySelector("#daySearch"));
const searchBtn =
/**@type { HTMLButtonElement } */
(document.querySelector("#searchBtn"));
const container =
/**@type { HTMLDivElement } */
(document.querySelector(".cardContainer"));
if(!(searchNameInput instanceof HTMLInputElement)){
    throw new Error("searchNameが見つかりません。");
}
if(!(yearSearchInput instanceof HTMLInputElement)){
    throw new Error("yearSearchが見つかりません。");
}
if(!(monthSearchInput instanceof HTMLInputElement)){
    throw new Error("monthSearchが見つかりません。");
}
if(!(daySearchInput instanceof HTMLInputElement)){
    throw new Error("daySearchが見つかりません。");
}
if(!(searchBtn instanceof HTMLButtonElement)){
    throw new Error("searchBtnが見つかりません。");
}
if(!(container instanceof HTMLDivElement)){
    throw new Error("cardContainerが見つかりません。");
}
//console.log(cardContainer);
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
/**searchはチェックリスト名,年,月,日の情報を送り該当するチェックリストを受けとる関数である。
 * それぞれの値は入力必須ではないため入力されていない可能性がある。
 */
async function search(){
    console.log("関数search開始");
    container.classList.remove("notFoundText");
    const searchName = searchNameInput.value;
    const yearSearch = yearSearchInput.value;
    const monthSearch = monthSearchInput.value;
    const daySearch = daySearchInput.value;
    /*テストのためfakeFetchJs.jsの関数に差し替えている。
    const response = await fetch("/search", {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify({
            searchName,
            yearSearch,
            monthSearch,
            daySearch
        })
    });
    */
    const response = await searchResultFetchNoContent(searchName,yearSearch,monthSearch,daySearch);
    console.log("response");
    if(response.ok){
        container.replaceChildren();
        /**@type { TableViewDTO[] } */
        const checkLists = await response.json();
        checkListsTable(checkLists);
        //console.log("checkListsTable");
    }else if(response.status === 204){
        searchNotFound();
        console.log("searchNotFound");
    }else if(response.status === 400){
        const caution = await response.json();
        alert(caution.join("\n"));
    }else{
        throw new Error("Fetch通信の応答にてError発生");
    }
}
searchBtn.addEventListener("click",search);