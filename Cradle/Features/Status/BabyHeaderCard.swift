import SwiftUI

struct BabyHeaderCard: View {
    let baby: Baby

    var body: some View {
        GlassCard(tint: Theme.babyPink) {
            HStack(spacing: Theme.itemSpacing) {
                BabyAvatar(name: baby.name)

                VStack(alignment: .leading, spacing: 4) {
                    Text(baby.name, format: .name(style: .medium))
                        .font(.title2)
                        .bold()

                    Text("Day \(baby.dayOfLife()) · Born \(baby.birthDate, format: .dateTime.day().month())")
                        .font(.subheadline)
                        .foregroundStyle(.secondary)

                    Label("\(baby.ward.shortName) · Bed \(baby.bed)", systemImage: "bed.double.fill")
                        .font(.subheadline)
                        .foregroundStyle(Theme.inkBlue)
                }
            }
        }
        .accessibilityElement(children: .combine)
    }
}

#Preview {
    BabyHeaderCard(baby: Baby.samples()[0])
        .padding()
        .background { NurseryBackground() }
}
