import SwiftUI

/// A full-width Liquid Glass panel used for every content card.
struct GlassCard<Content: View>: View {
    var tint: Color?
    @ViewBuilder let content: Content

    var body: some View {
        content
            .frame(maxWidth: .infinity, alignment: .leading)
            .padding(Theme.cardPadding)
            .glassEffect(.regular.tint(tint?.opacity(0.25)), in: .rect(cornerRadius: Theme.cardRadius))
    }
}

#Preview {
    ZStack {
        NurseryBackground()
        GlassCard(tint: Theme.babyPink) {
            Text("Hello, little one")
                .font(.headline)
        }
        .padding()
    }
}
