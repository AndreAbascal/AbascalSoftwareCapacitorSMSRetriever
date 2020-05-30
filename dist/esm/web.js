import { WebPlugin } from '@capacitor/core';
export class CapacitorSMSRetrieverWeb extends WebPlugin {
    constructor() {
        super({
            name: 'CapacitorSMSRetriever',
            platforms: ['web']
        });
    }
    start() {
        return new Promise((resolve) => {
            return resolve();
        });
    }
}
const CapacitorSMSRetriever = new CapacitorSMSRetrieverWeb();
export { CapacitorSMSRetriever };
import { registerWebPlugin } from '@capacitor/core';
registerWebPlugin(CapacitorSMSRetriever);
//# sourceMappingURL=web.js.map