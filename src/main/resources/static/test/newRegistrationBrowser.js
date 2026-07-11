import { setupWorker } from "msw/browser";
/**ブラウザ版mswを使えるようにするためのインポート*/
import { newRegistrationHandlers } from "./newRegistrationHandlers";
/**newRegistrationHandlersを読み込む。 */
export const worker = setupWorker(...newRegistrationHandlers);
//handlersのルールを使ってMSWを起動させる準備をする
await worker.start();