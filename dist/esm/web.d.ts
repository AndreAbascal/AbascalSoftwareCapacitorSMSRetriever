import { WebPlugin } from '@capacitor/core';
import { CapacitorSMSRetrieverPlugin } from './definitions';
export declare class CapacitorSMSRetrieverWeb extends WebPlugin implements CapacitorSMSRetrieverPlugin {
    constructor();
    start(): Promise<any>;
}
declare const CapacitorSMSRetriever: CapacitorSMSRetrieverWeb;
export { CapacitorSMSRetriever };
