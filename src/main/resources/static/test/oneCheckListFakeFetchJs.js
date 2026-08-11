//@ts-check
/**
 * @typedef {Object} OneCheckListViewDTO
 * @property {string} checkListsId,
 * @property {string} checkListsName,
 * @property {OneItemsViewDTO[]} items,
 * @property {string} createdAt
 */
/**
 * @typedef {Object} OneItemsViewDTO
 * @property {string} itemId,
 * @property {string} itemNames,
 * @property {boolean} isChecked
 */
export async function oneCheckListFakeFetch(){
    return {
        ok: true,
        status: 200,
        json: async () => ({
            checkListsId: "1",
            checkListsName: "買い物",
            items: [
                {
                    itemId: "1",
                    itemNames: "りんご",
                    isChecked: true
                },
                {
                    itemId: "2",
                    itemNames: "みかん",
                    isChecked: false
                }
            ],
            createdAt: "2026/01/01"
        })
    }
}