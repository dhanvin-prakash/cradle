import SwiftUI

struct StatusCard: View {
    let status: CareStatus
    let lastUpdated: Date

    @Environment(\.accessibilityReduceMotion) private var reduceMotion
    @ScaledMetric(relativeTo: .title3) private var iconSize = 44.0

    var body: some View {
        GlassCard(tint: status.tint) {
            VStack(alignment: .leading, spacing: Theme.itemSpacing) {
                HStack(spacing: Theme.itemSpacing) {
                    Image(systemName: status.systemImage)
                        .font(.title3)
                        .foregroundStyle(Theme.inkPink)
                        .frame(width: iconSize, height: iconSize)
                        .glassEffect(.regular.tint(status.tint.opacity(0.6)), in: .circle)
                        .symbolEffect(.pulse, options: .repeat(2), value: status)
                        .accessibilityHidden(true)

                    Text(status.title)
                        .font(.title3)
                        .bold()
                }
                .animation(reduceMotion ? nil : .smooth, value: status)

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
