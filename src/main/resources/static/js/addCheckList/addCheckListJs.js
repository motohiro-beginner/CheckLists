//@ts-check
import { element } from "./helperJs.js";
function createAddCheckList(){
    const newCheckListContainer = element(document,".newCheckListContainer",HTMLDivElement);
    const checkListName = document.createElement("input");
    checkListName.type = "text";
    checkListName.classList.add("checkListName");
    newCheckListContainer.appendChild(checkListName);
    const createdAt = document.createElement("input");
    createdAt.type = "text";
    createdAt.classList.add("createdAt");
    newCheckListContainer.appendChild(createdAt);
    
}