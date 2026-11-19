import SwiftUI

/// Shared design constants so every screen feels consistent.
/// Pastel colours are for surfaces; the "ink" colours are darker
/// variants that keep text and icons readable in light and dark mode.
enum Theme {
    static let babyBlue = Color(.nurseryBlue)
    static let babyPink = Color(.nurseryPink)
    static let mist = Color(.nurseryMist)
    static let inkBlue = Color(.inkBlue)
    static let inkPink = Color(.inkPink)

    static let sectionSpacing = 20.0
    static let itemSpacing = 12.0
    static let cardPadding = 18.0

    static let cardRadius = 28.0
    static let tileRadius = 22.0

    static let minimumTapSize = 44.0
    static let glassSpacing = 16.0
}
