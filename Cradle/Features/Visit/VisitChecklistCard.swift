import SwiftUI

struct VisitChecklistCard: View {
    var body: some View {
        VStack(alignment: .leading, spacing: Theme.itemSpacing) {
            SectionHeader(title: "Before You Visit", systemImage: "checklist")

            GlassCard(tint: Theme.babyPink) {
                VStack(alignment: .leading, spacing: Theme.itemSpacing) {
                    Label("Wash or sanitise your hands at the door", systemImage: "hands.and.sparkles.fill")
                    Label("Stay home if you have a cough, fever or cold", systemImage: "facemask.fill")
                    Label("Switch your phone to silent near the cots", systemImage: "bell.slash.fill")
                    Label("Bring your parent pass for the ward", systemImage: "person.text.rectangle.fill")
                    Label("Ask about skin-to-skin cuddles", systemImage: "heart.fill")
                }
                .labelStyle(TintedIconLabelStyle())
            }
        }
    }
}

#Preview {
    VisitChecklistCard()
        .padding()
        .background { NurseryBackground() }
}
