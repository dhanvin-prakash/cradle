import SwiftUI

struct VitalDetailSheet: View {
    let vital: Vital

    @Environment(\.dismiss) private var dismiss

    var body: some View {
        NavigationStack {
            ScrollView {
                VStack(alignment: .leading, spacing: Theme.sectionSpacing) {
                    GlassCard(tint: Theme.babyPink) {
                        VStack(alignment: .leading, spacing: Theme.itemSpacing) {
                            VitalValueText(vital: vital, font: .largeTitle)
                            VitalRangeLabel(vital: vital)
                        }
                    }
                    .accessibilityElement(children: .combine)

                    VStack(alignment: .leading, spacing: Theme.itemSpacing) {
                        SectionHeader(title: "What this means", systemImage: "info.circle.fill")
                        Text(vital.kind.explanation)
                    }

                    VStack(alignment: .leading, spacing: Theme.itemSpacing) {
                        SectionHeader(title: "Typical for newborns", systemImage: "chart.bar.fill")
                        TypicalRangeText(kind: vital.kind)
                    }

                    Label(
                        "Every baby is different. If anything worries you, please speak with your baby's nurse.",
                        systemImage: "person.fill.questionmark"
                    )
                    .font(.callout)
                    .foregroundStyle(.secondary)
                }
                .padding()
            }
            .navigationTitle(vital.kind.title)
            .navigationBarTitleDisplayMode(.inline)
            .toolbar {
                ToolbarItem(placement: .confirmationAction) {
                    Button("Done", systemImage: "checkmark", role: .confirm, action: close)
                }
            }
        }
        .presentationDetents([.medium, .large])
    }

    private func close() {
        dismiss()
    }
}

#Preview {
    VitalDetailSheet(vital: Vital(kind: .oxygen, value: 97))
}
