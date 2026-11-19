import SwiftUI

struct VitalTile: View {
    let vital: Vital
    let action: () -> Void

    var body: some View {
        Button(action: action) {
            VStack(alignment: .leading, spacing: 6) {
                Label(vital.kind.title, systemImage: vital.kind.systemImage)
                    .font(.subheadline)
                    .foregroundStyle(Theme.inkBlue)

                VitalValueText(vital: vital, font: .title)

                VitalRangeLabel(vital: vital)
            }
            .frame(maxWidth: .infinity, minHeight: Theme.minimumTapSize, alignment: .leading)
            .padding(Theme.cardPadding)
            .contentShape(.rect(cornerRadius: Theme.tileRadius))
        }
        .buttonStyle(.plain)
        .glassEffect(.regular.interactive(), in: .rect(cornerRadius: Theme.tileRadius))
        .accessibilityElement(children: .combine)
        .accessibilityHint("Explains what this reading means")
        .accessibilityInputLabels([vital.kind.title])
    }
}

#Preview {
    VitalTile(vital: Vital(kind: .heartRate, value: 140)) { }
        .padding()
        .background { NurseryBackground() }
}
