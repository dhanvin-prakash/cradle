import SwiftUI

/// Standard label layout with the icon tinted in the nursery ink colour.
struct TintedIconLabelStyle: LabelStyle {
    func makeBody(configuration: Configuration) -> some View {
        Label {
            configuration.title
        } icon: {
            configuration.icon
                .foregroundStyle(Theme.inkPink)
        }
    }
}
