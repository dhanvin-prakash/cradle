import Foundation
import Testing
@testable import Cradle

@MainActor
struct CareTeamTests {
    private func readyDraft() -> UpdateDraft {
        var draft = UpdateDraft(author: "Nurse Siti")
        draft.apply(.fullFeed)
        return draft
    }

    @Test func draftNeedsTitleAndAuthor() {
        #expect(UpdateDraft().isReadyToSend == false)
        #expect(UpdateDraft(title: "Feed", author: "  ").isReadyToSend == false)
        #expect(UpdateDraft(title: "   ", author: "Nurse Siti").isReadyToSend == false)
        #expect(readyDraft().isReadyToSend)
    }

    @Test func templateFillsDraftButKeepsAuthor() {
        let draft = readyDraft()

        #expect(draft.kind == .feed)
        #expect(draft.title == UpdateTemplate.fullFeed.title)
        #expect(draft.author == "Nurse Siti")
    }

    @Test func resetKeepsOnlyAuthor() {
        var draft = readyDraft()
        draft.changesStatus = true

        draft.reset()

        #expect(draft == UpdateDraft(author: "Nurse Siti"))
    }

    @Test func sendingAddsUpdateForParents() throws {
        let store = NurseryStore()
        let baby = try #require(store.babies.last)
        let now = Date.now.addingTimeInterval(60)

        store.send(readyDraft(), to: baby.id, now: now)

        let updated = try #require(store.babies.last)
        #expect(updated.updates.count == baby.updates.count + 1)
        #expect(updated.updates.max()?.title == UpdateTemplate.fullFeed.title)
        #expect(updated.lastUpdated == now)
        #expect(updated.status == baby.status)
        #expect(store.unreadUpdateCount == 1)
    }

    @Test func sendingCanChangeStatus() throws {
        let store = NurseryStore()
        let baby = try #require(store.babies.last)
        var draft = readyDraft()
        draft.changesStatus = true
        draft.status = .readyForHome

        store.send(draft, to: baby.id)

        #expect(store.babies.last?.status == .readyForHome)
    }

    @Test func incompleteDraftIsNotSent() throws {
        let store = NurseryStore()
        let baby = try #require(store.babies.first)

        store.send(UpdateDraft(), to: baby.id)

        #expect(store.babies.first?.updates.count == baby.updates.count)
        #expect(store.unreadUpdateCount == 0)
    }

    @Test func readingUpdatesClearsBadge() throws {
        let store = NurseryStore()
        let baby = try #require(store.babies.first)
        store.send(readyDraft(), to: baby.id)

        store.markUpdatesRead()

        #expect(store.unreadUpdateCount == 0)
    }
}
