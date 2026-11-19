import Foundation
import Testing
@testable import Cradle

@MainActor
struct NurseryStoreTests {
    @Test func selectsFirstBabyByDefault() {
        let babies = Baby.samples()
        let store = NurseryStore(babies: babies)

        #expect(store.selectedBaby?.id == babies.first?.id)
    }

    @Test func switchingBabyUpdatesSelection() {
        let babies = Baby.samples()
        let store = NurseryStore(babies: babies)

        store.selectedBabyID = babies[1].id

        #expect(store.selectedBaby?.id == babies[1].id)
    }

    @Test func emptyStoreHasNoSelection() {
        let store = NurseryStore(babies: [])

        #expect(store.selectedBaby == nil)
    }

    @Test func feedFilterOnlyReturnsFeeds() throws {
        let store = NurseryStore()
        let baby = try #require(store.selectedBaby)

        let updates = store.updateDays(for: baby, filter: .feeds).flatMap(\.updates)

        #expect(updates.isEmpty == false)
        #expect(updates.allSatisfy { $0.kind == .feed })
    }

    @Test func updatesAreGroupedNewestFirst() throws {
        let store = NurseryStore()
        let baby = try #require(store.selectedBaby)

        let days = store.updateDays(for: baby, filter: .all)

        #expect(days.map(\.day) == days.map(\.day).sorted(by: >))
        for day in days {
            #expect(day.updates == day.updates.sorted(by: >))
        }
        #expect(days.flatMap(\.updates).count == baby.updates.count)
    }

    @Test func refreshStampsLatestTime() async {
        let store = NurseryStore(refreshDelay: .zero)
        let now = Date.now.addingTimeInterval(60)

        await store.refresh(now: now)

        #expect(store.babies.allSatisfy { $0.lastUpdated == now })
        #expect(store.isRefreshing == false)
    }
}
