import Foundation

/// An update a nurse or doctor is writing for a baby's parents.
struct UpdateDraft: Equatable {
    var kind = UpdateKind.nurseNote
    var title = ""
    var detail = ""
    var author = ""
    var changesStatus = false
    var status = CareStatus.stable

    var isReadyToSend: Bool {
        title.trimmingCharacters(in: .whitespacesAndNewlines).isEmpty == false
            && author.trimmingCharacters(in: .whitespacesAndNewlines).isEmpty == false
    }

    /// Prefills the draft from a common template, keeping the author's name.
    mutating func apply(_ template: UpdateTemplate) {
        kind = template.kind
        title = template.title
        detail = template.detail
    }

    /// Clears the message but remembers who is signing, so staff don't retype their name.
    mutating func reset() {
        self = UpdateDraft(author: author)
    }

    func makeUpdate(now: Date) -> CareUpdate {
        CareUpdate(
            date: now,
            kind: kind,
            title: title.trimmingCharacters(in: .whitespacesAndNewlines),
            detail: detail.trimmingCharacters(in: .whitespacesAndNewlines),
            author: author.trimmingCharacters(in: .whitespacesAndNewlines)
        )
    }
}
