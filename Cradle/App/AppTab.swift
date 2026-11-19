import Foundation

enum AppTab: String, Hashable, Identifiable {
    case status
    case updates
    case visit
    case careTeam

    var id: Self { self }

    var title: String {
        switch self {
        case .status: "Today"
        case .updates: "Updates"
        case .visit: "Visit"
        case .careTeam: "Send Update"
        }
    }

    var systemImage: String {
        switch self {
        case .status: "heart.text.square.fill"
        case .updates: "list.bullet.clipboard.fill"
        case .visit: "figure.2.and.child.holdinghands"
        case .careTeam: "square.and.pencil"
        }
    }

    /// Reads `-initialTab <name>` from launch arguments so CI can screenshot each tab.
    static var initial: AppTab {
        UserDefaults.standard.string(forKey: "initialTab").flatMap(AppTab.init(rawValue:)) ?? .status
    }
}
