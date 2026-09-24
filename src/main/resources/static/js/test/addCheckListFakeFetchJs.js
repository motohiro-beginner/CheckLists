//@ts-check
/** addCheckListFakeFetchはテストのために用意した関数である。
 * 具体的にはsaveCheckListのfetch通信の部分をaddCheckListFakeFetchに差し替える。
*/
export async function okFakeFetch(){
    return {
        json: async () => ({
            ok: true,
            status: 200
        })
    };
}