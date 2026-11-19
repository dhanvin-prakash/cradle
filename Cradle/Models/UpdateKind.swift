import SwiftUI

enum UpdateKind: String, CaseIterable, Hashable {
    case feed
    case nappy
    case weight
    case cuddle
    case nurseNote
    case doctorRound

    var title: String {
        switch self {
        case .feed: "Feed"
        case .nappy: "Nappy"
        case .weight: "Weight"
        case .cuddle: "Cuddle"
        case .nurseNote: "Nurse note"
        case .doctorRound: "Doctor's round"
        }
    }

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
