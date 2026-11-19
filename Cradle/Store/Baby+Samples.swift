import Foundation

extension Baby {
    /// Demo twins so the baby switcher and both ward types can be explored.
    static func samples(now: Date = .now) -> [Baby] {
        let birth = now.addingTimeInterval(-4 * 86_400 - 3 * 3_600)

        func ago(minutes: Double) -> Date {
            now.addingTimeInterval(-minutes * 60)
        }

        func person(_ given: String, _ family: String) -> PersonNameComponents {
            PersonNameComponents(givenName: given, familyName: family)
        }

        let chloe = Baby(
            id: UUID(),
            name: person("Chloe", "Tan"),
            birthDate: birth,
            birthWeightInKilograms: 2.41,
            ward: .specialCareNursery,
            bed: "SCN 12",
            status: .stable,
            vitals: [
                Vital(kind: .heartRate, value: 138),
                Vital(kind: .oxygen, value: 98),
                Vital(kind: .breathing, value: 44),
                Vital(kind: .temperature, value: 36.8),
                Vital(kind: .weight, value: 2.36)
            ],
            feeding: FeedingInfo(
                lastFeed: ago(minutes: 75),
                nextFeed: now.addingTimeInterval(105 * 60),
                method: "Expressed breast milk by bottle",
                amountInMillilitres: 40
            ),
            nurse: person("Siti", "Rahman"),
            doctor: person("Wei Ming", "Lim"),
            nurseNote: "Chloe finished her whole feed and loved her skin-to-skin cuddle this morning. She's a little star!",
            updates: [
                CareUpdate(date: ago(minutes: 75), kind: .feed, title: "Finished a full feed", detail: "40 ml of expressed breast milk. Burped well afterwards.", author: "Nurse Siti"),
                CareUpdate(date: ago(minutes: 80), kind: .nappy, title: "Nappy change", detail: "Wet and dirty nappy – all normal.", author: "Nurse Siti"),
                CareUpdate(date: ago(minutes: 210), kind: .cuddle, title: "Skin-to-skin with Mum", detail: "45 minutes of kangaroo care. Heart rate stayed lovely and steady.", author: "Nurse Siti"),
                CareUpdate(date: ago(minutes: 300), kind: .doctorRound, title: "Morning ward round", detail: "Dr Lim is happy with Chloe's progress. Plan: keep building feeds.", author: "Dr Lim"),
                CareUpdate(date: ago(minutes: 1_300), kind: .weight, title: "Weighed tonight", detail: "2.36 kg – a normal small dip from her birth weight of 2.41 kg.", author: "Nurse Grace"),
                CareUpdate(date: ago(minutes: 1_420), kind: .nurseNote, title: "Settled night", detail: "Slept well between feeds and was easy to settle.", author: "Nurse Grace")
            ],
            lastUpdated: ago(minutes: 6)
        )

        let ethan = Baby(
            id: UUID(),
            name: person("Ethan", "Tan"),
            birthDate: birth,
            birthWeightInKilograms: 1.98,
            ward: .neonatalIntensiveCare,
            bed: "NICU 4",
            status: .closelyMonitored,
            vitals: [
                Vital(kind: .heartRate, value: 152),
                Vital(kind: .oxygen, value: 94),
                Vital(kind: .breathing, value: 58),
                Vital(kind: .temperature, value: 36.6),
                Vital(kind: .weight, value: 1.93)
            ],
            feeding: FeedingInfo(
                lastFeed: ago(minutes: 50),
                nextFeed: now.addingTimeInterval(130 * 60),
                method: "Tube feed of breast milk",
                amountInMillilitres: 22
            ),
            nurse: person("Priya", "Nair"),
            doctor: person("Hui Min", "Goh"),
            nurseNote: "Ethan is having some gentle breathing support to help him rest. He's comfortable and grabbed my finger today!",
            updates: [
                CareUpdate(date: ago(minutes: 50), kind: .feed, title: "Tube feed", detail: "22 ml of breast milk given slowly. Tolerated well.", author: "Nurse Priya"),
                CareUpdate(date: ago(minutes: 140), kind: .nurseNote, title: "Breathing support", detail: "Ethan is on a little extra air flow through his nose. This is very common for twins born early.", author: "Nurse Priya"),
                CareUpdate(date: ago(minutes: 290), kind: .doctorRound, title: "Morning ward round", detail: "Dr Goh says Ethan is stable. We'll review his breathing support tomorrow.", author: "Dr Goh"),
                CareUpdate(date: ago(minutes: 1_350), kind: .weight, title: "Weighed tonight", detail: "1.93 kg – a small, expected dip.", author: "Nurse Grace"),
                CareUpdate(date: ago(minutes: 1_500), kind: .cuddle, title: "Hand hug with Dad", detail: "Dad held Ethan's hand through the incubator for 20 minutes.", author: "Nurse Grace")
            ],
            lastUpdated: ago(minutes: 3)
        )

        return [chloe, ethan]
    }
}
