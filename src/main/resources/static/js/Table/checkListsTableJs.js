//@ts-check
/**
 * @typedef {Object} TableViewDTO
 * @property {string} checkListsName,
 * @property {TableItemsViewDTO[]} items,
 * @property {string} createdAt
 */
/**
 * @typedef {Object} TableItemsViewDTO
 * @property {string} itemName,
 * @property {boolean} isChecked
 */
function checkListsTable(/**@type {TableViewDTO[]} */checkLists)