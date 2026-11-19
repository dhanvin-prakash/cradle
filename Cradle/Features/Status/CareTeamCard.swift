import SwiftUI

struct CareTeamCard: View {
    let baby: Baby

    var body: some View {
        VStack(alignment: .leading, spacing: Theme.itemSpacing) {
            SectionHeader(title: "Care Team", systemImage: "person.2.fill")

            GlassCard(tint: Theme.babyPink) {
                VStack(alignment: .leading, spacing: Theme.itemSpacing) {
                    LabeledContent {
                        Text(baby.nurse, format: .name(style: .medium))
                    } label: {
                        Label("Nurse today", systemImage: "cross.case.fill")
                    }

                    LabeledContent {
                        Text(baby.doctor, format: .name(style: .medium))
                    } label: {
                        Label("Doctor", systemImage: "stethoscope")
                    }

                    Divider()

                    Label {
                        Text("“\(baby.nurseNote)”")
                            .italic()
                            .fixedSize(horizontal: false, vertical: true)
                    } icon: {
                        Image(systemName: "quote.bubble.fill")
                    }
                }
                .labelStyle(TintedIconLabelStyle())
            }
        }
    }
}

#Preview {
    CareTeamCard(baby: Baby.samples()[1])
        .padding()
        .background { NurseryBackground() }
}
