import SwiftUI

struct FeedingCard: View {
    let feeding: FeedingInfo

    var body: some View {
        VStack(alignment: .leading, spacing: Theme.itemSpacing) {
            SectionHeader(title: "Feeding", systemImage: "drop.fill")

            GlassCard(tint: Theme.babyPink) {
                VStack(alignment: .leading, spacing: Theme.itemSpacing) {
                    LabeledContent {
                        Text(feeding.lastFeed, format: .relative(presentation: .named))
                    } label: {
                        Label("Last feed", systemImage: "clock.fill")
                    }

                    LabeledContent {
                        Text(feeding.nextFeed, format: .dateTime.hour().minute())
                    } label: {
                        Label("Next feed", systemImage: "alarm.fill")
                    }

                    LabeledContent {
                        Text(Measurement(value: Double(feeding.amountInMillilitres), unit: UnitVolume.milliliters), format: .measurement(width: .abbreviated))
                    } label: {
                        Label("Amount", systemImage: "drop.halffull")
                    }

                    Text(feeding.method)
                        .font(.subheadline)
                        .foregroundStyle(.secondary)
                }
                .labelStyle(TintedIconLabelStyle())
            }
        }
    }
}

#Preview {
    FeedingCard(feeding: Baby.samples()[0].feeding)
        .padding()
        .background { NurseryBackground() }
}
