import Foundation

/// Who is using the app. A simple toggle for the MVP; real sign-in comes later.
enum AppRole: String, CaseIterable, Identifiable {
    case parent
    case careTeam

    static let storageKey = "appRole"

    var id: Self { self }

    var title: String {
        switch self {
        case .parent: "Parent"
        case .careTeam: "Care Team (Admin)"
        }
    }

    var systemImage: String {
        switch self {
        case .parent: "figure.2.and.child.holdinghands"
        case .careTeam: "stethoscope"
        }
    }

    /// Parents see their baby; staff get the update composer first.
    var tabs: [AppTab] {
        switch self {
        case .parent: [.status, .updates, .visit]
        case .careTeam: [.careTeam, .status, .updates]
        }
    }

    var defaultTab: AppTab { tabs[0] }
}
