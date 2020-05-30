import { WebPlugin } from '@capacitor/core';
import { CapacitorSMSRetrieverPlugin } from './definitions';

export class CapacitorSMSRetrieverWeb extends WebPlugin implements CapacitorSMSRetrieverPlugin {
	constructor() {
		super({
			name: 'CapacitorSMSRetriever',
			platforms: ['web']
		});
	}

	start(): Promise<any> {
		return new Promise((resolve) => {
			return resolve();
		});
	}
}

const CapacitorSMSRetriever = new CapacitorSMSRetrieverWeb();

export { CapacitorSMSRetriever };

import { registerWebPlugin } from '@capacitor/core';
registerWebPlugin(CapacitorSMSRetriever);
