import SwiftUI

struct UpdateTimeline: View {
    let days: [UpdateDay]

    var body: some View {
        if days.isEmpty {
            ContentUnavailableView(
                "No Updates Yet",
                systemImage: "tray",
                description: Text("New notes from the care team will appear here.")
            )
        } else {
            LazyVStack(alignment: .leading, spacing: Theme.sectionSpacing) {
                ForEach(days) { day in
                    VStack(alignment: .leading, spacing: Theme.itemSpacing) {
                        DayHeader(day: day.day)

                        GlassEffectContainer(spacing: Theme.itemSpacing) {
                            VStack(spacing: Theme.itemSpacing) {
                                ForEach(day.updates) { update in
                                    UpdateRow(update: update)
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
