import SwiftUI

struct VisitingHoursCard: View {
    let ward: Ward

    var body: some View {
        VStack(alignment: .leading, spacing: Theme.itemSpacing) {
            SectionHeader(title: "Visiting Hours", systemImage: "clock.fill")

            GlassCard(tint: Theme.babyPink) {
                VStack(alignment: .leading, spacing: Theme.itemSpacing) {
                    Text(ward.name)
                        .font(.headline)

                    LabeledContent {
                        Text(ward.parentHours)
                    } label: {
                        Label("Parents", systemImage: "heart.fill")
                    }

                    LabeledContent {
                        Text(ward.visitorHours)
                    } label: {
                        Label("Other visitors", systemImage: "person.2.fill")
                    }

                    LabeledContent {
                        Text("\(ward.visitorsAtBedside) people")
                    } label: {
                        Label("At the bedside", systemImage: "bed.double.fill")
                    }
                }
                .labelStyle(TintedIconLabelStyle())
            }
        }
    }
}

#Preview {
    VisitingHoursCard(ward: .neonatalIntensiveCare)
        .padding()
        .background { NurseryBackground() }
}
