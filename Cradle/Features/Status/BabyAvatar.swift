import SwiftUI

struct BabyAvatar: View {
    let name: PersonNameComponents

    @ScaledMetric(relativeTo: .title) private var size = 64.0

    var body: some View {
        Text(name, format: .name(style: .abbreviated))
            .font(.title2)
            .bold()
            .foregroundStyle(Theme.inkPink)
            .frame(width: size, height: size)
            .glassEffect(.regular.tint(Theme.babyPink.opacity(0.6)), in: .circle)
            .accessibilityHidden(true)
    }
}
