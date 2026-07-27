//@ts-check
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
async function search(){
    const searchName = searchNameInput.value;
    const yearSearch = yearSearchInput.value;
    const monthSearch = monthSearchInput.value;
    const daySearch = daySearchInput.value;
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
}