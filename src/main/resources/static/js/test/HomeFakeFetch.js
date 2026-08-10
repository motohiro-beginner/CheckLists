//@ts-check
export async function notFoundFakeFetch(){
    return {
        ok: false,
        status: 204,
        json: {}
    };
}