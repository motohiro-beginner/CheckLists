//@ts-check
/**inputCheckは入力されたチェックリスト名、日付、項目名に問題がないかをチェックする関数である。
 * 入力された値に問題があった場合、警告文をalertで表示し、保存を中止する。
 */
/**
 * @param {string} checkListName
 * @param {string} year
 * @param {string} month
 * @param {string} day
 * @param {string[]} items
 */
export function inputCheck(checkListName,year,month,day,items){
    let caution = [];
    let problem = false;
    if(checkListName.trim() === ""){
        caution.push("チェックリスト名を入力してください。");
        problem = true;
    }
    if(checkListName.length > 50){
        caution.push("チェックリスト名は50文字以内で入力してください。");
        problem = true;
    }
    if((year.trim() === "")||(month.trim() === "")||(day.trim() === "")){
        caution.push("年月日を入力してください。");
        problem = true;
    }
    const regex = /^[0-9]+$/;
    if(!regex.test(year) || !regex.test(month) || !regex.test(day)){
        /** テスト用コード
        console.log(!regex.test(year));
        console.log(!regex.test(month));
        console.log(!regex.test(day));*/
        caution.push("半角数字で入力してください。");
        problem = true;
    }else{
        const yearRegex = /^[1-9]\d{3}$/;
        const monthRegex = /^(0[1-9]|1[0-2])$/;
        const dayRegex = /^(0[1-9]|1[0-9]|2[0-9]|3[0-1])$/;
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
            console.log(!(checkDate.getFullYear() === yearNum));
            console.log(!(checkDate.getMonth() === monthNum-1));
            console.log(!(checkDate.getDate() === dayNum));
            caution.push("実在しない日付が入力されています。");
            problem = true;
        }
    }
    /**入力された日付が過去の値でないかをチェックする。 */
    const now = new Date();
    if(
    (now.getFullYear() > Number(year)) ||
    ((now.getFullYear() === Number(year)) && (now.getMonth() + 1 > Number(month))) ||
    ((now.getFullYear() === Number(year)) && (now.getMonth() + 1 === Number(month)) && (now.getDate() > Number(day)))
    ){
        caution.push("チェックリストに入力する日付に過去の日付を入力しないでください。");
        problem = true;
    }
    //50文字を超える項目名がないかをチェックする。
    for(const item of items){
        if(item.length > 50){
            caution.push("項目名は50文字以内で入力してください。");
            problem = true;
            break;
        }
    }
    //空白の項目名がないかをチェックする。
    for(const item of items){
        if(item.trim() === ""){
            caution.push("全ての項目名に入力してください。");
            problem = true;
            break;
        }
    }
    if(problem){
        alert(caution.join("\n"));
    }
    return {
        "problem": problem,
        "caution": caution
    };
}