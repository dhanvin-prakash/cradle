import SwiftUI

struct UpdatesView: View {
    @Environment(NurseryStore.self) private var store
    @State private var filter = UpdateFilter.all

    var body: some View {
        NavigationStack {
            ScrollView {
                VStack(alignment: .leading, spacing: Theme.sectionSpacing) {
                    UpdateFilterBar(selection: $filter)

                    if let baby = store.selectedBaby {
                        UpdateTimeline(days: store.updateDays(for: baby, filter: filter))
                    }
                }
                .padding()
            }
            .background { NurseryBackground() }
            .navigationTitle(navigationTitle)
            .toolbar {
                ToolbarItem(placement: .topBarLeading) {
                    BabySwitcher()
                }

                ToolbarItem(placement: .topBarTrailing) {
                    AccountMenu()
                }
            }
            .refreshable {
                await store.refresh()
            }
            .onAppear(perform: store.markUpdatesRead)
        }
    }

    private var navigationTitle: String {
        guard let baby = store.selectedBaby else { return "Updates" }
        return "\(baby.name.formatted(.name(style: .short)))'s Updates"
    }
}

#Preview {
    UpdatesView()
        .environment(NurseryStore())
}
