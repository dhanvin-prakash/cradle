import SwiftUI

struct VisitView: View {
    @Environment(NurseryStore.self) private var store

    var body: some View {
        NavigationStack {
            ScrollView {
                VStack(spacing: Theme.sectionSpacing) {
                    if let baby = store.selectedBaby {
                        VisitingHoursCard(ward: baby.ward)
                        ContactWardCard(ward: baby.ward)
                    }

                    VisitChecklistCard()

                    Text("Sample information for demonstration. Please confirm visiting rules with the ward.")
                        .font(.footnote)
                        .foregroundStyle(.secondary)
                        .multilineTextAlignment(.center)
                }
                .padding()
            }
            .background { NurseryBackground() }
            .navigationTitle("Visit")
            .toolbar {
                ToolbarItem(placement: .topBarLeading) {
                    BabySwitcher()
                }

                ToolbarItem(placement: .topBarTrailing) {
                    AccountMenu()
                }
            }
        }
    }
}

#Preview {
    VisitView()
        .environment(NurseryStore())
}
