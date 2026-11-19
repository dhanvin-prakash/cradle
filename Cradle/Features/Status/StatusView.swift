import SwiftUI

struct StatusView: View {
    @Environment(NurseryStore.self) private var store
    @State private var selectedVital: Vital?

    var body: some View {
        NavigationStack {
            ScrollView {
                if let baby = store.selectedBaby {
                    VStack(spacing: Theme.sectionSpacing) {
                        BabyHeaderCard(baby: baby)
                        StatusCard(status: baby.status, lastUpdated: baby.lastUpdated)
                        VitalsSection(vitals: baby.vitals, selectedVital: $selectedVital)
                        FeedingCard(feeding: baby.feeding)
                        CareTeamCard(baby: baby)
                    }
                    .padding()
                } else {
                    ContentUnavailableView(
                        "No Baby Linked",
                        systemImage: "figure.child",
                        description: Text("Ask a nurse for your baby's link code to see their updates here.")
                    )
                }
            }
            .background { NurseryBackground() }
            .navigationTitle("Today")
            .toolbar {
                ToolbarItem(placement: .topBarLeading) {
                    BabySwitcher()
                }

                ToolbarItem(placement: .topBarTrailing) {
                    RefreshButton()
                }

                ToolbarItem(placement: .topBarTrailing) {
                    AccountMenu()
                }
            }
            .refreshable {
                await store.refresh()
            }
            .sheet(item: $selectedVital, content: VitalDetailSheet.init)
        }
    }
}

#Preview {
    StatusView()
        .environment(NurseryStore())
}
