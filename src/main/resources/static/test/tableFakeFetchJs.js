//@ts-check
export async function tableFakeFetch(){
    return {
        ok: true,
        status: 200,
        json: async () => [
            {   
                checkListsId: "1",
                checkListsName: "買い物",
                items: [
                {   itemId: "1",
                    itemName: "リンゴ",
                    isChecked: true
                },
                {
                    itemId: "2",
                    itemName: "みかん",
                    isChecked: true
                },
                {
                    itemId: "3",
                    itemName: "いちご",
                    isChecked: false
                }
                ],
                createdAt: "2026/01/01"
            },
            {
                checkListsId: "2",
                checkListsName: "買い物",
                items: [
                {
                    itemId: "4",
                    itemName: "リンゴ",
                    isChecked: true
                },
                {
                    itemId: "5",
                    itemName: "みかん",
                    isChecked: true
                },
                {
                    itemId: "6",
                    itemName: "いちご",
                    isChecked: false
                }
                ],
                createdAt: "2026/01/01"
            },
            {
                checkListsId: "3",
                checkListsName: "買い物",
                items: [
                {
                    itemId: "7",
                    itemName: "リンゴ",
                    isChecked: true
                },
                {
                    itemId: "8",
                    itemName: "みかん",
                    isChecked: true
                },
                {
                    itemId: "9",
                    itemName: "いちご",
                    isChecked: false
                }
                ],
                createdAt: "2026/01/01"
            },
            {
                checkListsId: "4",
                checkListsName: "買い物",
                items: [
                {
                    itemId: "10",
                    itemName: "リンゴ",
                    isChecked: true
                },
                {
                    itemId: "11",
                    itemName: "みかん",
                    isChecked: true
                },
                {
                    itemId: "12",
                    itemName: "いちご",
                    isChecked: false
                }
                ],
                createdAt: "2026/01/01"
            },
            {
                checkListsId: "5",
                checkListsName: "買い物",
                items: [
                {
                    itemId: "13",
                    itemName: "リンゴ",
                    isChecked: true
                },
                {
                    itemId: "14",
                    itemName: "みかん",
                    isChecked: true
                },
                {
                    itemId: "15",
                    itemName: "いちご",
                    isChecked: false
                }
                ],
                createdAt: "2026/01/01"
            },
            {
                checkListsId: "6",
                checkListsName: "買い物",
                items: [
                {
                    itemId: "16",
                    itemName: "リンゴ",
                    isChecked: true
                },
                {
                    itemId: "17",
                    itemName: "みかん",
                    isChecked: true
                },
                {
                    itemId: "18",
                    itemName: "いちご",
                    isChecked: false
                }
                ],
                createdAt: "2026/01/01"
            }
        ]
    };
}