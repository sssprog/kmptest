import SwiftUI
import ComposeApp

@main
struct iOSApp: App {
    init() {
        KoinStarterKt.doInitKoinIos()
    }

    var body: some Scene {
        WindowGroup {
            ContentView()
        }
    }
}