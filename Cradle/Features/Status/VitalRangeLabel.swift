import SwiftUI

/// Uses both an icon and words so the meaning never relies on colour alone.
struct VitalRangeLabel: View {
    let vital: Vital

    var body: some View {
        Label(
            vital.isInTypicalRange ? "Typical range" : "Team is watching",
            systemImage: vital.isInTypicalRange ? "checkmark.circle.fill" : "eye.circle.fill"
        )
        .font(.footnote)
        .foregroundStyle(vital.isInTypicalRange ? Theme.inkBlue : Theme.inkPink)
    }
}
