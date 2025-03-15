import SwiftUI
import Shared

@main
struct iOSApp: App {
    init() {
        KoinContainer.shared.start()
    }
    
	var body: some Scene {
		WindowGroup {
			ContentView()
		}
	}
}
