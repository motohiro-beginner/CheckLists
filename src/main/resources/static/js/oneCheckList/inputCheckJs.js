//@ts-check
export function inputCheck(/**@type {string}*/checkListName,/**@type {string} */year,/**@type {string}*/month,/**@type {string}*/day,/**@type {string} */itemNames,/**@type {boolean} */isChecked){
    let caution = [];
    if(checkListName.trim() === ""){
        caution.push("チェックリスト名を入力してください。");
    }
    const now = new Date();
    const yearRegex = /^[1-9]\d{3}$/;
    const monthRegex = /^(0?[1-9]|1[0-2])$/;
    const dayRegex = /^(0?[1-9]|1[0-9]|2[0-9]|3[0-1])$/;
    if(!yearRegex.test(year) || !monthRegex.test(month) || !dayRegex.test(day)){
        caution.push("2026/01/01 このように日付を入力してください。");
    }
    //入力された日付が現在よりも過去の時刻でないかをチェックしている。
    if((now.getFullYear()>Number(year)) || (now.getFullYear() === Number(year) && now.getMonth() + 1>Number(month)) || ((now.getFullYear() === Number(year)) && (now.getMonth() === Number(month)) && (now.getDate()>Number(day)))){
        caution.push("チェックリストに入力する日付に過去の日付を入力しないでください。");
    }
}