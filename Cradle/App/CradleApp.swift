import SwiftUI

@main
struct CradleApp: App {
    @State private var store = NurseryStore()

    var body: some Scene {
        WindowGroup {
            ContentView()
                .environment(store)
        }
    }
}
