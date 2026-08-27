//@ts-check
/**
 * @template {Element} T
 * @param {ParentNode} parent
 * @param {string} selector
 * @param {new (...args:any[]) => T} type
 * @returns {T} 
 */
//elementはparentの子要素のselectorのelement(属性)がtypeで指定する属性と一致するかを確かめる関数である。
//変数の中身が存在することを保証するために作った関数である。
export function element(parent,selector,type){
    const element = parent.querySelector(selector);
    if(!(element instanceof type)){
            throw new Error(`${selector}の型が一致しません。`);
    }
    return element;
}
/**
 * @template {Element} T
 * @param {ParentNode} parent
 * @param {string} selector
 * @param {new (...args:any[]) => T} type
 * @returns {NodeListOf<T>} 
 */
//elementのquerySelectorAll版
export function allElement(parent,selector,type){
    const allElement = parent.querySelectorAll(selector);
    allElement.forEach(element => {
        if(!(element instanceof type)){
        throw new Error(`${selector}の型が一致しません。`);
      }
    });
    return /** @type {NodeListOf<T>} */(allElement);
}