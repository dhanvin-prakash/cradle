import Foundation

enum AppTab: String, Hashable {
    case status
    case updates
    case visit
    case careTeam

    /// Reads `-initialTab <name>` from launch arguments so CI can screenshot each tab.
    static var initial: AppTab {
        UserDefaults.standard.string(forKey: "initialTab").flatMap(AppTab.init(rawValue:)) ?? .status
    }
}
