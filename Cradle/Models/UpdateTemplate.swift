import Foundation

/// One-tap starting points for the updates staff send most often.
enum UpdateTemplate: String, CaseIterable, Identifiable {
    case fullFeed
    case nappy
    case sleeping
    case skinToSkin
    case weighed
    case wardRound

    var id: Self { self }

    var kind: UpdateKind {
        switch self {
        case .fullFeed: .feed
        case .nappy: .nappy
        case .sleeping: .nurseNote
        case .skinToSkin: .cuddle
        case .weighed: .weight
        case .wardRound: .doctorRound
        }
    }

    var title: String {
        switch self {
        case .fullFeed: "Finished a full feed"
        case .nappy: "Nappy change"
        case .sleeping: "Sleeping peacefully"
        case .skinToSkin: "Skin-to-skin cuddle"
        case .weighed: "Weighed today"
        case .wardRound: "Ward round"
        }
    }

    var detail: String {
        switch self {
        case .fullFeed: "Took the whole feed well and settled afterwards."
        case .nappy: "Wet nappy – all normal."
        case .sleeping: "Comfortable and resting well between cares."
        case .skinToSkin: "Lovely cuddle time. Heart rate stayed nice and steady."
        case .weighed: "Weight is on track for this stage."
        case .wardRound: "The doctor reviewed your baby today and is happy with the plan."
        }
    }

    var shortTitle: String {
        switch self {
        case .fullFeed: "Feed"
        case .nappy: "Nappy"
        case .sleeping: "Sleeping"
        case .skinToSkin: "Cuddle"
        case .weighed: "Weight"
        case .wardRound: "Ward Round"
        }
    }
}
