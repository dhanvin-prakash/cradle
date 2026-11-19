import Foundation

struct Baby: Identifiable, Hashable {
    let id: UUID
    var name: PersonNameComponents
    var birthDate: Date
    var birthWeightInKilograms: Double
    var ward: Ward
    var bed: String
    var status: CareStatus
    var vitals: [Vital]
    var feeding: FeedingInfo
    var nurse: PersonNameComponents
    var doctor: PersonNameComponents
    var nurseNote: String
    var updates: [CareUpdate]
    var lastUpdated: Date

    /// Day 1 is the day of birth, matching how neonatal teams count.
    func dayOfLife(on date: Date = .now, calendar: Calendar = .current) -> Int {
        let start = calendar.startOfDay(for: birthDate)
        let end = calendar.startOfDay(for: date)
        let days = calendar.dateComponents([.day], from: start, to: end).day ?? 0
        return max(days, 0) + 1
    }
}
