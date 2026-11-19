import SwiftUI

struct ContentView: View {
    @Environment(NurseryStore.self) private var store
    @State private var selectedTab = AppTab.initial

    var body: some View {
        TabView(selection: $selectedTab) {
            Tab("Today", systemImage: "heart.text.square.fill", value: AppTab.status) {
                StatusView()
            }

            Tab("Updates", systemImage: "list.bullet.clipboard.fill", value: AppTab.updates) {
                UpdatesView()
            }
            .badge(store.unreadUpdateCount)

            Tab("Visit", systemImage: "figure.2.and.child.holdinghands", value: AppTab.visit) {
                VisitView()
            }

            Tab("Care Team", systemImage: "stethoscope", value: AppTab.careTeam) {
                CareTeamComposeView()
            }
        }
        .tabBarMinimizeBehavior(.onScrollDown)
    }
}

#Preview {
    ContentView()
        .environment(NurseryStore())
}
