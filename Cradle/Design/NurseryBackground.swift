import SwiftUI

/// Calm vertical wash: pink at the top, white through the middle, a gentle blue at the bottom.
struct NurseryBackground: View {
    var body: some View {
        LinearGradient(
            stops: [
                .init(color: Theme.babyPink, location: 0),
                .init(color: Theme.babyPink.opacity(0.7), location: 0.25),
                .init(color: Theme.mist, location: 0.6),
                .init(color: Theme.babyBlue.opacity(0.6), location: 1)
            ],
            startPoint: .top,
            endPoint: .bottom
        )
        .background(Theme.mist)
        .ignoresSafeArea()
        .accessibilityHidden(true)
    }
}

#Preview {
    NurseryBackground()
}
