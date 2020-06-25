import Foundation
import Capacitor

/**
 * Please read the Capacitor iOS Plugin Development Guide
 * here: https://capacitor.ionicframework.com/docs/plugins/ios
 */
@objc(CapacitorSMSRetriever)
public class CapacitorSMSRetriever: CAPPlugin {
    
    @objc func start(_ call: CAPPluginCall) {
        call.resolve();
    }
    @objc func stop(_ call: CAPPluginCall) {
        call.resolve();
    }
}
