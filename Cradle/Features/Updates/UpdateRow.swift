import SwiftUI

struct UpdateRow: View {
    let update: CareUpdate

    @ScaledMetric(relativeTo: .body) private var iconSize = 44.0

    var body: some View {
        HStack(alignment: .top, spacing: Theme.itemSpacing) {
            Image(systemName: update.kind.systemImage)
                .font(.title3)
                .foregroundStyle(Theme.inkPink)
                .frame(width: iconSize, height: iconSize)
                .glassEffect(.regular.tint(update.kind.tint.opacity(0.6)), in: .circle)
                .accessibilityHidden(true)

            VStack(alignment: .leading, spacing: 4) {
                Text(update.title)
                    .font(.headline)

                Text(update.detail)
                    .font(.body)
                    .fixedSize(horizontal: false, vertical: true)

                Text("\(update.author) · \(update.date, format: .dateTime.hour().minute())")
                    .font(.footnote)
                    .foregroundStyle(.secondary)
            }
        }
        .frame(maxWidth: .infinity, alignment: .leading)
        .padding(Theme.cardPadding)
        .glassEffect(.regular, in: .rect(cornerRadius: Theme.tileRadius))
        .accessibilityElement(children: .combine)
    }
}

#Preview {
    UpdateRow(update: Baby.samples()[0].updates[0])
        .padding()
        .background { NurseryBackground() }
}
