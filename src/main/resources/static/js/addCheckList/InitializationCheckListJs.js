//@ts-check
import { element } from "./helperJs.js";
/**initializationは保存が終わった後、チェックリスト作成画面を初期状態に戻す関数である。*/
export function initialization(){
    const itemColumns = element(document,".itemColumns",HTMLDivElement);
    itemColumns.replaceChildren();
    const checkListName = element(document,".checkListName",HTMLInputElement);
    checkListName.value = "";
    const year = element(document,".year",HTMLInputElement);
    year.value = "";
    const month = element(document,".month",HTMLInputElement);
    month.value = "";
    const day = element(document,".day",HTMLInputElement);
    day.value = "";
}