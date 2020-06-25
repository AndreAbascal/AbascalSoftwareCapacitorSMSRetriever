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
		const result: any = {};
    	return result;
	}
	stop(): Promise<any> {
		const result: any = {};
    	return result;
	}
}

const CapacitorSMSRetriever = new CapacitorSMSRetrieverWeb();

export { CapacitorSMSRetriever };

import { registerWebPlugin } from '@capacitor/core';
registerWebPlugin(CapacitorSMSRetriever);
