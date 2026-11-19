import SwiftUI

struct RefreshButton: View {
    @Environment(NurseryStore.self) private var store

    var body: some View {
        Button("Refresh", systemImage: "arrow.clockwise", action: refresh)
            .symbolEffect(.rotate, isActive: store.isRefreshing)
            .disabled(store.isRefreshing)
            .sensoryFeedback(.success, trigger: store.isRefreshing) { wasRefreshing, isRefreshing in
                wasRefreshing && isRefreshing == false
            }
    }

    private func refresh() {
        Task {
            await store.refresh()
        }
    }
}
