import SwiftUI

enum UpdateKind: String, CaseIterable, Hashable {
    case feed
    case nappy
    case weight
    case cuddle
    case nurseNote
    case doctorRound

    var systemImage: String {
        switch self {
        case .feed: "drop.fill"
        case .nappy: "sparkles"
        case .weight: "scalemass.fill"
        case .cuddle: "hands.and.sparkles.fill"
        case .nurseNote: "text.bubble.fill"
        case .doctorRound: "stethoscope"
        }
    }

    var tint: Color {
        switch self {
        case .feed, .cuddle, .nappy: Theme.babyPink
        case .weight, .nurseNote, .doctorRound: Theme.babyBlue
        }
    }
}
