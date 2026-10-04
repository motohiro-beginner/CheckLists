//@ts-check
/**
 * @param {string} searchName
 * @param {string} yearSearch
 * @param {string} monthSearch
 * @param {string} daySearch
 */
export function inputCheck(searchName,yearSearch,monthSearch,daySearch){
    let caution = [];
    let problem = false;
    if(searchName.length > 50){
        caution.push("チェックリスト名は50文字以内と決まっています。");
        problem = true;
    }
    const regex = /^[0-9]+$/;
    if(!regex.test(yearSearch)){
        caution.push("半角数字で入力してください。");
        problem = true;
    }else{
        const yearRegex = /^[1-9]\d{3}$/;
        if(!yearRegex.test(yearSearch)){
            caution.push("年の情報は")
        }
    }
}