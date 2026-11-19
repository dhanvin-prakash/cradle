import SwiftUI

/// Soft baby blue and pink mesh that Liquid Glass surfaces refract.
struct NurseryBackground: View {
    var body: some View {
        MeshGradient(
            width: 3,
            height: 3,
            points: [
                [0, 0], [0.5, 0], [1, 0],
                [0, 0.5], [0.55, 0.45], [1, 0.5],
                [0, 1], [0.5, 1], [1, 1]
            ],
            colors: [
                Theme.babyPink, Theme.mist, Theme.babyBlue,
                Theme.babyBlue, Theme.babyPink, Theme.mist,
                Theme.mist, Theme.babyBlue, Theme.babyPink
            ]
        )
        .ignoresSafeArea()
        .accessibilityHidden(true)
    }
}

#Preview {
    NurseryBackground()
}
