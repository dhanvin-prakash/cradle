import SwiftUI

/// Parent-friendly summary of how a baby is doing right now.
enum CareStatus: String, CaseIterable, Hashable {
    case stable
    case sleeping
    case feeding
    case closelyMonitored
    case readyForHome

    var title: String {
        switch self {
        case .stable: "Stable and comfortable"
        case .sleeping: "Sleeping peacefully"
        case .feeding: "Having a feed"
        case .closelyMonitored: "Being closely watched"
        case .readyForHome: "Getting ready for home"
        }
    }

    var message: String {
        switch self {
        case .stable:
            "All checks look good. The nurses will keep caring for your baby around the clock."
        case .sleeping:
            "Your baby is resting. Sleep helps little ones grow, so the team will let them rest."
        case .feeding:
            "Your baby is feeding right now. You'll see how it went in Updates soon."
        case .closelyMonitored:
            "The care team is keeping an extra eye on your baby. This is common in the NICU, and they'll call you if anything changes."
        case .readyForHome:
            "Your baby is doing so well that the team is planning for discharge. Ask the nurse about going-home lessons."
        }
    }

    var systemImage: String {
        switch self {
        case .stable: "checkmark.heart.fill"
        case .sleeping: "moon.zzz.fill"
        case .feeding: "drop.fill"
        case .closelyMonitored: "eye.fill"
        case .readyForHome: "house.fill"
        }
    }

    var tint: Color {
        switch self {
        case .stable, .readyForHome, .sleeping: Theme.babyBlue
        case .feeding, .closelyMonitored: Theme.babyPink
        }
    }
}
