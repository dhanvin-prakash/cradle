import Foundation
import Observation

/// Holds the babies a parent can see and simulates syncing with the ward.
@MainActor
@Observable
final class NurseryStore {
    private(set) var babies: [Baby]
    var selectedBabyID: Baby.ID?
    private(set) var isRefreshing = false

    @ObservationIgnored private let refreshDelay: Duration

    init(babies: [Baby] = Baby.samples(), refreshDelay: Duration = .seconds(1)) {
        self.babies = babies
        self.selectedBabyID = babies.first?.id
        self.refreshDelay = refreshDelay
    }

    var selectedBaby: Baby? {
        babies.first { $0.id == selectedBabyID } ?? babies.first
    }

    /// Pretends to fetch the latest readings from the ward.
    func refresh(now: Date = .now) async {
        guard isRefreshing == false else { return }
        isRefreshing = true
        defer { isRefreshing = false }

        try? await Task.sleep(for: refreshDelay)
        guard Task.isCancelled == false else { return }

        for index in babies.indices {
            babies[index].lastUpdated = now
        }
    }

    /// Updates matching `filter`, grouped by calendar day with the newest day and update first.
    func updateDays(for baby: Baby, filter: UpdateFilter, calendar: Calendar = .current) -> [UpdateDay] {
        let matching = baby.updates
            .filter { filter.includes($0.kind) }
            .sorted(by: >)

        let grouped = Dictionary(grouping: matching) { calendar.startOfDay(for: $0.date) }

        return grouped
            .map { UpdateDay(day: $0.key, updates: $0.value) }
            .sorted { $0.day > $1.day }
    }
}
