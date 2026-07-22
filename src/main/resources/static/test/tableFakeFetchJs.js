//@ts-check
export async function tableFakeFetch(){
    return {
        ok: true,
        status: 200,
        json: async () => [
            {
                checkListsName: "買い物",
                items: [
                {
                    itemName: "リンゴ",
                    isChecked: true
                },
                {
                    itemName: "みかん",
                    isChecked: true
                },
                {
                    itemName: "いちご",
                    isChecked: false
                }
                ],
                createdAt: "2026/01/01"
            },
            {
                checkListsName: "買い物",
                items: [
                {
                    itemName: "リンゴ",
                    isChecked: true
                },
                {
                    itemName: "みかん",
                    isChecked: true
                },
                {
                    itemName: "いちご",
                    isChecked: false
                }
                ],
                createdAt: "2026/01/01"
            },
            {
                checkListsName: "買い物",
                items: [
                {
                    itemName: "リンゴ",
                    isChecked: true
                },
                {
                    itemName: "みかん",
                    isChecked: true
                },
                {
                    itemName: "いちご",
                    isChecked: false
                }
                ],
                createdAt: "2026/01/01"
            },
            {
                checkListsName: "買い物",
                items: [
                {
                    itemName: "リンゴ",
                    isChecked: true
                },
                {
                    itemName: "みかん",
                    isChecked: true
                },
                {
                    itemName: "いちご",
                    isChecked: false
                }
                ],
                createdAt: "2026/01/01"
            },
            {
                checkListsName: "買い物",
                items: [
                {
                    itemName: "リンゴ",
                    isChecked: true
                },
                {
                    itemName: "みかん",
                    isChecked: true
                },
                {
                    itemName: "いちご",
                    isChecked: false
                }
                ],
                createdAt: "2026/01/01"
            },
            {
                checkListsName: "買い物",
                items: [
                {
                    itemName: "リンゴ",
                    isChecked: true
                },
                {
                    itemName: "みかん",
                    isChecked: true
                },
                {
                    itemName: "いちご",
                    isChecked: false
                }
                ],
                createdAt: "2026/01/01"
            }
        ]
    };
}