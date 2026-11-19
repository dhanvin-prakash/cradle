import SwiftUI

struct ContentView: View {
    @State private var selectedTab = AppTab.status

    var body: some View {
        TabView(selection: $selectedTab) {
            Tab("Today", systemImage: "heart.text.square.fill", value: AppTab.status) {
                StatusView()
            }

            Tab("Updates", systemImage: "list.bullet.clipboard.fill", value: AppTab.updates) {
                UpdatesView()
            }

            Tab("Visit", systemImage: "figure.2.and.child.holdinghands", value: AppTab.visit) {
                VisitView()
            }
        }
        .tabBarMinimizeBehavior(.onScrollDown)
    }
}

#Preview {
    ContentView()
        .environment(NurseryStore())
}
