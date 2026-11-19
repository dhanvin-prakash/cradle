import Foundation

enum UpdateFilter: String, CaseIterable, Identifiable {
    case all
    case feeds
    case care
    case notes

    var id: Self { self }

    var title: String {
        switch self {
        case .all: "All"
        case .feeds: "Feeds"
        case .care: "Care"
        case .notes: "Notes"
        }
    }

    var systemImage: String {
        switch self {
        case .all: "square.grid.2x2.fill"
        case .feeds: "drop.fill"
        case .care: "hands.and.sparkles.fill"
        case .notes: "text.bubble.fill"
        }
    }

    func includes(_ kind: UpdateKind) -> Bool {
        switch self {
        case .all: true
        case .feeds: kind == .feed
        case .care: [.nappy, .weight, .cuddle].contains(kind)
        case .notes: [.nurseNote, .doctorRound].contains(kind)
        }
    }
}
