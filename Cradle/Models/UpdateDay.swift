import Foundation

/// A day's worth of updates, newest first.
struct UpdateDay: Identifiable, Hashable {
    let day: Date
    var updates: [CareUpdate]

    var id: Date { day }
}
