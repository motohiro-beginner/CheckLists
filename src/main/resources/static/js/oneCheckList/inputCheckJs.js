//@ts-check
/**
 * @typedef {Object} items
 * @property {string} itemId,
 * @property {string} itemNames,
 * @property {boolean} isChecked
 */
//inputCheckは入力されたチェックリスト名、日付、項目に関するデータに問題がないかをチェックするための関数である。
export function inputCheck(/**@type {string}*/checkListName,/**@type {string} */year,/**@type {string}*/month,/**@type {string}*/day,/**@type {items[]} */items){
    let caution = [];
    let problem = false;
    //チェックリストの名前が記入されているかを確かめる。
    if(checkListName.trim() === ""){
        caution.push("チェックリスト名を入力してください。");
        problem = true;
    }
    const now = new Date();
    const yearRegex = /^[1-9]\d{3}$/;
    const monthRegex = /^(0?[1-9]|1[0-2])$/;
    const dayRegex = /^(0?[1-9]|1[0-9]|2[0-9]|3[0-1])$/;
    //入力された日付がyyyy/MM/ddという形式になっているかを確かめる。
    if(!yearRegex.test(year) || !monthRegex.test(month) || !dayRegex.test(day)){
        caution.push("2026/01/01 このように日付を入力してください。");
        problem = true;
    }
    const yearNum = Number(year);
    const monthNum = Number(month);
    const dayNum = Number(day);
    //入力された日付が実在する日付かどうかを確かめる。
    const checkDate = new Date(yearNum,monthNum-1,dayNum);
    if(
        !(checkDate.getFullYear() === yearNum) ||
        !(checkDate.getMonth() === monthNum-1) ||
        !(checkDate.getDate() === dayNum)
    ){
        caution.push("実在しない日付が入力されています。");
        problem = true;
    }
    //入力された日付が現在よりも過去の時刻でないかをチェックしている。
    if(
        (now.getFullYear()>Number(year)) || 
    (now.getFullYear() === Number(year) && now.getMonth() + 1>Number(month)) || 
    ((now.getFullYear() === Number(year)) && (now.getMonth() === Number(month)) && (now.getDate()>Number(day))))
    {
        caution.push("チェックリストに入力する日付に過去の日付を入力しないでください。");
        problem = true;
    }
    for(const item of items){
        if(item.itemNames.trim() === ""){
        caution.push("入力されていない項目があります。");
        problem = true;
        break;
    }
    };
    return {
        caution: caution,
        problem: problem
    };
}