import Foundation

struct Vital: Identifiable, Hashable {
    let kind: VitalKind
    var value: Double

    var id: VitalKind { kind }

    var isInTypicalRange: Bool {
        kind.typicalRange.contains(value)
    }
}
