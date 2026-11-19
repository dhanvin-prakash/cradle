import SwiftUI

struct StatusCard: View {
    let status: CareStatus
    let lastUpdated: Date

    @Environment(\.accessibilityReduceMotion) private var reduceMotion

    var body: some View {
        GlassCard(tint: status.tint) {
            VStack(alignment: .leading, spacing: Theme.itemSpacing) {
                Label {
                    Text(status.title)
                        .font(.title3)
                        .bold()
                } icon: {
                    Image(systemName: status.systemImage)
                        .foregroundStyle(Theme.inkPink)
                        .symbolEffect(.breathe, isActive: reduceMotion == false)
                }

                Text(status.message)
                    .font(.body)
                    .fixedSize(horizontal: false, vertical: true)

                Label {
                    Text("Updated \(lastUpdated, format: .relative(presentation: .named))")
                } icon: {
                    Image(systemName: "clock.arrow.circlepath")
                }
                .font(.footnote)
                .foregroundStyle(.secondary)
            }
        }
        .accessibilityElement(children: .combine)
    }
}

#Preview {
    StatusCard(status: .closelyMonitored, lastUpdated: .now)
        .padding()
        .background { NurseryBackground() }
}
