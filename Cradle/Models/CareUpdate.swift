import Foundation

struct CareUpdate: Identifiable, Hashable, Comparable {
    let id: UUID
    var date: Date
    var kind: UpdateKind
    var title: String
    var detail: String
    var author: String

    init(id: UUID = UUID(), date: Date, kind: UpdateKind, title: String, detail: String, author: String) {
        self.id = id
        self.date = date
        self.kind = kind
        self.title = title
        self.detail = detail
        self.author = author
    }

    static func < (lhs: CareUpdate, rhs: CareUpdate) -> Bool {
        lhs.date < rhs.date
    }
}
