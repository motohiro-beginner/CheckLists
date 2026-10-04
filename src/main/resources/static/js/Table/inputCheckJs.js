//@ts-check
/**inputCheckは入力された情報に問題がないかをチェックする関数である。 */
/**
 * @param {string} searchName
 * @param {string} yearSearch
 * @param {string} monthSearch
 * @param {string} daySearch
 */
export function inputCheck(searchName,yearSearch,monthSearch,daySearch){
    let caution = [];
    let problem = false;
    //searchName,yearSearch,monthSearch,daySearchは空白を許すので、空白かどうかのチェックはしていない。
    if(searchName.length > 50){
        caution.push("チェックリスト名は50文字以内と決まっています。");
        problem = true;
    }
    const regex = /^[0-9]+$/;
    const yearRegex = /^[1-9]\d{3}$/;
    const monthRegex = /^(0[1-9]|1[0-2])$/;
    const dayRegex = /^(0[1-9]|1[0-9]|2[0-9]|3[0-1])$/;
    if(!regex.test(yearSearch)){
        caution.push("年の情報を半角数字で入力してください。");
        problem = true;
    }else{
        if(!yearRegex.test(yearSearch)){
            caution.push("年の情報は4桁の数字で入力してください。");
            problem = true;
        }
    }
    if(!regex.test(monthSearch)){
        caution.push("月の情報を半角数字で入力してください。");
        problem = true;
    }else{
        if(!monthRegex.test(monthSearch)){
            caution.push("月の情報は01~12(一桁の場合は前に0をつける)を入力してください。");
            problem = true;
        }
    }
    if(!regex.test(daySearch)){
        caution.push("日の情報を半角数字で入力してください。");
        problem = true;
    }else{
        if(!dayRegex.test(daySearch)){
            caution.push("日の情報は01~31の情報(一桁の場合は前に0をつける)を入力してください。");
            problem = true;
        }
    }
    if(yearSearch.trim() != "" && monthSearch.trim() != "" && daySearch.trim() != ""){
        const yearNum = Number(yearSearch);
        const monthNum = Number(monthSearch);
        const dayNum = Number(daySearch);
        const checkDate = new Date(yearNum,monthNum-1,dayNum);
        if(
            !(checkDate.getFullYear() === yearNum) ||
            !(checkDate.getMonth() === monthNum-1) ||
            !(checkDate.getDate() === dayNum)
        ){
            caution.push("実在しない日付が入力されています。");
            problem = true;
        }
    }
}