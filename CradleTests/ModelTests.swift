import Foundation
import Testing
@testable import Cradle

struct ModelTests {
    @Test(arguments: [
        (VitalKind.heartRate, 140.0, true),
        (VitalKind.heartRate, 190.0, false),
        (VitalKind.oxygen, 88.0, false),
        (VitalKind.temperature, 36.8, true)
    ])
    func typicalRange(kind: VitalKind, value: Double, expected: Bool) {
        #expect(Vital(kind: kind, value: value).isInTypicalRange == expected)
    }

    @Test func dayOfBirthIsDayOne() throws {
        let calendar = Calendar(identifier: .gregorian)
        let birth = try #require(calendar.date(from: DateComponents(year: 2026, month: 11, day: 14, hour: 23)))
        let sameDay = try #require(calendar.date(from: DateComponents(year: 2026, month: 11, day: 14, hour: 23, minute: 30)))
        let fiveDaysLater = try #require(calendar.date(from: DateComponents(year: 2026, month: 11, day: 19, hour: 1)))

        var baby = try #require(Baby.samples().first)
        baby.birthDate = birth

        #expect(baby.dayOfLife(on: sameDay, calendar: calendar) == 1)
        #expect(baby.dayOfLife(on: fiveDaysLater, calendar: calendar) == 6)
    }

    @Test func allFilterIncludesEveryKind() {
        #expect(UpdateKind.allCases.allSatisfy(UpdateFilter.all.includes))
    }

    @Test func everyKindBelongsToAFilterBesidesAll() {
        let specific = UpdateFilter.allCases.filter { $0 != .all }

        for kind in UpdateKind.allCases {
            #expect(specific.contains { $0.includes(kind) })
        }
    }
}
