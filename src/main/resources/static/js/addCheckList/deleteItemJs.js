//@ts-check
import { element } from "./helperJs.js";
/**
 * @param { HTMLDivElement } itemColumns
 */
export function deleteItem(itemColumns){
    const cancelBtn = document.createElement("button");
    cancelBtn.classList.add("cancelBtn");
    const addAndDelete = element(itemColumns,".addAndDelete",HTMLDivElement);
}