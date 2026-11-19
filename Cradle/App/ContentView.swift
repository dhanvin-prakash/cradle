import SwiftUI

struct ContentView: View {
    @Environment(NurseryStore.self) private var store
    @AppStorage(AppRole.storageKey) private var role = AppRole.parent
    @State private var selectedTab = AppTab.initial

    var body: some View {
        TabView(selection: $selectedTab) {
            ForEach(role.tabs) { tab in
                Tab(tab.title, systemImage: tab.systemImage, value: tab) {
                    TabRoot(tab: tab)
                }
                .badge(badgeCount(for: tab))
            }
        }
        .tabBarMinimizeBehavior(.onScrollDown)
        .onAppear(perform: keepTabValid)
        .onChange(of: role, keepTabValid)
    }

    /// Only parents get the unread badge; staff wrote the updates themselves.
    private func badgeCount(for tab: AppTab) -> Int {
        tab == .updates && role == .parent ? store.unreadUpdateCount : 0
    }

    private func keepTabValid() {
        if role.tabs.contains(selectedTab) == false {
            selectedTab = role.defaultTab
        }
    }
}

#Preview {
    ContentView()
        .environment(NurseryStore())
}
